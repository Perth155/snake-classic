package com.example.perth155.entities;


import java.util.List;
import java.util.LinkedList;

/**
 * Snake class
 * @author perth155
 */
public class Snake 
{
	private int point; //point scored by the snake
	private int xVel; 
	private int yVel;
	private Cell head;
	private List<Cell> body;
	private int[] rgbColor;
	
	public Snake()
	{
		this.head = new Cell(10, 2); 
		this.rgbColor = new int[3];
		this.point = 0;
		this.xVel = 0;
		this.yVel = 1;
	}
	
	public Cell getHead()
	{
		return this.head;
	}
	
	public List<Cell> getBody()
	{
		return this.body;
	}
	
	public int getPoint()
	{
		return this.point;
	}
	
	public int getVelocity(char dimension)
	{
		if(dimension=='x')
			return xVel;
		else 
			return yVel;
	}
	
	
	public void setVelocity(char dimension)
	{
		if(dimension=='y')
		{
			this.yVel = this.xVel;
			this.xVel = 0;
		}
		else{
			this.xVel = this.yVel;
			this.yVel = 0;
		}
	}
	
	public void setHead(int x, int y)
	{
		head.setRow(x);
		head.setCol(y);
	}
	
	public void setPoint(int p)
	{
		this.point = p;
	}
}
