package org.confetti.rcp.extensions;

import java.util.HashMap;
import java.util.Map;

import org.confetti.core.Teacher;

import org.confetti.rcp.ConfettiPlugin;
import org.confetti.util.Tuple;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Spinner;

/**
 * @author Gabor Bubla
 */
public class ConstraintField {
    
    private final static Map<String, FieldType> fieldTypeMapping = new HashMap<>();
    
    static {
        fieldTypeMapping.put("assignment-field", FieldType.Assignment);
        fieldTypeMapping.put("assignments-set-field", FieldType.AssignmentsSet);
        fieldTypeMapping.put("boolean-field", FieldType.Boolean);
        fieldTypeMapping.put("day-field", FieldType.Day);
        fieldTypeMapping.put("hour-field", FieldType.Hour);
        fieldTypeMapping.put("number-field", FieldType.Number);
        fieldTypeMapping.put("period-field", FieldType.Period);
        fieldTypeMapping.put("period-number-field", FieldType.PeriodNumber);
        fieldTypeMapping.put("teacher-field", FieldType.Teacher);
        fieldTypeMapping.put("week-field", FieldType.Week);
    }
    
    public enum FieldType {
        Boolean {
            @Override
            public void createControl(Composite parent) {
                new Button(parent, SWT.CHECK);
            }
        },
        Number{
            @Override
            public void createControl(Composite parent) {
                Spinner spinner = new Spinner(parent, SWT.BORDER);
                spinner.setMinimum(0);
                spinner.setMinimum(100);
                spinner.setIncrement(1);
                spinner.setPageIncrement(1);
                spinner.setSelection(98);
            }

        },
        Day{
            @Override
            public void createControl(Composite parent) {
                new Button(parent, SWT.CHECK);
            }

        },
        Hour{
            @Override
            public void createControl(Composite parent) {
                new Button(parent, SWT.CHECK);
            }

        },
        Week{
            @Override
            public void createControl(Composite parent) {
                new Button(parent, SWT.CHECK);
            }

        },
        Period{
            @Override
            public void createControl(Composite parent) {
                new Button(parent, SWT.CHECK);
            }

        },
        PeriodNumber{
            @Override
            public void createControl(Composite parent) {
                new Button(parent, SWT.CHECK);
            }

        },
        Teacher{
            @Override
            public void createControl(Composite parent) {
                ComboViewer combo = new ComboViewer(parent, SWT.READ_ONLY);
                combo.setContentProvider(ArrayContentProvider.getInstance());
                combo.setLabelProvider(new LabelProvider(){
                    @Override
                    public String getText(Object element) {
                        Teacher teacher = (Teacher) element;
                        return teacher.getName().getValue();
                    }
                });
                combo.setInput(ConfettiPlugin.getDefault().getDataProvider().getValue().getTeachers().getList());
            }

        },
        Assignment{
            @Override
            public void createControl(Composite parent) {
                
            }

        },
        AssignmentsSet{
            @Override
            public void createControl(Composite parent) {
                new Button(parent, SWT.CHECK);
            }

        };

        public abstract void createControl(Composite area);
    }
    
    private final String name;
    private final String label;
    private final FieldType type;
    
    public ConstraintField(IConfigurationElement element) {
        this.name = element.getAttribute("name");
        this.label = element.getAttribute("label");
        this.type = fieldTypeMapping.get(element.getName());
    }

    public String getName() { return name; }
    public String getLabel() { return label; }
    public FieldType getType() { return type; }

    public void createControl(Composite area) {
        getType().createControl(area);
    }

    
}
