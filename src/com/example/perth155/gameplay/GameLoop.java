package com.example.perth155.gameplay;

import com.example.perth155.entities.GridManager;
import com.example.perth155.gui.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JButton;

public class GameLoop
{
	private boolean gameRunning;
	private boolean dead;
	private Window disp;
	private GridManager gridList;
	private int score;
	private Timer timer;

	public GameLoop(Window w)
	{
		this.gameRunning = true;
		this.dead = false;
		disp = w;
		setUpResetButton();
		gridList = disp.getGridList();
		score = 0;
		checkHighScoreUpdate();
	    timer = new Timer();
	    timer.schedule(new Turn(), 0, 1000 / 10); 
	}

	
	private class Turn extends TimerTask
	{

		@Override
		public void run() 
		{
			gridList.snakeHeadMovement();
			disp.render();
			//System.out.println("**"+gridList.getSnake().getHead().getRow() + " " + gridList.getSnake().getHead().getCol());
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
		disp.setHiScore(score);
	}
	
	private void setHighScore(int s)
	{
		disp.setGameOverText("Game Over");
		//System.out.println(score + "  " + disp.getScore());
		if(score < disp.getScore())
		{
			disp.setNewHiScore(disp.getScore());
			System.out.println("New High Score!");
			File scFile = new File("res/score/score.txt");
			FileWriter scWrite;
			try {
				int score = disp.getScore();
				scWrite = new FileWriter(scFile, false);
				scWrite.write(""+score);
				scWrite.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // true to append
			                                                     // false to overwrite.
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
	
	private void setUpResetButton()
	{
		JButton resetButton = disp.getRestartButton();
		resetButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				//gameRunning = false;
				System.out.println("button pressed.");
				disp.getContent().requestFocus();
			}
		});
	}

}
