package com.example.perth155.main;

import com.example.perth155.gui.Window;
import javax.swing.JFrame;


public class Game
{
	public static void main(String[] args)
	{
		Window w = new Window();
		w.setVisible(true);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		w.setLocationRelativeTo(null);
		w.setResizable(false);
		GameLoop gl = new GameLoop(w);
		//gl.run();
	}
}
