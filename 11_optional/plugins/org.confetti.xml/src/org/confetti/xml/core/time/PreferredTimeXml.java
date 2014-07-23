package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(propOrder = {"day", "hour"})
public class PreferredTimeXml {
	
	private String day;
	private String hour;
	
	@XmlElement(name = "Preferred_Day")
	public String getDay() { return day; }
	public void setDay(String day) { this.day = day; }

	@XmlElement(name = "Preferred_Hour")
	public String getHour() { return hour; }
	public void setHour(String hour) { this.hour = hour; }
}
