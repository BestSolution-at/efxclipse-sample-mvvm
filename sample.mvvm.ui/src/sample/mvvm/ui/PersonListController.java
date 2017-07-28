package sample.mvvm.ui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;

import java.net.URL;
import java.util.ResourceBundle;

import javax.inject.Inject;
import org.eclipse.fx.core.observable.FXObservableUtil;
import sample.mvvm.vm.VM_PersonList;
import sample.mvvm.vm.VM_PersonList.ListItem;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;

public class PersonListController implements Initializable {
	
	@FXML ListView<VM_PersonList.ListItem> personListView;
	@FXML Button newButton;
	@FXML Button delButton;

	private final VM_PersonList vm;
	
	@Inject
	public PersonListController(VM_PersonList vm) {
		this.vm = vm;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		personListView.setCellFactory(ListItemCell::new);
		personListView.setItems(vm.items());
		
		newButton.disableProperty().bind(vm.newPerson().enabledProperty().not());
		delButton.disableProperty().bind(vm.delPerson().enabledProperty().not());
		
		MultipleSelectionModel<ListItem> selModel = personListView.getSelectionModel();
		if( vm.selectedItem().get() != null ) {
			selModel.select(vm.selectedItem().get());
		}
		FXObservableUtil.onChange(selModel.selectedItemProperty(), vm.selectedItem()::set);
		FXObservableUtil.onChange(vm.selectedItem(), selModel::select);
	}
	
	@FXML 
	public void newPerson() {
		vm.newPerson().execute();
	}

	@FXML 
	public void deletePerson() {
		vm.delPerson().execute();
	}

	static class ListItemCell extends ListCell<VM_PersonList.ListItem> {
		public ListItemCell(ListView<VM_PersonList.ListItem> v) {
		}
		
		@Override
		protected void updateItem(ListItem item, boolean empty) {
			super.updateItem(item, empty);
			
			textProperty().unbind();
			if( item != null && ! empty ) {
				textProperty().bind(item.label());
			} else {
				textProperty().set("");
			}
		}
	}
}
