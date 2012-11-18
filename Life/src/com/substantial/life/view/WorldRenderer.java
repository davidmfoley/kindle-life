package com.substantial.life.view;

import java.util.ArrayList;

import com.substantial.life.engine.World;

import android.graphics.Canvas;

public class WorldRenderer {
	private ArrayList<WorldViewLayer> renderLayers = new ArrayList<WorldViewLayer>();

	public WorldRenderer(World world, GridViewRect gridViewRect) {
		renderLayers = new ArrayList<WorldViewLayer>();
		renderLayers.add(new WorldViewLayer.Background());
		renderLayers.add(new WorldViewLayer.OriginTarget(gridViewRect));
		renderLayers.add(new WorldViewLayer.Cells(world, gridViewRect));
		renderLayers.add(new WorldViewLayer.TurnCounter(world));
	}

	public void render(Canvas canvas) {
		for (WorldViewLayer layer : renderLayers) {
			layer.render(canvas);
		}	
	}
}
