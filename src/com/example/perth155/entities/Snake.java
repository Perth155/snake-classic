package com.example.perth155.entities;


import java.util.List;
import java.util.LinkedList;
import com.example.perth155.gameplay.Constants;

/**
 * Snake class
 * @author perth155
 */
public class Snake
{
	private int point; //point scored by the snake
	private int xVel;
	private int yVel;
	private int speed;
	private Cell head;
	private List<Cell> body;
	private Cell vacant;
	//private int[] rgbColor;

	public Snake()
	{
		setSnake();
		this.point = 0;
		this.xVel = 0;
		this.yVel = 1;
		this.speed = 300;
	}

	/**
	 * Sets up the snake, default starting pos remains same for every game.
	 */
	private void setSnake()
	{
		this.head = new Cell(10, 3, Constants.SNAKE_HEAD);
		this.vacant = new Cell(10, 0, Constants.EMPTY);
		this.body = new LinkedList<Cell>();
		Cell c1 = new Cell(10, 2, Constants.SNAKE_BODY);
		Cell c2 = new Cell(10, 1, Constants.SNAKE_BODY);
		body.add(c1);
		body.add(c2);
	}

	public int getSpeed()
	{
		return this.speed;
	}

	public Cell getHead()
	{
		return this.head;
	}

	public Cell getVacant()
	{
		return this.vacant;
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


	public void setVelocity(char dimension, int vel)
	{
		if(dimension=='y')
		{
			this.yVel = vel;
			this.xVel = 0;
		}
		else{
			this.xVel = vel;
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

	public void setSpeed(int s)
	{
		this.speed = s;
	}

	public void snakeMove()
	{
		int prevRow = head.getRow();
		int prevCol = head.getCol();
		head.setRow((Constants.ROWS + prevRow + yVel)%Constants.ROWS);
		head.setCol((Constants.COLS + prevCol + xVel)%Constants.COLS);

		for(int x = 0; x < this.body.size(); x++)
		{
			int tempRow = body.get(x).getRow();
			int tempCol = body.get(x).getCol();
			body.get(x).setRow(prevRow);
			body.get(x).setCol(prevCol);
			prevRow = tempRow;
			prevCol = tempCol;
		}
		vacant.setRow(prevRow);
		vacant.setCol(prevCol);
		//vacant.setCol(c);
	}
}
