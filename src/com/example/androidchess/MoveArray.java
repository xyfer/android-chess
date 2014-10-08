package com.example.androidchess;

import java.io.Serializable;

import boardgame.Coordinates;
import android.widget.ImageButton;

public class MoveArray implements Serializable {
	
	Coordinates[] movelist;
	String name;
	String date;
	private static final long serialVersionUID = 3871300896235596188L;
	
	MoveArray(Coordinates[] movelist, String name, String date)
	{
		this.movelist = movelist;
		this.name = name;
		this.date = date;
	}
	
	@Override
	public String toString()
	{
		return this.name + "    " + this.date;
	}

}
