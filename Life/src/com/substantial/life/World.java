package com.substantial.life;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.substantial.life.World.Location;

public class World {
	ArrayList<Location> aliveCells;
	private Runnable onChangeHandler;

	public World() {
		aliveCells = new ArrayList<Location>();
		onChangeHandler = new Runnable() {
			public void run() {}
		};
	}
	
	public class Location {
		int x;
		int y;
		public Location(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getOuterType().hashCode();
			result = prime * result + x;
			result = prime * result + y;
			return result;
		}
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Location other = (Location) obj;
			if (!getOuterType().equals(other.getOuterType()))
				return false;
			return x == other.x && y == other.y;			
		}
		
		private World getOuterType() {
			return World.this;
		}
		public ArrayList<Location> getNeighbors() {
			// TODO Auto-generated method stub
			ArrayList<Location> locations = new ArrayList<Location>();
			
			locations.add(new Location(x-1, y-1));
			locations.add(new Location(x, y-1));
			locations.add(new Location(x+1, y-1));
			locations.add(new Location(x-1, y));
			locations.add(new Location(x+1, y));
			locations.add(new Location(x-1, y+1));
			locations.add(new Location(x, y+1));
			locations.add(new Location(x+1, y+1));
			
			return locations;
		}
	}

	public void toggle(int x, int y) {
		Location location = new Location(x, y);
		if (!isAlive(x, y))
			aliveCells.add(location);
		else
			aliveCells.remove(location);
		onChangeHandler.run();
	}
	
	public void set(int x, int y, boolean alive) {
		if (isAlive(x, y) == alive)
			return;
		toggle(x, y);
	}

	public boolean isAlive(int x, int y) {
		// TODO Auto-generated method stub
		return aliveCells.contains(new Location(x,y));
	}

	public void evolve() {
		ArrayList<Location> nextGeneration = new ArrayList<Location>();
		HashMap<Location, Integer> touchedCells = new HashMap<Location, Integer>();
		// TODO Auto-generated method stub
		for (Location aliveCell : aliveCells) {
			touchNeighbors(touchedCells, aliveCell);
		}
		for (Location touchedCell : touchedCells.keySet()) {
			Integer neighborCount = touchedCells.get(touchedCell);
			boolean wasAlive = aliveCells.contains(touchedCell);
			boolean aliveNextGen = (neighborCount == 3) || (neighborCount == 2 && wasAlive);
			if (aliveNextGen)
				nextGeneration.add(touchedCell);
		}
		aliveCells = nextGeneration;
		onChangeHandler.run();
	}

	private void touchNeighbors(HashMap<Location, Integer> touchCollector, Location aliveCell) {
		// TODO Auto-generated method stub
		for (Location neighbor : aliveCell.getNeighbors()) {
			if (!touchCollector.containsKey(neighbor))
				touchCollector.put(neighbor, 1);
			else
				touchCollector.put(neighbor, touchCollector.get(neighbor) + 1);
		}
	}

	public List<Location> getAliveCells() {
		// TODO Auto-generated method stub
		return (List<Location>) aliveCells.clone();
	}

	public void setOnChangeHandler(Runnable runnable) {
		// TODO Auto-generated method stub
		onChangeHandler = runnable;
	}
}
