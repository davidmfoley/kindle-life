package com.substantial.life.view;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.substantial.life.engine.World;

public class WorldView extends ImageView {
	private World world;
	private GridViewRect gridViewRect;
	private int zoomLevel = 64;
	private WorldRenderer renderer;

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
		
		renderer = new WorldRenderer(world, gridViewRect);
		
			
		WorldTouchHandler touchHandler = new WorldTouchHandler(this.gridViewRect, this.world);
		
		gridViewRect.setOnChangeListener(new OnWorldViewChangeListener(){
			public void onViewChange() {
				WorldView.this.postInvalidate();				
			}
		});
		
		this.setOnTouchListener(touchHandler);
	}

	@Override
	public void onDraw(Canvas canvas) {
		gridViewRect.setViewSize(canvas.getWidth(), canvas.getHeight());
		renderer.render(canvas);
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
