package com.yoc.android.yocperformance.adsdk;

import android.content.Context;
import android.provider.Settings.Secure;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class d
{
  private static String a = "";
  private static String b = null;
  private static final String c = "INSTALLATION";
  private static final String d = "0123456789abcdefabcd0123456789abcdefabcd";

  public static String a()
  {
    return "0123456789abcdefabcd0123456789abcdefabcd";
  }

  public static String a(Context paramContext)
  {
    try
    {
      File localFile;
      if (b == null)
        localFile = new File(paramContext.getFilesDir(), "INSTALLATION");
      try
      {
        if (!localFile.exists())
          a(localFile, paramContext);
        b = a(localFile);
        a += "App ist installiert,\n";
        a = a + "Device ID:" + b + "\n";
        String str = b;
        return str;
      }
      catch (Exception localException)
      {
        throw new RuntimeException(localException);
      }
    }
    finally
    {
    }
  }

  private static String a(File paramFile)
    throws IOException
  {
    RandomAccessFile localRandomAccessFile = new RandomAccessFile(paramFile, "r");
    byte[] arrayOfByte = new byte[(int)localRandomAccessFile.length()];
    localRandomAccessFile.readFully(arrayOfByte);
    localRandomAccessFile.close();
    return new String(arrayOfByte);
  }

  private static void a(File paramFile, Context paramContext)
    throws IOException
  {
    a += "App wird installiert...\n";
    FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
    String str = Settings.Secure.getString(paramContext.getContentResolver(), "android_id");
    a = a + "Android ID:" + str + "\n";
    if ((str == null) || (str.equals("")) || (str.equals("9774d56d682e549c")))
      str = "no Id";
    while (true)
    {
      a = a + "Device ID:" + str + "\n";
      a += "App Hash:0123456789abcdefabcd0123456789abcdefabcd\n";
      b = str;
      localFileOutputStream.write(str.getBytes());
      localFileOutputStream.close();
      return;
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
        localMessageDigest.update(str.getBytes());
        byte[] arrayOfByte = localMessageDigest.digest();
        StringBuilder localStringBuilder = new StringBuilder(arrayOfByte.length << 1);
        for (int i = 0; ; i++)
        {
          if (i >= arrayOfByte.length)
          {
            str = localStringBuilder.toString();
            break;
          }
          localStringBuilder.append(Character.forDigit((0xF0 & arrayOfByte[i]) >> 4, 16));
          localStringBuilder.append(Character.forDigit(0xF & arrayOfByte[i], 16));
        }
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        localNoSuchAlgorithmException.printStackTrace();
      }
    }
  }

  public static String b()
  {
    return a;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.d
 * JD-Core Version:    0.6.2
 */