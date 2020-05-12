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

/**
 * 
 * Living refers to the life form occupying a square in a plain grid. It is a
 * superclass of Empty, Grass, and Animal, the latter of which is in turn a
 * superclass of Badger, Fox, and Rabbit. Living has two abstract methods
 * awaiting implementation.
 *
 */
public abstract class Living {
	protected Plain plain; // the plain in which the life form resides
	protected int row; // location of the square on which
	protected int column; // the life form resides

	// constants to be used as indices.
	protected static final int BADGER = 0;
	protected static final int EMPTY = 1;
	protected static final int FOX = 2;
	protected static final int GRASS = 3;
	protected static final int RABBIT = 4;

	public static final int NUM_LIFE_FORMS = 5;

	// life expectancies
	public static final int BADGER_MAX_AGE = 4;
	public static final int FOX_MAX_AGE = 6;
	public static final int RABBIT_MAX_AGE = 3;

	/**
	 * Censuses all life forms in the 3 X 3 neighborhood in a plain.
	 * 
	 * @param population counts of all life forms
	 */
	protected void census(int population[]) {
		// Count the numbers of Badgers, Empties, Foxes, Grasses, and Rabbits
		// in the 3x3 neighborhood centered at this Living object. Store the
		// counts in the array population[] at indices 0, 1, 2, 3, 4, respectively.
		
		
		//handle edge cases such as corners and edges
		int rowmin = Math.max(0, row-1); //Minimum possible value of row in this neighborhood
		int colmin = Math.max(0, column-1); //Minimum possible value of column
		
		int rowmax = Math.min(row + 1, plain.getWidth()-1);//max possible row
		int colmax = Math.min(column + 1, plain.getWidth()-1);//max possible row
		
		//create a state to determine what element to add to the population
		//iterate through the neighborhood 
		
		
		for (int i = rowmin; i <= rowmax; i++) {
			for (int j = colmin; j <= colmax; j++) {
				State who = plain.grid[i][j].who();//sets the state of who
				if (who.equals(State.BADGER)) {
					population[BADGER] += 1;
				} else if (who.equals(State.FOX)) {
					population[FOX] += 1;
				} else if (who.equals(State.RABBIT)) {
					population[RABBIT] += 1;
				} else if (who.equals(State.GRASS)) {
					population[GRASS] += 1;
				} else if (who.equals(State.EMPTY)) {
					population[EMPTY] += 1;
				}
			}
		}
	}

