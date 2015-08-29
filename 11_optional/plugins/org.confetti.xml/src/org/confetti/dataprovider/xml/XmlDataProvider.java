package org.confetti.dataprovider.xml;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

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
import org.confetti.rcp.extensions.ConstraintDescr;
import org.confetti.rcp.extensions.ConstraintRegistry;
import org.confetti.xml.FAOException;
import org.confetti.xml.InstituteFAO;
import org.confetti.xml.core.ActivityXml;
import org.confetti.xml.core.BaseConstraintXml;
import org.confetti.xml.core.ConstraintFactory;
import org.confetti.xml.core.ConstraintSetter;
import org.confetti.xml.core.GroupXml;
import org.confetti.xml.core.INameBean;
import org.confetti.xml.core.InstituteXml;
import org.confetti.xml.core.RoomXml;
import org.confetti.xml.core.SubgroupXml;
import org.confetti.xml.core.SubjectXml;
import org.confetti.xml.core.TeacherXml;
import org.confetti.xml.core.YearXml;
import org.confetti.xml.core.space.SpaceConstraint;
import org.confetti.xml.core.space.activities.ConstraintActivitiesOccupyMaxDifferentRooms;
import org.confetti.xml.core.space.activities.ConstraintActivitiesSameRoomIfConsecutive;
import org.confetti.xml.core.space.activities.ConstraintActivityPreferredRoom;
import org.confetti.xml.core.space.activities.ConstraintActivityPreferredRooms;
import org.confetti.xml.core.space.activitytags.ConstraintActivityTagPreferredRoom;
import org.confetti.xml.core.space.activitytags.ConstraintActivityTagPreferredRooms;
import org.confetti.xml.core.space.misc.ConstraintBasicCompulsorySpace;
import org.confetti.xml.core.space.rooms.ConstraintRoomNotAvailableTimes;
import org.confetti.xml.core.space.students.ConstraintStudentsMaxBuildingChangesPerDay;
import org.confetti.xml.core.space.students.ConstraintStudentsMaxBuildingChangesPerWeek;
import org.confetti.xml.core.space.students.ConstraintStudentsMinGapsBetweenBuildingChanges;
import org.confetti.xml.core.space.students.ConstraintStudentsSetHomeRoom;
import org.confetti.xml.core.space.students.ConstraintStudentsSetHomeRooms;
import org.confetti.xml.core.space.students.ConstraintStudentsSetMaxBuildingChangesPerDay;
import org.confetti.xml.core.space.students.ConstraintStudentsSetMaxBuildingChangesPerWeek;
import org.confetti.xml.core.space.students.ConstraintStudentsSetMinGapsBetweenBuildingChanges;
import org.confetti.xml.core.space.subjects.ConstraintSubjectPreferredRoom;
import org.confetti.xml.core.space.subjects.ConstraintSubjectPreferredRooms;
import org.confetti.xml.core.space.subjects_activitytags.ConstraintSubjectActivityTagPreferredRoom;
import org.confetti.xml.core.space.subjects_activitytags.ConstraintSubjectActivityTagPreferredRooms;
import org.confetti.xml.core.space.teachers.ConstraintTeacherHomeRoom;
import org.confetti.xml.core.space.teachers.ConstraintTeacherHomeRooms;
import org.confetti.xml.core.space.teachers.ConstraintTeacherMaxBuildingChangesPerDay;
import org.confetti.xml.core.space.teachers.ConstraintTeacherMaxBuildingChangesPerWeek;
import org.confetti.xml.core.space.teachers.ConstraintTeacherMinGapsBetweenBuildingChanges;
import org.confetti.xml.core.space.teachers.ConstraintTeachersMaxBuildingChangesPerDay;
import org.confetti.xml.core.space.teachers.ConstraintTeachersMaxBuildingChangesPerWeek;
import org.confetti.xml.core.space.teachers.ConstraintTeachersMinGapsBetweenBuildingChanges;
import org.confetti.xml.core.time.TimeConstraint;
import org.confetti.xml.core.time.activities.ConstraintActivitiesEndStudentsDay;
import org.confetti.xml.core.time.activities.ConstraintActivitiesMaxSimultaneousInSelectedTimeSlots;
import org.confetti.xml.core.time.activities.ConstraintActivitiesNotOverlapping;
import org.confetti.xml.core.time.activities.ConstraintActivitiesOccupyMaxTimeSlotsFromSelection;
import org.confetti.xml.core.time.activities.ConstraintActivitiesPreferredStartingTimes;
import org.confetti.xml.core.time.activities.ConstraintActivitiesPreferredTimeSlots;
import org.confetti.xml.core.time.activities.ConstraintActivitiesSameStartingDay;
import org.confetti.xml.core.time.activities.ConstraintActivitiesSameStartingHour;
import org.confetti.xml.core.time.activities.ConstraintActivitiesSameStartingTime;
import org.confetti.xml.core.time.activities.ConstraintActivityEndsStudentsDay;
import org.confetti.xml.core.time.activities.ConstraintActivityPreferredStartingTime;
import org.confetti.xml.core.time.activities.ConstraintActivityPreferredStartingTimes;
import org.confetti.xml.core.time.activities.ConstraintActivityPreferredTimeSlots;
import org.confetti.xml.core.time.activities.ConstraintMaxDaysBetweenActivities;
import org.confetti.xml.core.time.activities.ConstraintMinDaysBetweenActivities;
import org.confetti.xml.core.time.activities.ConstraintMinGapsBetweenActivities;
import org.confetti.xml.core.time.activities.ConstraintSubactivitiesPreferredStartingTimes;
import org.confetti.xml.core.time.activities.ConstraintSubactivitiesPreferredTimeSlots;
import org.confetti.xml.core.time.activities.ConstraintThreeActivitiesGrouped;
import org.confetti.xml.core.time.activities.ConstraintTwoActivitiesConsecutive;
import org.confetti.xml.core.time.activities.ConstraintTwoActivitiesGrouped;
import org.confetti.xml.core.time.activities.ConstraintTwoActivitiesOrdered;
import org.confetti.xml.core.time.misc.ConstraintBasicCompulsoryTime;
import org.confetti.xml.core.time.misc.ConstraintBreakTimes;
import org.confetti.xml.core.time.students.ConstraintStudentsActivityTagMaxHoursContinuously;
import org.confetti.xml.core.time.students.ConstraintStudentsActivityTagMaxHoursDaily;
import org.confetti.xml.core.time.students.ConstraintStudentsEarlyMaxBeginningsAtSecondHour;
import org.confetti.xml.core.time.students.ConstraintStudentsIntervalMaxDaysPerWeek;
import org.confetti.xml.core.time.students.ConstraintStudentsMaxDaysPerWeek;
import org.confetti.xml.core.time.students.ConstraintStudentsMaxGapsPerDay;
import org.confetti.xml.core.time.students.ConstraintStudentsMaxGapsPerWeek;
import org.confetti.xml.core.time.students.ConstraintStudentsMaxHoursContinuously;
import org.confetti.xml.core.time.students.ConstraintStudentsMaxHoursDaily;
import org.confetti.xml.core.time.students.ConstraintStudentsMinHoursDaily;
import org.confetti.xml.core.time.students.ConstraintStudentsSetActivityTagMaxHoursContinuously;
import org.confetti.xml.core.time.students.ConstraintStudentsSetActivityTagMaxHoursDaily;
import org.confetti.xml.core.time.students.ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour;
import org.confetti.xml.core.time.students.ConstraintStudentsSetIntervalMaxDaysPerWeek;
import org.confetti.xml.core.time.students.ConstraintStudentsSetMaxDaysPerWeek;
import org.confetti.xml.core.time.students.ConstraintStudentsSetMaxGapsPerDay;
import org.confetti.xml.core.time.students.ConstraintStudentsSetMaxGapsPerWeek;
import org.confetti.xml.core.time.students.ConstraintStudentsSetMaxHoursContinuously;
import org.confetti.xml.core.time.students.ConstraintStudentsSetMaxHoursDaily;
import org.confetti.xml.core.time.students.ConstraintStudentsSetMinHoursDaily;
import org.confetti.xml.core.time.students.ConstraintStudentsSetNotAvailableTimes;
import org.confetti.xml.core.time.teachers.ConstraintTeacherActivityTagMaxHoursContinuously;
import org.confetti.xml.core.time.teachers.ConstraintTeacherActivityTagMaxHoursDaily;
import org.confetti.xml.core.time.teachers.ConstraintTeacherIntervalMaxDaysPerWeek;
import org.confetti.xml.core.time.teachers.ConstraintTeacherMaxDaysPerWeek;
import org.confetti.xml.core.time.teachers.ConstraintTeacherMaxGapsPerDay;
import org.confetti.xml.core.time.teachers.ConstraintTeacherMaxGapsPerWeek;
import org.confetti.xml.core.time.teachers.ConstraintTeacherMaxHoursContinuously;
import org.confetti.xml.core.time.teachers.ConstraintTeacherMaxHoursDaily;
import org.confetti.xml.core.time.teachers.ConstraintTeacherMinDaysPerWeek;
import org.confetti.xml.core.time.teachers.ConstraintTeacherMinHoursDaily;
import org.confetti.xml.core.time.teachers.ConstraintTeacherNotAvailableTimes;
import org.confetti.xml.core.time.teachers.ConstraintTeachersActivityTagMaxHoursContinuously;
import org.confetti.xml.core.time.teachers.ConstraintTeachersActivityTagMaxHoursDaily;
import org.confetti.xml.core.time.teachers.ConstraintTeachersIntervalMaxDaysPerWeek;
import org.confetti.xml.core.time.teachers.ConstraintTeachersMaxDaysPerWeek;
import org.confetti.xml.core.time.teachers.ConstraintTeachersMaxGapsPerDay;
import org.confetti.xml.core.time.teachers.ConstraintTeachersMaxGapsPerWeek;
import org.confetti.xml.core.time.teachers.ConstraintTeachersMaxHoursContinuously;
import org.confetti.xml.core.time.teachers.ConstraintTeachersMaxHoursDaily;
import org.confetti.xml.core.time.teachers.ConstraintTeachersMinDaysPerWeek;
import org.confetti.xml.core.time.teachers.ConstraintTeachersMinHoursDaily;

