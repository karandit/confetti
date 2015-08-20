package org.confetti.xml.core;

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

public enum GetConstraintTypeVisitor implements ConstraintXmlVisitor<String, Object>{
	INSTANCE;

	//------------------------ Space -----------------------------------------------------------------------------------
	@Override
	public String visitSpace(ConstraintActivitiesOccupyMaxDifferentRooms c, Object p) {
		return "space.SomeActivitiesOccupyMaxDifferentRooms";
	}

	@Override
	public String visitSpace(ConstraintActivitiesSameRoomIfConsecutive c, Object p) {
		return "space.SomeActivitiesAreInTheSameRoomIfTheyAreConsecutive";
	}

	@Override
	public String visitSpace(ConstraintActivityPreferredRoom c, Object p) {
		return "space.ActivityHasAPreferredRoom";
	}

	@Override
	public String visitSpace(ConstraintActivityPreferredRooms c, Object p) {
		return "space.ActivityHasSomePreferredRooms";
	}

	@Override
	public String visitSpace(ConstraintActivityTagPreferredRoom c, Object p) {
		return "space.ActivityTagHasAPreferredRoom";
	}

	@Override
	public String visitSpace(ConstraintActivityTagPreferredRooms c, Object p) {
		return "space.ActivityTagHasSomePreferredRooms";
	}

	@Override
	public String visitSpace(ConstraintBasicCompulsorySpace c, Object p) {
		return "space.BasicCompulsorySpace";
	}

	@Override
	public String visitSpace(ConstraintRoomNotAvailableTimes c, Object p) {
		return "space.NotAvailableTimesForARoom";
	}

	@Override
	public String visitSpace(ConstraintStudentsMaxBuildingChangesPerDay c, Object p) {
		return "space.MaxBuildingChangesPerDayForAllStudentGroups";
	}

	@Override
	public String visitSpace(ConstraintStudentsMaxBuildingChangesPerWeek c, Object p) {
		return "space.MaxBuildingChangesPerWeekForAllStudentGroups";
	}

	@Override
	public String visitSpace(ConstraintStudentsMinGapsBetweenBuildingChanges c, Object p) {
		return "space.MinGapsBetweenBuildingChangesForAllStudentGroups";
	}

	@Override
	public String visitSpace(ConstraintStudentsSetHomeRoom c, Object p) {
		return "space.StudentGroupHasAHomeRoom";
	}

	@Override
	public String visitSpace(ConstraintStudentsSetHomeRooms c, Object p) {
		return "space.StudentGroupHasSomeHomeRooms";
	}

	@Override
	public String visitSpace(ConstraintStudentsSetMaxBuildingChangesPerDay c, Object p) {
		return "space.MaxBuildingChangesPerDayForAStudentGroup";
	}

	@Override
	public String visitSpace(ConstraintStudentsSetMaxBuildingChangesPerWeek c, Object p) {
		return "space.MaxBuildingChangesPerWeekForAStudentGroup";
	}

	@Override
	public String visitSpace(ConstraintStudentsSetMinGapsBetweenBuildingChanges c, Object p) {
		return "space.MinGapsBetweenBuildingChangesForAStudentGroup";
	}

	@Override
	public String visitSpace(ConstraintSubjectActivityTagPreferredRoom c, Object p) {
		return "space.SubjectAndActivityTagHaveAPreferredRoom";
	}

	@Override
	public String visitSpace(ConstraintSubjectActivityTagPreferredRooms c, Object p) {
		return "space.SubjectAndActivityTagHaveSomePreferredRooms";
	}

	@Override
	public String visitSpace(ConstraintSubjectPreferredRoom c, Object p) {
		return "space.SubjectHasAPreferredRoom";
	}

	@Override
	public String visitSpace(ConstraintSubjectPreferredRooms c, Object p) {
		return "space.SubjectHasSomePreferredRooms";
	}

	@Override
	public String visitSpace(ConstraintTeacherHomeRoom c, Object p) {
		return "space.TeacherHasAHomeRoom";
	}

	@Override
	public String visitSpace(ConstraintTeacherHomeRooms c, Object p) {
		return "space.TeacherHasSomeHomeRooms";
	}

	@Override
	public String visitSpace(ConstraintTeacherMaxBuildingChangesPerDay c, Object p) {
		return "space.MaxBuildingChangesPerDayForATeacher";
	}

