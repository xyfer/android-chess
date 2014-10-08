package boardgame;



public class Driver {
	
		private Board board;
		public boolean gameHasConcluded;

		public Driver() throws Exception {
			this.board = new Board();
		}

		public void resign() {
			gameHasConcluded = true;
		}

		public void makeMove(char fileOne, int rankOne, char fileTwo, int rankTwo, String promo) throws Exception{
			Coordinates ccpone;
			Coordinates ccptwo;
			
			if (promo != null) {
				if (!(promo.equals("N") || promo.equals("R") || promo.equals("Q") || promo.equals("B"))) {
					throw new Exception("Promotion selection must be valid.");
				}
			}
			
			try {
			
				ccpone = new Coordinates(rankOne, fileOne);
				ccptwo = new Coordinates(rankTwo, fileTwo);
			}
			
			catch (Exception e){
				throw e;
			}

			if (ccpone != null && ccptwo != null) {
				
				try{
					board.attemptMove(ccpone, ccptwo, promo);
					
				}
					catch (Exception e){
						throw e;
					}
					
			}
		}

		public String getBoardString() {
			return this.board.toString();
		}

		public boolean isWhiteTurn() {
			return board.whiteTurnCheck();
		}
}


