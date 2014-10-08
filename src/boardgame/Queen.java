package boardgame;

public class Queen extends Piece{
	
	public Queen(boolean isWhite){
		super(isWhite);
	}
	
public boolean validCheck (){
		
		return true;
		
	}

public String toString(){
	
	if (super.isWhite == true){
		return "wQ";
	}
	
	else{
		return "bQ";
	}
}


public boolean checkMove(Coordinates start, Coordinates end, Conditions c){


		if (start.file == end.file) {
				return true;
			} 
		else if (start.rank == end.rank) {
				return true;
			} 
		else if (start.isDiagonal(end)) {
				return true;
			}
	
	return false;
}
}
