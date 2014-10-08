package boardgame;

import java.io.Serializable;

public class Coordinates  implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public int rank;
	public int file;

	public Coordinates(int rank, char file)  throws Exception{
		
		if(rank >7 || rank < 0)
		{
			throw new Exception( "Invalid coordinates (rank out of bounds)");
		}
		
		
		else{
			int temp = getFileNum(file);
			
			if (temp >7 || temp < 0){
				throw new Exception( "Invalid coordinates (file out of bounds)");
			}
			
			else{
				this.rank = rank;
				this.file = temp;
			}	
		}
		
	}
	
	
	
	public int getRankNum(){
		
		return 0;
	}
	
	public int getFileNum(char f){
		
		return Character.toLowerCase(f) - 'a';
	}
	
	public char getFileChar(){
	
		return 0;
	}
	
	public boolean isDiagonal(Coordinates c) {

		if (this.equals(c)) {
			return false;
			
		} 
		else {
			int slope = this.getSlopeTo(c);

			System.out.println("slope: " + slope);

			if (slope == 1 || slope == -1)
				return true;
			else
				return false;
		}
	}

	
	
	public boolean hasSameRankAs(Coordinates c) {
		if (this.rank == c.rank)
			return true;
		else
			return false;
	}

	public boolean hasSameFileAs(Coordinates c) {
		if (this.file == c.file)
			return true;
		else
			return false;
	}

	
	public int getSlopeTo(Coordinates c) {
		int rankOne = this.rank + 1;
		int fileOne = this.file + 1;

		int rankTwo = c.rank + 1;
		int fileTwo = c.file + 1;

		int denominator = fileOne - fileTwo;

		if (denominator != 0)
			return (rankOne - rankTwo) / denominator;
		else
			return 0;
	}


	public boolean isAdjacentTo(Coordinates c) {
		if (this.rank <= c.rank + 1 && this.rank >= c.rank - 1) {
			
			if (this.file <= c.file + 1 && this.file >= c.file - 1) {
				return true;
			}
		}

		return false;
	}

	
	

}
