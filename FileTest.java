/*


*/

public class FileTest
{
   public static void main(String[] args) throws Exception
   {
      
      String[] data = FileUtil.readRecords("input.txt");
      Cryptor c = new Cryptor("password");
      
      FileUtil.writeRecords("output.txt", c.encryptArray(data));
      FileUtil.writeRecords("output2.txt", c.decryptArray(c.encryptArray(data)));
      //System.out.println(FileUtil.deleteFile("test"));
   } // end main
   
} // end class FileTest