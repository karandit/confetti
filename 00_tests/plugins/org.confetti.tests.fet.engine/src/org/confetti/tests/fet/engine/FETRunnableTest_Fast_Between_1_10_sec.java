package org.confetti.tests.fet.engine;
import static org.confetti.tests.fet.engine.FETRunnableTest.importFetAndSolve;

import org.confetti.tests.fet.engine.SpeedCategories.Fast_Between_1_10_sec;
import org.junit.Test;
import org.junit.experimental.categories.Category;

public class FETRunnableTest_Fast_Between_1_10_sec {
	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_2008_2009() throws Exception {
		importFetAndSolve("Greece/Vartholomio/2008-2009.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_Brazil() throws Exception {
		importFetAndSolve("Brazil/1/Brazil.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_Brazil_more_difficult() throws Exception {
		importFetAndSolve("Brazil/1/Brazil-more-difficult.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_computers_craiova() throws Exception {
		importFetAndSolve("Romania/Faculty-Computers-Craiova/computers-craiova.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_dgspro200809() throws Exception {
		importFetAndSolve("Germany/DGS-Pro/dgspro200809.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_DGS_Pro_Germany() throws Exception {
		importFetAndSolve("Germany/DGS-Pro/older/DGS_Pro_Germany.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_EEBLJ_Diurno() throws Exception {
		importFetAndSolve("Brazil/2/EEBLJ-Diurno.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_es_secondary_school() throws Exception {
		importFetAndSolve("Spain/2-secondary-school/secondary-school.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_highlands_christian_school() throws Exception {
		importFetAndSolve("Namibia/by-Willy/highlands_christian_school.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_Hopwood() throws Exception {
		importFetAndSolve("United-Kingdom/Hopwood/Hopwood.fet");
	}
	
	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_input_Lectures_40() throws Exception {
		importFetAndSolve("Egypt/Dakahlia/input_Lectures_40.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_MAPS() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-2/MAPS.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_PBS() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-1/PBS.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_PutSS() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-2/PutSS.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_sacred_heart_college() throws Exception {
		importFetAndSolve("Belize/Sacred-Heart-College/shc-2007/sacred_heart_college.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_school() throws Exception {
		importFetAndSolve("Spain/1-school/school.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_school_10_oradea_2007_2008() throws Exception {
		importFetAndSolve("Romania/Oradea/school-10-oradea-2007-2008.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_shc_6_gaps_per_teacher() throws Exception {
		importFetAndSolve("Belize/Sacred-Heart-College/shc-2007/shc_6_gaps_per_teacher.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_simpler_italian() throws Exception {
		importFetAndSolve("Italy/2007/simple/simpler-italian.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_telpolytech_even() throws Exception {
		importFetAndSolve("Indonesia/Telpolytech-Bandung/2010-2011/telpolytech_even.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_telpolytech_odd() throws Exception {
		importFetAndSolve("Indonesia/Telpolytech-Bandung/2010-2011/telpolytech_odd.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_timetable_July_Nov_09_9() throws Exception {
		importFetAndSolve("India/Chennai/anna-university/timetable_July_Nov_09_9.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_WTHS() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-2/WTHS.fet");
	}


}
