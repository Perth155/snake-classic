package com.example.perth155.gameplay;

import com.example.perth155.entities.GridManager;
import com.example.perth155.gui.Window;

public class GameLoop
{
	private boolean gameRunning;
	private Window disp;
	private GridManager gridList;

	public GameLoop(Window w)
	{
		this.gameRunning = true;
		disp = w;
		gridList = disp.getGridList();
	}

	public void run()
	{
		while(gameRunning)
		{
			gridList.snakeHeadMovement();
			try
			{
				Thread.sleep(disp.getSnake().getSpeed());
				//Thread.sleep(1000);
			}
			catch(InterruptedException ex)
			{
				Thread.currentThread().interrupt();
			}
			disp.render();
			System.out.println(disp.getSnake().getHead().getRow() + "  "+ disp.getSnake().getHead().getCol()  );
			checkGameState();
		}
	}

	private void checkGameState()
	{
//		disp.getGridList().getSnake().getHead())
		gameRunning = gridList.snakeDeath();
		if(gridList.ItemObtained())
		{
			gridList.spawnItem(Constants.ROWS);
			gridList.getSnake().snakeExpansion();
		}
	}



}
