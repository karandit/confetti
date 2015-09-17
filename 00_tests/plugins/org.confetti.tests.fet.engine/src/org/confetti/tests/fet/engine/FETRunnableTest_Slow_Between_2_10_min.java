package org.confetti.tests.fet.engine;

import static org.confetti.tests.fet.engine.FETRunnableTest.importFetAndSolve;

import org.confetti.tests.fet.engine.SpeedCategories.Slow_Between_2_10_min;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FETRunnableTest_Slow_Between_2_10_min {

	@Category(Slow_Between_2_10_min.class)
	@Test
	public void test_Arabic_saudi_3() throws Exception {
		importFetAndSolve("Saudi-Arabia/Arabic_saudi_3.fet");
	}

	@Category(Slow_Between_2_10_min.class)
	@Test
	public void test_Collegiate_Junior_School2() throws Exception {
		importFetAndSolve("South-Africa/difficult/Collegiate_Junior_School2.fet");
	}

	@Category(Slow_Between_2_10_min.class)
	@Test
	public void test_eng_college() throws Exception {
		importFetAndSolve("India/New-Delhi/eng_college.fet");
	}

	@Category(Slow_Between_2_10_min.class)
	@Test
	public void test_GYR() throws Exception {
		importFetAndSolve("Germany/secondary-school-2/GYR.fet");
	}

	@Category(Slow_Between_2_10_min.class)
	@Test
	public void test_Insight_Learning_Centre2() throws Exception {
		importFetAndSolve("South-Africa/difficult/Insight_Learning_Centre2.fet");
	}
	
	@Category(Slow_Between_2_10_min.class)
	@Test
	public void test_Liceo_2010() throws Exception {
		importFetAndSolve("Italy/2010/Liceo-2010.fet");
	}


}
