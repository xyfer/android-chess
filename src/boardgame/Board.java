package boardgame;

public class Board {
	
	public static final int RANKS= 8;
	public static final int FILES = 8;
	int moves;
	public boolean whiteTurn = true;
	public boolean Check = false;
	Coordinates[] Kingarray = new Coordinates[2];
	public boolean selfCheck = false;
	
	public Square[][] board;

	public Board() throws Exception{
		
		board = new Square[RANKS][FILES];
		
		for (int i = 0; i < board.length; i++)
		{
			for (int j = 0; j < board[i].length; j++) 
			{
				board[i][j] = new Square(new Coordinates(i, getFileChar(j) ) );

			}
		}

		this.boardSetUp();
	}
		

	
	public void boardSetUp() {
		
		for (int i = 0; i <= 1; i++) {
			for (int j = 0; j <= 7; j++) {	
				
				if (i == 0){	
					if (j == 0 || j == 7) {
						board[i][j].piece = new Rook(false);
					} else if (j == 1 || j == 6) {
						board[i][j].piece = new Knight(false);
					} else if (j == 2 || j == 5) {
						board[i][j].piece = new Bishop(false);
					} else if (j == 3) {
						board[i][j].piece = new Queen(false);
					} else if (j == 4) {
						board[i][j].piece = new King(false);
					}
				}	
				else if (i == 1) {
					board[i][j].piece = new Pawn(false);
				}
			}	
		}
			
		for (int r = 6; r <= 7; r++) {
			for (int c= 0; c <= 7; c++) {
				if (r == 7){
					if (c == 0 || c == 7) {
						board[r][c].piece = new Rook(true);
					} else if (c == 1 || c == 6) {
						board[r][c].piece = new Knight(true);
					} else if (c == 2 || c == 5) {
						board[r][c].piece = new Bishop(true);
					} else if (c == 3) {
						board[r][c].piece = new Queen(true);
					} else if (c == 4) {
						board[r][c].piece = new King(true);
					}
				}	
				else if (r == 6) {
					board[r][c].piece = new Pawn(true);
				}
			}
		}		
	}
	

	
	public boolean checkmachine(Coordinates start, Coordinates end){
		
		testmove(start, end);
		this.Kingarray = KingFinder();
		boolean enemycheck =false;
		boolean yourcheck = false;
		boolean specialcase = false;
		
		if (whiteTurn == true){ 
			outerloop:
			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {	
					enemycheck = CheckChecker(board[i][j].coords, Kingarray[0], specialcase);
					if (enemycheck == true){
						break outerloop;
					}
				}
			}
		}
		
