package org.confetti.xml.core;

import java.util.LinkedList;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

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

@XmlRootElement(name = "fet")
@XmlType(name = "institute_type", 
		 propOrder = {"name", "comment", "hours", "days", "years", 
				 	"teachers", "subjects", "activityTags", "activities", "buildings", "rooms", 
				 	"timeConstraints", "spaceConstraints"}
)
public class InstituteXml {

	// --------------- fields ------------------------------------------------------------------------------------------
	private String name;
	private String version;
	private String comment;
	private HoursXml hours;
	private DaysXml days;
	private List<YearXml> years = new LinkedList<>();
	private List<SubjectXml> subjects = new LinkedList<>();
	private List<TeacherXml> teachers = new LinkedList<>();
	private List<BuildingXml> buildings = new LinkedList<>();
	private List<RoomXml> rooms = new LinkedList<>();
	private List<ActivityTagXml> activityTags = new LinkedList<>();
	private List<ActivityXml> activities  = new LinkedList<>();
	private List<TimeConstraint> timeConstraints = new LinkedList<>();
	private List<SpaceConstraint> spaceConstraints = new LinkedList<>();
	
	// --------------- constructors ------------------------------------------------------------------------------------
	
	InstituteXml() {
	}
	
	public InstituteXml(String name, String version, String comment) {
		this.name = name;
		this.version = version;
		this.comment = comment;
	}
	
	// --------------- getters and setters -----------------------------------------------------------------------------
	@XmlAttribute(name = "version")
	public String getVersion() 										{ return version; }
	public void setVersion(String version) 							{ this.version = version; }

	@XmlElement(name = "Institution_Name")
	public String getName() 										{ return name; }
	public void setName(String name) 								{ this.name = name; }

	@XmlElement(name = "Comments")
	public String getComment() 										{ return comment; }
	public void setComment(String comment) 							{ this.comment = comment; }

	@XmlElement(name = "Hours_List")
	public HoursXml getHours() 										{ return hours; }
	public void setHours(HoursXml hours) 							{ this.hours = hours; }
	
	@XmlElement(name = "Days_List")
	public DaysXml getDays() 										{ return days; }
	public void setDays(DaysXml days) 								{ this.days = days; }
	
	@XmlElementWrapper(name = "Students_List")
	@XmlElement(name = "Year")
	public List<YearXml> getYears() 								{ return years; }
	public void setYears(List<YearXml> years)						{ this.years = years; }

	@XmlElementWrapper(name = "Subjects_List")
	@XmlElement(name = "Subject")
	public List<SubjectXml> getSubjects() 							{ return subjects; }
	public void setSubjects(List<SubjectXml> subjects) 				{ this.subjects = subjects; }
	
	@XmlElementWrapper(name = "Teachers_List")
	@XmlElement(name = "Teacher")
	public List<TeacherXml> getTeachers() 							{ return teachers; }
	public void setTeachers(List<TeacherXml> teachers) 				{ this.teachers = teachers; }

	@XmlElementWrapper(name = "Buildings_List")
	@XmlElement(name = "Building")
	public List<BuildingXml> getBuildings() 						{ return buildings; }
	public void setBuildings(List<BuildingXml> buildings)			{ this.buildings = buildings; }

	@XmlElementWrapper(name = "Rooms_List")
	@XmlElement(name = "Room")
	public List<RoomXml> getRooms() 								{ return rooms; }
	public void setRooms(List<RoomXml> rooms) 						{ this.rooms = rooms; }
	
	@XmlElementWrapper(name = "Activity_Tags_List")
	@XmlElement(name = "Activity_Tag")
	public List<ActivityTagXml> getActivityTags() 					{ return activityTags; }
	public void setActivityTags(List<ActivityTagXml> activityTags) 	{ this.activityTags = activityTags; }

	@XmlElementWrapper(name = "Activities_List")
	@XmlElement(name = "Activity")
	public List<ActivityXml> getActivities() 						{ return activities; }
	public void setActivities(List<ActivityXml> activities) 		{ this.activities = activities; }
	
