package pumapass.utillity;

import java.security.SecureRandom;

/**
* RandomPassGen
* 
* Class used to securely generate a randomized password
* TODO: modify implementation to allow user to exclude specific characters
* TODO: are all of these special characters commonly allowed in passwords? Maybe trim this list
* 
*/
public class RandomPassGen
{


	private static final String lowerCase = "abcdefghijklmnopqrstuvwxyz";
	private static final String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String numeric = "0123456789";
	private static final String special = "'~!@#$%^&*()_-+={}[]\\|:;\"'<>,.?/";
	private static final int defaultLength = 10;
	
	private int passLength;
	private String charsToUse = "";
	
	/**
	 * Constructor with parameters
	 * 
	 * @param length: length of password to generate
	 * @param lowerFlag: flag to include lower case char set
	 * @param upperFlag: flag to include upper char set
	 * @param numericFlag: flag to include numeric char set
	 * @param specialFlag: flag to include special char set
	 */
	public RandomPassGen(int length, boolean lowerFlag, boolean upperFlag, boolean numericFlag, boolean specialFlag)
	{
		this.init(length, lowerFlag, upperFlag, numericFlag, specialFlag);
	}

	/**
	 * Default constructor with no parameters
	 */
	public RandomPassGen()
	{
		this.init(defaultLength, true, true, true, true);
	}
	
	/**
	 * Initializes object, used by constructors
	 * 
	 * @param length: length of password to generate
	 * @param lowerFlag: flag to include lower case char set
	 * @param upperFlag: flag to include upper char set
	 * @param numericFlag: flag to include numeric char set
	 * @param specialFlag: flag to include special char set
	 */
	private void init(int length, boolean lowerFlag, boolean upperFlag, boolean numericFlag, boolean specialFlag)
	{
		//set length of password
		this.passLength = length;
		
		//add any character sets that are requested
		if(lowerFlag)
		{
			charsToUse += lowerCase;
		}
		if(upperFlag)
		{
			charsToUse += upperCase;
		}
		if(numericFlag)
		{
			charsToUse += numeric;
		}
		if(specialFlag)
		{
			charsToUse += special;
		}
		
		//if charsToUse does not contain any characters we are in a pickle... handle this elegantly later
		if(charsToUse.equals(""))
		{
			//TODO: Make this thrown an exception or something because the user doesn't want any characters in their password???
			charsToUse = "qwertyuiopasdfghjklzxcvbnm123456789";
		}
	}
	
	/**
	 * Generates a password using charsToUse characters and a secure random number generator
	 * 
	 * @return randomly generated password
	 */
	public String generatePassword()
	{
		//Create a secure random generator and a string builder
		SecureRandom rng = new SecureRandom();
		StringBuilder pwBuilder = new StringBuilder(passLength);
		
		//grab a random character out of our set and append it to our string
		for(int i=0; i<this.passLength; i++)
		{
			int randNum = rng.nextInt(charsToUse.length());
			pwBuilder.append(charsToUse.charAt(randNum));
		}
		
		//return the beautiful password
		return pwBuilder.toString();
		
	}

}//end class