package org.confetti.xml.core;

import static com.google.common.collect.Iterables.transform;

import java.util.HashMap;
import java.util.Map;

import org.confetti.core.Assignment;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.dataprovider.xml.AssignmentImpl;
import org.confetti.dataprovider.xml.ConstraintBuilder;
import org.confetti.util.Triple;
import org.confetti.util.Tuple;
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

public class GetConstraintAttrVisitor implements ConstraintXmlVisitor<ConstraintBuilder, Object> {

	private final Map<String, Day> daysByName;
	private final Map<String, Hour> hoursByName;
	private final Map<String, StudentGroup> studentGroupsByName;
	private final Map<String, Teacher> teachersByName;
	private final Map<String, Subject> subjectsByName;
	private final Map<String, Room> roomsByName;
	private final Map<Long, Assignment> assignmentsById;

	public GetConstraintAttrVisitor(
			final Iterable<Day> days,
			final Iterable<Hour> hours,
			final Iterable<Teacher> teachers, 
			final Iterable<Subject> subjects,
			final Iterable<Room> rooms,
			final Map<String, StudentGroup> studentGroups,
			final Iterable<Assignment> assignments) {
		this.daysByName = storeByName(days);
		this.hoursByName = storeByName(hours);
		this.studentGroupsByName = studentGroups;
		this.teachersByName = storeByName(teachers);
		this.subjectsByName = storeByName(subjects);
		this.roomsByName = storeByName(rooms);
		this.assignmentsById = storeById(assignments);
	}
	
