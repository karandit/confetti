package org.confetti.xml.core;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "hours_type", propOrder = {"numbers", "hours"})
public class Hours_v5_24_0_Xml {
	
	private int numbers;
	private List<Hour_v5_24_0_Xml> hours = new ArrayList<>();
	
	Hours_v5_24_0_Xml() {
	}
	
	public Hours_v5_24_0_Xml(List<Hour_v5_24_0_Xml> hours) {
		this.hours = hours;
		this.numbers = hours.size();
	}
	
	@XmlElement(name = "Number_of_Hours")
	public int getNumbers() 					{ return numbers; }
	public void setNumbers(int numbers) 		{ this.numbers = numbers; }
	
	@XmlElement(name = "Hour")
	public List<Hour_v5_24_0_Xml> getHours() 			{ return hours; }
	public void setHours(List<Hour_v5_24_0_Xml> hours) 	{ this.hours = hours; }
	
}
