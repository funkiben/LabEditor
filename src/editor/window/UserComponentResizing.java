package editor.window;

import java.awt.Cursor;
import java.awt.Point;

import javax.swing.JPanel;

import lab.component.ClickableArea;
import lab.component.LabComponent;


// this entire class is really messy :(
// EDIT 2/9: its a little better now! :)
public class UserComponentResizing {

	private final LabComponent component;
	private int clickableAreaWidth = 5;
	private ClickableArea N_dragArea;
	private ClickableArea E_dragArea;
	private ClickableArea S_dragArea;
	private ClickableArea W_dragArea;
	private ClickableArea NE_dragArea;
	private ClickableArea NW_dragArea;
	private ClickableArea SW_dragArea;
	private ClickableArea SE_dragArea;
	private ClickableArea[] dragAreas = new ClickableArea[8];
	private int minWidth;
	private int minHeight;
	private boolean hasDrag = false;
	
	public UserComponentResizing(LabComponent component, int minWidth, int minHeight) {
		this.component = component;
		this.minWidth = minWidth;
		this.minHeight = minHeight;
		
		for (int i = 0; i < 8; i++) {
			dragAreas[i] = new ClickableArea(component);
		}

		N_dragArea = dragAreas[0];
		E_dragArea = dragAreas[1];
		S_dragArea = dragAreas[2];
		W_dragArea = dragAreas[3];
		NE_dragArea = dragAreas[4];
		NW_dragArea = dragAreas[5];
		SW_dragArea = dragAreas[6];
		SE_dragArea = dragAreas[7];
		
		N_dragArea.setHoverCursorIcon(Cursor.N_RESIZE_CURSOR);
		N_dragArea.setDragCursorIcon(Cursor.N_RESIZE_CURSOR);

		NE_dragArea.setHoverCursorIcon(Cursor.NE_RESIZE_CURSOR);
		NE_dragArea.setDragCursorIcon(Cursor.NE_RESIZE_CURSOR);
		
		E_dragArea.setHoverCursorIcon(Cursor.E_RESIZE_CURSOR);
		E_dragArea.setDragCursorIcon(Cursor.E_RESIZE_CURSOR);
		
		SE_dragArea.setHoverCursorIcon(Cursor.SE_RESIZE_CURSOR);
		SE_dragArea.setDragCursorIcon(Cursor.SE_RESIZE_CURSOR);
		
		S_dragArea.setHoverCursorIcon(Cursor.S_RESIZE_CURSOR);
		S_dragArea.setDragCursorIcon(Cursor.S_RESIZE_CURSOR);
		
		SW_dragArea.setHoverCursorIcon(Cursor.SW_RESIZE_CURSOR);
		SW_dragArea.setDragCursorIcon(Cursor.SW_RESIZE_CURSOR);
		
		W_dragArea.setHoverCursorIcon(Cursor.W_RESIZE_CURSOR);
		W_dragArea.setDragCursorIcon(Cursor.W_RESIZE_CURSOR);
		
		NW_dragArea.setHoverCursorIcon(Cursor.NW_RESIZE_CURSOR);
		NW_dragArea.setDragCursorIcon(Cursor.NW_RESIZE_CURSOR);

	}

	public int getClickableAreaWidth() {
		return clickableAreaWidth;
	}

	public void setClickableAreaWidth(int clickableAreaWidth) {
		this.clickableAreaWidth = clickableAreaWidth;
	}

	public void enableNDrag(boolean on) {
		N_dragArea.setEnabled(on);
	}

	public void enableSDrag(boolean on) {
		S_dragArea.setEnabled(on);
	}

	public void enableEDrag(boolean on) {
		E_dragArea.setEnabled(on);
	}

	public void enableWDrag(boolean on) {
		W_dragArea.setEnabled(on);
	}

	public void enableNEDrag(boolean on) {
		NE_dragArea.setEnabled(on);
	}

	public void enableSEDrag(boolean on) {
		SE_dragArea.setEnabled(on);
	}

	public void enableNWDrag(boolean on) {
		NW_dragArea.setEnabled(on);
	}

	public void enableSWDrag(boolean on) {
		SW_dragArea.setEnabled(on);
	}

	public int getMinWidth() {
		return minWidth;
	}

	public void setMinWidth(int minWidth) {
		this.minWidth = minWidth;
	}

	public int getMinHeight() {
		return minHeight;
	}

	public void setMinHeight(int minHeight) {
		this.minHeight = minHeight;
	}

	public boolean hasDrag() {
		return hasDrag;
	}

	private int clickHeight = -1, clickWidth = -1, clickOffsetX = -1, clickOffsetY = -1;

