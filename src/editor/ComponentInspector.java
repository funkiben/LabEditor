package editor;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import editor.fieldregistry.EditableField;
import editor.fieldregistry.EditableFieldRegistry;
import editor.fieldregistry.InputWatcher;
import lab.component.EmptyComponent;
import lab.component.LabComponent;
import lab.component.MinimizableComponent;
import lab.component.swing.Label;
import lab.component.swing.input.InputComponent;

public class ComponentInspector extends LabComponent {

	private LabComponent target = null;
	private final List<InputWatcher> inputWatchers = new ArrayList<InputWatcher>();
	
	public ComponentInspector(int width, int height) {
		super(width, height);
		
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
		
		addEditableFieldInputs(target, this, 0);
	}
	
	private void addEditableFieldInputs(final Object target, LabComponent container, int offsetX) {
		
		Label label;
		
		Set<EditableField> fields = EditableFieldRegistry.getEditableFields(target.getClass());
		
		InputComponent input;
		
		int height = 0;
		
		for (EditableField field : fields) {
			if (field.getInputComponentInstantiator() == null) {
				
				MinimizableComponent c = new MinimizableComponent(field.getName(), 350, 350, 17);
				c.setOffsetY(5);
				c.setOffsetX(offsetX + 3);
				c.setMinimized(false);
				//c.setScaleChildren(false);
				
				container.addChild(c);
				
				addEditableFieldInputs(field.getValue(target), c, offsetX + 5);
				
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
			label.setFontSize(12);
			label.setOffsetX(5 + offsetX);
			container.addChild(label);
			container.addChild(input);
			container.addChild(new EmptyComponent(1000000, 0));
			
			height += 20;
			
		}
		
		container.setHeight(height);
		
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
