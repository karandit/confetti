package org.confetti.dataprovider.xml;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import org.confetti.core.Assignable;
import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Entity;
import org.confetti.core.Hour;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.confetti.xml.InstituteFAO;
import org.confetti.xml.core.GroupXml;
import org.confetti.xml.core.InstituteXml;
import org.confetti.xml.core.RoomXml;
import org.confetti.xml.core.SubgroupXml;
import org.confetti.xml.core.SubjectXml;
import org.confetti.xml.core.TeacherXml;
import org.confetti.xml.core.YearXml;

/**
 * @author Bubla Gabor
 */
public class XmlDataProvider implements DataProvider {
	
	//----------------------------- inner classes ----------------------------------------------------------------------
	private static class AssignmentImpl implements Assignment {

		private final Subject subj;
		private final List<Room> rooms = new LinkedList<>();
		private final List<Teacher> teachers = new LinkedList<>();
		private final List<StudentGroup> stGroups = new LinkedList<>();
		
		public AssignmentImpl(Subject subj) {
			this.subj = subj;
			subj.addAssignment(this);
		}

		public void addTeacher(Teacher teacher) 			{ teachers.add(teacher); teacher.addAssignment(this);} 
		public void addRoom(Room room) 			{ rooms.add(room); room.addAssignment(this);} 
		public void addStudentGroup(StudentGroup group) 	{ stGroups.add(group); group.addAssignment(this);} 
		
		@Override public Subject getSubj() { return subj; }
		@Override public List<Teacher> getTeachers() { return teachers; }
		@Override public List<StudentGroup> getStudentGroups() { return stGroups; }
		@Override public Room getRoom() { return null; }
	}
	
	private static class EntityImpl implements Entity, Assignable {

		private final ValueMutator<String> name;
		
		private final List<Assignment> assignments = new LinkedList<>();
		
		public EntityImpl(String name) {
			this.name = new ValueMutator<>(name);
		}
		
		@Override public ObservableValue<String> getName() 			{ return name.getObservableValue(); }
		@Override public void addAssignment(Assignment assignment) 	{ assignments.add(assignment);} 
		@Override public List<Assignment> getAssignments() 			{ return assignments; }
	}
	
	private static class TeacherImpl extends EntityImpl implements Teacher {
		public TeacherImpl(String name) { super(name); }
	}

	private static class SubjectImpl extends EntityImpl implements Subject {
		public SubjectImpl(String name) { super(name); }
	}

	private static class RoomImpl extends EntityImpl implements Room {
		public RoomImpl(String name) { super(name); }
	}

	private static class StudentGroupImpl extends EntityImpl implements StudentGroup {
		
		private final List<StudentGroup> children = new LinkedList<>();
		
		public StudentGroupImpl(String name) {
			super(name);
		}

		public void addChild(StudentGroup child) 			{ children.add(child); }
		@Override public List<StudentGroup> getChildren() 	{ return children; }
		@Override public StudentGroup getParent() 			{ return null; }
	}
	
	//----------------------------- fields -----------------------------------------------------------------------------
	private ValueMutator<String> instName = new ValueMutator<>();
	private ListMutator<Teacher> teachers = new ListMutator<>();
	private ListMutator<Subject> subjects = new ListMutator<>();
	private ListMutator<StudentGroup> stdGroups = new ListMutator<>();
	private ListMutator<Room> rooms = new ListMutator<>();
	private ListMutator<Day> days = new ListMutator<>();
	private ListMutator<Hour> hours = new ListMutator<>();

	//----------------------------- constructors -----------------------------------------------------------------------
	public XmlDataProvider(File file) {
		try {
			InstituteXml inst = new InstituteFAO().importFrom(file);
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
//			for (ActivityXml act : inst.getActivities()) {
//				AssignmentImpl ass = new AssignmentImpl(findByName(subjects, act.getSubject().getName()));
//				for (String stGroupName : act.getStudents()) {
//					try {
//						ass.addStudentGroup(findByName(stdGroups, stGroupName));
//					} catch (Exception e) {
////						System.out.println(stGroupName);
////						System.out.println(e.getMessage());
//					}
//				}
//				for (TeacherRef teacherRef : act.getTeachers()) {
//					ass.addTeacher(findByName(teachers, teacherRef.getName()));
//				}
//			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//----------------------------- DataProvider's API -----------------------------------------------------------------
	@Override public ObservableValue<String> getName() 					{ return instName.getObservableValue(); }
	@Override public ObservableList<Teacher> getTeachers() 				{ return teachers.getObservableList(); }
	@Override public ObservableList<Subject> getSubjects() 				{ return subjects.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	{ return stdGroups.getObservableList(); }
	@Override public ObservableList<Room> getRooms() 					{ return rooms.getObservableList(); }
	@Override public ObservableList<Day> getDays() 						{ return days.getObservableList(); }
	@Override public ObservableList<Hour> getHours() 					{ return hours.getObservableList(); }

	
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
	public Room addRoom(String name) {
		RoomImpl roomImpl = new RoomImpl(name);
		rooms.addItem(roomImpl);
		return roomImpl;
	}
	
	@Override
	public void setDays(List<String> days) {
		//TODO
	}
	
	@Override
	public void setHours(List<String> hours) {
		//TODO
	}
	//----------------------------- helpers ----------------------------------------------------------------------------
//	private static InputStream openStream(final String path) throws IOException {
//		return XmlDataProvider.class.getResource(path).openStream();
//	}
	
	private static <T extends Entity> T findByName(List<T> items, String name) {
		for (T item : items) {
			if (item.getName().equals(name)) {
				return item;
			}
		}
		return null;
	}

}
