import java.io.IOException;

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
			DicewarePassGen test = new DicewarePassGen();
			System.out.println("Size of wordlist: "+test.wordList.size());
			
			System.out.println("Default password gen: "+test.generatePassword());
			
			DicewarePassGen test2 = new DicewarePassGen('.', 3);
			System.out.println("Size of wordlist: "+test2.wordList.size());
			
			System.out.println("3 words, period sep: "+test2.generatePassword());
			
			System.out.println("Should be xylem: "+ test.wordList.get(64623));
			
		}
		catch (IOException e) 
		{
			
			e.printStackTrace();
		}

	}

}
