package com.substantial.life.test;

import com.substantial.life.World;

import junit.framework.TestCase;

public class WorldTests extends TestCase {
	World world;
	protected void setUp() throws Exception {
		world = new World();
		
		super.setUp();
	}

	public void testAliveAndDeadCells() {
		world.toggle(1,1);
		
		assertEquals(true, world.isAlive(1,1));
		assertEquals(false, world.isAlive(0,2));

		assertEquals(1, world.getAliveCells().size());
	}
	public void testToggleTwice() {
		world.toggle(0,1);
		world.toggle(0,1);
		assertEquals(false, world.isAlive(0,1));
		assertEquals(0, world.getAliveCells().size());
	}
	public void testDiamond() {
		world.toggle(0,1);
		world.toggle(1,2);
		world.toggle(1,0);
		world.toggle(2,1);
		
		world.evolve();
		assertEquals(true, world.isAlive(0,1));
		assertEquals(true, world.isAlive(1,0));
		assertEquals(true, world.isAlive(1,2));
		assertEquals(true, world.isAlive(2,1));
		
		assertEquals(false, world.isAlive(0,2));
		assertEquals(false, world.isAlive(0,0));
		assertEquals(false, world.isAlive(1,1));
		

		assertEquals(4, world.getAliveCells().size());
	}
	
	public void testLine() {
		world.toggle(0,1);
		world.toggle(1,1);
		world.toggle(2,1);
		
		world.evolve();
		assertEquals(true, world.isAlive(1,0));
		assertEquals(true, world.isAlive(1,1));
		assertEquals(true, world.isAlive(1,2));		
		assertEquals(false, world.isAlive(2,1));
		assertEquals(false, world.isAlive(0,1));
		
		world.evolve();
		assertEquals(true, world.isAlive(0,1));
		assertEquals(true, world.isAlive(1,1));
		assertEquals(true, world.isAlive(2,1));		
		assertEquals(false, world.isAlive(1,2));
		assertEquals(false, world.isAlive(1,0));

		assertEquals(3, world.getAliveCells().size());
	}
	
	
	protected void tearDown() throws Exception {
		super.tearDown();
	}

}
