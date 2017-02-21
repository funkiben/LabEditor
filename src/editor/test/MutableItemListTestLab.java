package editor.test;

import editor.input.list.MutableIntegerList;
import lab.LabFrame;

public class MutableItemListTestLab extends LabFrame {

	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		new MutableItemListTestLab();
	}
	
	public MutableItemListTestLab() {
		super("Mutable Item List Test Lab", 800, 800);
		
		MutableIntegerList list = new MutableIntegerList(300, 300, -99999, 99999);
		
		list.setOffset(50, 50);
		
		addComponent(list);
		
		start(60);
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
