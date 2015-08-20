package org.confetti.xml.core;

import static com.google.common.collect.Iterables.transform;

import java.util.HashMap;
import java.util.Map;

import org.confetti.core.Assignment;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
import org.confetti.dataprovider.xml.AssignmentImpl;
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

public class GetConstraintAttrVisitor implements ConstraintXmlVisitor<ConstraintAttributes, ConstraintAttributes> {

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
	public ConstraintAttributes visitTime(ConstraintBasicCompulsoryTime c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withBoolean("active", c.isActive())
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintBreakTimes c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withWeek("break-times", transform(c.getBreakTimes(), 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
	;}
	
	//----- Teachers
	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherNotAvailableTimes c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.getTeacher()))
			.withWeek("not-available-times", transform(c.getNotAvailableTimes(), 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMaxDaysPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withDay("days", c.maxDaysPerWeek)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMinDaysPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withDay("days", c.minDaysPerWeek)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMaxGapsPerDay c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withHour("hours", c.maxGaps)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMaxGapsPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withDay("days", c.maxGaps)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMaxHoursDaily c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherActivityTagMaxHoursDaily c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMinHoursDaily c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withHour("hours", c.minimumHoursDaily)
			.withBoolean("allow-empty-days", c.allowEmptyDays)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMaxHoursContinuously c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherActivityTagMaxHoursContinuously c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherIntervalMaxDaysPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacherName))
			.withPeriod("interval", null)
			.withDay("days", c.maxDaysPerWeek)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMaxDaysPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withDay("days", c.maxDaysPerWeek)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMinDaysPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withDay("days", c.minDaysPerWeek)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMaxGapsPerDay c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("hours", c.maxGaps)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMaxGapsPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withDay("days", c.maxGaps)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMaxHoursDaily c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersActivityTagMaxHoursDaily c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMinHoursDaily c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("hours", c.minHoursDaily)
			.withBoolean("allow-empty-days", c.allowEmptyDays)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMaxHoursContinuously c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersActivityTagMaxHoursContinuously c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersIntervalMaxDaysPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withPeriod("interval", null)
			.withDay("days", c.maxDaysPerWeek)
	;}

	//----- Students
	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetNotAvailableTimes c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.studentsName))
			.withWeek("not-available-times", transform(c.notAvailableTimes, 
					x -> new Tuple<>(findDay(x.getDay()), findHour(x.getHour()))))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetMaxDaysPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withDay("days", c.maxDaysPerWeek)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetMaxGapsPerDay c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("hours", c.maxGaps)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetMaxGapsPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withDay("days", c.maxGaps)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour c, ConstraintAttributes p) {
		return fillDefault(c, p)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetMaxHoursDaily c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetActivityTagMaxHoursDaily c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetMinHoursDaily c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("hours", c.minHoursDaily)
			.withBoolean("allow-empty-days", c.allowEmptyDays)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetMaxHoursContinuously c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetActivityTagMaxHoursContinuously c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetIntervalMaxDaysPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withPeriod("interval", null)
			.withDay("days", c.maxDaysPerWeek)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsMaxDaysPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
				.withDay("days", c.maxDaysPerWeek)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsMaxGapsPerDay c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("hours", c.maxGaps)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsMaxGapsPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withDay("days", c.getMaxGaps())
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsEarlyMaxBeginningsAtSecondHour c, ConstraintAttributes p) {
		return fillDefault(c, p)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsMaxHoursDaily c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsActivityTagMaxHoursDaily c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("hours", c.maxHoursDaily)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsMinHoursDaily c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("hours", c.minHoursDaily)
			.withBoolean("allow-empty-days", c.allowEmptyDays)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsMaxHoursContinuously c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsActivityTagMaxHoursContinuously c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("hours", c.maxHoursContinuously)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsIntervalMaxDaysPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withPeriod("interval", null)
			.withDay("days", c.maxDaysPerWeek)
	;}

	//----- Activities
	@Override
	public ConstraintAttributes visitTime(ConstraintActivityPreferredStartingTime c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignment("assignment", findAssignment(c.getActivityId()))
			.withPeriod("period", null)
			.withBoolean("locked", c.isLocked())
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivityPreferredStartingTimes c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignment("assignment", findAssignment(c.activityId))
			.withWeek("starting-times", transform(c.preferredStartingTimes, 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivityPreferredTimeSlots c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignment("assignment", findAssignment(c.activityId))
			.withWeek("time-slots", transform(c.preferredTimeSlots, 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivitiesPreferredStartingTimes c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignment", null)
			.withWeek("starting-times", transform(c.preferredStartingTimes, 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
			.withBoolean("duration-enabled", null)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivitiesPreferredTimeSlots c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignment", null)
			.withWeek("time-slots", transform(c.preferredTimeSlots, 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
			.withBoolean("duration-enabled", null)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintSubactivitiesPreferredStartingTimes c, ConstraintAttributes p) {
		return fillDefault(c, p)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintSubactivitiesPreferredTimeSlots c, ConstraintAttributes p) {
		return fillDefault(c, p)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintMinDaysBetweenActivities c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignment", transform(c.getActivityId(), id -> findAssignment(id)))
			.withDay("min-days", c.getMinDays())
			.withBoolean("force-consecutive", c.isConsecutiveIfSameDay())
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintMaxDaysBetweenActivities c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignment", transform(c.activityIds, id -> findAssignment(id)))
			.withDay("max-days", c.maxDays)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivityEndsStudentsDay c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignment("assignment", findAssignment(c.activityId))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivitiesEndStudentsDay c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignments", null)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivitiesSameStartingTime c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivitiesSameStartingDay c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivitiesSameStartingHour c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivitiesOccupyMaxTimeSlotsFromSelection c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
			.withWeek("time-slots", transform(c.selectedTimeSlots, x -> slot(findDay(x.day), findHour(x.hour))))
			.withInteger("max-occupied", c.maxNrOfOccupiedTimeSlots)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTwoActivitiesOrdered c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignment("first-assignment", findAssignment(c.firstActivityId))
			.withAssignment("second-assignment", findAssignment(c.secondActivityId))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTwoActivitiesConsecutive c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignment("first-assignment", findAssignment(c.firstActivityId))
			.withAssignment("second-assignment", findAssignment(c.secondActivityId))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintTwoActivitiesGrouped c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignment("first-assignment", findAssignment(c.firstActivityId))
			.withAssignment("second-assignment", findAssignment(c.secondActivityId))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintThreeActivitiesGrouped c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignment("first-assignment", findAssignment(c.firstActivityId))
			.withAssignment("second-assignment", findAssignment(c.secondActivityId))
			.withAssignment("third-assignment", findAssignment(c.thirdActivityId))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivitiesNotOverlapping c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivitiesMaxSimultaneousInSelectedTimeSlots c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
			.withWeek("time-slots", transform(c.selectedTimeSlots, x -> slot(findDay(x.day), findHour(x.hour))))
			.withInteger("max-simult", c.maxNrOfSimultaneousActivities)
	;}

	@Override
	public ConstraintAttributes visitTime(ConstraintMinGapsBetweenActivities c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
			.withHour("min-gaps", c.minGaps)
	;}

	//----- Space constraints
	//----- Miscellaneous
	@Override
	public ConstraintAttributes visitSpace(ConstraintBasicCompulsorySpace c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withBoolean("active", c.isActive())
	;}

	//----- Rooms
	@Override
	public ConstraintAttributes visitSpace(ConstraintRoomNotAvailableTimes c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withRoom("room", findRoom(c.room))
			.withWeek("not-available-times", transform(c.notAvailableTimes, 
					x -> slot(findDay(x.getDay()), findHour(x.getHour()))))
	;}

	//----- Teachers
	@Override
	public ConstraintAttributes visitSpace(ConstraintTeacherHomeRoom c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacher))
			.withRoom("room", findRoom(c.room))
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintTeacherHomeRooms c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacher))
			.withRoomsSet("rooms", transform(c.rooms, x -> findRoom(x)))
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintTeacherMaxBuildingChangesPerDay c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacher))
			.withHour("max-building-changes", c.maxBuildingChangesPerDay)
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintTeacherMaxBuildingChangesPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacher))
			.withInteger("max-building-changes", c.maxBuildingChangesPerWeek)
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintTeacherMinGapsBetweenBuildingChanges c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withTeacher("teacher", findTeacher(c.teacher))
			.withHour("min-gaps", c.minGapsBetweenBuildingChanges)
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintTeachersMaxBuildingChangesPerDay c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("max-building-changes", c.maxBuildingChangesPerDay)
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintTeachersMaxBuildingChangesPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withInteger("max-building-changes", c.maxBuildingChangesPerWeek)
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintTeachersMinGapsBetweenBuildingChanges c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("min-gaps", c.minGapsBetweenBuildingChanges)
	;}

	//----- Students
	@Override
	public ConstraintAttributes visitSpace(ConstraintStudentsSetHomeRoom c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withRoom("room", findRoom(c.room))
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintStudentsSetHomeRooms c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withRoomsSet("rooms", transform(c.rooms, x -> findRoom(x)))
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintStudentsSetMaxBuildingChangesPerDay c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("max-building-changes", c.maxBuildingChangesPerDay)
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintStudentsSetMaxBuildingChangesPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withInteger("max-building-changes", c.maxBuildingChangesPerWeek)
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintStudentsSetMinGapsBetweenBuildingChanges c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withStudentGroup("studentgroup", findStudentGroup(c.students))
			.withHour("min-gaps", c.minGapsBetweenBuildingChanges)
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintStudentsMaxBuildingChangesPerDay c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("max-building-changes", c.maxBuildingChangesPerDay)
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintStudentsMaxBuildingChangesPerWeek c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withInteger("max-building-changes", c.maxBuildingChangesPerWeek)
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintStudentsMinGapsBetweenBuildingChanges c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withHour("min-gaps", c.minGapsBetweenBuildingChanges)
	;}

	//----- Subjects
	@Override
	public ConstraintAttributes visitSpace(ConstraintSubjectPreferredRoom c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withSubject("subject", findSubject(c.subject))
			.withRoom("room", findRoom(c.room))
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintSubjectPreferredRooms c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withSubject("subject", findSubject(c.subject))
			.withRoomsSet("rooms", transform(c.preferredRooms, x -> findRoom(x)))
	;}

	//----- Activity tags
	@Override
	public ConstraintAttributes visitSpace(ConstraintActivityTagPreferredRoom c, ConstraintAttributes p) {
		return fillDefault(c, p)
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintActivityTagPreferredRooms c, ConstraintAttributes p) {
		return fillDefault(c, p)
	;}

	//----- Subjects and activity tags
	@Override
	public ConstraintAttributes visitSpace(ConstraintSubjectActivityTagPreferredRoom c, ConstraintAttributes p) {
		return fillDefault(c, p)
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintSubjectActivityTagPreferredRooms c, ConstraintAttributes p) {
		return fillDefault(c, p)
	;}

	//----- Activities
	@Override
	public ConstraintAttributes visitSpace(ConstraintActivityPreferredRoom c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignment("assignment", findAssignment(c.getActivityId()))
			.withRoom("room", findRoom(c.getRoom()))
			.withBoolean("locked", c.isLocked())
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintActivityPreferredRooms c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignment("assignment", findAssignment(c.activityId))
			.withRoomsSet("rooms", transform(c.preferredRooms, x -> findRoom(x)))
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintActivitiesSameRoomIfConsecutive c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
	;}

	@Override
	public ConstraintAttributes visitSpace(ConstraintActivitiesOccupyMaxDifferentRooms c, ConstraintAttributes p) {
		return fillDefault(c, p)
			.withAssignmentsSet("assignments", transform(c.activityIds, id -> findAssignment(id)))
			.withInteger("max-diff-rooms", c.maxNrOfDifferentRooms)
	;}

	//------------------- helpers --------------------------------------------------------------------------------------
	private static ConstraintAttributes fillDefault(BaseConstraintXml c, ConstraintAttributes p) {
		return p.withDouble("weight-percentage", c.getWeight());
	}

	private static Tuple<Day, Hour> slot(Day day, Hour hour) {
		return new Tuple<>(day, hour);
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

	private Teacher findTeacher(final String teacherName) {
		return safeGet(teacherName, teachersByName, "Teacher");
	}
	
	private StudentGroup findStudentGroup(final String studentGroupName) {
		return safeGet(studentGroupName, studentGroupsByName, "Student group");
	}

	private Subject findSubject(final String subjectName) {
		return safeGet(subjectName, subjectsByName, "Subject");
	}

	private Room findRoom(final String roomName) {
		return safeGet(roomName, roomsByName, "Room");
	}

	private Assignment findAssignment(final long id) {
		return safeGet(id, assignmentsById, "Assignment");
	}

}
