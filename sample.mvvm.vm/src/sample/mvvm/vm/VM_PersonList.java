package sample.mvvm.vm;

import javafx.beans.binding.StringExpression;
import javafx.beans.property.ObjectProperty;
import javafx.collections.ObservableList;
import org.eclipse.fx.core.command.Command;

public interface VM_PersonList {
	ObservableList<ListItem> items();
	ObjectProperty<ListItem> selectedItem();
	Command<Void> newPerson();
	Command<Void> delPerson();
	
	interface ListItem {
		public StringExpression label();
	}
}
