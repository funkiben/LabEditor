package editor.input;

import lab.component.swing.input.TextField;

public class MutableStringList extends MutableItemList<String> {

	public MutableStringList(int width, int height) {
		super(width, height);
	}

	@Override
	public TextField createEntryField() {
		return new TextField(0);
	}

}
