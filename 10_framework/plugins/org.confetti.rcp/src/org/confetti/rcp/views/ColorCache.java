package org.confetti.rcp.views;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;

public enum ColorCache {
	
	INSTANCE;
	
	private static final List<RGB> RGBS = Arrays.asList(
		new RGB(255, 0, 111),
		new RGB(0, 255, 242),
		new RGB(255, 210, 70),
		new RGB(177, 66, 165),
		new RGB(51, 255, 143),
		new RGB(255, 78, 84),
		new RGB(0, 197, 222),
		new RGB(221, 255, 82),
		new RGB(252, 0, 131),
		new RGB(0, 255, 202),
		new RGB(255, 161, 69),
		new RGB(129, 119, 186),
		new RGB(129, 255, 112),
		new RGB(255, 0, 100),
		new RGB(0, 244, 246),
		new RGB(251, 239, 74),
		new RGB(207, 0, 152),
		new RGB(0, 255, 165),
		new RGB(255, 111, 76),
		new RGB(68, 168, 208)
	);

	public static final int COUNT = RGBS.size();
	
	private List<Color> colors;
	
	public Color getColor(int id) {
		if (colors == null) {
			Display display = Display.getCurrent();
			colors = RGBS
					.stream()
					.map(rgb -> new Color(display, rgb))
					.collect(Collectors.toList());
		}
		return colors.get(id % COUNT);
	}
}
