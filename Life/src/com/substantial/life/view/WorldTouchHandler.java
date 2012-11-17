package com.substantial.life.view;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class WorldTouchHandler implements OnTouchListener {
	private TouchActionHandler actionHandler;

	public WorldTouchHandler(TouchActionHandler touchActionHandler) {
		this.actionHandler = touchActionHandler;
	}
	public boolean onTouch(View view, MotionEvent motionEvent) {
		if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
			actionHandler.onTap((int)motionEvent.getX(), (int)motionEvent.getY());
			
			
		}
		return true;
	}

	interface TouchActionHandler {
		void onTap(int x, int y);
	}
}
