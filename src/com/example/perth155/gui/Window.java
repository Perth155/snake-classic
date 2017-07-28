package com.example.perth155.gui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.util.Arrays;



public class Window extends JFrame
{
	
	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	private static final int ROWS = 20;
	private static final int COLS = 20;
	//content of each grid element.
	private static final int EMPTY = 0;
	private static final int ITEM = 1;
	private static final int SNAKE_BODY = 2;
	
	private int winHeightWidth;
	
	public Window()
	{
		super("Snake Classic");
		winHeightWidth = getDimension(); 
		setSize(winHeightWidth+100,winHeightWidth+200);
		Content cn = new Content();
		add(cn);
	}
	
	
	
	private int getDimension()
	{
		if(SCREEN_SIZE.height < SCREEN_SIZE.width)
			return (int)(SCREEN_SIZE.height*0.6);
		return (int)(SCREEN_SIZE.width*0.6);
	}
	
	
	
	/**
	 * An inner class for the JPanel where the game will be rendered in.
	 */
	private class Content extends JPanel
	{
		private int[][] gridState;
		private JLabel[][] gridContent;
		private JLabel highScoreText;
		private Color backGroundColor;

		public Content()
		{
			JPanel gui = new JPanel(new GridLayout(ROWS, COLS));
			//setBorder(BorderFactory.createEmptyBorder(2,2,2,2));
			backGroundColor = Color.DARK_GRAY;
			render();
			setPreferredSize(new Dimension(winHeightWidth+100, winHeightWidth+200));
		}
		
		
		private void setUpGridArrays()
		{
			gridState = new int[ROWS][COLS];
			for(int y = 0; y<gridState.length; y++)
			{
				Arrays.fill(gridState[y], EMPTY);
			}
			gridContent = new JLabel[ROWS][COLS];
			
			//TEST
			gridState[2][6] = SNAKE_BODY;
			gridState[2][7] = SNAKE_BODY;
			gridState[2][8] = SNAKE_BODY;
			gridState[1][8] = SNAKE_BODY;
			
			gridState[5][6] = ITEM;
		}
		
		public void render() 
		{
			setUpGridArrays();
		    for (int row = 0; row < ROWS; row++) 
		    {
		    	
		        for (int col = 0; col < COLS; col++) 
		        {
		            gridContent[row][col] = new JLabel(); // allocate element of array
		            add(gridContent[row][col]);  // ContentPane adds JTextField
		            int number = gridState[row][col];
		            if (number == EMPTY) 
		            {
		               gridContent[row][col].setText("");  // empty
		               //tfCells[row][col].setEditable(true);
		               gridContent[row][col].setBackground(backGroundColor);
		            } else if(number == ITEM){
		               gridContent[row][col].setText("");
		               //gridContent[row][col].setEditable(false);
		               gridContent[row][col].setBackground(Color.RED);
		            } else{
			           gridContent[row][col].setText("");
			               //gridContent[row][col].setEditable(false);
			           gridContent[row][col].setBackground(Color.WHITE);
		            }
		            gridContent[row][col].setOpaque(true);
		            gridContent[row][col].setHorizontalAlignment(JLabel.CENTER);
		            gridContent[row][col].setFont(new Font("Monospaced", Font.BOLD, 20));
		            gridContent[row][col].setPreferredSize(new Dimension(winHeightWidth/20, winHeightWidth/20));
		         }
		      }
		      setPreferredSize(new Dimension((int)(SCREEN_SIZE.height*0.6), (int)(SCREEN_SIZE.height*0.6)));
		 
		      //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		      //pack();
		      //setTitle("Sudoku");
		      //setVisible(true);
		}
	
	}
	
}


