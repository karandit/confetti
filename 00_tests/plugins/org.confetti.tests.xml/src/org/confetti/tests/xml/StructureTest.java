package org.confetti.tests.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.confetti.xml.FAOException;
import org.confetti.xml.InstituteFAO;
import org.confetti.xml.core.ActivityTagXml;
import org.confetti.xml.core.ActivityXml;
import org.confetti.xml.core.DaysXml;
import org.confetti.xml.core.GroupXml;
import org.confetti.xml.core.HoursXml;
import org.confetti.xml.core.InstituteXml;
import org.confetti.xml.core.RoomXml;
import org.confetti.xml.core.SubgroupXml;
import org.confetti.xml.core.SubjectXml;
import org.confetti.xml.core.TeacherXml;
import org.confetti.xml.core.YearXml;
import org.confetti.xml.core.space.SpaceConstraint;
import org.confetti.xml.core.time.TimeConstraint;
import org.junit.BeforeClass;
import org.junit.Test;

public class StructureTest {
	
	private static InstituteXml inst;
	
	@BeforeClass
	public static void setUp() throws Exception {
		try (InputStream is = openStream("Hopwood.fet")) {
			inst =  new InstituteFAO().importFrom(is);
		} 
	}

	static InputStream openStream(final String path) throws IOException {
		URL uri = StructureTest.class.getResource(path);
		return uri.openStream();
	}
	
	@Test
	public void testInstituteDetails() throws FAOException {
		assertEquals("5.16.0", inst.getVersion());
		assertEquals("Hopwood ESOL", inst.getName());
		assertEquals("Default comments", inst.getComment());
	}

	@Test
	public void testHours() throws FAOException {
		HoursXml hours = inst.getHours();
		assertEquals(5, hours.getNumbers());
		assertEquals("9.30 - 11.00", hours.getHours().get(0).getName());
		assertEquals("11.15 - 12.45", hours.getHours().get(1).getName());
		assertEquals("13.00 - 15.15", hours.getHours().get(2).getName());
		assertEquals("15.30 - 17.30", hours.getHours().get(3).getName());
		assertEquals("18.15 - 20.15", hours.getHours().get(4).getName());
	}

	@Test
	public void testDays() throws FAOException {
		DaysXml days = inst.getDays();
		assertEquals(6, days.getNumbers());
		assertEquals("Monday", days.getDays().get(0).getName());
		assertEquals("Tuesday", days.getDays().get(1).getName());
		assertEquals("Wednesday", days.getDays().get(2).getName());
		assertEquals("Thursday", days.getDays().get(3).getName());
		assertEquals("Friday", days.getDays().get(4).getName());
		assertEquals("Saturday", days.getDays().get(5).getName());
	}

	@Test
	public void testYears_Groups_Subgroups() throws FAOException {
		List<YearXml> years = inst.getYears();
		assertEquals(1, years.size());
		YearXml year2005 = years.get(0);
		assertEquals("2005", year2005.getName());
		assertEquals(new Integer(20), year2005.getNrOfStudents());
		List<GroupXml> groups = year2005.getGroups();
		assertEquals(46, groups.size());
		GroupXml foundGroup = findGroupByName("Basic Skills", groups);
		assertNotNull(foundGroup);
		assertEquals("Basic Skills", foundGroup.getName());
		assertEquals(new Integer(10), foundGroup.getNrOfStudents());
		assertEquals(6, foundGroup.getSubgroups().size());
		SubgroupXml foundSubgroup = findSubgroupByName("Basic L1 - L2", foundGroup.getSubgroups());
		assertNotNull(foundSubgroup);
		assertEquals("Basic L1 - L2", foundSubgroup.getName());
		assertEquals(new Integer(10), foundSubgroup.getNrOfStudents());
	}
	
	@Test
	public void testSubjects() {
		Set<String> expSubjectNames = new HashSet<>(Arrays.asList(
				"Admin",
				"Assessment",
				"CALL",
				"Call_Literacy",
				"EAL",
				"EFL",
				"Exam_tech",
				"Grammar",
				"IELTS",
				"IT",
				"Interviews",
				"Listening_Speaking",
				"Lit_E3_L2",
				"Lit_Pre_Ent_E2",
				"Literacy",
				"Meeting_Training",
				"Numeracy",
				"O2",
				"PGCE",
				"Project_Writing_Tutorial",
				"Reading",
				"Reading_English_Usage",
				"Spelling_Dictionary",
				"Tutorial",
				"Work_based_learning",
				"Writing"
		));
		
		List<SubjectXml> subjects = inst.getSubjects();
		assertEquals(expSubjectNames.size(), subjects.size());
		for (SubjectXml subj : subjects) {
			assertTrue(subj.getName() + " not found in the subjects.", expSubjectNames.contains(subj.getName()));
		}
	}

