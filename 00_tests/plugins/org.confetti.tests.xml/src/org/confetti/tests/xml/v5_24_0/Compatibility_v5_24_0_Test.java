package org.confetti.tests.xml.v5_24_0;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.confetti.dataprovider.xml.XmlDataProvider;
import org.confetti.xml.FAOException;
import org.confetti.xml.Institute_v5_24_0_FAO;
import org.confetti.xml.core.Institute_v5_24_0_Xml;
import org.junit.Test;

public class Compatibility_v5_24_0_Test {

	static InputStream openStream(final String path) throws IOException {
		URL uri = Compatibility_v5_24_0_Test.class.getResource(path);
		return uri.openStream();
	}

	public static XmlDataProvider readFromFet(final String path) throws FAOException {
		try (InputStream is = openStream(path)) {
			Institute_v5_24_0_Xml  inst =  new Institute_v5_24_0_FAO().importFrom(is);
			return new XmlDataProvider(inst, new File(path));
		} catch (IOException e) {
			throw new FAOException(e);
		} 
	}
	
	private static void importFet(final String path) throws FAOException {
		XmlDataProvider dp = readFromFet(path);
		System.out.println(dp.getVersion() + "\t" + dp.getName().getValue());
	}

	@Test
	public void test_all_constraints() throws FAOException {
		importFet("test-all-constraints.fet");
	}

	@Test
	public void test_2006_2007() throws FAOException {
		importFet("Romania/Pedagogic-High-School-Tg-Mures/2006-2007.fet");
	}

	@Test
	public void test_2007_2008_sem1_a() throws FAOException {
		importFet("Romania/Pedagogic-High-School-Tg-Mures/2007-2008_sem1-a.fet");
	}

	@Test
	public void test_2007_2008_sem1_d() throws FAOException {
		importFet("Romania/Pedagogic-High-School-Tg-Mures/2007-2008_sem1-d.fet");
	}

	@Test
	public void test_2008_09_difficult() throws FAOException {
		importFet("Hong-Kong/Yew-Chung-Intl-School/2008-09-difficult.fet");
	}

	@Test
	public void test_2008_2009() throws FAOException {
		importFet("Greece/Vartholomio/2008-2009.fet");
	}

	@Test
	public void test_6th_Primary_School() throws FAOException {
		importFet("Greece/Didymoteicho/6th-Primary-School.fet");
	}

	@Test
	public void test_8th_highschool() throws FAOException {
		importFet("Greece/Pireus-difficult-warn/8th-highschool.fet");
	}

	@Test
	public void test_Arabic_saudi_1() throws FAOException {
		importFet("Saudi-Arabia/Arabic_saudi_1.fet");
	}

	@Test
	public void test_Arabic_saudi_2() throws FAOException {
		importFet("Saudi-Arabia/Arabic_saudi_2.fet");
	}

	@Test
	public void test_Arabic_saudi_3() throws FAOException {
		importFet("Saudi-Arabia/Arabic_saudi_3.fet");
	}

	@Test
	public void test_Arabic_saudi_examination() throws FAOException {
		importFet("Saudi-Arabia/may-not-solve/Arabic_saudi_examination.fet");
	}

	@Test
	public void test_base2010_23() throws FAOException {
		importFet("Italy/2010/base2010-23.fet");
	}

	@Test
	public void test_Bethlen_2007_2008() throws FAOException {
		importFet("Hungary/Bethlen/2007-2008-difficult/Bethlen_2007_2008.fet");
	}

	@Test
	public void test_Bethlen_2008_2009() throws FAOException {
		importFet("Hungary/Bethlen/2008-2009/2-newer/Bethlen-2008-2009.fet");
	}

	@Test
	public void test_Bethlen_2008_2009_old() throws FAOException {
		importFet("Hungary/Bethlen/2008-2009/1-older/Bethlen-2008-2009-old.fet");
	}

	@Test
	public void test_Brazil() throws FAOException {
		importFet("Brazil/1/Brazil.fet");
	}

