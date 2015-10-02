package org.confetti.dataprovider.xml;

import java.util.Optional;

import org.confetti.core.Assignment;
import org.confetti.core.AssignmentGroup;
import org.confetti.core.Constraint;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Tag;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;

/**
 * @author Kárándi Tamás
 */
public class AssignmentImpl implements Assignment {

    private final Long id;
    private final ValueMutator<Integer> duration;
    private final ValueMutator<Integer> nrOfStudents;
	private final Subject subj;
	private final ListMutator<Teacher> teachers = new ListMutator<>();
	private final ListMutator<StudentGroup> stGroups = new ListMutator<>();
	private final ListMutator<Constraint> constraints = new ListMutator<>();
	private final ListMutator<Tag> tags = new ListMutator<>();
    private final ValueMutator<Optional<AssignmentGroup>> group;

	public AssignmentImpl(Long id, int duration, int nrOfStudents, Subject subj, Optional<AssignmentGroup> group) {
        this.id = id;
        this.duration = new ValueMutator<>(this, duration);
        this.nrOfStudents = new ValueMutator<>(this, nrOfStudents);
        this.subj = subj;
        subj.addAssignment(this);
        this.group = new ValueMutator<>(this, group);
		group.ifPresent(x -> x.addAssignment(this));
	}

	public void addTeacher(Teacher teacher) 			{ teachers.addItem(teacher); teacher.addAssignment(this);} 
	public void addStudentGroup(StudentGroup group) 	{ stGroups.addItem(group); group.addAssignment(this);} 
	public void addConstraint(Constraint cnstr) 		{ constraints.addItem(cnstr);} 
	public void addTag(FETTag tag) 					{ tags.addItem(tag); tag.addAssignment(this); } 
	
	public Long getId() { return id; }
	@Override public ObservableValue<Integer> getDuration() 			{ return duration.getObservableValue(); }
	@Override public ObservableValue<Integer> getNrOfStudents() 		{ return nrOfStudents.getObservableValue(); }
	@Override public Subject getSubject() 								{ return subj; }
	@Override public ObservableList<Teacher> getTeachers() 				{ return teachers.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	{ return stGroups.getObservableList(); }
	@Override public ObservableList<Constraint> getConstraints() 		{ return constraints.getObservableList(); }
	@Override public ObservableList<Tag> getTags() 						{ return tags.getObservableList(); }
	@Override public ObservableValue<Optional<AssignmentGroup>> getGroup() { return group.getObservableValue(); }

}
