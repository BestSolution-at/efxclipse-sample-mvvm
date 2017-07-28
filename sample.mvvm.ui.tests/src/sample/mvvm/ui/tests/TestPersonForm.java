//package sample.mvvm.ui.tests;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.testfx.framework.junit.ApplicationTest;
//
//import javafx.beans.property.Property;
//import javafx.beans.property.SimpleObjectProperty;
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.scene.control.TextField;
//import javafx.stage.Stage;
//import sample.mvvm.ui.PersonFormController;
//import sample.mvvm.ui.PersonListController;
//import sample.mvvm.vm.simple.Mock_VM_PersonForm;
//import sample.mvvm.vm.simple.model.Person;
//
//public class TestPersonForm extends ApplicationTest {
//	private Property<Person> selectedPerson = new SimpleObjectProperty<>();
//	
//	@Override
//	public void start(Stage stage) throws Exception {
//		FXMLLoader l = new FXMLLoader();
//		l.setControllerFactory( v -> {
//			return new PersonFormController( new Mock_VM_PersonForm(selectedPerson) );
//		});
//		l.setLocation(PersonListController.class.getResource("PersonForm.fxml"));
//		
//		Parent root = l.load();
//		Scene s = new Scene(root, 400, 400);
//		stage.setScene(s);
//		stage.show();
//	}
//	
//	@Test
//	public void testSelection() {
//		selectedPerson.setValue(new Person("John", "Doe", false, null));
//		Assert.assertEquals("John", lookup("#firstname-textfield").<TextField>query().getText());
//	}
//}
