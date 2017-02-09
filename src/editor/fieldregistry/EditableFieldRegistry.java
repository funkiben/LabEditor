package editor.fieldregistry;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lab.component.BunsenBurner;
import lab.component.GraduatedComponent;
import lab.component.Graduation;
import lab.component.LabComponent;
import lab.component.MeasurableComponent;
import lab.component.fx.Flame;
import lab.component.swing.SwingComponent;
import lab.component.swing.input.CheckBox;
import lab.component.swing.input.NumberField;

public class EditableFieldRegistry {

	private static final Map<Class<?>, List<EditableField>> registry = new HashMap<Class<?>, List<EditableField>>();
	private static Field modifiersField;
	
	private static final int NUMBER_FIELD_HEIGHT = 20;
	private static final int CHECKBOX_WIDTH = 30;
	private static final int CHECKBOX_HEIGHT = 30;
	
	static {
		try {
			modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		registerField(LabComponent.class, "offsetX", "X", new NumberField(100, NUMBER_FIELD_HEIGHT, "####"));
		registerField(LabComponent.class, "offsetY", "Y", new NumberField(100, NUMBER_FIELD_HEIGHT, "####"));
		registerField(LabComponent.class, "zOrder", "Z", new NumberField(100, NUMBER_FIELD_HEIGHT, "####"));
		registerField(LabComponent.class, "width", new NumberField(100, NUMBER_FIELD_HEIGHT, "####"));
		registerField(LabComponent.class, "height", new NumberField(100, NUMBER_FIELD_HEIGHT, "####"));
		registerField(LabComponent.class, "visible", new CheckBox(CHECKBOX_WIDTH, CHECKBOX_HEIGHT, ""));
		
		registerField(MeasurableComponent.class, "value", new NumberField(100, NUMBER_FIELD_HEIGHT, "####"));
		registerField(MeasurableComponent.class, "showValue", "show value", new CheckBox(CHECKBOX_WIDTH, CHECKBOX_HEIGHT, ""));
		
		registerField(GraduatedComponent.class, "graduation", null);
		registerField(Graduation.class, "start", new NumberField(100, NUMBER_FIELD_HEIGHT, "####"));
		registerField(Graduation.class, "end", new NumberField(100, NUMBER_FIELD_HEIGHT, "####"));
		registerField(Graduation.class, "lineIntervals", new NumberField(100, NUMBER_FIELD_HEIGHT, "###.##"));
		registerField(Graduation.class, "subLineIntervals", new NumberField(100, NUMBER_FIELD_HEIGHT, "###.##"));
		
		registerField(BunsenBurner.class, "flame", null);
		registerField(Flame.class, "resolutionX", new NumberField(100, NUMBER_FIELD_HEIGHT, "###"));
		registerField(Flame.class, "resolutionY", new NumberField(100, NUMBER_FIELD_HEIGHT, "###"));
		
		
	}
	
	public static List<EditableField> getEditableFields(Class<?> c) {
		List<EditableField> editableFields = new ArrayList<EditableField>();
		
		editableFields.addAll(registry.get(c));
		
		if (c.getSuperclass() != Object.class) {
			editableFields.addAll(getEditableFields((Class<?>) c.getSuperclass()));
		}
		
		return editableFields;
	}
	
	public static Set<Class<?>> getEditableLabComponents() {
		return registry.keySet();
	}
	
	public static void registerField(Class<?> clazz, String fieldName, SwingComponent input) {
		char[] chars = fieldName.toCharArray();
		
		chars[0] += 32;
		
		for (int i = 1 ; i < chars.length - 1; i++) {
			if (chars[i] >= 65 && chars[i] <= 90) {
				chars[i + 1] += 32;
			}
		}
		
		registerField(clazz, fieldName, new String(chars), input);
	}
	
	public static void registerField(Class<?> clazz, String fieldName, String aliasName, SwingComponent input) {
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

	private static Field findAndPrepareField(Class<?> clazz, String fieldName) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field field = clazz.getDeclaredField(fieldName);

		field.setAccessible(true);

		modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
		
		return field;
	}
	

}