	@Test
	public void test_Brazil_more_difficult() throws FAOException {
		importFet("Brazil/1/Brazil-more-difficult.fet");
	}

	@Test
	public void test_Collegiate_Junior_School2() throws FAOException {
		importFet("South-Africa/difficult/Collegiate_Junior_School2.fet");
	}

	@Test
	public void test_computers_craiova() throws FAOException {
		importFet("Romania/Faculty-Computers-Craiova/computers-craiova.fet");
	}

	@Test
	public void test_ConColY13T1a() throws FAOException {
		importFet("Namibia/by-Bobby/set-3/ConColY13T1a.fet");
	}

	@Test
	public void test_CONCORDIA() throws FAOException {
		importFet("Namibia/by-Bobby/set-2/may-take-hours/CONCORDIA.fet");
	}
	
	@Test
	public void test_ConcordiaY15T1a() throws FAOException {
		importFet("Namibia/by-Bobby/set-4/ConcordiaY15T1a.fet");
	}
	
	@Test
	public void test_de_secondary_school() throws FAOException {
		importFet("Germany/secondary-school-1/older/secondary_school.fet");
	}

	@Test
	public void test_dgspro200809() throws FAOException {
		importFet("Germany/DGS-Pro/dgspro200809.fet");
	}

	@Test
	public void test_DGS_Pro_Germany() throws FAOException {
		importFet("Germany/DGS-Pro/older/DGS_Pro_Germany.fet");
	}

	@Test
	public void test_Econ_Timisoara() throws FAOException {
		importFet("Romania/Faculty-Econ-Timisoara-difficult/2007-2008-sem-2/Econ-Timisoara.fet");
	}

	@Test
	public void test_econ_tim_ver8() throws FAOException {
		importFet("Romania/Faculty-Econ-Timisoara-difficult/2008-2009-sem-1/econ-tim-ver8.fet");
	}

	@Test
	public void test_econ_v0_8() throws FAOException {
		importFet("Romania/Faculty-Econ-Timisoara-difficult/2009-2010-sem-1/econ-v0.8.fet");
	}

	@Test
	public void test_EEBLJ_Diurno() throws FAOException {
		importFet("Brazil/2/EEBLJ-Diurno.fet");
	}

	@Test
	public void test_EEBLJ_Noturno() throws FAOException {
		importFet("Brazil/2/EEBLJ-Noturno.fet");
	}

	@Test
	public void test_eng_college() throws FAOException {
		importFet("India/New-Delhi/eng_college.fet");
	}

	@Test
	public void test_es_secondary_school() throws FAOException {
		importFet("Spain/2-secondary-school/secondary-school.fet");
	}

	@Test
	public void test_ET2012_2013_S1() throws FAOException {
		importFet("Algeria/Mechanical-Batna_Univ/ET2012-2013-S1.fet");
	}

	@Test
	public void test_ET2012_2013_S2() throws FAOException {
		importFet("Algeria/Mechanical-Batna_Univ/ET2012-2013-S2.fet");
	}

	@Test
	public void test_EXAM_2013_2014_S1() throws FAOException {
		importFet("Algeria/Mechanical-Batna_Univ/EXAM-2013-2014-S1.fet");
	}

	@Test
	public void test_FGPS() throws FAOException {
		importFet("Namibia/by-Bobby/set-2/FGPS.fet");
	}

	@Test
	public void test_FS_2008_2009_difficult() throws FAOException {
		importFet("Romania/Oradea/FS-2008-2009-difficult.fet");
	}

	@Test
	public void test_german_100_and_0() throws FAOException {
		importFet("Germany/secondary-school-1/constraints-min-days-100-few-0/german-100_and_0.fet");
	}

	@Test
	public void test_german_subact_constr() throws FAOException {
		importFet("Germany/secondary-school-1/using_subactivities_constraints/german_subact_constr.fet");
	}

