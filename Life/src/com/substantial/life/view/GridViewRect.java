package com.substantial.life.view;

import com.substantial.life.engine.CellRect;
import com.substantial.life.engine.Location;
import com.substantial.life.engine.LocationF;

public class GridViewRect {

	private Location viewSize;
	private LocationF centerCell;
	private float zoomFactor;
	private OnWorldViewChangeListener changeListener = new OnWorldViewChangeListener(){public void onViewChange() {}};

	public void setViewSize(int x, int y) {
		viewSize = new Location(x, y);
		changeListener.onViewChange();
	}

	public void setCenterCell(float x, float y) {
		centerCell = new LocationF(x, y);
		changeListener.onViewChange();
	}

	public void setZoom(float zoomLevel) {
		zoomFactor = zoomLevel;
		changeListener.onViewChange();
	}

	public void setOnChangeListener(
			OnWorldViewChangeListener onWorldViewChangeListener) {
		this.changeListener = onWorldViewChangeListener;
	}

	public Location cellAt(int x, int y) {
		int translatedX = translateScreenToWorld(x, viewSize.x, centerCell.x);
		int translatedY = translateScreenToWorld(y, viewSize.y, centerCell.y);
		return new Location(translatedX, translatedY);
	}

	public CellRect getCellRect(int x, int y) {
		int translatedX = translateWorldToScreen(x, viewSize.x, centerCell.x);
		int translatedY = translateWorldToScreen(y, viewSize.y, centerCell.y);
		int cellSize = (int)zoomFactor - 1;

		return new CellRect(translatedX, translatedY, translatedX + cellSize, translatedY + cellSize);
	}

	private int translateWorldToScreen(int position, int size, float center) {
		float offsetFromCenter = position - center;
		int scaledOffset = (int) (offsetFromCenter * zoomFactor);
		return scaledOffset + (size / 2);
	}

	private int translateScreenToWorld(int position, int size, float center) {
		float offsetFromCenter = (position - ((float)size / 2));
		float scaledOffset = (offsetFromCenter / zoomFactor);
		float rawWorldPosition = (scaledOffset + center);
		if (rawWorldPosition < 0)
			return (int) (rawWorldPosition - 0.99);
		return (int) rawWorldPosition;
	}
}
