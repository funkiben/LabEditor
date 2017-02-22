package editor;

import java.awt.Font;

import editor.window.EditorWindow;
import lab.LabFrame;
import lab.component.LabComponent;
import lab.component.swing.Label;
import lab.component.swing.input.Button;
import lab.component.swing.input.CheckBox;
import lab.component.swing.input.DoubleField;
import lab.component.swing.input.DoubleSlider;
import lab.component.swing.input.Dropdown;
import lab.component.swing.input.IntegerField;
import lab.component.swing.input.LabeledDoubleSlider;
import lab.component.swing.input.LabeledIntegerSlider;

public class LabEditor extends LabFrame {

	private static final long serialVersionUID = 1L;
	private EditorWindow testWindow;
	
	//private MenuBarComponent menuBar;
	
	public LabEditor() {
		super("Lab Editor", 1500, 800);

		getRoot().setScaleChildren(false);
		getRoot().setLayout(LabComponent.FREE_FORM);
		
		/*
		testWindow = new EditorWindow("Test Window", 300, 500);
		testWindow.getContent().addChild(new ComponentPicker(300, 500));
		testWindow.setOffsetY(45);
		testWindow.setMinWidth(300);
		testWindow.setMinHeight(500);
		
		
		menuBar = new MenuBarComponent(1500, 25) {
			@Override
			public void componentPickerMethod() {
				if(testWindow.isVisible()) {
					testWindow.setVisible(false);
					this.getComponentPicker().setSelected(false);
					
				} else {
					testWindow.setVisible(true);
					this.getComponentPicker().setSelected(true);
				}
			}
		};
		
		menuBar.setOffsetY(0);
		menuBar.setOffsetX(0);
		
		addComponent(menuBar);
		
		
		addComponent(testWindow);
		*/
		
		testWindow = new EditorWindow("TestWindow", 250, 500);
		testWindow.getContent().setScaleChildren(false);
		
		ComponentInspector inspector = new ComponentInspector();
		
		testWindow.getContent().addChild(inspector);
		testWindow.setOffsetY(45);
		testWindow.setResizable(false);
		
		LabComponent target = new Dropdown<String>(100, 25);
		
		inspector.setTarget(target);
		
		testWindow.setWidth(inspector.getWidth());
		testWindow.setHeight(inspector.getHeight());
		
		testWindow.setOffsetX(400);
		
		addComponent(testWindow);
		addComponent(target);
		
		start(60);
	
		
	}

	@Override
	public void update() {
		/*
		if(testWindow.isVisible()) {
			menuBar.getComponentPicker().setSelected(true);
		} else {
			menuBar.getComponentPicker().setSelected(false);
		}
		*/
		
		
	}

}
