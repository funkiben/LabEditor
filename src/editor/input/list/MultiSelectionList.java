package editor.input.list;

import java.util.List;

import javax.swing.ListSelectionModel;

import javax.swing.event.ListSelectionListener;

public class MultiSelectionList<E> extends ItemList<E> implements ListSelectionListener {

	public MultiSelectionList(int width, int height) {
		super(width, height);

		getJList().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

	}

	public int[] getSelectedIndices() {
		return getJList().getSelectedIndices();
	}
	
	public void setSelectedIndices(int[] indices) {
		getJList().setSelectedIndices(indices);
	}

	public List<E> getSelectedValues() {
		return getJList().getSelectedValuesList();
	}

	@Override
	public List<E> getValue() {
		return getJList().getSelectedValuesList();
	}

	@Override
	public void setValue(Object v) {
		@SuppressWarnings("unchecked")
		List<E> list = (List<E>) v;
		int[] arr = new int[list.size()];

		for (int i = 0; i < list.size(); i++) {
			arr[i] = indexOf(list.get(i));
		}

		getJList().setSelectedIndices(arr);
	}

}