	@XmlElementWrapper(name = "Time_Constraints_List")
	@XmlElements({
		@XmlElement(name = "ConstraintBasicCompulsoryTime", type = ConstraintBasicCompulsoryTime.class),
		@XmlElement(name = "ConstraintTwoActivitiesConsecutive", type = ConstraintTwoActivitiesConsecutive.class),
		@XmlElement(name = "ConstraintActivityPreferredStartingTime", type = ConstraintActivityPreferredStartingTime.class),
		@XmlElement(name = "ConstraintActivitiesPreferredStartingTimes", type = ConstraintActivitiesPreferredStartingTimes.class),
		@XmlElement(name = "ConstraintActivityPreferredStartingTimes", type = ConstraintActivityPreferredStartingTimes.class),
		@XmlElement(name = "ConstraintBreakTimes", type = ConstraintBreakTimes.class),
		@XmlElement(name = "ConstraintStudentsEarlyMaxBeginningsAtSecondHour", type = ConstraintStudentsEarlyMaxBeginningsAtSecondHour.class),
		@XmlElement(name = "ConstraintStudentsSetActivityTagMaxHoursDaily", type = ConstraintStudentsSetActivityTagMaxHoursDaily.class),
		@XmlElement(name = "ConstraintStudentsSetActivityTagMaxHoursContinuously", type = ConstraintStudentsSetActivityTagMaxHoursContinuously.class),
		@XmlElement(name = "ConstraintStudentsMaxGapsPerWeek", type = ConstraintStudentsMaxGapsPerWeek.class),
		@XmlElement(name = "ConstraintMinDaysBetweenActivities", type = ConstraintMinDaysBetweenActivities.class),
		@XmlElement(name = "ConstraintMaxDaysBetweenActivities", type = ConstraintMaxDaysBetweenActivities.class),
		@XmlElement(name = "ConstraintTeacherNotAvailableTimes", type = ConstraintTeacherNotAvailableTimes.class),
		@XmlElement(name = "ConstraintTeacherMaxDaysPerWeek", type = ConstraintTeacherMaxDaysPerWeek.class),
		@XmlElement(name = "ConstraintTeachersMaxGapsPerWeek", type = ConstraintTeachersMaxGapsPerWeek.class),
		@XmlElement(name = "ConstraintTeacherMaxGapsPerWeek", type = ConstraintTeacherMaxGapsPerWeek.class),
		@XmlElement(name = "ConstraintTeachersMaxGapsPerDay", type = ConstraintTeachersMaxGapsPerDay.class),
		@XmlElement(name = "ConstraintTeachersMaxHoursDaily", type = ConstraintTeachersMaxHoursDaily.class),
		@XmlElement(name = "ConstraintTeachersMinHoursDaily", type = ConstraintTeachersMinHoursDaily.class),
		@XmlElement(name = "ConstraintTeacherMaxHoursContinuously", type = ConstraintTeacherMaxHoursContinuously.class),
		@XmlElement(name = "ConstraintActivitiesSameStartingTime", type = ConstraintActivitiesSameStartingTime.class),
		@XmlElement(name = "ConstraintActivitiesPreferredTimeSlots", type = ConstraintActivitiesPreferredTimeSlots.class),
		@XmlElement(name = "ConstraintStudentsSetNotAvailableTimes", type = ConstraintStudentsSetNotAvailableTimes.class),
		@XmlElement(name = "ConstraintTeacherIntervalMaxDaysPerWeek", type = ConstraintTeacherIntervalMaxDaysPerWeek.class),
		@XmlElement(name = "ConstraintTeacherMinHoursDaily", type = ConstraintTeacherMinHoursDaily.class),
		@XmlElement(name = "ConstraintTeacherMaxGapsPerDay", type = ConstraintTeacherMaxGapsPerDay.class),
		@XmlElement(name = "ConstraintActivityEndsStudentsDay", type = ConstraintActivityEndsStudentsDay.class),
		@XmlElement(name = "ConstraintStudentsSetMinHoursDaily", type = ConstraintStudentsSetMinHoursDaily.class),
		@XmlElement(name = "ConstraintStudentsSetMaxHoursDaily", type = ConstraintStudentsSetMaxHoursDaily.class),
		@XmlElement(name = "ConstraintStudentsSetMaxHoursContinuously", type = ConstraintStudentsSetMaxHoursContinuously.class),
		@XmlElement(name = "ConstraintTeacherMinDaysPerWeek", type = ConstraintTeacherMinDaysPerWeek.class),
		@XmlElement(name = "ConstraintTeacherMaxHoursDaily", type = ConstraintTeacherMaxHoursDaily.class),
		@XmlElement(name = "ConstraintTeachersActivityTagMaxHoursContinuously", type = ConstraintTeachersActivityTagMaxHoursContinuously.class),
		@XmlElement(name = "ConstraintTeachersActivityTagMaxHoursDaily", type = ConstraintTeachersActivityTagMaxHoursDaily.class),
		@XmlElement(name = "ConstraintActivitiesNotOverlapping", type = ConstraintActivitiesNotOverlapping.class),
		@XmlElement(name = "ConstraintTeachersMaxHoursContinuously", type = ConstraintTeachersMaxHoursContinuously.class),
		@XmlElement(name = "ConstraintTeachersMaxDaysPerWeek", type = ConstraintTeachersMaxDaysPerWeek.class),
		@XmlElement(name = "ConstraintStudentsActivityTagMaxHoursContinuously", type = ConstraintStudentsActivityTagMaxHoursContinuously.class),
		@XmlElement(name = "ConstraintStudentsSetMaxGapsPerWeek", type = ConstraintStudentsSetMaxGapsPerWeek.class),
		@XmlElement(name = "ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour", type = ConstraintStudentsSetEarlyMaxBeginningsAtSecondHour.class),
		@XmlElement(name = "ConstraintTeachersIntervalMaxDaysPerWeek", type = ConstraintTeachersIntervalMaxDaysPerWeek.class),
		@XmlElement(name = "ConstraintStudentsIntervalMaxDaysPerWeek", type = ConstraintStudentsIntervalMaxDaysPerWeek.class),
		@XmlElement(name = "ConstraintActivityPreferredTimeSlots", type = ConstraintActivityPreferredTimeSlots.class),
		@XmlElement(name = "ConstraintStudentsMaxHoursDaily", type = ConstraintStudentsMaxHoursDaily.class),
		@XmlElement(name = "ConstraintTwoActivitiesGrouped", type = ConstraintTwoActivitiesGrouped.class),
		@XmlElement(name = "ConstraintThreeActivitiesGrouped", type = ConstraintThreeActivitiesGrouped.class),
		@XmlElement(name = "ConstraintTeacherActivityTagMaxHoursDaily", type = ConstraintTeacherActivityTagMaxHoursDaily.class),
		@XmlElement(name = "ConstraintTeacherActivityTagMaxHoursContinuously", type = ConstraintTeacherActivityTagMaxHoursContinuously.class),
		@XmlElement(name = "ConstraintStudentsMaxGapsPerDay", type = ConstraintStudentsMaxGapsPerDay.class),
		@XmlElement(name = "ConstraintStudentsSetMaxGapsPerDay", type = ConstraintStudentsSetMaxGapsPerDay.class),
		@XmlElement(name = "ConstraintActivitiesSameStartingHour", type = ConstraintActivitiesSameStartingHour.class),
		@XmlElement(name = "ConstraintMinGapsBetweenActivities", type = ConstraintMinGapsBetweenActivities.class),
		@XmlElement(name = "ConstraintTwoActivitiesOrdered", type = ConstraintTwoActivitiesOrdered.class),
		@XmlElement(name = "ConstraintStudentsMaxHoursContinuously", type = ConstraintStudentsMaxHoursContinuously.class),
		@XmlElement(name = "ConstraintActivitiesSameStartingDay", type = ConstraintActivitiesSameStartingDay.class),
		@XmlElement(name = "ConstraintSubactivitiesPreferredTimeSlots", type = ConstraintSubactivitiesPreferredTimeSlots.class),
		@XmlElement(name = "ConstraintStudentsMinHoursDaily", type = ConstraintStudentsMinHoursDaily.class),
		@XmlElement(name = "ConstraintSubactivitiesPreferredStartingTimes", type = ConstraintSubactivitiesPreferredStartingTimes.class),
		@XmlElement(name = "ConstraintActivitiesEndStudentsDay", type = ConstraintActivitiesEndStudentsDay.class),
		@XmlElement(name = "ConstraintStudentsSetIntervalMaxDaysPerWeek", type = ConstraintStudentsSetIntervalMaxDaysPerWeek.class),
		@XmlElement(name = "ConstraintTeachersMinDaysPerWeek", type = ConstraintTeachersMinDaysPerWeek.class),
		@XmlElement(name = "ConstraintStudentsActivityTagMaxHoursDaily", type = ConstraintStudentsActivityTagMaxHoursDaily.class),
		@XmlElement(name = "ConstraintActivitiesOccupyMaxTimeSlotsFromSelection", type = ConstraintActivitiesOccupyMaxTimeSlotsFromSelection.class),
		@XmlElement(name = "ConstraintActivitiesMaxSimultaneousInSelectedTimeSlots", type = ConstraintActivitiesMaxSimultaneousInSelectedTimeSlots.class),
	})
	public List<TimeConstraint> getTimeConstraints() 						{ return timeConstraints; }
	public void setTimeConstraints(List<TimeConstraint> timeConstraints) 	{ this.timeConstraints = timeConstraints; }

