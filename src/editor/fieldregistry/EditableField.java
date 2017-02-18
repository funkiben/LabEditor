package editor.fieldregistry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EditableField {

	private final String name;
	private final Method getter;
	private final Method setter;
	private final InputComponentInstantiator inputComponentInstantiator;
	
	public EditableField(String name, Method getter, Method setter, InputComponentInstantiator inputComponentInstantiator) {
		this.name = name;
		this.getter = getter;
		this.setter = setter;
		this.inputComponentInstantiator = inputComponentInstantiator;
	}
	
	public InputComponentInstantiator getInputComponentInstantiator() {
		return inputComponentInstantiator;
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
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof EditableField)) {
			return false;
		}
		EditableField other = (EditableField) obj;
		if (name == null) {
			if (other.name != null) {
				return false;
			}
		} else if (!name.equals(other.name)) {
			return false;
		}
		return true;
	}
	

	
}
