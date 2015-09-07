package org.confetti.rcp.views;

import org.confetti.core.DataProvider;
import org.confetti.core.Entity;
import org.confetti.core.EntityVisitor;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.util.Tuple;

import de.kupzog.ktable.KTable;
import de.kupzog.ktable.KTableDefaultModel;

enum TimeTableModelFactory implements EntityVisitor<KTableDefaultModel, Tuple<DataProvider, KTable>> {
	
	INSTANCE;

	@Override public KTableDefaultModel visitSubject(Subject e, Tuple<DataProvider, KTable> p) { return create(e, p); }
	@Override public KTableDefaultModel visitTeacher(Teacher e, Tuple<DataProvider, KTable> p) { return create(e, p); }
	@Override public KTableDefaultModel visitRoom(Room e, Tuple<DataProvider, KTable> p) 		{ return create(e, p); }

	@Override
	public KTableDefaultModel visitStudentGroup(StudentGroup sg, Tuple<DataProvider, KTable> p) {
		return new TimeTableColumnModel(p.getSecond(), p.getFirst(), sg);
	}

	//----------------- helper -----------------------------------------------------------------------------------------
	private KTableDefaultModel create(Entity e, Tuple<DataProvider, KTable> p) {
		return new TimeTableModel(p.getSecond(), p.getFirst(), e); 
	}

}