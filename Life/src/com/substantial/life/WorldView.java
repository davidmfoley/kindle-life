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
	int cellWidth = 64;
	int cellHeight = 64;

	private World world;

	public WorldView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
	}

	public WorldView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public WorldView(Context context) {
		super(context);
	}
	
	public void setWorld(World world) {
		this.world = world;
		
		this.setOnTouchListener(new OnTouchListener() {
			public boolean onTouch(View view, MotionEvent motionEvent) {
				if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
					int x = (int)motionEvent.getX() / cellWidth;
					int y = (int)motionEvent.getY() / cellHeight;
					
					Log.d("X",  "" + x);
					Log.d("Y",  "" + y);
					WorldView.this.world.toggle(x, y);
				}
				// TODO Auto-generated method stub
				return false;
			}});
	}
	
	

	@Override
	public void onDraw(Canvas canvas) {
		Paint paint = new Paint();
		paint.setARGB(255, 245, 240, 220);
		Rect wholeScreen = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
		canvas.drawRect(wholeScreen, paint);
		
		for (Location aliveCell : world.getAliveCells()) {
			paint.setARGB(255, 154, 86, 220);
			Rect rect = getRectForCell(aliveCell);
			if (isOnScreen(rect))
				canvas.drawRect(rect, paint);
		}		
	}

	private boolean isOnScreen(Rect rect) {
		// TODO Auto-generated method stub
		return true;
	}

	private Rect getRectForCell(Location aliveCell) {
		
		int left = aliveCell.x * cellWidth;
		int top = aliveCell.y * cellHeight;
		return new Rect(left, top, left + cellWidth, top + cellHeight);
	}

	public void zoomToFitAll() {
		// TODO Auto-generated method stub
		
	}

	public void zoomIn() {
		// TODO Auto-generated method stub
		cellWidth = cellWidth * 2;
		cellHeight = cellHeight * 2;
	}

	public void zoomOut() {
		// TODO Auto-generated method stub
		cellWidth = cellWidth / 2;
		cellHeight = cellHeight / 2;
	}
}
