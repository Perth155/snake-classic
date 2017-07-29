package com.example.perth155.gameplay;

import com.example.perth155.gui.Window;

public class GameLoop
{
  private boolean gameRunning;
	private Window disp;

	public GameLoop(Window w)
	{
		this.gameRunning = true;
		disp = w;
	}

	public void run()
	{
		while(gameRunning)
		{
			disp.getGridList().snakeHeadMovement();
			try
			{
				Thread.sleep(disp.getSnake().getSpeed());
			}
			catch(InterruptedException ex)
			{
				Thread.currentThread().interrupt();
			}
			disp.render();
		}
	}
}
