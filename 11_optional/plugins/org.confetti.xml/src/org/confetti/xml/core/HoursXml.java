package org.confetti.xml.core;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "hours_type", propOrder = {"numbers", "hours"})
public class HoursXml {
	
	private int numbers;
	private List<HourXml> hours = new ArrayList<>();
	
	HoursXml() {
	}
	
	public HoursXml(List<HourXml> hours) {
		this.hours = hours;
		this.numbers = hours.size();
	}
	
	@XmlElement(name = "Number")
	public int getNumbers() 					{ return numbers; }
	public void setNumbers(int numbers) 		{ this.numbers = numbers; }
	
	@XmlElement(name = "Name")
	public List<HourXml> getHours() 			{ return hours; }
	public void setHours(List<HourXml> hours) 	{ this.hours = hours; }
	
}
