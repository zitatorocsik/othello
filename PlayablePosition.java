package comp249.othello;

public class PlayablePosition extends Position {
	
	
	//constructor
	public PlayablePosition(char piece) {
		super();
		this.setPiece(piece);
	}
	
	//implementing abstract canPlay() method from parent class
	public boolean canPlay() {
		if (this.getPiece() == EMPTY) return true;
		else return false;
	}
	
	public void flipPiece() {
		if (this.getPiece() == BLACK) {
			this.setPiece(WHITE);
		} else if (this.getPiece() == WHITE) {
			this.setPiece(BLACK);
		}
	}

}
