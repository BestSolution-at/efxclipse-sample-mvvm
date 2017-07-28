package sample.mvvm.ui.e4;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.fx.core.URLUtils;
import org.eclipse.fx.core.di.LocalInstance;
import org.eclipse.fx.core.log.Log;
import org.eclipse.fx.core.log.Logger;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;

public class PersonForm {
	@Inject
	@Log
	Logger logger;
	
	@PostConstruct
	void init(BorderPane parent, @LocalInstance FXMLLoader loader) {
		loader.setLocation(URLUtils.createUrl("platform:/plugin/sample.mvvm.ui/sample/mvvm/ui/PersonForm.fxml"));
		try {
			Node n = loader.load();
			parent.setCenter(n);
		} catch (IOException e) {
			logger.error("Failed to load 'PersonForm.fxml'", e);
		}
	}
}
