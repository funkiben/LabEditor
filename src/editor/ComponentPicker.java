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
	private static final Class<? extends LabComponent>[] components = new Class[] {
		BunsenBurner.class,
		Piston.class,
		Beaker.class,
		Bulb.class,
		Flask.class,
		GraduatedCylinder.class,
		ParticleSystem.class,
		Manometer.class,
		Thermometer.class,
		Flame.class,
		Graph.class,
		Button.class,
		DropdownMenu.class,
		Label.class,
		Slider.class,
		Switch.class,
		NumberField.class
	};
	
	private static final Map<Class<? extends LabComponent>, String> componentNameAliases = new HashMap<Class<? extends LabComponent>, String>();
	
	static {
		componentNameAliases.put(BunsenBurner.class, "Bunsen Burner");
		componentNameAliases.put(GraduatedCylinder.class, "Grad. Cylinder");
		componentNameAliases.put(ParticleSystem.class, "Particles");
		componentNameAliases.put(NumberField.class, "Text Field");
	}
	
	
	
	public ComponentPicker(int width, int height) {
		super(width, height);
		
		Label label = new Label(100000, 20, "Component Picker");
		
		addChild(label);
		
		for (Class<? extends LabComponent> component : components) {
			createButtonFor(component);
		}
		
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
