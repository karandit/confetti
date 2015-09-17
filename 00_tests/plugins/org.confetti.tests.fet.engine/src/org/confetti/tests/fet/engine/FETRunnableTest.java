package org.confetti.tests.fet.engine;

import static org.junit.Assert.assertNotNull;

import java.net.URL;

import org.confetti.dataprovider.xml.XmlDataProvider;
import org.confetti.fet.engine.FETEngineWizard;
import org.confetti.fet.engine.FETRunnable;
import org.confetti.tests.fet.engine.SpeedCategories.Failing;
import org.confetti.tests.fet.engine.SpeedCategories.Fast_Between_1_10_sec;
import org.confetti.tests.fet.engine.SpeedCategories.Fast_Under_1_sec;
import org.confetti.tests.fet.engine.SpeedCategories.Slow_Between_10_120_sec;
import org.confetti.tests.fet.engine.SpeedCategories.Slow_Between_2_10_min;
import org.confetti.tests.fet.engine.SpeedCategories.Slow_Over_10_min;
import org.confetti.tests.xml.CompatibilityTest;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.categories.Category;

/**
 * @author Bubla Gabor
 */
public class FETRunnableTest {

	private static void importFetAndSolve(final String path) throws Exception {
		XmlDataProvider xmlDataProvider = CompatibilityTest.readFromFet(path);
		URL copyingUrl = FETEngineWizard.class.getResource("COPYING");
		FETRunnable runnable = new FETRunnable(xmlDataProvider, copyingUrl);
		runnable.attachPrintListener(event -> System.out.println((String) event.data));
		runnable.run(null);
		assertNotNull(runnable.getSolution());
	}
	
