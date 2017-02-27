package editor.fieldregistry.modifier;

import editor.fieldregistry.EditableField;
import editor.fieldregistry.LabelInputFieldMap;
import editor.fieldregistry.modifier.condition.ModifierCondition;
import lab.component.swing.Label;
import lab.component.swing.input.InputComponent;

public abstract class EditableFieldModifier {
	
	public static AliasModifier alias(Class<?> clazz, String name, ModifierCondition...conditions) {
		return new AliasModifier(clazz, name, conditions);
	}

	private final ModifierCondition[] conditions;
	
	public EditableFieldModifier(ModifierCondition...conditions) {
		this.conditions = conditions;
	}
	
	protected boolean allConditionsTrue(Object object, LabelInputFieldMap map) {
		for (ModifierCondition condition : conditions) {
			if (!condition.isTrue(object, map)) {
				return false;
			}
		}
		
		return true;
	}
	
	public abstract void run(EditableField field, Object object, Label label, InputComponent input, LabelInputFieldMap labelInputFieldMap);
	
}
