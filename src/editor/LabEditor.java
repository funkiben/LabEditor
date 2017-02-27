package editor;

import lab.LabFrame;
import lab.component.BunsenBurner;
import lab.component.LabComponent;

public class LabEditor extends LabFrame {

	private static final long serialVersionUID = 1L;
<<<<<<< HEAD
	private EditorWindow componentWindow;
	private EditorWindow labWindow;
	private MenuBarComponent menuBar;
=======
	private EditorWindow testWindow;
	
	//private MenuBarComponent menuBar;
>>>>>>> refs/remotes/origin/master
	
	public LabEditor() {
		super("Lab Editor", 1500, 800);

		getRoot().setScaleChildren(false);
		getRoot().setLayout(LabComponent.FREE_FORM);
<<<<<<< HEAD
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
=======
		
		/*
		testWindow = new EditorWindow("Test Window", 300, 500);
		testWindow.getContent().addChild(new ComponentPicker(300, 500));
		testWindow.setOffsetY(45);
		testWindow.setMinWidth(300);
		testWindow.setMinHeight(500);
		
		
		menuBar = new MenuBarComponent(1500, 25) {
>>>>>>> refs/remotes/origin/master
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
<<<<<<< HEAD
		addComponent(componentWindow);
		addComponent(labWindow);
		labWindow.setVisible(false);

=======
		
		
		addComponent(testWindow);
		*/
		
		testWindow = new EditorWindow("TestWindow", 300, 500);
		testWindow.getContent().setScaleChildren(false);
		
		ComponentInspector inspector = new ComponentInspector(300, 500);
		
		testWindow.getContent().addChild(inspector);
		testWindow.setOffsetY(45);
		testWindow.setMinWidth(300);
		testWindow.setMinHeight(500);
		
		LabComponent target = new BunsenBurner(30, 350);
		
		inspector.setTarget(target);
		
		addComponent(testWindow);
		addComponent(target);
		
>>>>>>> refs/remotes/origin/master
		start(60);

	}

	@Override
	public void update() {
<<<<<<< HEAD
		if(componentWindow.isVisible()) {
=======
		/*
		if(testWindow.isVisible()) {
>>>>>>> refs/remotes/origin/master
			menuBar.getComponentPicker().setSelected(true);
		} else {
			menuBar.getComponentPicker().setSelected(false);
		}
<<<<<<< HEAD
		if(labWindow.isVisible()) {
			menuBar.getLabOptions().setSelected(true);
		} else {
			menuBar.getLabOptions().setSelected(false);
		}
=======
		*/
		
>>>>>>> refs/remotes/origin/master
		
	}

}
