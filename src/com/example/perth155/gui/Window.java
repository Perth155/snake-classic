package com.example.perth155.gui;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.AbstractAction;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

import java.util.Arrays;
import java.util.LinkedList;

import com.example.perth155.entities.Cell;
import com.example.perth155.entities.GridManager;
import com.example.perth155.entities.Snake;
import com.example.perth155.gameplay.Constants;


public class Window extends JFrame
{
	//content of each grid element.

	private int winHeightWidth;
	private GridManager grids;
	Content cn;

	public Window()
	{
		super("Snake Classic");
		winHeightWidth = getDimension();
		grids = new GridManager(Constants.ROWS);
		setSize(winHeightWidth+100,winHeightWidth+250);
		cn = new Content();
		add(cn);
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
			Font gameFont = new Font("Monospaced", Font.BOLD, 24);
			JLabel sc = new JLabel(" Score : 000 ");
			sc.setFont(gameFont);
			sc.setForeground(Color.BLUE);
			JLabel hSc = new JLabel(" High Score : 999 ");
			hSc.setForeground(Color.RED);
			hSc.setFont(gameFont);
			JButton jb = new JButton("Restart");
			jb.setBackground(Color.yellow);
			jb.setFont(gameFont);
			add(sc);
			add(hSc);
			add(jb);
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


	}


}
