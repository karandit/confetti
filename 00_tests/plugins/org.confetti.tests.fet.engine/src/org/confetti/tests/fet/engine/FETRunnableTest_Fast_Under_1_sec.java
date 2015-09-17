package org.confetti.tests.fet.engine;

import static org.confetti.tests.fet.engine.FETRunnableTest.importFetAndSolve;

import org.confetti.tests.fet.engine.SpeedCategories.Fast_Under_1_sec;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FETRunnableTest_Fast_Under_1_sec {

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_2006_2007() throws Exception {
		importFetAndSolve("Romania/Pedagogic-High-School-Tg-Mures/2006-2007.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_6th_Primary_School() throws Exception {
		importFetAndSolve("Greece/Didymoteicho/6th-Primary-School.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_EEBLJ_Noturno() throws Exception {
		importFetAndSolve("Brazil/2/EEBLJ-Noturno.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_ET2012_2013_S1() throws Exception {
		importFetAndSolve("Algeria/Mechanical-Batna_Univ/ET2012-2013-S1.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_ET2012_2013_S2() throws Exception {
		importFetAndSolve("Algeria/Mechanical-Batna_Univ/ET2012-2013-S2.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_Horario_ISJ() throws Exception {
		importFetAndSolve("Argentina/Horario_ISJ.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_KPS() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-2/KPS.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_Orar_5_3() throws Exception {
		importFetAndSolve("Romania/Medgidia/Orar_5_3.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_primaria() throws Exception {
		importFetAndSolve("Spain/3-school/primaria.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_Sharif() throws Exception {
		importFetAndSolve("Iran/Sharif-University/Sharif.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_small_school() throws Exception {
		importFetAndSolve("Denmark/small-school.fet");
	}


}
