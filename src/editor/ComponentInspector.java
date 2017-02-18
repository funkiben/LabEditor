package editor;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import editor.fieldregistry.EditableField;
import editor.fieldregistry.EditableFieldRegistry;
import editor.fieldregistry.InputWatcher;
import lab.component.EmptyComponent;
import lab.component.LabComponent;
import lab.component.geo.Rectangle;
import lab.component.swing.Label;
import lab.component.swing.input.InputComponent;

public class ComponentInspector extends LabComponent {

	private LabComponent target = null;
	private final List<InputWatcher> inputWatchers = new ArrayList<InputWatcher>();
	
	public ComponentInspector() {
		super(250, 500);
		
		setScaleChildren(false);
	}
	
	public LabComponent getTarget() {
		return target;
	}
	
	public void setTarget(LabComponent target) {
		this.target = target;
		
		for (LabComponent c : getChildren()) {
			this.removeChild(c);
		}
		
		inputWatchers.clear();
		
		addEditableFieldInputs(target, this);
	}
	
	private void addEditableFieldInputs(final Object target, LabComponent container) {
		
		Label label;
		
		Set<EditableField> fields = EditableFieldRegistry.getEditableFields(target.getClass());
		
		InputComponent input;
		
		int height = 0;
		
		for (EditableField field : fields) {
			if (field.getInputComponentInstantiator() == null) {
				
				MinimizableComponent c = new MinimizableComponent(field.getName(), container.getWidth(), 350);
				c.setMinimized(false);
				container.addChild(c);
				
				addEditableFieldInputs(field.getValue(target), c);
				
				height += c.getHeight();
				
				continue;
			}
			
			input = field.getInputComponentInstantiator().create();
			
			input.setValue(field.getValue(target));
			
			inputWatchers.add(new InputWatcher(input) {
				
				@Override
				public void onChangeDetected(Object previousValue, Object newValue) {
					field.setValue(target, newValue);
				}
				
			});
			
			label = new Label(100, 20, field.getName());
			label.setWidth(label.getTextWidth());
			label.setFontSize(12);
			label.setOffsetX(5);
			container.addChild(label);
			
			container.addChild(input);
			container.addChild(new EmptyComponent(1000000, 0));
			
			Rectangle rect = new Rectangle(getWidth(), 1);
			rect.setFillColor(Color.lightGray);
			rect.setStroke(false);
			container.addChild(rect);
			
			
			height += 20;
			
		}
		
		
	}
	
	@Override
	public void update() {
		for (InputWatcher watcher : inputWatchers) {
			watcher.check();
		}
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {	
	
		
	}

}
