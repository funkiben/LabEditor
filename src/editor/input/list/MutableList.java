package editor.input.list;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;

import lab.component.swing.input.Button;
import lab.component.swing.input.InputComponent;
import lab.component.swing.input.TextField;

public abstract class MutableList<E> extends InputComponent {

	private static final JPanel dummyComponent = new JPanel();

	static {
		dummyComponent.setOpaque(false);
	}
	
	private final MultiSelectionList<E> itemList;
	protected final TextField entryField = createEntryField();
	private final Button addButton;
	private final JPopupMenu deleteMenu = new JPopupMenu("Delete");
	
	public MutableList(int width, int height) {
		super(width, height);

		itemList = new MultiSelectionList<E>(width, height - 40);
		itemList.getJList().addKeyListener(new ListKeyListener());
		itemList.getJList().addMouseListener(new ListMouseListener());
		
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
		entryField.getJComponent().addKeyListener(new EntryFieldKeyListener());
		
		addChild(itemList, addButton, entryField);
		
		
		JMenuItem deleteMenuItem = new JMenuItem("Delete");
		
		deleteMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				for (E e : itemList.getSelectedValues()) {
					itemList.remove(e);
				}
			}
		});
		
		deleteMenu.add(deleteMenuItem);
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

	public boolean remove(E e) {
		return itemList.remove(e);
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

	public int[] getSelectedIndices() {
		return itemList.getSelectedIndices();
	}
	
	public List<E> getSelectedValues() {
		return itemList.getValue();
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
	
	@Override
	public Component getJComponent() {
		return dummyComponent;
	}

	class EntryFieldKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == KeyEvent.VK_ENTER && addButton.isEnabled()) {
				addButton.doSomething();
			}
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
	}
	
	class ListKeyListener implements KeyListener {
		@Override
		public void keyPressed(KeyEvent ev) {
			if (ev.getKeyCode() == KeyEvent.VK_DELETE) {
				for (E e : itemList.getSelectedValues()) {
					itemList.remove(e);
				}
			}
		}
	
		@Override
		public void keyReleased(KeyEvent e) {
			
		}
		
		@Override
		public void keyTyped(KeyEvent e) {
			
		}
	}
	
	class ListMouseListener implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.isPopupTrigger()) {
				int row = itemList.getJList().locationToIndex(e.getPoint());
				
				int[] selected = Arrays.copyOf(itemList.getSelectedIndices(), itemList.getSelectedIndices().length + 1);
				selected[selected.length - 1] = row;
				
				itemList.setSelectedIndices(selected);
				
				deleteMenu.show(e.getComponent(), e.getX(), e.getY());
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			
		}
		
	}

}
