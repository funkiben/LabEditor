package editor.input;

import java.awt.Color;

import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import lab.component.geo.Rectangle;
import lab.component.swing.input.Button;

public class ChangeColorButton extends Button implements ChangeListener {

	private JColorChooser colorChooser;
	private JFrame frame;
	private Color color = Color.white;
	private Rectangle colorSampleRect;
	
	public ChangeColorButton(int width, int height, String name) {
		super(width, height, name);
		
		setOffsetX(height);
		
		frame = new JFrame(name);
		frame.setSize(200, 200);
		
		colorChooser = new JColorChooser();
		colorChooser.getSelectionModel().addChangeListener(this);
		frame.setContentPane(colorChooser);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		colorSampleRect = new Rectangle(height - 4, height - 4);
		colorSampleRect.setOffset(-height + 4, 2);
		colorSampleRect.setStrokeColor(Color.black);
		addChild(colorSampleRect);
		
	}
	
	public Color getColor() {
		return color;
	}
	
	@Override
	public Color getValue() {
		return color;
	}
	
	@Override
	public void setValue(Object v) {
		this.color = (Color) v;
		colorChooser.setColor(color);
		colorSampleRect.setFillColor(color);
	}

	@Override
	public void doSomething() {
		frame.setVisible(true);
		colorChooser.setColor(color);
	}

	@Override
	public void stateChanged(ChangeEvent paramChangeEvent) {
		color = colorChooser.getColor();
		colorSampleRect.setFillColor(color);
	}

	@Override
	public boolean hasFocus() {
		return super.hasFocus() || colorChooser.isVisible();
	}

}
