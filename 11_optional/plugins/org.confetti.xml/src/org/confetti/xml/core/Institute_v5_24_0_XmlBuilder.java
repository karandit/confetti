package org.confetti.xml.core;

import java.util.function.Function;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;

public class Institute_v5_24_0_XmlBuilder extends AbstractInstituteXmlBuilder<Institute_v5_24_0_Xml> {

	public Institute_v5_24_0_XmlBuilder(NameGetter nameGetter, Function<Assignment, Long> getAssgIdFunc) {
		super(nameGetter, getAssgIdFunc);
	}

	@Override
	protected Institute_v5_24_0_Xml newXml(String name, String version, String comment) {
		return new Institute_v5_24_0_Xml(name, version, comment);
	}

	@Override
	protected void setMembers(Institute_v5_24_0_Xml inst, DataProvider dp) {
		inst.setDays(new Days_v5_24_0_Xml(dp.getDays()		.toList(GET_NAME.andThen(Day_v5_24_0_Xml::new))));
		inst.setHours(new Hours_v5_24_0_Xml(dp.getHours()	.toList(GET_NAME.andThen(Hour_v5_24_0_Xml::new))));
	}

}
