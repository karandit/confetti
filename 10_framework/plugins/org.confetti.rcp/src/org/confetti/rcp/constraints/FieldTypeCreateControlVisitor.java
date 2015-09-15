package org.confetti.rcp.constraints;

import static org.confetti.rcp.views.AssignmentsView.toStr;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.confetti.core.Assignment;
import org.confetti.core.ConstraintAttribute;
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
import org.confetti.util.Tuple;
import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.jface.viewers.ComboViewer;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableNoScrollModel;

public enum FieldTypeCreateControlVisitor implements FieldTypeVisitor<Control, Composite, ConstraintAttribute<?>> {
	INSTANCE;
	
	@Override
	public Control visitBoolean(Composite parent, ConstraintAttribute<?> attribute) {
        Button button = new Button(parent, SWT.CHECK);
		java.lang.Boolean value = safeGet(attribute, java.lang.Boolean.FALSE);
		button.setSelection(value);
        return button;
    }

	@Override
	public Control visitDouble(Composite parent, ConstraintAttribute<?> attribute) {
    	//TODO: spinner doesn't support decimal values
    	Double defVal = safeGet(attribute, new Double(98.0));
    	return createSpinnerField(parent, 0, 100, defVal.intValue());
    }

	@Override
	public Control visitInteger(Composite parent, ConstraintAttribute<?> attribute) {
        return createSpinnerField(parent, 0, 100, safeGet(attribute, new Integer(98)));
    }

	@Override
	public Control visitDay(Composite parent, ConstraintAttribute<?> attribute) {
        Iterable<Day> days = ConfettiPlugin.getDefault().getDataProvider().getValue().getDays().getList();
        int daysCount = Iterables.size(days);
        return createSpinnerField(parent, 0, daysCount, safeGet(attribute, daysCount));
    }

	@Override
	public Control visitHour(Composite parent, ConstraintAttribute<?> attribute) {
        Iterable<Hour> hours = ConfettiPlugin.getDefault().getDataProvider().getValue().getHours().getList();
        int hoursCount = Iterables.size(hours);
        return createSpinnerField(parent, 0, hoursCount, safeGet(attribute, hoursCount));
    }

	@Override
	public Control visitWeek(Composite parent, ConstraintAttribute<?> attribute) {
		final KTable ktable = new KTable(parent, SWT.NONE);
		ktable.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_LIST_BACKGROUND));
		//TODO: implement pre-selection
		DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
		KTableNoScrollModel model = new ConstraintFieldWeekModel(ktable, dp);
		ktable.setModel(model);
		model.initialize();
    	return ktable;
    }

	@Override
	public Control visitPeriod(Composite parent, ConstraintAttribute<?> attribute) {
    	Button button = new Button(parent, SWT.PUSH);
		button.setText("Period Field NOT IMPLEMENTED");
    	return button;
    }

	@Override
	public Control visitInterval(Composite parent, ConstraintAttribute<?> attribute) {
    	Button button = new Button(parent, SWT.PUSH);
		button.setText("Interval Field NOT IMPLEMENTED");
    	return button;
    }

	@Override
	public Control visitTeacher(Composite parent, ConstraintAttribute<?> attribute) {
    	DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
        return createComboField(parent, dp.getTeachers().getList(), this.<Teacher>safeGet(attribute, null));
    }

	@Override
	public Control visitStudentGroup(Composite parent, ConstraintAttribute<?> attribute) {
    	DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
    	List<Tuple<StudentGroup, Integer>> groupsAndIndents = flatHierarchy(0, dp.getStudentGroups());
        List<StudentGroup> groups = Lists.transform(groupsAndIndents, x -> x.getFirst());
    	Map<StudentGroup, Integer> indents = new HashMap<>();
    	groupsAndIndents.forEach(x -> indents.put(x.getFirst(), x.getSecond()));
    	return createComboField(parent, groups, this.<StudentGroup>safeGet(attribute, null), new LabelProvider(){
            @Override
            public String getText(Object element) {
				StudentGroup sg = (StudentGroup) element;
                return indent(indents.get(sg)) + safeGetName(sg);
            }
        });
    }

	@Override
	public Control visitAssignment(Composite parent, ConstraintAttribute<?> attribute) {
        DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
    	return createComboField(parent, dp.getAssignments().getList(), 
    			this.<Assignment>safeGet(attribute, null), new LabelProvider(){
            @Override
            public String getText(Object element) {
            	Assignment ass = (Assignment) element;
                return String.format("[%-30s][%-30s][%-30s]"
                		, safeGetName(ass.getSubject())
                		, toStr(ass.getStudentGroups().getList())
                		, toStr(ass.getTeachers().getList())
                		);
            }
        });
    }

	@Override
	public Control visitAssignmentsSet(Composite parent, ConstraintAttribute<?> attribute) {
    	Button button = new Button(parent, SWT.PUSH);
		button.setText("AssignmentSet Field NOT IMPLEMENTED");
    	return button;
    }

	@Override
	public Control visitAssignmentsCriteria(Composite parent, ConstraintAttribute<?> attribute) {
    	Button button = new Button(parent, SWT.PUSH);
		button.setText("AssignmentsCriteria Field NOT IMPLEMENTED");
    	return button;
    }
	
	@Override
	public Control visitRoom(Composite parent, ConstraintAttribute<?> attribute) {
    	DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
        return createComboField(parent, dp.getRooms().getList(), this.<Room>safeGet(attribute, null));
    }

	@Override
	public Control visitRoomsSet(Composite parent, ConstraintAttribute<?> attribute) {
    	Button button = new Button(parent, SWT.PUSH);
		button.setText("RoomSet Field NOT IMPLEMENTED");
    	return button;
    }

	@Override
	public Control visitSubject(Composite parent, ConstraintAttribute<?> attribute) {
    	DataProvider dp = ConfettiPlugin.getDefault().getDataProvider().getValue();
        return createComboField(parent, dp.getSubjects().getList(), this.<Subject>safeGet(attribute, null));
    }
    //---------------- helpers -----------------------------------------------------------------------------------------
    static String safeGetName(Nameable ent) {
    	String name = AssignmentsView.getName(ent);
		return name == null ? "" : name;
    }
    
    @SuppressWarnings("unchecked")
	private <T> T safeGet(ConstraintAttribute<?> attribute, T defaultValue) {
		if (attribute != null && attribute.getValue() != null) {
			return (T) attribute.getValue();
		}
    	return defaultValue;
	}
    
    private static String indent(int count) {
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

    private static Control createSpinnerField(Composite parent, int min, int max, int cur) {
		Spinner spinner = new Spinner(parent, SWT.BORDER);
        spinner.setMinimum(min);
        spinner.setMaximum(max);
        spinner.setIncrement(1);
        spinner.setPageIncrement(1);
        spinner.setSelection(cur);
        return spinner;
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
