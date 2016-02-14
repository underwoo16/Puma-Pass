package pumapass.utillity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;
import java.util.Hashtable;

/**
 * 
 */

/**
 * @author Chandler Underwood
 *
 * TODO add comments, code review to make sure this does what I want it too
 */
public class DicewarePassGen 
{
	//Final variables
	private static final int WORD_LIST_LENGTH = 7752;
	private static final int MAX_INDEX_LENGTH = 66626;
	private static final int DEFAULT_NUM_WORDS = 6;
	
	//Instance variables
	public Hashtable<Integer, String> wordList = null;
	private char separatingChar = ' ';
	private int numWords;

	/**
	 * Default constructor
	 * 
	 * Initializes instance of password generator including reading wordlist into Hashtable
	 */
	public DicewarePassGen() throws IOException
	{
		// set instance variables and initialize word list
		this.numWords = DEFAULT_NUM_WORDS;
		this.initialize();
	}

	/**
	 * Overloaded constructor
	 * 
	 * @throws IOException
	 * @param sepChar: the separating character in between words
	 * @param num: the number of words to include in password
	 */
	public DicewarePassGen(char sepChar, int num) throws IOException
	{
		//We don't want a zero (or less) length password
		if(num < 1) num = DEFAULT_NUM_WORDS;
		
		// set instance variables and initialize word list
		this.numWords = num;
		this.separatingChar = sepChar;
		this.initialize();
	}
	
	/**
	 * Initialization of password generator reads words and indexes into a hashtable for use later
	 * 
	 * @throws IOException
	 */
	private void initialize() throws IOException 
	{
		// creates a new hashtable with integer key and word as value
		this.wordList = new Hashtable<Integer, String>(WORD_LIST_LENGTH);

		//gets a handle on the word list
		FileInputStream fstream = new FileInputStream(new File("res/wordlist.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		
		//reads the lines in, splits them, and puts them in word list hashtable
		String strLine;
		while((strLine = br.readLine()) != null)
		{
			String[] keyValPair = strLine.split("\t");
			this.wordList.put(Integer.parseInt(keyValPair[0]), keyValPair[1]);
		}
		
		//tidy up
		br.close();
		fstream.close();
	}
	
	/**
	 * generatePassword
	 * 
	 * Creates a diceware password based on the number of words and separating chars defined in ctor
	 * 
	 * @return securely generated diceware password
	 */
	public String generatePassword()
	{
		// secure random number generator and string builder to generate password
		SecureRandom rng = new SecureRandom();
		StringBuilder pwBuilder = new StringBuilder();
		
		// iterate through, grabbing the correct number of words
		for(int i=0; i<this.numWords; i++)
		{
			// this index will be randomly generated, and is used to get a word from word list
			int idx = 0;
			
			// each index is comprised of 5 digits
			for(int j=0; j<5; j++)
			{
				// each digit must be in range 1..6 and get appended in front of the last digit
				idx += (rng.nextInt(6)+1)*Math.pow(10, j);	
			}

			// our word list index does not go up to 66666
			if(idx > MAX_INDEX_LENGTH)
			{
				// adjust index by a random amount between 40 (min required to be valid)
				// and 55515 (max required to be valid)
				idx -= (rng.nextInt(55515)+40);
			}
			
			// add the word
			pwBuilder.append(this.wordList.get(idx));
			
			// as long as we aren't on last word, we also need a separating char
			if(i<numWords-1)
			{
				pwBuilder.append(this.separatingChar);
			}
		}
		
		// return our perfect password
		return pwBuilder.toString();

	}



}
