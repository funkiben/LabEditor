package editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import lab.component.LabComponent;
import lab.component.swing.Label;
import lab.component.swing.input.Button;
import lab.component.swing.input.field.DoubleField;

public class LabOptions extends LabComponent {

	public LabOptions(int width, int height) {
		super(width, height);
		
		Label header = new Label(100000, 20, "Window Size");
		header.setFont(new Font("default",Font.BOLD,18));
		
		Label windowWidth = new Label(100000, 20, "Window width:");
		DoubleField labWidth = new DoubleField(250, 20, 10, 0);
		
		Label windowHeight = new Label(100000, 20, "Window height:");
		DoubleField labHeight = new DoubleField(250, 20, 10, 0);
		
		Button applyButton = new Button(250, 50, "Apply") {

			@Override
			public void doSomething() {
				// TODO Auto-generated method stub
				
			}
		};
		
		addChild(header);
		addChild(windowWidth);
		addChild(labWidth);
		addChild(windowHeight);
		addChild(labHeight);
		addChild(applyButton);
		
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
	}

}
