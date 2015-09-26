package org.confetti.xml.core;

import java.util.function.Function;

import org.confetti.core.Assignment;
import org.confetti.core.DataProvider;

public class Institute_v5_23_4_XmlBuilder extends AbstractInstituteXmlBuilder<InstituteXml> {

	public Institute_v5_23_4_XmlBuilder(NameGetter nameGetter, Function<Assignment, Long> getAssgIdFunc) {
		super(nameGetter, getAssgIdFunc);
	}

	@Override
	protected InstituteXml newXml(String name, String version, String comment) {
		return new InstituteXml(name, version, comment);
	}

	@Override
	protected void setMembers(InstituteXml inst, DataProvider dp) {
		inst.setDays(new DaysXml(dp.getDays()		.toList(GET_NAME.andThen(DayXml::new))));
		inst.setHours(new HoursXml(dp.getHours()	.toList(GET_NAME.andThen(HourXml::new))));
	}

}