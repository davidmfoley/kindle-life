package com.substantial.life;
import java.util.ArrayList;


public class Location extends LocationBase<Integer> 
{
	public Location(Integer x, Integer y) {
		super(x, y);		// TODO Auto-generated constructor stub
	}

	public ArrayList<Location> getNeighbors() {
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

