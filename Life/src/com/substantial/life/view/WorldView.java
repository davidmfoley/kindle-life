package com.substantial.life.view;

import java.util.ArrayList;

import com.substantial.life.engine.Location;
import com.substantial.life.engine.World;
import com.substantial.life.view.WorldTouchHandler.TouchActionHandler;

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
	private ArrayList<WorldViewLayer> renderLayers = new ArrayList<WorldViewLayer>();

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
		
		renderLayers = new ArrayList<WorldViewLayer>();
		renderLayers.add(new WorldViewLayer.Background());
		renderLayers.add(new WorldViewLayer.OriginTarget(gridViewRect));
		renderLayers.add(new WorldViewLayer.Cells(world, gridViewRect));
		renderLayers.add(new WorldViewLayer.TurnCounter(world));
		
		WorldTouchHandler touchHandler = new WorldTouchHandler(new TouchActionHandler() {
			public void onTap(int x, int y) {
				Location cellAtPoint = gridViewRect.cellAt(x, y);			
				WorldView.this.world.toggle(cellAtPoint);
			}
		});
		
		this.setOnTouchListener(touchHandler);
	}

	@Override
	public void onDraw(Canvas canvas) {
		gridViewRect.setViewSize(canvas.getWidth(), canvas.getHeight());

		for (WorldViewLayer layer : renderLayers) {
			layer.render(canvas);
		}	
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
