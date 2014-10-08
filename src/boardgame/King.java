package boardgame;

public class King extends Piece {
	
	public King(boolean isWhite){
		super(isWhite);
	}
	
public boolean validCheck (){
		
		return true;
		
	}

public String toString(){
	
	if (super.isWhite == true){
		return "wK";
	}
	
	else{
		return "bK";
	}
}


public boolean checkMove(Coordinates start, Coordinates end, Conditions c)
{

	
		if (start.isAdjacentTo(end)){
			return true;
		}
		
		else {
			return false;
		}	
		
	
}







}
