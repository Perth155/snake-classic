package com.example.perth155.entities;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

public final class Constants
{
	public static final int EMPTY = 0;
	public static final int ITEM = 1;
	public static final int SNAKE_BODY = 2;
	public static final int SNAKE_HEAD = 3;
	public static final int ROWS = 20;
	public static final int COLS = 20;
	public static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final Color SNAKE_COLOR = new Color(0, 0, 0);
	public static final Color BOARD_COLOR = new Color(27,227,127);
	public static final Color ITEM_COLOR = new Color(200, 0, 0);
}