import com.google.common.collect.Lists;

/**
 * @author Bubla Gabor
 */
public class XmlDataProvider implements DataProvider {
	
	//----------------------------- fields for UI client----------------------------------------------------------------
	private ValueMutator<String> instName = new ValueMutator<>();
	private ValueMutator<String> instComment = new ValueMutator<>();
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
		instName.setValue(this, inst.getName());
		instComment.setValue(this, inst.getComment());
		inst.getDays().getDays()	.forEach(day -> days.addItem(new DayImpl(day.getName())));
		inst.getHours().getHours()	.forEach(hour -> hours.addItem(new HourImpl(hour.getName())));
        inst.getSubjects()			.forEach(subj -> subjects.addItem(new SubjectImpl(subj.getName())));
		inst.getTeachers()			.forEach(teacher -> teachers.addItem(new TeacherImpl(teacher.getName())));
		inst.getRooms()				.forEach(room -> rooms.addItem(new RoomImpl(room.getName())));
		inst.getYears()				.forEach(year -> stdGroups.addItem(createStudentGroup(year)));
		
		Map<String, StudentGroup> studentGroupsByName = collectStudentGroups(stdGroups.getObservableList().getList());
		Iterable<Subject> allSubjects = subjects.getObservableList().getList();
		Iterable<Teacher> allTeachers = teachers.getObservableList().getList();
		inst.getActivities().forEach(act -> assignments.addItem(createAssignment(act, 
				studentGroupsByName, allSubjects, allTeachers)));
		
