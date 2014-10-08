package com.example.androidchess;

import java.io.IOException;

import com.example.androidchess.ChessActivity.on_click;

import boardgame.Board;
import boardgame.Coordinates;
import boardgame.Piece;
import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.os.Build;

public class ReplayActivity extends Activity {
	
	
	public static Board board;
	public static boolean first;
	public static ImageButton button1, button2;
	public static Piece piece;
	public static boolean upgrade;
	public static Coordinates[] moves = new Coordinates[2];
	ImageButton[] movelist = new ImageButton[200];
	int moveindex = 0;

	public static ImageButton a1 = null, a2 = null, a3 = null, a4 = null, a5 = null, a6 = null, a7 = null, a8 = null;
	public static ImageButton b1 = null, b2 = null, b3 = null, b4 = null, b5 = null, b6 = null, b7 = null, b8 = null;
	public static ImageButton c1 = null, c2 = null, c3 = null, c4 = null, c5 = null, c6 = null, c7 = null, c8 = null;
	public static ImageButton d1 = null, d2 = null, d3 = null, d4 = null, d5 = null, d6 = null, d7 = null, d8 = null;
	public static ImageButton e1 = null, e2 = null, e3 = null, e4 = null, e5 = null, e6 = null, e7 = null, e8 = null;
	public static ImageButton f1 = null, f2 = null, f3 = null, f4 = null, f5 = null, f6 = null, f7 = null, f8 = null;
	public static ImageButton g1 = null, g2 = null, g3 = null, g4 = null, g5 = null, g6 = null, g7 = null, g8 = null;
	public static ImageButton h1 = null, h2 = null, h3 = null, h4 = null, h5 = null, h6 = null, h7 = null, h8 = null;
	
	Button next = null;
	Button quit = null;
	int pos;
	
	MoveArray replayarray = ReplayList.masterarray.get(ReplayList.selected);
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.replay);
		try {
			board = new Board();
			board.boardSetUp();
			loadBoard();
			print("starting replay!");
			next = (Button) findViewById(R.id.next);
			quit = (Button) findViewById(R.id.quit_btn);
			
			
			
			 next.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		            	
		            	if (replayarray.movelist[pos] == null)
		            	{
		            		print("No moves left! Games Over");
		            	}
		            	
		            	else{
		            	
		                button1 = buttonfinder(replayarray.movelist[pos].rank, replayarray.movelist[pos].file);
		                button2 = buttonfinder(replayarray.movelist[pos+1].rank, replayarray.movelist[pos+1].file);
		                
		                move(board, replayarray.movelist[pos], replayarray.movelist[pos+1], null);
		                pos = pos+2;
		            	}
		            }
		        });
			 
			 quit.setOnClickListener(new View.OnClickListener() {
		            public void onClick(View view) {
		            	finish();
		            }
		        });
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


	}
	
