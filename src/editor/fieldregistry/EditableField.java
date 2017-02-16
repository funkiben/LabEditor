package editor.fieldregistry;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lab.component.swing.SwingComponent;

public class EditableField {

	private final String name;
	private final Method getter;
	private final Method setter;
	private final SwingComponent input;
	
	public EditableField(String name, Method getter, Method setter, SwingComponent input) {
		this.name = name;
		this.getter = getter;
		this.setter = setter;
		this.input = input;
	}
	
	public SwingComponent getInputComponent() {
		return input;
	}
	
	public String getName() {
		return name;
	}
	
	public Object getValue(Object c) {
		try {
			return getter.invoke(c);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public void setValue(Object c, Object value) {
		try {
			setter.invoke(c, value);
		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
