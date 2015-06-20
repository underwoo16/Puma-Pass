/*
 * A collection of methods that deal with file IO.
 * @author Cody Machine
*/

import java.io.*;
import java.util.Scanner;

public class FileUtil
{
   
   /**
     * Reads in all the records from the data file.
     * @param filename name of the input file
     * @return returns a string array containing the records
    */
   public static String[] readRecords(final String filename) throws Exception
   {
      File file = new File(filename);
      Scanner fin = new Scanner(file);
      
      int recordCount = fin.nextInt();
      fin.nextLine();
      String[] data = new String[recordCount * 3];
      
      for (int x = 0; x < (recordCount * 3); x++)
      {
         data[x] = fin.nextLine();
      }
      
      return data;
   }
   
   /**
     * Writes all the records to a data file.
     * @param filename name of the output file
    */
   public static void writeRecords(final String filename, final String[] data) throws Exception
   {
      File file = new File(filename);
      PrintStream fin = new PrintStream(file);
      
      int recordCount = (data.length / 3);
      fin.println(recordCount);
      
      for (int x = 0; x < (recordCount * 3); x++)
      {
         fin.println(data[x]);
      }
      
   }
   
   /**
     * Reads in all the filenames of save files.
     * @return a string array containing the filenames
    */
   public static String[] readFilenames()
   {
      // CANNOT IMPLEMENT UNTIL A NAMING SCHEME IS DECIDED
      return null;
   }
   
   /**
     * Delete a save file.
     * @param filename name of the file to be deleted
     * @return returns true if the deletion was successful
    */
   public static boolean deleteFile(final String filename)
   {
      return false;
   }
   
} // end class FileUtil