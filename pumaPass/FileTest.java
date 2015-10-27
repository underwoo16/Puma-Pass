/*


*/

package pumaPass;

//import java.io.*;
import pumaPass.pumaUtils.FileUtil;

public class FileTest
{
   public static void main(String[] args) throws Exception
   {
      
      String[] data = FileUtil.readRecords("profile1.dat");
      for (int x = 0; x < data.length; x++)
      {
         System.out.println(data[x]);
      }
      FileUtil.writeRecords("profile2.dat", data);
      FileUtil.deleteFile("profile2.dat");
      
      /*Cryptor c = new Cryptor("password");
      
      FileUtil.writeRecords("output.txt", c.encryptArray(data));
      FileUtil.writeRecords("output2.txt", c.decryptArray(c.encryptArray(data)));
      //System.out.println(FileUtil.deleteFile("test"));
      File file = new File("output.txt");
      System.out.println(file.list());*/
      
      /*File file = new File(".");
      File[] list = file.listFiles();
      for (int x = 0; x < list.length; x++)
      {
         if (list[x].isFile())
         {
            System.out.println("FILE: " + list[x].getName());
         }
         else if (list[x].isDirectory())
         {
            System.out.println("DIR: " + list[x].getName());
         }
         
      }*/
      
   } // end main
   
} // end class FileTest