		ConstraintFactory factory = new ConstraintFactory(
				days.getObservableList().getList(),
				hours.getObservableList().getList(),
				teachers.getObservableList().getList(),
				subjects.getObservableList().getList(),
				rooms.getObservableList().getList(),
				studentGroupsByName,
				assignments.getObservableList().getList());
		inst.getTimeConstraints()	.forEach(x -> constraints.addItem(x.accept(factory, null).build(x)));
		inst.getSpaceConstraints()	.forEach(x -> constraints.addItem(x.accept(factory, null).build(x)));
	}
	
	private AssignmentImpl createAssignment(ActivityXml act , Map<String, StudentGroup> allStdGroups,
		Iterable<Subject> allSubjects, Iterable<Teacher> allTeachers) {
	    if (act.getId() > currentMaxId) {
	        currentMaxId = act.getId();
	    }
		AssignmentImpl ass = new AssignmentImpl(act.getId(), findByName(allSubjects, act.getSubject().getName()));
		if (act.getStudents() != null) {
			act.getStudents().forEach(stGroupName -> ass.addStudentGroup(allStdGroups.get(stGroupName)));
		}
		if (act.getTeachers() != null) {
			act.getTeachers().forEach(teacherRef -> ass.addTeacher(findByName(allTeachers, teacherRef.getName())));
		}
		return ass;
	}

	private StudentGroupImpl createStudentGroup(YearXml year) {
		StudentGroupImpl studentGroup1 = new StudentGroupImpl(year.getName());
		for (GroupXml group : year.getGroups()) {
			StudentGroupImpl studentGroup2 = new StudentGroupImpl(group.getName());
			studentGroup1.addChild(studentGroup2);
			for (SubgroupXml subgroup : group.getSubgroups()) {
				StudentGroupImpl studentGroup3 = new StudentGroupImpl(subgroup.getName());
				studentGroup2.addChild(studentGroup3);
			}
		}
		return studentGroup1;
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
	@Override public ObservableValue<String> getComment() 				   { return instComment.getObservableValue(); }
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
	public void updateInstituteNameAndComment(String newName, String newComment) {
		instXml.setName(newName);
		instXml.setComment(newComment);
		save();
		instName.setValue(this, newName);
		instComment.setValue(this, newComment);
	}

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
	public void addConstraint(final String type, ConstraintAttributes attrs) {
		String shortType = type.substring(ConstraintBuilder.FET_CONSTRAINTS_NAMESPACE.length());
		BaseConstraintXml xmlConstr = shortType.startsWith("time")
			? newXmlConstraint(instXml.getTimeConstraints(), XmlDataProvider::newTimeXmlConstraint, attrs, shortType)
			: newXmlConstraint(instXml.getSpaceConstraints(), XmlDataProvider::newSpaceXmlConstraint, attrs, shortType);
		save();
		
		Constraint constr = new ConstraintImpl(xmlConstr, type, attrs);
		constraints.addItem(constr);
		
		ConstraintDescr constraintDescr = ConstraintRegistry.INSTANCE.getConstraintDescrById(type);
		FieldTypeAddToVisitor visitor = new FieldTypeAddToVisitor(attrs);
		constraintDescr.getFields().forEach(field -> field.getType().accept(visitor, field.getName(), constr));
	}

	private <T extends BaseConstraintXml> T newXmlConstraint(List<T> xmlCons, Function<String, T> fact, 
			ConstraintAttributes attrs, String shortType) {
		T xmlConstr = fact.apply(shortType);
		xmlConstr.accept(ConstraintSetter.INSTANCE, attrs);
		xmlCons.add(xmlConstr);
		return xmlConstr;
	}

	private static TimeConstraint newTimeXmlConstraint(final String shortType) {
		switch (shortType) {
		//----- Miscellaneous
		case "time.BasicCompulsoryTime": return new ConstraintBasicCompulsoryTime();
		case "time.BreakTimes": return new ConstraintBreakTimes();
		//----- Teachers
		case "time.NotAvailableTimesForATeacher": return new ConstraintTeacherNotAvailableTimes();
		case "time.MaxDaysPerWeekForATeacher": return new ConstraintTeacherMaxDaysPerWeek();
		case "time.MinDaysPerWeekForATeacher": return new ConstraintTeacherMinDaysPerWeek();
		case "time.MaxGapsPerDayForATeacher": return new ConstraintTeacherMaxGapsPerDay();
		case "time.MaxGapsPerWeekForATeacher": return new ConstraintTeacherMaxGapsPerWeek();
		case "time.MaxHoursPerDayForATeacher": return new ConstraintTeacherMaxHoursDaily();
		case "time.MaxHoursPerDayWithAnActivityTagForATeacher": return new ConstraintTeacherActivityTagMaxHoursDaily();
		case "time.MinHoursPerDayForATeacher": return new ConstraintTeacherMinHoursDaily();
		case "time.MaxHoursContinuouslyForATeacher": return new ConstraintTeacherMaxHoursContinuously();
		case "time.MaxHoursContinuouslyWithAnActivityTagForATeacher": return new ConstraintTeacherActivityTagMaxHoursContinuously();
		case "time.HourlyIntervalMaxDaysPerWeekForATeacher": return new ConstraintTeacherIntervalMaxDaysPerWeek();
		case "time.MaxDaysPerWeekForAllTeachers": return new ConstraintTeachersMaxDaysPerWeek();
		case "time.MinDaysPerWeekForAllTeachers": return new ConstraintTeachersMinDaysPerWeek();
		case "time.MaxGapsPerDayForAllTeachers": return new ConstraintTeachersMaxGapsPerDay();
		case "time.MaxGapsPerWeekForAllTeachers": return new ConstraintTeachersMaxGapsPerWeek();
		case "time.MaxHoursPerDayForAllTeachers": return new ConstraintTeachersMaxHoursDaily();
		case "time.MaxHoursPerDayWithAnActivityTagForAllTeachers": return new ConstraintTeachersActivityTagMaxHoursDaily();
		case "time.MinHoursPerDayForAllTeachers": return new ConstraintTeachersMinHoursDaily();
		case "time.MaxHoursContinuouslyForAllTeachers": return new ConstraintTeachersMaxHoursContinuously();
		case "time.MaxHoursContinuouslyWithAnActivityTagForAllTeachers": return new ConstraintTeachersActivityTagMaxHoursContinuously();
		case "time.HourlyIntervalMaxDaysPerWeekForAllTeachers": return new ConstraintTeachersIntervalMaxDaysPerWeek();
		//----- Students
		case "time.NotAvailableTimesForAStudentGroup": return new ConstraintStudentsSetNotAvailableTimes();
		case "time.MaxDaysPerWeekForAStudentGroup": return new ConstraintStudentsSetMaxDaysPerWeek();
		case "time.MaxGapsPerDayForAStudentGroup": return new ConstraintStudentsSetMaxGapsPerDay();
		case "time.MaxGapsPerWeekForAStudentGroup": return new ConstraintStudentsSetMaxGapsPerWeek();
		case "time.MaxBeginningsAtSecondHourForAStudentGroup": return new ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour();
		case "time.MaxHoursPerDayForAStudentGroup": return new ConstraintStudentsSetMaxHoursDaily();
		case "time.MaxHoursPerDayWithAnActivityTagForAStudentGroup": return new ConstraintStudentsSetActivityTagMaxHoursDaily();
		case "time.MinHoursPerDayForAStudentGroup": return new ConstraintStudentsSetMinHoursDaily();
		case "time.MaxHoursContinuouslyForAStudentGroup": return new ConstraintStudentsSetMaxHoursContinuously();
		case "time.MaxHoursContinuouslyWithAnActivityTagForAStudentGroup": return new ConstraintStudentsSetActivityTagMaxHoursContinuously();
		case "time.HourlyIntervalMaxDaysPerWeekForAStudentGroup": return new ConstraintStudentsSetIntervalMaxDaysPerWeek();
		case "time.MaxDaysPerWeekForAllStudentGroups": return new ConstraintStudentsMaxDaysPerWeek();
		case "time.MaxGapsPerDayForAllStudentGroups": return new ConstraintStudentsMaxGapsPerDay();
		case "time.MaxGapsPerWeekForAllStudentGroups": return new ConstraintStudentsMaxGapsPerWeek();
		case "time.MaxBeginningsAtSecondHourForAllStudentGroups": return new ConstraintStudentsEarlyMaxBeginningsAtSecondHour();
		case "time.MaxHoursPerDayForAllStudentGroups": return new ConstraintStudentsMaxHoursDaily();
		case "time.MaxHoursPerDayWithAnActivityTagForAllStudentGroups": return new ConstraintStudentsActivityTagMaxHoursDaily();
		case "time.MinHoursPerDayForAllStudentGroups": return new ConstraintStudentsMinHoursDaily();
		case "time.MaxHoursContinuouslyForAllStudentGroups": return new ConstraintStudentsMaxHoursContinuously();
		case "time.MaxHoursContinuouslyWithAnActivityTagForAllStudentGroups": return new ConstraintStudentsActivityTagMaxHoursContinuously();
		case "time.HourlyIntervalMaxDaysPerWeekForAllStudentGroups": return new ConstraintStudentsIntervalMaxDaysPerWeek();
		//----- Activities
		case "time.ActivityHasAPreferredStartingTime": return new ConstraintActivityPreferredStartingTime();
		case "time.ActivityHasSomePreferredStartingTimes": return new ConstraintActivityPreferredStartingTimes();
		case "time.ActivityHasSomePreferredTimeSlots": return new ConstraintActivityPreferredTimeSlots();
		case "time.MoreActivitiesHaveSomePreferredStartingTimes": return new ConstraintActivitiesPreferredStartingTimes();
		case "time.MoreActivitiesHaveSomePreferredTimeSlots": return new ConstraintActivitiesPreferredTimeSlots();
		case "time.MoreSubActivitiesHaveSomePreferredStartingTimes": return new ConstraintSubactivitiesPreferredStartingTimes();
		case "time.MoreSubActivitiesHaveSomePreferredTimeSlots": return new ConstraintSubactivitiesPreferredTimeSlots();
		case "time.MinDaysBetweenActivities": return new ConstraintMinDaysBetweenActivities();
		case "time.MaxDaysBetweenActivities": return new ConstraintMaxDaysBetweenActivities();
		case "time.ActivityEndsStudentsDay": return new ConstraintActivityEndsStudentsDay();
		case "time.MoreActivitiesEndStudentsDay": return new ConstraintActivitiesEndStudentsDay();
		case "time.MoreActivitiesHaveSameStartingTime": return new ConstraintActivitiesSameStartingTime();
		case "time.MoreActivitiesHaveSameStartingDay": return new ConstraintActivitiesSameStartingDay();
		case "time.MoreActivitiesHaveSameStartingHour": return new ConstraintActivitiesSameStartingHour();
		case "time.MoreActivitiesOccupyMaxTimeSlotsFromSelection": return new ConstraintActivitiesOccupyMaxTimeSlotsFromSelection();
		case "time.TwoActivitiesAreOrdered": return new ConstraintTwoActivitiesOrdered();
		case "time.TwoActivitiesAreConsecutive": return new ConstraintTwoActivitiesConsecutive();
		case "time.TwoActivitiesAreGrouped": return new ConstraintTwoActivitiesGrouped();
		case "time.ThreeActivitiesAreGrouped": return new ConstraintThreeActivitiesGrouped();
		case "time.MoreActivitiesAreNotOverlapping": return new ConstraintActivitiesNotOverlapping();
		case "time.MaxSimultaneousActivitiesFromASetInTimeSlots": return new ConstraintActivitiesMaxSimultaneousInSelectedTimeSlots();
		case "time.MinGapsBetweenActivities": return new ConstraintMinGapsBetweenActivities();
		default: return null;
		}
	}

	private static SpaceConstraint newSpaceXmlConstraint(final String shortType) {
		switch (shortType) {
		//----- Miscellaneous
		case "space.BasicCompulsorySpace": return new ConstraintBasicCompulsorySpace();
		//----- Rooms
		case "space.NotAvailableTimesForARoom": return new ConstraintRoomNotAvailableTimes();
		//----- Teachers
		case "space.TeacherHasAHomeRoom": return new ConstraintTeacherHomeRoom();
		case "space.TeacherHasSomeHomeRooms": return new ConstraintTeacherHomeRooms();
		case "space.MaxBuildingChangesPerDayForATeacher": return new ConstraintTeacherMaxBuildingChangesPerDay();
		case "space.MaxBuildingChangesPerWeekForATeacher": return new ConstraintTeacherMaxBuildingChangesPerWeek();
		case "space.MinGapsBetweenBuildingChangesForATeacher": return new ConstraintTeacherMinGapsBetweenBuildingChanges();
		case "space.MaxBuildingChangesPerDayForAllTeachers": return new ConstraintTeachersMaxBuildingChangesPerDay();
		case "space.MaxBuildingChangesPerWeekForAllTeachers": return new ConstraintTeachersMaxBuildingChangesPerWeek();
		case "space.MinGapsBetweenBuildingChangesForAllTeachers": return new ConstraintTeachersMinGapsBetweenBuildingChanges();
		//----- Students
		case "space.StudentGroupHasAHomeRoom": return new ConstraintStudentsSetHomeRoom();
		case "space.StudentGroupHasSomeHomeRooms": return new ConstraintStudentsSetHomeRooms();
		case "space.MaxBuildingChangesPerDayForAStudentGroup": return new ConstraintStudentsSetMaxBuildingChangesPerDay();
		case "space.MaxBuildingChangesPerWeekForAStudentGroup": return new ConstraintStudentsSetMaxBuildingChangesPerWeek();
		case "space.MinGapsBetweenBuildingChangesForAStudentGroup": return new ConstraintStudentsSetMinGapsBetweenBuildingChanges();
		case "space.MaxBuildingChangesPerDayForAllStudentGroups": return new ConstraintStudentsMaxBuildingChangesPerDay();
		case "space.MaxBuildingChangesPerWeekForAllStudentGroups": return new ConstraintStudentsMaxBuildingChangesPerWeek();
		case "space.MinGapsBetweenBuildingChangesForAllStudentGroups": return new ConstraintStudentsMinGapsBetweenBuildingChanges();
		//----- Subjects
		case "space.SubjectHasAPreferredRoom": return new ConstraintSubjectPreferredRoom();
		case "space.SubjectHasSomePreferredRooms": return new ConstraintSubjectPreferredRooms();
		//----- Activity tags
		case "space.ActivityTagHasAPreferredRoom": return new ConstraintActivityTagPreferredRoom();
		case "space.ActivityTagHasSomePreferredRooms": return new ConstraintActivityTagPreferredRooms();
		//----- Subjects and activity tags
		case "space.SubjectAndActivityTagHaveAPreferredRoom": return new ConstraintSubjectActivityTagPreferredRoom();
		case "space.SubjectAndActivityTagHaveSomePreferredRooms": return new ConstraintSubjectActivityTagPreferredRooms();
		//----- Activities
		case "space.ActivityHasAPreferredRoom": return new ConstraintActivityPreferredRoom();
		case "space.ActivityHasSomePreferredRooms": return new ConstraintActivityPreferredRooms();
		case "space.SomeActivitiesAreInTheSameRoomIfTheyAreConsecutive": return new ConstraintActivitiesSameRoomIfConsecutive();
		case "space.SomeActivitiesOccupyMaxDifferentRooms": return new ConstraintActivitiesOccupyMaxDifferentRooms();
		default: return null;
	}
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

	@Override
	public void updateConstraint(Constraint constraint, ConstraintAttributes attrs) {
		ConstraintImpl constraintImpl = (ConstraintImpl) constraint;
		BaseConstraintXml xmlConstraint = constraintImpl.getXmlConstraint();
		xmlConstraint.accept(ConstraintSetter.INSTANCE, attrs);
		save();
		constraintImpl.getAttrsMutator().setValue(constraint, attrs);
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
