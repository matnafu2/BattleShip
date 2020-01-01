import java.util.Random;
import java.util.Scanner;

public class BattleShip {
	static Scanner keyboard = new Scanner(System.in);
	static int userShip = 5;
	static int computerShip = 5;

	public static void main(String[] args) {
		System.out.println("\n******* BATTLE SHIP GAME ***********\n");
		System.out.println("****      About the Game   ***********\n");
		System.out.println("You and the computer will deploy 5 ships, and taking turns\n"
				+ "Both you and the computer try to guess where your opponent\n"
				+ "ships are located. If you sink the computers ship by guessing\n"
				+ "the correct coordinates or if the computer sinks one of its own\n"
				+ "ships, you will get a hit. And it will be displayed as \"!\".\n"
				+ "If the computer sunk you ship or you sunk your own, it will be\n"
				+ "displayed as \"x\". Finally, when you miss, you will see \"-\" symbol");

		System.out.println("\n******   GOOD LUCK   *******\n");
		System.out.println("******* HERE IS YOU MAP  ******** \n");
		int[][] oceanMap = new int[10][10];
		deploy(oceanMap);
		play(oceanMap);
	}

	public static void deploy(int[][] oceanMap) {
		int x, y;
		display(oceanMap);
		System.out.println("\nYou Will Deploy 5 Ships");
		for (int round = 0; round < 5; round++) {
			System.out.print("Enter X coordinate for your ship " + (round + 1) + ": ");
			x = keyboard.nextInt();
			if (x < 0 || x > 9)
				x = validateNum(x);
			System.out.print("Enter Y coordinate for your ship " + (round + 1) + ": ");
			y = keyboard.nextInt();
			if (y < 0 || y > 9)
				y = validateNum(y);

			while (oceanMap[x][y] == 1) {
				System.out.println("\nYou can not deploy two ships in one place\n" + "Choose another place\n");
				System.out.print("Enter another X coordinate for your ship " + (round + 1) + ": ");
				x = keyboard.nextInt();
				System.out.print("Enter another Y coordinate for your ship " + (round + 1) + ": ");
				y = keyboard.nextInt();

			}

			oceanMap[x][y] = 1;
		}
		computerDeployShip(oceanMap);
		display(oceanMap);

	}

	public static int validateNum(int num) {
		System.out.println("Error!! Invalid number." + "You must not Enter a value less than 0 or greater than 9\n");
		System.out.print("Enter another number: ");
		num = keyboard.nextInt();
		while (num < 0 || num > 9) {
			System.out.print("Invalid number: Try again: ");
			num = keyboard.nextInt();
		}
		return num;
	}

	public static void display(int[][] oceanMap) {
		HArray(); // calling a HorizontalArray method to display the top rows before the 10x10
					// grid
		for (int a = 0; a < oceanMap.length; a++) {
			System.out.print(a + " |"); // prints out the first "0 and |" indexes every time it loops
			for (int b = 0; b < oceanMap.length; b++) {

				if (oceanMap[a][b] == 1)
					System.out.print("@");
				else if (oceanMap[a][b] == 3)
					System.out.print("x");
				else if (oceanMap[a][b] == 4)
					System.out.print("!");
				else if (oceanMap[a][b] == 5)
					System.out.print("-");
				else
					System.out.print(" ");
			}
			System.out.println("| " + a); // add "| and 0" at the end of the array
		}

		HArray();
	}

	public static void HArray() {
		int z = 0; // initializing variable for the while loop
		while (z < 10) // loop 10 times
		{
			if (z == 0) // indents two space the first time the while loop starts
				System.out.print("   "); // prints a double space

			System.out.print(z); // print out the value of z
			z++; // increment z to control the while loop
			if (z == 10) // checks whether z is equal to 10
				System.out.println(); // print new line if z = 10 in order to make a space
										// between the horizontal numbers and the grid
		}
	}

	public static void computerDeployShip(int[][] grid) {
		Random rand = new Random();
		int x, y;
		System.out.println("\nComputer is deploying sheep");
		for (int i = 0; i < 5; i++) {
			x = rand.nextInt(10);
			y = rand.nextInt(10);

			while (grid[x][y] == 1) {
				x = rand.nextInt(10);
				y = rand.nextInt(10);
			}
			grid[x][y] = 2; // code for computer ship
			System.out.println("Ship " + (i + 1) + " is Deployed");

		}
	}

