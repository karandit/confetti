package org.confetti.dataprovider.xml;

import org.confetti.core.Assignment;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;

/**
 * @author Kárándi Tamás
 */
class AssignmentImpl implements Assignment {

    private final Long id;
	private final Subject subj;
	private final ListMutator<Teacher> teachers = new ListMutator<>();
	private final ListMutator<StudentGroup> stGroups = new ListMutator<>();
	
	public AssignmentImpl(Long id, Subject subj) {
        this.id = id;
        this.subj = subj;
		subj.addAssignment(this);
	}

	public void addTeacher(Teacher teacher) 			{ teachers.addItem(teacher); teacher.addAssignment(this);} 
	public void addStudentGroup(StudentGroup group) 	{ stGroups.addItem(group); group.addAssignment(this);} 
	
	public Long getId() { return id; }
	@Override public Subject getSubject() 								{ return subj; }
	@Override public ObservableList<Teacher> getTeachers() 				{ return teachers.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	{ return stGroups.getObservableList(); }
	@Override public Room getRoom() 									{ return null; }

}
