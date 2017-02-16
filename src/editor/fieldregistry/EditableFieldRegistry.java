package editor.fieldregistry;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import editor.input.ChangeColorButton;
import lab.component.BunsenBurner;
import lab.component.GraduatedComponent;
import lab.component.Graduation;
import lab.component.LabComponent;
import lab.component.MeasurableComponent;
import lab.component.Piston;
import lab.component.fx.Flame;
import lab.component.swing.SwingComponent;
import lab.component.swing.input.CheckBox;
import lab.component.swing.input.DoubleField;
import lab.component.swing.input.IntegerField;

public class EditableFieldRegistry {

	private static final Map<Class<?>, List<EditableField>> registry = new HashMap<Class<?>, List<EditableField>>();
	private static Field modifiersField;
	
	private static final int CHECKBOX_WIDTH = 30;
	private static final int CHECKBOX_HEIGHT = 30;
	
	private static Class<?> currentClass = null;
	
	static {
		try {
			modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
		
		currentClass = LabComponent.class;
		registerField("getOffsetX", "setOffsetX", "X", integerField(0, 9999));
		registerField("getOffsetY", "setOffsetY", "Y", integerField(0, 9999));
		registerField("getZOrder", "setZOrder", "Z", integerField(-9999, 9999));
		registerField("getWidth", "setWidth", "Width", integerField(1, 9999));
		registerField("getHeight", "setHeight", "Height", integerField(1, 9999));
		registerField("isVisible", "setVisible", "Visible", checkBox());
		
		currentClass = MeasurableComponent.class;
		registerField("getValue", "setValue", "Value", doubleField(0, 9999, 4));
		registerField("canShowValue", "setShowValue", "Show Value", checkBox());
		
		registerField(GraduatedComponent.class, "getGraduation", "setGraduation", "Graduation", null);
		
		currentClass = Graduation.class;
		registerField("getStart", "setStart", "Start", doubleField(Double.MIN_VALUE, Double.MAX_VALUE, 5, 5));
		registerField("getEnd", "setEnd", "End", doubleField(Double.MIN_VALUE, Double.MAX_VALUE, 5, 5));
		registerField("getLineIntervals", "setLineIntervals", "Tick Intervals", doubleField(Double.MIN_VALUE, Double.MAX_VALUE, 5, 5));
		registerField("getSubLineIntervals", "setSubLineIntervals", "Subtick Intervals", doubleField(Double.MIN_VALUE, Double.MAX_VALUE, 5, 5));
		
		registerField(Piston.class, "getGasColor", "setGasColor", "Gas Color", new ChangeColorButton(150, 50, "Change Gas Color"));
		
		registerField(BunsenBurner.class, "getFlame", "setFlame", "Flame", null);
		
		currentClass = Flame.class;
		registerField("getResolutionX", "setResolutionX", "X Resolution", integerField(1, 200));
		registerField("getResolutionY", "setResolutionY", "Y Resolution", integerField(1, 200));
		
		/*
		
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
	
	private static DoubleField doubleField(double min, double max, int sigfigs, int scientificNotationMinPower) {
		return new DoubleField(50, min, max, sigfigs, scientificNotationMinPower);
	}
	
	private static DoubleField doubleField(double min, double max, int sigfigs) {
		return new DoubleField(50, min, max, sigfigs);
	}
	
	private static IntegerField integerField(int min, int max) {
		return new IntegerField(50, min, max);
	}
	
	private static CheckBox checkBox() {
		return new CheckBox(CHECKBOX_WIDTH, CHECKBOX_HEIGHT, "");
	}
	
	private static void registerField(String getter, String setter, String name, SwingComponent input) {
		registerField(currentClass, getter, setter, name, input);
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
	
	public static Set<EditableField> getEditableFields(Class<?> c) {
		Set<EditableField> editableFields = new HashSet<EditableField>();
		
		if (c.getSuperclass() != Object.class) {
			editableFields.addAll(getEditableFields((Class<?>) c.getSuperclass()));
		}
		
		editableFields.addAll(registry.get(c));
		
		return editableFields;
	}

}
