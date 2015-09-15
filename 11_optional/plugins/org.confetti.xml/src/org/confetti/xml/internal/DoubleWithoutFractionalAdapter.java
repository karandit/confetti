package org.confetti.xml.internal;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DoubleWithoutFractionalAdapter extends XmlAdapter<String, Double> {
	@Override
	public String marshal(Double d) throws Exception {
		double dValue = d.doubleValue();
		if (dValue == Math.floor(dValue)) {
			return Integer.toString(d.intValue());
		}
		return d.toString();
	}

	@Override public Double unmarshal(String s) throws Exception 	{ return Double.parseDouble(s); }
}