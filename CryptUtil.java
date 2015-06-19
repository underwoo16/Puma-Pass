/*


*/

public class CryptUtil
{
   
   public static String[] encryptArray(final String[] data, final String key) throws Exception
   {
      String[] result = new String[data.length];
      Cryptor cryptor = new Cryptor(key);
      
      for (int x = 0; x < data.length; x++)
      {
         result[x] = cryptor.encrypt(data[x]);
      }
      
      return result;
   }
   
   public static String[] decryptArray(final String[] data, final String key) throws Exception
   {
      String[] result = new String[data.length];
      Cryptor cryptor = new Cryptor(key);
      
      for (int x = 0; x < data.length; x++)
      {
         result[x] = cryptor.decrypt(data[x]);
      }
      
      return result;
   }
   
} // end class