package editor.fieldregistry;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
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
import lab.component.container.Container;
import lab.component.container.ContentState;
import lab.component.fx.Flame;
import lab.component.sensor.Manometer; 
import lab.component.swing.input.CheckBox;
import lab.component.swing.input.DoubleField;
import lab.component.swing.input.IntegerField;
import lab.component.swing.input.IntegerSlider;
import lab.component.swing.input.DoubleSlider;
import lab.component.swing.input.DropdownMenu;
import lab.component.swing.input.InputComponent;

public class EditableFieldRegistry {

	private static final Map<Class<?>, List<EditableField>> registry = new HashMap<Class<?>, List<EditableField>>();
	
	private static Class<?> currentClass = null;
	
	static {
		
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
		registerField("getStart", "setStart", "Graduation Start", doubleField(5, 5));
		registerField("getEnd", "setEnd", "Graduation End", doubleField(5, 5));
		registerField("getLineIntervals", "setLineIntervals", "Graduation Tick Intervals", doubleField(3, 5));
		registerField("getSubLineIntervals", "setSubLineIntervals", "Graduation Subtick Intervals", doubleField(3, 5));
		
		currentClass = Manometer.class;
		registerField("getValue", "setValue", "Pressure Reading", doubleField(0, Double.MAX_VALUE, 5, 5));
		registerField("getGraduation", "setGraduation", "Graduation", null);
		
		currentClass = Container.class;
		registerField("getColor", "setColor", "Content Color", changeColorButton("Change Content Color"));
		registerField("getContentState", "setContentState", "Content State", dropdownMenu(ContentState.GAS, ContentState.LIQUID, ContentState.SOLID));
		
		registerField(Piston.class, "getGasColor", "setGasColor", "Gas Color", changeColorButton("Change Gas Color"));
		
		registerField(BunsenBurner.class, "getFlame", null, "Flame", null);
		
		currentClass = Flame.class;
		registerField("getResolutionX", "setResolutionX", "X Resolution", integerField(1, 200));
		registerField("getResolutionY", "setResolutionY", "Y Resolution", integerField(1, 200));
		registerField("getIntensity", "setIntensity", "Intensity", integerSlider(1, 200));
		registerField("getNoiseFrequency", "setNoiseFrequency", "Density", doubleSlider(0.1, 25, 0.1));
		registerField("getNoiseIncrement", "setNoiseIncrement", "Speed", doubleSlider(0, 50, 1));
		registerField("getSeed", "setSeed", "Seed", integerField());
		
		
	}
	
	private static DoubleField doubleField(double min, double max, int sigfigs, int scientificNotationMinPower) {
		return new DoubleField(50, min, max, sigfigs, scientificNotationMinPower);
	}
	
	private static DoubleField doubleField(double min, double max, int sigfigs) {
		return new DoubleField(50, min, max, sigfigs);
	}
	
	private static DoubleField doubleField(int sigfigs) {
		return doubleField(Double.MIN_VALUE, Double.MAX_VALUE, sigfigs);
	}
	
	private static DoubleField doubleField(int sigfigs, int scientificNotationMinPower) {
		return doubleField(Double.MIN_VALUE, Double.MAX_VALUE, sigfigs, scientificNotationMinPower);
	}
	
	private static IntegerField integerField(int min, int max) {
		return new IntegerField(50, min, max);
	}
	
	private static IntegerField integerField() {
		return integerField(Integer.MIN_VALUE, Integer.MAX_VALUE);
	}
	
	private static DoubleSlider doubleSlider(double min, double max, double increment) {
		return new DoubleSlider(150, 25, min, max, increment, DoubleSlider.HORIZONTAL);
	}
	
	private static IntegerSlider integerSlider(int min, int max) {
		return new IntegerSlider(150, 25, min, max, DoubleSlider.HORIZONTAL);
	}
	
	private static CheckBox checkBox() {
		return new CheckBox(25, 20, "");
	}
	
	private static ChangeColorButton changeColorButton(String name) {
		return new ChangeColorButton(150, 25, name);
	}
	
	@SafeVarargs
	private static <E> DropdownMenu<E> dropdownMenu(E...args) {
		return new DropdownMenu<E>(200, 25, args);
	}
	
	private static void registerField(String getter, String setter, String name, InputComponent input) {
		registerField(currentClass, getter, setter, name, input);
	}
	
	private static void registerField(Class<?> clazz, String getter, String setter, String name, InputComponent input) {
		Method getterMethod, setterMethod = null;
		
		try {
			getterMethod = clazz.getMethod(getter);
			
			for (Method method : clazz.getMethods()) {
				if (method.getName().equals(setter)) {
					setterMethod = method;
					setterMethod.setAccessible(true);
					break;
				}
			}
			
			getterMethod.setAccessible(true);
			
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
		Set<EditableField> editableFields = new LinkedHashSet<EditableField>();
		
		if (c.getSuperclass() != Object.class) {
			editableFields.addAll(getEditableFields(c.getSuperclass()));
		}
		
		editableFields.addAll(registry.get(c));
		
		return editableFields;
	}

}
