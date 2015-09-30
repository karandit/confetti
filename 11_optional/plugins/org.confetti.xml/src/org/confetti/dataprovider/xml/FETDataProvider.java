package org.confetti.dataprovider.xml;

import static java.util.Arrays.asList;
import static java.util.Optional.empty;
import static java.util.Optional.of;
import static java.util.Optional.ofNullable;
import static java.util.stream.StreamSupport.stream;

import java.io.File;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.confetti.core.Assignment;
import org.confetti.core.AssignmentGroup;
import org.confetti.core.Building;
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
import org.confetti.core.Tag;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.extensions.ConstraintRegistry;
import org.confetti.xml.FAOException;
import org.confetti.xml.GenericFAO;
import org.confetti.xml.core.AbstractInstituteXml;
import org.confetti.xml.core.AbstractInstituteXmlBuilder;
import org.confetti.xml.core.ActivityXml;
import org.confetti.xml.core.BaseConstraintXml;
import org.confetti.xml.core.ConstraintFactory;
import org.confetti.xml.core.ConstraintSetter;
import org.confetti.xml.core.GroupXml;
import org.confetti.xml.core.INameBean;
import org.confetti.xml.core.InstituteXmlRelease;
import org.confetti.xml.core.NameGetter;
import org.confetti.xml.core.RoomXml;
import org.confetti.xml.core.SubgroupXml;
import org.confetti.xml.core.SubjectXml;
import org.confetti.xml.core.TeacherXml;
import org.confetti.xml.core.YearXml;

import com.google.common.collect.Lists;

/**
 * @author Bubla Gabor
 * @author Kárándi Tamás
 */
public class FETDataProvider implements DataProvider {
	
	private static final Function<Assignment, Long> GET_ASSG_ID_FUNC = assg -> ((AssignmentImpl) assg).getId();
	private static final ConstraintSetter CONSTRAINT_SETTER = new ConstraintSetter(new NameGetter(), GET_ASSG_ID_FUNC);
	
	//----------------------------- fields for UI client----------------------------------------------------------------
	private final ValueMutator<String> instName = new ValueMutator<>();
	private final ValueMutator<String> instComment = new ValueMutator<>();
	private final ListMutator<Teacher> teachers = new ListMutator<>();
	private final ListMutator<Subject> subjects = new ListMutator<>();
	private final ListMutator<StudentGroup> stdGroups = new ListMutator<>();
	private final ListMutator<Room> rooms = new ListMutator<>();
	private final ListMutator<Building> buildings = new ListMutator<>();
	private final ListMutator<Day> days = new ListMutator<>();
	private final ListMutator<Hour> hours = new ListMutator<>();
	private final ListMutator<Assignment> assignments = new ListMutator<>();
	private final ListMutator<Constraint> constraints = new ListMutator<>();
	private final ListMutator<Tag> tags = new ListMutator<>();
	private final ValueMutator<Iterable<SolutionSlot>> solution = new ValueMutator<>();

	//----------------------------- fields for xml persistence ---------------------------------------------------------
    private File file;
    private final String version;
    private long currentMaxId = 0;
    private int colorCounter = 0;
	private InstituteXmlRelease<?> release;
    
