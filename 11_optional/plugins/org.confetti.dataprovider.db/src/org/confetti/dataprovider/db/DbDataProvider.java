package org.confetti.dataprovider.db;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Entity;
import org.confetti.core.Hour;
import org.confetti.core.Room;
import org.confetti.core.SolutionSlot;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.dataprovider.db.dto.AssignmentDTO;
import org.confetti.dataprovider.db.dto.DayDTO;
import org.confetti.dataprovider.db.dto.HourDTO;
import org.confetti.dataprovider.db.dto.RoomDTO;
import org.confetti.dataprovider.db.dto.StudentGroupDTO;
import org.confetti.dataprovider.db.dto.SubjectDTO;
import org.confetti.dataprovider.db.dto.TeacherDTO;
import org.confetti.dataprovider.db.entities.AssignmentDb;
import org.confetti.dataprovider.db.entities.DayDb;
import org.confetti.dataprovider.db.entities.HourDb;
import org.confetti.dataprovider.db.entities.InstituteDb;
import org.confetti.dataprovider.db.entities.RoomDb;
import org.confetti.dataprovider.db.entities.StudentGroupDb;
import org.confetti.dataprovider.db.entities.SubjectDb;
import org.confetti.dataprovider.db.entities.TeacherDb;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.hibernate.SessionFactory;

/**
 * @author Gabor Bubla
 */
public class DbDataProvider implements DataProvider {

	//----------------------------- fields -----------------------------------------------------------------------------
	private ValueMutator<String> instName = new ValueMutator<>();
	private ListMutator<Teacher> teachers = new ListMutator<>();
	private ListMutator<Subject> subjects = new ListMutator<>();
	private ListMutator<StudentGroup> stdGroups = new ListMutator<>();
	private ListMutator<Room> rooms = new ListMutator<>();
	private ListMutator<Day> days = new ListMutator<>();
	private ListMutator<Hour> hours = new ListMutator<>();
	private ListMutator<Assignment> assignments = new ListMutator<>();
	private ValueMutator<Iterable<SolutionSlot>> solution = new ValueMutator<>();
	
	private final SessionFactory sFact;

	public DbDataProvider(SessionFactory sFact) {
		this.sFact = sFact;
	}
	
	public DbDataProvider(SessionFactory sessFact, InstituteDb instDb) {
	    this(sessFact);

	    for (DayDb day : instDb.getDays()) {
	        days.addItem(new DayDTO(day.getName()));
	    }
	    for (HourDb hour : instDb.getHours()) {
	        hours.addItem(new HourDTO(hour.getName()));
	    }
        for (SubjectDb subj : instDb.getSubjects()) {
            subjects.addItem(new SubjectDTO(subj.getName()));
        }
        for (TeacherDb teacher : instDb.getTeachers()) {
            teachers.addItem(new TeacherDTO(teacher.getName()));
        }
        for (StudentGroupDb sG : instDb.getStudentGroups()) {
            StudentGroupDTO studentGroupDTO = new StudentGroupDTO(sG.getName());
            stdGroups.addItem(studentGroupDTO);
            //TODO read as the children of the StudentGroups
        }
        for (RoomDb room : instDb.getRooms()) {
            rooms.addItem(new RoomDTO(room.getName()));
        }

        Iterable<Subject> allSubjects = subjects.getObservableList().getList();
        Iterable<Teacher> allTeachers = teachers.getObservableList().getList();
        Map<String, StudentGroup> allStdGroups = collectStudentGroups(stdGroups.getObservableList().getList());
        for (AssignmentDb assDb : instDb.getAssignments()) {
            AssignmentDTO ass = new AssignmentDTO(findByName(allSubjects, assDb.getSubjects().iterator().next().getName()));
            if (assDb.getStudentGroups() != null) {
                for (StudentGroupDb stGroupDb : assDb.getStudentGroups()) {
                    ass.addStudentGroup(allStdGroups.get(stGroupDb.getName()));
                }
            }
            if (assDb.getTeachers() != null) {
                for (TeacherDb teacherDb : assDb.getTeachers()) {
                    ass.addTeacher(findByName(allTeachers, teacherDb.getName()));
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
	
	private static <T extends Entity> T findByName(Iterable<T> items, String name) {
        for (T item : items) {
            if (item.getName().getValue().equals(name)) {
                return item;
            }
        }
        return null;
    }
	
	//----------------------------- DataProvider's API -----------------------------------------------------------------
	@Override public ObservableValue<String> getName() 					   { return instName.getObservableValue(); }
	@Override public ObservableList<Teacher> getTeachers() 				   { return teachers.getObservableList(); }
	@Override public ObservableList<Subject> getSubjects() 				   { return subjects.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	   { return stdGroups.getObservableList(); }
	@Override public ObservableList<Room> getRooms() 					   { return rooms.getObservableList(); }
	@Override public ObservableList<Day> getDays() 						   { return days.getObservableList(); }
	@Override public ObservableList<Hour> getHours() 					   { return hours.getObservableList(); }
	@Override public ObservableList<Assignment> getAssignments() 		   { return assignments.getObservableList(); }
	@Override public ObservableValue<Iterable<SolutionSlot>> getSolution() { return solution.getObservableValue(); }
	
	@Override public void addSubjects(List<String> names) {  }
	@Override public void addTeachers(List<String> names) {  }
	@Override public void addStudentGroups(StudentGroup parent, List<String> names) {  }
	@Override public void addRooms(List<String> names) {  }
	@Override public void setDays(List<String> days) { }
	@Override public void setHours(List<String> hours) { }
	@Override public Assignment addAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups) { return null; }
	@Override public void setSolution(Iterable<SolutionSlot> solution) { }
	@Override public void removeSubjects(List<Subject> subjects) { }
	@Override public void removeTeachers(List<Teacher> teachers) { }
	@Override public void removeStudentGroups(List<StudentGroup> studentGroups) { }
	@Override public void removeRooms(List<Room> rooms) { }
	@Override public void removeAssignment(Assignment assignment) { }
	@Override public void rename(Entity entity, String newName) { }

}
