//package sample.mvvm.ui.tests;
//
//import java.util.function.Consumer;
//
//import org.junit.Assert;
//import org.junit.Test;
//import org.testfx.framework.junit.ApplicationTest;
//
//import javafx.fxml.FXMLLoader;
//import javafx.scene.Parent;
//import javafx.scene.Scene;
//import javafx.stage.Stage;
//import sample.mvvm.ui.PersonListController;
//import sample.mvvm.vm.simple.Mock_VM_PersonList;
//import sample.mvvm.vm.simple.model.Person;
//
//public class TestPersonList extends ApplicationTest {
//	private Person selectedPerson;
//	
//	@Override
//	public void start(Stage stage) throws Exception {
//		Consumer<Person> selectedPerson = v -> {
//			this.selectedPerson = v;
//		};
//
//		FXMLLoader l = new FXMLLoader();
//		l.setControllerFactory( v -> {
//			return new PersonListController( new Mock_VM_PersonList(selectedPerson));
//		});
//		l.setLocation(PersonListController.class.getResource("PersonList.fxml"));
//		
//		Parent root = l.load();
//		Scene s = new Scene(root, 400, 400);
//		stage.setScene(s);
//		stage.show();
//	}
//	
//	@Test
//	public void testSelection() {
//		clickOn("#person-list-view").clickOn("Doe John");
//		Assert.assertNotNull(selectedPerson);
//	}
//}
