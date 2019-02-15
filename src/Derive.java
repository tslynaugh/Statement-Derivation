/*
 * CS 421 Assignment 1
 * 
 * This program takes in a function as a program argument, and then determines
 * the right-most derivation of the statement, displaying each step
 * 
 * The grammer for this argument is:
 * Exp -> + Exp Exp | - Exp Exp | * Exp Exp | / Exp Exp | Literal
 * Literal -> Var | Int
 * Var -> a | b | ... | z
 * Int -> 0 | 1 | ... | 9
 * 
 * @author Thomas Lynaugh
 * 
 * Last Modified: February 13, 2019
 */

public class Derive {

	public static void main(String[] args) {
		deriveStatement(args);
	}

	/**
	 * Determine the right-most derivation of a statement with multiple symbols.
	 * Print out each step in the correct order.
	 * 
	 * @param argArray
	 *            - argArray is holding the program arguments, which is the original
	 *            derivation statement to be derived
	 */
	public static void deriveStatement(String[] args) {

		String[] argArray = argToArray(args);

		String[] steps = new String[100];

		System.out.println();

		// Keeps track of the line number
		int stepNum = 0;

		// Keeps track of the symbol in the statement being worked on
		int counter = 0;

		while (true) {

			// if the step is just one "Exp" symbol, then the derivation must be complete
			if (arrToString(argArray).replaceAll("\\s+", "").equals("Exp")) {
				steps[stepNum] = arrToString(argArray);

				break;
			}

			// if the symbol is "[op] Exp Exp", turn it into an "Exp" symbol and account for
			// the removed symbols
			if ((argArray[counter].equals("Exp") && (counter != argArray.length - 1) && (counter != 0))) {
				if ((argArray[counter + 1].equals("Exp") && (argArray[counter - 1] != null))) {

					argArray[counter - 1] = "Exp";

					int d = counter;
					for (d = counter; d < argArray.length - 2; d++) {
						argArray[d] = argArray[d + 2];
					}

					// Account for the two symbols removed
					argArray[d] = "";
					argArray[d + 1] = "";

					stepNum++;
					counter = 0;

					continue;
				}

				else {
					counter++;
				}

			}

			while (true) {
				// if the symbol is an operator, skip it for now
				if ((argArray[counter].equals("*")) || (argArray[counter].equals("/"))
						|| (argArray[counter].equals("+")) || (argArray[counter].equals("-"))) {

					counter++;
				} else {
					steps[stepNum] = arrToString(argArray);
					break;
				}

			}
			
			// if the symbol is either an integer or a variable
			if ((!argArray[counter].equals("Var")) && (!argArray[counter].equals("Int"))
					&& (!argArray[counter].equals("Exp")) && (!argArray[counter].equals("Literal"))
					&& !argArray[counter].equals(" ")) {

				// check if the argument is a Var or an Int
				try {
					// Check if the argument is an Int
					Integer.parseInt(argArray[counter]);
					argArray[counter] = "Int";
					counter = 0;
					stepNum++;
					continue;

				} catch (NumberFormatException e) {
					// not an Int, so it must be a Var
					argArray[counter] = "Var";
					counter = 0;
					stepNum++;
					continue;

				}
			}

			else if (argArray[counter].equals("Var") || argArray[counter].equals("Int")) {
				// These symbols must point to "Literal"
				argArray[counter] = "Literal";
				counter = 0;

				stepNum++;
				continue;
			}

			else if (argArray[counter].equals("Literal")) {
				// This symbol must point to "Exp"
				argArray[counter] = "Exp";
				counter = 0;

				stepNum++;
				continue;
			}

		}

		// Since the derivation was found going backwards from the original statement,
		// the steps must be printed backwards to be in the correct order
		printArrBackwards(steps);
	}

	/**
	 * Splits the arguments into different spaces in an array, split by the
	 * whitespace character
	 * 
	 * @param args
	 *            - Program arguments containing a statement to be derived
	 * @return argArray - An array that contains each program argument, split by the
	 *         whitespace character
	 */
	public static String[] argToArray(String[] args) {

		String[] argArray = args[0].split(" ");

		return argArray;
	}

	/**
	 * Concatenate each value in an array of Strings into one single String
	 * 
	 * @param arr
	 *            - An array of Strings
	 * @return result - a String that is made up of each String in the array
	 */
	public static String arrToString(String[] arr) {
		String result = "";
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				result += arr[i];

				// if not at the last argument, add a space
				if (i != arr.length - 1) {
					result += " ";
				}
			}

		}

		return result;
	}

	/**
	 * Print out each String in the array of Strings
	 * 
	 * @param args
	 *            - An array of strings
	 */
	public static void print(String[] arr) {
		// Print out values in the array

		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i]);
			System.out.print(" ");
		}

		System.out.println();
	}

	/**
	 * Print out each line of derivation backwards
	 * 
	 * @param steps
	 *            - steps in an array that holds the strings of each line in the
	 *            derivation process
	 */
	public static void printArrBackwards(String[] steps) {

		System.out.println(
				"--------------------------------------------------------------------------------------------------------");
		System.out.println("Finding the right-most derivation of the statement: " + steps[0]);
		System.out.println();

		for (int i = steps.length - 1; i >= 0; i--) {

			if (steps[i] != null) {
				System.out.println(steps[i]);
			}
		}
	}

}
