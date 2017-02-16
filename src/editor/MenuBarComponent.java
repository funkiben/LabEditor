package editor;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

import lab.component.swing.SwingComponent;

public class MenuBarComponent extends SwingComponent {

	private JMenuBar menuBar;
	
	private JMenu fileMenu;

	private JMenuItem newLab;
	private JMenuItem openLab;
	private JMenuItem saveLab;
	private JMenuItem saveLabAs;
	private JMenuItem exportLab;
	
	private JMenu editMenu;
	private JMenuItem undoAction;
	private JMenuItem redoAction;
	
	private JMenu windowMenu;
	private JMenuItem componentPicker;
	private JMenuItem labOptions;
	private JMenuItem substanceWindow;
	
	private JMenu helpMenu;
	private JMenuItem getHelp;
	
	
	
	public MenuBarComponent(int width,int height) {
		super(width, height);
		
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		newLab = new JMenuItem("New Lab");
		openLab = new JMenuItem("Open Lab");
		saveLab = new JMenuItem("Save Lab");
		saveLabAs = new JMenuItem("Save Lab As");
		exportLab = new JMenuItem("Export Lab");
		fileMenu.add(newLab);
		fileMenu.add(openLab);
		fileMenu.add(saveLab);
		fileMenu.add(saveLabAs);
		fileMenu.add(exportLab);
		
		editMenu = new JMenu("Edit");
		undoAction = new JMenuItem("Undo...");
		redoAction = new JMenuItem("Redo...");
		editMenu.add(undoAction);
		editMenu.add(redoAction);
		
		windowMenu = new JMenu("Window");
		componentPicker = new JRadioButtonMenuItem("Component Picker");
		componentPicker.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				componentPickerMethod();
				
			}
			
		});
		componentPicker.setSelected(true);
		labOptions = new JRadioButtonMenuItem("Lab Options");
		substanceWindow = new JRadioButtonMenuItem("Substance Options");
		windowMenu.add(componentPicker);
		windowMenu.add(labOptions);
		windowMenu.add(substanceWindow);
		
		helpMenu = new JMenu("Help");
		getHelp = new JMenu("Get Help...");
		helpMenu.add(getHelp);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(windowMenu);
		menuBar.add(helpMenu);
	
	}

	@Override
	public Component getJComponent() {
		return menuBar;
	}
	
	public JMenuBar getMenuBar() {
		return menuBar;
	}

	public void setMenuBar(JMenuBar menuBar) {
		this.menuBar = menuBar;
	}

	public JMenuItem getNewLab() {
		return newLab;
	}

	public void setNewLab(JMenuItem newLab) {
		this.newLab = newLab;
	}

	public JMenuItem getOpenLab() {
		return openLab;
	}

	public void setOpenLab(JMenuItem openLab) {
		this.openLab = openLab;
	}

	public JMenuItem getSaveLab() {
		return saveLab;
	}

	public void setSaveLab(JMenuItem saveLab) {
		this.saveLab = saveLab;
	}

	public JMenuItem getSaveLabAs() {
		return saveLabAs;
	}

	public void setSaveLabAs(JMenuItem saveLabAs) {
		this.saveLabAs = saveLabAs;
	}

	public JMenuItem getExportLab() {
		return exportLab;
	}

	public void setExportLab(JMenuItem exportLab) {
		this.exportLab = exportLab;
	}

	public JMenuItem getUndoAction() {
		return undoAction;
	}

	public void setUndoAction(JMenuItem undoAction) {
		this.undoAction = undoAction;
	}

	public JMenuItem getRedoAction() {
		return redoAction;
	}

	public void setRedoAction(JMenuItem redoAction) {
		this.redoAction = redoAction;
	}

	public JMenuItem getComponentPicker() {
		return componentPicker;
	}

	public void setComponentPicker(JMenuItem componentPicker) {
		this.componentPicker = componentPicker;
	}

	public JMenuItem getLabOptions() {
		return labOptions;
	}

	public void setLabOptions(JMenuItem labOptions) {
		this.labOptions = labOptions;
	}

	public JMenuItem getSubstanceWindow() {
		return substanceWindow;
	}

	public void setSubstanceWindow(JMenuItem substanceWindow) {
		this.substanceWindow = substanceWindow;
	}

	public JMenuItem getGetHelp() {
		return getHelp;
	}

	public void setGetHelp(JMenuItem getHelp) {
		this.getHelp = getHelp;
	}

	public void componentPickerMethod() {
		
	};

}
