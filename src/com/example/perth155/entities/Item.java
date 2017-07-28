package com.example.perth155.entities;

public class Item 
{
	private Cell location;
	private int[] rgbColor; 
	
	
	public Item(int r, int c)
	{
		location = new Cell(r,c);
		rgbColor = new int[3];
	}
	
	public Cell getLocation()
	{
		return this.location;
	}
}
