/*


*/

public class CipherTest
{
   public static void main(String[] args) throws Exception
   {
      Cryptor c = new Cryptor("password");
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
      
   } // end main
   
} // end class CipherTest