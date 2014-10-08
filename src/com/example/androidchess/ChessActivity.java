package com.example.androidchess;


import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import boardgame.Board;
import boardgame.Coordinates;
import boardgame.Piece;


public class ChessActivity extends Activity {

	public static Board board;
	public static boolean first;
	public static ImageButton button1, button2;
	public static Piece piece;
	public static boolean upgrade;
	public static Coordinates[] moves = new Coordinates[2];
	Coordinates[] movelist = new Coordinates[200];
	int moveindex = 0;
	Piece lastpiece;
	Context context = this;
	String gamename = "";
	boolean drawoffered;
	boolean undoallowed = false;

	public static ImageButton a1 = null, a2 = null, a3 = null, a4 = null, a5 = null, a6 = null, a7 = null, a8 = null;
	public static ImageButton b1 = null, b2 = null, b3 = null, b4 = null, b5 = null, b6 = null, b7 = null, b8 = null;
	public static ImageButton c1 = null, c2 = null, c3 = null, c4 = null, c5 = null, c6 = null, c7 = null, c8 = null;
	public static ImageButton d1 = null, d2 = null, d3 = null, d4 = null, d5 = null, d6 = null, d7 = null, d8 = null;
	public static ImageButton e1 = null, e2 = null, e3 = null, e4 = null, e5 = null, e6 = null, e7 = null, e8 = null;
	public static ImageButton f1 = null, f2 = null, f3 = null, f4 = null, f5 = null, f6 = null, f7 = null, f8 = null;
	public static ImageButton g1 = null, g2 = null, g3 = null, g4 = null, g5 = null, g6 = null, g7 = null, g8 = null;
	public static ImageButton h1 = null, h2 = null, h3 = null, h4 = null, h5 = null, h6 = null, h7 = null, h8 = null;
	
