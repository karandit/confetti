package org.confetti.tests.fet.engine;
import static org.confetti.tests.fet.engine.FETRunnableTest.importFetAndSolve;

import org.confetti.tests.fet.engine.SpeedCategories.Slow_Over_10_min;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FETRunnableTest_Slow_Over_10_min {

	@Category(Slow_Over_10_min.class)
	@Test
	public void test_Econ_Timisoara() throws Exception {
		importFetAndSolve("Romania/Faculty-Econ-Timisoara-difficult/2007-2008-sem-2/Econ-Timisoara.fet");
	}

	@Category(Slow_Over_10_min.class)
	@Test
	public void test_econ_tim_ver8() throws Exception {
		importFetAndSolve("Romania/Faculty-Econ-Timisoara-difficult/2008-2009-sem-1/econ-tim-ver8.fet");
	}

	@Category(Slow_Over_10_min.class)
	@Test
	public void test_GYR_5_gaps_per_teacher() throws Exception {
		importFetAndSolve("Germany/secondary-school-2/GYR-5-gaps-per-teacher.fet");
	}

	@Category(Slow_Over_10_min.class)
	@Test
	public void test_highschool_Ancona() throws Exception {
		importFetAndSolve("Italy/2007/difficult/highschool-Ancona.fet");
	}

	@Category(Slow_Over_10_min.class)
	@Test
	public void test_StPaulsColY13T1a() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-3/StPaulsColY13T1a.fet");
	}

}
