package boardgame;

public class inputs {

	
	

	public Driver driver;
	public boolean drawOffered;

	public inputs(Driver driver) {
		this.driver = driver;
	}

	public void giveArgument(String arg) {
		if (arg.compareTo("resign") == 0) {
			System.out.println(this.driver.isWhiteTurn() ? "Black wins!" : "White wins!");
			this.driver.resign();
		}
		  if(drawOffered == true){
			 if (arg.compareTo("draw") == 0){
				 System.out.println("Draw.");
					System.exit(0);
		      }
			 else{
				 drawOffered = false;
			 }
		  }	 
		 if (arg.contains(" draw?")) {
			arg = arg.substring(0, arg.indexOf(" draw?"));
			this.drawOffered = true;
		} 
		
		 if (arg.length() == 5){
			char fileOne = arg.charAt(0);
			int rankOne = 8 - (Character.getNumericValue(arg.charAt(1)));   /* changes */

			char fileTwo = arg.charAt(3);
			int rankTwo = 8 - (Character.getNumericValue(arg.charAt(4)));    /* changes */

			try{
				driver.makeMove(fileOne, rankOne, fileTwo, rankTwo, null);
			}
			
			catch (Exception e)
			{
				e.getMessage();
			}
		}
		 else if (arg.length() == 7) {
				char fileOne = arg.charAt(0);
				int rankOne = Character.getNumericValue(arg.charAt(1)) -1;

				char fileTwo = arg.charAt(3);
				int rankTwo = Character.getNumericValue(arg.charAt(4)) -1;
				
				
				char promochar = Character.toUpperCase(arg.charAt(6));
				
			String promo = promochar+"";

			try {
				driver.makeMove(fileOne, rankOne, fileTwo, rankTwo, promo);
			} 
			catch (Exception e)
			{
				System.out.println(e.getMessage() );
			} 
		}
}	

	public void printBoard() {
		System.out.println(this.driver.getBoardString());
	}

	public void printPrompt() {
		System.out.print(driver.isWhiteTurn() ? "White's turn: " : "Blacks's turn: ");
	}
}