	@Category(Failing.class)
	@Test
	public void test_all_constraints() throws Exception {
		importFetAndSolve("test-all-constraints.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_2006_2007() throws Exception {
		importFetAndSolve("Romania/Pedagogic-High-School-Tg-Mures/2006-2007.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_2007_2008_sem1_a() throws Exception {
		importFetAndSolve("Romania/Pedagogic-High-School-Tg-Mures/2007-2008_sem1-a.fet");
	}
	
	@Ignore
	@Test
	public void test_2007_2008_sem1_d() throws Exception {
		importFetAndSolve("Romania/Pedagogic-High-School-Tg-Mures/2007-2008_sem1-d.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_2008_09_difficult() throws Exception {
		importFetAndSolve("Hong-Kong/Yew-Chung-Intl-School/2008-09-difficult.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_2008_2009() throws Exception {
		importFetAndSolve("Greece/Vartholomio/2008-2009.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_6th_Primary_School() throws Exception {
		importFetAndSolve("Greece/Didymoteicho/6th-Primary-School.fet");
	}

	@Category(Failing.class)
	@Test
	public void test_8th_highschool() throws Exception {
		importFetAndSolve("Greece/Pireus-difficult-warn/8th-highschool.fet");
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

	@Category(Slow_Between_2_10_min.class)
	@Test
	public void test_Arabic_saudi_3() throws Exception {
		importFetAndSolve("Saudi-Arabia/Arabic_saudi_3.fet");
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
	
	@Ignore
	@Test
	public void test_Bethlen_2007_2008() throws Exception {
		importFetAndSolve("Hungary/Bethlen/2007-2008-difficult/Bethlen_2007_2008.fet");
	}
	
	@Ignore
	@Test
	public void test_Bethlen_2008_2009() throws Exception {
		importFetAndSolve("Hungary/Bethlen/2008-2009/newer/Bethlen-2008-2009.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_Bethlen_2008_2009_old() throws Exception {
		importFetAndSolve("Hungary/Bethlen/2008-2009/older/Bethlen-2008-2009-old.fet");
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

	@Category(Slow_Between_2_10_min.class)
	@Test
	public void test_Collegiate_Junior_School2() throws Exception {
		importFetAndSolve("South-Africa/difficult/Collegiate_Junior_School2.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_computers_craiova() throws Exception {
		importFetAndSolve("Romania/Faculty-Computers-Craiova/computers-craiova.fet");
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
	
//	@Test
//	public void test_ConcordiaY15T1a() throws Exception {
//		importFet("Namibia/by-Bobby/set-4/ConcordiaY15T1a.fet");
//	}
	
	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_de_secondary_school() throws Exception {
		importFetAndSolve("Germany/secondary-school-1/older/secondary_school.fet");
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

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_econ_v0_8() throws Exception {
		importFetAndSolve("Romania/Faculty-Econ-Timisoara-difficult/2009-2010-sem-1/econ-v0.8.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_EEBLJ_Diurno() throws Exception {
		importFetAndSolve("Brazil/2/EEBLJ-Diurno.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_EEBLJ_Noturno() throws Exception {
		importFetAndSolve("Brazil/2/EEBLJ-Noturno.fet");
	}

	@Category(Slow_Between_2_10_min.class)
	@Test
	public void test_eng_college() throws Exception {
		importFetAndSolve("India/New-Delhi/eng_college.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_es_secondary_school() throws Exception {
		importFetAndSolve("Spain/2-secondary-school/secondary-school.fet");
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

//	@Test
//	public void test_EXAM_2013_2014_S1() throws Exception {
//		importFet("Algeria/Mechanical-Batna_Univ/EXAM-2013-2014-S1.fet");
//	}

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

	@Category(Slow_Between_2_10_min.class)
	@Test
	public void test_GYR() throws Exception {
		importFetAndSolve("Germany/secondary-school-2/GYR.fet");
	}

	@Category(Slow_Over_10_min.class)
	@Test
	public void test_GYR_5_gaps_per_teacher() throws Exception {
		importFetAndSolve("Germany/secondary-school-2/GYR-5-gaps-per-teacher.fet");
	}
	@Ignore
	@Test
	public void test_GYR_more_difficult() throws Exception {
		importFetAndSolve("Germany/secondary-school-2/GYR-more-difficult.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_highlands_christian_school() throws Exception {
		importFetAndSolve("Namibia/by-Willy/highlands_christian_school.fet");
	}

	@Category(Slow_Over_10_min.class)
	@Test
	public void test_highschool_Ancona() throws Exception {
		importFetAndSolve("Italy/2007/difficult/highschool-Ancona.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_Hopwood() throws Exception {
		importFetAndSolve("United-Kingdom/Hopwood/Hopwood.fet");
	}
	
	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_Horario_ISJ() throws Exception {
		importFetAndSolve("Argentina/Horario_ISJ.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_input_Lectures_40() throws Exception {
		importFetAndSolve("Egypt/Dakahlia/input_Lectures_40.fet");
	}

	@Category(Slow_Between_2_10_min.class)
	@Test
	public void test_Insight_Learning_Centre2() throws Exception {
		importFetAndSolve("South-Africa/difficult/Insight_Learning_Centre2.fet");
	}
	
	@Ignore
	@Test
	public void test_institution_2012_2013() throws Exception {
		importFetAndSolve("Egypt/Dakahlia/institution-2012-2013.fet");
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
	@Ignore
	@Test
	public void test_Klikks_SLP() throws Exception {
		importFetAndSolve("Malta/Klikk-SLP/Klikks-SLP.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_KPS() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-2/KPS.fet");
	}

	@Category(Slow_Between_2_10_min.class)
	@Test
	public void test_Liceo_2010() throws Exception {
		importFetAndSolve("Italy/2010/Liceo-2010.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_liceo_scientifico() throws Exception {
		importFetAndSolve("Italy/2009/liceo-scientifico.fet");
	}
	@Ignore
	@Test
	public void test_Lom_high_school_2007_2008() throws Exception {
		importFetAndSolve("Bulgaria/Lom_high_school_2007-2008.fet");
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
	@Ignore
	@Test
	public void test_lycee_goulmima() throws Exception {
		importFetAndSolve("Morocco/Goulmima/lycee-goulmima.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_MAPS() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-2/MAPS.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_Orar_5_3() throws Exception {
		importFetAndSolve("Romania/Medgidia/Orar_5_3.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_PBS() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-1/PBS.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_primaria() throws Exception {
		importFetAndSolve("Spain/3-school/primaria.fet");
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

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_Sharif() throws Exception {
		importFetAndSolve("Iran/Sharif-University/Sharif.fet");
	}

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

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_shc_6_gaps_per_teacher() throws Exception {
		importFetAndSolve("Belize/Sacred-Heart-College/shc-2007/shc_6_gaps_per_teacher.fet");
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

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_simpler_italian() throws Exception {
		importFetAndSolve("Italy/2007/simple/simpler-italian.fet");
	}

	@Category(Fast_Under_1_sec.class)
	@Test
	public void test_small_school() throws Exception {
		importFetAndSolve("Denmark/small-school.fet");
	}

	@Category(Slow_Over_10_min.class)
	@Test
	public void test_StPaulsColY13T1a() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-3/StPaulsColY13T1a.fet");
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

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_test_2012() throws Exception {
		importFetAndSolve("Belize/Sacred-Heart-College/shc-2012/test-2012.fet");
	}

	@Category(Failing.class)
	@Test
	public void test_THE_2008_2009_exams_sep() throws Exception {
		importFetAndSolve("Greece/Corfu/THE-2008-2009-exams-sep.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_timetable_July_Nov_09_9() throws Exception {
		importFetAndSolve("India/Chennai/anna-university/timetable_July_Nov_09_9.fet");
	}

	@Ignore
	@Test
	public void test_v1_7() throws Exception {
		importFetAndSolve("Romania/Faculty-Econ-Timisoara-difficult/2009-2010-sem-2/v1_7.fet");
	}

	@Category(Slow_Between_10_120_sec.class)
	@Test
	public void test_Van_RhynFinal() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-1/Van_RhynFinal.fet");
	}

	@Category(Fast_Between_1_10_sec.class)
	@Test
	public void test_WTHS() throws Exception {
		importFetAndSolve("Namibia/by-Bobby/set-2/WTHS.fet");
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
