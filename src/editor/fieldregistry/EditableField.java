package editor.fieldregistry;

import java.lang.reflect.Field;

import lab.component.LabComponent;
import lab.component.swing.SwingComponent;

public class EditableField {

	private final Field field;
	private final String name;
	private final SwingComponent input;
	
	public EditableField(Field field, String name, SwingComponent input) {
		this.field = field;
		this.name = name;
		this.input = input;
	}
	
	public EditableField(Field field, SwingComponent input) {
		this(field, field.getName(), input);
	}
	
	public SwingComponent getInputComponent() {
		return input;
	}
	
	public String getName() {
		return name;
	}
	
	public Object getValue(LabComponent c) {
		try {
			return field.get(c);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void setValue(LabComponent c, Object value) {
		try {
			field.set(c, value);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
