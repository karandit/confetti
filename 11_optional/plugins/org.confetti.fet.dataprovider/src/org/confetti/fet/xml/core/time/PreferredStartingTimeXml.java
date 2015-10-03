package org.confetti.fet.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(propOrder = {"day", "hour"})
public class PreferredStartingTimeXml {
	
	PreferredStartingTimeXml() {
	}
	
	public PreferredStartingTimeXml(final String day, final String hour) {
		this.day = day;
		this.hour = hour;
	}
	
	private String day;
	private String hour;
	
	@XmlElement(name = "Preferred_Starting_Day")
	public String getDay() { return day; }
	public void setDay(String day) { this.day = day; }

	@XmlElement(name = "Preferred_Starting_Hour")
	public String getHour() { return hour; }
	public void setHour(String hour) { this.hour = hour; }
}
