package sample.mvvm.vm.simple.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Person {
	private final StringProperty firstname = new SimpleStringProperty(this, "firstname");
	private final StringProperty lastname = new SimpleStringProperty(this, "lastname");
	private final BooleanProperty married = new SimpleBooleanProperty(this, "married");
	private final StringProperty maidenname = new SimpleStringProperty(this, "maidenname");

	public Person(String firstname, String lastname, boolean married, String maidenname) {
		this.firstname.set(firstname);
		this.lastname.set(lastname);
		this.married.set(married);
		this.maidenname.set(maidenname);
	}

	public final StringProperty firstnameProperty() {
		return this.firstname;
	}

	public final String getFirstname() {
		return this.firstnameProperty().get();
	}

	public final void setFirstname(final String firstname) {
		this.firstnameProperty().set(firstname);
	}

	public final StringProperty lastnameProperty() {
		return this.lastname;
	}

	public final String getLastname() {
		return this.lastnameProperty().get();
	}

	public final void setLastname(final String lastname) {
		this.lastnameProperty().set(lastname);
	}

	public final BooleanProperty marriedProperty() {
		return this.married;
	}

	public final boolean isMarried() {
		return this.marriedProperty().get();
	}

	public final void setMarried(final boolean married) {
		this.marriedProperty().set(married);
	}

	public final StringProperty maidennameProperty() {
		return this.maidenname;
	}

	public final String getMaidenname() {
		return this.maidennameProperty().get();
	}

	public final void setMaidenname(final String maidenname) {
		this.maidennameProperty().set(maidenname);
	}

}
