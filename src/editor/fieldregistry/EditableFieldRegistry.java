package editor.fieldregistry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lab.component.LabComponent;
import lab.component.MeasurableComponent;
import lab.component.swing.SwingComponent;
import lab.component.swing.input.CheckBox;
import lab.component.swing.input.NumberField;

public class EditableFieldRegistry {

	private static final Map<Class<? extends LabComponent>, List<EditableField>> registry = new HashMap<Class<? extends LabComponent>, List<EditableField>>();
	private static Field modifiersField;
	
	static {
		try {
			modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		registerField(LabComponent.class, "offsetX", "X", new NumberField(100, 20, "####"));
		registerField(LabComponent.class, "offsetY", "Y", new NumberField(100, 20, "####"));
		registerField(LabComponent.class, "zOrder", "Z", new NumberField(100, 20, "####"));
		registerField(LabComponent.class, "width", "Width", new NumberField(100, 20, "####"));
		registerField(LabComponent.class, "height", "Height", new NumberField(100, 20, "####"));
		registerField(LabComponent.class, "visible", "Visible", new CheckBox(30, 30, ""));
		
		registerField(MeasurableComponent.class, "value", "Value", new NumberField(100, 20, "####"));
		registerField(MeasurableComponent.class, "showValue", "Show Value", new CheckBox(30, 30, ""));
		
		
		
		
	}
	
	@SuppressWarnings("unchecked")
	public static List<EditableField> getEditableFields(Class<? extends LabComponent> c) {
		List<EditableField> editableFields = new ArrayList<EditableField>();
		
		editableFields.addAll(registry.get(c));
		
		if (c.getSuperclass() != Object.class) {
			editableFields.addAll(getEditableFields((Class<? extends LabComponent>) c.getSuperclass()));
		}
		
		return editableFields;
	}
	
	public static Set<Class<? extends LabComponent>> getEditableLabComponents() {
		return registry.keySet();
	}
	
	public static void registerField(Class<? extends LabComponent> clazz, String fieldName, SwingComponent input) {
		registerField(clazz, fieldName, fieldName, input);
	}
	
	public static void registerField(Class<? extends LabComponent> clazz, String fieldName, String aliasName, SwingComponent input) {
		Field field = null;
		
		try {
			field = findAndPrepareField(clazz, fieldName);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
			return;
		}
		
		List<EditableField> fields;

		if (registry.containsKey(clazz)) {
			fields = registry.get(clazz);
			fields.add(new EditableField(field, aliasName, input));
			
		} else {
			fields = new ArrayList<EditableField>();
			
			fields.add(new EditableField(field, aliasName, input));
			
			registry.put(clazz,  fields);
		}

	}

	private static Field findAndPrepareField(Class<? extends LabComponent> clazz, String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = clazz.getDeclaredField(fieldName);

		field.setAccessible(true);

		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		
		return field;
	}
	

}
