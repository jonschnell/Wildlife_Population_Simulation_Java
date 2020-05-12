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
 * This class is to be extended by the Badger, Fox, and Rabbit classes. 
 */
public abstract class Animal extends Living implements MyAge
{
	protected int age;   // age of the animal 

	/**
	 * 
	 * @return age of the animal 
	 */
	@Override
	public int myAge() {
		return age;
	}
}
