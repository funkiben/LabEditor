package editor;

import lab.LabFrame;
import lab.component.LabComponent;

public class LabEditor extends LabFrame {

	private static final long serialVersionUID = 1L;
	private EditorWindow componentWindow;
	private EditorWindow labWindow;
	private MenuBarComponent menuBar;
	
	public LabEditor() {
		super("Lab Editor", 1500, 800);

		getRoot().setScaleChildren(false);
		getRoot().setLayout(LabComponent.FREE_FORM);
		componentWindow = new EditorWindow("Component Picker", 300, 700);
		componentWindow.getContent().addChild(new ComponentPicker(300, 700));
		componentWindow.setOffsetY(45);
		componentWindow.setMinWidth(300);
		componentWindow.setMinHeight(700);
		
		labWindow = new EditorWindow("Lab Options", 300, 500);
		labWindow.getContent().addChild(new LabOptions(300,500));
		labWindow.setOffsetY(45);
		labWindow.setMinWidth(300);
		labWindow.setMinHeight(500);
		menuBar = new MenuBarComponent(1920, 25) {
			@Override
			public void componentPickerMethod() {
				if(componentWindow.isVisible()) {
					componentWindow.setVisible(false);
					this.getComponentPicker().setSelected(false);
					
				} else {
					componentWindow.setVisible(true);
					this.getComponentPicker().setSelected(true);
				}
			}
			
			@Override
			public void labOptionsMethod() {
				if(labWindow.isVisible()) {
					labWindow.setVisible(false);
					this.getLabOptions().setSelected(false);
				} else {
					labWindow.setVisible(true);
					this.getLabOptions().setSelected(true);
				}
			}
		};
		menuBar.setOffsetY(0);
		menuBar.setOffsetX(0);
		
		addComponent(menuBar);
		addComponent(componentWindow);
		addComponent(labWindow);
		labWindow.setVisible(false);

		start(60);

	}

	@Override
	public void update() {
		if(componentWindow.isVisible()) {
			menuBar.getComponentPicker().setSelected(true);
		} else {
			menuBar.getComponentPicker().setSelected(false);
		}
		if(labWindow.isVisible()) {
			menuBar.getLabOptions().setSelected(true);
		} else {
			menuBar.getLabOptions().setSelected(false);
		}
		
	}

}
