package com.substantial.life;

import com.substantial.life.Location;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

public class WorldView extends ImageView {
	private World world;
	private GridViewRect gridViewRect;
	private int zoomLevel = 64;

	public WorldView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public WorldView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public WorldView(Context context) {
		super(context);
	}
	
	public void setWorld(World world) {
		this.world = world;
		gridViewRect = new GridViewRect();
		gridViewRect.setZoom(64);
		gridViewRect.setCenterCell(0, 0);
		
		
		this.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					Location cellAtPoint = gridViewRect.cellAt((int)motionEvent.getX(), (int)motionEvent.getY());
					
					WorldView.this.world.toggle(cellAtPoint);
				}
				return false;
			}});
	}

	@Override
	public void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		gridViewRect.setViewSize(this.getWidth(), this.getHeight());
		
		paint.setARGB(255, 245, 240, 220);
		Rect wholeScreen = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
		canvas.drawRect(wholeScreen, paint);
		
		paint.setARGB(255, 200, 180, 200);
		paint.setStrokeWidth(1);
		
		CellRect origin = gridViewRect.getCellRect(0, 0);
		
		canvas.drawLine(0, origin.top, getWidth(), origin.top, paint);
		canvas.drawLine(origin.left, 0, origin.left, getHeight(), paint);
		
		for (Location aliveCell : world.getAliveCells()) {
			paint.setARGB(255, 154, 86, 220);
			Rect rect = getRectForCell(aliveCell);
			if (isOnScreen(rect))
				canvas.drawRect(rect, paint);
		}		
	}

	private boolean isOnScreen(Rect rect) {
		return rect.intersects(0, 0, getWidth(), getHeight());
	}

	private Rect getRectForCell(Location aliveCell) {
		CellRect cellRect = gridViewRect.getCellRect(aliveCell.x, aliveCell.y);
		
		return new Rect(cellRect.left, cellRect.top, cellRect.right, cellRect.bottom);
	}

	public void zoomToFitAll() {
		
	}

	public void zoomIn() {
		gridViewRect.setZoom(zoomLevel *= 2);
	}

	public void zoomOut() {
		gridViewRect.setZoom(zoomLevel /= 2);
	}
}
