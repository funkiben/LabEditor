package editor.fieldregistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import lab.component.swing.Label;
import lab.component.swing.input.InputComponent;

public class LabelInputFieldMap extends HashMap<String, Object[]> {
	
	private static final long serialVersionUID = 1L;

	private final List<Label> labels = new ArrayList<Label>();
	private final List<InputComponent> inputs = new ArrayList<InputComponent>();
	private final List<EditableField> fields = new ArrayList<EditableField>();
	
	public void put(String id, Label label, InputComponent input, EditableField field) {
		put(id, new Object[] { label, input, field});
		
		labels.add(label);
		inputs.add(input);
		fields.add(field);
	}
	
	public Label getLabel(String fieldName) {
		Object[] values = get(fieldName);
		
		if (values != null) {
			return (Label) values[0];
		}
		
		return null;
	}
	
	public List<Label> getLabels() {
		return labels;
	}
	
	public InputComponent getInput(String fieldName) {
		Object[] values = get(fieldName);
		
		if (values != null) {
			return (InputComponent) values[1];
		}
		
		return null;
	}
	
	public List<InputComponent> getInputs() {
		return inputs;
	}
	
	public EditableField getField(String fieldName) {
		Object[] values = get(fieldName);
		
		if (values != null) {
			return (EditableField) values[2];
		}
		
		return null;
	}
	
	public List<EditableField> getFields() {
		return fields;
	}
	
	public void runModifiers(Object object) {
		for (Object[] value : values()) {
			((EditableField) value[2]).runModifiers(object, (Label) value[0], (InputComponent) value[1], this);
		}
	}
	
}
