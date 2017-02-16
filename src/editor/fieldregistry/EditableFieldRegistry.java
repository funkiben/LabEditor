package editor.fieldregistry;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.sun.prism.paint.Color;

import lab.component.BunsenBurner;
import lab.component.GraduatedComponent;
import lab.component.Graduation;
import lab.component.LabComponent;
import lab.component.MeasurableComponent;
import lab.component.Piston;
import lab.component.fx.Flame;
import lab.component.geo.Oval;
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
		
		/*
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
		
		registerField(Piston.class, "gasColor", null);
		
		registerField(Color.class, "r", new NumberField(100, NUMBER_FIELD_HEIGHT, "#.###"));
		registerField(Color.class, "g", new NumberField(100, NUMBER_FIELD_HEIGHT, "#.###"));
		registerField(Color.class, "b", new NumberField(100, NUMBER_FIELD_HEIGHT, "#.###"));
		
		registerField(BunsenBurner.class, "flame", null);
		registerField(Flame.class, "resolutionX", new NumberField(100, NUMBER_FIELD_HEIGHT, "###"));
		registerField(Flame.class, "resolutionY", new NumberField(100, NUMBER_FIELD_HEIGHT, "###"));
		registerField(Flame.class, "intensity", "Amount", new NumberField(100, NUMBER_FIELD_HEIGHT, "###"));
		registerField(Flame.class, "noiseFrequency", "Density", new NumberField(100, NUMBER_FIELD_HEIGHT, "##.#"));
		registerField(Flame.class, "noiseIncrement", "Speed", new NumberField(100, NUMBER_FIELD_HEIGHT, "##"));
		*/
		
	}
	
	public static List<EditableField> getEditableFields(Class<?> c) {
		List<EditableField> editableFields = new ArrayList<EditableField>();
		
		editableFields.addAll(registry.get(c));
		
		if (c.getSuperclass() != Object.class) {
			editableFields.addAll(getEditableFields((Class<?>) c.getSuperclass()));
		}
		
		return editableFields;
	}
	
	private static void registerField(Class<?> clazz, String getter, String setter, String name, SwingComponent input) {
		Method getterMethod, setterMethod;
		
		try {
			getterMethod = clazz.getMethod(getter);
			setterMethod = clazz.getMethod(setter);
			
			getterMethod.setAccessible(true);
			setterMethod.setAccessible(true);
			
		} catch (SecurityException | IllegalArgumentException | NoSuchMethodException e) {
			e.printStackTrace();
			return;
			
		}
		
		List<EditableField> fields;

		EditableField editableField = new EditableField(name, getterMethod, setterMethod, input);
		
		if (registry.containsKey(clazz)) {
			fields = registry.get(clazz);
			fields.add(editableField);
		} else {
			fields = new ArrayList<EditableField>();
			
			fields.add(editableField);
			
			registry.put(clazz,  fields);
		}
	}

}
