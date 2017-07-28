package sample.mvvm.vm.simple.e4;

import org.eclipse.e4.core.contexts.ContextFunction;
import org.eclipse.e4.core.contexts.ContextInjectionFactory;
import org.eclipse.e4.core.contexts.IContextFunction;
import org.eclipse.e4.core.contexts.IEclipseContext;
import org.osgi.service.component.annotations.Component;

import sample.mvvm.vm.simple.Mock_VM_PersonList;

@Component(property="service.context.key=sample.mvvm.vm.VM_PersonList")
public class Mock_VM_PersonListCF extends ContextFunction implements IContextFunction {
	@Override
	public Object compute(IEclipseContext context) {
		return ContextInjectionFactory.make(Mock_VM_PersonList.class, context);
	}
}
