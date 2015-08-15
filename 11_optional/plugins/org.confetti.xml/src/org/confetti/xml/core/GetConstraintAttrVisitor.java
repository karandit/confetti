package org.confetti.xml.core;

import org.confetti.core.ConstraintAttributes;
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
import org.confetti.xml.core.time.students.ConstraintStudentsMaxGapsPerDay;
import org.confetti.xml.core.time.students.ConstraintStudentsMaxGapsPerWeek;
import org.confetti.xml.core.time.students.ConstraintStudentsMaxHoursContinuously;
import org.confetti.xml.core.time.students.ConstraintStudentsMaxHoursDaily;
import org.confetti.xml.core.time.students.ConstraintStudentsMinHoursDaily;
import org.confetti.xml.core.time.students.ConstraintStudentsSetActivityTagMaxHoursContinuously;
import org.confetti.xml.core.time.students.ConstraintStudentsSetActivityTagMaxHoursDaily;
import org.confetti.xml.core.time.students.ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour;
import org.confetti.xml.core.time.students.ConstraintStudentsSetIntervalMaxDaysPerWeek;
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

public enum GetConstraintAttrVisitor implements ConstraintXmlVisitor<ConstraintAttributes, ConstraintAttributes> {
	INSTANCE;

	//------------------------ Space -----------------------------------------------------------------------------------
	@Override
	public ConstraintAttributes visitSpace(ConstraintBasicCompulsorySpace c, ConstraintAttributes p) {
		return fillDefault(c, p)
				.withBoolean("active", c.isActive());
	}

	@Override
	public ConstraintAttributes visitSpace(ConstraintRoomNotAvailableTimes c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public ConstraintAttributes visitSpace(ConstraintActivitiesOccupyMaxDifferentRooms c, ConstraintAttributes p) {
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintActivitiesSameRoomIfConsecutive c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(ConstraintActivityPreferredRoom c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(ConstraintActivityPreferredRooms c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintActivityTagPreferredRoom c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintActivityTagPreferredRooms c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintStudentsMaxBuildingChangesPerDay c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintStudentsMaxBuildingChangesPerWeek c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintStudentsMinGapsBetweenBuildingChanges c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(ConstraintStudentsSetHomeRoom c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(ConstraintStudentsSetHomeRooms c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintStudentsSetMaxBuildingChangesPerDay c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintStudentsSetMaxBuildingChangesPerWeek c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintStudentsSetMinGapsBetweenBuildingChanges c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintSubjectActivityTagPreferredRoom c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintSubjectActivityTagPreferredRooms c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(ConstraintSubjectPreferredRoom c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(ConstraintSubjectPreferredRooms c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(ConstraintTeacherHomeRoom c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(ConstraintTeacherHomeRooms c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintTeacherMaxBuildingChangesPerDay c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintTeacherMaxBuildingChangesPerWeek c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintTeacherMinGapsBetweenBuildingChanges c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintTeachersMaxBuildingChangesPerDay c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintTeachersMaxBuildingChangesPerWeek c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitSpace(
			ConstraintTeachersMinGapsBetweenBuildingChanges c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivitiesEndStudentsDay c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintActivitiesMaxSimultaneousInSelectedTimeSlots c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivitiesNotOverlapping c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintActivitiesOccupyMaxTimeSlotsFromSelection c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintActivitiesPreferredStartingTimes c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintActivitiesPreferredTimeSlots c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintActivitiesSameStartingDay c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintActivitiesSameStartingHour c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintActivitiesSameStartingTime c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintActivityEndsStudentsDay c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintActivityPreferredStartingTime c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintActivityPreferredStartingTimes c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintActivityPreferredTimeSlots c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintBasicCompulsoryTime c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintBreakTimes c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintMaxDaysBetweenActivities c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintMinDaysBetweenActivities c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintMinGapsBetweenActivities c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintStudentsActivityTagMaxHoursContinuously c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintStudentsActivityTagMaxHoursDaily c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintStudentsEarlyMaxBeginningsAtSecondHour c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintStudentsIntervalMaxDaysPerWeek c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsMaxGapsPerDay c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsMaxGapsPerWeek c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintStudentsMaxHoursContinuously c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsMaxHoursDaily c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsMinHoursDaily c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintStudentsSetActivityTagMaxHoursContinuously c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintStudentsSetActivityTagMaxHoursDaily c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintStudentsSetIntervalMaxDaysPerWeek c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetMaxGapsPerDay c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintStudentsSetMaxGapsPerWeek c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintStudentsSetMaxHoursContinuously c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetMaxHoursDaily c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintStudentsSetMinHoursDaily c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintStudentsSetNotAvailableTimes c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintSubactivitiesPreferredStartingTimes c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintSubactivitiesPreferredTimeSlots c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintTeacherActivityTagMaxHoursContinuously c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintTeacherActivityTagMaxHoursDaily c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintTeacherIntervalMaxDaysPerWeek c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMaxDaysPerWeek c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMaxGapsPerDay c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMaxGapsPerWeek c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintTeacherMaxHoursContinuously c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMaxHoursDaily c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMinDaysPerWeek c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherMinHoursDaily c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeacherNotAvailableTimes c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintTeachersActivityTagMaxHoursContinuously c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintTeachersActivityTagMaxHoursDaily c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintTeachersIntervalMaxDaysPerWeek c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMaxDaysPerWeek c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMaxGapsPerDay c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMaxGapsPerWeek c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(
			ConstraintTeachersMaxHoursContinuously c, ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMaxHoursDaily c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMinDaysPerWeek c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTeachersMinHoursDaily c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintThreeActivitiesGrouped c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTwoActivitiesConsecutive c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTwoActivitiesGrouped c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ConstraintAttributes visitTime(ConstraintTwoActivitiesOrdered c,
			ConstraintAttributes p) {
		// TODO Auto-generated method stub
		return null;
	}
	//------------------- helpers --------------------------------------------------------------------------------------
	private static ConstraintAttributes fillDefault(BaseConstraintXml c, ConstraintAttributes p) {
		return p.withDouble("weight-percentage", c.getWeight());
	}

}