public boolean move(Board board, Coordinates start, Coordinates end, String promotion) {
		
		
		boolean success = false;
		piece = board.board[start.rank][start.file].piece;

		try {
			if(Engine.validate(board, start, end, promotion)) {
				success = true;
				if (upgrade) {
					upgrade = false;
					Engine.set_image(button2, board.getPromoPiece(promotion));
				} 
				else {
					Engine.set_image(button2, piece);
				}
				if ((start.rank + start.file)%2 == 0) {
					Engine.set_image(button1, new Piece(true));
				}
				else {
					Engine.set_image(button1, new Piece(false));
				}
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		moves[0] = null;
		moves[1] = null;
		first = true;
		return success;
	}
	
	public void print(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}
	
	public void loadBoard() throws Exception {
		// creates the buttons
		
		a8 = (ImageButton)findViewById(R.id.imageButton81);
		b8 = (ImageButton)findViewById(R.id.imageButton82);
		c8 = (ImageButton)findViewById(R.id.imageButton83);
		d8 = (ImageButton)findViewById(R.id.imageButton84);
		e8 = (ImageButton)findViewById(R.id.imageButton85);
		f8 = (ImageButton)findViewById(R.id.imageButton86);
		g8 = (ImageButton)findViewById(R.id.imageButton87);
		h8 = (ImageButton)findViewById(R.id.imageButton88);
		a7 = (ImageButton)findViewById(R.id.imageButton71);
		b7 = (ImageButton)findViewById(R.id.imageButton72);
		c7 = (ImageButton)findViewById(R.id.imageButton73);
		d7 = (ImageButton)findViewById(R.id.imageButton74);
		e7 = (ImageButton)findViewById(R.id.imageButton75);
		f7 = (ImageButton)findViewById(R.id.imageButton76);
		g7 = (ImageButton)findViewById(R.id.imageButton77);
		h7 = (ImageButton)findViewById(R.id.imageButton78);
		a6 = (ImageButton)findViewById(R.id.imageButton61);
		b6 = (ImageButton)findViewById(R.id.imageButton62);
		c6 = (ImageButton)findViewById(R.id.imageButton63);
		d6 = (ImageButton)findViewById(R.id.imageButton64);
		e6 = (ImageButton)findViewById(R.id.imageButton65);
		f6 = (ImageButton)findViewById(R.id.imageButton66);
		g6 = (ImageButton)findViewById(R.id.imageButton67);
		h6 = (ImageButton)findViewById(R.id.imageButton68);
		a5 = (ImageButton)findViewById(R.id.imageButton51);
		b5 = (ImageButton)findViewById(R.id.imageButton52);
		c5 = (ImageButton)findViewById(R.id.imageButton53);
		d5 = (ImageButton)findViewById(R.id.imageButton54);
		e5 = (ImageButton)findViewById(R.id.imageButton55);
		f5 = (ImageButton)findViewById(R.id.imageButton56);
		g5 = (ImageButton)findViewById(R.id.imageButton57);
		h5 = (ImageButton)findViewById(R.id.imageButton58);
		a4 = (ImageButton)findViewById(R.id.imageButton41);
		b4 = (ImageButton)findViewById(R.id.imageButton42);
		c4 = (ImageButton)findViewById(R.id.imageButton43);
		d4 = (ImageButton)findViewById(R.id.imageButton44);
		e4 = (ImageButton)findViewById(R.id.imageButton45);
		f4 = (ImageButton)findViewById(R.id.imageButton46);
		g4 = (ImageButton)findViewById(R.id.imageButton47);
		h4 = (ImageButton)findViewById(R.id.imageButton48);
		a3 = (ImageButton)findViewById(R.id.imageButton31);
		b3 = (ImageButton)findViewById(R.id.imageButton32);
		c3 = (ImageButton)findViewById(R.id.imageButton33);
		d3 = (ImageButton)findViewById(R.id.imageButton34);
		e3 = (ImageButton)findViewById(R.id.imageButton35);
		f3 = (ImageButton)findViewById(R.id.imageButton36);
		g3 = (ImageButton)findViewById(R.id.imageButton37);
		h3 = (ImageButton)findViewById(R.id.imageButton38);
		a2 = (ImageButton)findViewById(R.id.imageButton21);
		b2 = (ImageButton)findViewById(R.id.imageButton22);
		c2 = (ImageButton)findViewById(R.id.imageButton23);
		d2 = (ImageButton)findViewById(R.id.imageButton24);
		e2 = (ImageButton)findViewById(R.id.imageButton25);
		f2 = (ImageButton)findViewById(R.id.imageButton26);
		g2 = (ImageButton)findViewById(R.id.imageButton27);
		h2 = (ImageButton)findViewById(R.id.imageButton28);
		a1 = (ImageButton)findViewById(R.id.imageButton11);
		b1 = (ImageButton)findViewById(R.id.imageButton12);
		c1 = (ImageButton)findViewById(R.id.imageButton13);
		d1 = (ImageButton)findViewById(R.id.imageButton14);
		e1 = (ImageButton)findViewById(R.id.imageButton15);
		f1 = (ImageButton)findViewById(R.id.imageButton16);
		g1 = (ImageButton)findViewById(R.id.imageButton17);
		h1 = (ImageButton)findViewById(R.id.imageButton18);
		//add the listeners
		
	}
	
public ImageButton buttonfinder(int rank, int file){
		
		int therank;
		String thefile="";
		
		if (file == 0){
			thefile = "a";
		}
		
		if (file == 1){
			thefile = "b";
		}
		
		if (file == 2){
			thefile = "c";
		}
		
		if (file == 3){
			thefile = "d";
		}
		
		if (file == 4){
			thefile = "e";
		}
		
		if (file == 5){
			thefile = "f";
		}
		
		if (file == 6){
			thefile = "g";
		}
		if (file == 7){
			thefile = "h";
		}
		
		therank = (8-rank);
		
		String thebutton = thefile + therank;
		
		if (thebutton.equals("a1"))
		{
			return a1;
		}
		
		if (thebutton.equals("a2"))
		{
			return a2;
		}
		if (thebutton.equals("a3"))
		{
			return a3;
		}
		if (thebutton.equals("a4"))
		{
			return a4;
		}
		if (thebutton.equals("a5"))
		{
			return a5;
		}
		if (thebutton.equals("a6"))
		{
			return a6;
		}
		if (thebutton.equals("a7"))
		{
			return a7;
		}
		
		if (thebutton.equals("a8"))
		{
			return a8;
		}
		if (thebutton.equals("b1"))
		{
			return b1;
		}
		if (thebutton.equals("b2"))
		{
			return b2;
		}
		if (thebutton.equals("b3"))
		{
			return b3;
		}
		if (thebutton.equals("b4"))
		{
			return b4;
		}
		if (thebutton.equals("b5"))
		{
			return b5;
		}
		if (thebutton.equals("b6"))
		{
			return b6;
		}
		if (thebutton.equals("b7"))
		{
			return b7;
		}
		
		if (thebutton.equals("b8"))
		{
			return b8;
		}
		
		if (thebutton.equals("c1"))
		{
			return c1;
		}
		if (thebutton.equals("c2"))
		{
			return c2;
		}
		if (thebutton.equals("c3"))
		{
			return c3;
		}
		if (thebutton.equals("c4"))
		{
			return c4;
		}
		if (thebutton.equals("c5"))
		{
			return c5;
		}
		if (thebutton.equals("c6"))
		{
			return c6;
		}
		if (thebutton.equals("c7"))
		{
			return c7;
		}
		
		if (thebutton.equals("c8"))
		{
			return c8;
		}
		
		if (thebutton.equals("d1"))
		{
			return d1;
		}if (thebutton.equals("d2"))
		{
			return d2;
		}if (thebutton.equals("d3"))
		{
			return d3;
		}if (thebutton.equals("d4"))
		{
			return d4;
		}if (thebutton.equals("d5"))
		{
			return d5;
		}if (thebutton.equals("d6"))
		{
			return d6;
		}if (thebutton.equals("d7"))
		{
			return d7;
		}if (thebutton.equals("d8"))
		{
			return d8;
		}if (thebutton.equals("e1"))
		{
			return e1;
		}
		if (thebutton.equals("e2"))
		{
			return e2;
		}
		if (thebutton.equals("e3"))
		{
			return e3;
		}
		if (thebutton.equals("e4"))
		{
			return e4;
		}
		if (thebutton.equals("e5"))
		{
			return e5;
		}
		if (thebutton.equals("e6"))
		{
			return e6;
		}
		if (thebutton.equals("e7"))
		{
			return e7;
		}
		if (thebutton.equals("e8"))
		{
			return e8;
		}
		if (thebutton.equals("f1"))
		{
			return f1;
		}
		if (thebutton.equals("f2"))
		{
			return f2;
		}
		if (thebutton.equals("f3"))
		{
			return f3;
		}
		if (thebutton.equals("f4"))
		{
			return f4;
		}
		if (thebutton.equals("f5"))
		{
			return f5;
		}
		if (thebutton.equals("f6"))
		{
			return f6;
		}
		if (thebutton.equals("f7"))
		{
			return f7;
		}
		if (thebutton.equals("f8"))
		{
			return f8;
		}
		if (thebutton.equals("g1"))
		{
			return g1;
		}
		if (thebutton.equals("g2"))
		{
			return g2;
		}
		if (thebutton.equals("g3"))
		{
			return g3;
		}
		if (thebutton.equals("g4"))
		{
			return g4;
		}
		if (thebutton.equals("g5"))
		{
			return g5;
		}
		if (thebutton.equals("g6"))
		{
			return g6;
		}
		if (thebutton.equals("g7"))
		{
			return g7;
		}
		if (thebutton.equals("g8"))
		{
			return g8;
		}
		if (thebutton.equals("h1"))
		{
			return h1;
		}
		if (thebutton.equals("h2"))
		{
			return h2;
		}
		if (thebutton.equals("h3"))
		{
			return h3;
		}
		if (thebutton.equals("h4"))
		{
			return h4;
		}
		if (thebutton.equals("h5"))
		{
			return h5;
		}
		if (thebutton.equals("h6"))
		{
			return h6;
		}
		if (thebutton.equals("h7"))
		{
			return h7;
		}
		if (thebutton.equals("h8"))
		{
			return h8;
		}
		
		return null;
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.replay, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */


}
