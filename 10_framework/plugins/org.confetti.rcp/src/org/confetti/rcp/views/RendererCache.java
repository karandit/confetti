package org.confetti.rcp.views;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;

import de.kupzog.ktable.renderers.TextCellRenderer;

public enum RendererCache {
	INSTANCE;
	
	private Map<Integer, TextCellRenderer> renderers = new HashMap<>();
	
	public TextCellRenderer getRenderer(final int colorId) {
		if (!renderers.containsKey(colorId)) {
			TextCellRenderer renderer = new TextCellRenderer(SWT.NONE);
			renderer.setBackground(ColorCache.INSTANCE.getColor(colorId));
			renderers.put(colorId, renderer);
			return renderer;
		}
		return renderers.get(colorId);
	}
}
