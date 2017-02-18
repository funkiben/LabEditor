package editor;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import editor.fieldregistry.EditableField;
import editor.fieldregistry.EditableFieldRegistry;
import editor.fieldregistry.FieldInputSyncer;
import lab.component.EmptyComponent;
import lab.component.LabComponent;
import lab.component.geo.Rectangle;
import lab.component.swing.Label;
import lab.component.swing.input.InputComponent;

public class ComponentInspector extends LabComponent {

	private LabComponent target = null;
	private final List<FieldInputSyncer> fieldInputSyncers = new ArrayList<FieldInputSyncer>();
	
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
		
		fieldInputSyncers.clear();
		
		addEditableFieldInputs(target, this);
	}
	
	private void addEditableFieldInputs(final Object target, LabComponent container) {
		
		Label label;
		
		Set<EditableField> fields = EditableFieldRegistry.getEditableFields(target.getClass());
		
		InputComponent input;
		
		int height = container instanceof MinimizableComponent ? 17 : 0;
		
		for (EditableField field : fields) {
			if (field.getInputComponentInstantiator() == null) {
				
				MinimizableComponent c = new MinimizableComponent(field.getName(), container.getWidth(), container.getHeight());
				container.addChild(c);
				
				addEditableFieldInputs(field.getValue(target), c);
				
				height += c.getHeight();
				
				continue;
			}
			
			input = field.getInputComponentInstantiator().create();
			
			input.setValue(field.getValue(target));
			
			fieldInputSyncers.add(new FieldInputSyncer(target, input, field));
			
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
			
			
			height += 1 + Math.max(input.getHeight(), label.getHeight());
			
		}
		
		if (container instanceof MinimizableComponent) {
			Rectangle rect = new Rectangle(getWidth(), 3);
			rect.setFillColor(new Color(230, 230, 230));
			rect.setStroke(false);
			container.addChild(rect);
			height += 3;
		}
		
		container.setHeight(height);
	}
	
	@Override
	public void update() {
		for (FieldInputSyncer syncer : fieldInputSyncers) {
			syncer.sync(true);
		}
	}

	@Override
	public void draw(int x, int y, int width, int height, Graphics g) {	
	
		
	}

}
