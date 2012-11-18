package com.substantial.life.view;

import com.substantial.life.engine.Location;
import com.substantial.life.engine.World;

import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class WorldTouchHandler implements OnTouchListener {
	final int TAP_THRESHOLD = 30;
	private float downX;
	private float downY;
	private GridViewRect gridViewRect;
	private World world;

	public WorldTouchHandler(GridViewRect gridViewRect, World world) {
		this.gridViewRect = gridViewRect;
		this.world = world;
	}
	
	public boolean onTouch(View view, MotionEvent motionEvent) {
		int action = motionEvent.getAction();
		if (action == MotionEvent.ACTION_DOWN) {
			downX = motionEvent.getX();
			downY = motionEvent.getY();
		}
		if (action == MotionEvent.ACTION_UP) {
			long elapsed = motionEvent.getEventTime() - motionEvent.getDownTime();
			
			if (elapsed > 400)
				return false;
			int x = (int)motionEvent.getX();
			int y = (int)motionEvent.getY();

			if (Math.abs(downX - x) > TAP_THRESHOLD)
				return false;
			if (Math.abs(downY - y) > TAP_THRESHOLD)
				return false;
			
			Location cellAtPoint = gridViewRect.cellAt(x, y);			
			world.toggle(cellAtPoint);
		}
		return true;
	}

}
