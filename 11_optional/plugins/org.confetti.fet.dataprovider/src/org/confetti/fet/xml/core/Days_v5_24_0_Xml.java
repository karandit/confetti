package org.confetti.fet.xml.core;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(name = "days_type", propOrder = {"numbers", "days"})
public class Days_v5_24_0_Xml {

	private int numbers;
	private List<Day_v5_24_0_Xml> days = new ArrayList<>();

	Days_v5_24_0_Xml() {
	}
	
	public Days_v5_24_0_Xml(List<Day_v5_24_0_Xml> days) {
		this.days = days;
		this.numbers = days.size();
	}
	
	@XmlElement(name = "Number_of_Days")
	public int getNumbers() 				{ return numbers; }
	public void setNumbers(int numbers) 	{ this.numbers = numbers; }

	@XmlElement(name = "Day")
	public List<Day_v5_24_0_Xml> getDays() 				{ return days; }
	public void setDays(List<Day_v5_24_0_Xml> days) 	{ this.days = days; }

}
