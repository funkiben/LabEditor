package editor;

import java.awt.Component;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
		
		helpMenu = new JMenu("Help");
		getHelp = new JMenu("Get Help...");
		helpMenu.add(getHelp);
		
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		menuBar.add(helpMenu);
	
	}

	@Override
	public Component getJComponent() {
		return menuBar;
	}

}
