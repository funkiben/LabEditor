package editor.fieldregistry;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

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
	private static final Map<Class<?>, List<String>> hiddenFields = new HashMap<Class<?>, List<String>>();
	
	private static Class<?> currentClass = null;

	private static final int NUMBER_FIELD_WIDTH = 60;
	
	static {

		currentClass = LabComponent.class;
		registerField("getOffsetX", "setOffsetX", "X", integerField(0, 9999));
		registerField("getOffsetY", "setOffsetY", "Y", integerField(0, 9999));
		registerField("getZOrder", "setZOrder", "Z", integerField(-9999, 9999));
		registerField("getWidth", "setWidth", "Width", integerField(1, 9999));
		registerField("getHeight", "setHeight", "Height", integerField(1, 9999));
		registerField("isVisible", "setVisible", "Visible", checkBox());
		
		registerField(MeasurableComponent.class, "getValue", "setValue", "Value", doubleField(0, 9999, 4, 5));
		
		registerField(GraduatedComponent.class, "getGraduation", "setGraduation", "Graduation", null);
		
		currentClass = Manometer.class;
		registerField("getValue", "setValue", "Pressure Reading", doubleField(0, Double.MAX_VALUE, 5, 5));
		registerField("getGraduation", "setGraduation", "Graduation", null);

		currentClass = Container.class;
		registerField("getContentColor", "setContentColor", "Content Color", changeColorButton("Change Content Color"));
		registerField("getContentState", "setContentState", "Content State", dropdownMenu(ContentState.GAS, ContentState.LIQUID, ContentState.SOLID));

		currentClass = Graduation.class;
		registerField("getStart", "setStart", "Start", doubleField(5, 5));
		registerField("getEnd", "setEnd", "End", doubleField(5, 5));
		registerField("getLineIntervals", "setLineIntervals", "Tick Intervals", doubleField(3, 5));
		registerField("getSubLineIntervals", "setSubLineIntervals", "Subtick Intervals", doubleField(3, 5));
		
		registerField(Piston.class, "getGasColor", "setGasColor", "Gas Color", changeColorButton("Change Gas Color"));

		registerField(BunsenBurner.class, "getFlame", null, "Flame", null);

		currentClass = Flame.class;
		registerField("getResolutionX", "setResolutionX", "X Resolution", integerField(1, 200));
		registerField("getResolutionY", "setResolutionY", "Y Resolution", integerField(1, 200));
		registerField("getIntensity", "setIntensity", "Intensity", integerSlider(1, 200));
		registerField("getNoiseFrequency", "setNoiseFrequency", "Density", doubleSlider(0.1, 25, 0.1));
		registerField("getNoiseIncrement", "setNoiseIncrement", "Speed", doubleSlider(0, 50, 1));
		registerField("getSeed", "setSeed", "Seed", integerField(0, Integer.MAX_VALUE));
		hideField("X", "Y", "Z", "Width", "Height");

	}

	private static InputComponentInstantiater doubleField(double min, double max, int sigfigs, int scientificNotationMinPower) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new DoubleField(NUMBER_FIELD_WIDTH, min, max, sigfigs, scientificNotationMinPower);
			}
		};
	}

	private static InputComponentInstantiater doubleField(double min, double max, int sigfigs) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new DoubleField(NUMBER_FIELD_WIDTH, min, max, sigfigs);
			}
		};
	}

	private static InputComponentInstantiater doubleField(int sigfigs, int scientificNotationMinPower) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new DoubleField(NUMBER_FIELD_WIDTH, -Double.MAX_VALUE, Double.MAX_VALUE, sigfigs, scientificNotationMinPower);
			}
		};
	}

	private static InputComponentInstantiater integerField(int min, int max) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {

				return new IntegerField(NUMBER_FIELD_WIDTH, min, max);
			}
		};
	}

	private static InputComponentInstantiater integerField() {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new IntegerField(NUMBER_FIELD_WIDTH, Integer.MIN_VALUE, Integer.MAX_VALUE);
			}
		};
	}

	private static InputComponentInstantiater doubleSlider(double min, double max, double increment) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new DoubleSlider(150, 25, min, max, increment, DoubleSlider.HORIZONTAL);
			}
		};
	}

	private static InputComponentInstantiater integerSlider(int min, int max) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new IntegerSlider(150, 25, min, max, DoubleSlider.HORIZONTAL);
			}
		};
	}

	private static InputComponentInstantiater checkBox() {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new CheckBox(25, 20, "");
			}
		};
	}

	private static InputComponentInstantiater changeColorButton(String name) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new ChangeColorButton(150, 25, name);
			}
		};
	}

	@SafeVarargs
	private static <E> InputComponentInstantiater dropdownMenu(E... args) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new DropdownMenu<E>(100, 25, args);
			}
		};
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

	private static void registerField(String getter, String setter, String name,
			InputComponentInstantiater inputComponentInstantiator) {
		registerField(currentClass, getter, setter, name, inputComponentInstantiator);
	}

	private static void registerField(Class<?> clazz, String getter, String setter, String name,
			InputComponentInstantiater inputComponentInstantiator) {
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
