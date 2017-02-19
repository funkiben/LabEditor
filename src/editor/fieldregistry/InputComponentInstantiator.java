package editor.fieldregistry;

import editor.input.ChangeColorButton;
import lab.component.swing.input.CheckBox;
import lab.component.swing.input.DoubleField;
import lab.component.swing.input.DoubleSlider;
import lab.component.swing.input.DropdownMenu;
import lab.component.swing.input.InputComponent;
import lab.component.swing.input.IntegerField;
import lab.component.swing.input.IntegerSlider;
import lab.component.swing.input.TextField;

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
	
	static InputComponentInstantiator textField(int columns, String deflt) {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				TextField tf = new TextField(columns * 5, deflt);
				tf.setColumns(columns);
				return tf;
			}
		};
	}
	
	static InputComponentInstantiator textField(String deflt) {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				return new TextField(60, deflt);
			}
		};
	}
	
	static InputComponentInstantiator textField() {
		return textField("");
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
	static <E> InputComponentInstantiator dropdownMenu(E... args) {
		return new InputComponentInstantiator() {
			@Override
			public InputComponent create() {
				return new DropdownMenu<E>(100, 25, args);
			}
		};
	}
	
	
}
