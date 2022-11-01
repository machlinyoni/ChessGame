package utils;

import main.exceptions.InvalidInputException;
import main.exceptions.InvalidMoveException;

public class Convertors {


	public static int convertInputToIndex(String a) throws InvalidInputException {
		int num;
		switch ( a ) {
			case "a":
			case "1":
				num = 0;
				break;
			case "b":
			case "2":
				num = 1;
				break;
			case "c":
			case "3":
				num = 2;
				break;
			case "d":
			case "4":
				num = 3;
				break;
			case "e":
			case "5":
				num = 4;
				break;
			case "f":
			case "6":
				num = 5;
				break;
			case "g":
			case "7":
				num = 6;
				break;
			case "h":
			case "8":
				num = 7;
				break;
			default:
				throw new InvalidInputException( "Invalid input " );

		}
		return num;
	}

	public static int convertRow(int a) throws InvalidInputException {
		if ( a == 9 ) return 9;
		if ( a < 1 || a > 8 ) {
			throw new InvalidInputException( "Invalid input" );
		}
		return a - 1;
	}
}
