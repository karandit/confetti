package org.confetti.xml.core;


import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import org.confetti.core.Assignment;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Teacher;
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
import org.confetti.xml.core.time.BreakTimeXml;
import org.confetti.xml.core.time.PreferredStartingTimeXml;
import org.confetti.xml.core.time.PreferredTimeXml;
import org.confetti.xml.core.time.SelectedTimeXml;
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

public class ConstraintSetter implements ConstraintXmlVisitor<BaseConstraintXml, ConstraintAttributes> {

	private Function<Assignment, Long> getAssgIdFunc;

	public ConstraintSetter(Function<Assignment, Long> assgGetId) {
		this.getAssgIdFunc = assgGetId;
	}
	
	//----- Time constraints
	//----- Miscellaneous
	@Override
	public BaseConstraintXml visitTime(ConstraintBasicCompulsoryTime c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActive(p.asBoolean("active"));
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintBreakTimes c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setBreakTimes(toList(p.asWeek("break-times"), ConstraintSetter::toBreakTimeXml));
	return c;}
	
	//----- Teachers
	@Override
	public BaseConstraintXml visitTime(ConstraintTeacherNotAvailableTimes c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setTeacher(getSafeName(p.asTeacher("teacher")));
		c.setNotAvailableTimes(toList(p.asWeek("not-available-times"), ConstraintSetter::toBreakTimeXml));
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeacherMaxDaysPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.teacherName = getSafeName(p.asTeacher("teacher"));
		c.maxDaysPerWeek = p.asDay("days");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeacherMinDaysPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.teacherName = getSafeName(p.asTeacher("teacher"));
		c.minDaysPerWeek = p.asDay("days");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeacherMaxGapsPerDay c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.teacherName = getSafeName(p.asTeacher("teacher"));
			c.maxGaps = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeacherMaxGapsPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.teacherName = getSafeName(p.asTeacher("teacher"));
			c.maxGaps = p.asDay("days");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeacherMaxHoursDaily c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.teacherName = getSafeName(p.asTeacher("teacher"));
			c.maxHoursDaily = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeacherActivityTagMaxHoursDaily c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.teacherName = getSafeName(p.asTeacher("teacher"));
			c.maxHoursDaily = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeacherMinHoursDaily c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.teacherName = getSafeName(p.asTeacher("teacher"));
			c.minimumHoursDaily = p.asHour("hours");
			c.allowEmptyDays = p.asBoolean("allow-empty-days");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeacherMaxHoursContinuously c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.teacherName = getSafeName(p.asTeacher("teacher"));
			c.maxHoursContinuously = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeacherActivityTagMaxHoursContinuously c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.teacherName = getSafeName(p.asTeacher("teacher"));
			c.maxHoursContinuously = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeacherIntervalMaxDaysPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.teacherName = getSafeName(p.asTeacher("teacher"));
		Tuple<Hour, Hour> interval = p.asInterval("interval");
		c.intervalStartHour = getSafeName(interval.getFirst());
		c.intervalEndHour = getSafeName(interval.getSecond());
		c.maxDaysPerWeek = p.asDay("days");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeachersMaxDaysPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.maxDaysPerWeek = p.asDay("days");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeachersMinDaysPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.minDaysPerWeek = p.asDay("days");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeachersMaxGapsPerDay c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.maxGaps = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeachersMaxGapsPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.maxGaps = p.asDay("days");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeachersMaxHoursDaily c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.maxHoursDaily = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeachersActivityTagMaxHoursDaily c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.maxHoursDaily = p.asHour("hours");
		c.activityTagName = getSafeName(p.asTag("tag"));
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeachersMinHoursDaily c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.minHoursDaily = p.asHour("hours");
			c.allowEmptyDays = p.asBoolean("allow-empty-days");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeachersMaxHoursContinuously c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.maxHoursContinuously = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeachersActivityTagMaxHoursContinuously c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.maxHoursContinuously = p.asHour("hours");
			c.activityTagName = getSafeName(p.asTag("tag"));
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTeachersIntervalMaxDaysPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
		Tuple<Hour, Hour> interval = p.asInterval("interval"); 
		c.intervalStartHour = getSafeName(interval.getFirst());
		c.intervalEndHour = getSafeName(interval.getSecond());
		c.maxDaysPerWeek = p.asDay("days");
		return c;
	}

