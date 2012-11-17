package com.substantial.life.view;

import com.substantial.life.engine.CellRect;
import com.substantial.life.engine.Location;
import com.substantial.life.engine.World;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public interface WorldViewLayer {

	public void render(Canvas canvas);

	public class Background implements WorldViewLayer {
		public void render(Canvas canvas) {
			Paint paint = new Paint();

			paint.setARGB(255, 245, 240, 220);
			Rect wholeScreen = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());
			canvas.drawRect(wholeScreen, paint);
		}
	}

	public class OriginTarget implements WorldViewLayer {
		private GridViewRect gridViewRect;
		private Paint paint;

		public OriginTarget(GridViewRect gridViewRect) {
			this.gridViewRect = gridViewRect;

			this.paint = new Paint();
			paint.setARGB(255, 200, 180, 200);
			paint.setStrokeWidth(1);
		}

		public void render(Canvas canvas) {
			CellRect origin = gridViewRect.getCellRect(0, 0);

			canvas.drawLine(0, origin.top, canvas.getWidth(), origin.top, paint);
			canvas.drawLine(origin.left, 0, origin.left, canvas.getHeight(), paint);
		}
	}

	public class Cells implements WorldViewLayer {
		private GridViewRect gridViewRect;
		private World world;
		private Paint paint;

		public Cells(World world, GridViewRect gridViewRect) {
			this.paint = new Paint();
			paint.setARGB(255, 154, 86, 220);

			this.world = world;
			this.gridViewRect = gridViewRect;
		}

		public void render(Canvas canvas) {
			Rect canvasRect = new Rect(0, 0, canvas.getWidth(), canvas.getHeight());

			for (Location aliveCell : world.getAliveCells()) {
				Rect rect = getRectForCell(aliveCell);
				if (rect.intersect(canvasRect))
					canvas.drawRect(rect, paint);
			}		
		}

		private Rect getRectForCell(Location aliveCell) {
			CellRect cellRect = gridViewRect.getCellRect(aliveCell.x, aliveCell.y);

			return new Rect(cellRect.left, cellRect.top, cellRect.right, cellRect.bottom);
		}

		
	}
	public class TurnCounter implements WorldViewLayer {
		private World world;
		private Paint paint;

		public TurnCounter(World world) {
			this.paint = new Paint();
			paint.setARGB(255, 54, 39, 99);
			paint.setTextSize(16);
			this.world = world;
		}

		public void render(Canvas canvas) {
			canvas.drawText("" + world.getGeneration(), 10, 26, paint);
		}
	}
}
