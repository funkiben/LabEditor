package editor;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import lab.component.BunsenBurner;
import lab.component.LabComponent;
import lab.component.Piston;
import lab.component.container.Beaker;
import lab.component.container.Bulb;
import lab.component.container.Flask;
import lab.component.container.GraduatedCylinder;
import lab.component.data.Graph;
import lab.component.fx.Flame;
import lab.component.fx.ParticleSystem;
import lab.component.sensor.Manometer;
import lab.component.sensor.Thermometer;
import lab.component.swing.Label;
import lab.component.swing.input.Button;
import lab.component.swing.input.DropdownMenu;
import lab.component.swing.input.NumberField;
import lab.component.swing.input.Slider;
import lab.component.swing.input.Switch;

public class ComponentPicker extends LabComponent {

	@SuppressWarnings("unchecked")
	private static final Class<? extends LabComponent>[] components = new Class[] { BunsenBurner.class, Piston.class,
			Beaker.class, Bulb.class, Flask.class, GraduatedCylinder.class, ParticleSystem.class, Manometer.class,
			Thermometer.class, Flame.class, Graph.class, Button.class, DropdownMenu.class, Label.class, Slider.class,
			Switch.class, NumberField.class };

	private static final Map<Class<? extends LabComponent>, String> componentNameAliases = new HashMap<Class<? extends LabComponent>, String>();

	static {
		componentNameAliases.put(BunsenBurner.class, "Bunsen Burner");
		componentNameAliases.put(GraduatedCylinder.class, "Grad. Cylinder");
		componentNameAliases.put(ParticleSystem.class, "Particles");
		componentNameAliases.put(NumberField.class, "Text Field");
	}

	public ComponentPicker(int width, int height) {
		super(width, height);

		Label header = new Label(100000, 20, "Pick Components: ");

		Label inputHeader = new Label(100000, 20, "User Input");
		DropdownMenu inputMenu = new DropdownMenu(150, 30, "Button", "Checkbox", "Dropdown Menu", "Slider",
				"Slider (labeled)", "Text Box", "Text Box (numbers only)", "Switch");
		Button addInput = new Button(120, 30, "Add Input") {
			@Override
			public void doSomething() {
				// TODO Auto-generated method stub
			}
		};

		Label containerHeader = new Label(100000, 20, "Containers");
		DropdownMenu containerMenu = new DropdownMenu(150, 30, "Beaker", "Bulb", "Flask", "Graduated Cylinder");
		Button addContainer = new Button(120, 30, "Add Container") {
			@Override
			public void doSomething() {
				// TODO Auto-generated method stub
				
			}
		};

		Label equipmentHeader = new Label(100000, 20, "Lab Equipment");
		DropdownMenu equipmentMenu = new DropdownMenu(150, 30, "Bunsen Burner", "Piston");
		Button addEquipment = new Button(120, 30, "Add Equipment") {
			@Override
			public void doSomething() {
				// TODO Auto-generated method stub

			}
		};

		Label sensorHeader = new Label(100000, 20, "Sensors");
		DropdownMenu sensorMenu = new DropdownMenu(150, 30, "Thermometer", "Manometer");
		Button addSensor = new Button(120, 30, "Add Sensor") {
			@Override
			public void doSomething() {
				// TODO Auto-generated method stub

			}
		};

		Label analysisHeader = new Label(100000, 20, "Analytics");
		DropdownMenu analysisMenu = new DropdownMenu(150, 30, "Graph", "Data Table");
		Button addAnalysis = new Button(120, 30, "Add Analytics") {
			@Override
			public void doSomething() {
				// TODO Auto-generated method stub

			}
		};

		addChild(header);

		addChild(inputHeader);
		addChild(inputMenu);
		addChild(addInput);

		addChild(equipmentHeader);
		addChild(equipmentMenu);
		addChild(addEquipment);

		addChild(containerHeader);
		addChild(containerMenu);
		addChild(addContainer);

		addChild(sensorHeader);
		addChild(sensorMenu);
		addChild(addSensor);

		addChild(analysisHeader);
		addChild(analysisMenu);
		addChild(addAnalysis);

		/*
		 * for (Class<? extends LabComponent> component : components) {
		 * createButtonFor(component); }
		 */

		setLayout(LabComponent.PARAGRAPH);

		setScaleChildren(false);

	}

	private void createButtonFor(Class<? extends LabComponent> component) {

		Button button = new Button(100, 20, getName(component)) {
			@Override
			public void doSomething() {

			}
		};

		button.setOffsetX(5);
		button.setOffsetY(5);

		button.setFontSize(12);

		addChild(button);
	}

	private String getName(Class<? extends LabComponent> component) {
		if (componentNameAliases.containsKey(component)) {
			return componentNameAliases.get(component);
		}

		return component.getSimpleName().replaceAll("Component", "");
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);

	}
	
}
