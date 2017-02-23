package editor.fieldregistry.modifier.condition;

import editor.fieldregistry.LabelInputFieldMap;

public class InputValueCondition extends ModifierCondition {
	
	private final String fieldName;
	private final Object value;
	
	public InputValueCondition(String fieldName, Object value) {
		this.fieldName = fieldName;
		this.value = value;
	}

	@Override
	public boolean isTrue(Object object, LabelInputFieldMap map) {
		return map.getInput(fieldName).getValue().equals(value);
	}

}
