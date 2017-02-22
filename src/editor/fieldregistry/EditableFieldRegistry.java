package editor.fieldregistry;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lab.component.BunsenBurner;
import lab.component.GraduatedComponent;
import lab.component.Graduation;
import lab.component.LabComponent;
import lab.component.MeasurableComponent;
import lab.component.Piston;
import lab.component.container.Bulb;
import lab.component.container.Container;
import lab.component.container.ContentState;
import lab.component.fx.Flame;
import lab.component.geo.GeoComponent;
import lab.component.geo.Line;
import lab.component.sensor.Manometer;
import lab.component.sensor.Thermometer;
import lab.component.swing.Label;
import lab.component.swing.SwingComponent;
import lab.component.swing.input.Button;
import lab.component.swing.input.CheckBox;
import lab.component.swing.input.DoubleField;
import lab.component.swing.input.DoubleSlider;
import lab.component.swing.input.Dropdown;
import lab.component.swing.input.FontStyle;
import lab.component.swing.input.IntegerField;
import lab.component.swing.input.IntegerSlider;
import lab.component.swing.input.LabeledDoubleSlider;
import lab.component.swing.input.LabeledIntegerSlider;
import lab.component.swing.input.TextField;

import static editor.fieldregistry.InputComponentInstantiator.*;

public class EditableFieldRegistry {

	private static final Map<Class<?>, List<EditableField>> registry = new HashMap<Class<?>, List<EditableField>>();
	private static final Map<Class<?>, List<String>> hiddenFields = new HashMap<Class<?>, List<String>>();
	
	private static Class<?> currentClass = null;
	
	static {

		currentClass = LabComponent.class;
		registerField("getOffsetX", "setOffsetX", "X", integerField(0, 9999));
		registerField("getOffsetY", "setOffsetY", "Y", integerField(0, 9999));
		registerField("getZOrder", "setZOrder", "Z", integerField(-9999, 9999));
		registerField("getWidth", "setWidth", "Width", integerField(1, 9999));
		registerField("getHeight", "setHeight", "Height", integerField(1, 9999));
		registerField("isVisible", "setVisible", "Visible", checkBox());
		
		registerField(MeasurableComponent.class, "getValue", "setValue", "Value", doubleField(0, 9999, 4, 5));
		
		registerField(GraduatedComponent.class, "getGraduation", "Graduation");
		
		registerField(Manometer.class, "getValue", "setValue", "Pressure", doubleField(0, 9999999, 5, 5));
		
		currentClass = Container.class;
		registerField("getContentColor", "setContentColor", "Content Color", changeColorButton());
		registerField("getContentState", "setContentState", "Content State", dropdown(ContentState.GAS, ContentState.LIQUID, ContentState.SOLID));

		currentClass = Graduation.class;
		registerField("getStart", "setStart", "Start", doubleField(5, 5));
		registerField("getEnd", "setEnd", "End", doubleField(5, 5));
		registerField("getLineIntervals", "setLineIntervals", "Tick Intervals", doubleField(0, 999999, 3, 5));
		registerField("getSubLineIntervals", "setSubLineIntervals", "Subtick Intervals", doubleField(0, 999999, 3, 5));
		registerField("getSuffix", "setSuffix", "Units", textField(""));
		registerField(Piston.class, "getGasColor", "setGasColor", "Gas Color", changeColorButton());

		registerField(BunsenBurner.class, "getFlame", "Flame");

		currentClass = Flame.class;
		registerField("getResolutionX", "setResolutionX", "X Resolution", integerField(1, 200));
		registerField("getResolutionY", "setResolutionY", "Y Resolution", integerField(1, 200));
		registerField("getIntensity", "setIntensity", "Intensity", integerSlider(1, 200));
		registerField("getNoiseFrequency", "setNoiseFrequency", "Density", doubleSlider(0.1, 25, 0.1));
		registerField("getNoiseIncrement", "setNoiseIncrement", "Speed", doubleSlider(0, 50, 1));
		registerField("getSeed", "setSeed", "Seed", integerField(0, 9999999));
		hideField("X", "Y", "Z", "Width", "Height");
		
		hideField(Bulb.class, "Graduation");
		hideField(Thermometer.class, "Width");
		
		registerField(SwingComponent.class, "isEnabled", "setEnabled", "Enabled", checkBox());
		
		currentClass = Label.class;
		registerField("getText", "setText", "Text", textField(150));
		registerField("getFontSize", "setFontSize", "Font Size", integerField(1, 200));
		registerField("getFontStyle", "setFontStyle", "Font Style", dropdown(FontStyle.PLAIN, FontStyle.BOLD, FontStyle.ITALIC));
		registerField("canWrap", "setWrap", "Wrap", checkBox());
		
		currentClass = Button.class;
		registerField("getText", "setText", "Text", textField(150));
		registerField("getFontSize", "setFontSize", "Font Size", integerField(1, 200));
		registerField("getFontStyle", "setFontStyle", "Font Style", dropdown(FontStyle.PLAIN, FontStyle.BOLD, FontStyle.ITALIC));
		
		registerField(CheckBox.class, "isSelected", "setSelected", "Selected", checkBox());
		
		registerField(TextField.class, "getText", "setText", "Text", textField(150));
		
		currentClass = DoubleField.class;
		registerField("getValue", "setValue", "Value", doubleField(5, 5));
		registerField("getMin", "setMin", "Minimum", doubleField(5, 5));
		registerField("getMax", "setMax", "Maximum", doubleField(5, 5));
		registerField("getSigFigs", "setSigFigs", "Sig Figs", integerField(1, 99999));
		hideField("Text");
		
		currentClass = IntegerField.class;
		registerField("getValue", "setValue", "Value", integerField());
		registerField("getMin", "setMin", "Minimum", integerField());
		registerField("getMax", "setMax", "Maximum", integerField());
		hideField("Text");
		
		currentClass = DoubleSlider.class;
		registerField("getValue", "setValue", "Value", doubleField(5, 5));
		registerField("getMin", "setMin", "Minimum", doubleField(5, 5));
		registerField("getMax", "setMax", "Maximum", doubleField(5, 5));
		registerField("getIncrement", "setIncrement", "Increment", doubleField(3, 5));
		
		currentClass = LabeledDoubleSlider.class;
		registerField("getSigFigs", "setSigFigs", "Sig Figs", integerField(1, 99999));
		registerField("getLabel", "Label");
		
		currentClass = IntegerSlider.class;
		registerField("getValue", "setValue", "Value", integerField());
		registerField("getMin", "setMin", "Minimum", integerField());
		registerField("getMax", "setMax", "Maximum", integerField());
		
		registerField(LabeledIntegerSlider.class, "getLabel", "Label");
		
		registerField(Dropdown.class, "getItems", "setItems", "Options", mutableStringList());
		
		currentClass = GeoComponent.class;
		registerField("getFillColor", "setFillColor", "Fill Color", changeColorButton());
		registerField("canFill", "setFill", "Fill", checkBox());
		registerField("getStrokeColor", "setStrokeColor", "Stroke Color", changeColorButton());
		registerField("canStroke", "setStroke", "Stroke", checkBox());
		
		currentClass = Line.class;
		registerField("getStartX", "setStartX", "X1", integerField(0, 9999));
		registerField("getStartY", "setStartY", "Y1", integerField(0, 9999));
		registerField("getEndX", "setEndX", "X2", integerField(0, 9999));
		registerField("getEndY", "setEndY", "Y2", integerField(0, 9999));
		registerField("getColor", "setColor", "Color", changeColorButton());
		hideField("X", "Y", "Width", "Height");
	}

