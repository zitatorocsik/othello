package comp249.othello;

public abstract class Position {
	
	private char piece;
	final public static char EMPTY = ' ';
	final public static char WHITE = 'W';
	final public static char BLACK = 'B';
	
	//getter and setter
	public char getPiece() { return this.piece; }

	public void setPiece(char piece) { this.piece = piece; }

	//abstract method to determine whether position is playable
	public abstract boolean canPlay();
	public abstract void flipPiece();
}