	//----------------------------- constructors -----------------------------------------------------------------------
	public <T extends AbstractInstituteXml> FETDataProvider(T inst, InstituteXmlRelease<T> release, File file) {
		this.release = release;
		this.version = inst.getVersion();
        this.file = file;
		instName.setValue(this, inst.getName());
		instComment.setValue(this, inst.getComment());
		inst.getDayNames()			.forEach(dayName -> days.addItem(new DayImpl(dayName)));
		inst.getHourNames()			.forEach(hourName -> hours.addItem(new HourImpl(hourName)));
        inst.getSubjects()			.forEach(subj -> subjects.addItem(new SubjectImpl(subj.getName(), this.getNextColorId())));
		inst.getTeachers()			.forEach(teacher -> teachers.addItem(new TeacherImpl(teacher.getName())));
		inst.getBuildings()			.forEach(building -> buildings.addItem(new BuildingImpl(building.getName())));
		Repo repo_ = new Repo()
					.withBuildings(buildings.getObservableList().getList());
		
		inst.getRooms().forEach(room -> rooms.addItem(new RoomImpl(room.getName(), room.getCapacity(),
				ofNullable(repo_.maybeFindBuilding(room.getBuilding())))));
		inst.getYears()				.forEach(year -> stdGroups.addItem(createStudentGroup(year)));
		inst.getActivityTags()		.forEach(actTag -> tags.addItem(new TagImpl(actTag.getName())));
		
		Repo repo = repo_
			.withSubjects(subjects.getObservableList().getList())
			.withTeachers(teachers.getObservableList().getList())
			.withStudentGroups(stdGroups.getObservableList().getList())
			.withTags(tags.getObservableList().getList());
		inst.getActivities()		.forEach(act -> assignments.addItem(createAssignment(act, repo)));
		
		ConstraintFactory factory = new ConstraintFactory(repo
				.withDays(days.getObservableList().getList())
				.withHours(hours.getObservableList().getList())
				.withRooms(rooms.getObservableList().getList())
				.withAssignments(assignments.getObservableList().getList())
		);
		inst.getTimeConstraints()	.forEach(x -> constraints.addItem(x.accept(factory, null).build()));
		inst.getSpaceConstraints()	.forEach(x -> constraints.addItem(x.accept(factory, null).build()));
	}
	
	private AssignmentImpl createAssignment(ActivityXml act, Repo repo) {
	    if (act.getId() > currentMaxId) {
	        currentMaxId = act.getId();
	    }
	    
	    Optional<AssignmentGroup> group = (act.getActivityGroupId() == 0)
	    		? empty() 
	    		: of(repo.findAssignmentGroup(act.getActivityGroupId()));
	    
		AssignmentImpl ass = new AssignmentImpl(act.getId(), act.getDuration(), act.getNrOfStudents(),
				repo.findSubject(act.getSubject().getName()), group);
		if (act.getStudents() != null) {
			act.getStudents().forEach(stGroupName -> ass.addStudentGroup(repo.findStudentGroup(stGroupName)));
		}
		if (act.getTeachers() != null) {
			act.getTeachers().forEach(teacherRef -> ass.addTeacher(repo.findTeacher(teacherRef.getName())));
		}
		if (act.getActivityTag() != null) {
			act.getActivityTag().forEach(tagName -> ass.addTag((TagImpl) repo.findTag(tagName)));
		}
		return ass;
	}

	private StudentGroupImpl createStudentGroup(YearXml year) {
		StudentGroupImpl studGr1 = new StudentGroupImpl(year.getName(), year.getNrOfStudents());
		for (GroupXml group : year.getGroups()) {
			StudentGroupImpl studGr2 = new StudentGroupImpl(group.getName(), group.getNrOfStudents(), studGr1);
			studGr1.addChild(studGr2);
			for (SubgroupXml subgroup : group.getSubgroups()) {
				StudentGroupImpl studGr3 = new StudentGroupImpl(subgroup.getName(), subgroup.getNrOfStudents(), studGr2);
				studGr2.addChild(studGr3);
			}
		}
		return studGr1;
	}
	
