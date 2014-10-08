package boardgame;

public class Piece {
	
	public boolean isWhite;
	
	public Piece(boolean isWhite){
		
		this.isWhite = isWhite;
	}
	
	public boolean checkMove(Coordinates start, Coordinates end, Conditions c) {

		return true;
	}

	
	public String toString(){
		if (isWhite) {
			return "white";
		}
		else {
			return "black";
		}
	}
	
	public boolean inBounds(Coordinates end)
	{
		if (end.rank <8 && end.rank >=0 && end.file <8 && end.file > 0 ){
			return true;
		}
		
		
		else{
			return false;
		}
	}
	


}