	@Override
	public String visitSpace(ConstraintTeacherMaxBuildingChangesPerWeek c, Object p) {
		return "space.MaxBuildingChangesPerWeekForATeacher";
	}

	@Override
	public String visitSpace(ConstraintTeacherMinGapsBetweenBuildingChanges c, Object p) {
		return "space.MinGapsBetweenBuildingChangesForATeacher";
	}

	@Override
	public String visitSpace(ConstraintTeachersMaxBuildingChangesPerDay c, Object p) {
		return "space.MaxBuildingChangesPerDayForAllTeachers";
	}

	@Override
	public String visitSpace(ConstraintTeachersMaxBuildingChangesPerWeek c, Object p) {
		return "space.MaxBuildingChangesPerWeekForAllTeachers";
	}

	@Override
	public String visitSpace(ConstraintTeachersMinGapsBetweenBuildingChanges c, Object p) {
		return "space.MinGapsBetweenBuildingChangesForAllTeachers";
	}
	//------------------------ Time Misc -------------------------------------------------------------------------------
	@Override
	public String visitTime(ConstraintBasicCompulsoryTime c, Object p) {
		return "time.BasicCompulsoryTime";
	}

	@Override
	public String visitTime(ConstraintBreakTimes c, Object p) {
		return "time.BreakTimes";
	}

	//------------------------ Time - One Teacher ----------------------------------------------------------------------
	@Override
	public String visitTime(ConstraintTeacherNotAvailableTimes c, Object p) {
		return "time.NotAvailableTimesForATeacher";
	}

	@Override
	public String visitTime(ConstraintTeacherMaxDaysPerWeek c, Object p) {
		return "time.MaxDaysPerWeekForATeacher";
	}

	@Override
	public String visitTime(ConstraintTeacherMinDaysPerWeek c, Object p) {
		return "time.MinDaysPerWeekForATeacher";
	}

	@Override
	public String visitTime(ConstraintTeacherMaxGapsPerDay c, Object p) {
		return "time.MaxGapsPerDayForATeacher";
	}

	@Override
	public String visitTime(ConstraintTeacherMaxGapsPerWeek c, Object p) {
		return "time.MaxGapsPerWeekForATeacher";
	}

	@Override
	public String visitTime(ConstraintTeacherMaxHoursDaily c, Object p) {
		return "time.MaxHoursPerDayForATeacher";
	}

	@Override
	public String visitTime(ConstraintTeacherActivityTagMaxHoursDaily c, Object p) {
		return "time.MaxHoursPerDayWithAnActivityTagForATeacher";
	}

	@Override
	public String visitTime(ConstraintTeacherMinHoursDaily c, Object p) {
		return "time.MinHoursPerDayForATeacher";
	}

	@Override
	public String visitTime(ConstraintTeacherMaxHoursContinuously c, Object p) {
		return "time.MaxHoursContinuouslyForATeacher";
	}

	@Override
	public String visitTime(ConstraintTeacherActivityTagMaxHoursContinuously c, Object p) {
		return "time.MaxHoursContinuouslyWithAnActivityTagForATeacher";
	}

	@Override
	public String visitTime(ConstraintTeacherIntervalMaxDaysPerWeek c, Object p) {
		return "time.HourlyIntervalMaxDaysPerWeekForATeacher";
	}

	//------------------------ Time - All Teachers ---------------------------------------------------------------------
	@Override
	public String visitTime(ConstraintTeachersMaxDaysPerWeek c, Object p) {
		return "time.MaxDaysPerWeekForAllTeachers";
	}

	@Override
	public String visitTime(ConstraintTeachersMinDaysPerWeek c, Object p) {
		return "time.MinDaysPerWeekForAllTeachers";
	}

	@Override
	public String visitTime(ConstraintTeachersMaxGapsPerDay c, Object p) {
		return "time.MaxGapsPerDayForAllTeachers";
	}

	@Override
	public String visitTime(ConstraintTeachersMaxGapsPerWeek c, Object p) {
		return "time.MaxGapsPerWeekForAllTeachers";
	}
	
	@Override
	public String visitTime(ConstraintTeachersMaxHoursDaily c, Object p) {
		return "time.MaxHoursPerDayForAllTeachers";
	}

	@Override
	public String visitTime(ConstraintTeachersActivityTagMaxHoursDaily c, Object p) {
		return "time.MaxHoursPerDayWithAnActivityTagForAllTeachers";
	}

