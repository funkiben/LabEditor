package editor.input;

import lab.component.swing.input.IntegerField;
import lab.component.swing.input.TextField;

public class MutableIntegerList extends MutableItemList<Integer> {

	public MutableIntegerList(int width, int height, int min, int max) {
		super(width, height);
		
		((IntegerField) entryField).setMin(min);
		((IntegerField) entryField).setMax(max);
	}

	@Override
	public TextField createEntryField() {
		return new IntegerField(0, 0, 0);
	}
	
}