	@Test
	public void testTeachers() {
		Set<String> expTeacherNames = new HashSet<>(Arrays.asList(
				"Adrian",
				"Angela",
				"Angie",
				"Ann",
				"Anne_Rush",
				"Ayesha",
				"David",
				"Eugenia",
				"Fozia",
				"Inka",
				"John",
				"Julie",
				"Kausar",
				"Leonora",
				"Lynn",
				"Nargis",
				"Patricia",
				"Paul",
				"Pauline",
				"Philip",
				"Robert",
				"Rona",
				"Saima",
				"Stephen",
				"Vanessa",
				"Wilf"
		));
		
		List<TeacherXml> teachers = inst.getTeachers();
		assertEquals(expTeacherNames.size(), teachers.size());
		for (TeacherXml teacher : teachers) {
			assertTrue(teacher.getName() + " not found in the teachers.", expTeacherNames.contains(teacher.getName()));
		}
	}

	@Test
	public void testRooms() {
		List<RoomXml> expRooms = Arrays.asList(
				mockRoom("A103", 30),
				mockRoom("A208", 30),
				mockRoom("A213", 20),
				mockRoom("A217", 20),
				mockRoom("ASH113 (Midd)", 30),
				mockRoom("ASH212 (Midd)", 30),
				mockRoom("B114", 30),
				mockRoom("B225", 20),
				mockRoom("B301", 20),
				mockRoom("B302", 20),
				mockRoom("B303", 20),
				mockRoom("B305", 30),
				mockRoom("B305A", 20),
				mockRoom("B306", 20),
				mockRoom("B309", 30),
				mockRoom("B310", 100),
				mockRoom("B311", 20),
				mockRoom("B312", 20),
				mockRoom("B313A", 30),
				mockRoom("B314", 20),
				mockRoom("B318", 20),
				mockRoom("Bolton", 1000),
				mockRoom("Crescent Partnership", 30),
				mockRoom("HW32 (Midd)", 30),
				mockRoom("Hebron", 20),
				mockRoom("L301 (Midd)", 30),
				mockRoom("Middleton", 30),
				mockRoom("Middleton1", 30),
				mockRoom("Middleton2", 30),
				mockRoom("Pringle St", 30),
				mockRoom("Sparth Comm Centre", 30),
				mockRoom("Stansfield", 30)
		);
		Map<String, RoomXml> expRoomsMap = new HashMap<>();
		for (RoomXml room : expRooms) {
			expRoomsMap.put(room.getName(), room);
		}
		
		List<RoomXml> rooms = inst.getRooms();
		assertEquals(expRooms.size(), rooms.size());
		for (RoomXml room : rooms) {
			RoomXml expRoom = expRoomsMap.get(room.getName());
			assertNotNull(room.getName() + " not found in the rooms.", expRoom);
			assertEquals(room.getName() + " room's capacity.", expRoom.getCapacity(), room.getCapacity());
		}
	}

	@Test
	public void testActivityTag() {
		Set<String> expActTagNames = new HashSet<>(Arrays.asList(
				"CALL",
				"CALL_Literacy",
				"ESOL IT",
				"Listening_Speaking",
				"Literacy",
				"Num",
				"Project_Writing_Tutorial"
		));
		
		List<ActivityTagXml> actTags = inst.getActivityTags();
		assertEquals(expActTagNames.size(), actTags.size());
		for (ActivityTagXml actTag : actTags) {
			assertTrue(actTag.getName() + " not found in the activity tags.", expActTagNames.contains(actTag.getName()));
		}
	}
	
	@Test
	public void testActivity() {
		List<ActivityXml> activities = inst.getActivities();
		assertEquals(163, activities.size());
		ActivityXml foundAct = findActivityById(146L, activities);
		assertEquals(1, foundAct.getTeachers().size());
		assertEquals("Angela", foundAct.getTeachers().get(0).getName());
		assertEquals("Work_based_learning", foundAct.getSubject().getName());
	}
	
	@Test
	public void testTimeConstraints() {
		List<TimeConstraint> constraints = inst.getTimeConstraints();
		assertEquals(163, constraints.size());
	}

	@Test
	public void testSpaceConstraints() {
		List<SpaceConstraint> constraints = inst.getSpaceConstraints();
		assertEquals(163, constraints.size());
	}
	// -------------------- helpers ------------------------------------------------------------------------------------
	/**
	 * @param i
	 * @return 
	 */
	private ActivityXml findActivityById(Long id, List<ActivityXml> activities) {
		for (ActivityXml iActivity : activities) {
			if (iActivity.getId().equals(id)) {
				return iActivity;
			}
		}
		return null;
	}

	private GroupXml findGroupByName(String key, List<GroupXml> items) {
		for (GroupXml item : items) {
			if (item.getName().equals(key)) {
				return item;
			}
		}
		return null;
	}

	private SubgroupXml findSubgroupByName(String key, List<SubgroupXml> items) {
		for (SubgroupXml item : items) {
			if (item.getName().equals(key)) {
				return item;
			}
		}
		return null;
	}

	private static RoomXml mockRoom(final String name, final int cap) {
		return new RoomXml(name) {
			@Override public String getName() { return name; }
			@Override public int getCapacity() { return cap; }
		};
	}
	
}
