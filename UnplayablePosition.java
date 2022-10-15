package comp249.othello;

public class UnplayablePosition extends Position {
	
	final public static char UNPLAYABLE = '*';
	
	//constructor sets piece to unplayable
	public UnplayablePosition() {
		super();
		this.setPiece(UNPLAYABLE);
	}
	
	//implementing method from abstract parent class position
	public boolean canPlay() {
		return false;
	}
	
	public void flipPiece() {
	}
		
}
