package com.example.perth155.entities;
import com.example.perth155.util.Constants;

public class Item
{
	private Cell location;

	public Item(int r, int c)
	{
		location = new Cell(r,c, Constants.ITEM);
	}

	public Cell getLocation()
	{
		return this.location;
	}
}