	private static void hideField(String... names) {
		hideField(currentClass, names);
	}
	
	private static void hideField(Class<?> clazz, String...names) {
		List<String> list = hiddenFields.get(clazz);
		
		if (list == null) {
			list = new ArrayList<String>();
		}
		
		for (String name : names) {
			list.add(name);
		}
		
		hiddenFields.put(clazz, list);
	}

	private static void registerField(String getter, String setter, String name, InputComponentInstantiator inputComponentInstantiator) {
		registerField(getter, setter, name, inputComponentInstantiator, null);
	}
	
	private static void registerField(Class<?> clazz, String getter, String setter, String name, InputComponentInstantiator inputComponentInstantiator) {
		registerField(clazz, getter, setter, name, inputComponentInstantiator, null);
	}
	
	private static void registerField(String getter, String setter, String name, InputComponentInstantiator inputComponentInstantiator, Class<?> setterParameter) {
		registerField(currentClass, getter, setter, name, inputComponentInstantiator, setterParameter);
	}

	private static void registerField(String getter, String name) {
		registerField(currentClass, getter, name);
	}
	
	private static void registerField(Class<?> clazz, String getter, String name) {
		registerField(clazz, getter, null, name, null);
	}
	
	private static void registerField(Class<?> clazz, String getter, String setter, String name, InputComponentInstantiator inputComponentInstantiator, Class<?> setterParameter) {
		Method getterMethod, setterMethod = null;

		try {
			getterMethod = clazz.getMethod(getter);
			
			if (setter != null) {
				if (setterParameter == null) {
					for (Method method : clazz.getDeclaredMethods()) {
						if (method.getName().equals(setter)) {
							setterMethod = method;
							setterMethod.setAccessible(true);
							break;
						}
					}
				} else {
					setterMethod = clazz.getDeclaredMethod(setter, setterParameter);
				}
			}

			getterMethod.setAccessible(true);

		} catch (SecurityException | IllegalArgumentException | NoSuchMethodException e) {
			e.printStackTrace();
			return;

		}

		List<EditableField> fields;

		EditableField editableField = new EditableField(name, getterMethod, setterMethod, inputComponentInstantiator);

		if (registry.containsKey(clazz)) {

			fields = registry.get(clazz);
			fields.add(editableField);

		} else {

			fields = new ArrayList<EditableField>();

			fields.add(editableField);

			registry.put(clazz, fields);

		}
	}
	
	private static void removeHiddenFields(Set<EditableField> editableFields, Class<?> c) {
		List<String> hidden = hiddenFields.get(c);
		
		if (hidden != null) {
			Iterator<EditableField> iter = editableFields.iterator();
			
			while (iter.hasNext()) {
				if (hidden.contains(iter.next().getName())) {
					iter.remove();
				}
			}
			
		}
	}

	public static Set<EditableField> getEditableFields(Class<?> c) {
		Set<EditableField> editableFields = new LinkedHashSet<EditableField>();

		if (c.getSuperclass() != Object.class) {
			editableFields.addAll(getEditableFields(c.getSuperclass()));
		}
		
		if (registry.containsKey(c)) {
			editableFields.addAll(registry.get(c));
		}
		
		removeHiddenFields(editableFields, c);
		
		return editableFields;
	}

}
