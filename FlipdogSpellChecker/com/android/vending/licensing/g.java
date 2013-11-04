package com.android.vending.licensing;

import com.android.vending.licensing.a.a;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class g
  implements d
{
  private static final String a = "UTF-8";
  private static final String b = "PBEWITHSHAAND256BITAES-CBC-BC";
  private static final String c = "AES/CBC/PKCS5Padding";
  private static final byte[] d = arrayOfByte;
  private static final String e = "com.android.vending.licensing.AESObfuscator-1|";
  private Cipher f;
  private Cipher g;

  static
  {
    byte[] arrayOfByte = new byte[16];
    arrayOfByte[0] = 16;
    arrayOfByte[1] = 74;
    arrayOfByte[2] = 71;
    arrayOfByte[3] = -80;
    arrayOfByte[4] = 32;
    arrayOfByte[5] = 101;
    arrayOfByte[6] = -47;
    arrayOfByte[7] = 72;
    arrayOfByte[8] = 117;
    arrayOfByte[9] = -14;
    arrayOfByte[11] = -29;
    arrayOfByte[12] = 70;
    arrayOfByte[13] = 65;
    arrayOfByte[14] = -12;
    arrayOfByte[15] = 74;
  }

  public g(byte[] paramArrayOfByte, String paramString1, String paramString2)
  {
    try
    {
      SecretKeySpec localSecretKeySpec = new SecretKeySpec(SecretKeyFactory.getInstance("PBEWITHSHAAND256BITAES-CBC-BC").generateSecret(new PBEKeySpec((paramString1 + paramString2).toCharArray(), paramArrayOfByte, 1024, 256)).getEncoded(), "AES");
      this.f = Cipher.getInstance("AES/CBC/PKCS5Padding");
      this.f.init(1, localSecretKeySpec, new IvParameterSpec(d));
      this.g = Cipher.getInstance("AES/CBC/PKCS5Padding");
      this.g.init(2, localSecretKeySpec, new IvParameterSpec(d));
      return;
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      throw new RuntimeException("Invalid environment", localGeneralSecurityException);
    }
  }

  public String a(String paramString)
  {
    if (paramString == null)
      return null;
    try
    {
      String str = a.a(this.f.doFinal(("com.android.vending.licensing.AESObfuscator-1|" + paramString).getBytes("UTF-8")));
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException("Invalid environment", localUnsupportedEncodingException);
    }
    catch (GeneralSecurityException localGeneralSecurityException)
    {
      throw new RuntimeException("Invalid environment", localGeneralSecurityException);
    }
  }

  public String b(String paramString)
    throws b
  {
    if (paramString == null)
      return null;
    try
    {
      str1 = new String(this.g.doFinal(a.a(paramString)), "UTF-8");
      if (str1.indexOf("com.android.vending.licensing.AESObfuscator-1|") != 0)
        throw new b("Header not found (invalid data or key):" + paramString);
    }
    catch (com.android.vending.licensing.a.b localb)
    {
      String str1;
      throw new b(localb.getMessage() + ":" + paramString);
      String str2 = str1.substring("com.android.vending.licensing.AESObfuscator-1|".length(), str1.length());
      return str2;
    }
    catch (IllegalBlockSizeException localIllegalBlockSizeException)
    {
      throw new b(localIllegalBlockSizeException.getMessage() + ":" + paramString);
    }
    catch (BadPaddingException localBadPaddingException)
    {
      throw new b(localBadPaddingException.getMessage() + ":" + paramString);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new RuntimeException("Invalid environment", localUnsupportedEncodingException);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.vending.licensing.g
 * JD-Core Version:    0.6.2
 */