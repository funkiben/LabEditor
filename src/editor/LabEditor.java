package editor;

import lab.LabFrame;
import lab.component.LabComponent;

public class LabEditor extends LabFrame {
	
	private static final long serialVersionUID = 1L;

	public LabEditor() {
		super("Lab Editor", 1500, 800);
		
		getRoot().setScaleChildren(false);
		getRoot().setLayout(LabComponent.FREE_FORM);
		
		EditorWindow testWindow = new EditorWindow("Test Window", 220, 360);
		testWindow.getContent().addChild(new ComponentPicker(220, 360));
		testWindow.setOffsetY(20);
		testWindow.setMinWidth(220);
		testWindow.setMinHeight(360);
		addComponent(testWindow);
		
		start(30);
		
	}

	@Override
	public void update() {
		
	}

}
