/*


*/

public class CipherTest
{
   public static void main(String[] args) throws Exception
   {
      /*Cryptor c = new Cryptor("password");
      String temp = "This is a test. This is a test.";
      String enc = c.encrypt(temp);
      String dec = c.decrypt(enc);
      
      System.out.println("String before encryption: " + temp);
      System.out.println("String after encryption: " + enc);
      System.out.println("String after decryption: " + dec);
      
      Cryptor d = new Cryptor("wrongpass");
      String temp2 = d.decrypt(enc);
      System.out.println("String after decryption with wrong pass: " + temp2);
      d.setKey("password");
      temp2 = d.decrypt(enc);
      System.out.println("String after decryption with correct pass after setKey call: " + temp2);
      
      //Cryptor e = new Cryptor();
      */
      
      Cryptor c = new Cryptor("password");
      String[] temp = {"##########", "username", "pass", "http://www.blurp.com", "NAME", "PASSWORD", "http://facebook.com", "FBNAME", "duhpass", "http://www.website.com", "mork", "mindy", "http://mail.hotmale.cz", "Timothy", "Treadwell"};
      temp = c.encryptArray(temp);
      for (int x = 0; x < temp.length; x++)
      {
         System.out.println(temp[x]);
      }
      temp = c.decryptArray(temp);
      for (int x = 0; x < temp.length; x++)
      {
         System.out.println(temp[x]);
      }
      temp = c.encryptArray(temp);
      c.setKey("notpass");
      temp = c.decryptArray(temp);
      for (int x = 0; x < temp.length; x++)
      {
         System.out.println(temp[x]);
      }
      
   } // end main
   
} // end class CipherTest