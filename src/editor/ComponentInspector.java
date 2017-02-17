package editor;

import java.awt.Graphics;
import java.util.Set;

import editor.fieldregistry.EditableField;
import editor.fieldregistry.EditableFieldRegistry;
import lab.component.EmptyComponent;
import lab.component.LabComponent;
import lab.component.MinimizableComponent;
import lab.component.swing.Label;

public class ComponentInspector extends LabComponent {

	private LabComponent target = null;
	
	public ComponentInspector(int width, int height) {
		super(width, height);
	}
	
	public LabComponent getTarget() {
		return target;
	}
	
	public void setTarget(LabComponent target) {
		this.target = target;
		
		for (LabComponent c : getChildren()) {
			this.removeChild(c);
		}
		
		addEditableFieldInputs(target, this);
	}
	
	private void addEditableFieldInputs(Object target, LabComponent container) {
		
		Label label;
		
		Set<EditableField> fields = EditableFieldRegistry.getEditableFields(target.getClass());
		
		for (EditableField field : fields) {
			if (field.getInputComponent() == null) {
				
				LabComponent c = new MinimizableComponent(field.getName(), 350, 350, 17);
				c.setOffsetY(5);
				container.addChild(c);
				addEditableFieldInputs(field.getValue(target), c);
				
				continue;
			}
			
			field.getInputComponent().setValue(field.getValue(target));
			
			label = new Label(100, 20, field.getName());
			label.setFontSize(12);
			label.setOffsetX(5);
			container.addChild(label);
			container.addChild(field.getInputComponent());
			container.addChild(new EmptyComponent(1000000, 0));
			
		}
		
	}
	
	@Override
	public void update() {
		
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {
		
	}

}
