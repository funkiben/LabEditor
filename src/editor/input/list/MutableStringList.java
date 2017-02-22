package editor.input.list;

import lab.component.swing.input.TextField;

public class MutableStringList extends MutableList<String> {

	public MutableStringList(int width, int height) {
		super(width, height);
	}

	@Override
	public TextField createEntryField() {
		return new TextField(0);
	}

}
