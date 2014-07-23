package org.confetti.xml.core.time;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * @author Bubla Gabor
 */
@XmlType(propOrder = {"day", "hour"})
public class SelectedTimeXml {
	@XmlElement(name = "Selected_Day") 		private String day;
	@XmlElement(name = "Selected_Hour") 	private String hour;
}