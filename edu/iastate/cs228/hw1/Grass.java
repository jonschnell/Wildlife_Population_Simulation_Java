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
 * Grass remains if more than rabbits in the neighborhood; otherwise, it is eaten. 
 *
 */
public class Grass extends Living 
{
	public Grass (Plain p, int r, int c) 
	{
		this.plain = p;
		this.row = r;
		this.column = c;
	}
	
	public State who()
	{
		// TODO  
		return State.GRASS; 
	}
	
	/**
	 * Grass can be eaten out by too many rabbits. Rabbits may also multiply fast enough to take over Grass.
	 */
	public Living next(Plain pNew)
	{
		// See Living.java for an outline of the function. 
		// See the project description for the survival rules for grass. 
		//ensures a new population will all elements set to 0
		int [] population = new int [5];
		//takes census of animals around this
		this.census(population);
		//System.out.println(population);
		
		//Empty if at least three times as manyRabbits asGrasses in the neighborhood;
		if (population[RABBIT] >= population[GRASS] * 3) {
			return new Empty(pNew, row, column);
		}
		
		//Rabbit if there are at least threeRabbits in the neighborhood;
		else if (population[RABBIT] >= 3) {
			return new Rabbit(pNew, row, column, 0);
		}
		
		//grass lives on
		else{
			return new Grass(pNew, row, column);
		}
	}
}
