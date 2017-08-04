package com.example.perth155.main;

import com.example.perth155.util.Constants;
import com.example.perth155.entities.GridManager;
import com.example.perth155.gui.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import javax.sound.sampled.Clip;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.JButton;

public class GameLoop
{
	private boolean gameRunning;
	private Window disp;
	private GridManager gridList;
	private int score;
	private Timer timer;
	private Clip pointSound;
	private Clip gameOverSound;
	private Clip highScoreSound;
	private boolean playSound;


	public GameLoop(Window w)
	{
		gameRunning = true;
		playSound = false;
		disp = w;
		loadAudioFiles();
		gridList = disp.getGridList();
		score = 0;
		checkHighScoreUpdate();
	    timer = new Timer();
	    timer.schedule(new Turn(), 0, 1000 / 10);
	    setUpResetButton();
	    setUpSoundButton();
	}


	private class Turn extends TimerTask
	{
		private int currentScore = 0;
		@Override
		public void run()
		{
			disp.getContent().requestFocus();
			gridList.snakeHeadMovement();
			disp.render();
			if(currentScore < disp.getScore())
			{
				currentScore = disp.getScore();
				if(playSound)
				{
					pointSound.setFramePosition(0);
					pointSound.start();
				}
			}
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
		if(playSound)
		{
			gameOverSound.setFramePosition(0);
			gameOverSound.start();
		}
		disp.setGameOverText("Game Over");
		if(score < disp.getScore())
		{
			if(playSound)
			{
				highScoreSound.setFramePosition(0);
				highScoreSound.start();
			}
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


	private void loadAudioFiles()
	{
		try {
			AudioInputStream pointAudio = AudioSystem.getAudioInputStream(getClass().getResource("/audio/point.wav"));
			AudioInputStream deathAudio = AudioSystem.getAudioInputStream(getClass().getResource("/audio/game_over.wav"));
			AudioInputStream successAudio = AudioSystem.getAudioInputStream(getClass().getResource("/audio/high_score.wav"));
            pointSound = AudioSystem.getClip();
            pointSound.open(pointAudio);
            gameOverSound = AudioSystem.getClip();
            highScoreSound = AudioSystem.getClip();
            gameOverSound.open(deathAudio);
            highScoreSound.open(successAudio);

		} catch (Exception e) {
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
				disp.getContent().requestFocus();
				disp.reset();
				resume();
				disp.setAllFontsToDefaults() ;
			}
		});
	}

	private void setUpSoundButton()
	{
		JButton soundButton = disp.getSoundButton();
		soundButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent evt)
			{
				if(playSound)
				{
					playSound = false;
					disp.deactivateSoundButton();
				} else{
					playSound = true;
					disp.activateSoundButton();
				}
				disp.getContent().requestFocus();
			}
		});
	}

}