	//----- Students
	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsSetNotAvailableTimes c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.studentsName = getSafeName(p.asStudentGroup("studentgroup"));
		c.setNotAvailableTimes(toList(p.asWeek("not-available-times"), ConstraintSetter::toBreakTimeXml));
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsSetMaxDaysPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.students = getSafeName(p.asStudentGroup("studentgroup"));
		c.maxDaysPerWeek = p.asDay("days");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsSetMaxGapsPerDay c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.students = getSafeName(p.asStudentGroup("studentgroup"));
		c.maxGaps = p.asHour("hours");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsSetMaxGapsPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.students = getSafeName(p.asStudentGroup("studentgroup"));
		c.maxGaps = p.asDay("days");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.students = getSafeName(p.asStudentGroup("studentgroup"));
		c.maxBeginningsAtSecondHour = p.asInteger("maxBeginnings");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsSetMaxHoursDaily c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.students = getSafeName(p.asStudentGroup("studentgroup"));
		c.maxHoursDaily = p.asHour("hours");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsSetActivityTagMaxHoursDaily c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.students = getSafeName(p.asStudentGroup("studentgroup"));
		c.maxHoursDaily = p.asHour("hours");
		c.activityTag = getSafeName(p.asTag("tag"));
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsSetMinHoursDaily c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.students = getSafeName(p.asStudentGroup("studentgroup"));
		c.minHoursDaily = p.asHour("hours");
		c.allowEmptyDays = p.asBoolean("allow-empty-days");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsSetMaxHoursContinuously c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.students = getSafeName(p.asStudentGroup("studentgroup"));
			c.maxHoursContinuously = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsSetActivityTagMaxHoursContinuously c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.students = getSafeName(p.asStudentGroup("studentgroup"));
			c.maxHoursContinuously = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsSetIntervalMaxDaysPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.students = getSafeName(p.asStudentGroup("studentgroup"));
		Tuple<Hour, Hour> interval = p.asInterval("interval");
		c.intervalStartHour = getSafeName(interval.getFirst());
		c.intervalEndHour = getSafeName(interval.getSecond());
		c.maxDaysPerWeek = p.asDay("days");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsMaxDaysPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
				c.maxDaysPerWeek = p.asDay("days");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsMaxGapsPerDay c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.maxGaps = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsMaxGapsPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setMaxGaps(p.asDay("days"));
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsEarlyMaxBeginningsAtSecondHour c, ConstraintAttributes p) {
		fillDefault(c, p);
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsMaxHoursDaily c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.maxHoursDaily = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsActivityTagMaxHoursDaily c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.maxHoursDaily = p.asHour("hours");
		c.activityTag = getSafeName(p.asTag("tag"));
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsMinHoursDaily c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.minHoursDaily = p.asHour("hours");
			c.allowEmptyDays = p.asBoolean("allow-empty-days");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsMaxHoursContinuously c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.maxHoursContinuously = p.asHour("hours");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsActivityTagMaxHoursContinuously c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.maxHoursContinuously = p.asHour("hours");
		c.activityTag = getSafeName(p.asTag("tag"));
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintStudentsIntervalMaxDaysPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
		Tuple<Hour, Hour> interval = p.asInterval("interval");
		c.intervalStartHour = getSafeName(interval.getFirst());
		c.intervalEndHour = getSafeName(interval.getSecond());
		c.maxDaysPerWeek = p.asDay("days");
		return c;
	}

	//----- Activities
	@Override
	public BaseConstraintXml visitTime(ConstraintActivityPreferredStartingTime c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActivityId(getAssgId(p.asAssignment("assignment")));
		Tuple<Day, Hour> period = p.asPeriod("period"); 
		c.setPreferredDay(getSafeName(period.getFirst()));
		c.setPreferredHour(getSafeName(period.getSecond()));
		c.setLocked(p.asBoolean("locked")); 
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintActivityPreferredStartingTimes c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.activityId = getAssgId(p.asAssignment("assignment"));
		c.setPreferredStartingTimes(
				toList(p.asWeek("starting-times"), ConstraintSetter::toPreferredStartingTimeXml)); 
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintActivityPreferredTimeSlots c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.activityId = getAssgId(p.asAssignment("assignment"));
		c.setPreferredTimeSlots(toList(p.asWeek("time-slots"), ConstraintSetter::toPreferredTimeXml));
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintActivitiesPreferredStartingTimes c, ConstraintAttributes p) {
		fillDefault(c, p);
		Triple<Subject, Teacher, StudentGroup> triple = p.asAssignmentsCriteria("assignment");
		c.subjectName = getSafeName(triple.getFirst()); 
		c.teacherName = getSafeName(triple.getSecond()); 
		c.studentsName = getSafeName(triple.getThird());
		c.setPreferredStartingTimes(toList(p.asWeek("starting-times"), ConstraintSetter::toPreferredStartingTimeXml));
		c.activityTagName = p.asMaybeTag("tag").map(ConstraintSetter::getSafeName).orElse("");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintActivitiesPreferredTimeSlots c, ConstraintAttributes p) {
		fillDefault(c, p);
		Triple<Subject, Teacher, StudentGroup> triple = p.asAssignmentsCriteria("assignment");
		c.subjectName = getSafeName(triple.getFirst()); 
		c.teacherName = getSafeName(triple.getSecond()); 
		c.studentsName = getSafeName(triple.getThird());
		c.setPreferredTimeSlots(toList(p.asWeek("time-slots"), ConstraintSetter::toPreferredTimeXml));
		c.activityTagName = p.asMaybeTag("tag").map(ConstraintSetter::getSafeName).orElse("");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintSubactivitiesPreferredStartingTimes c, ConstraintAttributes p) {
		fillDefault(c, p);
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintSubactivitiesPreferredTimeSlots c, ConstraintAttributes p) {
		fillDefault(c, p);
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintMinDaysBetweenActivities c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActivityId(toList(p.asAssignmentsSet("assignment"), this::getAssgId));
		c.setMinDays(p.asDay("min-days"));
		c.setConsecutiveIfSameDay(p.asBoolean("force-consecutive"));
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintMaxDaysBetweenActivities c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.activityIds = toList(p.asAssignmentsSet("assignment"), this::getAssgId);
		c.maxDays = p.asDay("max-days");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintActivityEndsStudentsDay c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.activityId = getAssgId(p.asAssignment("assignment")).intValue();
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintActivitiesEndStudentsDay c, ConstraintAttributes p) {
		fillDefault(c, p);
		Triple<Subject, Teacher, StudentGroup> triple = p.asAssignmentsCriteria("assignment");
		c.subjectName = getSafeName(triple.getFirst()); 
		c.teacherName = getSafeName(triple.getSecond()); 
		c.studentsName = getSafeName(triple.getThird());
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintActivitiesSameStartingTime c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActivityIds(toList(p.asAssignmentsSet("assignments"), this::getAssgId)); 
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintActivitiesSameStartingDay c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActivityIds(toList(p.asAssignmentsSet("assignments"), this::getAssgId)); 
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintActivitiesSameStartingHour c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActivityIds(toList(p.asAssignmentsSet("assignments"), this::getAssgId)); 
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintActivitiesOccupyMaxTimeSlotsFromSelection c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActivityIds(toList(p.asAssignmentsSet("assignments"), this::getAssgId)); 
		c.setSelectedTimeSlots(toList(p.asWeek("time-slots"), ConstraintSetter::toSelectedTimeXml)); 
		c.maxNrOfOccupiedTimeSlots = p.asInteger("max-occupied");
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTwoActivitiesOrdered c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.firstActivityId = getAssgId(p.asAssignment("first-assignment")).intValue();
			c.secondActivityId = getAssgId(p.asAssignment("second-assignment")).intValue();
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTwoActivitiesConsecutive c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.firstActivityId = getAssgId(p.asAssignment("first-assignment")).intValue();
			c.secondActivityId = getAssgId(p.asAssignment("second-assignment")).intValue();
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintTwoActivitiesGrouped c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.firstActivityId = getAssgId(p.asAssignment("first-assignment")).intValue();
			c.secondActivityId = getAssgId(p.asAssignment("second-assignment")).intValue();
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintThreeActivitiesGrouped c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.firstActivityId = getAssgId(p.asAssignment("first-assignment")).intValue();
			c.secondActivityId = getAssgId(p.asAssignment("second-assignment")).intValue();
			c.thirdActivityId = getAssgId(p.asAssignment("third-assignment")).intValue();
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintActivitiesNotOverlapping c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActivityIds(toList(p.asAssignmentsSet("assignments"), this::getAssgId));
	return c;}

	@Override
	public BaseConstraintXml visitTime(ConstraintActivitiesMaxSimultaneousInSelectedTimeSlots c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.activityIds = toList(p.asAssignmentsSet("assignments"), this::getAssgId);
		c.selectedTimeSlots	= toList(p.asWeek("time-slots"), ConstraintSetter::toSelectedTimeXml); 
		c.maxNrOfSimultaneousActivities = p.asInteger("max-simult");
		return c;
	}

	@Override
	public BaseConstraintXml visitTime(ConstraintMinGapsBetweenActivities c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActivityIds(toList(p.asAssignmentsSet("assignments"), this::getAssgId));
		c.minGaps = p.asHour("min-gaps");
		return c;
	}

	//----- Space constraints
	//----- Miscellaneous
	@Override
	public BaseConstraintXml visitSpace(ConstraintBasicCompulsorySpace c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActive(p.asBoolean("active"));
	return c;}

	//----- Rooms
	@Override
	public BaseConstraintXml visitSpace(ConstraintRoomNotAvailableTimes c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.room = getSafeName(p.asRoom("room"));
		c.setNotAvailableTimes(toList(p.asWeek("not-available-times"), ConstraintSetter::toBreakTimeXml));
		return c;
	}

	//----- Teachers
	@Override
	public BaseConstraintXml visitSpace(ConstraintTeacherHomeRoom c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.teacher = getSafeName(p.asTeacher("teacher"));
		c.room = getSafeName(p.asRoom("room"));
		return c;
	}

	@Override
	public BaseConstraintXml visitSpace(ConstraintTeacherHomeRooms c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.teacher = getSafeName(p.asTeacher("teacher"));
		c.setRooms(toList(p.asRoomsSet("rooms"), ConstraintSetter::getSafeName));
		return c;
	}

	@Override
	public BaseConstraintXml visitSpace(ConstraintTeacherMaxBuildingChangesPerDay c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.teacher = getSafeName(p.asTeacher("teacher"));
		c.maxBuildingChangesPerDay = p.asHour("max-building-changes");
		return c;
	}

	@Override
	public BaseConstraintXml visitSpace(ConstraintTeacherMaxBuildingChangesPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.teacher = getSafeName(p.asTeacher("teacher"));
		c.maxBuildingChangesPerWeek = p.asInteger("max-building-changes");
		return c;
	}

	@Override
	public BaseConstraintXml visitSpace(ConstraintTeacherMinGapsBetweenBuildingChanges c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.teacher = getSafeName(p.asTeacher("teacher"));
		c.minGapsBetweenBuildingChanges = p.asHour("min-gaps");
		return c;
	}

	@Override
	public BaseConstraintXml visitSpace(ConstraintTeachersMaxBuildingChangesPerDay c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.maxBuildingChangesPerDay = p.asHour("max-building-changes");
		return c;
	}

	@Override
	public BaseConstraintXml visitSpace(ConstraintTeachersMaxBuildingChangesPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.maxBuildingChangesPerWeek = p.asInteger("max-building-changes");
	return c;}

	@Override
	public BaseConstraintXml visitSpace(ConstraintTeachersMinGapsBetweenBuildingChanges c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.minGapsBetweenBuildingChanges = p.asHour("min-gaps");
	return c;}

	//----- Students
	@Override
	public BaseConstraintXml visitSpace(ConstraintStudentsSetHomeRoom c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.students = getSafeName(p.asStudentGroup("studentgroup"));
			c.room = getSafeName(p.asRoom("room"));
	return c;}

	@Override
	public BaseConstraintXml visitSpace(ConstraintStudentsSetHomeRooms c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.students = getSafeName(p.asStudentGroup("studentgroup"));
		c.setRooms(toList(p.asRoomsSet("rooms"), ConstraintSetter::getSafeName));
		return c;
	}

	@Override
	public BaseConstraintXml visitSpace(ConstraintStudentsSetMaxBuildingChangesPerDay c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.students = getSafeName(p.asStudentGroup("studentgroup"));
			c.maxBuildingChangesPerDay = p.asHour("max-building-changes");
	return c;}

	@Override
	public BaseConstraintXml visitSpace(ConstraintStudentsSetMaxBuildingChangesPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.students = getSafeName(p.asStudentGroup("studentgroup"));
			c.maxBuildingChangesPerWeek = p.asInteger("max-building-changes");
	return c;}

	@Override
	public BaseConstraintXml visitSpace(ConstraintStudentsSetMinGapsBetweenBuildingChanges c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.students = getSafeName(p.asStudentGroup("studentgroup"));
			c.minGapsBetweenBuildingChanges = p.asHour("min-gaps");
	return c;}

	@Override
	public BaseConstraintXml visitSpace(ConstraintStudentsMaxBuildingChangesPerDay c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.maxBuildingChangesPerDay = p.asHour("max-building-changes");
	return c;}

	@Override
	public BaseConstraintXml visitSpace(ConstraintStudentsMaxBuildingChangesPerWeek c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.maxBuildingChangesPerWeek = p.asInteger("max-building-changes");
	return c;}

	@Override
	public BaseConstraintXml visitSpace(ConstraintStudentsMinGapsBetweenBuildingChanges c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.minGapsBetweenBuildingChanges = p.asHour("min-gaps");
	return c;}

	//----- Subjects
	@Override
	public BaseConstraintXml visitSpace(ConstraintSubjectPreferredRoom c, ConstraintAttributes p) {
		fillDefault(c, p);
			c.subject = getSafeName(p.asSubject("subject"));
			c.room = getSafeName(p.asRoom("room"));
	return c;}

	@Override
	public BaseConstraintXml visitSpace(ConstraintSubjectPreferredRooms c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.subject = getSafeName(p.asSubject("subject"));
		c.setPreferredRooms(toList(p.asRoomsSet("rooms"), ConstraintSetter::getSafeName));
		return c;
	}

	//----- Activity tags
	@Override
	public BaseConstraintXml visitSpace(ConstraintActivityTagPreferredRoom c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.room = getSafeName(p.asRoom("room"));
		c.activityTag = getSafeName(p.asTag("tag"));
		return c;
	}

	@Override
	public BaseConstraintXml visitSpace(ConstraintActivityTagPreferredRooms c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setPreferredRooms(toList(p.asRoomsSet("rooms"), ConstraintSetter::getSafeName));
		c.activityTag = getSafeName(p.asTag("tag"));
		return c;
	}

	//----- Subjects and activity tags
	@Override
	public BaseConstraintXml visitSpace(ConstraintSubjectActivityTagPreferredRoom c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.activityTag = getSafeName(p.asTag("tag"));
		c.room = getSafeName(p.asRoom("room"));
		c.subject = getSafeName(p.asSubject("subject"));
		return c;
	}

	@Override
	public BaseConstraintXml visitSpace(ConstraintSubjectActivityTagPreferredRooms c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.subject = getSafeName(p.asSubject("subject"));
		c.setRooms(toList(p.asRoomsSet("rooms"), ConstraintSetter::getSafeName));
		c.activityTag = getSafeName(p.asTag("tag"));
		return c;
	}

	//----- Activities
	@Override
	public BaseConstraintXml visitSpace(ConstraintActivityPreferredRoom c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActivityId(getAssgId(p.asAssignment("assignment")));
			c.setRoom(getSafeName(p.asRoom("room")));
			c.setLocked(p.asBoolean("locked"));
	return c;}

	@Override
	public BaseConstraintXml visitSpace(ConstraintActivityPreferredRooms c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.activityId = getAssgId(p.asAssignment("assignment"));
		c.setPreferredRooms(toList(p.asRoomsSet("rooms"), ConstraintSetter::getSafeName));
		return c;
	}

	@Override
	public BaseConstraintXml visitSpace(ConstraintActivitiesSameRoomIfConsecutive c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActivityIds(toList(p.asAssignmentsSet("assignments"), this::getAssgId));
		return c;
	}

	@Override
	public BaseConstraintXml visitSpace(ConstraintActivitiesOccupyMaxDifferentRooms c, ConstraintAttributes p) {
		fillDefault(c, p);
		c.setActivityIds(toList(p.asAssignmentsSet("assignments"), this::getAssgId));
		c.maxNrOfDifferentRooms = p.asInteger("max-diff-rooms");
		return c;
	}

	//------------------- helpers --------------------------------------------------------------------------------------
	private void fillDefault(BaseConstraintXml c, ConstraintAttributes attrs) {
		c.setWeight(attrs.asDouble("weight-percentage"));
	}
	
	private static BreakTimeXml toBreakTimeXml(Tuple<Day, Hour> x) {
		return new BreakTimeXml(getSafeName(x.getFirst()), getSafeName(x.getSecond()));
	}
	
	private static SelectedTimeXml toSelectedTimeXml(Tuple<Day, Hour> x) {
		return new SelectedTimeXml(getSafeName(x.getFirst()), getSafeName(x.getSecond()));
	}
	
	private static PreferredStartingTimeXml toPreferredStartingTimeXml(Tuple<Day, Hour> x) {
		return new PreferredStartingTimeXml(getSafeName(x.getFirst()), getSafeName(x.getSecond()));
	}

	private static PreferredTimeXml toPreferredTimeXml(Tuple<Day, Hour> x) {
		return new PreferredTimeXml(getSafeName(x.getFirst()), getSafeName(x.getSecond()));
	}
	
	private static <T1, T2> List<T2> toList(Iterable<T1> input, Function<T1, T2> f) {
		List<T2> res = new LinkedList<>();
		input.forEach(t1 -> res.add(f.apply(t1)));
		return res;
	}

	private static String getSafeName(Nameable nameable) {
		if (nameable == null) {
			return "";
		}
		String value = nameable.getName().getValue();
		return value == null ? "" : value;
	}
	
	private Long getAssgId(Assignment assg) {
		return getAssgIdFunc.apply(assg);
	}

}
