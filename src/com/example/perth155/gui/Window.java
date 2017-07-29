package com.example.perth155.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.net.URL;
import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import java.util.LinkedList;
import com.example.perth155.entities.Cell;
import com.example.perth155.entities.GridManager;
import com.example.perth155.entities.Snake;
import com.example.perth155.gameplay.Constants;


public class Window extends JFrame
{
	private int winHeightWidth;
	private GridManager grids;
	Font gameFont;
	Content cn;
	
	public Window()
	{
		super("Snake Classic");
		setGameFont();
		winHeightWidth = getDimension();
		grids = new GridManager(Constants.ROWS);
		setSize(winHeightWidth+100,winHeightWidth+250);
		cn = new Content();
		add(cn);
		cn.requestFocus();
	}
	
	
	public void reset()
	{
		grids = new GridManager(Constants.ROWS);
		cn.updateGrids();
	}

	private int getDimension()
	{
		if(Constants.SCREEN_SIZE.height < Constants.SCREEN_SIZE.width)
			return (int)(Constants.SCREEN_SIZE.height*0.5);
		return (int)(Constants.SCREEN_SIZE.width*0.5);
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
		cn.updateScores();
	}
	
	
	public int getScore()
	{
		return grids.getSnake().getPoint();
	}

	public void setHiScore(int hs)
	{
		cn.updateHighScore(hs);
	}
	
	public void setNewHiScore(int score) {
		cn.setNewHighScore(score);
	}
	
	
	private void setGameFont() 
	{
	    try {
	    	gameFont = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("res/font/VT323-Regular.ttf"));
	    	gameFont = gameFont.deriveFont(27F);
	    	gameFont = gameFont.deriveFont(gameFont.getStyle() | Font.BOLD);
	    	//fontSmall = Font.createFont(Font.TRUETYPE_FONT, new FileInputStream("font.ttf"));
	    	//fontSmall.deriveFont(16F);
		}catch (Exception e) {
			e.printStackTrace();
			gameFont = new Font("Monospaced", Font.BOLD, 24);
		} 
	}
	
	
	public Font getGameFont()
	{
		return gameFont;
	}
	
	public void setGameOverText(String str)
	{
		cn.gameOverText.setText(str);
		cn.gameOverText.setOpaque(true);
	}
	
	public JPanel getContent()
	{
		return cn;
	}

	/**
	 * An inner class for the JPanel where the game will be rendered in.
	 */
	private class Content extends JPanel
	{
		private int[][] gridState;
		private JLabel[][] gridContent;
		private JLabel highScoreText;
		private JLabel scoreText;
		private JLabel gameOverText;
		private JButton jb;
		private Color backGroundColor;


		public Content()
		{
			JPanel gui = new JPanel(new GridLayout(Constants.ROWS, Constants.ROWS));
			//setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
			backGroundColor = Color.DARK_GRAY;
			setSnakeMovementControls();
			render();
			setPreferredSize(new Dimension(winHeightWidth+100, winHeightWidth+250));
			setUpOuterPlayArea();
		}
		
		
		public void setUpOuterPlayArea()
		{
			//Font gameFont = new Font("Monospaced", Font.BOLD, 24);
			scoreText = new JLabel(" Score : " +getSnake().getPoint()+" ");
			scoreText.setFont(gameFont);
			scoreText.setForeground(Color.BLUE);
			highScoreText = new JLabel(" High Score :  ");
			highScoreText.setForeground(new Color(5, 90, 5));
			highScoreText.setFont(gameFont);
			gameOverText = new JLabel("         ");
			gameOverText.setForeground(Color.WHITE);
			gameOverText.setBackground(Color.RED);
			//gameOverText.setOpaque(true);
			gameOverText.setFont(gameFont);
			highScoreText.setFont(gameFont);
			jb = new JButton("Restart");
			jb.setBackground(Color.yellow);
			jb.setFont(gameFont);
			add(scoreText);
			add(highScoreText);
			add(jb);
			add(gameOverText);
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
			gridContent = new JLabel[Constants.ROWS][Constants.COLS];
		}


		public void render()
		{
			setUpGridArrays();
		    for (int row = 0; row < Constants.ROWS; row++)
		    {
		        for (int col = 0; col < Constants.COLS; col++)
		        {
		            gridContent[row][col] = new JLabel(); // allocate element of array
		            add(gridContent[row][col]);  // ContentPane adds JTextField
		         }
		      }
		      updateGrids();
		      fillSnakeBodyAndItem();
		}

		public void fillSnakeBodyAndItem()
		{
			Cell head = grids.getSnake().getHead();
			gridContent[head.getRow()][head.getCol()].setBackground(Color.pink);
			Cell item = grids.getItem().getLocation();
			gridContent[item.getRow()][item.getCol()].setBackground(Color.RED);
			Cell vacant = grids.getSnake().getVacant();
			gridContent[vacant.getRow()][vacant.getCol()].setBackground(backGroundColor);
			LinkedList<Cell> body = (LinkedList<Cell>) grids.getSnake().getBody();
			for(int i = 0; i < body.size(); i++)
			{
				gridContent[body.get(i).getRow()][body.get(i).getCol()].setBackground(Color.WHITE);
			}
		}
		
		
		public void updateScores()
		{
			scoreText.setText(" Score : " +getSnake().getPoint()+" ");
		}
		
		
		public void updateHighScore(int h)
		{
			highScoreText.setText(" High Score : " + h + " ");
		}
		
		
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
		

		public void updateGrids()
		{
		    for (int row = 0; row < Constants.ROWS; row++)
		    {

		        for (int col = 0; col < Constants.COLS; col++)
		        {
					int number = gridState[row][col];

		            gridContent[row][col].setText("");
		            gridContent[row][col].setBackground(backGroundColor);
		            gridContent[row][col].setOpaque(true);
		            gridContent[row][col].setHorizontalAlignment(JLabel.CENTER);
		            gridContent[row][col].setFont(new Font("Monospaced", Font.BOLD, 20));
		            gridContent[row][col].setPreferredSize(new Dimension(winHeightWidth/Constants.ROWS,
		            		winHeightWidth/Constants.COLS));
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


		public JButton getRestartButton() {
			return jb;
		}
	}

	public JButton getRestartButton() 
	{
		return cn.getRestartButton();
	}


}
