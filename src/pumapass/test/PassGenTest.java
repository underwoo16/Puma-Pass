/**
 * 
 */

/**
 * Tests the RandomPassGen class
 * 
 * @author Chandler Underwood
 *
 */
package pumapass.test;

import pumapass.utillity.RandomPassGen;


public class PassGenTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		RandomPassGen defaultPassGen = new RandomPassGen();
		System.out.println("Password generated with default values: "+ defaultPassGen.generatePassword());
		
		RandomPassGen noSpecialPassGen = new RandomPassGen(15, true, true, true, false);
		System.out.println("Password generated with length 15 and no special chars: "+ noSpecialPassGen.generatePassword());
		
		RandomPassGen onlyUpper = new RandomPassGen(15, false, true, false, false);
		System.out.println("Password generated with length 15 and only uppercase chars: "+ onlyUpper.generatePassword());
		
		RandomPassGen onlySpecial = new RandomPassGen(15, false, false, false, true);
		System.out.println("Password generated with length 15 and only special chars: "+onlySpecial.generatePassword());
	}

}
