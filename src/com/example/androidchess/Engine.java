package com.example.androidchess;

import java.io.IOException;

import android.widget.ImageButton;
import android.widget.Toast;
import boardgame.Board;
import boardgame.Conditions;
import boardgame.Coordinates;
import boardgame.Piece;
import boardgame.Square;




public class Engine {
	
	

	/**
     * Validates the given two inputs
     * @return true if the move is valid, false otherwise
	 * @throws IOException 
     */
    public static boolean validate(Board board, Coordinates start, Coordinates end, String promo) throws IOException {
    	
    	Square startsquare = board.board[start.rank][start.file];
		Square endsquare = board.board[end.rank][end.file];
		Piece selectpiece = startsquare.piece;
		Piece targetpiece = endsquare.piece;
		
		Conditions cond = new Conditions();
		
		cond.isAttacking = targetpiece == null ? false : true;
		
		if (board.blockedPath(start, end) == true){
			cond.blocked = true;
		}
		
		else if (selectpiece == null){
			//empty square
			return false;
		}
		
		else if (start.file == end.file && start.rank == end.rank){
			//same position
			return false;
		}
		
		else if ( (selectpiece.isWhite == true && board.whiteTurn == false) || (selectpiece.isWhite == false && board.whiteTurn == true) ){
			//moving wrong piece
			return false;
		}
		
		else if (cond.isAttacking == true &&  (targetpiece.isWhite == board.whiteTurn) )
		{
			//attacking own piece
			return false;
		}
		
		else if (selectpiece.checkMove(start, end, cond) == true){
			
			boolean checkresponse = board.checkmachine(start, end);
			//board.move(start, end);
		
			if (cond.promoting && cond.promotable) {
				board.board[end.rank][end.file].piece = board.getPromoPiece(promo);
				cond.promotable = false;
				ChessActivity.upgrade = true;
			}
			
			return checkresponse;	
		}
		
		else if (selectpiece.checkMove(start, end, cond) == false){
			return false;
		}
		return false;
    }
    
    /**
	 * Sets the image accordingly to the piece that is on top of it
	 * @param image
	 * @param piece
	 */
	public static void set_image(ImageButton image, Piece piece) {
		if (piece.toString().equals("wR")) {
			image.setImageResource(R.drawable.wr);
		} else if (piece.toString().equals("wN")) {
			image.setImageResource(R.drawable.wn);
		} else if (piece.toString().equals("wB")) {
			image.setImageResource(R.drawable.wb);
		} else if (piece.toString().equals("wQ")) {
			image.setImageResource(R.drawable.wq);
		} else if (piece.toString().equals("wK")) {
			image.setImageResource(R.drawable.wk);
		} else if (piece.toString().equals("wP")) {
			image.setImageResource(R.drawable.white_pawn);
		} else if (piece.toString().equals("bR")) {
			image.setImageResource(R.drawable.br);
		} else if (piece.toString().equals("bN")) {
			image.setImageResource(R.drawable.bn);
		} else if (piece.toString().equals("bB")) {
			image.setImageResource(R.drawable.bb);
		} else if (piece.toString().equals("bQ")) {
			image.setImageResource(R.drawable.bq);
		} else if (piece.toString().equals("bK")) {
			image.setImageResource(R.drawable.bk);
		} else if (piece.toString().equals("bP")) {
			image.setImageResource(R.drawable.black_pawn);
		} else if (piece.toString().equals("white")) {
			image.setImageResource(R.drawable.white);
		} else if (piece.toString().equals("black")) {
			image.setImageResource(R.drawable.black);
		} else {
			image.setImageResource(R.drawable.ic_launcher);
		}
	}
    
}
