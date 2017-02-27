package editor.input.list;

import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

public class SingleSelectionList<E> extends ItemList<E> implements ListSelectionListener {

	public SingleSelectionList(int width, int height) {
		super(width, height);

		getJList().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	}

	public int getSelectedIndex() {
		return getJList().getSelectedIndex();
	}
	
	public void setSelectedIndex(int index) {
		getJList().setSelectedIndex(index);
	}

	public E getSelectedValue() {
		return getJList().getSelectedValue();
	}

	@Override
	public E getValue() {
		return getJList().getSelectedValue();
	}

	@Override
	public void setValue(Object v) {
		getJList().setSelectedIndex((Integer) v);
	}

}
