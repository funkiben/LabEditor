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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getter == null) ? 0 : getter.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EditableField))
			return false;
		EditableField other = (EditableField) obj;
		if (getter == null) {
			if (other.getter != null)
				return false;
		} else if (!getter.equals(other.getter))
			return false;
		return true;
	}
	
	
}
