package editor.input.list;

import lab.component.swing.input.field.TextField;

public class MutableStringList extends PrimitiveMutableList<String> {

	public MutableStringList(int width, int height) {
		super(width, height);
	}

	@Override
	public TextField createEntryField() {
		return new TextField(0);
	}

}
