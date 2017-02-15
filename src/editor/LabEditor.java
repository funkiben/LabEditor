package editor;

import lab.LabFrame;
import lab.component.LabComponent;

public class LabEditor extends LabFrame {
	
	private static final long serialVersionUID = 1L;

	public LabEditor() {
		super("Lab Editor", 1500, 800);
		
		getRoot().setScaleChildren(false);
		getRoot().setLayout(LabComponent.FREE_FORM);
		MenuBarComponent menuBar = new MenuBarComponent(1500,25);
		menuBar.setOffsetY(0);
		menuBar.setOffsetX(0);
		EditorWindow testWindow = new EditorWindow("Test Window", 300, 500);
		testWindow.getContent().addChild(new ComponentPicker(300, 500));
		testWindow.setOffsetY(45);
		testWindow.setMinWidth(300);
		testWindow.setMinHeight(500);
		//EditorWindow testWindow2 = new EditorWindow("Test Inspector", 300, 500);
		//testWindow2.getContent().addChild(new );
		addComponent(menuBar);
		addComponent(testWindow);
		//addComponent(testWindow2);
		
		start(30);
		
	}

	@Override
	public void update() {
		
	}

}
