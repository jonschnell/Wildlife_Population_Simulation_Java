package edu.iastate.cs228.hw1;

/**
 *  
 * @author Jonathon Schnell
 * @version 1.0
 * @since 2-14-2020
 * COM S 228
 * homework1
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Random;

/**
 * 
 * The plain is represented as a square grid of size width x width.
 *
 */
public class Plain {
	private int width; // grid size: width X width

	public Living[][] grid;

	/**
	 * Default constructor reads from a file
	 */
	public Plain(String inputFileName) throws FileNotFoundException {
		//setup file reader
		File f = new File(inputFileName);
		
		// Assumption: The input file is in correct format.
		//
		// You may create the grid plain in the following steps:
		//
		// 1) Reads the first line to determine the width of the grid.
		Scanner sc = new Scanner(f);
		String firstLine = sc.nextLine();
		
		//used to determine width by scanning the first line string
		Scanner sc2 = new Scanner(firstLine);
		//used to read the whole grid
		Scanner sc3 = new Scanner(f);
		
		int width = 0;
		
		//used to determine width by scanning the first line string
		while (sc2.hasNext()) {
			width += 1;
			sc2.next();
		}
		this.width = width;
		//System.out.println(width);
		
		// 2) Creates a grid object.
		grid = new Living[width][width];

		// 3) Fills in the grid according to the input file.
		for (int i = 0; i < width; i++) {
			//j is columns so it must be more frequent than i rows
			for (int j = 0; j < width; j++) {
				String Animal = sc3.next();
				//constructs a lifeform for each element of the file
				if (Animal.contains("B")) {
					grid[i][j] = new Badger(this, i, j, (Animal.charAt(1) - '0'));
				}
				if (Animal.contains("F")) {
					grid[i][j] = new Fox(this, i, j, (Animal.charAt(1) - '0'));
				}
				if (Animal.contains("R")) {
					grid[i][j] = new Rabbit(this, i, j, (Animal.charAt(1) - '0'));
				}
				if (Animal.contains("G")) {
					grid[i][j] = new Grass(this, i, j);
				}
				if (Animal.contains("E")) {
					grid[i][j] = new Empty(this, i, j);
				}
			}
		}
		// Be sure to close the input file when you are done.
		sc.close();
		sc2.close();
		sc3.close();

	}

	/**
	 * Constructor that builds a w x w grid without initializing it.
	 * 
	 * @param width the grid
	 */
	public Plain(int w) {
		//construct a grid
		width = w;
		grid = new Living[w][w];

	}
	/**
	 * 
	 * @return width (w) of Living Grid
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Initialize the plain by randomly assigning to every square of the grid one of
	 * BADGER, FOX, RABBIT, GRASS, or EMPTY.
	 * 
	 * Every animal starts at age 0.
	 */
	public void randomInit() {
		//setup random number generator
		Random generator = new Random();
		//System.out.println(width);
		
		//loops to populate the grid
		for (int i = 0; i < width; i++) {
			//j is columns so it must be more frequent than i rows
			for (int j = 0; j < width; j++) {
				//generates a new random number for each element
				int random = generator.nextInt(5);
				//constructs an lifeform for each element in the grid
				if (random == 0) {
					this.grid[i][j] = new Badger(this, i, j, 0);
				} else if (random == 1) {
					this.grid[i][j] = new Fox(this, i, j, 0);
				} else if (random == 2) {
					this.grid[i][j] = new Rabbit(this, i, j, 0);
				} else if (random == 3) {
					this.grid[i][j] = new Grass(this, i, j);
				} else if (random == 4) {
					this.grid[i][j] = new Empty(this, i, j);
				}

			}
		}
	}

	/**
	 * Output the plain grid. For each square, output the first letter of the living
	 * form occupying the square. If the living form is an animal, then output the
	 * age of the animal followed by a blank space; otherwise, output two blanks.
	 */
	public String toString() {
		
		String line = "";
		State who;
		//loops to construct to a string based on what animal in in what element
		for (int i = 0; i < width; i++) {
			//j is columns so it must be more frequent than i rows
			for (int j = 0; j < width; j++) {
				
				//assign state who to the current element
				who = grid[i][j].who();
				
				//format the lifeform and and age to a string line
				if (who.equals(State.BADGER)) {
					Animal a = (Animal)grid[i][j];
					line = line + "B" + a.age + " ";
				}
				else if (who.equals(State.FOX)) {
					Animal a = (Animal)grid[i][j];
					line = line + "F" + a.age + " ";
				}
				else if (who.equals(State.RABBIT)) {
					Animal a = (Animal)grid[i][j];
					line = line + "R" + a.age + " ";
				}
				else if (who.equals(State.GRASS)) {
					line = line + "G  ";
				}
				else if (who.equals(State.EMPTY)) {
					line = line + "E  ";
				}
			}
			//insert a line break before jumping to the next line row
			line = line + "\n";
		}
		//String a = "test\ntest";
		return line;
	}

	/**
	 * Write the plain grid to an output file. Also useful for saving a randomly
	 * generated plain for debugging purpose.
	 * 
	 * @throws FileNotFoundException
	 */
	public void write(String outputFileName) throws FileNotFoundException {
		// 1. Open the file.
		File f = new File(outputFileName);
		// 2. Write to the file. The five life forms are represented by characters
		// B, E, F, G, R. Leave one blank space in between. Examples are given in
		// the project description.
		//converts grid of lifeforms to a string
		String toPrint = this.toString();
		FileOutputStream outputStream = new FileOutputStream(f);
		//FileWriter fileWriter = new FileWriter(f);
	    PrintWriter printWriter = new PrintWriter(outputStream);
	    printWriter.print(toPrint);
		// 3. Close the file.
	    printWriter.close();

	}
}
