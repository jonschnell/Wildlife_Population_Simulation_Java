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
 * A badger eats a rabbit and competes against a fox. 
 */
public class Badger extends Animal
{
	/**
	 * Constructor 
	 * @param p: plain
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	
	
	public Badger (Plain p, int r, int c, int a) 
	{
		this.plain = p;
		this.row = r;
		this.column = c;
		this.age = a;
		
	}
	
	/**
	 * A badger occupies the square. 	 
	 */
	public State who()
	{
		return State.BADGER; 
	}
	
	/**
	 * A badger dies of old age or hunger, or from isolation and attack by a group of foxes. 
	 * @param pNew     plain of the next cycle
	 * @return Living  life form occupying the square in the next cycle. 
	 */
	public Living next(Plain pNew)
	{
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a badger.
		
		//ensures a new population will all elements set to 0
		int [] population = new int [5];
		//takes census of animals around this
		this.census(population);
		//System.out.println(population);
		
		//Empty if theBadgeris currently at age 4;
		if (this.age == 4) {
			return new Empty(pNew, row, column);
		}
		
		//Fox, if there is only oneBadgerbut more than oneFoxin the neighborhood;
		else if (population[BADGER] == 1 && population[FOX] > 1 ) {
			return new Fox(pNew, row, column, 0);
		}
		
		//Empty, ifBadgers andFoxes together outnumberRabbits in the neighborhood;
		else if ((population[BADGER] + population[FOX]) > population[RABBIT]) {
			return new Empty(pNew, row, column);
		}
		
		//Badger(the badger will live on).
		else {
			return new Badger(pNew, row, column, age + 1);
		}
		
	}
}
