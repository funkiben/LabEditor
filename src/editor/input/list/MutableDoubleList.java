package editor.input.list;

import lab.component.swing.input.DoubleField;
import lab.component.swing.input.TextField;

public class MutableDoubleList extends MutableItemList<Double> {

	public MutableDoubleList(int width, int height, double min, double max) {
		super(width, height);
		
		((DoubleField) entryField).setMin(min);
		((DoubleField) entryField).setMax(max);
		((DoubleField) entryField).setSigFigs(-1);
	}

	@Override
	public TextField createEntryField() {
		return new DoubleField(0, 0, 0, 0);
	}

}
