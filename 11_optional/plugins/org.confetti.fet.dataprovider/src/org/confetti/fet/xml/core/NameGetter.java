package org.confetti.fet.xml.core;

import static java.util.function.Function.identity;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.confetti.core.Building;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Nameable;
import org.confetti.core.NameableVisitor;
import org.confetti.core.Room;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Tag;
import org.confetti.core.Teacher;

/**
 * @author Kárándi Tamás
 */
public class NameGetter implements NameableVisitor<String, Void> {

	//--------------------------- Enums --------------------------------------------------------------------------------
	private enum Renames {
		Day,
		Hour,
		Subject,
		Teacher,
		StudGroup,
		Room,
		Building,
		Tag;
	}
	
	//--------------------------- Fields -------------------------------------------------------------------------------
	private Map<Renames, Function<String, String>> renameFunctions = new HashMap<>();
	
	//--------------------------- Constructors -------------------------------------------------------------------------
	public NameGetter() {
		for(Renames key : Renames.values()) {
			renameFunctions.put(key, identity());
		}
	}

	//--------------------------- NameableVisitor ----------------------------------------------------------------------
	@Override public String visitDay(Day day, Void p) 					{ return getName(Renames.Day, day); }
	@Override public String visitHour(Hour hour, Void p) 				{ return getName(Renames.Hour, hour); }
	@Override public String visitSubject(Subject subj, Void p) 			{ return getName(Renames.Subject, subj); }
	@Override public String visitTeacher(Teacher teacher, Void p) 		{ return getName(Renames.Teacher, teacher); }
	@Override public String visitStudentGroup(StudentGroup sg, Void p) 	{ return getName(Renames.StudGroup, sg); }
	@Override public String visitRoom(Room room, Void p) 				{ return getName(Renames.Room, room); }
	@Override public String visitBuilding(Building building, Void p) 	{ return getName(Renames.Building, building); }
	@Override public String visitTag(Tag tag, Void p) 					{ return getName(Renames.Tag, tag); }

	//--------------------------- renameXXX methods --------------------------------------------------------------------
	public NameGetter renameDay(Day day, String to) 		  		  	{ return rename(Renames.Day, day, to); }
	public NameGetter renameHour(Hour hour, String to) 		  	  		{ return rename(Renames.Hour, hour, to); }
	public NameGetter renameSubject(Subject subject, String to) 		{ return rename(Renames.Subject, subject, to); }
	public NameGetter renameTeacher(Teacher teacher, String to) 	  	{ return rename(Renames.Teacher, teacher, to); }
	public NameGetter renameStudentGroup(StudentGroup sg, String to) 	{ return rename(Renames.StudGroup, sg, to); }
	public NameGetter renameRoom(Room room, String to) 		  	  		{ return rename(Renames.Room, room, to); }
	public NameGetter renameBuilding(Building building, String to)   	{ return rename(Renames.Building, building, to); }
	public NameGetter renameTag(Tag tag, String to) 		  		  	{ return rename(Renames.Tag, tag, to); }

	//--------------------------- helper methods -----------------------------------------------------------------------
	private NameGetter rename(Renames key, Nameable nameable, String newName) {
		String oldName = nameable.getName().getValue();
		renameFunctions.put(key, name -> name.equals(oldName) ? newName : name);
		return this;
	}

	private String getName(Renames key, Nameable nameable) {
		return renameFunctions.get(key).apply(nameable.getName().getValue());
	}

}
