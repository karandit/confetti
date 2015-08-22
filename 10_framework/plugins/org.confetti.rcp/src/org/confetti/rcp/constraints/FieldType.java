package org.confetti.rcp.constraints;

import static com.google.common.collect.Iterables.transform;
import static org.confetti.rcp.views.AssignmentsView.toStr;

import java.util.LinkedList;
import java.util.List;

import org.confetti.core.Assignment;
import org.confetti.core.ConstraintAttribute;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.observable.ObservableList;
import org.confetti.rcp.ConfettiPlugin;
import org.confetti.rcp.views.AssignmentsView;
import org.confetti.util.Triple;
import org.confetti.util.Tuple;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;

import com.google.common.collect.Iterables;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableNoScrollModel;

public enum FieldType {
	
    Boolean("boolean-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
            return new Button(parent, SWT.CHECK);
        }
        @Override
        public void putValue(String key, Control ctrl, ConstraintAttributes attrs) { 
        	attrs.withBoolean(key, ((Button) ctrl).isEnabled());
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return attrs.asBoolean(key).toString();
        }
    },
    Double("double-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
            return createSpinnerField(parent, 1, 100, 98);
        }
        @Override
        public void putValue(String key, Control ctrl, ConstraintAttributes attrs) { 
        	attrs.withDouble(key, (double) ((Spinner) ctrl).getSelection());
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return attrs.asDouble(key).toString();
        }
    },
    Integer("integer-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
            return createSpinnerField(parent, 1, 100, 98);
        }
        @Override
        public void putValue(String key, Control ctrl, ConstraintAttributes attrs) { 
        	attrs.withInteger(key, ((Spinner) ctrl).getSelection());
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return attrs.asInteger(key).toString();
        }
    },
    Day("day-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
            Iterable<Day> days = ConfettiPlugin.getDefault().getDataProvider().getValue().getDays().getList();
            int daysCount = Iterables.size(days);
            return createSpinnerField(parent, 1, daysCount, daysCount);
        }
        @Override
        public void putValue(String key, Control ctrl, ConstraintAttributes attrs) { 
        	attrs.withInteger(key, ((Spinner) ctrl).getSelection());
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return attrs.asInteger(key).toString();
        }
    },
    Hour("hour-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
            Iterable<Hour> hours = ConfettiPlugin.getDefault().getDataProvider().getValue().getHours().getList();
            int hoursCount = Iterables.size(hours);
            return createSpinnerField(parent, 1, hoursCount, hoursCount);
        }
        @Override
        public void putValue(String key, Control ctrl, ConstraintAttributes attrs) { 
        	attrs.withInteger(key, ((Spinner) ctrl).getSelection());
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return attrs.asInteger(key).toString();
        }
    },
    Week("week-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
    		final KTable ktable = new KTable(parent, SWT.NONE);
    		ktable.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
    		
    		DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
			KTableNoScrollModel model = new ConstraintFieldWeekModel(ktable, dp);
			ktable.setModel(model);
			model.initialize();
        	return ktable;
        }
        @Override
        public void applyLayout(Control ctrl) {
            GridDataFactory.fillDefaults().grab(true, true).applyTo(ctrl);
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return Iterables.toString(transform(attrs.asWeek(key), 
        			tuple -> safeGetName(tuple.getFirst()) + " " + safeGetName(tuple.getSecond())));
        }
    },
    Period("period-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
        	Button button = new Button(parent, SWT.PUSH);
			button.setText("Period Field NOT IMPLEMENTED");
        	return button;
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	Tuple<Day, Hour> period = attrs.asPeriod(key);
			return safeGetName(period.getFirst()) + " " + safeGetName(period.getSecond());
        }
    },
    Interval("interval-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
        	Button button = new Button(parent, SWT.PUSH);
			button.setText("Interval Field NOT IMPLEMENTED");
        	return button;
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	Tuple<Hour, Hour> interval = attrs.asInterval(key);
			return safeGetName(interval.getFirst()) + " " + safeGetName(interval.getSecond());
        }
    },
    Teacher("teacher-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
        	DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
            return createComboField(parent, dp.getTeachers().getList(), (Teacher) attribute.getValue());
        }
        @Override
        public void putValue(String key, Control ctrl, ConstraintAttributes attrs) { 
        	attrs.withTeacher(key, FieldType.<Teacher>getItemFromCombo(ctrl));
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return safeGetName(attrs.asTeacher(key));
        }
    }, 
    StudentGroup("studentgroup-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
        	DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
        	List<Tuple<StudentGroup, Integer>> groups = flatHierarchy(0, dp.getStudentGroups());
            return createComboField(parent, groups, (StudentGroup) attribute.getValue(), new LabelProvider(){
                @Override
                public String getText(Object element) {
                	@SuppressWarnings("unchecked")
					Tuple<StudentGroup, Integer> tuple = (Tuple<StudentGroup, Integer>) element;
                    return indent(tuple.getSecond()) + tuple.getFirst().getName().getValue();
                }
            });
        }
        @Override
        public void putValue(String key, Control ctrl, ConstraintAttributes attrs) {
        	Tuple<StudentGroup, Integer> selected = FieldType.<Tuple<StudentGroup, Integer>> getItemFromCombo(ctrl);
			attrs.withStudentGroup(key, selected.getFirst());
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return safeGetName(attrs.asStudentGroup(key));
        }

        private String indent(int count) {
        	StringBuilder sb = new StringBuilder();
        	for (int i = 0; i < count; i++) {
				sb.append("  ");
			}
        	return sb.toString();
        }
        
		private List<Tuple<StudentGroup, Integer>> flatHierarchy(int depth, ObservableList<StudentGroup> studentGroups) {
			List<Tuple<StudentGroup, Integer>> res = new LinkedList<>();
			for (StudentGroup sg : studentGroups.getList()) {
				res.add(new Tuple<>(sg, depth));
				res.addAll(flatHierarchy(depth + 1, sg.getChildren()));
			}
			return res;
		}
    },
    Assignment("assignment-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
            DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
        	return createComboField(parent, dp.getAssignments().getList(), 
        			(Assignment) attribute.getValue(), new LabelProvider(){
                @Override
                public String getText(Object element) {
                	Assignment ass = (Assignment) element;
                    return String.format("[%-30s][%-30s][%-30s][%-20s]"
                    		, safeGetName(ass.getSubject())
                    		, toStr(ass.getStudentGroups().getList())
                    		, toStr(ass.getTeachers().getList())
                    		, safeGetName(ass.getRoom())
                    		);
                }
            });
        }
        @Override
        public void putValue(String key, Control ctrl, ConstraintAttributes attrs) {
        	attrs.withAssignment(key, FieldType.<Assignment>getItemFromCombo(ctrl));
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return convertAssignmentToString(attrs.asAssignment(key));
        }
    },
    AssignmentsSet("assignments-set-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
        	Button button = new Button(parent, SWT.PUSH);
			button.setText("AssignmentSet Field NOT IMPLEMENTED");
        	return button;
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return Iterables.toString(transform(attrs.asAssignmentsSet(key), FieldType::convertAssignmentToString));
        }
    }, 
    AssignmentsCriteria("assignments-criteria-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
        	Button button = new Button(parent, SWT.PUSH);
			button.setText("AssignmentsCriteria Field NOT IMPLEMENTED");
        	return button;
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	Triple<Subject, Teacher, StudentGroup> triple = attrs.asAssignmentsCriteria(key);
        	return String.format("%s %s %s"
            		, safeGetName(triple.getFirst())
            		, safeGetName(triple.getSecond())
            		, safeGetName(triple.getThird())
            		);
        }
    }, 
    Room("room-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
        	DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
            return createComboField(parent, dp.getRooms().getList(), (Room) attribute.getValue());
        }
        @Override
        public void putValue(String key, Control ctrl, ConstraintAttributes attrs) {
        	attrs.withRoom(key, FieldType.<Room>getItemFromCombo(ctrl));
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return safeGetName(attrs.asRoom(key));
        }
    }, 
    RoomsSet("rooms-set-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
        	Button button = new Button(parent, SWT.PUSH);
			button.setText("RoomSet Field NOT IMPLEMENTED");
        	return button;
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return Iterables.toString(transform(attrs.asRoomsSet(key), FieldType::safeGetName));
        }
    }, 
    Subject("subject-field") {
        @Override
        public Control createControl(Composite parent, ConstraintAttribute<?> attribute) {
        	DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
            return createComboField(parent, dp.getSubjects().getList(), (Subject) attribute.getValue());
        }
        @Override
        public void putValue(String key, Control ctrl, ConstraintAttributes attrs) {
        	attrs.withSubject(key, FieldType.<Subject>getItemFromCombo(ctrl));
        }
        @Override
        public String prettyPrint(String key, ConstraintAttributes attrs) {
        	return safeGetName(attrs.asSubject(key));
        }
    };

    private String type;

	private FieldType(final String type) {
		this.type = type;
	}
    
	public static FieldType getByType(String name) {
		for (FieldType fieldType : values()) {
			if (fieldType.type.equals(name)) {
				return fieldType;
			}
		}
		return null;
	}

    //---------------------- API abstract methods ----------------------------------------------------------------------
    public abstract Control createControl(Composite area, ConstraintAttribute<?> attribute);
    public /* abstract */ void putValue(String key, Control ctrl, ConstraintAttributes attrs) {}
    public abstract String prettyPrint(String key, ConstraintAttributes attrs); 
    
    //---------------------- API methods -------------------------------------------------------------------------------
    public void applyLayout(Control ctrl) {
        GridDataFactory.fillDefaults().grab(true, false).applyTo(ctrl);
    }
    
    //---------------- helpers -----------------------------------------------------------------------------------------
    private static String safeGetName(Nameable ent) {
    	String name = AssignmentsView.getName(ent);
		return name == null ? "" : name;
    }

    private static String convertAssignmentToString(Assignment ass) {
		StringBuilder sb = new StringBuilder()
    	.append("{")
    	.append(safeGetName(ass.getSubject()))
    	.append("/")
    	.append(Iterables.toString(Iterables.transform(
    			ass.getTeachers().getList(), FieldType::safeGetName)))
    	.append("/")
    	.append(Iterables.toString(Iterables.transform(
    			ass.getStudentGroups().getList(), FieldType::safeGetName)));
    	if (ass.getRoom() != null) {
    		sb.append("/")
    		.append(safeGetName(ass.getRoom()));
    	}
    	sb.append("}");
		return sb.toString();
	}

    private static Control createSpinnerField(Composite parent, int min, int max, int cur) {
		Spinner spinner = new Spinner(parent, SWT.BORDER);
        spinner.setMinimum(min);
        spinner.setMaximum(max);
        spinner.setIncrement(1);
        spinner.setPageIncrement(1);
        spinner.setSelection(cur);
        return spinner;
	}

    private static <T> T getItemFromCombo(Control ctrl) {
		Combo combo = (Combo) ctrl;
    	int selectionIndex = combo.getSelectionIndex();
    	@SuppressWarnings("unchecked")
		Iterable<T> list = (Iterable<T>) combo.getData();
    	return (T) Iterables.get(list, selectionIndex);
	}
    
	private static <T extends Nameable >Control createComboField(Composite parent, 
			Iterable<T> items, T item) {
		return createComboField(parent, items, item, new LabelProvider(){
            @Override
            public String getText(Object element) {
            	return ((Nameable) element).getName().getValue();
            }
        });
	}
	
	private static Control createComboField(Composite parent, Iterable<?> items, Object selectedItem,
			LabelProvider labelProvider) {
		ComboViewer combo = new ComboViewer(parent, SWT.READ_ONLY);
        combo.setContentProvider(ArrayContentProvider.getInstance());
        combo.setLabelProvider(labelProvider);
		combo.setInput(items);
        combo.getControl().setData(items);
        if (selectedItem != null) {
        	combo.setSelection(new StructuredSelection(selectedItem));
        }
        return combo.getControl();
	}
}
