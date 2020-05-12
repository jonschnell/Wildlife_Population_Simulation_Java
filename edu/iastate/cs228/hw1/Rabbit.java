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

/*
 * A rabbit eats grass and lives no more than three years.
 */
public class Rabbit extends Animal 
{	
	/**
	 * Creates a Rabbit object.
	 * @param p: plain  
	 * @param r: row position 
	 * @param c: column position
	 * @param a: age 
	 */
	public Rabbit (Plain p, int r, int c, int a) 
	{
		this.plain = p;
		this.row = r;
		this.column = c;
		this.age = a;
	}
		
	// Rabbit occupies the square.
	public State who()
	{
		// TODO  
		return State.RABBIT; 
	}
	
	/**
	 * A rabbit dies of old age or hunger. It may also be eaten by a badger or a fox.  
	 * @param pNew     plain of the next cycle 
	 * @return Living  new life form occupying the same square
	 */
	public Living next(Plain pNew)
	{
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for a rabbit.
		//ensures a new population will all elements set to 0
		int [] population = new int [5];
		//takes census of animals around this
		this.census(population);
		//System.out.println(population);
		
		//Empty if theRabbit’s current age is 3;
		if (this.age == 3) {
			return new Empty(pNew, row, column);
		}
		
		//Empty if there is noGrassin the neighborhood (the rabbit needs food);
		else if (population[GRASS] == 0) {
			return new Empty(pNew, row, column);
		}
		
		//Fox if in the neighborhood there are at least as manyFoxes andBadgers combinedasRabbits, and furthermore, if there are moreFoxes thanBadgers;
		else if (population[BADGER] + population[FOX] >= population[RABBIT] && population[BADGER] < population[FOX]) {
			return new Fox(pNew, row, column, 0);
		}
		
		//Badger if there are moreBadgers thanRabbits in the neighborhood;
		else if (population[BADGER] > population[RABBIT]) {
			return new Badger(pNew, row, column, 0);
		}
		
		//Rabbit(the rabbit will live on).
		else {
			return new Rabbit(pNew, row, column, age + 1);
		}
	}
}