	private int getNextColorId() {
		return colorCounter++;
	}
	//----------------------------- DataProvider's API -----------------------------------------------------------------
	@Override public String getInformation()                               { return file.getAbsolutePath(); }
	@Override public ObservableValue<String> getName() 					   { return instName.getObservableValue(); }
	@Override public ObservableValue<String> getComment() 				   { return instComment.getObservableValue(); }
	@Override public ObservableList<Subject> getSubjects() 				   { return subjects.getObservableList(); }
	@Override public ObservableList<Teacher> getTeachers() 				   { return teachers.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	   { return stdGroups.getObservableList(); }
	@Override public ObservableList<Room> getRooms() 					   { return rooms.getObservableList(); }
	@Override public ObservableList<Building> getBuildings() 			   { return buildings.getObservableList(); }
	@Override public ObservableList<Day> getDays() 						   { return days.getObservableList(); }
	@Override public ObservableList<Hour> getHours() 				       { return hours.getObservableList(); }
	@Override public ObservableList<Assignment> getAssignments() 		   { return assignments.getObservableList(); }
	@Override public ObservableList<Constraint> getConstraints() 		   { return constraints.getObservableList(); }
	@Override public ObservableList<Tag> getTags() 						   { return tags.getObservableList(); }
	@Override public ObservableValue<Iterable<SolutionSlot>> getSolution() { return solution.getObservableValue(); }
	
	@Override
	public void updateInstituteNameAndComment(String newName, String newComment) {
	    AbstractInstituteXml xml = defaultXmlBuilder().build(this);
		xml.setName(newName);
		xml.setComment(newComment);
		marshall(xml);
		
		instName.setValue(this, newName);
		instComment.setValue(this, newComment);
	}

	@Override
	public void addSubjects(List<String> names) {
		AbstractInstituteXml xml = defaultXmlBuilder().build(this);
	    names.forEach(name -> xml.getSubjects().add(new SubjectXml(name)));
	    marshall(xml);

	    names.forEach(name -> subjects.addItem(new SubjectImpl(name, this.getNextColorId())));
	}
	
    @Override
	public void addTeachers(List<String> names) {
    	AbstractInstituteXml xml = defaultXmlBuilder().build(this);
        names.forEach(name -> xml.getTeachers().add(new TeacherXml(name)));
        marshall(xml);

        names.forEach(name -> teachers.addItem(new TeacherImpl(name)));
	}
	
	@Override
	public void addStudentGroups(StudentGroup parent, List<String> names) {
		AbstractInstituteXml xml = defaultXmlBuilder().build(this);
		if (parent == null) {
	        List<StudentGroupImpl> groups = Lists.transform(names, name -> new StudentGroupImpl(name, 0));
            groups.forEach(group -> xml.getYears().add(new YearXml(group.getName().getValue(), group)));
            marshall(xml);
	    
            groups.forEach(group -> stdGroups.addItem(group));
        } else { //TODO implement if has parent
        }
	}
	
	@Override
	public void addRooms(List<String> names) {
		AbstractInstituteXml xml = defaultXmlBuilder().build(this);
		names.forEach(name -> xml.getRooms().add(new RoomXml(name, null, 0)));
        marshall(xml);
        
        names.forEach(name -> rooms.addItem(new RoomImpl(name, 0, Optional.empty())));
	}
	
	@Override
	public Assignment addAssignment(Subject subject, Iterable<Teacher> teachers, Iterable<StudentGroup> studentGroups) {
		AbstractInstituteXml xml = defaultXmlBuilder().build(this);
		currentMaxId++;
	    int duration = 1;
	    ActivityXml activityXml = new ActivityXml(currentMaxId, duration, 0L, duration, 0
	    										, subject.getName().getValue()
	    										, stream(teachers.spliterator(), false)
	    											.map(t -> t.getName().getValue())
	    											.collect(Collectors.toList())
	    										, stream(studentGroups.spliterator(), false)
	    											.map(sg -> sg.getName().getValue())
	    											.collect(Collectors.toList())
	    										, asList());
		xml.getActivities().add(activityXml);
	    marshall(xml);
	    
	    AssignmentImpl assignment = new AssignmentImpl(currentMaxId, duration, 0, subject, Optional.empty());
	    teachers.forEach(assignment::addTeacher);
	    studentGroups.forEach(assignment::addStudentGroup);
        assignments.addItem(assignment);
	    return assignment;
	}

	@Override
	public void addConstraint(final String type, ConstraintAttributes attrs) {
		AbstractInstituteXml xml = defaultXmlBuilder().build(this);
		BaseConstraintXml.newXmlConstraint(xml, type, attrs, CONSTRAINT_SETTER);
		marshall(xml);
		
		Constraint constr = new ConstraintImpl(type, attrs);
		constraints.addItem(constr);
		ConstraintDescr constraintDescr = ConstraintRegistry.INSTANCE.getConstraintDescrById(type);
		FieldTypeAddToVisitor visitor = new FieldTypeAddToVisitor(attrs);
		constraintDescr.getFields().forEach(field -> field.getType().accept(visitor, field.getName(), constr));
	}

	@Override
	public void setSolution(Iterable<SolutionSlot> solution) {
	    this.solution.setValue(this, solution);
	}
	
	@Override
	public void removeSubjects(List<Subject> toRemove) {
		removeEntities(toRemove, subjects, xml -> xml.getSubjects());
	}

	@Override
	public void removeTeachers(List<Teacher> toRemove) {
		removeEntities(toRemove, teachers, xml -> xml.getTeachers());
	}

	@Override
	public void removeStudentGroups(List<StudentGroup> toRemove) {
		removeEntities(toRemove, stdGroups, xml -> xml.getYears());
	}

	@Override
	public void removeRooms(List<Room> toRemove) {
		removeEntities(toRemove, rooms, xml -> xml.getRooms());
	}
	
	@Override
	public void removeAssignment(Assignment assignment) {
	    AbstractInstituteXml xml = defaultXmlBuilder().build(this);
		Long assgId = ((AssignmentImpl) assignment).getId();
		Optional<ActivityXml> foundActivity = xml.getActivities().stream()
	    		.filter(act -> act.getId().equals(assgId))
	    		.findFirst();
		foundActivity.ifPresent(xml.getActivities()::remove);
	    marshall(xml);
	    
	    assignment.getSubject().removeAssignment(assignment);
        assignment.getTeachers().getList().forEach(teacher -> teacher.removeAssignment(assignment));
        assignment.getStudentGroups().getList().forEach(studentGroup -> studentGroup.removeAssignment(assignment));
        assignments.removeItem(assignment);
	}
	
	@Override
	public void rename(Entity entity, String newName) {
		NameGetter nameGetter = entity.accept(RenameVisitor.INSTANCE, newName);
		AbstractInstituteXml xml = release.newXmlBuilder(nameGetter, GET_ASSG_ID_FUNC).build(this);
	    marshall(xml);

	    ((EntityImpl) entity).getNameMutator().setValue(entity, newName);
	}

	@Override
	public void updateConstraint(Constraint constraint, ConstraintAttributes attrs) {
		AbstractInstituteXml xml = defaultXmlBuilder().updateConstraint(constraint, attrs).build(this);
		marshall(xml);
		
		ConstraintImpl constraintImpl = (ConstraintImpl) constraint;
		constraintImpl.getAttrsMutator().setValue(constraint, attrs);
		//TODO: not symmetric with addConstraint, maybe not necessary, check it
//		ConstraintDescr constraintDescr = ConstraintRegistry.INSTANCE.getConstraintDescrById(type);
//		FieldTypeAddToVisitor visitor = new FieldTypeAddToVisitor(attrs);
//		constraintDescr.getFields().forEach(field -> field.getType().accept(visitor, field.getName(), constr));
	}
	
	public String getVersion() {
		return this.version;
	}

	//----------------------------- helpers ----------------------------------------------------------------------------
	public void save() {
		AbstractInstituteXml xml = defaultXmlBuilder().build(this);
        marshall(xml);
	}

	private void marshall(AbstractInstituteXml xml) {
        try {
			@SuppressWarnings("unchecked")
			GenericFAO<AbstractInstituteXml> fao = (GenericFAO<AbstractInstituteXml>) release.newFAO();
            fao.exportTo(xml, file);
        } catch (FAOException e) {
            throw new RuntimeException(e);
        }
	 }
    
	private <ET extends Entity, XT extends INameBean> void removeEntities(List<ET> entitiesToRemove, 
			ListMutator<ET> allEntities, Function<AbstractInstituteXml, List<XT>> xmlEntitiesSupplier) {
        
		AbstractInstituteXml xml = defaultXmlBuilder().build(this);
		List<XT> xmlEntities = xmlEntitiesSupplier.apply(xml);

		for (ET ent : entitiesToRemove) {
			String entName = ent.getName().getValue();
            Optional<XT> foundXmlEntity = xmlEntities.stream()
            		.filter(xmlEnt -> xmlEnt.getName().equals(entName))
            		.findFirst();
            foundXmlEntity.ifPresent(xmlEntities::remove);
        }
        marshall(xml);
        
        entitiesToRemove.forEach(allEntities::removeItem);
    }

	private AbstractInstituteXmlBuilder<?> defaultXmlBuilder() {
		return release.newXmlBuilder(new NameGetter(), GET_ASSG_ID_FUNC);
	}
	
}
