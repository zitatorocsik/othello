package comp249.othello;

public class Board {
	
	private Position[] board = new Position[64];
		
	
	public Position[] getBoard() {
		return board;
	}

	public void setBoard(Position[] board) {
		this.board = board;
	}

	//constructor that sets board positions to given string of positions
	public Board(String positions) {
		
		//these four positions will always be unplayable in every instance of this othello game
		this.board[16] = new UnplayablePosition();
		this.board[24] = new UnplayablePosition();
		this.board[32] = new UnplayablePosition();
		this.board[40] = new UnplayablePosition();
		
		//setting all other positions to playable positions
		for (int i = 0; i < positions.length(); i++) {
			if (i != 16 && i != 24 && i != 32 && i != 40) {
				this.board[i] = new PlayablePosition(positions.charAt(i));
			} else {
				continue;
			}
		}
	}
	
	//method to print current board to console
	public void drawBoard() {
		
		System.out.println("    0   1   2   3   4   5   6   7");
		
		for (int i = 0; i < 8; i++) {
			System.out.println("  ---------------------------------");
			System.out.print(i + " ");
			for (int j = 0; j<8; j++) {
				System.out.print("| " + this.board[j+i*8].getPiece() + " ");
				if (j== 7) {
					System.out.print("|");
				}
			}
			System.out.println("");
		}
		System.out.println("  ---------------------------------");
		
	}
	
	@Override
	public String toString() {
		
		String boardState = "";
		
		for (int i = 0; i < board.length; i ++) {
			boardState = boardState + board[i].getPiece();
		}
		
		return boardState;
	}

