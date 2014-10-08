package boardgame;

public class Knight extends Piece {
	
	public Knight(boolean isWhite){
		super(isWhite);
	}
	
	
public boolean validCheck (){
		
		return true;
		
	}

public String toString(){
	
	if (super.isWhite == true){
		return "wN";
	}
	
	else{
		return "bN";
	}
}



public boolean checkMove(Coordinates start, Coordinates end, Conditions c)
{
	if (start.rank + 2 == end.rank || start.rank - 2 == end.rank) {
		if (start.file + 1 == end.file || start.file - 1 == end.file) {
			return true;
		}
	} else if (start.file + 2 == end.file || start.file - 2 == end.file) {
			if (start.rank + 1 == end.rank || start.rank - 1 == end.rank) {
				return true;
			}
	}
			
	return false;
}







}