		if (whiteTurn == false){
			outerloop:
			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {	
					enemycheck = CheckChecker(board[i][j].coords, Kingarray[1], specialcase);
					if (enemycheck == true){
						break outerloop;
					}
				}
			}
		}
		
		if (whiteTurn == true){
			specialcase = true;
			outerloop:
			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {	
					yourcheck = CheckChecker(board[i][j].coords, Kingarray[1], specialcase);
					if (yourcheck == true){
						break outerloop;
					}
				}
			}
		}
		
		if (whiteTurn == false){
			specialcase = true;
			outerloop:
			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {	
					yourcheck = CheckChecker(board[i][j].coords, Kingarray[0], specialcase);
					if (yourcheck == true){
						break outerloop;
					}
				}
			}
		}
		
		if (yourcheck == true && enemycheck == true)
		{
			testmove(end, start);
			System.out.println("Can't move there, would put you in CHECK");
			selfCheck = true;
			return false;
		}
		
		if (yourcheck == true && enemycheck == false)
		{
			testmove(end, start);
			System.out.println("Can't move there, would put you in CHECK");
			selfCheck = true;
			return false;
		}
		
		if (yourcheck == false && enemycheck == true)
		{
			testmove(end, start);
			move(start, end);
			Check = true;
			return true;
		}
		
		if (yourcheck == false && enemycheck == false)
		{
			testmove(end, start);
			move(start, end);
			return true;
		}
		
		return false;
		
	}
		

	public void attemptMove(Coordinates start, Coordinates end, String promo){
		
		selfCheck = false;
		Square startsquare = board[start.rank][start.file];
		Square endsquare = board[end.rank][end.file];
		Piece selectpiece = startsquare.piece;
		Piece targetpiece = endsquare.piece;
		
		Conditions cond = new Conditions();
		
		cond.promoting = promo == null ? false : true;
		cond.isAttacking = targetpiece == null ? false : true;
		
		if (blockedPath(start, end) == true){
			cond.blocked = true;
		}
		
		else if (selectpiece == null){
			System.out.println("No piece to move at that location.");
		}
		
		else if (start.file == end.file && start.rank == end.rank){
			System.out.println("Can't move to same square!");
		}
		
		else if ( (selectpiece.isWhite == true && this.whiteTurn == false) || (selectpiece.isWhite == false && this.whiteTurn == true) ){
			System.out.println("That's not your piece!");
		}
		
		else if (cond.isAttacking == true &&  (targetpiece.isWhite == this.whiteTurn) )
		{
			System.out.println("Can't attack your own piece!");
		}
		
		else if (selectpiece.checkMove(start, end, cond) == true){
			
			testmove(start, end);
			this.Kingarray = KingFinder();
			boolean enemycheck =false;
			boolean yourcheck = false;
			boolean specialcase = false;
			
			if (whiteTurn == true){ 
				outerloop:
				for (int i = 0; i <= 7; i++) {
					for (int j = 0; j <= 7; j++) {	
						enemycheck = CheckChecker(board[i][j].coords, Kingarray[0], specialcase);
						if (enemycheck == true){
							break outerloop;
						}
					}
				}
			}
			
			if (whiteTurn == false){
				outerloop:
				for (int i = 0; i <= 7; i++) {
					for (int j = 0; j <= 7; j++) {	
						enemycheck = CheckChecker(board[i][j].coords, Kingarray[1], specialcase);
						if (enemycheck == true){
							break outerloop;
						}
					}
				}
			}
			
			if (whiteTurn == true){
				specialcase = true;
				outerloop:
				for (int i = 0; i <= 7; i++) {
					for (int j = 0; j <= 7; j++) {	
						yourcheck = CheckChecker(board[i][j].coords, Kingarray[1], specialcase);
						if (yourcheck == true){
							break outerloop;
						}
					}
				}
			}
			
			if (whiteTurn == false){
				specialcase = true;
				outerloop:
				for (int i = 0; i <= 7; i++) {
					for (int j = 0; j <= 7; j++) {	
						yourcheck = CheckChecker(board[i][j].coords, Kingarray[0], specialcase);
						if (yourcheck == true){
							break outerloop;
						}
					}
				}
			}
			
			if (yourcheck == true && enemycheck == true)
			{
				testmove(end, start);
				System.out.println("Can't move there, would put you in CHECK");
				selfCheck = true;
			}
			
			if (yourcheck == true && enemycheck == false)
			{
				testmove(end, start);
				System.out.println("Can't move there, would put you in CHECK");
				selfCheck = true;
			}
			
			if (yourcheck == false && enemycheck == true)
			{
				testmove(end, start);
				move(start, end);
				Check = true;
			}
			
			if (yourcheck == false && enemycheck == false)
			{
				testmove(end, start);
				move(start, end);
			}
			
			
			
			
			if (cond.promoting && cond.promotable) {
				board[end.rank][end.file].piece = this.getPromoPiece(promo);
				cond.promotable = false;
			}
			
			
		}
		
		else if (selectpiece.checkMove(start, end, cond) == false){
			System.out.println("illegal move, try again");
		}
		
	}
	
	public Coordinates[] KingFinder(){
		

		Coordinates[] kingarray = new Coordinates[2];
		
		
			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {
					if(board[i][j].piece != null){
						if (board[i][j].piece.toString().equals("bK"))
						{
							Coordinates king = board[i][j].coords;
							System.out.println("black king coords"+king.rank + king.file);
							kingarray[0] = king;
						}
					}
				}
			}

			for (int i = 0; i <= 7; i++) {
				for (int j = 0; j <= 7; j++) {	
					if(board[i][j].piece != null){
						if (board[i][j].piece.toString().equals("wK"))
						{
							Coordinates king2 = board[i][j].coords;
							System.out.println("white king coords"+king2.rank + king2.file);
							kingarray[1] = king2;
							
						}
					}
				}
			}	
		
		 return kingarray;
		
	}
	
	public boolean CheckChecker(Coordinates start, Coordinates end, boolean specialcase){
		
		
		
		
		Square startsquare = board[start.rank][start.file];
		Square endsquare = board[end.rank][end.file];
		Piece selectpiece = startsquare.piece;
		Piece targetpiece = endsquare.piece;
		
		Conditions cond = new Conditions();
		
		cond.isAttacking = targetpiece == null ? false : true;
		
		if (blockedPath(start, end) == true){
			cond.blocked = true;
			return false;
		}
		
		else if (selectpiece == null){
			return false;
		}
		
		else if (start.file == end.file && start.rank == end.rank){
			return false;
		}
		
		else if (specialcase == false){
		
			 if ( (selectpiece.isWhite == true && this.whiteTurn == false) || (selectpiece.isWhite == false && this.whiteTurn == true) ){
				return false;
			}
			 
			 if (cond.isAttacking == true &&  (targetpiece.isWhite == this.whiteTurn) )
				{
				return false;
				}
			 
			 if (selectpiece.checkMove(start, end, cond) == true){
					System.out.println("CHECK!!!!!!!");
					return true;	
				}
				
			if (selectpiece.checkMove(start, end, cond) == false){
					return false;
				}
		} 
		
		else if (specialcase == true){
			
			 if ( (selectpiece.isWhite == true && this.whiteTurn == true) || (selectpiece.isWhite == false && this.whiteTurn == false) ){
				return false;
			}
			 
			 if (selectpiece.checkMove(start, end, cond) == true){
					System.out.println("CHECK!!!!!!!");
					return true;	
				}
				
				if (selectpiece.checkMove(start, end, cond) == false){
					return false;
				}
		} 
		
		
		
		return false;
		
	}
	
	public void testmove(Coordinates start, Coordinates end)
	{
		this.board[end.rank][end.file].piece = this.board[start.rank][start.file].piece;
		this.board[start.rank][start.file].piece = null;
	}
	
	public void move(Coordinates start, Coordinates end){
		this.board[end.rank][end.file].piece = this.board[start.rank][start.file].piece;
		this.board[start.rank][start.file].piece = null;
		this.moves++;
		
	
		
		if(this.whiteTurn == false){
		this.whiteTurn = true;
		}
		
		else{
			this.whiteTurn = false;
		}
	}
	
	public boolean blockedPath(Coordinates start, Coordinates end){
		
			if (start.file == end.file) {

				if (start.rank < end.rank) {
				for (int i = start.rank + 1; i < end.rank; i++) {
					if (board[i][start.file].isOccupied()) {
						System.out.println("illegal move, try again");
						return true;
					}
				}
				} else {
					for (int i = start.rank - 1; i > end.rank; i--) {
						if (board[i][start.file].isOccupied()) {
							System.out.println("illegal move, try again");
							return true;
						}
					}
				}

			} else if (start.rank == end.rank) {
				if (start.file < end.file) {
				for (int i = start.file + 1; i < end.file; i++) {
					if (board[start.rank][i].isOccupied()) {
						System.out.println("illegal move, try again");
						return true;
					}
				} 
				} else {
					for (int i = start.file - 1; i > end.file; i--) {
						if (board[start.rank][i].isOccupied()) {
							System.out.println("illegal move, try again");
							return true;
						}
					} 
				}
			} else if (start.isDiagonal(end)) {

			    {
					System.out.printf("\n\t\t\t%s isDiagonalTo %s\n",
							start.toString(), end.toString());
				}

				if (start.isAdjacentTo(end)) {
					if (this.getSquare(end).isOccupied() && (this.getSquare(end).piece.isWhite == whiteTurn))
						
						{System.out.println("illegal move try again!");
						return true;
						}
					
					} else {
	
						int diagFile;
						int diagRank;
	
						int currRank = start.rank;
						int currFile = start.file;
	
						if (end.file > start.file) {
							diagFile = 1;
						} else {
							diagFile = -1;
						}
	
						if (end.rank > start.rank) {
							diagRank = 1;
						} else {
							diagRank = -1;
						}
	
						currRank += diagRank;
						currFile += diagFile;
	
						while (currFile != end.file
								&& currRank != end.rank) {
							{
								System.out.println("\t\t\tChecking " + currRank
										+ " " + currFile);
							}
	
							if (this.board[currRank][currFile].isOccupied()) {
								{
									System.out.println("Occupied!" + currRank
											+ " " + currFile);
								}
								return true;
							}
	
							currFile += diagFile;
							currRank += diagRank;
						}
					}
				}

			return false;
		}

	
	public Piece getPromoPiece(String promo) {
		if (promo.equals("R")) {
			return new Rook(!this.whiteTurn);
		} else if (promo.equals("N")) {
			return new Knight(!this.whiteTurn);
		} else if (promo.equals("B")) {
			return new Bishop(!this.whiteTurn);
		} else {
			return new Queen(!this.whiteTurn);
		}
	}


public Square getSquare(Coordinates c) {
	return board[c.rank][c.file];
}
	
	
	public boolean Check(){
		return false;
	}
	
	
	public String toString() {
		String str = "";

		for (int i = 0; i <= 7; i++) {
			for (int j = 0; j <= 7; j++) {
				str += this.board[i][j].toString() + " ";
			}

			str += (8 - i) + "\n";    /* changed */
		}

		str += " a  b  c  d  e  f  g  h\n";

		return str;
	}
	
	public boolean whiteTurnCheck() {
		return whiteTurn;
	}
	
	private char getFileChar(int file) {
		return (char) (file + 'a');
	}
}