	//check if move is valid and turn pieces if yes
	public boolean makeMove(int x, int y, Player current) {
		int index = x+(8*y);
				
		int changer = 1;
		boolean legal = false;
		
		//if its empty
		if (board[index].canPlay()) {

			//direction NW
			//if its within array, not equal to player's piece, not empty and not unplayable
			if (((x-1) > -1) && (y-1) > -1 && ((x-1) < 8) && ((y-1) < 8) && board[(x-1)+(8*(y-1))].getPiece() != current.getPiece() && board[(x-1)+(8*(y-1))].getPiece() != Position.EMPTY && board[(x-1)+(8*(y-1))].getPiece() != UnplayablePosition.UNPLAYABLE) {
				
				//while its the opposite colour
				while (((x-1-changer) > -1) && ((y-1-changer) > -1) && ((x-1-changer) < 8) && ((y-1-changer) < 8) && board[(x-1-changer)+(8*(y-1-changer))].getPiece() == board[(x-1)+(8*(y-1))].getPiece()) {
					changer++;
				}
				
				//if the last piece in that row of same colour is now equal to the player's piece, its a valid move
				if (((x-1-changer) > -1) && ((y-1-changer) > -1) && ((x-1-changer) < 8) && ((y-1-changer) < 8) && board[(x-1-changer)+(8*(y-1-changer))].getPiece() == current.getPiece()) {
					board[index].setPiece(current.getPiece());
					for (int i = 0; i < changer; i++) {
						board[(x-1-i)+(8*(y-1-i))].flipPiece();
					}
					changer = 1;
					legal = true;
				} else {
					changer = 1;
				}
			
			
			} 

			
			//direction N
			if (((x) > -1) && ((y-1) > -1) && ((x) < 8) && ((y-1) < 8) && board[(x)+(8*(y-1))].getPiece() != current.getPiece() && board[(x)+(8*(y-1))].getPiece() != Position.EMPTY && board[(x)+(8*(y-1))].getPiece() != UnplayablePosition.UNPLAYABLE) {
				
				while (x > -1 && (y-1-changer) > -1 && x < 8 && (y-1-changer) < 8 && board[(x)+(8*(y-1-changer))].getPiece() == board[(x)+(8*(y-1))].getPiece()) {
					changer++;
				}
				
				if (((x) > -1) && ((y-1-changer) > -1) && ((x) < 8) && ((y-1-changer) < 8) && board[(x)+(8*(y-1-changer))].getPiece() == current.getPiece()) {
					board[index].setPiece(current.getPiece());
					for (int i = 0; i < changer; i++) {
						board[(x)+(8*(y-1-i))].flipPiece();
					}

					changer = 1;
					legal = true;
				} else {
					changer = 1;
				}
			
			}
			
			//direction NE
			if (((x+1) > -1) && ((y-1) > -1) && ((x+1) < 8) && ((y-1) < 8) && board[(x+1)+(8*(y-1))].getPiece() != current.getPiece() && board[(x+1)+(8*(y-1))].getPiece() != Position.EMPTY && board[(x+1)+(8*(y-1))].getPiece() != UnplayablePosition.UNPLAYABLE) {
				
				while ((x+1+changer) > -1 && (y-1-changer) > -1 && (x+1+changer) < 8 && (y-1-changer) < 8 && board[(x+1+changer)+(8*(y-1-changer))].getPiece() == board[(x+1)+(8*(y-1))].getPiece()) {
					changer++;
				}
				
				if (((x+1+changer) > -1) && ((y-1-changer) > -1) && ((x+1+changer) < 8) && ((y-1-changer) < 8) && board[(x+1+changer)+(8*(y-1-changer))].getPiece() == current.getPiece()) {
					board[index].setPiece(current.getPiece());

					for (int i = 0; i < changer; i++) {
						board[(x+1+i)+(8*(y-1-i))].flipPiece();
					}
					changer = 1;
					legal = true;
				} else {
					changer = 1;
				}
			
			}
			
			//direction E
			if (((x+1) > -1) && ((y) > -1) &&((x+1) < 8) && ((y) < 8) && board[(x+1)+(8*(y))].getPiece() != current.getPiece() && board[(x+1)+(8*(y))].getPiece() != Position.EMPTY && board[(x+1)+(8*(y))].getPiece() != UnplayablePosition.UNPLAYABLE) {
				
				while ((x+1+changer) > -1 && (y) > -1 && (x+1+changer) < 8 && (y) < 8 && board[(x+1+changer)+(8*(y))].getPiece() == board[(x+1)+(8*(y))].getPiece()) {
					changer++;
				}
				
				if (((x+1+changer) > -1) && ((y) > -1) && ((x+1+changer) < 8) && ((y) < 8) && board[(x+1+changer)+(8*(y))].getPiece() == current.getPiece()) {
					board[index].setPiece(current.getPiece());
					for (int i = 0; i < changer; i++) {
						board[(x+1+i)+(8*(y))].flipPiece();
					}
					changer = 1;
					legal = true;
				} else {
					changer = 1;
				}
				
			}
			
			//direction SE
			if (((x+1) > -1) && ((y+1) > -1) &&((x+1) < 8) && ((y+1) < 8) && board[(x+1)+(8*(y+1))].getPiece() != current.getPiece() && board[(x+1)+(8*(y+1))].getPiece() != Position.EMPTY && board[(x+1)+(8*(y+1))].getPiece() != UnplayablePosition.UNPLAYABLE) {
				
				while ((x+1+changer) > -1 && (y+1+changer) > -1 && (x+1+changer) < 8 && (y+1+changer) < 8 && board[(x+1+changer)+(8*(y+1+changer))].getPiece() == board[(x+1)+(8*(y+1))].getPiece()) {
					changer++;
				}
				
				if (((x+1+changer) > -1) && ((y+1+changer) > -1) && ((x+1+changer) < 8) && ((y+1+changer) < 8) && board[(x+1+changer)+(8*(y+1+changer))].getPiece() == current.getPiece()) {
					board[index].setPiece(current.getPiece());

					for (int i = 0; i < changer; i++) {
						board[(x+1+i)+(8*(y+1+i))].flipPiece();
					}
					changer = 1;
					legal = true;
				} else {
					changer = 1;
				}
			}
			
			//direction S
			if (((x) > -1) && ((y+1) > -1) && ((x) < 8) && ((y+1) < 8) && board[(x)+(8*(y+1))].getPiece() != current.getPiece() && board[(x)+(8*(y+1))].getPiece() != Position.EMPTY && board[(x)+(8*(y+1))].getPiece() != UnplayablePosition.UNPLAYABLE) {
				while ((x) > -1 && (y+1+changer) > -1 && (x) < 8 && (y+1+changer) < 8 && board[(x)+(8*(y+1+changer))].getPiece() == board[(x)+(8*(y+1))].getPiece()) {
					changer++;
				}
				
				if (((x) > -1) && ((y+1+changer) > -1) && ((x) < 8) && ((y+1+changer) < 8) && board[(x)+(8*(y+1+changer))].getPiece() == current.getPiece()) {
					board[index].setPiece(current.getPiece());

					for (int i = 0; i < changer; i++) {
						board[(x)+(8*(y+1+i))].flipPiece();
					}
					
					changer = 1;
					legal = true;
				} else {
					changer = 1;
				}
				
			}
			
			//direction SW
			if (((x-1) > -1) && ((y+1) > -1) && ((x-1) < 8) && ((y+1) < 8) && board[(x-1)+(8*(y+1))].getPiece() != current.getPiece() && board[(x-1)+(8*(y+1))].getPiece() != Position.EMPTY && board[(x-1)+(8*(y+1))].getPiece() != UnplayablePosition.UNPLAYABLE) {
				
				while ((x-1-changer) > -1 && (y+1+changer) > -1 && (x-1-changer) < 8 && (y+1+changer) < 8 && board[(x-1-changer)+(8*(y+1+changer))].getPiece() == board[(x-1)+(8*(y+1))].getPiece()) {
					changer++;
				}
				
				if (((x-1-changer) > -1) && ((y+1+changer) > -1) && ((x-1-changer) < 8) && ((y+1+changer) < 8) && board[(x-1-changer)+(8*(y+1+changer))].getPiece() == current.getPiece()) {
					board[index].setPiece(current.getPiece());

					for (int i = 0; i < changer; i++) {
						board[(x-1-i)+(8*(y+1+i))].flipPiece();
					}
					changer = 1;
					legal = true;
				} else {
					changer = 1;
				}
				
			}
			
			//direction W
			if (((x-1) > -1) && ((y) > -1) && ((x-1) < 8) && ((y) < 8) && board[(x-1)+(8*(y))].getPiece() != current.getPiece() && board[(x-1)+(8*(y))].getPiece() != Position.EMPTY && board[(x-1)+(8*(y))].getPiece() != UnplayablePosition.UNPLAYABLE) {
				while ((x-1-changer) > -1 && (y) > -1 && (x-1-changer) < 8 && (y) < 8 && board[(x-1-changer)+(8*(y))].getPiece() == board[(x-1)+(8*(y))].getPiece()) {
					changer++;
				}
				
				if (((x-1-changer) > -1) && ((y) > -1) && ((x-1-changer) < 8) && ((y) < 8) && board[(x-1-changer)+(8*(y))].getPiece() == current.getPiece()) {
					board[index].setPiece(current.getPiece());

					for (int i = 0; i < changer; i++) {
						board[(x-1-i)+(8*(y))].flipPiece();
				}
					changer = 1;
					legal = true;
				} else {
					changer = 1;
				}
			}	
		}  
		
		return legal;
			
	}

