package org.confetti.tablix.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "matrix_type")
public class MatrixXml {
	private int width;
	private int height;
	
	@XmlAttribute
	public int getWidth() { return width; }
	public void setWidth(int width) { this.width = width; }
	
	@XmlAttribute
	public int getHeight() { return height; }
	public void setHeight(int height) { this.height = height; }
	
}
