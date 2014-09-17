package org.confetti.dataprovider.xml;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.confetti.core.Assignable;
import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Entity;
import org.confetti.core.EntityVisitor;
import org.confetti.core.Hour;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.confetti.xml.FAOException;
import org.confetti.xml.InstituteFAO;
import org.confetti.xml.core.ActivityXml;
import org.confetti.xml.core.DayXml;
import org.confetti.xml.core.GroupXml;
import org.confetti.xml.core.HourXml;
import org.confetti.xml.core.InstituteXml;
import org.confetti.xml.core.RoomXml;
import org.confetti.xml.core.SubgroupXml;
import org.confetti.xml.core.SubjectXml;
import org.confetti.xml.core.TeacherRef;
import org.confetti.xml.core.TeacherXml;
import org.confetti.xml.core.YearXml;

/**
 * @author Bubla Gabor
 */
public class XmlDataProvider implements DataProvider {
	
	//----------------------------- inner classes ----------------------------------------------------------------------
	private static class AssignmentImpl implements Assignment {

		private final Subject subj;
		private final ListMutator<Teacher> teachers = new ListMutator<>();
		private final ListMutator<StudentGroup> stGroups = new ListMutator<>();
		
		
		public AssignmentImpl(Subject subj) {
			this.subj = subj;
			subj.addAssignment(this);
		}

		public void addTeacher(Teacher teacher) 			{ teachers.addItem(teacher); teacher.addAssignment(this);} 
		public void addStudentGroup(StudentGroup group) 	{ stGroups.addItem(group); group.addAssignment(this);} 
		
		@Override public Subject getSubject() 								{ return subj; }
		@Override public ObservableList<Teacher> getTeachers() 				{ return teachers.getObservableList(); }
		@Override public ObservableList<StudentGroup> getStudentGroups() 	{ return stGroups.getObservableList(); }
		@Override public Room getRoom() 									{ return null; }
	}
	
	private static abstract class EntityImpl implements Entity, Assignable {

		private final ValueMutator<String> name;
		private final ListMutator<Assignment> assignments = new ListMutator<>();
		
		public EntityImpl(String name) {
			this.name = new ValueMutator<>(this, name);
		}
		
		@Override public ObservableValue<String> getName() 			{ return name.getObservableValue(); }
		@Override public void addAssignment(Assignment assignment) 	{ assignments.addItem(assignment);} 
		@Override public ObservableList<Assignment> getAssignments() 			{ return assignments.getObservableList(); }
		
		public ValueMutator<String> getNameMutator() { return name; }

	}
	
	private static class TeacherImpl extends EntityImpl implements Teacher {
		public TeacherImpl(String name) { super(name); }