	public boolean gameOver(Player first, Player second) {
		if (!this.hasValidMove(first) && !this.hasValidMove(second)) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean hasValidMove(Player current) {
		boolean valid = false;
		//get the colour of current player
		char currentPiece = current.getPiece();
		char opponentPiece;
		if (currentPiece == 'B') {
			opponentPiece = 'W';
		} else {
			opponentPiece = 'B';
		}
		
		
		for (int i = 0; i < board.length; i ++) {
			int changer = 1;

			//check around all pieces of current player
			if (board[i].getPiece() == currentPiece) {
				int x = i % 8;
				int y = i / 8;
				
				//NW
				if (((x-1) > -1) && (y-1) > -1 && ((x-1) < 8) && ((y-1) < 8) && board[(x-1)+(8*(y-1))].getPiece() == opponentPiece) {
					while (((x-1-changer) > -1) && ((y-1-changer) > -1) && ((x-1-changer) < 8) && ((y-1-changer) < 8) && board[(x-1-changer)+(8*(y-1-changer))].getPiece() == opponentPiece) {
						changer++;
					}
					
					if (((x-1-changer) > -1) && ((y-1-changer) > -1) && ((x-1-changer) < 8) && ((y-1-changer) < 8) && board[(x-1-changer)+(8*(y-1-changer))].getPiece() == Position.EMPTY) {
						changer = 1;
						valid = true;
						break;
					} else {
						changer = 1;
						valid = false;
					}
					
				}
				
				//N
				if (((x) > -1) && ((y-1) > -1) && ((x) < 8) && ((y-1) < 8) && board[(x)+(8*(y-1))].getPiece() == opponentPiece) {
					
					while (x > -1 && (y-1-changer) > -1 && x < 8 && (y-1-changer) < 8 && board[(x)+(8*(y-1-changer))].getPiece() == board[(x)+(8*(y-1))].getPiece()) {
						changer++;
					}
					
					if (((x) > -1) && ((y-1-changer) > -1) && ((x) < 8) && ((y-1-changer) < 8) && board[(x)+(8*(y-1-changer))].getPiece() == Position.EMPTY) {
						
						changer = 1;
						valid = true;
						break;
					} else {
						changer = 1;
						valid = false;
					}
				
				}
				
				//direction NE
				if (((x+1) > -1) && ((y-1) > -1) && ((x+1) < 8) && ((y-1) < 8) && board[(x+1)+(8*(y-1))].getPiece() == opponentPiece) {
					
					while ((x+1+changer) > -1 && (y-1-changer) > -1 && (x+1+changer) < 8 && (y-1-changer) < 8 && board[(x+1+changer)+(8*(y-1-changer))].getPiece() == board[(x+1)+(8*(y-1))].getPiece()) {
						changer++;
					}
					
					if (((x+1+changer) > -1) && ((y-1-changer) > -1) && ((x+1+changer) < 8) && ((y-1-changer) < 8) && board[(x+1+changer)+(8*(y-1-changer))].getPiece() == Position.EMPTY) {
				
						changer = 1;
						valid = true;
						break;
					} else {
						changer = 1;
					}
				
				}
				
				//direction E
				if (((x+1) > -1) && ((y) > -1) &&((x+1) < 8) && ((y) < 8) && board[(x+1)+(8*(y))].getPiece() == opponentPiece) {
					
					while ((x+1+changer) > -1 && (y) > -1 && (x+1+changer) < 8 && (y) < 8 && board[(x+1+changer)+(8*(y))].getPiece() == board[(x+1)+(8*(y))].getPiece()) {
						changer++;
					}
					
					if (((x+1+changer) > -1) && ((y) > -1) && ((x+1+changer) < 8) && ((y) < 8) && board[(x+1+changer)+(8*(y))].getPiece() == Position.EMPTY) {
					
						changer = 1;
						valid = true;
						break;
					} else {
						changer = 1;
					}
					
				}
				
				//direction SE
				if (((x+1) > -1) && ((y+1) > -1) &&((x+1) < 8) && ((y+1) < 8) && board[(x+1)+(8*(y+1))].getPiece() == opponentPiece) {
					
					while ((x+1+changer) > -1 && (y+1+changer) > -1 && (x+1+changer) < 8 && (y+1+changer) < 8 && board[(x+1+changer)+(8*(y+1+changer))].getPiece() == board[(x+1)+(8*(y+1))].getPiece()) {
						changer++;
					}
					
					if (((x+1+changer) > -1) && ((y+1+changer) > -1) && ((x+1+changer) < 8) && ((y+1+changer) < 8) && board[(x+1+changer)+(8*(y+1+changer))].getPiece() == Position.EMPTY) {
						
						changer = 1;
						valid = true;
						break;
					} else {
						changer = 1;
					}
				}
				
				//direction S
				if (((x) > -1) && ((y+1) > -1) && ((x) < 8) && ((y+1) < 8) && board[(x)+(8*(y+1))].getPiece() == opponentPiece) {
					while ((x) > -1 && (y+1+changer) > -1 && (x) < 8 && (y+1+changer) < 8 && board[(x)+(8*(y+1+changer))].getPiece() == board[(x)+(8*(y+1))].getPiece()) {
						changer++;
					}
					
					if (((x) > -1) && ((y+1+changer) > -1) && ((x) < 8) && ((y+1+changer) < 8) && board[(x)+(8*(y+1+changer))].getPiece() == Position.EMPTY) {
						
						
						changer = 1;
						valid = true;
						break;
					} else {
						changer = 1;
					}
					
				}
				
				//direction SW
				if (((x-1) > -1) && ((y+1) > -1) && ((x-1) < 8) && ((y+1) < 8) && board[(x-1)+(8*(y+1))].getPiece() == opponentPiece) {
					
					while ((x-1-changer) > -1 && (y+1+changer) > -1 && (x-1-changer) < 8 && (y+1+changer) < 8 && board[(x-1-changer)+(8*(y+1+changer))].getPiece() == board[(x-1)+(8*(y+1))].getPiece()) {
						changer++;
					}
					
					if (((x-1-changer) > -1) && ((y+1+changer) > -1) && ((x-1-changer) < 8) && ((y+1+changer) < 8) && board[(x-1-changer)+(8*(y+1+changer))].getPiece() == Position.EMPTY) {
						
						changer = 1;
						valid = true;
						break;
					} else {
						changer = 1;
					}
					
				}
				
				//direction W
				if (((x-1) > -1) && ((y) > -1) && ((x-1) < 8) && ((y) < 8) && board[(x-1)+(8*(y))].getPiece() == opponentPiece) {
					while ((x-1-changer) > -1 && (y) > -1 && (x-1-changer) < 8 && (y) < 8 && board[(x-1-changer)+(8*(y))].getPiece() == board[(x-1)+(8*(y))].getPiece()) {
						changer++;
					}
					
					if (((x-1-changer) > -1) && ((y) > -1) && ((x-1-changer) < 8) && ((y) < 8) && board[(x-1-changer)+(8*(y))].getPiece() == Position.EMPTY) {
					
						changer = 1;
						valid = true;
						break;
					} else {
						changer = 1;
					}
				}	
			}
		}
		return valid;
	}
	
	public char winner() {
		char winningPiece;
		int white = 0;
		int black = 0;
		
		for (int i = 0; i < board.length; i++) {
			if (board[i].getPiece() == Position.BLACK) {
				black++;
			} else if (board[i].getPiece() == Position.WHITE) {
				white++;
			}
		}
		
		if (white > black) {
			winningPiece = 'W';
		} else if (white < black) {
			winningPiece = 'B';
		} else {
			winningPiece = 'N'; //incase there is a tie
		}
		 
		return winningPiece;
	}
	
}
