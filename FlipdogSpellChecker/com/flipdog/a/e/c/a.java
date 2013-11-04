package com.flipdog.a.e.c;

import com.a.a.m;
import com.flipdog.a.e.a.b;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class a
{
  public static String a()
  {
    try
    {
      MessageDigest localMessageDigest2 = MessageDigest.getInstance("SHA-1");
      localMessageDigest1 = localMessageDigest2;
      localMessageDigest1.update(b.b.getBytes(), 0, b.b.length());
      return String.format("%1$040X", new Object[] { new BigInteger(1, localMessageDigest1.digest()) }).substring(32);
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        MessageDigest localMessageDigest1 = null;
    }
  }

  public static String a(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://www.dropbox.com/").append(1).append("/connect?");
    localStringBuilder.append("k=").append(b.a);
    localStringBuilder.append("&s=").append(paramString);
    return localStringBuilder.toString();
  }

  public static String a(String paramString1, String paramString2)
  {
    StringBuilder localStringBuilder = new StringBuilder("https://www.dropbox.com/m/connect_login?cont=");
    localStringBuilder.append(paramString1);
    localStringBuilder.append("&signup_data=").append(paramString2);
    return localStringBuilder.toString();
  }

  public static String b(String paramString)
  {
    String[] arrayOfString = new String[4];
    arrayOfString[0] = "k";
    arrayOfString[1] = b.a;
    arrayOfString[2] = "s";
    arrayOfString[3] = paramString;
    return m.a("www.dropbox.com", 1, "/connect", arrayOfString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.e.c.a
 * JD-Core Version:    0.6.2
 */