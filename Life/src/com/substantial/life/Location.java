package com.substantial.life;

import java.util.ArrayList;

public class Location {
	int x;
	int y;
	
	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getX() { return x;}
	public int getY() { return y;}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		
		return x == other.x && y == other.y;			
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