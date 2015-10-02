package org.confetti.tests.fet.engine;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.confetti.fet.engine.solution.ResultActivityXML;
import org.confetti.fet.engine.solution.SolutionFAO;
import org.confetti.fet.engine.solution.SolutionXML;
import org.confetti.fet.xml.FAOException;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author Gabor Bubla
 */
public class SolutionFAOTest {

	private static SolutionXML importSolution(final String path) throws FAOException {
		try (InputStream is = openStream(path)) {
			SolutionXML sol = new SolutionFAO().importFrom(is);
			return sol;
		} catch (IOException e) {
			throw new FAOException(e);
		} 
	}
	
	static InputStream openStream(final String path) throws IOException {
		URL uri = SolutionFAOTest.class.getResource(path);
		return uri.openStream();
	}

	@Test
	public void test_bethlen() throws FAOException {
		SolutionXML sol = importSolution("Bethlen-2008-2009_activities.xml");
		Assert.assertEquals(766, sol.getActivities().size());
		Map<String, ResultActivityXML> activities = new HashMap<>();
		for (ResultActivityXML act : sol.getActivities()) {
			activities.put(act.getId(), act);
		}
		assertActivity(activities.get("114"), "Csütörtök", "4.", "Ének-rajz terem");
		assertActivity(activities.get("118"), "Csütörtök", "2.", "Tornaterem");
		assertActivity(activities.get("247"), "Szerda", "6.", "");
		assertActivity(activities.get("472"), "Péntek", "5.", "");
	}

	private void assertActivity(ResultActivityXML act, String expDay, String expHour, String expRoom) {
		assertEquals(act.getDay(), expDay);
		assertEquals(act.getHour(), expHour);
		assertEquals(act.getRoom(), expRoom);
	}

}
