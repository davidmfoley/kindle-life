package com.substantial.life.engine;

public abstract class LocationBase<T extends Number> {
	public T x;
	public T y;

	public LocationBase(T x, T y) {
		this.x = x;
		this.y = y;
	}

	public T getX() { return x;}
	public T getY() { return y;}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x.hashCode();
		result = prime * result + y.hashCode();
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
		LocationBase<T> other = (LocationBase<T>) obj;

		return x == other.x && y == other.y;
	}
}
