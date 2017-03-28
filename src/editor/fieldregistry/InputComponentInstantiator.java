package editor.fieldregistry;

import editor.input.ChangeColorButton;
import editor.input.list.MutableStringList;
import lab.component.swing.input.CheckBox;
import lab.component.swing.input.Dropdown;
import lab.component.swing.input.InputComponent;
import lab.component.swing.input.field.DoubleField;
import lab.component.swing.input.field.IntegerField;
import lab.component.swing.input.field.TextField;
import lab.component.swing.input.slider.DoubleSlider;
import lab.component.swing.input.slider.IntegerSlider;

public abstract class InputComponentInstantiator {

	public abstract InputComponent create();

	private static final int NUMBER_FIELD_WIDTH = 60;
	private static final int SLIDER_WIDTH = 150;

	static InputComponentInstantiator doubleField(double min, double max, int sigfigs, int scientificNotationMinPower) {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				return new DoubleField(NUMBER_FIELD_WIDTH, min, max, sigfigs, scientificNotationMinPower);
			}
		};
	}

	static InputComponentInstantiator doubleField(double min, double max, int sigfigs) {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				return new DoubleField(NUMBER_FIELD_WIDTH, min, max, sigfigs);
			}
		};
	}

	static InputComponentInstantiator doubleField(int sigfigs, int scientificNotationMinPower) {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				return new DoubleField(NUMBER_FIELD_WIDTH, -Double.MAX_VALUE, Double.MAX_VALUE, sigfigs, scientificNotationMinPower);
			}
		};
	}

	static InputComponentInstantiator integerField(int min, int max) {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {

				return new IntegerField(NUMBER_FIELD_WIDTH, min, max);
			}
		};
	}

	static InputComponentInstantiator integerField() {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				return new IntegerField(NUMBER_FIELD_WIDTH, Integer.MIN_VALUE, Integer.MAX_VALUE);
			}
		};
	}
	
	static InputComponentInstantiator textField(int width, String deflt) {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				return new TextField(width, deflt);
			}
		};
	}
	
	static InputComponentInstantiator textField() {
		return textField("");
	}
	
	static InputComponentInstantiator textField(String deflt) {
		return textField(60, deflt);
	}
	
	static InputComponentInstantiator textField(int width) {
		return textField(width, "");
	}

	static InputComponentInstantiator doubleSlider(double min, double max, double increment) {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				return new DoubleSlider(SLIDER_WIDTH, min, max, increment, DoubleSlider.HORIZONTAL);
			}
		};
	}

	static InputComponentInstantiator integerSlider(int min, int max) {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				return new IntegerSlider(SLIDER_WIDTH, min, max, DoubleSlider.HORIZONTAL);
			}
		};
	}

	static InputComponentInstantiator checkBox() {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				return new CheckBox(25, 20, "");
			}
		};
	}

	static InputComponentInstantiator changeColorButton() {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				return new ChangeColorButton(100, 25, "Change");
			}
		};
	}

	@SafeVarargs
	static <E> InputComponentInstantiator dropdown(E... args) {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				return new Dropdown<E>(150, 25, args);
			}
		};
	}
	
	static InputComponentInstantiator mutableStringList() {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				MutableStringList list = new MutableStringList(170, 200);
				list.setOffsetY(5);
				return list;
			}
		};
	}
	
	
}