		@Override
		public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
			return visitor.visitTeacher(this, param);
		}
	}

	private static class SubjectImpl extends EntityImpl implements Subject {
		public SubjectImpl(String name) { super(name); }

		@Override
		public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
			return visitor.visitSubject(this, param);
		}
	}

	private static class RoomImpl extends EntityImpl implements Room {
		public RoomImpl(String name) { super(name); }

		@Override
		public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
			return visitor.visitRoom(this, param);
		}
	}

	private static class StudentGroupImpl extends EntityImpl implements StudentGroup {
		
		private final ListMutator<StudentGroup> children = new ListMutator<>();
		
		public StudentGroupImpl(String name) {
			super(name);
		}

		public void addChild(StudentGroup child) 			{ children.addItem(child); }
		@Override public ObservableList<StudentGroup> getChildren() 	{ return children.getObservableList(); }
		@Override public StudentGroup getParent() 			{ return null; }

		@Override
		public <R, P> R accept(EntityVisitor<R, P> visitor, P param) {
			return visitor.visitStudentGroup(this, param);
		}
	}
	
	private static class DayImpl implements Day {

		private final ValueMutator<String> name;
		public DayImpl(String name) {
			this.name = new ValueMutator<>(this, name);
		}
		
		@Override public ObservableValue<String> getName() 			{ return name.getObservableValue(); }

	}

	private static class HourImpl implements Hour {

		private final ValueMutator<String> name;
		public HourImpl(String name) {
			this.name = new ValueMutator<>(this, name);
		}
		
		@Override public ObservableValue<String> getName() 			{ return name.getObservableValue(); }

	}

	//----------------------------- fields -----------------------------------------------------------------------------
	private ValueMutator<String> instName = new ValueMutator<>();
	private ListMutator<Teacher> teachers = new ListMutator<>();
	private ListMutator<Subject> subjects = new ListMutator<>();
	private ListMutator<StudentGroup> stdGroups = new ListMutator<>();
	private ListMutator<Room> rooms = new ListMutator<>();
	private ListMutator<Day> days = new ListMutator<>();
	private ListMutator<Hour> hours = new ListMutator<>();
	private ListMutator<Assignment> assignments = new ListMutator<>();

	//----------------------------- constructors -----------------------------------------------------------------------
	public XmlDataProvider(File file) throws FAOException {
		this(new InstituteFAO().importFrom(file));
	}
	
	public XmlDataProvider(InstituteXml inst ) {
			for (SubjectXml subj : inst.getSubjects()) {
				subjects.addItem(new SubjectImpl(subj.getName()));
			}
			for (TeacherXml teacher : inst.getTeachers()) {
				teachers.addItem(new TeacherImpl(teacher.getName()));
			}
			for (RoomXml room : inst.getRooms()) {
				rooms.addItem(new RoomImpl(room.getName()));
			}
			for (YearXml year : inst.getYears()) {
				StudentGroupImpl studentGroup1 = new StudentGroupImpl(year.getName());
				stdGroups.addItem(studentGroup1);
				for (GroupXml group : year.getGroups()) {
					StudentGroupImpl studentGroup2 = new StudentGroupImpl(group.getName());
					studentGroup1.addChild(studentGroup2);
					for (SubgroupXml subgroup : group.getSubgroups()) {
						StudentGroupImpl studentGroup3 = new StudentGroupImpl(subgroup.getName());
						studentGroup2.addChild(studentGroup3);
					}
				}
			}
			for (DayXml day : inst.getDays().getDays()) {
				days.addItem(new DayImpl(day.getName()));
			}
			for (HourXml hour : inst.getHours().getHours()) {
				hours.addItem(new HourImpl(hour.getName()));
			}

			Iterable<Subject> allSubjects = subjects.getObservableList().getList();
			Iterable<Teacher> allTeachers = teachers.getObservableList().getList();
			Map<String, StudentGroup> allStdGroups = collectStudentGroups(stdGroups.getObservableList().getList());
			for (ActivityXml act : inst.getActivities()) {
				AssignmentImpl ass = new AssignmentImpl(findByName(allSubjects, act.getSubject().getName()));
				if (act.getStudents() != null) {
					for (String stGroupName : act.getStudents()) {
						ass.addStudentGroup(allStdGroups.get(stGroupName));
					}
				}
				if (act.getTeachers() != null) {
					for (TeacherRef teacherRef : act.getTeachers()) {
						ass.addTeacher(findByName(allTeachers, teacherRef.getName()));
					}
				}
			}
	}
	
	private Map<String, StudentGroup> collectStudentGroups(Iterable<StudentGroup> list) {
		Map<String, StudentGroup> res = new HashMap<>();
		for (StudentGroup sg : list) {
			res.put(sg.getName().getValue(), sg);
			res.putAll(collectStudentGroups(sg.getChildren().getList()));
		}
		return res;
	}

	//----------------------------- DataProvider's API -----------------------------------------------------------------
	@Override public ObservableValue<String> getName() 					{ return instName.getObservableValue(); }
	@Override public ObservableList<Teacher> getTeachers() 				{ return teachers.getObservableList(); }
	@Override public ObservableList<Subject> getSubjects() 				{ return subjects.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	{ return stdGroups.getObservableList(); }
	@Override public ObservableList<Room> getRooms() 					{ return rooms.getObservableList(); }
	@Override public ObservableList<Day> getDays() 						{ return days.getObservableList(); }
	@Override public ObservableList<Hour> getHours() 					{ return hours.getObservableList(); }
	@Override public ObservableList<Assignment> getAssignments() 		{ return assignments.getObservableList(); }

	
	@Override
	public Subject addSubject(String name) {
		SubjectImpl subjectImpl = new SubjectImpl(name);
		subjects.addItem(subjectImpl);
		return subjectImpl;
	}
	
	@Override
	public Teacher addTeacher(String name) {
		TeacherImpl teacherImpl = new TeacherImpl(name);
		teachers.addItem(teacherImpl);
		return teacherImpl;
	}
	
	@Override
	public StudentGroup addStudentGroup(StudentGroup parent, String name) {
		//TODO
		return null;
	}
	
	@Override
	public Room addRoom(String name) {
		RoomImpl roomImpl = new RoomImpl(name);
		rooms.addItem(roomImpl);
		return roomImpl;
	}
	
	@Override public void removeSubject(Subject subject) 	{ subjects.removeItem(subject); }
	@Override public void removeTeacher(Teacher teacher) 	{ teachers.removeItem(teacher); }
	@Override public void removeRoom(Room room)          	{ rooms.removeItem(room); }
	
	@Override
	public void setDays(List<String> days) {
		//TODO
	}
	
	@Override
	public void setHours(List<String> hours) {
		//TODO
	}
	
	@Override
	public Assignment addAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups) {
		AssignmentImpl assignment = new AssignmentImpl(subject);
		for (Teacher teacher : teachers) {
			assignment.addTeacher(teacher);
		}
		for (StudentGroup studentGroup : studentGroups) {
			assignment.addStudentGroup(studentGroup);
		}
		return assignment;
	}
	
	@Override
	public void rename(Entity entity, String newName) {
		((EntityImpl) entity).getNameMutator().setValue(entity, newName);
	}
	
	//----------------------------- helpers ----------------------------------------------------------------------------
//	private static InputStream openStream(final String path) throws IOException {
//		return XmlDataProvider.class.getResource(path).openStream();
//	}
	
	private static <T extends Entity> T findByName(Iterable<T> items, String name) {
		for (T item : items) {
			if (item.getName().getValue().equals(name)) {
				return item;
			}
		}
		return null;
	}

}
