package sample.mvvm.ui.tests.product;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.fx.core.databinding.JFXRealm;
import org.eclipse.fx.ui.services.Constants;
import org.junit.Before;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.ServiceReference;
import org.osgi.service.application.ApplicationDescriptor;
import org.osgi.service.application.ApplicationException;
import org.osgi.service.application.ApplicationHandle;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.stage.Stage;

public abstract class E4TestFXTestCase extends ApplicationTest {
	private static Stage stage;
	private static final String applicationId = "sample.mvvm.ui.tests.id1";
	private static final String productId = System.getProperty("testfx.osgi.product.id");
	private BooleanProperty applicationLaunched = new SimpleBooleanProperty(false);
	private ApplicationHandle runningApplication;

	public E4TestFXTestCase() {
		if( stage == null ) {
			EventHandler handler = new EventHandler() {
				public void handleEvent(final Event event) {
					applicationLaunched.set(true);
				}
			};
			Dictionary<String, String> properties = new Hashtable<String, String>();
			properties.put(EventConstants.EVENT_TOPIC, Constants.APPLICATION_LAUNCHED);
			getBundleContext().registerService(EventHandler.class, handler, properties);
		}
	}
	
	private BundleContext getBundleContext() {
		return FrameworkUtil.getBundle(E4TestFXApplication.class).getBundleContext();
	}

	@Before
	public void test() {
		try {
			WaitForAsyncUtils.waitFor(10000, TimeUnit.MILLISECONDS, applicationLaunched);
		} catch (TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void start(Stage arg0) throws Exception {
		if( E4TestFXTestCase.stage == null ) {
			E4TestFXTestCase.stage = arg0;
			JFXRealm.createDefault();
			applicationLaunched.set(false);
			String tmp = applicationId;

			final List<String> brandingArgs = new ArrayList<String>();
			if (productId != null) {
				IConfigurationElement[] elements = RegistryFactory.getRegistry().getConfigurationElementsFor("org.eclipse.core.runtime", "products", productId);
				if (elements != null && elements.length > 0) {
					for (IConfigurationElement prop : elements[0].getChildren("property")) {
						brandingArgs.add("-" + prop.getAttribute("name"));
						brandingArgs.add(prop.getAttribute("value"));
					}
				}
			}

			final String applicationId = tmp;

			try {
				BundleContext context = getBundleContext();
				Collection<ServiceReference<ApplicationDescriptor>> col = context.getServiceReferences(ApplicationDescriptor.class, "(service.pid=" + applicationId + ")");
				if (col.isEmpty()) {
					System.err.println("There's no application with ID '" + applicationId + "' known.");
					throw new IllegalStateException("There's no application with ID '" + applicationId + "' known.");
				} else if (col.size() > 1) {
					System.err.println("There are more than 1 application with ID '" + applicationId + "' known.");
					throw new IllegalStateException("There are more than 1 application with ID '" + applicationId + "' known.");
				} else {
					ServiceReference<ApplicationDescriptor> ref = col.iterator().next();
					final ApplicationDescriptor desc = getBundleContext().getService(ref);
					new Thread() {
						public void run() {
							try {
								Map<String, Object> test = new HashMap<String, Object>();
								if (!brandingArgs.isEmpty()) {
									test.put(IApplicationContext.APPLICATION_ARGS, brandingArgs.toArray(new String[0]));
								}

								runningApplication = desc.launch(test);
							} catch (ApplicationException e) {
								System.err.println("Failed to launch application '" + applicationId + "'");
								e.printStackTrace();
							}
						}
					}.start();
				}
			} catch (Exception e) {
				throw new IllegalStateException(e);
			}
		} else {
			applicationLaunched.set(true);
		}
	}

	@Override
	public void stop() throws Exception {
		super.stop();
	}
}
