package org.confetti.xml.core;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "days_type", propOrder = {"numbers", "days"})
public class DaysXml {

	private int numbers;
	private List<DayXml> days;

	DaysXml() {
	}
	
	public DaysXml(List<DayXml> days) {
		this.days = days;
		this.numbers = days.size();
	}
	
	@XmlElement(name = "Number")
	public int getNumbers() 				{ return numbers; }
	public void setNumbers(int numbers) 	{ this.numbers = numbers; }

	@XmlElement(name = "Name")
	public List<DayXml> getDays() 			{ return days; }
	public void setDays(List<DayXml> days) 	{ this.days = days; }

}
