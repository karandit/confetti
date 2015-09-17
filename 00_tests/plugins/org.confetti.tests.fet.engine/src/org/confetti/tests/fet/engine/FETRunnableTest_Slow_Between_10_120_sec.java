package org.confetti.tests.fet.engine;

import static org.confetti.tests.fet.engine.FETRunnableTest.importFetAndSolve;

import org.confetti.tests.fet.engine.SpeedCategories.Slow_Between_10_120_sec;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FETRunnableTest_Slow_Between_10_120_sec {

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_2007_2008_sem1_a() throws Exception {
		importFetAndSolve("Romania/Pedagogic-High-School-Tg-Mures/2007-2008_sem1-a.fet");
	}
	
	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_2008_09_difficult() throws Exception {
		importFetAndSolve("Hong-Kong/Yew-Chung-Intl-School/2008-09-difficult.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_Arabic_saudi_1() throws Exception {
		importFetAndSolve("Saudi-Arabia/Arabic_saudi_1.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_Arabic_saudi_2() throws Exception {
		importFetAndSolve("Saudi-Arabia/Arabic_saudi_2.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_Arabic_saudi_examination() throws Exception {
		importFetAndSolve("Saudi-Arabia/may-not-solve/Arabic_saudi_examination.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_base2010_23() throws Exception {
		importFetAndSolve("Italy/2010/base2010-23.fet");
	}
	
	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_Bethlen_2008_2009_old() throws Exception {
		importFetAndSolve("Hungary/Bethlen/2008-2009/older/Bethlen-2008-2009-old.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_ConColY13T1a() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-3/ConColY13T1a.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_CONCORDIA() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-2/may-take-hours/CONCORDIA.fet");
	}
	
	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_de_secondary_school() throws Exception {
		importFetAndSolve("Germany/secondary-school-1/older/secondary_school.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_econ_v0_8() throws Exception {
		importFetAndSolve("Romania/Faculty-Econ-Timisoara-difficult/2009-2010-sem-1/econ-v0.8.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_FGPS() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-2/FGPS.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_FS_2008_2009_difficult() throws Exception {
		importFetAndSolve("Romania/Oradea/FS-2008-2009-difficult.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_german_100_and_0() throws Exception {
		importFetAndSolve("Germany/secondary-school-1/constraints-min-days-100-few-0/german-100_and_0.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_german_subact_constr() throws Exception {
		importFetAndSolve("Germany/secondary-school-1/using_subactivities_constraints/german_subact_constr.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_Gymnasio() throws Exception {
		importFetAndSolve("Greece/Gymnasio/Gymnasio.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_JMSS() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-2/JMSS.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_Khartoum_2010_02_13() throws Exception {
		importFetAndSolve("Sudan/Khartoum-2010-02-13.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_liceo_scientifico() throws Exception {
		importFetAndSolve("Italy/2009/liceo-scientifico.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_lycee_Farabi() throws Exception {
		importFetAndSolve("Morocco/Hadkourt/lycee-Farabi.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_lycee_Farabi_old() throws Exception {
		importFetAndSolve("Morocco/Hadkourt/old-without-max-7-hours/lycee-Farabi-old.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_shc_prelim_08() throws Exception {
		importFetAndSolve("Belize/Sacred-Heart-College/shc-2008/shc-prelim-08.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_Shipena() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-2/Shipena.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_test_2012() throws Exception {
		importFetAndSolve("Belize/Sacred-Heart-College/shc-2012/test-2012.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_Van_RhynFinal() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-1/Van_RhynFinal.fet");
	}


}
