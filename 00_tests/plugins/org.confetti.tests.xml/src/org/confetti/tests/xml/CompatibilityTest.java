package org.confetti.tests.xml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.confetti.core.IActivityTag;
import org.confetti.core.IRoom;
import org.confetti.core.ISubject;
import org.confetti.core.ITeacher;
import org.confetti.xml.FAOException;
import org.confetti.xml.InstituteFAO;
import org.confetti.xml.core.InstituteXml;
import org.junit.BeforeClass;
import org.junit.Test;

public class CompatibilityTest {
	
	private static InstituteXml inst;
	
	@BeforeClass
	public static void setUp() throws FAOException {
		File file = new File("/00_tests/plugins/org.confetti.tests.xml/resources/examples/United-Kingdom/Hopwood/Hopwood.fet");
		inst =  new InstituteFAO().importFrom(file);
	}
	
	@Test
	public void testInstituteDetails() throws FAOException {
		assertEquals("5.16.0", inst.getVersion());
		assertEquals("Hopwood ESOL", inst.getName());
		assertEquals("Default comments", inst.getComment());
	}

	@Test
	public void testSubjects() {
		Set<String> expSubjectNames = new HashSet<>(Arrays.asList(
				"Admin",
				"Assessment",
				"CALL",
				"Call/Literacy",
				"EAL",
				"EFL",
				"Exam tech",
				"Grammar",
				"IELTS",
				"IT",
				"Interviews",
				"Listening/Speaking",
				"Lit E3 - L2",
				"Lit Pre-Ent - E2",
				"Literacy",
				"Meeting/Training",
				"Numeracy",
				"O2",
				"PGCE",
				"Project/Writing/Tutorial",
				"Reading",
				"Reading/English Usage",
				"Spelling/Dictionary",
				"Tutorial",
				"Work-based learning",
				"Writing"
		));
		
		List<ISubject> subjects = inst.getSubjects();
		assertEquals(expSubjectNames.size(), subjects.size());
		for (ISubject subj : subjects) {
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
				"Anne Rush",
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
		
		List<ITeacher> teachers = inst.getTeachers();
		assertEquals(expTeacherNames.size(), teachers.size());
		for (ITeacher teacher : teachers) {
			assertTrue(teacher.getName() + " not found in the teachers.", expTeacherNames.contains(teacher.getName()));
		}
	}

	@Test
	public void testRooms() {
		List<IRoom> expRooms = Arrays.asList(
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
		Map<String, IRoom> expRoomsMap = new HashMap<>();
		for (IRoom room : expRooms) {
			expRoomsMap.put(room.getName(), room);
		}
		
		List<IRoom> rooms = inst.getRooms();
		assertEquals(expRooms.size(), rooms.size());
		for (IRoom room : rooms) {
			IRoom expRoom = expRoomsMap.get(room.getName());
			assertNotNull(room.getName() + " not found in the rooms.", expRoom);
			assertEquals(room.getName() + " room's capacity.", expRoom.getCapacity(), room.getCapacity());
		}
	}

	@Test
	public void testActivityTag() {
		Set<String> expActTagNames = new HashSet<>(Arrays.asList(
				"CALL",
				"CALL/Literacy",
				"ESOL IT",
				"Listening/Speaking",
				"Literacy",
				"Num",
				"Project/Writing/Tutorial"
		));
		
		List<IActivityTag> actTags = inst.getActivityTags();
		assertEquals(expActTagNames.size(), actTags.size());
		for (IActivityTag actTag : actTags) {
			assertTrue(actTag.getName() + " not found in the activity tags.", expActTagNames.contains(actTag.getName()));
		}
	}

	// -------------------- helpers ------------------------------------------------------------------------------------
	private static IRoom mockRoom(final String name, final int cap) {
		return new IRoom() {
			@Override public String getName() { return name; }
			@Override public int getCapacity() { return cap; }
		};
	}
	
	
}
