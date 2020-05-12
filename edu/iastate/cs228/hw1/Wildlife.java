package edu.iastate.cs228.hw1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *  
 * @author Jonathon Schnell
 * @version 1.0
 * @since 2-4-2020
 * COM S 228
 * homework1
 *
 */

/**
 * 
 * The Wildlife class performs a simulation of a grid plain with squares
 * inhabited by badgers, foxes, rabbits, grass, or none.
 *
 */
public class Wildlife {
	/**
	 * Update the new plain from the old plain in one cycle.
	 * 
	 * @param pOld old plain
	 * @param pNew new plain
	 */
	public static void updatePlain(Plain pOld, Plain pNew) {
		// For every life form (i.e., a Living object) in the grid pOld, generate
		// a Living object in the grid pNew at the corresponding location such that
		// the former life form changes into the latter life form.
		//
		// Employ the method next() of the Living class.
		//iterates through whole plain
		for (int i = 0; i < pOld.getWidth(); i++) {
			for (int j = 0; j < pOld.getWidth(); j++) {
				//pnew gets assigned to pold.next of pnew
				//updates lifeform in thus element based on a census and the next() logic for each animal
				pNew.grid[i][j] = pOld.grid[i][j].next(pNew);

			}
		}
	}

	/**
	 * Repeatedly generates plains either randomly or from reading files. Over each
	 * plain, carries out an input number of cycles of evolution.
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		//experimental code tests
		// Plain pOld = new Plain(3);
		// Plain pNew = new Plain(3);
		// System.out.println(pOld.getWidth());
		// pOld.randomInit();
		// System.out.println(pOld.toString());
		// updatePlain(pOld, pNew);
		// System.out.println(pNew.toString());
		// pNew.write("test.txt");
		/*
		 * Plain test = new Plain("test.txt"); Plain test2 = new Plain(test.getWidth());
		 * System.out.println(test.getWidth()); System.out.println(test.toString());
		 * updatePlain(test, test2); updatePlain(test2, test); updatePlain(test, test2);
		 * updatePlain(test2, test); updatePlain(test, test2); updatePlain(test2, test);
		 * updatePlain(test, test2); updatePlain(test2, test);
		 * System.out.println(test.toString());
		 */

		// TODO
		//
		// Generate wildlife simulations repeatedly like shown in the
		// sample run in the project description.
		//
		// 1. Enter 1 to generate a random plain, 2 to read a plain from an input
		// file, and 3 to end the simulation. (An input file always ends with
		// the suffix .txt.)
		//
		// 2. Print out standard messages as given in the project description.
		//
		// 3. For convenience, you may define two plains even and odd as below.
		// In an even numbered cycle (starting at zero), generate the plain
		// odd from the plain even; in an odd numbered cycle, generate even
		// from odd.
		// 4. Print out initial and final plains only. No intermediate plains should
		// appear in the standard output. (When debugging your program, you can
		// print intermediate plains.)
		//
		// 5. You may save some randomly generated plains as your own test cases.
		//
		// 6. It is not necessary to handle file input & output exceptions for this
		// project. Assume data in an input file to be correctly formated.

		Plain even; // the plain after an even number of cycles
		Plain odd; // the plain after an odd number of cycles

		
		//setup system scanner
		Scanner scanner = new Scanner(System.in);

		System.out.print("Simulation of Wildlife of the Plain\nkeys: 1 (random plain) 2 (file input) 3 (exit)\n");

		//master for allows for 99 trials
		for (int trial = 1; trial < 100; trial++) {
			//keeps track of number of trials
			System.out.print("Trail " + trial +":");
			//scans user selection 1, 2, or 3
			int selection = scanner.nextInt();

			//random plain
			if (selection == 1) {
				System.out.println("Random plain");
				System.out.print("Enter grid width:");
				int width = scanner.nextInt();
				System.out.print("Enter the number of cycles:");
				int cycles = scanner.nextInt();
				//constructs plains
				even = new Plain(width);
				even.randomInit();
				odd = new Plain(width);
				//prints initial plain
				System.out.println("Initial plain:\n");
				System.out.print(even.toString());
				System.out.print("\n\n");
				//prints final palin
				System.out.println("Final plain:\n");
				
				//to avoid constructing as many plains as iterations even and odd switch off being the new plain so we always have access to the old plain to calculate census
				//even number of cycles
				if (cycles % 2 == 0) {
					for (int a = 0; a <= (cycles-1) / 2; a++) {
						updatePlain(even, odd);
						updatePlain(odd, even);
					}
					System.out.println(even.toString());
				}
				//odd number of cycles
				else {
					for (int a = 0; a <= (cycles-1) / 2; a++) {
						updatePlain(even, odd);
						updatePlain(odd, even);
					}
					//print the final pain
					System.out.println(odd.toString());
				}

			//file input
			} else if (selection == 2) {
				System.out.println("Plain input from a file");
				System.out.print("File name:");
				String file = scanner.next();
				System.out.print("Enter the number of cycles:");
				int cycles = scanner.nextInt();
				//constructs plains
				even = new Plain(file);
				odd = new Plain(even.getWidth());
				//prints initial
				System.out.println("Initial plain:\n");
				System.out.print(even.toString());
				System.out.print("\n\n");
				//prints final
				System.out.println("Final plain:\n");
				
				//same as case 1
				//even
				if (cycles % 2 == 0) {
					for (int a = 0; a <= (cycles-1) / 2; a++) {
						updatePlain(even, odd);
						updatePlain(odd, even);
					}
					System.out.println(even.toString());
				}
				// odd
				else {
					for (int a = 0; a <= (cycles-1) / 2; a++) {
						updatePlain(even, odd);
						updatePlain(odd, even);
					}
					System.out.println(odd.toString());
				}
				
			//exit the program
			} else if (selection == 3) {
				//close the scanner
				scanner.close();
				System.exit(0);
				
			//invalid user input catch
			} else {
				System.out.println("Error enter 1 for random, 2 for file, or 3 to exit.");
			}
		}

	}
}
