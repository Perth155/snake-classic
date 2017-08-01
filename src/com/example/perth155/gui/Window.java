package com.example.perth155.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import java.util.LinkedList;
import com.example.perth155.entities.Cell;
import com.example.perth155.entities.Constants;
import com.example.perth155.entities.GridManager;
import com.example.perth155.entities.Snake;


/**
 * A class for rendering the game in a window.
 * @author Abrar Amin
 */
public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;


	private int winHeight;
	private int winWidth;
	private GridManager grids;
	private Font gameFont;
	private GamePanel cn;
	private JPanel outerArea;
	private JPanel gameOverPanel;
	private JLabel scoreText;
	private JLabel highScoreText;
	private JLabel gameOverText;
	private JButton jb;
	private JButton sound;
	private JButton wall;

	public Window()
	{
		super("Snake Classic");
		super.getContentPane().setLayout(new BorderLayout());
		setWindowSize();
		setGameFont();
		getContentPane().setPreferredSize(new Dimension(winWidth, winHeight));
		grids = new GridManager(Constants.ROWS);
		pack();
		addGamePanel();
		addOuterPlayPanel();
		addGameOverPanel();
		pack();
		getContentPane().add(cn, BorderLayout.CENTER);
		getContentPane().add(outerArea, BorderLayout.PAGE_START);
		getContentPane().add(gameOverPanel, BorderLayout.PAGE_END);
		System.out.println(getContentPane().getSize().width + " x " + getContentPane().getSize().height );
	}


	private void addGamePanel()
	{
		cn = new GamePanel();
		cn.setSnakeMovementControls();
		cn.requestFocus();
		cn.setPreferredSize(new Dimension(winWidth, (int)(winHeight*0.8)));
	}

	private void addOuterPlayPanel()
	{
		scoreText = new JLabel(" Score : " +getSnake().getPoint()+" ");
		scoreText.setFont(gameFont);
		scoreText.setForeground(Color.CYAN);
		highScoreText = new JLabel(" High Score :  ");
		highScoreText.setForeground(Color.GREEN);
		highScoreText.setFont(gameFont);
		highScoreText.setFont(gameFont);
		jb = new JButton("Restart");
		jb.setBackground(Color.yellow);
		jb.setFont(gameFont);
		sound = new JButton("Sound:OFF");
		sound.setBackground(Color.GRAY);
		sound.setFont(gameFont);
		outerArea = new JPanel();
		outerArea.add(scoreText);
		outerArea.add(highScoreText);
		outerArea.add(jb);
		outerArea.add(sound);
		outerArea.setPreferredSize(new Dimension(winWidth, (int)(winHeight*0.1)));
		outerArea.setBackground(Color.DARK_GRAY);
	}

	private void addGameOverPanel()
	{
		gameOverPanel = new JPanel();
		gameOverText = new JLabel("         ");
		gameOverText.setForeground(Color.WHITE);
		gameOverText.setBackground(Color.RED);
		gameOverText.setFont(gameFont);
		gameOverPanel.add(gameOverText);
		gameOverPanel.setPreferredSize(new Dimension(winWidth, (int)(winHeight*0.1)));
		gameOverPanel.setBackground(Color.DARK_GRAY);
		//getContentPane().add(gameOverPanel);
	}

	public void reset()
	{
		grids.resetSnake(Constants.ROWS);
		cn.updateGrids();
	}

	/**
	 * This method gets the screen resolution of the current screen, so that
	 * the game could be scaled nicely regardless of screen resolution of the
	 * computer the game is played in, set to half the width and height at launch
	 * by default.
	 */
	private void setWindowSize()
	{
		this.winHeight = (int)(Constants.SCREEN_SIZE.height*0.5);
		this.winWidth = (int)(Constants.SCREEN_SIZE.width*0.5);
	}



	public Snake getSnake()
	{
		return grids.getSnake();
	}

	public GridManager getGridList()
	{
		return this.grids;
	}


	public void render()
	{
		cn.fillSnakeBodyAndItem();
		updateScores();
	}

	public void updateScores()
	{
		scoreText.setText(" Score : " +getSnake().getPoint()+" ");
	}


	public int getScore()
	{
		return grids.getSnake().getPoint();
	}


	public void updateHighScore(int i)
	{
		highScoreText.setText(" High Score : " + i + " ");
	}

	/**
	 * The score and high score fields display the same value and remain highlighted
	 * If there is a new highscore.
	 * @param i the new highscore.
	 */
	public void setNewHighScore(int i)
	{
		updateScores();
		updateHighScore(i);
		scoreText.setForeground(Color.WHITE);
		highScoreText.setForeground(Color.WHITE);
		scoreText.setBackground(Color.BLUE);
		scoreText.setOpaque(true);
		highScoreText.setBackground(new Color(5, 90, 5));
		highScoreText.setOpaque(true);
	}


	private void setGameFont()
	{
		float fontSize = (float) (winHeight*0.05);
		System.out.println(fontSize);
	    try {
	    	InputStream ins = getClass().getResourceAsStream("/font/VT323-Regular.ttf");
	    	gameFont = Font.createFont(Font.TRUETYPE_FONT, ins);
	    	gameFont = gameFont.deriveFont(fontSize);
	    	gameFont = gameFont.deriveFont(gameFont.getStyle() | Font.BOLD);
		}catch (Exception e) {
			e.printStackTrace();
			gameFont = new Font(Font.MONOSPACED, Font.BOLD, (int)fontSize);
		}
	}

	public void setGameOverText(String str)
	{
		gameOverText.setText(str);
		gameOverText.setOpaque(true);
		gameOverPanel.setBackground(Color.RED);
	}

	public JPanel getContent()
	{
		return cn;
	}

	public JButton getRestartButton()
	{
		return this.jb;
	}

	public JButton getSoundButton()
	{
		return this.sound;
	}

	public void activateSoundButton()
	{
		sound.setText("Sound:ON ");
		sound.setBackground(Color.magenta);
		sound.setForeground(Color.WHITE);
	}

	public void deactivateSoundButton()
	{
		sound.setText("Sound:OFF");
		sound.setBackground(Color.GRAY);
		sound.setForeground(Color.DARK_GRAY);
	}

	/**
	 * Un-highlight all scores, this is applied if a new game is started
	 * after a game is restarted in which highscore had been received.
	 */
	public void setAllFontsToDefaults()
	{
		gameOverText.setText("         ");
		gameOverText.setOpaque(false);
		scoreText.setOpaque(false);
		highScoreText.setOpaque(false);
		scoreText.setForeground(Color.CYAN);
		highScoreText.setForeground(Color.GREEN);
		gameOverPanel.setBackground(Color.DARK_GRAY);
	}

	/**
	 * An inner class for the JPanel where only the game will be rendered in.
	 * A size*size grid layout is used, defined by number of ROWS in
	 * Constants. Note The number of rows and cols must me equal.
	 */
	private class GamePanel extends JPanel
	{
		private static final long serialVersionUID = 1L;
		private int[][] gridState;
		private JTextField[][] gridContent;

		public GamePanel()
		{
			super(new GridLayout(Constants.ROWS,0, 2, 2));
			setSnakeMovementControls();
			this.requestFocus();
			render();
		}


		private void setUpGridArrays()
		{
			gridState = new int[Constants.ROWS][Constants.COLS];
			for(int y = 0; y<gridState.length; y++)
			{
				for(int z = 0; z<gridState[y].length; z++)
				{
					gridState[y][z] = grids.getCellState(y, z).getFill();
				}
			}
			gridContent = new JTextField[Constants.ROWS][Constants.COLS];
		}


		public void render()
		{
			setUpGridArrays();
		    for (int row = 0; row < Constants.ROWS; row++)
		    {
		        for (int col = 0; col < Constants.COLS; col++)
		        {
		        		gridContent[row][col] = new JTextField();
		        		gridContent[row][col].setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		        		add(gridContent[row][col]);
		        }
		      }
		      updateGrids();
		      fillSnakeBodyAndItem();
		}

		public void fillSnakeBodyAndItem()
		{
			Cell head = grids.getSnake().getHead();
			gridContent[head.getRow()][head.getCol()].setBackground(Constants.SNAKE_HEAD_COLOR);
			Cell item = grids.getItem().getLocation();
			gridContent[item.getRow()][item.getCol()].setBackground(Constants.ITEM_COLOR);
			Cell vacant = grids.getSnake().getVacant();
			gridContent[vacant.getRow()][vacant.getCol()].setBackground(Constants.BOARD_COLOR);
			LinkedList<Cell> body = (LinkedList<Cell>) grids.getSnake().getBody();
			for(int i = 0; i < body.size(); i++)
			{
				gridContent[body.get(i).getRow()][body.get(i).getCol()].setBackground(Constants.SNAKE_COLOR);
			}
		}



		public void updateGrids()
		{
		    for (int row = 0; row < Constants.ROWS; row++)
		    {

		        for (int col = 0; col < Constants.COLS; col++)
		        {
		            gridContent[row][col].setBackground(Constants.BOARD_COLOR);
		            gridContent[row][col].setOpaque(true);
		            gridContent[row][col].setEditable(false);
		         }
		    }

		}


		private void setSnakeMovementControls()
		{
			AbstractAction moveUp = new AbstractAction()
			{
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e){
					if(grids.getSnake().getVelocity('y') != 1){
						grids.getSnake().setVelocity('y', -1);
					}
				}
			};
			AbstractAction moveDown = new AbstractAction()
			{
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e){
					if(grids.getSnake().getVelocity('y') != -1){
						grids.getSnake().setVelocity('y', 1);
					}
				}
			};
			AbstractAction moveLeft = new AbstractAction()
			{
				static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e){
					if(grids.getSnake().getVelocity('x') != 1){
						grids.getSnake().setVelocity('x', -1);
					}
				}
			};
			AbstractAction moveRight = new AbstractAction()
			{
				private static final long serialVersionUID = 1L;

				public void actionPerformed(ActionEvent e){
					if(grids.getSnake().getVelocity('x') != -1){
						grids.getSnake().setVelocity('x', 1);
					}
				}
			};
			getInputMap().put(KeyStroke.getKeyStroke("W"), "moveUp");
			getActionMap().put("moveUp", moveUp);
			getInputMap().put(KeyStroke.getKeyStroke("S"), "moveDown");
			getActionMap().put("moveDown", moveDown);
			getInputMap().put(KeyStroke.getKeyStroke("A"), "moveLeft");
			getActionMap().put("moveLeft", moveLeft);
			getInputMap().put(KeyStroke.getKeyStroke("D"), "moveRight");
			getActionMap().put("moveRight", moveRight);
		}
	}
}
