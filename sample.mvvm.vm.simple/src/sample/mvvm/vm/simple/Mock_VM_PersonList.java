package sample.mvvm.vm.simple;

import java.util.Map;
import java.util.function.Consumer;

import javax.inject.Inject;

import org.eclipse.fx.core.bindings.FXBindings;
import org.eclipse.fx.core.command.Command;
import org.eclipse.fx.core.di.ContextValue;
import org.eclipse.fx.core.observable.FXObservableUtil;

import javafx.beans.binding.StringExpression;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.mvvm.vm.VM_PersonList;
import sample.mvvm.vm.simple.model.Person;

public class Mock_VM_PersonList implements VM_PersonList {

	private static ObservableList<Person> DATASOURCE = FXCollections.observableArrayList();
	
	static 
	{
		DATASOURCE.add(new Person("John", "Doe", true, null));
		DATASOURCE.add(new Person("Jane", "Doe", true, "Roe"));
	}
	
	private final ObservableList<ListItem> items = FXCollections.observableArrayList();
	private final ObservableList<ListItem> ro_items = FXCollections.unmodifiableObservableList(items);
	private final ObjectProperty<ListItem> selectedItem = new SimpleObjectProperty<>(this, "selectedItem");
	
	private final Command<Void> newPerson;
	private final Command<Void> delPerson;
	
	@Inject
	public Mock_VM_PersonList(@ContextValue("selectedPerson") Consumer<Person> selectedPerson) {
		FXBindings.bindContent(items, DATASOURCE, ListItemImpl::new);
		this.newPerson = Command.createCommand(this::_newPerson);
		this.delPerson = Command.createCommand(this::_delPerson, this::_delEnabled);
		this.delPerson.evaluate(); // Bug should not be required
		FXObservableUtil.onChange(this.selectedItem, v -> {
			this.delPerson.evaluate();
			if( v != null ) {
				selectedPerson.accept(((ListItemImpl)v).p);
			}
		});
	}
	
	private void _newPerson() {
		DATASOURCE.add(new Person("<Firstname>", "<Lastname>", false, null));
		selectedItem.set(items.get(items.size()-1));
	}
	
	private Void _delPerson(Map<String, String> parameters) {
		ListItem item = selectedItem.get();
		if( item != null ) {
			int idx = items.indexOf(item);
			DATASOURCE.remove(((ListItemImpl)item).p);
			if( idx >= items.size() ) {
				idx = items.size() - 1;
			}
			if( idx >= 0 ) {
				selectedItem.set(items.get(idx));
			}
		}
		return null;
	}
	
	private boolean _delEnabled(Map<String, String> parameters) {
		return selectedItem.get() != null;
	}
	
	@Override
	public ObservableList<ListItem> items() {
		return ro_items;
	}

	@Override
	public ObjectProperty<ListItem> selectedItem() {
		return selectedItem;
	}
	
	@Override
	public Command<Void> newPerson() {
		return newPerson;
	}
	
	@Override
	public Command<Void> delPerson() {
		return delPerson;
	}

	static class ListItemImpl implements ListItem {
		private final Person p;
		
		public ListItemImpl(Person p) {
			this.p = p;
		}
		
		@Override
		public StringExpression label() {
			return p.lastnameProperty().concat( " " ).concat( p.firstnameProperty() );
		}
		
	}
}