	Button AI = null;
	Button undo = null;
	Button draw = null;
	Button resign = null;
	
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	Date date = new Date();
	


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.chess);
		
		try {
			board = new Board();
			board.boardSetUp();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		first = true;
		try {
			loadBoard();
			AI = (Button) findViewById(R.id.AI_btn);
			undo = (Button) findViewById(R.id.undo_btn);
			draw = (Button) findViewById(R.id.draw_btn);
			resign = (Button) findViewById(R.id.resign_btn);
			
			AI.setOnClickListener(new OnClickListener(){
				public void onClick(View v){
					
					Coordinates location;
					ImageButton aibutton1;
					ImageButton aibutton2;
					print("AI Thinking");
					boolean wedidit = false;
					
	
					
					while (wedidit == false){

						int x = (int)(Math.random() * 7);
						int y = (int)(Math.random() * 7);
							
							if (board.board[x][y].piece != null)
							{
							
								//if (board.whiteTurn == board.board[x][y].piece.isWhite)
								{
									location = board.board[x][y].coords;
									button1 = buttonfinder(x,y);

									int r = (int)(Math.random() * 7);
									int c = (int)(Math.random() * 7);	
									
											button2 = buttonfinder(r,c);
											boolean response = move(board, location, board.board[r][c].coords, null);
											if (response == true){
												button1 = null;
												button2 = null;
												wedidit = true;
												return;
											}
										}
								}
					}
					
				
				} 			
			});
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		undo.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
				
			if (undoallowed == true){
				
				ImageButton undobutton1= buttonfinder(movelist[moveindex-1].rank, movelist[moveindex-1].file );
				ImageButton undobutton2 = buttonfinder(movelist[moveindex-2].rank, movelist[moveindex-2].file );
				
				piece = board.board[movelist[moveindex-1].rank][movelist[moveindex-1].file].piece;
				
				board.testmove(movelist[moveindex-1], movelist[moveindex-2]);
				

				Engine.set_image(undobutton2, piece);
				
				
				if (lastpiece == null){
				
					if ((movelist[moveindex-1].rank + movelist[moveindex-1].file)%2 == 0) {
						Engine.set_image(undobutton1, new Piece(true));
					}
					else {
						Engine.set_image(undobutton1, new Piece(false));
					}
				}
				
				else{
					Engine.set_image(undobutton1, lastpiece);
				}
				
				if (board.whiteTurn == true){
					board.whiteTurn = false;
				}
				
				else{
					board.whiteTurn = true;
				} 
				
				undoallowed = false;
				moveindex = moveindex -2;
			}
			else{
				print("Undo is disallowed");
			}
		}
		});
		
		resign.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
					if (board.whiteTurn == true)
					{
						print("Black wins!");
					}
					if (board.whiteTurn == false)
					{
						print("White wins!");
					}
					
					AlertDialog.Builder builder = new AlertDialog.Builder(context);
					builder.setTitle("Save Game");

					final EditText input = new EditText(context);
					input.setInputType(InputType.TYPE_CLASS_TEXT);
					builder.setView(input);

					builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
					    @Override
					    public void onClick(DialogInterface dialog, int which) {
					        gamename = input.getText().toString();
					        MoveArray finallist = new MoveArray(movelist, gamename, dateFormat.format(date));
							write(finallist);
							finish();
					    }
					});
					builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					    @Override
					    public void onClick(DialogInterface dialog, int which) {
					        dialog.cancel();
					        finish();
					    }
					});

					builder.show();
				

			}
		});
		
		
		draw.setOnClickListener(new OnClickListener(){
			
			public void onClick(View v){
				
				 
				
				if (drawoffered == true)
				{
					print("Draw!");
					

						AlertDialog.Builder builder = new AlertDialog.Builder(context);
						builder.setTitle("Save Game");

						final EditText input = new EditText(context);
						input.setInputType(InputType.TYPE_CLASS_TEXT);
						builder.setView(input);

						builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
						    @Override
						    public void onClick(DialogInterface dialog, int which) {
						        gamename = input.getText().toString();
						        MoveArray finallist = new MoveArray(movelist, gamename, dateFormat.format(date));
								write(finallist);
								finish();
						    }
						});
						builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						    @Override
						    public void onClick(DialogInterface dialog, int which) {
						        dialog.cancel();
						        finish();
						    }
						});

						builder.show();
						
					
				}
				
				
				if (drawoffered == false){
					if (board.whiteTurn == true)
					{
						print("White is offering a draw. Accept?");
					}
					if (board.whiteTurn == false)
					{
						print("Black is offering a draw. Accept?");
					}
					drawoffered = true;
				}
				

			}
		});
		
		print("starting game!");


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
	
	public boolean move(Board board, Coordinates start, Coordinates end, String promotion) {
		
		
		boolean success = false;
		piece = board.board[start.rank][start.file].piece;
		lastpiece = board.board[end.rank][end.file].piece;

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
				
				this.movelist[moveindex] = start;
				this.movelist[moveindex+1] = end;
				this.moveindex = moveindex +2;
				
	
			} 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		moves[0] = null;
		moves[1] = null;
		first = true;
		drawoffered = false;
		
		if (undoallowed == false)
		{
			undoallowed = true;
		}

		
		return success;
		
		
	}
	
	/**
	 * OnClickListener for each of the ImageButtons
	 */
	public class AIlistener implements OnClickListener{
		
		
		public void onClick(View v){
			
			print("Test?");
			
			quadloop:
			for (int x = 0; x <= 7; x++) {
				for (int y= 0; y <= 7; y++) {
			
					Coordinates location = board.board[x][y].coords;
					
		
						for (int r = 0; r <= 7; r++) {
							for (int c= 0; c <= 7; c++) {
								boolean response = move(board, location, board.board[r][c].coords, null);
								if (response == true){
									return;
								}
							}
						}
				}		
			}	
		
		}
	}	
	
	public class on_click implements OnClickListener {
		
		private Coordinates position;
		private ImageButton button;

		public on_click(Coordinates position, ImageButton button) {
			this.position = position;
			this.button = button;
		}

		public void onClick(View v) {
			
			if (first) { 
				moves[0] = this.position;
				button1 = this.button;
				if (board.getSquare(position).piece != null) {
					first = false;
				}
				print("Starting Square");
			} else {
				print("Ending Square");
				moves[1] = this.position;
				button2 = this.button;
				move(board, moves[0], moves[1], null);
				
			
				}
			
			if (board.selfCheck == true){
				print("Can't move there, would put you in Check! You're welcome! :)");
				}	
			
			if (board.Check == true){
				if (board.whiteTurn == true){
				print("Check! Black wins!");}
				else{
					print("Check! White wins!");}
				
				
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setTitle("Save Game");

				final EditText input = new EditText(context);
				input.setInputType(InputType.TYPE_CLASS_TEXT);
				builder.setView(input);

				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        gamename = input.getText().toString();
				        MoveArray finallist = new MoveArray(movelist, gamename, dateFormat.format(date));
						write(finallist);
						finish();
				    }
				});
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        dialog.cancel();
				        finish();
				    }
				});

				builder.show();

				}
			}
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
		a8.setOnClickListener(new on_click(board.board[0][0].coords, a8));
		b8.setOnClickListener(new on_click(board.board[0][1].coords, b8));
		c8.setOnClickListener(new on_click(board.board[0][2].coords, c8));
		d8.setOnClickListener(new on_click(board.board[0][3].coords, d8));
		e8.setOnClickListener(new on_click(board.board[0][4].coords, e8));
		f8.setOnClickListener(new on_click(board.board[0][5].coords, f8));
		g8.setOnClickListener(new on_click(board.board[0][6].coords, g8));
		h8.setOnClickListener(new on_click(board.board[0][7].coords, h8));
		a7.setOnClickListener(new on_click(board.board[1][0].coords, a7));
		b7.setOnClickListener(new on_click(board.board[1][1].coords, b7));
		c7.setOnClickListener(new on_click(board.board[1][2].coords, c7));
		d7.setOnClickListener(new on_click(board.board[1][3].coords, d7));
		e7.setOnClickListener(new on_click(board.board[1][4].coords, e7));
		f7.setOnClickListener(new on_click(board.board[1][5].coords, f7));
		g7.setOnClickListener(new on_click(board.board[1][6].coords, g7));
		h7.setOnClickListener(new on_click(board.board[1][7].coords, h7));
		a6.setOnClickListener(new on_click(board.board[2][0].coords, a6));
		b6.setOnClickListener(new on_click(board.board[2][1].coords, b6));
		c6.setOnClickListener(new on_click(board.board[2][2].coords, c6));
		d6.setOnClickListener(new on_click(board.board[2][3].coords, d6));
		e6.setOnClickListener(new on_click(board.board[2][4].coords, e6));
		f6.setOnClickListener(new on_click(board.board[2][5].coords, f6));
		g6.setOnClickListener(new on_click(board.board[2][6].coords, g6));
		h6.setOnClickListener(new on_click(board.board[2][7].coords, h6));
		a5.setOnClickListener(new on_click(board.board[3][0].coords, a5));
		b5.setOnClickListener(new on_click(board.board[3][1].coords, b5));
		c5.setOnClickListener(new on_click(board.board[3][2].coords, c5));
		d5.setOnClickListener(new on_click(board.board[3][3].coords, d5));
		e5.setOnClickListener(new on_click(board.board[3][4].coords, e5));
		f5.setOnClickListener(new on_click(board.board[3][5].coords, f5));
		g5.setOnClickListener(new on_click(board.board[3][6].coords, g5));
		h5.setOnClickListener(new on_click(board.board[3][7].coords, h5));
		a4.setOnClickListener(new on_click(board.board[4][0].coords, a4));
		b4.setOnClickListener(new on_click(board.board[4][1].coords, b4));
		c4.setOnClickListener(new on_click(board.board[4][2].coords, c4));
		d4.setOnClickListener(new on_click(board.board[4][3].coords, d4));
		e4.setOnClickListener(new on_click(board.board[4][4].coords, e4));
		f4.setOnClickListener(new on_click(board.board[4][5].coords, f4));
		g4.setOnClickListener(new on_click(board.board[4][6].coords, g4));
		h4.setOnClickListener(new on_click(board.board[4][7].coords, h4));
		a3.setOnClickListener(new on_click(board.board[5][0].coords, a3));
		b3.setOnClickListener(new on_click(board.board[5][1].coords, b3));
		c3.setOnClickListener(new on_click(board.board[5][2].coords, c3));
		d3.setOnClickListener(new on_click(board.board[5][3].coords, d3));
		e3.setOnClickListener(new on_click(board.board[5][4].coords, e3));
		f3.setOnClickListener(new on_click(board.board[5][5].coords, f3));
		g3.setOnClickListener(new on_click(board.board[5][6].coords, g3));
		h3.setOnClickListener(new on_click(board.board[5][7].coords, h3));
		a2.setOnClickListener(new on_click(board.board[6][0].coords, a2));
		b2.setOnClickListener(new on_click(board.board[6][1].coords, b2));
		c2.setOnClickListener(new on_click(board.board[6][2].coords, c2));
		d2.setOnClickListener(new on_click(board.board[6][3].coords, d2));
		e2.setOnClickListener(new on_click(board.board[6][4].coords, e2));
		f2.setOnClickListener(new on_click(board.board[6][5].coords, f2));
		g2.setOnClickListener(new on_click(board.board[6][6].coords, g2));
		h2.setOnClickListener(new on_click(board.board[6][7].coords, h2));
		a1.setOnClickListener(new on_click(board.board[7][0].coords, a1));
		b1.setOnClickListener(new on_click(board.board[7][1].coords, b1));
		c1.setOnClickListener(new on_click(board.board[7][2].coords, c1));
		d1.setOnClickListener(new on_click(board.board[7][3].coords, d1));
		e1.setOnClickListener(new on_click(board.board[7][4].coords, e1));
		f1.setOnClickListener(new on_click(board.board[7][5].coords, f1));
		g1.setOnClickListener(new on_click(board.board[7][6].coords, g1));
		h1.setOnClickListener(new on_click(board.board[7][7].coords, h1)); 
	}


	public void print(String text) {
		Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) { 

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chess, menu);
		return true;
	}
	
	  public void write(MoveArray finallist) {
	    	try {

				FileOutputStream fos = openFileOutput("movelist.dat", Context.MODE_PRIVATE);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(finallist);	
				
				print("Write successful");
				
				oos.close();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
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
