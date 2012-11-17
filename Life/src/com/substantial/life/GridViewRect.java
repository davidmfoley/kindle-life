package com.substantial.life;

import com.substantial.life.Location;
import com.substantial.life.LocationF;

public class GridViewRect {

	private Location viewSize;
	private LocationF centerCell;
	private float zoomFactor;

	public void setViewSize(int x, int y) {
		viewSize = new Location(x, y);
	}

	public void setCenterCell(float x, float y) {
		
		centerCell = new LocationF(x, y);
	}

	public void setZoom(float zoomLevel) {
		zoomFactor = zoomLevel;
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
		float offsetFromCenter = (float)position - center;
		int scaledOffset = (int) (offsetFromCenter * zoomFactor);
		return scaledOffset + (size / 2);
	}

	private int translateScreenToWorld(int position, int size, float center) {
		float offsetFromCenter = ((float)position - ((float)size / 2));
		float scaledOffset = (offsetFromCenter / zoomFactor);
		float rawWorldPosition = (scaledOffset + center);
		if (rawWorldPosition < 0)
			return (int) (rawWorldPosition - 0.99);
		return (int) rawWorldPosition;
	}
}
