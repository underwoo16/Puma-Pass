/*


*/

public class CryptUtil
{
   
   public static String[] encryptArray(final String[] data, final String key)
   {
      String[] result = new String[data.length];
      Cryptor cryptor = new Cryptor(key);
      
      for (int x = 0; x < data.length; x++)
      {
         result[x] = cryptor.encrypt(data[x]);
      }
   }
   
   public static String[] decryptArray(final String[] data, final String key)
   {
      String[] result = new String[data.length];
      Cryptor cryptor = new Cryptor(key);
      
      for (int x = 0; x < data.length; x++)
      {
         result[x] = cryptor.decrypt(data[x]);
      }
   }
   
} // end class