package org.confetti.tests.xml;

import static org.confetti.tests.xml.StructureTest.openStream;

import java.io.IOException;
import java.io.InputStream;

import org.confetti.xml.FAOException;
import org.confetti.xml.InstituteFAO;
import org.confetti.xml.core.InstituteXml;
import org.junit.Test;

/**
 * @author Bubla Gabor
 */
public class CompatibilityTest {

	private static void importFet(final String path) throws FAOException {
		try (InputStream is = openStream(path)) {
			InstituteXml  inst =  new InstituteFAO().importFrom(is);
			System.out.println(inst.getVersion() + "\t" + inst.getName());
		} catch (IOException e) {
			throw new FAOException(e);
		} 
	}

	@Test
	public void test_all_constraints() throws FAOException {
		importFet("test-all-constraints.fet");
	}

	@Test
	public void test_2006_2007() throws FAOException {
		importFet("2006_2007.fet");
	}

	@Test
	public void test_2007_2008_sem1_a() throws FAOException {
		importFet("2007_2008_sem1_a.fet");
	}

	@Test
	public void test_2007_2008_sem1_d() throws FAOException {
		importFet("2007_2008_sem1_d.fet");
	}

	@Test
	public void test_2008_09_difficult() throws FAOException {
		importFet("2008_09_difficult.fet");
	}

	@Test
	public void test_2008_2009() throws FAOException {
		importFet("2008_2009.fet");
	}

	@Test
	public void test_6th_Primary_School() throws FAOException {
		importFet("6th_Primary_School.fet");
	}

	@Test
	public void test_8th_highschool() throws FAOException {
		importFet("8th_highschool.fet");
	}

	@Test
	public void test_Arabic_saudi_1() throws FAOException {
		importFet("Arabic_saudi_1.fet");
	}

	@Test
	public void test_Arabic_saudi_2() throws FAOException {
		importFet("Arabic_saudi_2.fet");
	}

	@Test
	public void test_Arabic_saudi_3() throws FAOException {
		importFet("Arabic_saudi_3.fet");
	}

	@Test
	public void test_Arabic_saudi_examination() throws FAOException {
		importFet("Arabic_saudi_examination.fet");
	}

	@Test
	public void test_base2010_23() throws FAOException {
		importFet("base2010_23.fet");
	}

	@Test
	public void test_Bethlen_2007_2008() throws FAOException {
		importFet("Bethlen_2007_2008.fet");
	}

	@Test
	public void test_Bethlen_2008_2009() throws FAOException {
		importFet("Bethlen_2008_2009.fet");
	}

	@Test
	public void test_Bethlen_2008_2009_old() throws FAOException {
		importFet("Bethlen_2008_2009_old.fet");
	}

	@Test
	public void test_Brazil() throws FAOException {
		importFet("Brazil.fet");
	}

	@Test
	public void test_Brazil_more_difficult() throws FAOException {
		importFet("Brazil_more_difficult.fet");
	}

	@Test
	public void test_Collegiate_Junior_School2() throws FAOException {
		importFet("Collegiate_Junior_School2.fet");
	}

	@Test
	public void test_computers_craiova() throws FAOException {
		importFet("computers_craiova.fet");
	}

	@Test
	public void test_ConColY13T1a() throws FAOException {
		importFet("ConColY13T1a.fet");
	}

	@Test
	public void test_CONCORDIA() throws FAOException {
		importFet("CONCORDIA.fet");
	}

	@Test
	public void test_de_secondary_school() throws FAOException {
		importFet("de_secondary_school.fet");
	}

	@Test
	public void test_dgspro200809() throws FAOException {
		importFet("dgspro200809.fet");
	}

	@Test
	public void test_DGS_Pro_Germany() throws FAOException {
		importFet("DGS_Pro_Germany.fet");
	}

	@Test
	public void test_Econ_Timisoara() throws FAOException {
		importFet("Econ_Timisoara.fet");
	}

	@Test
	public void test_econ_tim_ver8() throws FAOException {
		importFet("econ_tim_ver8.fet");
	}

	@Test
	public void test_econ_v0_8() throws FAOException {
		importFet("econ_v0.8.fet");
	}

	@Test
	public void test_EEBLJ_Diurno() throws FAOException {
		importFet("EEBLJ_Diurno.fet");
	}

	@Test
	public void test_EEBLJ_Noturno() throws FAOException {
		importFet("EEBLJ_Noturno.fet");
	}

	@Test
	public void test_eng_college() throws FAOException {
		importFet("eng_college.fet");
	}

	@Test
	public void test_es_secondary_school() throws FAOException {
		importFet("es_secondary_school.fet");
	}

	@Test
	public void test_ET2012_2013_S1() throws FAOException {
		importFet("ET2012_2013_S1.fet");
	}

	@Test
	public void test_ET2012_2013_S2() throws FAOException {
		importFet("ET2012_2013_S2.fet");
	}

	@Test
	public void test_FGPS() throws FAOException {
		importFet("FGPS.fet");
	}

	@Test
	public void test_FS_2008_2009_difficult() throws FAOException {
		importFet("FS_2008_2009_difficult.fet");
	}

	@Test
	public void test_german_100_and_0() throws FAOException {
		importFet("german_100_and_0.fet");
	}

	@Test
	public void test_german_subact_constr() throws FAOException {
		importFet("german_subact_constr.fet");
	}

