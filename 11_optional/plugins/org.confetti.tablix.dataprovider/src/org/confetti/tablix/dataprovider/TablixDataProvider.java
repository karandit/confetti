package org.confetti.tablix.dataprovider;

import static java.util.Optional.ofNullable;
import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;

import java.io.File;

import org.confetti.core.Assignment;
import org.confetti.core.Building;
import org.confetti.core.Constraint;
import org.confetti.core.DataProvider;
import org.confetti.core.Day;
import org.confetti.core.Hour;
import org.confetti.core.Room;
import org.confetti.core.SolutionSlot;
import org.confetti.core.StudentGroup;
import org.confetti.core.Subject;
import org.confetti.core.Tag;
import org.confetti.core.Teacher;
import org.confetti.observable.ListMutator;
import org.confetti.observable.ObservableList;
import org.confetti.observable.ObservableValue;
import org.confetti.observable.ValueMutator;
import org.confetti.tablix.xml.TablixXml;

/**
 * @author Kárándi Tamás
 */
public class TablixDataProvider implements DataProvider {
	
	private static final String[] DAY_NAMES = new String[] {
			"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
	
	//----------------------------- fields for UI client----------------------------------------------------------------
	private final ValueMutator<String> name = new ValueMutator<>();
	private final ValueMutator<String> comment = new ValueMutator<>();
	private final ListMutator<Teacher> teachers = new ListMutator<>();
	private final ListMutator<Subject> subjects = new ListMutator<>();
	private final ListMutator<StudentGroup> stdGroups = new ListMutator<>();
	private final ListMutator<Room> rooms = new ListMutator<>();
	private final ListMutator<Building> buildings = new ListMutator<>();
	private final ListMutator<Day> days = new ListMutator<>();
	private final ListMutator<Hour> hours = new ListMutator<>();
	private final ListMutator<Assignment> assignments = new ListMutator<>();
	private final ListMutator<Constraint> constraints = new ListMutator<>();
	private final ListMutator<Tag> tags = new ListMutator<>();
	private final ValueMutator<Iterable<SolutionSlot>> solution = new ValueMutator<>();

	//----------------------------- fields for xml persistence ---------------------------------------------------------
    private final File file;
    
	//----------------------------- constructors -----------------------------------------------------------------------
	public TablixDataProvider(TablixXml xml, File file) {
        this.file = file;
        this.name.setValue(this, ofNullable(xml.getInfo()).flatMap(info -> ofNullable(info.getTitle())).orElse(""));
        this.comment.setValue(this, ofNullable(xml.getInfo()).flatMap(info -> ofNullable(info.getAuthor())).orElse(""));
        xml.getResources().getConstantTypes().stream()
    	.filter(resType -> "teacher".equals(resType.getType()))
    	.forEach(resType -> {
    		resType.getResourceDefs().stream()
    		.map(res -> res.getName())
    		.map(TablixTeacher::new)
    		.forEach(this.teachers::addItem);
    	});
        xml.getResources().getConstantTypes().stream()
    	.filter(resType -> "class".equals(resType.getType()))
    	.forEach(resType -> {
    		resType.getResourceDefs().stream() 
    		.map(res -> res.getName()) 
    		.map(TablixStudentGroup::new) 
    		.forEach(this.stdGroups::addItem);
    	});
        xml.getResources().getVariableTypes().stream()
    	.filter(resType -> "time".equals(resType.getType()))
        .forEach(resType -> {
    		resType.getMatrices().stream().findFirst()
    		.ifPresent(mat -> {
    			range(0, mat.getWidth())
        			.mapToObj(x -> DAY_NAMES[x % DAY_NAMES.length])
        			.map(TablixDay::new)
        			.forEach(this.days::addItem);
    			rangeClosed(1, mat.getHeight())
    				.mapToObj(Integer::toString)
    				.map(TablixHour::new)
    				.forEach(this.hours::addItem);
    		});
        });
        //TODO: handle the # placeholders
        //TODO: process not only the linears
        xml.getResources().getVariableTypes().stream()
    	.filter(resType -> "room".equals(resType.getType()))
        .forEach(resType -> {
    		resType.getLinears().stream()
			.map(lin -> lin.getName())
			.map(TablixRoom::new)
			.forEach(this.rooms::addItem);
        });
        //TODO: process the events
	}

	//----------------------------- DataProvider's API -----------------------------------------------------------------
	@Override public String getInformation()                               { return file.getAbsolutePath(); }
	@Override public ObservableValue<String> getName() 					   { return name.getObservableValue(); }
	@Override public ObservableValue<String> getComment() 				   { return comment.getObservableValue(); }
	@Override public ObservableList<Subject> getSubjects() 				   { return subjects.getObservableList(); }
	@Override public ObservableList<Teacher> getTeachers() 				   { return teachers.getObservableList(); }
	@Override public ObservableList<StudentGroup> getStudentGroups() 	   { return stdGroups.getObservableList(); }
	@Override public ObservableList<Room> getRooms() 					   { return rooms.getObservableList(); }
	@Override public ObservableList<Building> getBuildings() 			   { return buildings.getObservableList(); }
	@Override public ObservableList<Day> getDays() 						   { return days.getObservableList(); }
	@Override public ObservableList<Hour> getHours() 				       { return hours.getObservableList(); }
	@Override public ObservableList<Assignment> getAssignments() 		   { return assignments.getObservableList(); }
	@Override public ObservableList<Constraint> getConstraints() 		   { return constraints.getObservableList(); }
	@Override public ObservableList<Tag> getTags() 						   { return tags.getObservableList(); }
	@Override public ObservableValue<Iterable<SolutionSlot>> getSolution() { return solution.getObservableValue(); }
	
}