				/*THIS CODE IS UNFINISHED WITH MANY CATCHES AND AN O(n^2)
				// State who = plain.grid[i][j].who();
				// System.out.println(who);
				
				// top
				if (i > 0) {
					who = plain.grid[i - 1][j].who();
					if (who.equals(State.BADGER)) {
						population[BADGER] += 1;
					} else if (who.equals(State.FOX)) {
						population[FOX] += 1;
					} else if (who.equals(State.RABBIT)) {
						population[RABBIT] += 1;
					} else if (who.equals(State.GRASS)) {
						population[GRASS] += 1;
					} else if (who.equals(State.EMPTY)) {
						population[EMPTY] += 1;
					}
				}
				// left
				else if (j > 0) {
					who = plain.grid[i][j - 1].who();
					if (who.equals(State.BADGER)) {
						population[BADGER] += 1;
					} else if (who.equals(State.FOX)) {
						population[FOX] += 1;
					} else if (who.equals(State.RABBIT)) {
						population[RABBIT] += 1;
					} else if (who.equals(State.GRASS)) {
						population[GRASS] += 1;
					} else if (who.equals(State.EMPTY)) {
						population[EMPTY] += 1;
					}
				}
				// bottom
				else if (i < this.row - 1) {
					who = plain.grid[i + 1][j].who();
					if (who.equals(State.BADGER)) {
						population[BADGER] += 1;
					} else if (who.equals(State.FOX)) {
						population[FOX] += 1;
					} else if (who.equals(State.RABBIT)) {
						population[RABBIT] += 1;
					} else if (who.equals(State.GRASS)) {
						population[GRASS] += 1;
					} else if (who.equals(State.EMPTY)) {
						population[EMPTY] += 1;
					}
				}
				// right
				else if (j < this.column - 1) {
					who = plain.grid[i][j + 1].who();
					if (who.equals(State.BADGER)) {
						population[BADGER] += 1;
					} else if (who.equals(State.FOX)) {
						population[FOX] += 1;
					} else if (who.equals(State.RABBIT)) {
						population[RABBIT] += 1;
					} else if (who.equals(State.GRASS)) {
						population[GRASS] += 1;
					} else if (who.equals(State.EMPTY)) {
						population[EMPTY] += 1;
					}
				} else if (j == 0 && i == 0) {
					who = plain.grid[i][j + 1].who();
					if (who.equals(State.BADGER)) {
						population[BADGER] += 1;
					} else if (who.equals(State.FOX)) {
						population[FOX] += 1;
					} else if (who.equals(State.RABBIT)) {
						population[RABBIT] += 1;
					} else if (who.equals(State.GRASS)) {
						population[GRASS] += 1;
					} else if (who.equals(State.EMPTY)) {
						population[EMPTY] += 1;
					}
				}
			}

		}

	}


	//THIS CODE IS UNTESTED BUT SHOULD WORK WITH 0(n^4)
	// first row
	 * if (i == 0) { // first row first column if (j == 0) { for (int x = i; x <= i
	 * + 1; x++) { for (int y = j; y <= j + 1; j++) { who = plain.grid[x][y].who();
	 * if (who.equals(State.BADGER)) { population[BADGER] += 1; } else if
	 * (who.equals(State.FOX)) { population[FOX] += 1; } else if
	 * (who.equals(State.RABBIT)) { population[RABBIT] += 1; } else if
	 * (who.equals(State.GRASS)) { population[GRASS] += 1; } else if
	 * (who.equals(State.EMPTY)) { population[EMPTY] += 1; } } }
	 * 
	 * } // first row bottom column else if (j == this.column) { for (int x = i - 1;
	 * x <= i; x++) { for (int y = j - 1; y <= j; j++) { who =
	 * plain.grid[x][y].who(); if (who.equals(State.BADGER)) { population[BADGER] +=
	 * 1; } else if (who.equals(State.FOX)) { population[FOX] += 1; } else if
	 * (who.equals(State.RABBIT)) { population[RABBIT] += 1; } else if
	 * (who.equals(State.GRASS)) { population[GRASS] += 1; } else if
	 * (who.equals(State.EMPTY)) { population[EMPTY] += 1; } } } } // first row else
	 * { for (int x = i - 1; x <= i + 1; x++) { for (int y = j; y <= j + 1; j++) {
	 * who = plain.grid[x][y].who(); if (who.equals(State.BADGER)) {
	 * population[BADGER] += 1; } else if (who.equals(State.FOX)) { population[FOX]
	 * += 1; } else if (who.equals(State.RABBIT)) { population[RABBIT] += 1; } else
	 * if (who.equals(State.GRASS)) { population[GRASS] += 1; } else if
	 * (who.equals(State.EMPTY)) { population[EMPTY] += 1; } } }
	 * 
	 * } } // last row if (i == row) { // last row first column if (j == 0) { for
	 * (int x = i - 1; x <= i; x++) { for (int y = j; y <= j + 1; j++) { who =
	 * plain.grid[x][y].who(); if (who.equals(State.BADGER)) { population[BADGER] +=
	 * 1; } else if (who.equals(State.FOX)) { population[FOX] += 1; } else if
	 * (who.equals(State.RABBIT)) { population[RABBIT] += 1; } else if
	 * (who.equals(State.GRASS)) { population[GRASS] += 1; } else if
	 * (who.equals(State.EMPTY)) { population[EMPTY] += 1; } } }
	 * 
	 * } // last row last column else if (j == this.column) { for (int x = i - 1; x
	 * <= i; x++) { for (int y = j - 1; y <= j; j++) { who = plain.grid[x][y].who();
	 * if (who.equals(State.BADGER)) { population[BADGER] += 1; } else if
	 * (who.equals(State.FOX)) { population[FOX] += 1; } else if
	 * (who.equals(State.RABBIT)) { population[RABBIT] += 1; } else if
	 * (who.equals(State.GRASS)) { population[GRASS] += 1; } else if
	 * (who.equals(State.EMPTY)) { population[EMPTY] += 1; } } }
	 * 
	 * } // last row else { for (int x = i - 1; x <= i + 1; x++) { for (int y = j -
	 * 1; y <= j; j++) { who = plain.grid[x][y].who(); if (who.equals(State.BADGER))
	 * { population[BADGER] += 1; } else if (who.equals(State.FOX)) {
	 * population[FOX] += 1; } else if (who.equals(State.RABBIT)) {
	 * population[RABBIT] += 1; } else if (who.equals(State.GRASS)) {
	 * population[GRASS] += 1; } else if (who.equals(State.EMPTY)) {
	 * population[EMPTY] += 1; } } }
	 * 
	 * } } // top row ignoring corners if (j == 0 && i > 0 && i < row) { for (int x
	 * = i; x <= i + 1; x++) { for (int y = j - 1; y <= j + 1; j++) { who =
	 * plain.grid[x][y].who(); if (who.equals(State.BADGER)) { population[BADGER] +=
	 * 1; } else if (who.equals(State.FOX)) { population[FOX] += 1; } else if
	 * (who.equals(State.RABBIT)) { population[RABBIT] += 1; } else if
	 * (who.equals(State.GRASS)) { population[GRASS] += 1; } else if
	 * (who.equals(State.EMPTY)) { population[EMPTY] += 1; } } }
	 * 
	 * } // bottom row ignoring corners if (j == this.column && i > 0 && i < row) {
	 * for (int x = i - 1; x <= i + 1; x++) { for (int y = j - 1; y <= j; j++) { who
	 * = plain.grid[x][y].who(); if (who.equals(State.BADGER)) { population[BADGER]
	 * += 1; } else if (who.equals(State.FOX)) { population[FOX] += 1; } else if
	 * (who.equals(State.RABBIT)) { population[RABBIT] += 1; } else if
	 * (who.equals(State.GRASS)) { population[GRASS] += 1; } else if
	 * (who.equals(State.EMPTY)) { population[EMPTY] += 1; } } }
	 * 
	 * } }
	 */

	/**
	 * Gets the identity of the life form on the square.
	 * 
	 * @return State
	 */
	public abstract State who();
	// To be implemented in each class of Badger, Empty, Fox, Grass, and Rabbit.
	//
	// There are five states given in State.java. Include the prefix State in
	// the return value, e.g., return State.Fox instead of Fox.

	/**
	 * Determines the life form on the square in the next cycle.
	 * 
	 * @param pNew plain of the next cycle
	 * @return Living
	 */
	public abstract Living next(Plain pNew);
	// call census
	// census(this.population);

	// To be implemented in the classes Badger, Empty, Fox, Grass, and Rabbit.
	//
	// For each class (life form), carry out the following:
	//
	// 1. Obtains counts of life forms in the 3x3 neighborhood of the class object.

	// 2. Applies the survival rules for the life form to determine the life form
	// (on the same square) in the next cycle. These rules are given in the
	// project description.
	//
	// 3. Generate this new life form at the same location in the plain pNew.

}
