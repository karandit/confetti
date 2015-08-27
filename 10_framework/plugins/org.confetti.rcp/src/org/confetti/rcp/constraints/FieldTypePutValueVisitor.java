package org.confetti.rcp.constraints;

import org.confetti.core.Assignment;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Spinner;

import com.google.common.collect.Iterables;

public class FieldTypePutValueVisitor implements FieldTypeVisitor2<ConstraintAttributes, String, Control> {

	private final ConstraintAttributes attrs;
	public FieldTypePutValueVisitor(final ConstraintAttributes attrs) {
		this.attrs = attrs;
	}

	//-------------- FieldTypeVisitor ----------------------------------------------------------------------------------
	@Override
	public ConstraintAttributes visitBoolean(String key, Control ctrl) {
		return attrs.withBoolean(key, ((Button) ctrl).isEnabled());
	}
	@Override
	public ConstraintAttributes visitDouble(String key, Control ctrl) {
		return attrs.withDouble(key, (double) ((Spinner) ctrl).getSelection());
	}
	@Override
	public ConstraintAttributes visitInteger(String key, Control ctrl) {
		return attrs.withInteger(key, ((Spinner) ctrl).getSelection());
	}
	@Override
	public ConstraintAttributes visitDay(String key, Control ctrl) {
		return attrs.withInteger(key, ((Spinner) ctrl).getSelection());
	}
	@Override
	public ConstraintAttributes visitHour(String key, Control ctrl) {
		return attrs.withInteger(key, ((Spinner) ctrl).getSelection());
	}
	@Override
	public ConstraintAttributes visitWeek(String p1, Control p2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ConstraintAttributes visitPeriod(String p1, Control p2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ConstraintAttributes visitInterval(String p1, Control p2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ConstraintAttributes visitTeacher(String key, Control ctrl) {
		return attrs.withTeacher(key, this.<Teacher>getItemFromCombo(ctrl));
	}
	@Override
	public ConstraintAttributes visitStudentGroup(String key, Control ctrl) {
		return attrs.withStudentGroup(key, this.<StudentGroup> getItemFromCombo(ctrl));
	}
	@Override
	public ConstraintAttributes visitAssignment(String key, Control ctrl) {
    	return attrs.withAssignment(key, this.<Assignment>getItemFromCombo(ctrl));
	}
	@Override
	public ConstraintAttributes visitAssignmentsSet(String p1, Control p2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ConstraintAttributes visitAssignmentsCriteria(String p1, Control p2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ConstraintAttributes visitRoom(String key, Control ctrl) {
    	return attrs.withRoom(key, this.<Room>getItemFromCombo(ctrl));
	}
	@Override
	public ConstraintAttributes visitRoomsSet(String p1, Control p2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ConstraintAttributes visitSubject(String key, Control ctrl) {
		return attrs.withSubject(key, this.<Subject>getItemFromCombo(ctrl));
	}

	//---------------helpers -------------------------------------------------------------------------------------------
	private <T> T getItemFromCombo(Control ctrl) {
		Combo combo = (Combo) ctrl;
    	int selectionIndex = combo.getSelectionIndex();
    	@SuppressWarnings("unchecked")
		Iterable<T> list = (Iterable<T>) combo.getData();
    	return (T) Iterables.get(list, selectionIndex);
	}
}
