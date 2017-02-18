package editor;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import draw.animation.Animator;
import draw.animation.DoubleLinearAnimation;
import lab.component.ClickableArea;
import lab.component.EmptyComponent;
import lab.component.LabComponent;

public class MinimizableComponent extends LabComponent {

	private final EmptyComponent content;
	private final String title;
	private final ClickableArea clickArea;
	private boolean isMinimized = false;
	private int expandedHeight;
	private final Animator animator = new Animator();
	private final int minimizedHeight = 17;
	
	public MinimizableComponent(String title, int width, int height) {
		super(width, height);

		this.title = title;

		content = new EmptyComponent(width, height - minimizedHeight);
		content.setOffsetY(minimizedHeight);
		super.addChild(content);
		
		expandedHeight = height;
		
		clickArea = new ClickableArea(this, 0, 0, width, minimizedHeight);
	}

	public String getTitle() {
		return title;
	}
	
	public boolean isMinimized() {
		return isMinimized;
	}
	
	public int getMinimizedHeight() {
		return minimizedHeight;
	}
	
	@Override
	public void setHeight(int height) {
		super.setHeight(height);
		this.expandedHeight = height;
	}
	
	public void setMinimized(boolean isMinimized) {
		this.isMinimized = isMinimized;
		
		if (isMinimized) {
			
			animator.addAnimation("stretch", new DoubleLinearAnimation((double) minimizedHeight, 25.0) {
				
				@Override
				public void setValue(Double d) {
					stretchToNewHeight(d.intValue());
				}
				
				@Override
				public Double getValue() {
					return (double) getHeight();
				}
				
			});
			
		} else {
			
			animator.addAnimation("stretch", new DoubleLinearAnimation((double) expandedHeight, 25.0) {
				
				@Override
				public void setValue(Double d) {
					stretchToNewHeight(d.intValue());
				}
				
				@Override
				public Double getValue() {
					return (double) getHeight();
				}
				
			});
			
		}
		
	}
	
	@Override
	public void addChild(LabComponent component) {
		content.addChild(component);
	}

	@Override
	public void addChild(LabComponent... components) {
		content.addChild(components);
	}

	@Override
	public void removeChild(LabComponent component) {
		content.removeChild(component);
	}

	@Override
	public List<LabComponent> getChildren() {
		return content.getChildren();
	}

	@Override
	public void draw(int x, int y, int w, int h, Graphics g) {
		g.setColor(new Color(230, 230, 230));
		g.fillRect(x, y, w, minimizedHeight);
		
		g.setColor(Color.black);
		g.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 13));
		g.drawString(title, x + 20, y + minimizedHeight - 5);
		
		int[] xs = new int[3];
		int[] ys = new int[3];
		
		if (isMinimized) {
			xs[0] = x + 5;
			xs[1] = x + 5;
			xs[2] = x + 10;
			
			ys[0] = y + 2;
			ys[1] = y + 12;
			ys[2] = y + 7;
		} else {
			xs[0] = x + 5;
			xs[1] = x + 15;
			xs[2] = x + 10;
			
			ys[0] = y + 5;
			ys[1] = y + 5;
			ys[2] = y + 10;
		}
		
		g.setColor(Color.black);
		g.fillPolygon(xs, ys, 3);
		
		clickArea.check(x, y, w, h);
		
		if (clickArea.hasClick() && !animator.animationExists("stretch")) {
			setMinimized(!isMinimized);
			clickArea.mouseReleased(null);
		}
		
		//g.setColor(Color.darkGray);
		//g.drawLine(x, y, x, y + h);
		
	}
	
	@Override
	public void update() {
		animator.animate();
		
		if (!animator.animationExists("stretch") && isMinimized) {
			for (LabComponent c : getChildren()) {
				c.setVisible(false);
			}
		} else {
			for (LabComponent c : getChildren()) {
				c.setVisible(true);
			}
		}
	}
	
	@Override
	public void initJPanel(JPanel panel) {
		clickArea.initializeMouseListeners(panel);
	}

}
