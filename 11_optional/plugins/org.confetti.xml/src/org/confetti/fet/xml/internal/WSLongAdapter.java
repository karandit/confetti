package org.confetti.fet.xml.internal;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class WSLongAdapter extends XmlAdapter<String, Long> {
	@Override public String marshal(Long id) throws Exception 		{ return id == null ? "" : id.toString(); }
	@Override public Long unmarshal(String id) throws Exception 	{ return Long.parseLong(id); }
}