package org.confetti.tests.fet.engine;

import static org.confetti.tests.fet.engine.FETRunnableTest.importFetAndSolve;

import org.confetti.tests.fet.engine.SpeedCategories.Slow_IHaveNeverSeen;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FETRunnableTest_Slow_IHaveNeverSeen {

	@Category(Slow_IHaveNeverSeen.class)
	@Test
	public void test_2007_2008_sem1_d() throws Exception {
		importFetAndSolve("Romania/Pedagogic-High-School-Tg-Mures/2007-2008_sem1-d.fet");
	}

	@Category(Slow_IHaveNeverSeen.class)
	@Test
	public void test_Bethlen_2007_2008() throws Exception {
		importFetAndSolve("Hungary/Bethlen/2007-2008-difficult/Bethlen_2007_2008.fet");
	}
	
	@Category(Slow_IHaveNeverSeen.class)
	@Test
	public void test_Bethlen_2008_2009() throws Exception {
		importFetAndSolve("Hungary/Bethlen/2008-2009/newer/Bethlen-2008-2009.fet");
	}

	@Category(Slow_IHaveNeverSeen.class)
	@Test
	public void test_GYR_more_difficult() throws Exception {
		importFetAndSolve("Germany/secondary-school-2/GYR-more-difficult.fet");
	}

	@Category(Slow_IHaveNeverSeen.class)
	@Test
	public void test_institution_2012_2013() throws Exception {
		importFetAndSolve("Egypt/Dakahlia/institution-2012-2013.fet");
	}

	@Category(Slow_IHaveNeverSeen.class)
	@Test
	public void test_Klikks_SLP() throws Exception {
		importFetAndSolve("Malta/Klikk-SLP/Klikks-SLP.fet");
	}

	@Category(Slow_IHaveNeverSeen.class)
	@Test
	public void test_Lom_high_school_2007_2008() throws Exception {
		importFetAndSolve("Bulgaria/Lom_high_school_2007-2008.fet");
	}

	@Category(Slow_IHaveNeverSeen.class)
	@Test
	public void test_lycee_goulmima() throws Exception {
		importFetAndSolve("Morocco/Goulmima/lycee-goulmima.fet");
	}

	@Category(Slow_IHaveNeverSeen.class)
	@Test
	public void test_v1_7() throws Exception {
		importFetAndSolve("Romania/Faculty-Econ-Timisoara-difficult/2009-2010-sem-2/v1_7.fet");
	}

}