	@Override
	public String visitTime(ConstraintTeachersMinHoursDaily c, Object p) {
		return "time.MinHoursPerDayForAllTeachers";
	}

	@Override
	public String visitTime(ConstraintTeachersMaxHoursContinuously c, Object p) {
		return "time.MaxHoursContinuouslyForAllTeachers";
	}

	@Override
	public String visitTime(ConstraintTeachersActivityTagMaxHoursContinuously c, Object p) {
		return "time.MaxHoursContinuouslyWithAnActivityTagForAllTeachers";
	}

	@Override
	public String visitTime(ConstraintTeachersIntervalMaxDaysPerWeek c, Object p) {
		return "time.HourlyIntervalMaxDaysPerWeekForAllTeachers";
	}

	//------------------------ Time - One StudentGroup -----------------------------------------------------------------
	@Override
	public String visitTime(ConstraintStudentsSetMaxDaysPerWeek c, Object p) {
		return "time.MaxDaysPerWeekForAStudentGroup";
	}

	@Override
	public String visitTime(ConstraintStudentsSetNotAvailableTimes c, Object p) {
		return "time.NotAvailableTimesForAStudentGroup";
	}

	@Override
	public String visitTime(ConstraintStudentsSetMaxGapsPerDay c, Object p) {
		return "time.MaxGapsPerDayForAStudentGroup";
	}

	@Override
	public String visitTime(ConstraintStudentsSetMaxGapsPerWeek c, Object p) {
		return "time.MaxGapsPerWeekForAStudentGroup";
	}

	@Override
	public String visitTime(ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour c, Object p) {
		return "time.MaxBeginningsAtSecondHourForAStudentGroup";
	}
	
	@Override
	public String visitTime(ConstraintStudentsSetMaxHoursDaily c, Object p) {
		return "time.MaxHoursPerDayForAStudentGroup";
	}

	@Override
	public String visitTime(ConstraintStudentsSetActivityTagMaxHoursDaily c, Object p) {
		return "time.MaxHoursPerDayWithAnActivityTagForAStudentGroup";
	}

	@Override
	public String visitTime(ConstraintStudentsSetMinHoursDaily c, Object p) {
		return "time.MinHoursPerDayForAStudentGroup";
	}

	@Override
	public String visitTime(ConstraintStudentsSetMaxHoursContinuously c, Object p) {
		return "time.MaxHoursContinuouslyForAStudentGroup";
	}
	
	@Override
	public String visitTime(ConstraintStudentsSetActivityTagMaxHoursContinuously c, Object p) {
		return "time.MaxHoursContinuouslyWithAnActivityTagForAStudentGroup";
	}

	@Override
	public String visitTime(ConstraintStudentsSetIntervalMaxDaysPerWeek c, Object p) {
		return "time.HourlyIntervalMaxDaysPerWeekForAStudentGroup";
	}

	//------------------------ Time - All StudentGroups ----------------------------------------------------------------
	@Override
	public String visitTime(ConstraintStudentsMaxDaysPerWeek c, Object p) {
		return "time.MaxDaysPerWeekForAllStudentGroups";
	}

	@Override
	public String visitTime(ConstraintStudentsMaxGapsPerDay c, Object p) {
		return "time.MaxGapsPerDayForAllStudentGroups";
	}

	@Override
	public String visitTime(ConstraintStudentsMaxGapsPerWeek c, Object p) {
		return "time.MaxGapsPerWeekForAllStudentGroups";
	}

	@Override
	public String visitTime(ConstraintStudentsEarlyMaxBeginningsAtSecondHour c, Object p) {
		return "time.MaxBeginningsAtSecondHourForAllStudentGroups";
	}

	@Override
	public String visitTime(ConstraintStudentsMaxHoursDaily c, Object p) {
		return "time.MaxHoursPerDayForAllStudentGroups";
	}

	@Override
	public String visitTime(ConstraintStudentsActivityTagMaxHoursDaily c, Object p) {
		return "time.MaxHoursPerDayWithAnActivityTagForAllStudentGroups";
	}

	@Override
	public String visitTime(ConstraintStudentsMinHoursDaily c, Object p) {
		return "time.MinHoursPerDayForAllStudentGroups";
	}

	@Override
	public String visitTime(ConstraintStudentsMaxHoursContinuously c, Object p) {
		return "time.MaxHoursContinuouslyForAllStudentGroups";
	}
	
