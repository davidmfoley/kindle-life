package com.substantial.life.test;

import com.substantial.life.CellRect;
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

	public void testZoomLevelOneAtOriginTopLeft() {
		setUpWorldView(3, 3, 0.5f, 0.5f, 1.0f);
		
		Location topLeft = gridViewRect.cellAt(0, 0);
		
		assertEquals(-1, topLeft.getX().intValue());
		assertEquals(-1, topLeft.getY().intValue());
	}

	public void testZoomLevelOneAtOriginBottomRight() {
		setUpWorldView(3, 3, 0.5f, 0.5f, 1.0f);
		
		Location bottomRight = gridViewRect.cellAt(2,2);
		
		assertEquals(1, bottomRight.getX().intValue());
		assertEquals(1, bottomRight.getY().intValue());
	}
	
	public void testZoomedOutAtOriginTopLeft() {
		
		setUpWorldView(10, 6, 5, 3, 0.5f);
		
		Location topLeft = gridViewRect.cellAt(0, 0);
		
		assertEquals(-5, topLeft.getX().intValue());
		assertEquals(-3, topLeft.getY().intValue());
	}

	public void testZoomedInAtOriginBottomRight() {
		
		setUpWorldView(300, 200, 0, 0, 4f);
		
		Location bottomRight = gridViewRect.cellAt(299,199);
		
		assertEquals(37, bottomRight.getX().intValue());
		assertEquals(24, bottomRight.getY().intValue());
	}
	
	public void testAwayFromOriginTopLeft() {

		setUpWorldView(4, 6, 12, 3, 1.0f);
		
		Location topLeft = gridViewRect.cellAt(0, 0);
		
		assertEquals(10, topLeft.getX().intValue());
		assertEquals(0, topLeft.getY().intValue());
	}

	public void testAwayFromOriginBottomRight() {
		
		setUpWorldView(4, 6, 12, 3, 1.0f);
		
		Location bottomRight = gridViewRect.cellAt(3,5);
		
		assertEquals(13, bottomRight.getX().intValue());
		assertEquals(5, bottomRight.getY().intValue());
	}
	
	public void testGetCellRectNotZoomed() {
		
		setUpWorldView(100, 200, 0, 0, 1.0f);
		
		CellRect rect = gridViewRect.getCellRect(49, 99);
		
		assertEquals(99, rect.left);
		assertEquals(199, rect.top);
		assertEquals(99, rect.right);
		assertEquals(199, rect.bottom);
		
	}
	
	public void testGetCellRectZoomed() {
		
		setUpWorldView(100, 200, 0, 0, 10.0f);
		
		CellRect rect = gridViewRect.getCellRect(4, 6);
		
		assertEquals(90, rect.left);
		assertEquals(160, rect.top);
		assertEquals(99, rect.right);
		assertEquals(169, rect.bottom);
	}


	private void setUpWorldView(int sizeX, int sizeY, float centerX, float centerY, float zoom) {
		gridViewRect.setViewSize(sizeX, sizeY);
		gridViewRect.setCenterCell(centerX, centerY);
		gridViewRect.setZoom(zoom);
	}
}
