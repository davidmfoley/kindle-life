package com.substantial.life.test;

import com.substantial.life.GridViewRect;
import com.substantial.life.Location;

import junit.framework.TestCase;

public class GridViewRectTests extends TestCase {
	GridViewRect gridViewRect;
	protected void setUp() throws Exception {
		gridViewRect = new GridViewRect();
		super.setUp();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testZoomLevelOneAtOrigin() {
		gridViewRect.setViewSize(3, 3);
		gridViewRect.setCenterCell(1,1);
		gridViewRect.setZoom(1.0);
		
		Location topLeft = gridViewRect.cellAt(0, 0);
		Location bottomRight = gridViewRect.cellAt(2,2);
		
		assertEquals(0, topLeft.getX());
		assertEquals(0, topLeft.getY());
		
		assertEquals(2, bottomRight.getX());
		assertEquals(2, bottomRight.getY());
	}
}
