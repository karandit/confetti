package org.confetti.rcp.constraints;

import org.confetti.core.FieldTypeVisitor;
import org.eclipse.jface.layout.GridDataFactory;
import org.eclipse.swt.widgets.Control;

public enum FieldTypeApplyLayoutVisitor implements FieldTypeVisitor<Object, Control, Object> {
	INSTANCE;

	@Override public Object visitBoolean(Control ctrl, Object p2) 				{ return applyTo(ctrl); }
	@Override public Object visitDouble(Control ctrl, Object p2) 				{ return applyTo(ctrl); }
	@Override public Object visitInteger(Control ctrl, Object p2) 				{ return applyTo(ctrl); }
	@Override public Object visitDay(Control ctrl, Object p2) 					{ return applyTo(ctrl); }
	@Override public Object visitHour(Control ctrl, Object p2) 					{ return applyTo(ctrl); }
	@Override public Object visitWeek(Control ctrl, Object p2) 					{ return applyTo(ctrl, true, true); }
	@Override public Object visitPeriod(Control ctrl, Object p2) 				{ return applyTo(ctrl); }
	@Override public Object visitInterval(Control ctrl, Object p2) 				{ return applyTo(ctrl); }
	@Override public Object visitTeacher(Control ctrl, Object p2) 				{ return applyTo(ctrl); }
	@Override public Object visitStudentGroup(Control ctrl, Object p2) 			{ return applyTo(ctrl); }
	@Override public Object visitAssignment(Control ctrl, Object p2) 			{ return applyTo(ctrl); }
	@Override public Object visitAssignmentsSet(Control ctrl, Object p2) 		{ return applyTo(ctrl); }
	@Override public Object visitAssignmentsCriteria(Control ctrl, Object p2) 	{ return applyTo(ctrl); }
	@Override public Object visitRoom(Control ctrl, Object p2) 					{ return applyTo(ctrl); }
	@Override public Object visitRoomsSet(Control ctrl, Object p2) 				{ return applyTo(ctrl); }
	@Override public Object visitSubject(Control ctrl, Object p2) 				{ return applyTo(ctrl); }
	
	//----------- helpers ----------------------------------------------------------------------------------------------
	private static Object applyTo(Control ctrl, boolean hor, boolean ver) {
		GridDataFactory.fillDefaults().grab(hor, ver).applyTo(ctrl);
		return null;
	}
	private static Object applyTo(Control ctrl) {
		return applyTo(ctrl, true, false);
	}
}
