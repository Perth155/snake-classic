package com.example.perth155.main;

import com.example.perth155.gui.*;

import javax.swing.JFrame;

import com.example.perth155.gameplay.*;

public class Game
{
	public static void main(String[] args)
	{
		Window w = new Window();
		w.setVisible(true);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameLoop gl = new GameLoop(w);
		gl.run();
	}
}
