package org.confetti.dataprovider.xml;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.confetti.core.Assignment;
import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Entity;
import org.confetti.core.Hour;
import org.confetti.core.Room;
import org.confetti.core.SolutionSlot;
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
import org.confetti.xml.core.BaseConstraintXml;
import org.confetti.xml.core.GetConstraintAttrVisitor;
import org.confetti.xml.core.GetConstraintTypeVisitor;
import org.confetti.xml.core.GroupXml;
import org.confetti.xml.core.INameBean;
import org.confetti.xml.core.InstituteXml;
import org.confetti.xml.core.RoomXml;
import org.confetti.xml.core.SubgroupXml;
import org.confetti.xml.core.SubjectXml;
import org.confetti.xml.core.TeacherRef;
import org.confetti.xml.core.TeacherXml;
import org.confetti.xml.core.YearXml;
import org.confetti.xml.core.time.TimeConstraint;
import org.confetti.xml.core.time.misc.ConstraintBasicCompulsoryTime;

import com.google.common.collect.Lists;

/**
 * @author Bubla Gabor
 */
public class XmlDataProvider implements DataProvider {
	//----------------------------- constants --------------------------------------------------------------------------
	private static final String FET_CONSTRAINTS_NAMESPACE = "org.confetti.fet.constraints.";
	
	//----------------------------- fields for UI client----------------------------------------------------------------
	private ValueMutator<String> instName = new ValueMutator<>();
	private ListMutator<Teacher> teachers = new ListMutator<>();
	private ListMutator<Subject> subjects = new ListMutator<>();
	private ListMutator<StudentGroup> stdGroups = new ListMutator<>();
	private ListMutator<Room> rooms = new ListMutator<>();
	private ListMutator<Day> days = new ListMutator<>();
	private ListMutator<Hour> hours = new ListMutator<>();
	private ListMutator<Assignment> assignments = new ListMutator<>();
	private ListMutator<Constraint> constraints = new ListMutator<>();
	private ValueMutator<Iterable<SolutionSlot>> solution = new ValueMutator<>();

	//----------------------------- fields for xml persistence ---------------------------------------------------------
    private final InstituteXml instXml;
    private File file;
    private long currentMaxId = 0;

	//----------------------------- constructors -----------------------------------------------------------------------
    public XmlDataProvider(InstituteXml inst, File file) throws FAOException {
        this(inst);
        this.file = file;
    }
    
    public XmlDataProvider(File file) throws FAOException {
		this(new InstituteFAO().importFrom(file));
        this.file = file;
	}
	
	public XmlDataProvider(InstituteXml inst) {
		this.instXml = inst;
		inst.getDays().getDays()	.forEach(day -> days.addItem(new DayImpl(day.getName())));
		inst.getHours().getHours()	.forEach(hour -> hours.addItem(new HourImpl(hour.getName())));
        inst.getSubjects()			.forEach(subj -> subjects.addItem(new SubjectImpl(subj.getName())));
		inst.getTeachers()			.forEach(teacher -> teachers.addItem(new TeacherImpl(teacher.getName())));
		inst.getRooms()				.forEach(room -> rooms.addItem(new RoomImpl(room.getName())));
		createStudentGroups(inst);
		
		Map<String, StudentGroup> studentGroupsByName = collectStudentGroups(stdGroups.getObservableList().getList());
		createAssignments(inst, studentGroupsByName);
		
		GetConstraintAttrVisitor attrVisitor = new GetConstraintAttrVisitor(
				days.getObservableList().getList(),
				hours.getObservableList().getList(),
				teachers.getObservableList().getList(),
				subjects.getObservableList().getList(),
				rooms.getObservableList().getList(),
				studentGroupsByName,
				assignments.getObservableList().getList());
		createConstraints(inst.getTimeConstraints(), attrVisitor);
		createConstraints(inst.getSpaceConstraints(), attrVisitor);
	}

	private void createAssignments(InstituteXml inst, Map<String, StudentGroup> allStdGroups) {
		Iterable<Subject> allSubjects = subjects.getObservableList().getList();
		Iterable<Teacher> allTeachers = teachers.getObservableList().getList();
		for (ActivityXml act : inst.getActivities()) {
		    if (act.getId() > currentMaxId) {
		        currentMaxId = act.getId();
		    }
			AssignmentImpl ass = new AssignmentImpl(act.getId(), findByName(allSubjects, act.getSubject().getName()));
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
			assignments.addItem(ass);
		}
	}

	private void createStudentGroups(InstituteXml inst) {
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
	}

