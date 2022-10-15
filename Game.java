package comp249.othello;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Game {
	private Board board;
	private Player first;
	private Player second;
	private Player current;
	private Player winner;
	private static Scanner sc = new Scanner(System.in);
	
	//getters and setters for each private attribute
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Player getFirst() {
		return first;
	}
	public void setFirst(Player first) {
		this.first = first;
	}
	public Player getSecond() {
		return second;
	}
	public void setSecond(Player second) {
		this.second = second;
	}
	public Player getCurrent() {
		return current;
	}
	public void setCurrent(Player current) {
		this.current = current;
	}
	
	public Game() {
		System.out.println("Welcome to Othello!");
		System.out.println("Enter a number that corresponds to an action.");
		System.out.println("1. Start a new game");
		System.out.println("2. Load a game");
		System.out.println("3. Quit");
		int option = sc.nextInt();
		
		if (option == 1) 
			start();
		else if (option == 2)
			try {
				String[] savedState = load();
				this.first = new Player(savedState[0]);
				this.first.setPiece('W');
				this.second = new Player(savedState[1]);
				this.second.setPiece('B');
				if (savedState[2] == first.getName()) {
					this.current = first;
				} else 
					this.current = second;
				this.board = new Board(savedState[3]);
				play();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}		
	}
	
	//function to start a game of othello
	public void start() {
		System.out.println("Please enter two player names:");
		this.first = new Player(sc.next());
		this.second = new Player(sc.next());
		this.first.setPiece('W');
		this.second.setPiece('B');
		this.current = first;
		System.out.println("To start a new game please choose a starting position. \nOriginal(0), NW offset(1), NE offset(2), SE offest(3), SW offset(4).");
		int startPosition = sc.nextInt();
		if (startPosition == 0) {
			this.board = new Board("                           WB      BW                           ");
		} else if (startPosition == 1) {
			this.board = new Board("                  WB      BW                                    ");

		} else if (startPosition == 2) {
			this.board = new Board("                    WB      BW                                  ");

		} else if (startPosition == 3) {
			this.board = new Board("                                    WB      BW                  ");

		} else if (startPosition == 4) {
			this.board = new Board("                                  WB      BW                    ");

		}
		play();
		sc.close();
	}
	
	//function to load a saved game
	public static String[] load() throws FileNotFoundException {
		String[] gameState = new String[4];
		System.out.println("Please enter the file name:");
		File savedGame = new File(sc.next());
		if (savedGame.isFile()) {
			Scanner reader = new Scanner(savedGame);
			gameState[0] = reader.nextLine();
			gameState[1] = reader.nextLine();
			gameState[2] = reader.nextLine();
			gameState[3] = reader.nextLine();
			reader.close();
			
			}
		else {
			System.out.println("File does not exist");
		}
		
		return gameState;
	}
	
	//function that loops and plays the game
	public void play() {
		board.drawBoard();
		System.out.println(first.getName() + " you will play with white pieces. " + second.getName() + " you will play with black pieces.");
		System.out.println(current.getName() + " you will begin the game.");
		int choices;
		
		//loops while atleast one player has a valid move left
		while (!board.gameOver(this.first, this.second)) {
			
			//if current player has valid moves give option to move, save or forfeit
			if (board.hasValidMove(current)) {
				System.out.println(current.getName() + " would you like to make a move(1), save(2), or forfeit the game(3).");
				choices = sc.nextInt();
				
				//if player chooses to make a move
				if (choices == 1) {
					System.out.println(current.getName() + " please enter x and y coordinate to make a move:");
					
					if (board.makeMove(sc.nextInt(), sc.nextInt(), current)) {
						board.drawBoard();
					} else {
						System.out.println(current.getName() + " please make a valid move.");
						board.drawBoard();
						continue;
					}
					if (current.equals(first)) {
						current = second;
					} else if (current.equals(second)) 
						current = first;
					
				//if player chooses to save the game
				} else if (choices == 2) {
					save();	
				
				//if player chooses to forfeit the game
				} else if (choices == 3) {
					System.out.println(current.getName() + " you have forfeit the game.");
					if (current.equals(first)) {
						winner = second;
					} else if (current.equals(second)) {
						winner = first;
					}
					System.out.println(winner.getName() + " you won by forfeit!");
					System.exit(0);
				}
			} else {
				System.out.println(current.getName() + " you do not have any valid moves left. Do you wish to save(1), pass your turn(2), or forfeit the game(3)?");
				int noMovesOption = sc.nextInt();
				if (noMovesOption == 1) {
					save();
				} else if (noMovesOption == 2) {
					System.out.println(current.getName() + " you pass your turn.");
					if (current.equals(first)) {
						current = second;
					} else if (current.equals(second)) {
						current = first;
					}
				} else if (noMovesOption == 3) {
					System.out.println(current.getName() + " you have forfeit the game.");
					if (current.equals(first)) {
						winner = second;
					} else if (current.equals(second)) {
						winner = first;
					}
					System.out.println(winner.getName() + " you won by forfeit!");
					System.exit(0);
				}
				
			}
			
		}
		char winner = board.winner();
		if (winner == 'N') {
			System.out.println("The game is a tie.");
		} else if (winner == 'B') {
			System.out.println(this.second.getName() + " is the winner!");
		} else if (winner == 'W') {
			System.out.println(this.first.getName() + " is the winner!");
		}
	}
	
	//function to save the game being played
	public void save() {
		System.out.println("Please enter a filename for your saved game:");
		String fileName = sc.next();
		File savedGame = new File(fileName);
		try {
			savedGame.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			PrintWriter pw = new PrintWriter(savedGame);
			pw.println(this.first.getName());
			pw.println(this.second.getName());
			pw.println(this.current.getName());
			
			
			pw.println(board.toString());
			
			pw.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} 
		
		sc.close();
		System.exit(0);
	}
	
	
	
}
