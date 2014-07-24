package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(propOrder = {"day", "hour"})
public class BreakTimeXml {
	
	private String day;
	private String hour;
	
	@XmlElement(name = "Day")
	public String getDay() { return day; }
	public void setDay(String day) { this.day = day; }

	@XmlElement(name = "Hour")
	public String getHour() { return hour; }
	public void setHour(String hour) { this.hour = hour; }
	
}
