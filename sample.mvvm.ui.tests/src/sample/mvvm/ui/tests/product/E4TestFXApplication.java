package sample.mvvm.ui.tests.product;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.fx.core.app.ApplicationContext;
import org.eclipse.fx.core.app.ApplicationLocation;
import org.eclipse.fx.ui.workbench.fx.E4Application;

import javafx.application.Application;
import javafx.stage.Stage;

@SuppressWarnings("restriction")
public class E4TestFXApplication extends E4Application {

	@Override
	public Object start(IApplicationContext context) throws Exception {
		return super.start(context);
	}

	@Override
	protected void launchE4JavaFxApplication() throws Exception {
		ApplicationContext applicationContext = getApplicationContext();
		jfxStart(applicationContext, null, null);
	}

	@Override
	public boolean initE4Workbench(ApplicationContext context, Application jfxApplication, Stage primaryStage) {
		boolean initE4Workbench = super.initE4Workbench(context, jfxApplication, primaryStage);
		return initE4Workbench;
	}
	
	@Override
	public boolean checkInstanceLocation(ApplicationLocation instanceLocation,
			IEclipseContext context) {
		return true;
	}
}