/*
Cryptor class

06/18/2015
*/
package pumapass.encryption;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.util.*;

public class Cryptor
{
   private Key key;
   
   public Cryptor(final String userKey) throws Exception
   {
      this.setKey(userKey);
   } // end EVC
   
   public void setKey(final String userKey) throws Exception
   {
      // preconditions
      if (userKey == null || userKey.isEmpty())
         throw new RuntimeException("Invalid/Null userKey String passed in.");
      
      /* 
         The key must be 128/182/256 bits to work with AES.
         Here MessageDigest is used to make the users key become 128bits by
         hashing it with MD5 first.
      */ 
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] encodedKey = userKey.getBytes("UTF-8");
      byte[] hashedKey = md.digest(encodedKey);
      this.key = new SecretKeySpec(hashedKey, 0, hashedKey.length, "AES");
   } // end setKey
   
   public String encrypt(final String data) throws Exception
   {
      // preconditions
      if (data == null || data.isEmpty())
         throw new RuntimeException("Invalid/Null data String passed in.");
      
      // Create the Cipher object and set it to encrypt using the master key.
      Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
      cipher.init(Cipher.ENCRYPT_MODE, this.key);
      
      // decode the data String into a byte[]
      byte[] encodedData = data.getBytes("UTF-8");
      
      // encrypt the data using the Cipher object
      byte[] encryptedData = cipher.doFinal(encodedData);
      
      // encode encryptedData to a Base64 String so it can be easily displayed/stored in plaintext
      Base64.Encoder e = Base64.getEncoder();
      String encryptedString = e.encodeToString(encryptedData);
      
      return encryptedString;
   } // end encrypt
   
   public String decrypt(final String data) throws Exception
   {
      // preconditions
      if (data == null || data.isEmpty())
         throw new RuntimeException("Invalid/Null data String passed in.");
      
      try
      {
        // Create the Cipher object and set it to decrypt using the master key.
         Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
         cipher.init(Cipher.DECRYPT_MODE, this.key);
         
         // decode the data Base64 String into a byte[]
         Base64.Decoder d = Base64.getDecoder();
         byte[] encodedData = d.decode(data);
         
         // decrypt the data using the Cipher object
         byte[] decryptedData = cipher.doFinal(encodedData);
         
         // convert byte[] decryptedData back into a String
         String decryptedString = new String(decryptedData, "UTF-8");
         
         return decryptedString;
      }
      catch (BadPaddingException e)
      {
         // If a BadPaddingException is thrown, then an incorrect passkey was used.
         return "INCORRECT KEY";
      }
      
   } // end decrypt
   
   public String[] encryptArray(final String[] data) throws Exception
   {
      String[] result = new String[data.length];
      
      for (int x = 0; x < data.length; x++)
      {
         result[x] = encrypt(data[x]);
      }
      
      return result;
   } // end encryptArray
   
   public String[] decryptArray(final String[] data) throws Exception
   {
      String[] result = new String[data.length];
      
      for (int x = 0; x < data.length; x++)
      {
         result[x] = decrypt(data[x]);
      }
      
      return result;
   } // end decryptArray
   
} // end class Cryptor