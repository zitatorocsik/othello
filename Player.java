package comp249.othello;

import java.util.Objects;

public class Player {
	
	private String name;
	private char piece;
	
	public Player(String name) {
		super();
		this.name = name;
	}
	
	
	public char getPiece() {
		return piece;
	}


	public void setPiece(char piece) {
		this.piece = piece;
	}


	//setter for name attribute
	public void setName(String name) {
		this.name = name;
	}
	
	//getter for name attribute
	public String getName() {
		return this.name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		return Objects.equals(name, other.name) && piece == other.piece;
	}

	
}
