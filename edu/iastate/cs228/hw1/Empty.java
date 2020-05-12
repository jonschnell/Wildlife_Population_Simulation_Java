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
 * Empty squares are competed by various forms of life.
 */
public class Empty extends Living 
{
	public Empty (Plain p, int r, int c) 
	{
		this.plain = p;
		this.row = r;
		this.column = c;
	}
	
	public State who()
	{
		// TODO 
		return State.EMPTY; 
	}
	
	/**
	 * An empty square will be occupied by a neighboring Badger, Fox, Rabbit, or Grass, or remain empty. 
	 * @param pNew     plain of the next life cycle.
	 * @return Living  life form in the next cycle.   
	 */
	public Living next(Plain pNew)
	{
		// See Living.java for an outline of the function. 
		// See the project description for corresponding survival rules.
		//ensures a new population will all elements set to 0
		int [] population = new int [5];
		
		//takes census of animals around this
		this.census(population);
		//System.out.println(population);
		
		//Rabbit, if more than one neighboringRabbit;
		if (population[RABBIT] > 1) {
			return new Rabbit(pNew, row, column, 0);
		}
		
		//Fox, if more than one neighboringFox;
		else if (population[FOX] > 1) {
			return new Fox(pNew, row, column, 0);
		}
		
		//Badger, if more than one neighboringBadger;
		else if (population[BADGER] > 1) {
			return new Badger(pNew, row, column, 0);
		}
		
		//Grass, if at least one neighboringGrass;
		else if (population[GRASS] >= 1) {
			return new Grass(pNew, row, column);
		}
		
		//remain empty
		else {
			return new Empty(pNew, row, column);
		}
	}
}
