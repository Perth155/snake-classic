package com.example.perth155.entities;

public class Cell 
{
	private int row;
	private int col;
	
	public Cell(int r, int c)
	{
		this.row = r;
		this.col = c;
	}
	
	public int getRow()
	{
		return this.row;
	}
	
	public int getCol()
	{
		return this.col;
	}
	
	public void setRow(int r)
	{
		this.row = r;
	}
	
	public void setCol(int c)
	{
		this.col = c;
	}

}
