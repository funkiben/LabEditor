package editor.input.list;

import lab.component.swing.input.field.IntegerField;
import lab.component.swing.input.field.TextField;

public class MutableIntegerList extends PrimitiveMutableList<Integer> {

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
