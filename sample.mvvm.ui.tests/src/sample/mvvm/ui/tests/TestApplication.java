package sample.mvvm.ui.tests;

import org.junit.Assert;
import org.junit.Test;

import javafx.scene.control.TextField;
import sample.mvvm.ui.tests.product.E4TestFXTestCase;

public class TestApplication extends E4TestFXTestCase {
	@Test
	public void testSelection() {
		clickOn("#person-list-view").clickOn("Doe John");
		Assert.assertEquals("John", lookup("#firstname-textfield").<TextField>query().getText());
	}
}
