package org.confetti.tests.fet.engine;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.confetti.fet.dataprovider.FETDataProvider;
import org.confetti.fet.engine.FETEngineWizard;
import org.confetti.fet.engine.FETRunnable;
import org.confetti.tests.fet.engine.SpeedCategories.Failing;
import org.confetti.tests.xml.CompatibilityTest;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * @author Bubla Gabor
 */
public class FETRunnableTest {

	static void importFetAndSolve(final String path) throws Exception {
		FETDataProvider xmlDataProvider = CompatibilityTest.readFromFet(path);
		URL copyingUrl = FETEngineWizard.class.getResource("COPYING");
		FETRunnable runnable = new FETRunnable(xmlDataProvider, copyingUrl, false);
		runnable.attachPrintListener(event -> System.out.println((String) event.data));
		runnable.run(null);
		assertNotNull(runnable.getSolution());
	}
	
	@Category(Failing.class)
	@Test
	public void test_all_constraints() throws Exception {
		importFetAndSolve("test-all-constraints.fet");
	}

	@Category(Failing.class)
	@Test
	public void test_8th_highschool() throws Exception {
		importFetAndSolve("Greece/Pireus-difficult-warn/8th-highschool.fet");
	}
	
//	@Test
//	public void test_ConcordiaY15T1a() throws Exception {
//		importFet("Namibia/by-Bobby/set-4/ConcordiaY15T1a.fet");
//	}
	
//	@Test
//	public void test_EXAM_2013_2014_S1() throws Exception {
//		importFet("Algeria/Mechanical-Batna_Univ/EXAM-2013-2014-S1.fet");
//	}

	@Category(Failing.class)
	@Test
	public void test_shc_2013() throws Exception {
		importFetAndSolve("Belize/Sacred-Heart-College/shc-2013/shc-2013.fet");
	}

	@Category(Failing.class)
	@Test
	public void test_shc_2013_more_difficult() throws Exception {
		importFetAndSolve("Belize/Sacred-Heart-College/shc-2013/shc-2013-more-difficult.fet");
	}

	@Category(Failing.class)
	@Test
	public void test_THE_2008_2009_exams_sep() throws Exception {
		importFetAndSolve("Greece/Corfu/THE-2008-2009-exams-sep.fet");
	}

//	@Test
//	public void test_anonymous_2007() throws Exception {
//		importFet("anonymous/1/2007/anonymous-1-2007.fet");
//	}
//
//	@Test
//	public void test_anonymous_2008() throws Exception {
//		importFet("anonymous/1/2008/anonymous-1-2008.fet");
//	}
//
//	@Test
//	public void test_Vietnam_Khoi_chieu() throws Exception {
//		importFet("Vietnam/1-Le-Qui-Don_Lam-Ha/Khoi-chieu.fet");
//	}
//
//	@Test
//	public void test_Vietnam_Khoi_sang() throws Exception {
//		importFet("Vietnam/1-Le-Qui-Don_Lam-Ha/Khoi-sang.fet");
//	}
//
//	@Test
//	public void test_VietNam_HungYen() throws Exception {
//		importFet("Vietnam/2-secondary-school/VietNamHungYen.fet");
//	}
	
}
