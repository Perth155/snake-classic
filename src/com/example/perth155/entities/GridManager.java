package com.example.perth155.entities;

import com.example.perth155.gameplay.Constants;
import java.util.Random;

public class GridManager
{
	private Cell[][] grids;
	private Item item;
	private Snake sn;

	public GridManager(int size)
	{
		grids = new Cell[size][size];
		sn = new Snake();
		spawnItem(size);
		setUpGrids(size);
	}


	/**
	 * Randomly spawn an item and make sure it is not inside the snake.
	 */
	public void spawnItem(int size)
	{
		boolean insideSnake = true;
		while(insideSnake)
		{
			Random rand = new Random();
			int r = rand.nextInt(size);
			int c = rand.nextInt(size);
			System.out.println(r + " "+c);
			int count = 0;

			if(r == sn.getHead().getRow() && c == sn.getHead().getRow())
				continue;
			if(r == sn.getVacant().getRow() && c == sn.getVacant().getCol())
				continue;
			for(int i = 0; i < sn.getBody().size(); i++)
			{
				if((sn.getBody().get(i).getRow() == r) && (sn.getBody().get(i).getCol() == c))
					break;
				else
					count++;
			}
			if(count == sn.getBody().size())
			{
				insideSnake = false;
				item = new Item(r, c);
			}
		}
	}



	private void setUpGrids(int s)
	{
		for(int i = 0; i< grids.length; i++)
		{
			for(int j = 0; j<grids[i].length;j++)
			{
				Cell c;
				if(i == item.getLocation().getRow() && j == item.getLocation().getCol())
					c = new Cell(i, j, Constants.ITEM);
				else if(i==sn.getHead().getRow() && j == sn.getHead().getCol())
					c = new Cell(i, j, Constants.SNAKE_HEAD);
				else
					c = new Cell(i, j, Constants.EMPTY);
				grids[i][j] = c;
			}
		}
	}

	public void snakeHeadMovement()
	{
		Cell snakeHead = sn.getHead();
		grids[snakeHead.getRow()][snakeHead.getCol()].setFill(Constants.EMPTY);
		sn.snakeMove();
		snakeHead = sn.getHead();
		grids[snakeHead.getRow()][snakeHead.getCol()].setFill(Constants.SNAKE_HEAD);
	}

	public Cell getCellState(int r, int c)
	{
		return grids[r][c];
	}

	public Snake getSnake()
	{
		return this.sn;
	}

	public Item getItem()
	{
		return this.item;
	}

	/**
	 * True if snake has eaten the item, false otherwise
	 * @return state of the item. True if obtained.
	 */
	public boolean ItemObtained()
	{
		if(sn.getHead().getRow() == item.getLocation().getRow() &&
				sn.getHead().getCol() == item.getLocation().getCol())
		{
			System.out.println(true);
			return true;
		}
		return false;
	}


	/**
	 * False if snake has eaten the itself to indicate gameover,
	 * otherwise true returned.
	 * @return state of the game, true if running.
	 */
	public boolean snakeDeath()
	{
		for(int i = 0; i < sn.getBody().size(); i++)
		{
			if(sn.getHead().getRow() == sn.getBody().get(i).getRow() &&
					sn.getHead().getCol() == sn.getBody().get(i).getCol())
			{
				return false;
			}
		}
		return true;
	}

}
