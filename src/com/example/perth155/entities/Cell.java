package com.example.perth155.entities;

public class Cell
{
	private int row;
	private int col;
	private int fill;

	public Cell(int r, int c, int f)
	{
		this.row = r;
		this.col = c;
		this.fill = f;
	}

	public int getRow()
	{
		return this.row;
	}

	public int getCol()
	{
		return this.col;
	}

	public int getFill()
	{
		return this.fill;
	}

	public void setFill(int f)
	{
		this.fill = f;
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