	@Override
	public String visitTime(ConstraintStudentsActivityTagMaxHoursContinuously c, Object p) {
		return "time.MaxHoursContinuouslyWithAnActivityTagForAllStudentGroups";
	}

	@Override
	public String visitTime(ConstraintStudentsIntervalMaxDaysPerWeek c, Object p) {
		return "time.HourlyIntervalMaxDaysPerWeekForAllStudentGroups";
	}

	//------------------------ Time - Activities -----------------------------------------------------------------------
	@Override
	public String visitTime(ConstraintActivityPreferredStartingTime c, Object p) {
		return "time.ActivityHasAPreferredStartingTime";
	}

	@Override
	public String visitTime(ConstraintActivityPreferredStartingTimes c, Object p) {
		return "time.ActivityHasSomePreferredStartingTimes";
	}
	
	@Override
	public String visitTime(ConstraintActivityPreferredTimeSlots c, Object p) {
		return "time.ActivityHasSomePreferredTimeSlots";
	}
	
	@Override
	public String visitTime(ConstraintActivitiesPreferredStartingTimes c, Object p) {
		return "time.MoreActivitiesHaveSomePreferredStartingTimes";
	}

	@Override
	public String visitTime(ConstraintActivitiesPreferredTimeSlots c, Object p) {
		return "time.MoreActivitiesHaveSomePreferredTimeSlots";
	}

	@Override
	public String visitTime(ConstraintSubactivitiesPreferredStartingTimes c, Object p) {
		return "time.MoreSubActivitiesHaveSomePreferredStartingTimes";
	}

	@Override
	public String visitTime(ConstraintSubactivitiesPreferredTimeSlots c, Object p) {
		return "time.MoreSubActivitiesHaveSomePreferredTimeSlots";
	}

	@Override
	public String visitTime(ConstraintMinDaysBetweenActivities c, Object p) {
		return "time.MinDaysBetweenActivities";
	}
	
	@Override
	public String visitTime(ConstraintMaxDaysBetweenActivities c, Object p) {
		return "time.MaxDaysBetweenActivities";
	}

	@Override
	public String visitTime(ConstraintActivityEndsStudentsDay c, Object p) {
		return "time.ActivityEndsStudentsDay";
	}

	@Override
	public String visitTime(ConstraintActivitiesEndStudentsDay c, Object p) {
		return "time.MoreActivitiesEndStudentsDay";
	}

	@Override
	public String visitTime(ConstraintActivitiesSameStartingTime c, Object p) {
		return "time.MoreActivitiesHaveSameStartingTime";
	}

	@Override
	public String visitTime(ConstraintActivitiesSameStartingDay c, Object p) {
		return "time.MoreActivitiesHaveSameStartingDay";
	}
	
	@Override
	public String visitTime(ConstraintActivitiesSameStartingHour c, Object p) {
		return "time.MoreActivitiesHaveSameStartingHour";
	}
	
	@Override
	public String visitTime(ConstraintActivitiesOccupyMaxTimeSlotsFromSelection c, Object p) {
		return "time.MoreActivitiesOccupyMaxTimeSlotsFromSelection";
	}

	@Override
	public String visitTime(ConstraintTwoActivitiesOrdered c, Object p) {
		return "time.TwoActivitiesAreOrdered";
	}

	@Override
	public String visitTime(ConstraintTwoActivitiesConsecutive c, Object p) {
		return "time.TwoActivitiesAreConsecutive";
	}
	
	@Override
	public String visitTime(ConstraintTwoActivitiesGrouped c, Object p) {
		return "time.TwoActivitiesAreGrouped";
	}

	@Override
	public String visitTime(ConstraintThreeActivitiesGrouped c, Object p) {
		return "time.ThreeActivitiesAreGrouped";
	}

	@Override
	public String visitTime(ConstraintActivitiesNotOverlapping c, Object p) {
		return "time.MoreActivitiesAreNotOverlapping";
	}

	@Override
	public String visitTime(ConstraintActivitiesMaxSimultaneousInSelectedTimeSlots c, Object p) {
		return "time.MaxSimultaneousActivitiesFromASetInTimeSlots";
	}

	@Override
	public String visitTime(ConstraintMinGapsBetweenActivities c, Object p) {
		return "time.MinGapsBetweenActivities";
	}

}
