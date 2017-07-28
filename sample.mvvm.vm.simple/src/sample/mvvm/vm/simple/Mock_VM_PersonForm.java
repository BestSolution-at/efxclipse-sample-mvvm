package sample.mvvm.vm.simple;

import javax.inject.Inject;

import org.eclipse.fx.core.bindings.FXBindings;
import org.eclipse.fx.core.bindings.FXCollectors;
import org.eclipse.fx.core.di.ContextValue;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.StringProperty;
import sample.mvvm.vm.VM_PersonForm;
import sample.mvvm.vm.simple.model.Person;

public class Mock_VM_PersonForm implements VM_PersonForm {
	private final ObjectProperty<Person> selectedPerson;
	private final StringProperty firstname;
	private final StringProperty lastname;
	private final BooleanProperty married;
	private final StringProperty maidenname;
	
	@Inject
	public Mock_VM_PersonForm(@ContextValue("selectedPerson") Property<Person> selectedPerson) {
		this.selectedPerson = new SimpleObjectProperty<Person>();
		this.selectedPerson.bind(selectedPerson);
		
		this.firstname = FXBindings.bindStream(selectedPerson).collect(FXCollectors.toStringProperty( p -> p.firstnameProperty()));
		this.lastname = FXBindings.bindStream(selectedPerson).collect(FXCollectors.toStringProperty( p -> p.lastnameProperty()));
		this.married = FXBindings.bindStream(selectedPerson).collect(FXCollectors.toBooleanProperty( p -> p.marriedProperty()));
		this.maidenname = FXBindings.bindStream(selectedPerson).collect(FXCollectors.toStringProperty( p -> p.maidennameProperty()));
	}
	
	@Override
	public StringProperty firstname() {
		return this.firstname;
	}

	@Override
	public BooleanExpression firstname_enabled() {
		return selectedPerson.isNotNull();
	}

	@Override
	public StringProperty lastname() {
		return lastname;
	}

	@Override
	public BooleanExpression lastname_enabled() {
		return selectedPerson.isNotNull();
	}

	@Override
	public StringProperty maidenname() {
		return maidenname;
	}

	@Override
	public BooleanExpression maidenname_enabled() {
		return selectedPerson.isNotNull().and(married);
	}

	@Override
	public BooleanProperty married() {
		return married;
	}

	@Override
	public BooleanExpression married_enabled() {
		return selectedPerson.isNotNull();
	}

}
