package boardgame;

public class Bishop extends Piece {
	
	public Bishop(boolean isWhite){
		super(isWhite);
	}
	
public boolean validCheck (){
		
		return true;
		
	}

public String toString(){
	
	if (super.isWhite == true){
		return "wB";
	}
	
	else{
		return "bB";
	}
}


public boolean checkMove(Coordinates start, Coordinates end, Conditions c)
{
	
	
		if (start.isDiagonal(end))
		{
			System.out.println("DIAGONAL!");
			return true;
			
		}
		
		else
		{
			return false;
		}
		
}

}
