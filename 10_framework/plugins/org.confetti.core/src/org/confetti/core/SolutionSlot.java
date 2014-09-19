package org.confetti.core;

public class SolutionSlot {

	private final Assignment assignment;
	private final Day day;
	private final Hour hour;
	private final Room room;

	public SolutionSlot(Assignment assignment, Day day, Hour hour, Room room) {
		this.assignment = assignment;
		this.day = day;
		this.hour = hour;
		this.room = room;
	}
	
	public Assignment getAssignment() 	{ return assignment; } 
	public Day getDay() 				{ return day; } 
	public Hour getHour() 				{ return hour; } 
	public Room getRoom() 				{ return room; } 
	
}
