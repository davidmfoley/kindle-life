package com.substantial.life;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.substantial.life.Location;

public class World {
	ArrayList<Location> aliveCells;
	private Runnable onChangeHandler;

	public World() {
		aliveCells = new ArrayList<Location>();
		onChangeHandler = new Runnable() {
			public void run() {}
		};
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
		return aliveCells.contains(new Location(x,y));
	}

	public void evolve() {
		HashMap<Location, Integer> touchedCells = new HashMap<Location, Integer>();
		
		for (Location aliveCell : (ArrayList<Location>)aliveCells.clone()) {
			touchNeighbors(touchedCells, aliveCell);
		}
		
		ArrayList<Location> nextGeneration = new ArrayList<Location>();
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