	//----- Time constraints
	//----- Miscellaneous
	@Override
	public ConstraintBuilder visitTime(ConstraintBasicCompulsoryTime c, Object p) {
		return fillDefault("time.BasicCompulsoryTime", c)
			.withBoolean("active", c.isActive())
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintBreakTimes c, Object p) {
		return fillDefault("time.BreakTimes", c)
			.withWeek("break-times", transform(c.getBreakTimes(), 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
	;}
	
	//----- Teachers
	@Override
	public ConstraintBuilder visitTime(ConstraintTeacherNotAvailableTimes c, Object p) {
		return fillDefault("time.NotAvailableTimesForATeacher", c)
			.withTeacher("teacher", findTeacher(c.getTeacher()))
			.withWeek("not-available-times", transform(c.getNotAvailableTimes(), 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeacherMaxDaysPerWeek c, Object p) {
		return fillDefault("time.MaxDaysPerWeekForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withDay("days", c.maxDaysPerWeek)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeacherMinDaysPerWeek c, Object p) {
		return fillDefault("time.MinDaysPerWeekForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withDay("days", c.minDaysPerWeek)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeacherMaxGapsPerDay c, Object p) {
		return fillDefault("time.MaxGapsPerDayForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withHour("hours", c.maxGaps)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeacherMaxGapsPerWeek c, Object p) {
		return fillDefault("time.MaxGapsPerWeekForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withDay("days", c.maxGaps)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeacherMaxHoursDaily c, Object p) {
		return fillDefault("time.MaxHoursPerDayForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeacherActivityTagMaxHoursDaily c, Object p) {
		return fillDefault("time.MaxHoursPerDayWithAnActivityTagForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeacherMinHoursDaily c, Object p) {
		return fillDefault("time.MinHoursPerDayForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withHour("hours", c.minimumHoursDaily)
			.withBoolean("allow-empty-days", c.allowEmptyDays)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeacherMaxHoursContinuously c, Object p) {
		return fillDefault("time.MaxHoursContinuouslyForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeacherActivityTagMaxHoursContinuously c, Object p) {
		return fillDefault("time.MaxHoursContinuouslyWithAnActivityTagForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeacherIntervalMaxDaysPerWeek c, Object p) {
		return fillDefault("time.HourlyIntervalMaxDaysPerWeekForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withInterval("interval", new Tuple<>(findHour(c.intervalStartHour), maybeFindHour(c.intervalEndHour)))
			.withDay("days", c.maxDaysPerWeek)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeachersMaxDaysPerWeek c, Object p) {
		return fillDefault("time.MaxDaysPerWeekForAllTeachers", c)
			.withDay("days", c.maxDaysPerWeek)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeachersMinDaysPerWeek c, Object p) {
		return fillDefault("time.MinDaysPerWeekForAllTeachers", c)
			.withDay("days", c.minDaysPerWeek)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeachersMaxGapsPerDay c, Object p) {
		return fillDefault("time.MaxGapsPerDayForAllTeachers", c)
			.withHour("hours", c.maxGaps)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeachersMaxGapsPerWeek c, Object p) {
		return fillDefault("time.MaxGapsPerWeekForAllTeachers", c)
			.withDay("days", c.maxGaps)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeachersMaxHoursDaily c, Object p) {
		return fillDefault("time.MaxHoursPerDayForAllTeachers", c)
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeachersActivityTagMaxHoursDaily c, Object p) {
		return fillDefault("time.MaxHoursPerDayWithAnActivityTagForAllTeachers", c)
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeachersMinHoursDaily c, Object p) {
		return fillDefault("time.MinHoursPerDayForAllTeachers", c)
			.withHour("hours", c.minHoursDaily)
			.withBoolean("allow-empty-days", c.allowEmptyDays)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeachersMaxHoursContinuously c, Object p) {
		return fillDefault("time.MaxHoursContinuouslyForAllTeachers", c)
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeachersActivityTagMaxHoursContinuously c, Object p) {
		return fillDefault("time.MaxHoursContinuouslyWithAnActivityTagForAllTeachers", c)
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTeachersIntervalMaxDaysPerWeek c, Object p) {
		return fillDefault("time.HourlyIntervalMaxDaysPerWeekForAllTeachers", c)
			.withInterval("interval", new Tuple<>(findHour(c.intervalStartHour), maybeFindHour(c.intervalEndHour)))
			.withDay("days", c.maxDaysPerWeek)
	;}

	//----- Students
	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsSetNotAvailableTimes c, Object p) {
		return fillDefault("time.NotAvailableTimesForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.studentsName))
			.withWeek("not-available-times", transform(c.notAvailableTimes, 
					x -> new Tuple<>(findDay(x.getDay()), findHour(x.getHour()))))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsSetMaxDaysPerWeek c, Object p) {
		return fillDefault("time.MaxDaysPerWeekForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withDay("days", c.maxDaysPerWeek)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsSetMaxGapsPerDay c, Object p) {
		return fillDefault("time.MaxGapsPerDayForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("hours", c.maxGaps)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsSetMaxGapsPerWeek c, Object p) {
		return fillDefault("time.MaxGapsPerWeekForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withDay("days", c.maxGaps)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour c, Object p) {
		return fillDefault("time.MaxBeginningsAtSecondHourForAStudentGroup", c)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsSetMaxHoursDaily c, Object p) {
		return fillDefault("time.MaxHoursPerDayForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsSetActivityTagMaxHoursDaily c, Object p) {
		return fillDefault("time.MaxHoursPerDayWithAnActivityTagForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsSetMinHoursDaily c, Object p) {
		return fillDefault("time.MinHoursPerDayForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("hours", c.minHoursDaily)
			.withBoolean("allow-empty-days", c.allowEmptyDays)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsSetMaxHoursContinuously c, Object p) {
		return fillDefault("time.MaxHoursContinuouslyForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsSetActivityTagMaxHoursContinuously c, Object p) {
		return fillDefault("time.MaxHoursContinuouslyWithAnActivityTagForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsSetIntervalMaxDaysPerWeek c, Object p) {
		return fillDefault("time.HourlyIntervalMaxDaysPerWeekForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withInterval("interval", new Tuple<>(findHour(c.intervalStartHour), maybeFindHour(c.intervalEndHour)))
			.withDay("days", c.maxDaysPerWeek)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsMaxDaysPerWeek c, Object p) {
		return fillDefault("time.MaxDaysPerWeekForAllStudentGroups", c)
				.withDay("days", c.maxDaysPerWeek)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsMaxGapsPerDay c, Object p) {
		return fillDefault("time.MaxGapsPerDayForAllStudentGroups", c)
			.withHour("hours", c.maxGaps)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsMaxGapsPerWeek c, Object p) {
		return fillDefault("time.MaxGapsPerWeekForAllStudentGroups", c)
			.withDay("days", c.getMaxGaps())
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsEarlyMaxBeginningsAtSecondHour c, Object p) {
		return fillDefault("time.MaxBeginningsAtSecondHourForAllStudentGroups", c)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsMaxHoursDaily c, Object p) {
		return fillDefault("time.MaxHoursPerDayForAllStudentGroups", c)
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsActivityTagMaxHoursDaily c, Object p) {
		return fillDefault("time.MaxHoursPerDayWithAnActivityTagForAllStudentGroups", c)
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsMinHoursDaily c, Object p) {
		return fillDefault("time.MinHoursPerDayForAllStudentGroups", c)
			.withHour("hours", c.minHoursDaily)
			.withBoolean("allow-empty-days", c.allowEmptyDays)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsMaxHoursContinuously c, Object p) {
		return fillDefault("time.MaxHoursContinuouslyForAllStudentGroups", c)
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsActivityTagMaxHoursContinuously c, Object p) {
		return fillDefault("time.MaxHoursContinuouslyWithAnActivityTagForAllStudentGroups", c)
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintStudentsIntervalMaxDaysPerWeek c, Object p) {
		return fillDefault("time.HourlyIntervalMaxDaysPerWeekForAllStudentGroups", c)
			.withInterval("interval", new Tuple<>(findHour(c.intervalStartHour), maybeFindHour(c.intervalEndHour)))
			.withDay("days", c.maxDaysPerWeek)
	;}

	//----- Activities
	@Override
	public ConstraintBuilder visitTime(ConstraintActivityPreferredStartingTime c, Object p) {
		return fillDefault("time.ActivityHasAPreferredStartingTime", c)
			.withAssignment("assignment", findAssignment(c.getActivityId()))
			.withPeriod("period", slot(findDay(c.getPreferredDay()), findHour(c.getPreferredHour())))
			.withBoolean("locked", c.isLocked())
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintActivityPreferredStartingTimes c, Object p) {
		return fillDefault("time.ActivityHasSomePreferredStartingTimes", c)
			.withAssignment("assignment", findAssignment(c.activityId))
			.withWeek("starting-times", transform(c.preferredStartingTimes, 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintActivityPreferredTimeSlots c, Object p) {
		return fillDefault("time.ActivityHasSomePreferredTimeSlots", c)
			.withAssignment("assignment", findAssignment(c.activityId))
			.withWeek("time-slots", transform(c.preferredTimeSlots, 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintActivitiesPreferredStartingTimes c, Object p) {
		return fillDefault("time.MoreActivitiesHaveSomePreferredStartingTimes", c)
			.withAssignmentsCriteria("assignment", criteria(
					maybeFindSubject(c.subjectName), maybeFindTeacher(c.teacherName), maybeFindStudentGroup(c.studentsName)))
			.withWeek("starting-times", transform(c.preferredStartingTimes, 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintActivitiesPreferredTimeSlots c, Object p) {
		return fillDefault("time.MoreActivitiesHaveSomePreferredTimeSlots", c)
			.withAssignmentsCriteria("assignment", criteria(
					maybeFindSubject(c.subjectName), maybeFindTeacher(c.teacherName), maybeFindStudentGroup(c.studentsName)))
			.withWeek("time-slots", transform(c.preferredTimeSlots, 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintSubactivitiesPreferredStartingTimes c, Object p) {
		return fillDefault("time.MoreSubActivitiesHaveSomePreferredStartingTimes", c)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintSubactivitiesPreferredTimeSlots c, Object p) {
		return fillDefault("time.MoreSubActivitiesHaveSomePreferredTimeSlots", c)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintMinDaysBetweenActivities c, Object p) {
		return fillDefault("time.MinDaysBetweenActivities", c)
			.withAssignmentsSet("assignment", transform(c.getActivityId(), id -> findAssignment(id)))
			.withDay("min-days", c.getMinDays())
			.withBoolean("force-consecutive", c.isConsecutiveIfSameDay())
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintMaxDaysBetweenActivities c, Object p) {
		return fillDefault("time.MaxDaysBetweenActivities", c)
			.withAssignmentsSet("assignment", transform(c.activityIds, id -> findAssignment(id)))
			.withDay("max-days", c.maxDays)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintActivityEndsStudentsDay c, Object p) {
		return fillDefault("time.ActivityEndsStudentsDay", c)
			.withAssignment("assignment", findAssignment(c.activityId))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintActivitiesEndStudentsDay c, Object p) {
		return fillDefault("time.MoreActivitiesEndStudentsDay", c)
				.withAssignmentsCriteria("assignments", criteria(
		maybeFindSubject(c.subjectName), maybeFindTeacher(c.teacherName), maybeFindStudentGroup(c.studentsName)))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintActivitiesSameStartingTime c, Object p) {
		return fillDefault("time.MoreActivitiesHaveSameStartingTime", c)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintActivitiesSameStartingDay c, Object p) {
		return fillDefault("time.MoreActivitiesHaveSameStartingDay", c)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintActivitiesSameStartingHour c, Object p) {
		return fillDefault("time.MoreActivitiesHaveSameStartingHour", c)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintActivitiesOccupyMaxTimeSlotsFromSelection c, Object p) {
		return fillDefault("time.MoreActivitiesOccupyMaxTimeSlotsFromSelection", c)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
			.withWeek("time-slots", transform(c.selectedTimeSlots, x -> slot(findDay(x.day), findHour(x.hour))))
			.withInteger("max-occupied", c.maxNrOfOccupiedTimeSlots)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTwoActivitiesOrdered c, Object p) {
		return fillDefault("time.TwoActivitiesAreOrdered", c)
			.withAssignment("first-assignment", findAssignment(c.firstActivityId))
			.withAssignment("second-assignment", findAssignment(c.secondActivityId))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTwoActivitiesConsecutive c, Object p) {
		return fillDefault("time.TwoActivitiesAreConsecutive", c)
			.withAssignment("first-assignment", findAssignment(c.firstActivityId))
			.withAssignment("second-assignment", findAssignment(c.secondActivityId))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintTwoActivitiesGrouped c, Object p) {
		return fillDefault("time.TwoActivitiesAreGrouped", c)
			.withAssignment("first-assignment", findAssignment(c.firstActivityId))
			.withAssignment("second-assignment", findAssignment(c.secondActivityId))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintThreeActivitiesGrouped c, Object p) {
		return fillDefault("time.ThreeActivitiesAreGrouped", c)
			.withAssignment("first-assignment", findAssignment(c.firstActivityId))
			.withAssignment("second-assignment", findAssignment(c.secondActivityId))
			.withAssignment("third-assignment", findAssignment(c.thirdActivityId))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintActivitiesNotOverlapping c, Object p) {
		return fillDefault("time.MoreActivitiesAreNotOverlapping", c)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintActivitiesMaxSimultaneousInSelectedTimeSlots c, Object p) {
		return fillDefault("time.MaxSimultaneousActivitiesFromASetInTimeSlots", c)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
			.withWeek("time-slots", transform(c.selectedTimeSlots, x -> slot(findDay(x.day), findHour(x.hour))))
			.withInteger("max-simult", c.maxNrOfSimultaneousActivities)
	;}

	@Override
	public ConstraintBuilder visitTime(ConstraintMinGapsBetweenActivities c, Object p) {
		return fillDefault("time.MinGapsBetweenActivities", c)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
			.withHour("min-gaps", c.minGaps)
	;}

	//----- Space constraints
	//----- Miscellaneous
	@Override
	public ConstraintBuilder visitSpace(ConstraintBasicCompulsorySpace c, Object p) {
		return fillDefault("space.BasicCompulsorySpace", c)
			.withBoolean("active", c.isActive())
	;}

	//----- Rooms
	@Override
	public ConstraintBuilder visitSpace(ConstraintRoomNotAvailableTimes c, Object p) {
		return fillDefault("space.NotAvailableTimesForARoom", c)
			.withRoom("room", findRoom(c.room))
			.withWeek("not-available-times", transform(c.notAvailableTimes, 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
	;}

	//----- Teachers
	@Override
	public ConstraintBuilder visitSpace(ConstraintTeacherHomeRoom c, Object p) {
		return fillDefault("space.TeacherHasAHomeRoom", c)
			.withTeacher("teacher", findTeacher(c.teacher))
			.withRoom("room", findRoom(c.room))
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintTeacherHomeRooms c, Object p) {
		return fillDefault("space.TeacherHasSomeHomeRooms", c)
			.withTeacher("teacher", findTeacher(c.teacher))
			.withRoomsSet("rooms", transform(c.rooms, x -> findRoom(x)))
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintTeacherMaxBuildingChangesPerDay c, Object p) {
		return fillDefault("space.MaxBuildingChangesPerDayForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacher))
			.withHour("max-building-changes", c.maxBuildingChangesPerDay)
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintTeacherMaxBuildingChangesPerWeek c, Object p) {
		return fillDefault("space.MaxBuildingChangesPerWeekForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacher))
			.withInteger("max-building-changes", c.maxBuildingChangesPerWeek)
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintTeacherMinGapsBetweenBuildingChanges c, Object p) {
		return fillDefault("space.MinGapsBetweenBuildingChangesForATeacher", c)
			.withTeacher("teacher", findTeacher(c.teacher))
			.withHour("min-gaps", c.minGapsBetweenBuildingChanges)
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintTeachersMaxBuildingChangesPerDay c, Object p) {
		return fillDefault("space.MaxBuildingChangesPerDayForAllTeachers", c)
			.withHour("max-building-changes", c.maxBuildingChangesPerDay)
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintTeachersMaxBuildingChangesPerWeek c, Object p) {
		return fillDefault("space.MaxBuildingChangesPerWeekForAllTeachers", c)
			.withInteger("max-building-changes", c.maxBuildingChangesPerWeek)
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintTeachersMinGapsBetweenBuildingChanges c, Object p) {
		return fillDefault("space.MinGapsBetweenBuildingChangesForAllTeachers", c)
			.withHour("min-gaps", c.minGapsBetweenBuildingChanges)
	;}

	//----- Students
	@Override
	public ConstraintBuilder visitSpace(ConstraintStudentsSetHomeRoom c, Object p) {
		return fillDefault("space.StudentGroupHasAHomeRoom", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withRoom("room", findRoom(c.room))
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintStudentsSetHomeRooms c, Object p) {
		return fillDefault("space.StudentGroupHasSomeHomeRooms", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withRoomsSet("rooms", transform(c.rooms, x -> findRoom(x)))
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintStudentsSetMaxBuildingChangesPerDay c, Object p) {
		return fillDefault("space.MaxBuildingChangesPerDayForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("max-building-changes", c.maxBuildingChangesPerDay)
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintStudentsSetMaxBuildingChangesPerWeek c, Object p) {
		return fillDefault("space.MaxBuildingChangesPerWeekForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withInteger("max-building-changes", c.maxBuildingChangesPerWeek)
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintStudentsSetMinGapsBetweenBuildingChanges c, Object p) {
		return fillDefault("space.MinGapsBetweenBuildingChangesForAStudentGroup", c)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("min-gaps", c.minGapsBetweenBuildingChanges)
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintStudentsMaxBuildingChangesPerDay c, Object p) {
		return fillDefault("space.MaxBuildingChangesPerDayForAllStudentGroups", c)
			.withHour("max-building-changes", c.maxBuildingChangesPerDay)
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintStudentsMaxBuildingChangesPerWeek c, Object p) {
		return fillDefault("space.MaxBuildingChangesPerWeekForAllStudentGroups", c)
			.withInteger("max-building-changes", c.maxBuildingChangesPerWeek)
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintStudentsMinGapsBetweenBuildingChanges c, Object p) {
		return fillDefault("space.MinGapsBetweenBuildingChangesForAllStudentGroups", c)
			.withHour("min-gaps", c.minGapsBetweenBuildingChanges)
	;}

	//----- Subjects
	@Override
	public ConstraintBuilder visitSpace(ConstraintSubjectPreferredRoom c, Object p) {
		return fillDefault("space.SubjectHasAPreferredRoom", c)
			.withSubject("subject", findSubject(c.subject))
			.withRoom("room", findRoom(c.room))
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintSubjectPreferredRooms c, Object p) {
		return fillDefault("space.SubjectHasSomePreferredRooms", c)
			.withSubject("subject", findSubject(c.subject))
			.withRoomsSet("rooms", transform(c.preferredRooms, x -> findRoom(x)))
	;}

	//----- Activity tags
	@Override
	public ConstraintBuilder visitSpace(ConstraintActivityTagPreferredRoom c, Object p) {
		return fillDefault("space.ActivityTagHasAPreferredRoom", c)
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintActivityTagPreferredRooms c, Object p) {
		return fillDefault("space.ActivityTagHasSomePreferredRooms", c)
	;}

	//----- Subjects and activity tags
	@Override
	public ConstraintBuilder visitSpace(ConstraintSubjectActivityTagPreferredRoom c, Object p) {
		return fillDefault("space.SubjectAndActivityTagHaveAPreferredRoom", c)
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintSubjectActivityTagPreferredRooms c, Object p) {
		return fillDefault("space.SubjectAndActivityTagHaveSomePreferredRooms", c)
	;}

	//----- Activities
	@Override
	public ConstraintBuilder visitSpace(ConstraintActivityPreferredRoom c, Object p) {
		return fillDefault("space.ActivityHasAPreferredRoom", c)
			.withAssignment("assignment", findAssignment(c.getActivityId()))
			.withRoom("room", findRoom(c.getRoom()))
			.withBoolean("locked", c.isLocked())
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintActivityPreferredRooms c, Object p) {
		return fillDefault("space.ActivityHasSomePreferredRooms", c)
			.withAssignment("assignment", findAssignment(c.activityId))
			.withRoomsSet("rooms", transform(c.preferredRooms, x -> findRoom(x)))
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintActivitiesSameRoomIfConsecutive c, Object p) {
		return fillDefault("space.SomeActivitiesAreInTheSameRoomIfTheyAreConsecutive", c)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
	;}

	@Override
	public ConstraintBuilder visitSpace(ConstraintActivitiesOccupyMaxDifferentRooms c, Object p) {
		return fillDefault("space.SomeActivitiesOccupyMaxDifferentRooms", c)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
			.withInteger("max-diff-rooms", c.maxNrOfDifferentRooms)
	;}

	//------------------- helpers --------------------------------------------------------------------------------------
	private ConstraintBuilder fillDefault(String type, BaseConstraintXml c) {
		return new ConstraintBuilder(type)
			.withDouble("weight-percentage", c.getWeight());
	}

	private static Tuple<Day, Hour> slot(Day day, Hour hour) {
		return new Tuple<>(day, hour);
	}
	
	private static Triple<Subject, Teacher, StudentGroup> criteria(Subject subj, Teacher tea, StudentGroup sg) {
		return new Triple<Subject, Teacher, StudentGroup>(subj, tea, sg);
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

	private static <K, V> V safeGet(K key, Map<K, V> store, String errMsg) {
		if (!store.containsKey(key)) {
			throw new RuntimeException(errMsg + " with name '" + key + "' not found.");
		}
		return store.get(key);
	}
	
	private Day findDay(final String dayName) {
		return safeGet(dayName, daysByName, "Day");
	}

	private Hour findHour(final String hourName) {
		return safeGet(hourName, hoursByName, "Hour");
	}
	
	private Hour maybeFindHour(String hourName) {
		return hoursByName.get(hourName);
	}

	private Teacher findTeacher(final String teacherName) {
		return safeGet(teacherName, teachersByName, "Teacher");
	}
	
	private Teacher maybeFindTeacher(final String teacherName) {
		return teachersByName.get(teacherName);
	}
	
	private StudentGroup findStudentGroup(final String studentGroupName) {
		return safeGet(studentGroupName, studentGroupsByName, "Student group");
	}

	private StudentGroup maybeFindStudentGroup(final String studentGroupName) {
		return studentGroupsByName.get(studentGroupName);
	}

	private Subject findSubject(final String subjectName) {
		return safeGet(subjectName, subjectsByName, "Subject");
	}

	private Subject maybeFindSubject(final String subjectName) {
		return subjectsByName.get(subjectName);
	}

	private Room findRoom(final String roomName) {
		return safeGet(roomName, roomsByName, "Room");
	}

	private Assignment findAssignment(final long id) {
		return safeGet(id, assignmentsById, "Assignment");
	}

}
