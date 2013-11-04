package com.flipdog.commons.a;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.Channels;

public class n
{
  public static String a(File paramFile)
    throws IOException
  {
    FileInputStream localFileInputStream = new FileInputStream(paramFile);
    try
    {
      byte[] arrayOfByte = new byte[(int)paramFile.length()];
      localFileInputStream.read(arrayOfByte);
      String str = new String(arrayOfByte, "utf-8");
      return str;
    }
    finally
    {
      localFileInputStream.close();
    }
  }

  public static String a(InputStream paramInputStream, String paramString)
    throws IOException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    byte[] arrayOfByte = new byte[4096];
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i == -1)
        return localStringBuffer.toString();
      localStringBuffer.append(new String(arrayOfByte, 0, i, paramString));
    }
  }

  public static void a(File paramFile1, File paramFile2)
    throws IOException
  {
    FileInputStream localFileInputStream = new FileInputStream(paramFile1);
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(paramFile2);
      try
      {
        a(localFileInputStream, localFileOutputStream);
        return;
      }
      finally
      {
      }
    }
    finally
    {
      localFileInputStream.close();
    }
  }

  public static void a(InputStream paramInputStream, File paramFile)
    throws IOException
  {
    FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
    try
    {
      a(paramInputStream, localFileOutputStream);
      return;
    }
    finally
    {
      localFileOutputStream.close();
    }
  }

  public static void a(InputStream paramInputStream, File paramFile, com.flipdog.commons.q.b paramb, com.flipdog.commons.l.b paramb1)
    throws IOException, com.flipdog.commons.u.a
  {
    FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
    try
    {
      a(paramInputStream, localFileOutputStream, paramb, paramb1);
      return;
    }
    finally
    {
      localFileOutputStream.close();
    }
  }

  public static void a(InputStream paramInputStream, OutputStream paramOutputStream)
    throws IOException
  {
    com.flipdog.commons.q.a.a(Channels.newChannel(paramInputStream), Channels.newChannel(paramOutputStream));
  }

  public static void a(InputStream paramInputStream, OutputStream paramOutputStream, com.flipdog.commons.q.b paramb, com.flipdog.commons.l.b paramb1)
    throws IOException, com.flipdog.commons.u.a
  {
    com.flipdog.commons.q.a.a(Channels.newChannel(paramInputStream), Channels.newChannel(paramOutputStream), paramb, paramb1);
  }

  public static void a(String paramString, File paramFile)
    throws IOException
  {
    FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
    try
    {
      localFileOutputStream.write(paramString.getBytes("utf-8"));
      return;
    }
    finally
    {
      localFileOutputStream.close();
    }
  }

  public static void a(String paramString, OutputStream paramOutputStream)
    throws IOException
  {
    OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(paramOutputStream);
    localOutputStreamWriter.write(paramString);
    localOutputStreamWriter.flush();
  }

  public static void a(byte[] paramArrayOfByte, String paramString)
    throws IOException
  {
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramArrayOfByte);
    try
    {
      b(localByteArrayInputStream, paramString);
      return;
    }
    finally
    {
      localByteArrayInputStream.close();
    }
  }

  public static byte[] a(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    paramInputStream.read(arrayOfByte);
    return arrayOfByte;
  }

  public static byte[] a(String paramString)
    throws IOException
  {
    File localFile = new File(paramString);
    FileInputStream localFileInputStream = new FileInputStream(localFile);
    try
    {
      byte[] arrayOfByte = new byte[(int)localFile.length()];
      localFileInputStream.read(arrayOfByte);
      return arrayOfByte;
    }
    finally
    {
      localFileInputStream.close();
    }
  }

  public static void b(InputStream paramInputStream, File paramFile, com.flipdog.commons.q.b paramb, com.flipdog.commons.l.b paramb1)
    throws IOException, com.flipdog.commons.u.a
  {
    paramInputStream.skip(paramFile.length());
    FileOutputStream localFileOutputStream = new FileOutputStream(paramFile, true);
    try
    {
      a(paramInputStream, localFileOutputStream, paramb, paramb1);
      return;
    }
    finally
    {
      localFileOutputStream.close();
    }
  }

  public static void b(InputStream paramInputStream, String paramString)
    throws IOException
  {
    FileOutputStream localFileOutputStream = new FileOutputStream(paramString);
    try
    {
      a(paramInputStream, localFileOutputStream);
      return;
    }
    finally
    {
      localFileOutputStream.close();
    }
  }

  public static void b(String paramString, File paramFile)
    throws IOException
  {
    FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
    try
    {
      a(paramString, localFileOutputStream);
      return;
    }
    finally
    {
      localFileOutputStream.close();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.n
 * JD-Core Version:    0.6.2
 */