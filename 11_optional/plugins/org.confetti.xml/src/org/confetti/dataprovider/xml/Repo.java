package org.confetti.dataprovider.xml;

import java.util.HashMap;
import java.util.Map;

import org.confetti.core.Assignment;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Tag;
import org.confetti.core.Teacher;

public class Repo {

	private Map<String, Day> daysByName = new HashMap<>();
	private Map<String, Hour> hoursByName = new HashMap<>();
	private Map<String, StudentGroup> stdGrsByName = new HashMap<>();
	private Map<String, Teacher> teachersByName = new HashMap<>();
	private Map<String, Subject> subjectsByName = new HashMap<>();
	private Map<String, Room> roomsByName = new HashMap<>();
	private Map<Long, Assignment> assignmentsById = new HashMap<>();
	private Map<String, Tag> tagsByName = new HashMap<>();

	//------------------ withXXX methods -------------------------------------------------------------------------------
	public Repo withDays(final Iterable<Day> days) {
		this.daysByName = storeByName(days);
		return this;
	}
	
	public Repo withHours(final Iterable<Hour> hours) {
		this.hoursByName = storeByName(hours);
		return this;
	}

	public Repo withStudentGroups(final Iterable<StudentGroup> studentGroups) {
		this.stdGrsByName = collectStudentGroups(studentGroups);
		return this;
	}

	public Repo withTeachers(final Iterable<Teacher> teachers) {
		this.teachersByName = storeByName(teachers);
		return this;
	}

	public Repo withSubjects(final Iterable<Subject> subjects) {
		this.subjectsByName = storeByName(subjects);
		return this;
	}
	
	public Repo withRooms(final Iterable<Room> rooms) {
		this.roomsByName = storeByName(rooms);
		return this;
	}

	public Repo withAssignments(final Iterable<Assignment> assignments) {
		this.assignmentsById = storeById(assignments);
		return this;
	}

	public Repo withTags(final Iterable<Tag> tags) {
		this.tagsByName = storeByName(tags);
		return this;
	}

	//------------------ findXXX methods -------------------------------------------------------------------------------
	public Day findDay(final String name) 				  	{ return safeGet(name, daysByName, "Day"); }
	public Hour findHour(final String name) 			  	{ return safeGet(name, hoursByName, "Hour"); }
	public Teacher findTeacher(final String name) 	  		{ return safeGet(name, teachersByName, "Teacher"); }
	public StudentGroup findStudentGroup(final String name) { return safeGet(name, stdGrsByName, "Student group"); }
	public Subject findSubject(final String name) 	  		{ return safeGet(name, subjectsByName, "Subject"); }
	public Room findRoom(final String name) 			  	{ return safeGet(name, roomsByName, "Room"); }
	public Assignment findAssignment(final long id) 		{ return safeGet(id, assignmentsById, "Assignment"); }
	public Tag findTag(String name) 						{ return safeGet(name, tagsByName, "Tag"); }

	//------------------ maybeFindXXX methods --------------------------------------------------------------------------
	public Hour maybeFindHour(final String name) 				{ return hoursByName.get(name); }
	public Teacher maybeFindTeacher(final String name) 			{ return teachersByName.get(name); }
	public StudentGroup maybeFindStudentGroup(final String name){ return stdGrsByName.get(name); }
	public Subject maybeFindSubject(final String name) 			{ return subjectsByName.get(name); }
	//------------------ helpers ---------------------------------------------------------------------------------------
	private static <K, V> V safeGet(K key, Map<K, V> store, String errMsg) {
		if (!store.containsKey(key)) {
			throw new RuntimeException(errMsg + " with name '" + key + "' not found.");
		}
		return store.get(key);
	}

	private static <T extends Nameable> Map<String, T> storeByName(final Iterable<T> items) {
		Map<String, T> itemsByName = new HashMap<>();
		for (T item : items) {
			itemsByName.put(item.getName().getValue(), item);
		}
		return itemsByName;
	}

	private static Map<Long, Assignment> storeById(final Iterable<Assignment> assignments) {
		Map<Long, Assignment> itemsByName = new HashMap<>();
		for (Assignment item : assignments) {
			itemsByName.put(((AssignmentImpl) item).getId(), item);
		}
		return itemsByName;
	}

	private Map<String, StudentGroup> collectStudentGroups(Iterable<StudentGroup> list) {
		Map<String, StudentGroup> res = new HashMap<>();
		for (StudentGroup sg : list) {
			res.put(sg.getName().getValue(), sg);
			res.putAll(collectStudentGroups(sg.getChildren().getList()));
		}
		return res;
	}

}
