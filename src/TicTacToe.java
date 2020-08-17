import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;



public class TicTacToe {
	
	public static ArrayList<Integer> playerPositions = new ArrayList<Integer>();
	public static ArrayList<Integer> aiPositions = new ArrayList<Integer>();
	public static boolean continueGame = false;
	public static String result = "";
	public static char[][] gameBoard = {{' ', '|', ' ', '|', ' '}, {'-','+','-','+','-'}, {' ', '|', ' ', '|', ' '}, 
			{'-','+','-','+','-'}, {' ', '|', ' ', '|', ' '}};

	
	public static void main(String[] args) {
		while(true) {
			if (!result.isEmpty()) {
				try {
					Thread.sleep(5000);
					for(int i = 0; i < 5; i = i + 2) {
						for (int j = 0; j < 5; j = j + 2)
							gameBoard[i][j] = ' ';
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				result = "";
			}
		
		System.out.println("Tic-Tac-Toe:");
		printGameBoard(gameBoard);
		Scanner scan = new Scanner(System.in);
		if(continueGame == false) {
		System.out.println("\nDo you want to start your game?: YES or NO");
		String temp = scan.next();
		
		if (temp.equals("YES") || temp.equals("yes") || temp.equals("Yes")) {
			continueGame = true;
			aiPositions.clear();
			playerPositions.clear();
		}
		else if (temp.equals("NO") || temp.equals("no") || temp.equals("No"))
			System.exit(0);
		}

		while(continueGame == true) {
			System.out.println("Write the desired cage (1-9):");
			int playerPos = scan.nextInt();
			while (playerPositions.contains(playerPos) || aiPositions.contains(playerPos)) {
				System.out.println("\nThat cage is already taken, please choose another one");
				System.out.println("Write the desired cage (1-9):");
				playerPos = scan.nextInt();
			}
			while (playerPos < 1 || playerPos > 9) {
				System.out.println("\nInvalid number");
				System.out.println("Write the desired cage (1-9):");
				playerPos = scan.nextInt();
			}
			playerPositions.add(playerPos);
			placement(gameBoard, playerPos, "player");
			
			System.out.println(winningConditions());
			if (continueGame == false) 
				printGameBoard(gameBoard);
			if(continueGame == true) {
			Random rand = new Random();
			int aiPos = rand.nextInt(9)+1;
			while (playerPositions.contains(aiPos) || aiPositions.contains(aiPos) || aiPos == playerPos) {
				aiPos = rand.nextInt(9)+1;
			}
			aiPositions.add(aiPos);
			placement(gameBoard, aiPos, "AI");
			printGameBoard(gameBoard);
			System.out.println(winningConditions());
			}
		}
		}
	}

	
	
	public static void printGameBoard(char[][] gameBoard) {
		for(char[] row : gameBoard) {
			for(char col : row)
				System.out.print(col);
			System.out.println();
		}
	}
	
	public static void placement(char[][] gameBoard, int pos, String user) {
		char symbol = 'X';
		if (user != "player")
			symbol = 'O';
		switch(pos){
			case 1:
				gameBoard[0][0] = symbol;
				break;
			case 2:
				gameBoard[0][2] = symbol;
				break;
			case 3:
				gameBoard[0][4] = symbol;
				break;
			case 4:
				gameBoard[2][0] = symbol;
				break;
			case 5:
				gameBoard[2][2] = symbol;
				break;
			case 6:
				gameBoard[2][4] = symbol;
				break;
			case 7:
				gameBoard[4][0] = symbol;
				break;
			case 8:
				gameBoard[4][2] = symbol;
				break;
			case 9:
				gameBoard[4][4] = symbol;
				break;
		}
	}
	
	public static String winningConditions() {
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List rightCol = Arrays.asList(3,6,9);
		List firstCross = Arrays.asList(1,5,9);
		List secondCross = Arrays.asList(3,5,7);
		
		List<List> winningCond = new ArrayList<List>();
		winningCond.add(topRow);
		winningCond.add(midRow);
		winningCond.add(botRow);
		winningCond.add(leftCol);
		winningCond.add(midCol);
		winningCond.add(rightCol);
		winningCond.add(firstCross);
		winningCond.add(secondCross);
		
		for (List l : winningCond) {
			if (playerPositions.containsAll(l)) {
				continueGame = false;
				result = "You win\n";
			}
			if (aiPositions.containsAll(l)) {
				continueGame = false;
				result = "You lose\n";
			}
			else if (aiPositions.size() + playerPositions.size() == 9) {
				continueGame = false;
				result = "Draw\n";
			}
		}
		return result;
	}
}