	@Test
	public void test_Gymnasio() throws FAOException {
		importFet("Greece/Gymnasio/Gymnasio.fet");
	}

	@Test
	public void test_GYR() throws FAOException {
		importFet("Germany/secondary-school-2/GYR.fet");
	}

	@Test
	public void test_GYR_5_gaps_per_teacher() throws FAOException {
		importFet("Germany/secondary-school-2/GYR-5-gaps-per-teacher.fet");
	}

	@Test
	public void test_GYR_more_difficult() throws FAOException {
		importFet("Germany/secondary-school-2/GYR-more-difficult.fet");
	}

	@Test
	public void test_highlands_christian_school() throws FAOException {
		importFet("Namibia/by-Willy/highlands_christian_school.fet");
	}

	@Test
	public void test_highschool_Ancona() throws FAOException {
		importFet("Italy/2007/difficult/highschool-Ancona.fet");
	}

	@Test
	public void test_Hopwood() throws FAOException {
		importFet("United-Kingdom/Hopwood/Hopwood.fet");
	}

	@Test
	public void test_Horario_ISJ() throws FAOException {
		importFet("Argentina/Horario_ISJ.fet");
	}

	@Test
	public void test_input_Lectures_40() throws FAOException {
		importFet("Egypt/Dakahlia/input_Lectures_40.fet");
	}

	@Test
	public void test_Insight_Learning_Centre2() throws FAOException {
		importFet("South-Africa/difficult/Insight_Learning_Centre2.fet");
	}

	@Test
	public void test_institution_2012_2013() throws FAOException {
		importFet("Egypt/Dakahlia/institution-2012-2013.fet");
	}

	@Test
	public void test_JMSS() throws FAOException {
		importFet("Namibia/by-Bobby/set-2/JMSS.fet");
	}

	@Test
	public void test_Khartoum_2010_02_13() throws FAOException {
		importFet("Sudan/Khartoum-2010-02-13.fet");
	}

	@Test
	public void test_Klikks_SLP() throws FAOException {
		importFet("Malta/Klikk-SLP/Klikks-SLP.fet");
	}

	@Test
	public void test_KPS() throws FAOException {
		importFet("Namibia/by-Bobby/set-2/KPS.fet");
	}

	@Test
	public void test_Liceo_2010() throws FAOException {
		importFet("Italy/2010/Liceo-2010.fet");
	}

	@Test
	public void test_liceo_scientifico() throws FAOException {
		importFet("Italy/2009/liceo-scientifico.fet");
	}

	@Test
	public void test_Lom_high_school_2007_2008() throws FAOException {
		importFet("Bulgaria/Lom_high_school_2007-2008.fet");
	}

	@Test
	public void test_lycee_Farabi() throws FAOException {
		importFet("Morocco/Hadkourt/lycee-Farabi.fet");
	}

	@Test
	public void test_lycee_Farabi_old() throws FAOException {
		importFet("Morocco/Hadkourt/old-without-max-7-hours/lycee-Farabi-old.fet");
	}

	@Test
	public void test_lycee_goulmima() throws FAOException {
		importFet("Morocco/Goulmima/lycee-goulmima.fet");
	}

	@Test
	public void test_MAPS() throws FAOException {
		importFet("Namibia/by-Bobby/set-2/MAPS.fet");
	}

	@Test
	public void test_Orar_5_3() throws FAOException {
		importFet("Romania/Medgidia/Orar_5_3.fet");
	}

	@Test
	public void test_PBS() throws FAOException {
		importFet("Namibia/by-Bobby/set-1/PBS.fet");
	}

	@Test
	public void test_primaria() throws FAOException {
		importFet("Spain/3-school/primaria.fet");
	}

	@Test
	public void test_PutSS() throws FAOException {
		importFet("Namibia/by-Bobby/set-2/PutSS.fet");
	}

	@Test
	public void test_sacred_heart_college() throws FAOException {
		importFet("Belize/Sacred-Heart-College/shc-2007/sacred_heart_college.fet");
	}

