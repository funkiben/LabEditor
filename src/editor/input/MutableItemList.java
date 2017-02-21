package editor.input;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollBar;

import lab.component.swing.input.Button;
import lab.component.swing.input.InputComponent;
import lab.component.swing.input.ItemList;
import lab.component.swing.input.TextField;

public abstract class MutableItemList<E> extends InputComponent implements KeyListener {

	private final ItemList<E> itemList;
	protected final TextField entryField = createEntryField();
	private final Button addButton;

	public MutableItemList(int width, int height) {
		super(width, height);

		itemList = new ItemList<E>(width, height - 40);
		
		addButton = new Button(60, 25, "Add") {
			@SuppressWarnings("unchecked")
			@Override
			public void doSomething() {
				itemList.add((E) entryField.getValue());
				entryField.getJComponent().setText("");
				addButton.setEnabled(false);
				
				JScrollBar scrollBar = itemList.getJComponent().getVerticalScrollBar();
				scrollBar.setValue(scrollBar.getMaximum());
				
			}
		};
		
		addButton.setEnabled(false);

		entryField.setWidth(width - 60);
		entryField.getJComponent().setText("");
		entryField.getJComponent().addKeyListener(this);
		
		addChild(itemList, addButton, entryField);
	}

	public List<E> getItems() {
		return itemList.getItems();
	}

	public void setItems(List<E> items) {
		itemList.setItems(items);
	}

	public boolean contains(E elem) {
		return itemList.contains(elem);
	}

	public E getItemAt(int index) {
		return itemList.getItemAt(index);
	}

	public void setItemAt(E element, int index) {
		itemList.setItemAt(element, index);
	}

	public void add(E element) {
		itemList.add(element);
	}

	public boolean removeItem(Object obj) {
		return itemList.removeItem(obj);
	}

	public E set(int index, E element) {
		return itemList.set(index, element);
	}

	public void add(int index, E element) {
		itemList.add(index, element);
	}

	public E remove(int index) {
		return itemList.remove(index);
	}

	public void clear() {
		itemList.clear();
	}

	public int getSelectedIndex() {
		return itemList.getSelectedIndex();
	}

	@Override
	public List<E> getValue() {
		return itemList.getItems();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void setValue(Object v) {
		itemList.setItems((List<E>) v);
	}

	public Button getAddButton() {
		return addButton;
	}
	
	public TextField getEntryField() {
		return entryField;
	}

	public abstract TextField createEntryField();
	
	@Override
	public void update() {
		addButton.setEnabled(entryField.hasInput());
	}

	private static final JPanel dummyComponent = new JPanel();

	static {
		dummyComponent.setOpaque(false);
	}
	
	@Override
	public Component getJComponent() {
		return dummyComponent;
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ENTER && addButton.isEnabled()) {
			addButton.doSomething();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}

}
