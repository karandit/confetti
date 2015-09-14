package org.confetti.core;

import java.util.List;

public interface DataPersister {

	void updateInstituteNameAndComment(String newName, String newComment);
	
    void addSubjects(List<String> names);
    void addTeachers(List<String> names);
    void addStudentGroups(StudentGroup parent, List<String> names);
    void addRooms(List<String> names);
    Assignment addAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups);
    void addConstraint(String type, ConstraintAttributes attrs);
    //TODO: void addTags(String names);
    void setSolution(Iterable<SolutionSlot> solution);
    
    void removeSubjects(List<Subject> subjects);
    void removeTeachers(List<Teacher> teachers);
    void removeStudentGroups(List<StudentGroup> studentGroups);
    void removeRooms(List<Room> rooms);
    void removeAssignment(Assignment assignment);
    //TODO: void removeConstraint(Constraint constraint);
    //TODO: void removeTag(Tag tag);
    
    void rename(Entity entity, String newName);
    void updateConstraint(Constraint constraint, ConstraintAttributes attrs);
}