	@XmlElementWrapper(name = "Space_Constraints_List")
	@XmlElements( {
		@XmlElement(name = "ConstraintBasicCompulsorySpace", type = ConstraintBasicCompulsorySpace.class),
		@XmlElement(name = "ConstraintActivityPreferredRoom", type = ConstraintActivityPreferredRoom.class),
		@XmlElement(name = "ConstraintRoomNotAvailableTimes", type = ConstraintRoomNotAvailableTimes.class),
		@XmlElement(name = "ConstraintSubjectPreferredRoom", type = ConstraintSubjectPreferredRoom.class),
		@XmlElement(name = "ConstraintSubjectPreferredRooms", type = ConstraintSubjectPreferredRooms.class),
		@XmlElement(name = "ConstraintTeacherHomeRoom", type = ConstraintTeacherHomeRoom.class),
		@XmlElement(name = "ConstraintStudentsSetHomeRoom", type = ConstraintStudentsSetHomeRoom.class),
		@XmlElement(name = "ConstraintActivityTagPreferredRooms", type = ConstraintActivityTagPreferredRooms.class),
		@XmlElement(name = "ConstraintTeachersMaxBuildingChangesPerDay", type = ConstraintTeachersMaxBuildingChangesPerDay.class),
		@XmlElement(name = "ConstraintTeacherMinGapsBetweenBuildingChanges", type = ConstraintTeacherMinGapsBetweenBuildingChanges.class),
		@XmlElement(name = "ConstraintTeachersMinGapsBetweenBuildingChanges", type = ConstraintTeachersMinGapsBetweenBuildingChanges.class),
		@XmlElement(name = "ConstraintStudentsSetMinGapsBetweenBuildingChanges", type = ConstraintStudentsSetMinGapsBetweenBuildingChanges.class),
		@XmlElement(name = "ConstraintSubjectActivityTagPreferredRoom", type = ConstraintSubjectActivityTagPreferredRoom.class),
		@XmlElement(name = "ConstraintSubjectActivityTagPreferredRooms", type = ConstraintSubjectActivityTagPreferredRooms.class),
		@XmlElement(name = "ConstraintTeacherHomeRooms", type = ConstraintTeacherHomeRooms.class),
		@XmlElement(name = "ConstraintActivityPreferredRooms", type = ConstraintActivityPreferredRooms.class),
		@XmlElement(name = "ConstraintActivityTagPreferredRoom", type = ConstraintActivityTagPreferredRoom.class),
		@XmlElement(name = "ConstraintStudentsMaxBuildingChangesPerDay", type = ConstraintStudentsMaxBuildingChangesPerDay.class),
		@XmlElement(name = "ConstraintStudentsMinGapsBetweenBuildingChanges", type = ConstraintStudentsMinGapsBetweenBuildingChanges.class),
		@XmlElement(name = "ConstraintStudentsSetMaxBuildingChangesPerDay", type = ConstraintStudentsSetMaxBuildingChangesPerDay.class),
		@XmlElement(name = "ConstraintStudentsSetMaxBuildingChangesPerWeek", type = ConstraintStudentsSetMaxBuildingChangesPerWeek.class),
		@XmlElement(name = "ConstraintTeachersMaxBuildingChangesPerWeek", type = ConstraintTeachersMaxBuildingChangesPerWeek.class),
		@XmlElement(name = "ConstraintStudentsMaxBuildingChangesPerWeek", type = ConstraintStudentsMaxBuildingChangesPerWeek.class),
		@XmlElement(name = "ConstraintTeacherMaxBuildingChangesPerDay", type = ConstraintTeacherMaxBuildingChangesPerDay.class),
		@XmlElement(name = "ConstraintTeacherMaxBuildingChangesPerWeek", type = ConstraintTeacherMaxBuildingChangesPerWeek.class),
		@XmlElement(name = "ConstraintActivitiesOccupyMaxDifferentRooms", type = ConstraintActivitiesOccupyMaxDifferentRooms.class),
		@XmlElement(name = "ConstraintActivitiesSameRoomIfConsecutive", type = ConstraintActivitiesSameRoomIfConsecutive.class),
		@XmlElement(name = "ConstraintStudentsSetHomeRooms", type = ConstraintStudentsSetHomeRooms.class),
		
	})
	public List<SpaceConstraint> getSpaceConstraints() 						{ return spaceConstraints; }
	public void setSpaceConstraints(List<SpaceConstraint> spaceConstraints) { this.spaceConstraints = spaceConstraints; }

}
