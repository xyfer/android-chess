package boardgame;

public class Square {
	
	public Coordinates coords;
	public Piece piece;

	
	public Square(Coordinates coords){
		
		this.coords = coords;
	}
	
	public boolean isOccupied(){
		
		if (this.piece == null)
		{
			return false;
		}
		
		else{
		return true;}
	}
	
	public String toString() {
		if (this.piece == null) {
			if ((this.coords.file % 2 == 0 && this.coords.rank % 2 == 0)
					|| (this.coords.file % 2 != 0 && this.coords.rank % 2 != 0)) {
				return "  ";
			} else {
				return "##";
			}
		} else {
			return this.piece.toString();
		}
	}
	
	
	
	
	
}
