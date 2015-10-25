package pumaPass;

import java.io.IOException;
import pumaPass.pumaUtils.DicewarePassGen;


/**
 * 
 */

/**
 * @author Chandler Underwood
 *
 */
public class DicewareTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try 
		{
			// Some sanity checks, and fun random passwords
			DicewarePassGen test = new DicewarePassGen();
			System.out.println("Size of wordlist: "+test.wordList.size());
			
			System.out.println("Default password gen: "+test.generatePassword());
			
			DicewarePassGen test2 = new DicewarePassGen('.', 3);
			System.out.println("3 words, period sep: "+test2.generatePassword());
			
			// this ensures our word list is somewhat correct (at least one word)
			System.out.println("Should be xylem: "+ test.wordList.get(64623));
			
			//timing the generation of 10,000 passwords to make sure we aren't getting hung in a loop anywhere
			long startTime = System.currentTimeMillis();
			long endTime = 0;
			for(int i=0; i < 10000; i++)
			{
				test.generatePassword();	
			}
			endTime = System.currentTimeMillis();
			long timeneeded = ((endTime-startTime));
			System.out.println("DONE. Took: "+timeneeded+" milliseconds to generate 10,000 passwords");
			
		}
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}

	}

}
