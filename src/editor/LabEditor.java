package editor;

import lab.LabFrame;
import lab.component.LabComponent;

public class LabEditor extends LabFrame {

	private static final long serialVersionUID = 1L;
	private EditorWindow testWindow;
	private MenuBarComponent menuBar;
	
	public LabEditor() {
		super("Lab Editor", 1500, 800);

		getRoot().setScaleChildren(false);
		getRoot().setLayout(LabComponent.FREE_FORM);
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

		start(30);

	}

	@Override
	public void update() {
		if(testWindow.isVisible()) {
			menuBar.getComponentPicker().setSelected(true);
		} else {
			menuBar.getComponentPicker().setSelected(false);
		}
		
	}

}
