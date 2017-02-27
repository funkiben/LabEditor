package editor.input.list;

import lab.component.swing.input.TextField;

public abstract class PrimitiveMutableList<E> extends MutableList<E> {

	protected final TextField entryField;
	
	public PrimitiveMutableList(int width, int height) {
		super(width, height);
		
		entryField = createEntryField();
		
		entryField.setWidth(width - 60);
		entryField.getJComponent().setText("");
		entryField.getJComponent().addKeyListener(new EntryFieldKeyListener());
		
		addChild(entryField);
	}
	
	public abstract TextField createEntryField();

	@SuppressWarnings("unchecked")
	@Override
	public E getEntry() {
		if (entryField.hasInput()) {
			return (E) entryField.getValue();
		}
		
		return null;
	}
	
	public void clearEntry() {
		entryField.setText("");
	}
	
	public boolean entryHasFocus() {
		return entryField.hasFocus();
	}
	
}
