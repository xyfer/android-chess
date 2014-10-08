package main;

import model.*;
import driver.*;

import java.util.Scanner;

import boardgame.Driver;
import boardgame.inputs;

public class Chess {

	public static void main(String[]args) throws Exception
	{
		Driver driver = new Driver();
		inputs i = new inputs(driver);
		Scanner in = new Scanner(System.in);

		while (!driver.gameHasConcluded) {
			i.printBoard();
			i.printPrompt();
			String arg = in.nextLine();
			i.giveArgument(arg);
		}
	}
}

