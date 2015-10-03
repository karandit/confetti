package org.confetti.fet.xml.core;

import java.util.function.Function;

import org.confetti.core.Assignment;
import org.confetti.fet.xml.InstituteFAO;
import org.confetti.fet.xml.Institute_v5_24_0_FAO;
import org.confetti.xml.GenericFAO;

public interface InstituteXmlRelease<T extends AbstractInstituteXml> {

	InstituteXmlRelease<InstituteXml> v5_23_4 = new InstituteXmlRelease<InstituteXml>() {

		@Override
		public AbstractInstituteXmlBuilder<InstituteXml> newXmlBuilder(
				NameGetter nameGetter, Function<Assignment, Long> assgIdFunc) {
			return new Institute_v5_23_4_XmlBuilder(nameGetter, assgIdFunc);
		}

		@Override public GenericFAO<InstituteXml> newFAO() { return new InstituteFAO(); }
		
	};
	
	InstituteXmlRelease<Institute_v5_24_0_Xml> v5_24_0 = new InstituteXmlRelease<Institute_v5_24_0_Xml>() {

		@Override
		public AbstractInstituteXmlBuilder<Institute_v5_24_0_Xml> newXmlBuilder(
				NameGetter nameGetter, Function<Assignment, Long> assgIdFunc) {
			return new Institute_v5_24_0_XmlBuilder(nameGetter, assgIdFunc);
		}

		@Override public GenericFAO<Institute_v5_24_0_Xml> newFAO() { return new Institute_v5_24_0_FAO(); }
		
	};
	
	AbstractInstituteXmlBuilder<T> newXmlBuilder(NameGetter getter, Function<Assignment, Long> assgIdFunc);
	GenericFAO<T> newFAO();
}