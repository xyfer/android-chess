package boardgame;

public class Pawn extends Piece {
	
	public Pawn(boolean isWhite){
		super(isWhite);
	}
	

public boolean validCheck (){
		
		return true;
		
	}

public String toString(){
	
	if (super.isWhite == true){
		return "wP";
	}
	
	else{
		return "bP";
	}
}

public boolean checkMove(Coordinates start, Coordinates end, Conditions c){
	
	
	if (isWhite) {
		c.FirstMove = start.rank == 6 ? true : false;
		if (c.promoting)
		{c.promotable = end.rank == 0 ? true : false;} 
	} else {
		c.FirstMove = start.rank == 1 ? true : false;
		
		if (c.promoting)
			
		{c.promotable = end.rank == 7 ? true : false;} 
	}
	
	
	

	 if(c.isAttacking == false){
			
			if(c.FirstMove == false){	
		
				if(start.file == end.file)
				{	
				
					if(isWhite == true){
							
						if(end.rank == start.rank -1)
						{
							return true;
						}
						
						else{
							return false;
						}
					}	
					if(isWhite == false){
						if(end.rank == start.rank +1)
						{
							return true;
						}
						
						else{
							return false;
						}
					
					}
				}
			}		
			if(c.FirstMove == true){
				if(start.file == end.file)
				{	
				
					if(isWhite == true){
							
						if(end.rank == start.rank -1 || end.rank == start.rank -2  )
						{
							return true;
						}
						
						else{
							return false;
						}
					}	
					if(isWhite == false){
						if(end.rank == start.rank +1 || end.rank == start.rank +2 )
						{
							return true;
						}
						
						else{
							return false;
						}
					
					}
				}
			}
	 }
	 
		 if (c.isAttacking == true)
		 {
				if (start.file - 1 == end.file || start.file + 1 == end.file) {
					if (isWhite)
						return (start.rank - 1 == end.rank);
					else
						return (start.rank + 1 == end.rank);
				} else
					return false;
		 }
			
	
	
return false;
}


}
	

