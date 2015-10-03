package org.confetti.fet.xml.core.time;

import javax.xml.bind.annotation.XmlTransient;

import org.confetti.fet.xml.core.BaseConstraintXml;
import org.confetti.fet.xml.core.time.activities.ConstraintActivitiesEndStudentsDay;
import org.confetti.fet.xml.core.time.activities.ConstraintActivitiesMaxSimultaneousInSelectedTimeSlots;
import org.confetti.fet.xml.core.time.activities.ConstraintActivitiesNotOverlapping;
import org.confetti.fet.xml.core.time.activities.ConstraintActivitiesOccupyMaxTimeSlotsFromSelection;
import org.confetti.fet.xml.core.time.activities.ConstraintActivitiesPreferredStartingTimes;
import org.confetti.fet.xml.core.time.activities.ConstraintActivitiesPreferredTimeSlots;
import org.confetti.fet.xml.core.time.activities.ConstraintActivitiesSameStartingDay;
import org.confetti.fet.xml.core.time.activities.ConstraintActivitiesSameStartingHour;
import org.confetti.fet.xml.core.time.activities.ConstraintActivitiesSameStartingTime;
import org.confetti.fet.xml.core.time.activities.ConstraintActivityEndsStudentsDay;
import org.confetti.fet.xml.core.time.activities.ConstraintActivityPreferredStartingTime;
import org.confetti.fet.xml.core.time.activities.ConstraintActivityPreferredStartingTimes;
import org.confetti.fet.xml.core.time.activities.ConstraintActivityPreferredTimeSlots;
import org.confetti.fet.xml.core.time.activities.ConstraintMaxDaysBetweenActivities;
import org.confetti.fet.xml.core.time.activities.ConstraintMinDaysBetweenActivities;
import org.confetti.fet.xml.core.time.activities.ConstraintMinGapsBetweenActivities;
import org.confetti.fet.xml.core.time.activities.ConstraintSubactivitiesPreferredStartingTimes;
import org.confetti.fet.xml.core.time.activities.ConstraintSubactivitiesPreferredTimeSlots;
import org.confetti.fet.xml.core.time.activities.ConstraintThreeActivitiesGrouped;
import org.confetti.fet.xml.core.time.activities.ConstraintTwoActivitiesConsecutive;
import org.confetti.fet.xml.core.time.activities.ConstraintTwoActivitiesGrouped;
import org.confetti.fet.xml.core.time.activities.ConstraintTwoActivitiesOrdered;
import org.confetti.fet.xml.core.time.misc.ConstraintBasicCompulsoryTime;
import org.confetti.fet.xml.core.time.misc.ConstraintBreakTimes;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsActivityTagMaxHoursContinuously;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsActivityTagMaxHoursDaily;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsEarlyMaxBeginningsAtSecondHour;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsIntervalMaxDaysPerWeek;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsMaxDaysPerWeek;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsMaxGapsPerDay;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsMaxGapsPerWeek;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsMaxHoursContinuously;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsMaxHoursDaily;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsMinHoursDaily;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsSetActivityTagMaxHoursContinuously;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsSetActivityTagMaxHoursDaily;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsSetIntervalMaxDaysPerWeek;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsSetMaxDaysPerWeek;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsSetMaxGapsPerDay;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsSetMaxGapsPerWeek;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsSetMaxHoursContinuously;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsSetMaxHoursDaily;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsSetMinHoursDaily;
import org.confetti.fet.xml.core.time.students.ConstraintStudentsSetNotAvailableTimes;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeacherActivityTagMaxHoursContinuously;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeacherActivityTagMaxHoursDaily;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeacherIntervalMaxDaysPerWeek;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeacherMaxDaysPerWeek;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeacherMaxGapsPerDay;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeacherMaxGapsPerWeek;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeacherMaxHoursContinuously;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeacherMaxHoursDaily;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeacherMinDaysPerWeek;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeacherMinHoursDaily;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeacherNotAvailableTimes;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeachersActivityTagMaxHoursContinuously;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeachersActivityTagMaxHoursDaily;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeachersIntervalMaxDaysPerWeek;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeachersMaxDaysPerWeek;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeachersMaxGapsPerDay;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeachersMaxGapsPerWeek;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeachersMaxHoursContinuously;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeachersMaxHoursDaily;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeachersMinDaysPerWeek;
import org.confetti.fet.xml.core.time.teachers.ConstraintTeachersMinHoursDaily;

/**
 * @author Bubla Gabor
 */
@XmlTransient
public abstract class TimeConstraint extends BaseConstraintXml {

	public static TimeConstraint newTimeXmlConstraint(final String shortType) {
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

}
