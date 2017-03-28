package editor;

import java.awt.Color;
import java.awt.Font;
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
import lab.component.data.DataTable;
import lab.component.data.Graph;
import lab.component.fx.Flame;
import lab.component.fx.ParticleSystem;
import lab.component.sensor.Manometer;
import lab.component.sensor.Thermometer;
import lab.component.swing.Label;
import lab.component.swing.input.Button;
import lab.component.swing.input.CheckBox;
import lab.component.swing.input.Dropdown;
import lab.component.swing.input.Switch;
import lab.component.swing.input.field.DoubleField;
import lab.component.swing.input.field.TextField;
import lab.component.swing.input.slider.DoubleSlider;
import lab.component.swing.input.slider.LabeledDoubleSlider;
import lab.util.HorizontalGraduation;
import lab.util.VerticalGraduation;

public class ComponentPicker extends LabComponent {

	@SuppressWarnings("unchecked")
	private static final Class<? extends LabComponent>[] components = new Class[] { BunsenBurner.class, Piston.class,
			Beaker.class, Bulb.class, Flask.class, GraduatedCylinder.class, ParticleSystem.class, Manometer.class,
			Thermometer.class, Flame.class, Graph.class, Button.class, Dropdown.class, Label.class, DoubleSlider.class,
			Switch.class, DoubleField.class };

	private static final Map<Class<? extends LabComponent>, String> componentNameAliases = new HashMap<Class<? extends LabComponent>, String>();

	static {
		componentNameAliases.put(BunsenBurner.class, "Bunsen Burner");
		componentNameAliases.put(GraduatedCylinder.class, "Grad. Cylinder");
		componentNameAliases.put(ParticleSystem.class, "Particles");
		componentNameAliases.put(DoubleField.class, "Text Field");
	}
	
	public ComponentPicker(int width, int height) {
		super(width, height);

		
		
		Label header = new Label(100000, 20, "Pick Components: ");
		header.setFont(new Font("default",Font.BOLD,18));
		
		final int inputHeight = 30;
		final int inputWidth = 150;
		
		MinimizableComponent inputs = new MinimizableComponent("User Input",300,inputHeight*12);
		inputs.addChild(new Button(150, inputHeight, "Button") {


			@Override
			public void doSomething() {
				// TODO Auto-generated method stub
				
			}

		}, 
				new CheckBox(inputWidth,inputHeight,"Check Box"), 
				new Dropdown(inputWidth,inputHeight,"Dropdown Menu Item 1","Dropdown Menu Item 2","Dropdown Menu Item 3","Dropdown Menu Item 4","Dropdown Menu Item 5","Dropdown Menu Item 6"), 
				new LabeledDoubleSlider(inputWidth,0,100,.5f,2,0), 
				new TextField(inputWidth,"Text Field"), 
				new DoubleField(inputWidth,inputHeight*4,2,1)
		);
		inputs.setMinimized(true);
		
		MinimizableComponent containers = new MinimizableComponent("Containers",300,500);
		containers.addChild(
				new Beaker(50,100),
				new Bulb(100,100),
				new Flask(100,100),
				new GraduatedCylinder(50,200)
		);
		containers.setMinimized(true);
		
		MinimizableComponent equipment = new MinimizableComponent("Lab Equipment",300,400);
		equipment.addChild(new BunsenBurner(50,200), new Piston(100,200));
		equipment.setMinimized(true);
		
		MinimizableComponent sensors = new MinimizableComponent("Sensors",300,400);
		sensors.addChild(
				new Thermometer(200),
				new Manometer(200,300)
		);
		sensors.setMinimized(true);
		DataTable<Double> tempDataTable = new DataTable<Double>(200,200,20,20,DataTable.ROW_AND_COLUMN_TITLES);
		tempDataTable.setOffset(30, 30);
		MinimizableComponent analysis = new MinimizableComponent("Analytics",300,300);
		analysis.addChild(
				new Graph(200,200,"Graph","X axis","Y axis", new HorizontalGraduation(0,100,10,5),new VerticalGraduation(0,100,10,5)),
				
				tempDataTable
		);
		analysis.setMinimized(true);
		/*
		Label analysisHeader = new Label(100000, 20, "Analytics");
		Dropdown analysisMenu = new Dropdown(150, 30, "Graph", "Data Table");
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
