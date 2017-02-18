package editor.fieldregistry;

import editor.input.ChangeColorButton;
import lab.component.swing.input.CheckBox;
import lab.component.swing.input.DoubleField;
import lab.component.swing.input.DoubleSlider;
import lab.component.swing.input.DropdownMenu;
import lab.component.swing.input.InputComponent;
import lab.component.swing.input.IntegerField;
import lab.component.swing.input.IntegerSlider;

public abstract class InputComponentInstantiater {

	public abstract InputComponent create();

	
	
	
	private static final int NUMBER_FIELD_WIDTH = 60;

	static InputComponentInstantiater doubleField(double min, double max, int sigfigs, int scientificNotationMinPower) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new DoubleField(NUMBER_FIELD_WIDTH, min, max, sigfigs, scientificNotationMinPower);
			}
		};
	}

	static InputComponentInstantiater doubleField(double min, double max, int sigfigs) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new DoubleField(NUMBER_FIELD_WIDTH, min, max, sigfigs);
			}
		};
	}

	static InputComponentInstantiater doubleField(int sigfigs, int scientificNotationMinPower) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new DoubleField(NUMBER_FIELD_WIDTH, -Double.MAX_VALUE, Double.MAX_VALUE, sigfigs, scientificNotationMinPower);
			}
		};
	}

	static InputComponentInstantiater integerField(int min, int max) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {

				return new IntegerField(NUMBER_FIELD_WIDTH, min, max);
			}
		};
	}

	static InputComponentInstantiater integerField() {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new IntegerField(NUMBER_FIELD_WIDTH, Integer.MIN_VALUE, Integer.MAX_VALUE);
			}
		};
	}

	static InputComponentInstantiater doubleSlider(double min, double max, double increment) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new DoubleSlider(150, 25, min, max, increment, DoubleSlider.HORIZONTAL);
			}
		};
	}

	static InputComponentInstantiater integerSlider(int min, int max) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new IntegerSlider(150, 25, min, max, DoubleSlider.HORIZONTAL);
			}
		};
	}

	static InputComponentInstantiater checkBox() {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new CheckBox(25, 20, "");
			}
		};
	}

	static InputComponentInstantiater changeColorButton(String name) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new ChangeColorButton(150, 25, name);
			}
		};
	}

	@SafeVarargs
	static <E> InputComponentInstantiater dropdownMenu(E... args) {
		return new InputComponentInstantiater() {
			@Override
			public InputComponent create() {
				return new DropdownMenu<E>(100, 25, args);
			}
		};
	}
	
	
}
