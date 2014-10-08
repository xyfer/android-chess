package boardgame;

public class Rook extends Piece {
	
	public Rook(boolean isWhite){
		super(isWhite);
	}

	public boolean validCheck (){
		
		return true;
		
	}
	
	public String toString(){
		
		if (super.isWhite == true){
			return "wR";
		}
		
		else{
			return "bR";
		}
	}
	
	public boolean checkMove(Coordinates start, Coordinates end, Conditions c)
	{
		
				if (start.file == end.file)
				{
					return true;
				}
				
				if (start.rank == end.rank)
				{
					return true;
				}
			
			
	return false;
	}
	
}
