package org.confetti.core;

import java.util.List;

public interface IInstitute extends INameable {

	String getComment();
	List<IHour> getHours();
	List<ISubject> getSubjects();
	List<ITeacher> getTeachers();
	List<IRoom> getRooms();
	List<IActivityTag> getActivityTags();

}