	private void createConstraints(List<? extends BaseConstraintXml> constraintsList, 
			GetConstraintAttrVisitor attrVisitor) {
		for (BaseConstraintXml constraint : constraintsList) {
			String shortType = constraint.accept(GetConstraintTypeVisitor.INSTANCE, null);
			String type = FET_CONSTRAINTS_NAMESPACE + shortType;
			ConstraintAttributes attrs = constraint.accept(attrVisitor, new ConstraintAttributes());
			constraints.addItem(new ConstraintImpl(type, attrs));
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
	@Override public String getInformation()                               { return file.getAbsolutePath(); }
	@Override public ObservableValue<String> getName() 					   { return instName.getObservableValue(); }
	@Override public ObservableList<Subject> getSubjects() 				   { return subjects.getObservableList(); }
	@Override public ObservableList<Teacher> getTeachers() 				   { return teachers.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	   { return stdGroups.getObservableList(); }
	@Override public ObservableList<Room> getRooms() 					   { return rooms.getObservableList(); }
	@Override public ObservableList<Day> getDays() 						   { return days.getObservableList(); }
	@Override public ObservableList<Hour> getHours() 				       { return hours.getObservableList(); }
	@Override public ObservableList<Assignment> getAssignments() 		   { return assignments.getObservableList(); }
	@Override public ObservableList<Constraint> getConstraints() 		   { return constraints.getObservableList(); }
	@Override public ObservableValue<Iterable<SolutionSlot>> getSolution() { return solution.getObservableValue(); }
	
	@Override
	public void addSubjects(List<String> names) {
	    names.forEach(name -> instXml.getSubjects().add(new SubjectXml(name)));
	    save();
	    names.forEach(name -> subjects.addItem(new SubjectImpl(name)));
	}
	
    @Override
	public void addTeachers(List<String> names) {
        names.forEach(name -> instXml.getTeachers().add(new TeacherXml(name)));
        save();
        names.forEach(name -> teachers.addItem(new TeacherImpl(name)));
	}
	
	@Override
	public void addStudentGroups(StudentGroup parent, List<String> names) {
	    if (parent == null) {
	        List<StudentGroupImpl> groups = Lists.transform(names, name -> new StudentGroupImpl(name));
            groups.forEach(group -> instXml.getYears().add(new YearXml(group)));
	        save();
	        groups.forEach(group -> stdGroups.addItem(group));
        } else { //TODO implement if has parent
        }
	}
	
	@Override
	public void addRooms(List<String> names) {
		names.forEach(name -> instXml.getRooms().add(new RoomXml(name)));
        save();
        names.forEach(name -> rooms.addItem(new RoomImpl(name)));
	}
	
	@Override
	public Assignment addAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups) {
	    currentMaxId++;
	    instXml.getActivities().add(new ActivityXml(currentMaxId, subject, teachers, studentGroups));
	    save();
	    
	    AssignmentImpl assignment = new AssignmentImpl(currentMaxId, subject);
	    teachers.forEach(teacher -> assignment.addTeacher(teacher));
	    studentGroups.forEach(studentGroup -> assignment.addStudentGroup(studentGroup));
        assignments.addItem(assignment);
	    return assignment;
	}

	@Override
	public Constraint addConstraint(final String type, ConstraintAttributes attrs) {
		addXmlConstraint(type, attrs); 
		save();
		
		ConstraintImpl constraint = new ConstraintImpl(type, attrs);
		constraints.addItem(constraint);
		return constraint;
	}
	
	private BaseConstraintXml addXmlConstraint(final String type, final ConstraintAttributes attrs) {
		String shortType = type.substring(FET_CONSTRAINTS_NAMESPACE.length());
		
		switch (shortType) {
			case "time.BasicCompulsoryTime": return addTimeXmlConstraint(new ConstraintBasicCompulsoryTime(attrs));
			default: return null;
		}
	}

	private TimeConstraint addTimeXmlConstraint(final TimeConstraint xmlTimeConstraint) {
		instXml.getTimeConstraints().add(xmlTimeConstraint);
		return xmlTimeConstraint;
	}

	@Override
	public void setSolution(Iterable<SolutionSlot> solution) {
	    this.solution.setValue(this, solution);
	}
	
    @Override public void removeSubjects(List<Subject> toRemove) { removeEntities(toRemove, subjects, instXml.getSubjects()); }
    @Override public void removeTeachers(List<Teacher> toRemove) { removeEntities(toRemove, teachers, instXml.getTeachers()); }
    @Override public void removeStudentGroups(List<StudentGroup> toRemove) { removeEntities(toRemove, stdGroups, instXml.getYears()); }
    @Override public void removeRooms(List<Room> toRemove) { removeEntities(toRemove, rooms, instXml.getRooms()); }
	
	@Override
	public void removeAssignment(Assignment assignment) {
	    ActivityXml foundActivity = findActivityById(((AssignmentImpl) assignment).getId());
	    instXml.getActivities().remove(foundActivity);
	    save();
	    
	    assignment.getSubject().removeAssignment(assignment);
        assignment.getTeachers().getList().forEach(teacher -> teacher.removeAssignment(assignment));
        assignment.getStudentGroups().getList().forEach(studentGroup -> studentGroup.removeAssignment(assignment));
        assignments.removeItem(assignment);
	}
	
	@Override
	public void rename(Entity entity, String newName) {
	    entity.accept(new RenameVisitor(instXml), newName);
	    save();
		((EntityImpl) entity).getNameMutator().setValue(entity, newName);
	}
	
	//----------------------------- helpers ----------------------------------------------------------------------------
	public void save() {
        try {
            new InstituteFAO().exportTo(instXml, file);
        } catch (FAOException e) {
            throw new RuntimeException(e);
        }
	 }
    
	private static <T extends Entity> T findByName(Iterable<T> items, String name) {
		for (T item : items) {
			if (item.getName().getValue().equals(name)) {
				return item;
			}
		}
		return null;
	}

	static <T extends INameBean> T findXmlByName(Iterable<T> items, String name) {
        for (T item : items) {
            if (item.getName().equals(name)) {
                return item;
            }
        }
        return null;
    }
	
	private <ET extends Entity, XT extends INameBean> void removeEntities(List<ET> entitiesToRemove, ListMutator<ET> allEntities, List<XT> xmlEntities) {
        for (ET entityToRemove : entitiesToRemove) {
            XT foundXmlEntity = findXmlByName(xmlEntities, entityToRemove.getName().getValue());
            if (foundXmlEntity != null) {
                xmlEntities.remove(foundXmlEntity);
            }
        }
        save();
        
        for (ET entityToRemove : entitiesToRemove) {
            allEntities.removeItem(entityToRemove);
        }
    }
	
	private ActivityXml findActivityById(Long id) {
	    for (ActivityXml activity : instXml.getActivities()) {
	        if (activity.getId().equals(id)) {
	            return activity;
	        }
	    }
	    return null;
	}

}
