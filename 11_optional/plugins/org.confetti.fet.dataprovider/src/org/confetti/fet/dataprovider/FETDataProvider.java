package org.confetti.fet.dataprovider;

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
import org.confetti.core.DataPersister;
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
import org.confetti.fet.xml.core.AbstractInstituteXml;
import org.confetti.fet.xml.core.AbstractInstituteXmlBuilder;
import org.confetti.fet.xml.core.ActivityXml;
import org.confetti.fet.xml.core.BaseConstraintXml;
import org.confetti.fet.xml.core.ConstraintSetter;
import org.confetti.fet.xml.core.GroupXml;
import org.confetti.fet.xml.core.INameBean;
import org.confetti.fet.xml.core.InstituteXmlRelease;
import org.confetti.fet.xml.core.NameGetter;
import org.confetti.fet.xml.core.RoomXml;
import org.confetti.fet.xml.core.SubgroupXml;
import org.confetti.fet.xml.core.SubjectXml;
import org.confetti.fet.xml.core.TeacherXml;
import org.confetti.fet.xml.core.YearXml;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.extensions.ConstraintRegistry;
import org.confetti.xml.FAOException;
import org.confetti.xml.GenericFAO;

import com.google.common.collect.Lists;

/**
 * @author Bubla Gabor
 * @author Kárándi Tamás
 */
public class FETDataProvider implements DataProvider, DataPersister {
	
	private static final Function<Assignment, Long> GET_ASSG_ID_FUNC = assg -> ((FETAssignment) assg).getId();
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
		inst.getDayNames()			.forEach(dayName -> days.addItem(new FETDay(dayName)));
		inst.getHourNames()			.forEach(hourName -> hours.addItem(new FETHour(hourName)));
        inst.getSubjects()			.forEach(subj -> subjects.addItem(new FETSubject(subj.getName(), this.getNextColorId())));
		inst.getTeachers()			.forEach(teacher -> teachers.addItem(new FETTeacher(teacher.getName())));
		inst.getBuildings()			.forEach(building -> buildings.addItem(new FETBuilding(building.getName())));
		Repo repo_ = new Repo()
					.withBuildings(buildings.getObservableList().getList());
		
		inst.getRooms().forEach(room -> rooms.addItem(new FETRoom(room.getName(), room.getCapacity(),
				ofNullable(repo_.maybeFindBuilding(room.getBuilding())))));
		inst.getYears()				.forEach(year -> stdGroups.addItem(createStudentGroup(year)));
		inst.getActivityTags()		.forEach(actTag -> tags.addItem(new FETTag(actTag.getName())));
		
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
	
	private FETAssignment createAssignment(ActivityXml act, Repo repo) {
	    if (act.getId() > currentMaxId) {
	        currentMaxId = act.getId();
	    }
	    
	    Optional<AssignmentGroup> group = (act.getActivityGroupId() == 0)
	    		? empty() 
	    		: of(repo.findAssignmentGroup(act.getActivityGroupId()));
	    
		FETAssignment ass = new FETAssignment(act.getId(), act.getDuration(), act.getNrOfStudents(),
				repo.findSubject(act.getSubject().getName()), group);
		if (act.getStudents() != null) {
			act.getStudents().forEach(stGroupName -> ass.addStudentGroup(repo.findStudentGroup(stGroupName)));
		}
		if (act.getTeachers() != null) {
			act.getTeachers().forEach(teacherRef -> ass.addTeacher(repo.findTeacher(teacherRef.getName())));
		}
		if (act.getActivityTag() != null) {
			act.getActivityTag().forEach(tagName -> ass.addTag((FETTag) repo.findTag(tagName)));
		}
		return ass;
	}

	private FETStudentGroup createStudentGroup(YearXml year) {
		FETStudentGroup studGr1 = new FETStudentGroup(year.getName(), year.getNrOfStudents());
		for (GroupXml group : year.getGroups()) {
			FETStudentGroup studGr2 = new FETStudentGroup(group.getName(), group.getNrOfStudents(), studGr1);
			studGr1.addChild(studGr2);
			for (SubgroupXml subgroup : group.getSubgroups()) {
				FETStudentGroup studGr3 = new FETStudentGroup(subgroup.getName(), subgroup.getNrOfStudents(), studGr2);
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

	    names.forEach(name -> subjects.addItem(new FETSubject(name, this.getNextColorId())));
	}
	
    @Override
	public void addTeachers(List<String> names) {
    	AbstractInstituteXml xml = defaultXmlBuilder().build(this);
        names.forEach(name -> xml.getTeachers().add(new TeacherXml(name)));
        marshall(xml);

        names.forEach(name -> teachers.addItem(new FETTeacher(name)));
	}
	
	@Override
	public void addStudentGroups(StudentGroup parent, List<String> names) {
		AbstractInstituteXml xml = defaultXmlBuilder().build(this);
		if (parent == null) {
	        List<FETStudentGroup> groups = Lists.transform(names, name -> new FETStudentGroup(name, 0));
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
        
        names.forEach(name -> rooms.addItem(new FETRoom(name, 0, Optional.empty())));
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
	    
	    FETAssignment assignment = new FETAssignment(currentMaxId, duration, 0, subject, Optional.empty());
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
		
		Constraint constr = new FETConstraint(type, attrs);
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
		Long assgId = ((FETAssignment) assignment).getId();
		Optional<ActivityXml> foundActivity = xml.getActivities().stream()
	    		.filter(act -> act.getId().equals(assgId))
	    		.findFirst();
		foundActivity.ifPresent(xml.getActivities()::remove);
	    marshall(xml);
	    
	    ((FETSubject) assignment.getSubject()).removeAssignment(assignment);
        assignment.getTeachers().getList().forEach(teacher -> ((FETTeacher) teacher).removeAssignment(assignment));
        assignment.getStudentGroups().getList().forEach(sg -> ((FETStudentGroup) sg).removeAssignment(assignment));
        assignments.removeItem(assignment);
	}
	
	@Override
	public void rename(Entity entity, String newName) {
		NameGetter nameGetter = entity.accept(RenameVisitor.INSTANCE, newName);
		AbstractInstituteXml xml = release.newXmlBuilder(nameGetter, GET_ASSG_ID_FUNC).build(this);
	    marshall(xml);

	    ((FETEntity) entity).getNameMutator().setValue(entity, newName);
	}

	@Override
	public void updateConstraint(Constraint constraint, ConstraintAttributes attrs) {
		AbstractInstituteXml xml = defaultXmlBuilder().updateConstraint(constraint, attrs).build(this);
		marshall(xml);
		
		FETConstraint constraintImpl = (FETConstraint) constraint;
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
