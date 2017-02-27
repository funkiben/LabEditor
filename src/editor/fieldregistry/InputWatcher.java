package editor.fieldregistry;

import lab.component.swing.input.InputComponent;

public abstract class InputWatcher {

	private final InputComponent inputComponent;
	private Object value;
	
	public InputWatcher(InputComponent inputComponent) {
		this.inputComponent = inputComponent;
		value = inputComponent.getValue();
	}
	
	public InputComponent getComponent() {
		return inputComponent;
	}
	
	public void check() {
		if (!value.equals(inputComponent.getValue())) {
			onChangeDetected(value, inputComponent.getValue());
			value = inputComponent.getValue();
		}
	}
	
	public abstract void onChangeDetected(Object previousValue, Object newValue);

}
