package my.apache.http.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import my.apache.http.Header;
import my.apache.http.HeaderElement;
import my.apache.http.HttpEntity;
import my.apache.http.NameValuePair;
import my.apache.http.ParseException;
import my.apache.http.entity.ContentType;
import my.apache.http.protocol.HTTP;

public final class EntityUtils
{
  public static void consume(HttpEntity paramHttpEntity)
    throws IOException
  {
    if (paramHttpEntity == null);
    InputStream localInputStream;
    do
    {
      do
        return;
      while (!paramHttpEntity.isStreaming());
      localInputStream = paramHttpEntity.getContent();
    }
    while (localInputStream == null);
    localInputStream.close();
  }

  public static void consumeQuietly(HttpEntity paramHttpEntity)
  {
    try
    {
      consume(paramHttpEntity);
      return;
    }
    catch (IOException localIOException)
    {
    }
  }

  @Deprecated
  public static String getContentCharSet(HttpEntity paramHttpEntity)
    throws ParseException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    Header localHeader = paramHttpEntity.getContentType();
    String str = null;
    if (localHeader != null)
    {
      HeaderElement[] arrayOfHeaderElement = paramHttpEntity.getContentType().getElements();
      int i = arrayOfHeaderElement.length;
      str = null;
      if (i > 0)
      {
        NameValuePair localNameValuePair = arrayOfHeaderElement[0].getParameterByName("charset");
        str = null;
        if (localNameValuePair != null)
          str = localNameValuePair.getValue();
      }
    }
    return str;
  }

  @Deprecated
  public static String getContentMimeType(HttpEntity paramHttpEntity)
    throws ParseException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    Header localHeader = paramHttpEntity.getContentType();
    String str = null;
    if (localHeader != null)
    {
      HeaderElement[] arrayOfHeaderElement = paramHttpEntity.getContentType().getElements();
      int i = arrayOfHeaderElement.length;
      str = null;
      if (i > 0)
        str = arrayOfHeaderElement[0].getName();
    }
    return str;
  }

  public static byte[] toByteArray(HttpEntity paramHttpEntity)
    throws IOException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    InputStream localInputStream = paramHttpEntity.getContent();
    if (localInputStream == null)
      return null;
    try
    {
      if (paramHttpEntity.getContentLength() > 2147483647L)
        throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
    }
    finally
    {
      localInputStream.close();
    }
    int i = (int)paramHttpEntity.getContentLength();
    if (i < 0)
      i = 4096;
    ByteArrayBuffer localByteArrayBuffer = new ByteArrayBuffer(i);
    byte[] arrayOfByte1 = new byte[4096];
    while (true)
    {
      int j = localInputStream.read(arrayOfByte1);
      if (j == -1)
      {
        byte[] arrayOfByte2 = localByteArrayBuffer.toByteArray();
        localInputStream.close();
        return arrayOfByte2;
      }
      localByteArrayBuffer.append(arrayOfByte1, 0, j);
    }
  }

  public static String toString(HttpEntity paramHttpEntity)
    throws IOException, ParseException
  {
    return toString(paramHttpEntity, null);
  }

  public static String toString(HttpEntity paramHttpEntity, String paramString)
    throws IOException, ParseException
  {
    if (paramString != null);
    for (Charset localCharset = Charset.forName(paramString); ; localCharset = null)
      return toString(paramHttpEntity, localCharset);
  }

  public static String toString(HttpEntity paramHttpEntity, Charset paramCharset)
    throws IOException, ParseException
  {
    if (paramHttpEntity == null)
      throw new IllegalArgumentException("HTTP entity may not be null");
    InputStream localInputStream = paramHttpEntity.getContent();
    if (localInputStream == null)
      return null;
    try
    {
      if (paramHttpEntity.getContentLength() > 2147483647L)
        throw new IllegalArgumentException("HTTP entity too large to be buffered in memory");
    }
    finally
    {
      localInputStream.close();
    }
    long l = paramHttpEntity.getContentLength();
    int i = (int)l;
    if (i < 0)
      i = 4096;
    while (true)
    {
      CharArrayBuffer localCharArrayBuffer;
      char[] arrayOfChar;
      int j;
      try
      {
        ContentType localContentType = ContentType.get(paramHttpEntity);
        Object localObject2 = null;
        if (localContentType != null)
        {
          Charset localCharset = localContentType.getCharset();
          localObject2 = localCharset;
        }
        if (localObject2 == null)
          localObject2 = paramCharset;
        if (localObject2 == null)
          localObject2 = HTTP.DEF_CONTENT_CHARSET;
        InputStreamReader localInputStreamReader = new InputStreamReader(localInputStream, (Charset)localObject2);
        localCharArrayBuffer = new CharArrayBuffer(i);
        arrayOfChar = new char[1024];
        j = localInputStreamReader.read(arrayOfChar);
        if (j == -1)
        {
          String str = localCharArrayBuffer.toString();
          localInputStream.close();
          return str;
        }
      }
      catch (UnsupportedCharsetException localUnsupportedCharsetException)
      {
        throw new UnsupportedEncodingException(localUnsupportedCharsetException.getMessage());
      }
      localCharArrayBuffer.append(arrayOfChar, 0, j);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.util.EntityUtils
 * JD-Core Version:    0.6.2
 */