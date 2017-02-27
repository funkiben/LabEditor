package editor.fieldregistry.modifier.condition;

import editor.fieldregistry.LabelInputFieldMap;

public abstract class ModifierCondition {

	public static InputValueCondition inputValueCondition(String fieldName, Object value) {
		return new InputValueCondition(fieldName, value);
	}
	
	public abstract boolean isTrue(Object object, LabelInputFieldMap map);

}