	public static void play(int[][] ship) {
		boolean playAgain = false;
		String result;
		do {
			if (playAgain) {
				restOceanMap(ship);
				userShip = computerShip = 5;
				deploy(ship);
			}

			System.out.println("\n******   Time to Battle   ******\n");
			int x, y;

			// int checkUserShip = checkShip(ship, 1);
			// int checkAIShip = checkShip(ship, 2);
			while (userShip > 0 && computerShip > 0) {
				System.out.println("****************");
				System.out.println("   YOUR TURN  ");
				System.out.println("****************");

				System.out.print("Enter X coordinate: ");
				x = keyboard.nextInt();
				if (x < 0 || x > 9)
					x = validateNum(x);
				System.out.print("Enter Y coordinate: ");
				y = keyboard.nextInt();
				if (y < 0 || y > 9)
					y = validateNum(y);

				while (ship[x][y] == 3 || ship[x][y] == 4 || ship[x][y] == 5) {
					System.out.println("You have already Gussed this location: Try again");
					System.out.print("Enter X coordinate: ");
					x = keyboard.nextInt();

					System.out.print("Enter Y coordinate: ");
					y = keyboard.nextInt();

				}

				if (ship[x][y] == 1) {
					System.out.println("*********************************");
					System.out.println("\nOH NO! YOU SUNK YOUR OWN SHIP\n");
					System.out.println("*********************************");
					ship[x][y] = 3; // code when you sunk your own ship
					display(ship);
					userShip -= 1;
					// System.out.println("\nYour ship: " + (userShip -= 1) + " | " + "Computer
					// ship: "+ computerShip);
				} else if (ship[x][y] == 2) {
					System.out.println("*************************************");
					System.out.println("\nBOOM! YOU SUNK THE COMPUTER'S SHIP\n");
					System.out.println("*************************************");
					ship[x][y] = 4; // code when the computer ship sunk;
					display(ship);
					computerShip -= 1;
					// System.out.println("\nYour ship: " + (userShip) + " | " + "Computer ship: "+
					// (computerShip -= 1));
				} else {
					System.out.println("*********************************");
					System.out.println("       SORRY, YOU MISSED          ");
					System.out.println("*********************************");
					ship[x][y] = 5; // code when the user miss
					display(ship);
				}
				System.out.println("\nYOUR SHIP: " + userShip + " | " + "COMPUTER SHIP: " + computerShip);
				System.out.println();

				if (computerShip != 0 && userShip != 0)
					computerPlay(ship);
				else if (computerShip == 0) {
					System.out.println("*********************************");
					System.out.println("  HOORAY! YOU WIN THE BATTLE ");
					System.out.println("*********************************");
				} else if (userShip == 0) {
					System.out.println("*********************");
					System.out.println("    COMPUTER WON    ");
					System.out.println("*********************");
				}

			}
			System.out.println("\n***************************\n");
			System.out.println("******** GAME OVER  ******** ");
			System.out.println("\n***************************\n");
			keyboard.nextLine();
			System.out.print("Do you want to play again? (Y or N): ");
			result = keyboard.nextLine();
			playAgain = result.toUpperCase().startsWith("Y");
		} while (playAgain);

		System.out.println("Thanks for playing");
		System.out.println("***** GOOD BUY *******");

	}

	public static void computerPlay(int[][] ship) {

		Random rand = new Random();
		int x, y;
		System.out.println("  \nCOMPUTER'S TURN ");
		x = rand.nextInt(10);
		y = rand.nextInt(10);

		while (ship[x][y] == 3 || ship[x][y] == 4 || ship[x][y] == 6) {
			x = rand.nextInt(10);
			y = rand.nextInt(10);

		}

		if (ship[x][y] == 1) {
			System.out.println("***************************************");
			System.out.println("\nTHE COMPUTER SUNK ONE OF YOUR SHIP\n");
			System.out.println("***************************************");
			ship[x][y] = 3; // code when a ship sinks
			display(ship);
			userShip -= 1;
			// System.out.println("\nYour ship: " + (userShip -= 1) + " | " + "Computer
			// ship: "+ computerShip);

		} else if (ship[x][y] == 2) {
			System.out.println("********************************************");
			System.out.println("\nTHE COMPUTER SUNK ONE OF ITS OWN SHIP\n");
			System.out.println("********************************************");
			ship[x][y] = 4; // code when the computer ship sunk;
			display(ship);
			computerShip -= 1;
			// System.out.println("\nYour ship: " + (userShip) + " | " + "Computer ship: "+
			// (computerShip -= 1));

		} else {
			System.out.println("*********************");
			System.out.println("   COMPUTER MISSED   ");
			System.out.println("*********************");
			ship[x][y] = 6; // code when computer misses
			// display(ship);

		}

		System.out.println("\nYour ship: " + userShip + " | " + "Computer ship: " + computerShip);

	}

	public static void restOceanMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++)
				map[i][j] = 0;
		}
	}

}