	@Test
	public void test_Gymnasio() throws FAOException {
		importFet("Gymnasio.fet");
	}

	@Test
	public void test_GYR() throws FAOException {
		importFet("GYR.fet");
	}

	@Test
	public void test_GYR_5_gaps_per_teacher() throws FAOException {
		importFet("GYR_5_gaps_per_teacher.fet");
	}

	@Test
	public void test_GYR_more_difficult() throws FAOException {
		importFet("GYR_more_difficult.fet");
	}

	@Test
	public void test_highlands_christian_school() throws FAOException {
		importFet("highlands_christian_school.fet");
	}

	@Test
	public void test_highschool_Ancona() throws FAOException {
		importFet("highschool_Ancona.fet");
	}

	@Test
	public void test_Hopwood() throws FAOException {
		importFet("Hopwood.fet");
	}

	@Test
	public void test_Horario_ISJ() throws FAOException {
		importFet("Horario_ISJ.fet");
	}

	@Test
	public void test_input_Lectures_40() throws FAOException {
		importFet("input_Lectures_40.fet");
	}

	@Test
	public void test_Insight_Learning_Centre2() throws FAOException {
		importFet("Insight_Learning_Centre2.fet");
	}

	@Test
	public void test_institution_2012_2013() throws FAOException {
		importFet("institution_2012_2013.fet");
	}

	@Test
	public void test_JMSS() throws FAOException {
		importFet("JMSS.fet");
	}

	@Test
	public void test_Khartoum_2010_02_13() throws FAOException {
		importFet("Khartoum_2010_02_13.fet");
	}

	@Test
	public void test_Klikks_SLP() throws FAOException {
		importFet("Klikks_SLP.fet");
	}

	@Test
	public void test_KPS() throws FAOException {
		importFet("KPS.fet");
	}

	@Test
	public void test_Liceo_2010() throws FAOException {
		importFet("Liceo_2010.fet");
	}

	@Test
	public void test_liceo_scientifico() throws FAOException {
		importFet("liceo_scientifico.fet");
	}

	@Test
	public void test_Lom_high_school_2007_2008() throws FAOException {
		importFet("Lom_high_school_2007_2008.fet");
	}

	@Test
	public void test_lycee_Farabi() throws FAOException {
		importFet("lycee_Farabi.fet");
	}

	@Test
	public void test_lycee_Farabi_old() throws FAOException {
		importFet("lycee_Farabi_old.fet");
	}

	@Test
	public void test_lycee_goulmima() throws FAOException {
		importFet("lycee_goulmima.fet");
	}

	@Test
	public void test_MAPS() throws FAOException {
		importFet("MAPS.fet");
	}

	@Test
	public void test_Orar_5_3() throws FAOException {
		importFet("Orar_5_3.fet");
	}

	@Test
	public void test_PBS() throws FAOException {
		importFet("PBS.fet");
	}

	@Test
	public void test_primaria() throws FAOException {
		importFet("primaria.fet");
	}

	@Test
	public void test_PutSS() throws FAOException {
		importFet("PutSS.fet");
	}

	@Test
	public void test_sacred_heart_college() throws FAOException {
		importFet("sacred_heart_college.fet");
	}

	@Test
	public void test_school() throws FAOException {
		importFet("school.fet");
	}

	@Test
	public void test_school_10_oradea_2007_2008() throws FAOException {
		importFet("school_10_oradea_2007_2008.fet");
	}

	@Test
	public void test_Sharif() throws FAOException {
		importFet("Sharif.fet");
	}

	@Test
	public void test_shc_2013() throws FAOException {
		importFet("shc_2013.fet");
	}

	@Test
	public void test_shc_2013_more_difficult() throws FAOException {
		importFet("shc_2013_more_difficult.fet");
	}

	@Test
	public void test_shc_6_gaps_per_teacher() throws FAOException {
		importFet("shc_6_gaps_per_teacher.fet");
	}

	@Test
	public void test_shc_prelim_08() throws FAOException {
		importFet("shc_prelim_08.fet");
	}

	@Test
	public void test_Shipena() throws FAOException {
		importFet("Shipena.fet");
	}

	@Test
	public void test_simpler_italian() throws FAOException {
		importFet("simpler_italian.fet");
	}

	@Test
	public void test_small_school() throws FAOException {
		importFet("small_school.fet");
	}

	@Test
	public void test_StPaulsColY13T1a() throws FAOException {
		importFet("StPaulsColY13T1a.fet");
	}

	@Test
	public void test_telpolytech_even() throws FAOException {
		importFet("telpolytech_even.fet");
	}

	@Test
	public void test_telpolytech_odd() throws FAOException {
		importFet("telpolytech_odd.fet");
	}

	@Test
	public void test_test_2012() throws FAOException {
		importFet("test_2012.fet");
	}

	@Test
	public void test_THE_2008_2009_exams_sep() throws FAOException {
		importFet("THE_2008_2009_exams_sep.fet");
	}

	@Test
	public void test_timetable_July_Nov_09_9() throws FAOException {
		importFet("timetable_July_Nov_09_9.fet");
	}

	@Test
	public void test_v1_7() throws FAOException {
		importFet("v1_7.fet");
	}

	@Test
	public void test_Van_RhynFinal() throws FAOException {
		importFet("Van_RhynFinal.fet");
	}

	@Test
	public void test_WTHS() throws FAOException {
		importFet("WTHS.fet");
	}

}
