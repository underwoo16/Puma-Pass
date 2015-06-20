/*


*/

public class FileTest
{
   public static void main(String[] args) throws Exception
   {
      
      String[] data = FileUtil.readRecords("input.txt");
      
      FileUtil.writeRecords("output.txt", data);
      
   } // end main
   
} // end class FileTest