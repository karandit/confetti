package org.confetti.tests.xml;

import static org.junit.Assert.assertEquals;

import java.io.File;

import org.confetti.xml.FAOException;
import org.confetti.xml.InstituteFAO;
import org.confetti.xml.core.InstituteXml;
import org.junit.Test;

public class CompatibilityTest {

	@Test
	public void testUnitedKingdom_Hopwood() throws FAOException {
		File file = new File("examples/United-Kingdom/Hopwood/Hopwood.fet");
		InstituteXml inst =  new InstituteFAO().importFrom(file);
		assertEquals("5.16.0", inst.getVersion());
		assertEquals("Hopwood ESOL", inst.getName());
		assertEquals("Default comments", inst.getComment());
	}

}
