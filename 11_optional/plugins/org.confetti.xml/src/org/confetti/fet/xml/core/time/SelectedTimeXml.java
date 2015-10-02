package org.confetti.fet.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(propOrder = {"day", "hour"})
public class SelectedTimeXml {

	SelectedTimeXml() {
	}
	public SelectedTimeXml(final String day, final String hour) {
		this.day = day;
		this.hour = hour;
	}
	
	@XmlElement(name = "Selected_Day") 		public String day;
	@XmlElement(name = "Selected_Hour") 	public String hour;
}