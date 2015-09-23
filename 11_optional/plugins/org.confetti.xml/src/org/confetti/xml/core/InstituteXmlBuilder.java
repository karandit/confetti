package org.confetti.xml.core;

import static java.util.Optional.of;
import static java.util.stream.Collectors.toList;
import static org.confetti.xml.core.BaseConstraintXml.newXmlConstraint;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.confetti.core.Assignment;
import org.confetti.core.AssignmentGroup;
import org.confetti.core.Constraint;
import org.confetti.core.ConstraintAttributes;
import org.confetti.core.DataProvider;
import org.confetti.core.NameableVisitee;
import org.confetti.dataprovider.xml.AssignmentGroupImpl;
import org.confetti.util.Tuple;

/**
 * @author Kárándi Tamás
 */
public class InstituteXmlBuilder {
	
	//--------------------------- Fields -------------------------------------------------------------------------------
	private final NameGetter nameGetter;
	private final Function<NameableVisitee, String> GET_NAME;
	private final Function<Assignment, Long> GET_ASSG_ID;
	private Function<Constraint, ConstraintAttributes> GET_CONSTR_ATTRS;
	
	//--------------------------- Constructors -------------------------------------------------------------------------
	public InstituteXmlBuilder(NameGetter nameGetter, Function<Assignment, Long> getAssgIdFunc) {
		this.nameGetter = nameGetter;
		this.GET_NAME = nameable -> nameable.accept(nameGetter, null);
		this.GET_ASSG_ID = getAssgIdFunc;
		this.GET_CONSTR_ATTRS=  constr -> constr.getAttributes().getValue();
	}

	public InstituteXmlBuilder updateConstraint(Constraint constraint, ConstraintAttributes attrs) {
		this.GET_CONSTR_ATTRS=  constr -> constr == constraint ? attrs : constr.getAttributes().getValue();
		return this;
	}
	
	public InstituteXml build(DataProvider dp) {
		return buildWithAssignmentMap(dp).getFirst();
	}
	
	public Tuple<InstituteXml, List<Tuple<Long, Assignment>>> buildWithAssignmentMap(DataProvider dp) {
	
		InstituteXml inst = new InstituteXml(dp.getName().getValue(), "5.22.0", dp.getComment().getValue());
		
		//Transforming Subjects, Teachers, StudentGroups, Buildings, Rooms, Days, Hours, Tags for FET
		inst.setSubjects(dp.getSubjects()			.toList(GET_NAME.andThen(SubjectXml::new)));
		inst.setTeachers(dp.getTeachers()			.toList(GET_NAME.andThen(TeacherXml::new)));
		inst.setYears(dp.getStudentGroups()			.toList(sg -> new YearXml(GET_NAME.apply(sg), sg)));
		inst.setBuildings(dp.getBuildings()			.toList(GET_NAME.andThen(BuildingXml::new)));
		inst.setRooms(dp.getRooms()					.toList(room -> new RoomXml(
																GET_NAME.apply(room),
																room.getBuilding().getValue().map(GET_NAME).orElse(""),
																room.getCapacity().getValue())));
		inst.setDays(new DaysXml(dp.getDays()		.toList(GET_NAME.andThen(DayXml::new))));
		inst.setHours(new HoursXml(dp.getHours()	.toList(GET_NAME.andThen(HourXml::new))));
		inst.setActivityTags(dp.getTags()			.toList(GET_NAME.andThen(ActivityTagXml::new)));
		
		//Transforming Assignments for FET and saving the newly assigned ids for further look up
		Map<Assignment, Long> assgIds = new HashMap<>();
		List<Tuple<Long, Assignment>> tuples = new LinkedList<>();
		for (Assignment assignment : dp.getAssignments().getList()) {
			long assgId = GET_ASSG_ID.apply(assignment);
			tuples.add(new Tuple<>(assgId, assignment));
			assgIds.put(assignment, assgId);
		}
		inst.setActivities(tuples.stream().map(this::createActivityXml).collect(toList()));

		//Transforming Constraints for FET
		ConstraintSetter setter = new ConstraintSetter(nameGetter, assg -> assgIds.get(assg));
		dp.getConstraints().getList().forEach(constr ->
			newXmlConstraint(inst, constr.getConstraintType(), GET_CONSTR_ATTRS.apply(constr), setter));

		return new Tuple<>(inst, tuples);
	}
	
	//---------------------------- Helper methods ----------------------------------------------------------------------
	private ActivityXml createActivityXml(Tuple<Long, Assignment> tuple) {
		Long id = tuple.getFirst(); 
		Assignment assg = tuple.getSecond();
		int duration = assg.getDuration().getValue();
		long activityGroupId = assg.getGroup().getValue()
				.flatMap((AssignmentGroup x) -> of(((AssignmentGroupImpl) x).getId()))
				.orElse(0);
		int totalDuration = assg.getGroup().getValue()
				.flatMap(assGroup -> of(assGroup.getAssignments().stream()
					.map(ass -> ass.getDuration().getValue())
					.reduce(0, (a,b) -> a + b)))
				.orElse(duration); 
		
		return new ActivityXml(id, duration, activityGroupId, totalDuration,
			GET_NAME.apply(assg.getSubject()), 
      		assg.getTeachers().toList(GET_NAME),
      		assg.getStudentGroups().toList(GET_NAME),
      		assg.getTags().toList(GET_NAME));
	}

}
