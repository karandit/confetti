package org.confetti.rcp.extensions;

import java.util.HashMap;
import java.util.Map;

import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Teacher;
import org.confetti.rcp.ConfettiPlugin;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;

import com.google.common.collect.Iterables;

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
            public Control createControl(Composite parent) {
                return new Button(parent, SWT.CHECK);
            }
        },
        Number{
            @Override
            public Control createControl(Composite parent) {
                return createSpinnerField(parent, 1, 100, 98);
            }
        },
        Day{
            @Override
            public Control createControl(Composite parent) {
                Iterable<Day> days = ConfettiPlugin.getDefault().getDataProvider().getValue().getDays().getList();
                int daysCount = Iterables.size(days);
                return createSpinnerField(parent, 1, daysCount, daysCount);
            }
        },
        Hour{
            @Override
            public Control createControl(Composite parent) {
                Iterable<Hour> hours = ConfettiPlugin.getDefault().getDataProvider().getValue().getHours().getList();
                int hoursCount = Iterables.size(hours);
                return createSpinnerField(parent, 1, hoursCount, hoursCount);
            }
        },
        Week{
            @Override
            public Control createControl(Composite parent) {
                return new Button(parent, SWT.PUSH);
            }

        },
        Period{
            @Override
            public Control createControl(Composite parent) {
            	return new Button(parent, SWT.PUSH);
            }

        },
        PeriodNumber{
            @Override
            public Control createControl(Composite parent) {
            	return new Button(parent, SWT.PUSH);
            }

        },
        Teacher{
            @Override
            public Control createControl(Composite parent) {
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
                return combo.getControl();
            }

        },
        Assignment{
            @Override
            public Control createControl(Composite parent) {
            	return new Button(parent, SWT.PUSH);
            }

        },
        AssignmentsSet{
            @Override
            public Control createControl(Composite parent) {
                return new Button(parent, SWT.PUSH);
            }

        };

        public abstract Control createControl(Composite area);
		private static Control createSpinnerField(Composite parent, int min, int max, int cur) {
			Spinner spinner = new Spinner(parent, SWT.BORDER);
            spinner.setMinimum(min);
            spinner.setMaximum(max);
            spinner.setIncrement(1);
            spinner.setPageIncrement(1);
            spinner.setSelection(cur);
            return spinner;
		}
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

    public Control createControl(Composite area) {
        return getType().createControl(area);
    }

    
}