	@Test
	public void test_school() throws FAOException {
		importFet("Spain/1-school/school.fet");
	}

	@Test
	public void test_school_10_oradea_2007_2008() throws FAOException {
		importFet("Romania/Oradea/school-10-oradea-2007-2008.fet");
	}

	@Test
	public void test_Sharif() throws FAOException {
		importFet("Iran/Sharif-University/Sharif.fet");
	}

	@Test
	public void test_shc_2013() throws FAOException {
		importFet("Belize/Sacred-Heart-College/shc-2013/shc-2013.fet");
	}

	@Test
	public void test_shc_2013_more_difficult() throws FAOException {
		importFet("Belize/Sacred-Heart-College/shc-2013/shc-2013-more-difficult.fet");
	}

	@Test
	public void test_shc_6_gaps_per_teacher() throws FAOException {
		importFet("Belize/Sacred-Heart-College/shc-2007/shc_6_gaps_per_teacher.fet");
	}

	@Test
	public void test_shc_prelim_08() throws FAOException {
		importFet("Belize/Sacred-Heart-College/shc-2008/shc-prelim-08.fet");
	}

	@Test
	public void test_Shipena() throws FAOException {
		importFet("Namibia/by-Bobby/set-2/Shipena.fet");
	}

	@Test
	public void test_simpler_italian() throws FAOException {
		importFet("Italy/2007/simple/simpler-italian.fet");
	}

	@Test
	public void test_small_school() throws FAOException {
		importFet("Denmark/small-school.fet");
	}

	@Test
	public void test_StPaulsColY13T1a() throws FAOException {
		importFet("Namibia/by-Bobby/set-3/StPaulsColY13T1a.fet");
	}

	@Test
	public void test_telpolytech_even() throws FAOException {
		importFet("Indonesia/Telpolytech-Bandung/2010-2011/telpolytech_even.fet");
	}

	@Test
	public void test_telpolytech_odd() throws FAOException {
		importFet("Indonesia/Telpolytech-Bandung/2010-2011/telpolytech_odd.fet");
	}

	@Test
	public void test_test_2012() throws FAOException {
		importFet("Belize/Sacred-Heart-College/shc-2012/test-2012.fet");
	}

	@Test
	public void test_THE_2008_2009_exams_sep() throws FAOException {
		importFet("Greece/Corfu/THE-2008-2009-exams-sep.fet");
	}

	@Test
	public void test_timetable_July_Nov_09_9() throws FAOException {
		importFet("India/Chennai/anna-university/timetable_July_Nov_09_9.fet");
	}

	@Test
	public void test_v1_7() throws FAOException {
		importFet("Romania/Faculty-Econ-Timisoara-difficult/2009-2010-sem-2/v1_7.fet");
	}

	@Test
	public void test_Van_RhynFinal() throws FAOException {
		importFet("Namibia/by-Bobby/set-1/Van_RhynFinal.fet");
	}

	@Test
	public void test_WTHS() throws FAOException {
		importFet("Namibia/by-Bobby/set-2/WTHS.fet");
	}

	@Test
	public void test_anonymous_2007() throws FAOException {
		importFet("anonymous/1/2007/anonymous-1-2007.fet");
	}

	@Test
	public void test_anonymous_2008() throws FAOException {
		importFet("anonymous/1/2008/anonymous-1-2008.fet");
	}

	@Test
	public void test_Vietnam_Khoi_chieu() throws FAOException {
		importFet("Vietnam/1-Le-Qui-Don_Lam-Ha/Khoi-chieu.fet");
	}

	@Test
	public void test_Vietnam_Khoi_sang() throws FAOException {
		importFet("Vietnam/1-Le-Qui-Don_Lam-Ha/Khoi-sang.fet");
	}

	@Test
	public void test_VietNam_HungYen() throws FAOException {
		importFet("Vietnam/2-secondary-school/VietNamHungYen.fet");
	}
	
}
