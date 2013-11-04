package my.apache.http.util;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import my.apache.http.Consts;

public final class EncodingUtils
{
  public static byte[] getAsciiBytes(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Parameter may not be null");
    try
    {
      byte[] arrayOfByte = paramString.getBytes(Consts.ASCII.name());
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new Error("HttpClient requires ASCII support");
  }

  public static String getAsciiString(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    return getAsciiString(paramArrayOfByte, 0, paramArrayOfByte.length);
  }

  public static String getAsciiString(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    try
    {
      String str = new String(paramArrayOfByte, paramInt1, paramInt2, Consts.ASCII.name());
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    throw new Error("HttpClient requires ASCII support");
  }

  public static byte[] getBytes(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      throw new IllegalArgumentException("data may not be null");
    if ((paramString2 == null) || (paramString2.length() == 0))
      throw new IllegalArgumentException("charset may not be null or empty");
    try
    {
      byte[] arrayOfByte = paramString1.getBytes(paramString2);
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return paramString1.getBytes();
  }

  public static String getString(byte[] paramArrayOfByte, int paramInt1, int paramInt2, String paramString)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    if ((paramString == null) || (paramString.length() == 0))
      throw new IllegalArgumentException("charset may not be null or empty");
    try
    {
      String str = new String(paramArrayOfByte, paramInt1, paramInt2, paramString);
      return str;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return new String(paramArrayOfByte, paramInt1, paramInt2);
  }

  public static String getString(byte[] paramArrayOfByte, String paramString)
  {
    if (paramArrayOfByte == null)
      throw new IllegalArgumentException("Parameter may not be null");
    return getString(paramArrayOfByte, 0, paramArrayOfByte.length, paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.util.EncodingUtils
 * JD-Core Version:    0.6.2
 */