package org.confetti.xml.core.space;

import javax.xml.bind.annotation.XmlTransient;

import org.confetti.xml.core.BaseConstraintXml;
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

/**
 * @author Bubla Gabor
 */
@XmlTransient
public abstract class SpaceConstraint extends BaseConstraintXml {

	public static SpaceConstraint newSpaceXmlConstraint(final String shortType) {
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

}
