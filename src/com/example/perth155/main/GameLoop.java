package com.example.perth155.main;

import com.example.perth155.entities.Constants;
import com.example.perth155.entities.GridManager;
import com.example.perth155.gui.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class GameLoop
{
	private boolean gameRunning;
	private Window disp;
	private GridManager gridList;
	private int score;
	private Timer timer;

	public GameLoop(Window w)
	{
		gameRunning = true;
		disp = w;
		System.out.println(w.getContentPane().getSize().width + " x " + w.getContentPane().getSize().height );
		gridList = disp.getGridList();
		score = 0;
		checkHighScoreUpdate();
	    timer = new Timer();
	    timer.schedule(new Turn(), 0, 1000 / 10);
	    setUpResetButton();
	}


	private class Turn extends TimerTask
	{

		@Override
		public void run()
		{
			disp.getContent().requestFocus();
			gridList.snakeHeadMovement();
			disp.render();
			checkGameState();
			// TODO Auto-generated method stub
			if(!gameRunning)
			{
				setHighScore(score);
				timer.cancel();
			}
		}

	}

	private void checkGameState()
	{
		gameRunning = gridList.snakeDeath();
		if(gridList.ItemObtained())
		{
			gridList.spawnItem(Constants.ROWS);
			gridList.getSnake().snakeExpansion();
		}
	}


	private void checkHighScoreUpdate()
	{
		try {
			Scanner hscan = new Scanner(new File("res/score/score.txt"));
			score = hscan.nextInt();
			hscan.close();
		} catch (FileNotFoundException e) {
			score = 0;
		}
		disp.updateHighScore(score);
	}

	private void setHighScore(int s)
	{
		disp.setGameOverText("Game Over");
		if(score < disp.getScore())
		{
			score = disp.getScore();
			disp.setNewHighScore(disp.getScore());
			System.out.println("New High Score!");
		}
	}


	private void pause()
	{
		this.timer.cancel();
	}

	private void resume()
	{
		timer = new Timer();
		timer.schedule(new Turn(), 0, 1000 / 10);
	}

	private void sleep(int timeInMilliSec)
	{
		try {
			Thread.sleep(timeInMilliSec);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void setUpResetButton()
	{
		JButton resetButton = disp.getRestartButton();
		resetButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				pause();
				sleep(1000);
				disp.getContent().requestFocus();
				disp.reset();
				resume();
				disp.setAllFontsToDefaults() ;
			}
		});
	}

}
