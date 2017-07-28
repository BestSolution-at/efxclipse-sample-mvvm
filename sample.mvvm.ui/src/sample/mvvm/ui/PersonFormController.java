package sample.mvvm.ui;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;

import sample.mvvm.vm.VM_PersonForm;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;

public class PersonFormController implements Initializable {
	private final VM_PersonForm vm;
	
	@FXML TextField firstname_textfield;
	@FXML TextField lastname_textfield;
	@FXML CheckBox married_checkbox;
	@FXML TextField maidenname_textfield;
	
	@Inject
	public PersonFormController(VM_PersonForm vm) {
		this.vm = vm;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		firstname_textfield.textProperty().bindBidirectional(vm.firstname());
		firstname_textfield.disableProperty().bind(vm.firstname_enabled().not());
		
		lastname_textfield.textProperty().bindBidirectional(vm.lastname());
		lastname_textfield.disableProperty().bind(vm.lastname_enabled().not());
		
		married_checkbox.selectedProperty().bindBidirectional(vm.married());
		married_checkbox.disableProperty().bind(vm.married_enabled().not());
		
		maidenname_textfield.textProperty().bindBidirectional(vm.maidenname());
		maidenname_textfield.disableProperty().bind(vm.maidenname_enabled().not());
	}
}
