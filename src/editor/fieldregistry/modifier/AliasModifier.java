package editor.fieldregistry.modifier;

import editor.fieldregistry.EditableField;
import editor.fieldregistry.LabelInputFieldMap;
import editor.fieldregistry.modifier.condition.ModifierCondition;
import lab.component.swing.Label;
import lab.component.swing.input.InputComponent;

public class AliasModifier extends EditableFieldModifier {

	private final String name;
	private final Class<?> clazz;
	
	public AliasModifier(Class<?> clazz, String name, ModifierCondition...conditions) {
		super(conditions);
		this.clazz = clazz;
		this.name = name;
	}

	@Override
	public void run(EditableField field, Object object, Label label, InputComponent input, LabelInputFieldMap labelInputFieldMap) {
		if (clazz.isInstance(object)) {
			if (allConditionsTrue(object, labelInputFieldMap)) {
				if (!label.getText().equals(name)) {
					label.setText(name);
					label.setWidth(label.getTextWidth());
				}
			} else {
				if (label.getText().equals(name)) {
					label.setText(field.getName());
					label.setWidth(label.getTextWidth());
				}
			}
		}

	}

}
