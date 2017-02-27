package editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import lab.component.BunsenBurner;
import lab.component.HorizontalGraduation;
import lab.component.LabComponent;
import lab.component.MinimizableComponent;
import lab.component.Piston;
import lab.component.VerticalGraduation;
import lab.component.container.Beaker;
import lab.component.container.Bulb;
import lab.component.container.Flask;
import lab.component.container.GraduatedCylinder;
import lab.component.data.DataTable;
import lab.component.data.Graph;
import lab.component.fx.Flame;
import lab.component.fx.ParticleSystem;
import lab.component.sensor.Manometer;
import lab.component.sensor.Thermometer;
import lab.component.swing.Label;
import lab.component.swing.input.Button;
import lab.component.swing.input.CheckBox;
import lab.component.swing.input.DropdownMenu;
import lab.component.swing.input.LabeledSlider;
import lab.component.swing.input.NumberField;
import lab.component.swing.input.Slider;
import lab.component.swing.input.Switch;
import lab.component.swing.input.TextField;

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
		header.setFont(new Font("default",Font.BOLD,18));
		
		final int inputHeight = 30;
		final int inputWidth = 150;
		
		MinimizableComponent inputs = new MinimizableComponent("User Input",300,inputHeight*12,30);
		inputs.addChild(new Button(150, inputHeight, "Button") {

			@Override
			public void doSomething() {
				// TODO Auto-generated method stub
				
			}
		}, 
				new CheckBox(inputWidth,inputHeight,"Check Box"), 
				new DropdownMenu(inputWidth,inputHeight,"Dropdown Menu Item 1","Dropdown Menu Item 2","Dropdown Menu Item 3","Dropdown Menu Item 4","Dropdown Menu Item 5","Dropdown Menu Item 6"), 
				new LabeledSlider(inputWidth,inputHeight,0,100,.5f,2,0), 
				new TextField(inputWidth,(int) ((inputHeight/1.75)+.5),"Text Field"), 
				new NumberField(inputWidth,inputHeight*4,2,1)
		);
		inputs.setMinimized(true);
		
		MinimizableComponent containers = new MinimizableComponent("Containers",300,500,30);
		containers.addChild(
				new Beaker(50,100),
				new Bulb(100,100),
				new Flask(100,100),
				new GraduatedCylinder(50,200)
		);
		containers.setMinimized(true);
		
		MinimizableComponent equipment = new MinimizableComponent("Lab Equipment",300,400,30);
		equipment.addChild(new BunsenBurner(50,200), new Piston(100,200));
		equipment.setMinimized(true);
		
		MinimizableComponent sensors = new MinimizableComponent("Sensors",300,400,30);
		sensors.addChild(
				new Thermometer(200),
				new Manometer(200,300)
		);
		sensors.setMinimized(true);
		DataTable<Double> tempDataTable = new DataTable<Double>(200,200,20,20,DataTable.ROW_AND_COLUMN_TITLES);
		tempDataTable.setOffset(30, 30);
		MinimizableComponent analysis = new MinimizableComponent("Analytics",300,300,30);
		analysis.addChild(
				new Graph(200,200,"Graph","X axis","Y axis", new VerticalGraduation(0,100,10,5),new HorizontalGraduation(0,100,10,5)),
				
				tempDataTable
		);
		analysis.setMinimized(true);
		/*
		Label analysisHeader = new Label(100000, 20, "Analytics");
		DropdownMenu analysisMenu = new DropdownMenu(150, 30, "Graph", "Data Table");
		Button addAnalysis = new Button(120, 30, "Add Analytics") {
			@Override
			public void doSomething() {
				// TODO Auto-generated method stub

			}
		};
		*/
		
		addChild(header);
		addChild(inputs);
		addChild(containers);
		addChild(equipment);
		addChild(sensors);
		addChild(analysis);
		/*
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
		*/

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