	public void check(int x, int y, int width, int height) {
		
		Point p = N_dragArea.getMousePosition();
		
		if (p != null) {
			if (component.getRoot().isPointCovered(p.x, p.y, component.getZOrder())) {
				return;
			}
		}
		

		NE_dragArea.checkRaw(x + width - clickableAreaWidth, y - clickableAreaWidth, 2 * clickableAreaWidth,
				clickableAreaWidth * 2);
		N_dragArea.checkRaw(x + clickableAreaWidth, y - clickableAreaWidth, width - 2 * clickableAreaWidth,
				clickableAreaWidth * 2);
		NW_dragArea.checkRaw(x - clickableAreaWidth, y - clickableAreaWidth, 2 * clickableAreaWidth,
				clickableAreaWidth * 2);
		W_dragArea.checkRaw(x - clickableAreaWidth, y + clickableAreaWidth, clickableAreaWidth * 2,
				height - clickableAreaWidth * 2);
		SW_dragArea.checkRaw(x - clickableAreaWidth, y + height - clickableAreaWidth, clickableAreaWidth * 2,
				clickableAreaWidth * 2);
		S_dragArea.checkRaw(x + clickableAreaWidth, y + height - clickableAreaWidth, width - 2 * clickableAreaWidth,
				clickableAreaWidth * 2);
		SE_dragArea.checkRaw(x + width - clickableAreaWidth, y + height - clickableAreaWidth, clickableAreaWidth * 2,
				clickableAreaWidth * 2);
		E_dragArea.checkRaw(x + width - clickableAreaWidth, y + clickableAreaWidth, clickableAreaWidth * 2,
				height - clickableAreaWidth * 2);

		hasDrag = false;
		
		for (ClickableArea a : dragAreas) {
			if (a.hasClick()) {
				hasDrag = true;
				break;
			}
		}
		
		if (NE_dragArea.hasClick()) {
			setClickState(width, height);

			component.stretchToNewWidth(NE_dragArea.getDragDelta().x + clickWidth);
			component.stretchToNewHeight(-NE_dragArea.getDragDelta().y + clickHeight);

			component.setOffsetY(NE_dragArea.getDragDelta().y + clickOffsetY);

			checkDimensions(false, true);
			
		} else if (N_dragArea.hasClick()) {
			setClickState(width, height);

			component.stretchToNewHeight(-N_dragArea.getDragDelta().y + clickHeight);

			component.setOffsetY(N_dragArea.getDragDelta().y + clickOffsetY);

			checkDimensions(false, true);
			
		} else if (NW_dragArea.hasClick()) {
			setClickState(width, height);

			component.stretchToNewWidth(-NW_dragArea.getDragDelta().x + clickWidth);
			component.stretchToNewHeight(-NW_dragArea.getDragDelta().y + clickHeight);

			component.setOffsetX(NW_dragArea.getDragDelta().x + clickOffsetX);
			component.setOffsetY(NW_dragArea.getDragDelta().y + clickOffsetY);

			checkDimensions(true, true);
			
		} else if (W_dragArea.hasClick()) {
			setClickState(width, height);

			component.stretchToNewWidth(-W_dragArea.getDragDelta().x + clickWidth);

			component.setOffsetX(W_dragArea.getDragDelta().x + clickOffsetX);

			checkDimensions(true, false);

		} else if (SW_dragArea.hasClick()) {
			setClickState(width, height);

			component.stretchToNewWidth(-SW_dragArea.getDragDelta().x + clickWidth);
			component.stretchToNewHeight(SW_dragArea.getDragDelta().y + clickHeight);

			component.setOffsetX(SW_dragArea.getDragDelta().x + clickOffsetX);

			checkDimensions(true, false);

		} else if (S_dragArea.hasClick()) {
			setClickState(width, height);

			component.stretchToNewHeight(S_dragArea.getDragDelta().y + clickHeight);

			checkDimensions();

		} else if (SE_dragArea.hasClick()) {
			setClickState(width, height);

			component.stretchToNewWidth(SE_dragArea.getDragDelta().x + clickWidth);
			component.stretchToNewHeight(SE_dragArea.getDragDelta().y + clickHeight);

			checkDimensions();

		} else if (E_dragArea.hasClick()) {
			setClickState(width, height);

			component.stretchToNewWidth(E_dragArea.getDragDelta().x + clickWidth);

			checkDimensions();

		} else {
			clickWidth = -1;
			clickHeight = -1;
			clickOffsetX = -1;
			clickOffsetY = -1;

		}

	}
	
	private void checkDimensions(boolean x, boolean y) {
		if (component.getWidth() < minWidth) {
			component.stretchToNewWidth(minWidth);

			if (x) {
				component.setOffsetX(clickOffsetX + clickWidth - minWidth);
			}
		}

		if (component.getHeight() < minHeight) {
			component.stretchToNewHeight(minHeight);

			if (y) {
				component.setOffsetY(clickOffsetY + clickHeight - minHeight);
			}
		}
	}

	private void checkDimensions() {
		checkDimensions(false, false);
	}

	private void setClickState(int w, int h) {
		clickWidth = clickWidth == -1 ? w : clickWidth;
		clickHeight = clickHeight == -1 ? h : clickHeight;
		clickOffsetX = clickOffsetX == -1 ? component.getOffsetX() : clickOffsetX;
		clickOffsetY = clickOffsetY == -1 ? component.getOffsetY() : clickOffsetY;
	}

	public void initializeMouseListeners(JPanel p) {
		for (ClickableArea a : dragAreas) {
			a.initializeMouseListeners(p);
		}
	}

}
