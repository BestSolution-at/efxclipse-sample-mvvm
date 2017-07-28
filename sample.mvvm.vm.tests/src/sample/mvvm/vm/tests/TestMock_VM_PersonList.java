package sample.mvvm.vm.tests;

import java.util.concurrent.atomic.AtomicBoolean;

import org.junit.Assert;
import org.junit.Test;
import sample.mvvm.vm.simple.Mock_VM_PersonList;

public class TestMock_VM_PersonList {
	@Test
	public void testSelectionPropagation() {
		AtomicBoolean called = new AtomicBoolean();
		
		Mock_VM_PersonList vm = new Mock_VM_PersonList( v -> called.set(true) );
		vm.selectedItem().set(vm.items().get(0));
		Assert.assertTrue(called.get());
	}
}
