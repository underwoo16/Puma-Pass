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
	private static final int WORD_LIST_LENGTH = 7752;
	private static final int DEFAULT_NUM_WORDS = 6;
	
	protected Hashtable<Integer, String> wordList = null;
	private char separatingChar = ' ';
	private int numWords;
	
	public DicewarePassGen() throws IOException
	{
		this.numWords = DEFAULT_NUM_WORDS;
		this.initialize();
	}
	
	public DicewarePassGen(char sepChar, int num) throws IOException
	{
		this.numWords = num;
		this.separatingChar = sepChar;
		this.initialize();
	}

	private void initialize() throws IOException 
	{
		this.wordList = new Hashtable<Integer, String>(WORD_LIST_LENGTH);
		
		FileInputStream fstream = new FileInputStream(new File("Puma-Pass/Resources/wordlist.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		
		String strLine;
		while((strLine = br.readLine()) != null)
		{
			String[] keyValPair = strLine.split("\t");
			this.wordList.put(Integer.parseInt(keyValPair[0]), keyValPair[1]);
		}
		
		br.close();
		fstream.close();
	}
	
	public String generatePassword()
	{
		SecureRandom rng = new SecureRandom();
		StringBuilder pwBuilder = new StringBuilder();
		
		for(int i=0; i<this.numWords; i++)
		{
			int idx = 0;
			for(int j=0; j<5; j++)
			{
				idx += (rng.nextInt(6)+1)*Math.pow(10, j);	
			}
			
			pwBuilder.append(this.wordList.get(idx));
			
			if(i<numWords-1)
			{
				pwBuilder.append(this.separatingChar);
			}
		}
		
		return pwBuilder.toString();
		
	}
	
	

}
