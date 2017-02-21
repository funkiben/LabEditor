package editor.input.list;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import lab.component.swing.input.InputComponent;

public class ItemList<E> extends InputComponent implements ListSelectionListener {

	private final JScrollPane scrollPane = new JScrollPane();
	private final JList<E> list = new JList<E>();
	private final DefaultListModel<E> model = new DefaultListModel<E>();

	public ItemList(int width, int height) {
		super(width, height);

		list.setBorder(new LineBorder(Color.lightGray));
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(model);

		list.addListSelectionListener(this);

		scrollPane.setViewportView(list);
	}

	public List<E> getItems() {
		List<E> list = new ArrayList<E>();
		
		for (int i = 0; i < model.getSize(); i++) {
			list.add(model.get(i));
		}
	
		return list;
	}
	
	public void setItems(List<E> items) {
		model.clear();
		
		for (E e : items) {
			model.addElement(e);
		}
	}
	
	public boolean contains(E elem) {
		return model.contains(elem);
	}

	public E getItemAt(int index) {
		return model.elementAt(index);
	}

	public void setItemAt(E element, int index) {
		model.setElementAt(element, index);
	}

	public void add(E element) {
		model.addElement(element);
	}

	public boolean removeItem(Object obj) {
		return model.removeElement(obj);
	}

	public E set(int index, E element) {
		return model.set(index, element);
	}

	public void add(int index, E element) {
		model.add(index, element);
	}

	public E remove(int index) {
		return model.remove(index);
	}

	public void clear() {
		model.clear();
	}

	public int getSelectedIndex() {
		return list.getSelectedIndex();
	}
	
	@Override
	public E getValue() {
		return list.getSelectedValue();
	}

	@Override
	public void setValue(Object v) {
		list.setSelectedIndex((Integer) v);
	}

	@Override
	public JScrollPane getJComponent() {
		return scrollPane;
	}
	
	public JList<E> getJList() {
		return list;
	}
	
	public void onValueChanged() {

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		if (e.getValueIsAdjusting()) {
			onValueChanged();
		}
	}

}
