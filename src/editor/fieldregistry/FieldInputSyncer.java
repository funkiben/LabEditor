package editor.fieldregistry;

import lab.component.swing.input.InputComponent;

public class FieldInputSyncer {

	private final Object object;
	private final InputComponent inputComponent;
	private final EditableField field;
	private Object value;
	
	public FieldInputSyncer(Object object, InputComponent inputComponent, EditableField field) {
		this.object = object;
		this.inputComponent = inputComponent;
		this.field = field;
		value = inputComponent.getValue();
	}
	
	public InputComponent getInput() {
		return inputComponent;
	}
	
	public EditableField getField() {
		return field;
	}
	
	public void sync(boolean checkField) {
		if (inputComponent.hasFocus()) {
			if (!value.equals(inputComponent.getValue())) {
				field.setValue(object, inputComponent.getValue());
				value = inputComponent.getValue();
			}
		} else if (checkField) {
			Object v = field.getValue(object); // doing reflection every frame is really bad, need to keep an eye on this
			
			if (!v.equals(value)) {
				value = v;
				inputComponent.setValue(value);
			}
		}
	}
	
}
