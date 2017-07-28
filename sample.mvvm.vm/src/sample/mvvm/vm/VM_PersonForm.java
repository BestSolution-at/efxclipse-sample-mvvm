package sample.mvvm.vm;

import javafx.beans.binding.BooleanExpression;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.StringProperty;

public interface VM_PersonForm {

	StringProperty firstname();
	BooleanExpression firstname_enabled();
	
	StringProperty lastname();
	BooleanExpression lastname_enabled();
	
	StringProperty maidenname();
	BooleanExpression maidenname_enabled();
	
	BooleanProperty married();
	BooleanExpression married_enabled();
}
