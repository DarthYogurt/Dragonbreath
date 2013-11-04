package my.apache.http.client.utils;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import my.apache.http.Consts;
import my.apache.http.Header;
import my.apache.http.HeaderElement;
import my.apache.http.HttpEntity;
import my.apache.http.NameValuePair;
import my.apache.http.annotation.Immutable;
import my.apache.http.entity.ContentType;
import my.apache.http.message.BasicHeaderValueParser;
import my.apache.http.message.BasicNameValuePair;
import my.apache.http.message.ParserCursor;
import my.apache.http.protocol.HTTP;
import my.apache.http.util.CharArrayBuffer;
import my.apache.http.util.EntityUtils;

@Immutable
public class URLEncodedUtils
{
  public static final String CONTENT_TYPE = "application/x-www-form-urlencoded";
  private static final char[] DELIM = { '&' };
  private static final BitSet FRAGMENT;
  private static final String NAME_VALUE_SEPARATOR = "=";
  private static final String PARAMETER_SEPARATOR = "&";
  private static final BitSet PATHSAFE;
  private static final BitSet PUNCT;
  private static final int RADIX = 16;
  private static final BitSet RESERVED;
  private static final BitSet UNRESERVED = new BitSet(256);
  private static final BitSet URLENCODER;
  private static final BitSet USERINFO;

  static
  {
    PUNCT = new BitSet(256);
    USERINFO = new BitSet(256);
    PATHSAFE = new BitSet(256);
    FRAGMENT = new BitSet(256);
    RESERVED = new BitSet(256);
    URLENCODER = new BitSet(256);
    int i = 97;
    int j;
    if (i > 122)
    {
      j = 65;
      label114: if (j <= 90)
        break label493;
    }
    for (int k = 48; ; k++)
    {
      if (k > 57)
      {
        UNRESERVED.set(95);
        UNRESERVED.set(45);
        UNRESERVED.set(46);
        UNRESERVED.set(42);
        URLENCODER.or(UNRESERVED);
        UNRESERVED.set(33);
        UNRESERVED.set(126);
        UNRESERVED.set(39);
        UNRESERVED.set(40);
        UNRESERVED.set(41);
        PUNCT.set(44);
        PUNCT.set(59);
        PUNCT.set(58);
        PUNCT.set(36);
        PUNCT.set(38);
        PUNCT.set(43);
        PUNCT.set(61);
        USERINFO.or(UNRESERVED);
        USERINFO.or(PUNCT);
        PATHSAFE.or(UNRESERVED);
        PATHSAFE.set(47);
        PATHSAFE.set(59);
        PATHSAFE.set(58);
        PATHSAFE.set(64);
        PATHSAFE.set(38);
        PATHSAFE.set(61);
        PATHSAFE.set(43);
        PATHSAFE.set(36);
        PATHSAFE.set(44);
        RESERVED.set(59);
        RESERVED.set(47);
        RESERVED.set(63);
        RESERVED.set(58);
        RESERVED.set(64);
        RESERVED.set(38);
        RESERVED.set(61);
        RESERVED.set(43);
        RESERVED.set(36);
        RESERVED.set(44);
        RESERVED.set(91);
        RESERVED.set(93);
        FRAGMENT.or(RESERVED);
        FRAGMENT.or(UNRESERVED);
        return;
        UNRESERVED.set(i);
        i++;
        break;
        label493: UNRESERVED.set(j);
        j++;
        break label114;
      }
      UNRESERVED.set(k);
    }
  }

  private static String decodeFormFields(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return null;
    if (paramString2 != null);
    for (Charset localCharset = Charset.forName(paramString2); ; localCharset = Consts.UTF_8)
      return urldecode(paramString1, localCharset, true);
  }

  private static String decodeFormFields(String paramString, Charset paramCharset)
  {
    if (paramString == null)
      return null;
    if (paramCharset != null);
    while (true)
    {
      return urldecode(paramString, paramCharset, true);
      paramCharset = Consts.UTF_8;
    }
  }

  static String encFragment(String paramString, Charset paramCharset)
  {
    return urlencode(paramString, paramCharset, FRAGMENT, false);
  }

  static String encPath(String paramString, Charset paramCharset)
  {
    return urlencode(paramString, paramCharset, PATHSAFE, false);
  }

  static String encUserInfo(String paramString, Charset paramCharset)
  {
    return urlencode(paramString, paramCharset, USERINFO, false);
  }

  private static String encodeFormFields(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return null;
    if (paramString2 != null);
    for (Charset localCharset = Charset.forName(paramString2); ; localCharset = Consts.UTF_8)
      return urlencode(paramString1, localCharset, URLENCODER, true);
  }

  private static String encodeFormFields(String paramString, Charset paramCharset)
  {
    if (paramString == null)
      return null;
    if (paramCharset != null);
    while (true)
    {
      return urlencode(paramString, paramCharset, URLENCODER, true);
      paramCharset = Consts.UTF_8;
    }
  }

  public static String format(Iterable<? extends NameValuePair> paramIterable, Charset paramCharset)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramIterable.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localStringBuilder.toString();
      NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
      String str1 = encodeFormFields(localNameValuePair.getName(), paramCharset);
      String str2 = encodeFormFields(localNameValuePair.getValue(), paramCharset);
      if (localStringBuilder.length() > 0)
        localStringBuilder.append("&");
      localStringBuilder.append(str1);
      if (str2 != null)
      {
        localStringBuilder.append("=");
        localStringBuilder.append(str2);
      }
    }
  }

  public static String format(List<? extends NameValuePair> paramList, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localStringBuilder.toString();
      NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
      String str1 = encodeFormFields(localNameValuePair.getName(), paramString);
      String str2 = encodeFormFields(localNameValuePair.getValue(), paramString);
      if (localStringBuilder.length() > 0)
        localStringBuilder.append("&");
      localStringBuilder.append(str1);
      if (str2 != null)
      {
        localStringBuilder.append("=");
        localStringBuilder.append(str2);
      }
    }
  }

  public static boolean isEncoded(HttpEntity paramHttpEntity)
  {
    Header localHeader = paramHttpEntity.getContentType();
    boolean bool = false;
    if (localHeader != null)
    {
      HeaderElement[] arrayOfHeaderElement = localHeader.getElements();
      int i = arrayOfHeaderElement.length;
      bool = false;
      if (i > 0)
        bool = arrayOfHeaderElement[0].getName().equalsIgnoreCase("application/x-www-form-urlencoded");
    }
    return bool;
  }

  public static List<NameValuePair> parse(String paramString, Charset paramCharset)
  {
    Object localObject;
    if (paramString == null)
      localObject = Collections.emptyList();
    while (true)
    {
      return localObject;
      BasicHeaderValueParser localBasicHeaderValueParser = BasicHeaderValueParser.DEFAULT;
      CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
      localCharArrayBuffer.append(paramString);
      ParserCursor localParserCursor = new ParserCursor(0, localCharArrayBuffer.length());
      localObject = new ArrayList();
      while (!localParserCursor.atEnd())
      {
        NameValuePair localNameValuePair = localBasicHeaderValueParser.parseNameValuePair(localCharArrayBuffer, localParserCursor, DELIM);
        if (localNameValuePair.getName().length() > 0)
          ((List)localObject).add(new BasicNameValuePair(decodeFormFields(localNameValuePair.getName(), paramCharset), decodeFormFields(localNameValuePair.getValue(), paramCharset)));
      }
    }
  }

  public static List<NameValuePair> parse(URI paramURI, String paramString)
  {
    String str = paramURI.getRawQuery();
    if ((str != null) && (str.length() > 0))
    {
      ArrayList localArrayList = new ArrayList();
      parse(localArrayList, new Scanner(str), paramString);
      return localArrayList;
    }
    return Collections.emptyList();
  }

  public static List<NameValuePair> parse(HttpEntity paramHttpEntity)
    throws IOException
  {
    ContentType localContentType = ContentType.get(paramHttpEntity);
    if ((localContentType != null) && (localContentType.getMimeType().equalsIgnoreCase("application/x-www-form-urlencoded")))
    {
      String str = EntityUtils.toString(paramHttpEntity, Consts.ASCII);
      if ((str != null) && (str.length() > 0))
      {
        Charset localCharset = localContentType.getCharset();
        if (localCharset == null)
          localCharset = HTTP.DEF_CONTENT_CHARSET;
        return parse(str, localCharset);
      }
    }
    return Collections.emptyList();
  }

  public static void parse(List<NameValuePair> paramList, Scanner paramScanner, String paramString)
  {
    paramScanner.useDelimiter("&");
    if (!paramScanner.hasNext())
      return;
    String str1 = paramScanner.next();
    int i = str1.indexOf("=");
    String str2;
    if (i != -1)
      str2 = decodeFormFields(str1.substring(0, i).trim(), paramString);
    for (String str3 = decodeFormFields(str1.substring(i + 1).trim(), paramString); ; str3 = null)
    {
      paramList.add(new BasicNameValuePair(str2, str3));
      break;
      str2 = decodeFormFields(str1.trim(), paramString);
    }
  }

  private static String urldecode(String paramString, Charset paramCharset, boolean paramBoolean)
  {
    if (paramString == null)
      return null;
    ByteBuffer localByteBuffer = ByteBuffer.allocate(paramString.length());
    CharBuffer localCharBuffer = CharBuffer.wrap(paramString);
    while (true)
    {
      if (!localCharBuffer.hasRemaining())
      {
        localByteBuffer.flip();
        return paramCharset.decode(localByteBuffer).toString();
      }
      int i = localCharBuffer.get();
      if ((i == 37) && (localCharBuffer.remaining() >= 2))
      {
        char c1 = localCharBuffer.get();
        char c2 = localCharBuffer.get();
        int j = Character.digit(c1, 16);
        int k = Character.digit(c2, 16);
        if ((j != -1) && (k != -1))
        {
          localByteBuffer.put((byte)(k + (j << 4)));
        }
        else
        {
          localByteBuffer.put((byte)37);
          localByteBuffer.put((byte)c1);
          localByteBuffer.put((byte)c2);
        }
      }
      else if ((paramBoolean) && (i == 43))
      {
        localByteBuffer.put((byte)32);
      }
      else
      {
        localByteBuffer.put((byte)i);
      }
    }
  }

  private static String urlencode(String paramString, Charset paramCharset, BitSet paramBitSet, boolean paramBoolean)
  {
    if (paramString == null)
      return null;
    StringBuilder localStringBuilder = new StringBuilder();
    ByteBuffer localByteBuffer = paramCharset.encode(paramString);
    while (true)
    {
      if (!localByteBuffer.hasRemaining())
        return localStringBuilder.toString();
      int i = 0xFF & localByteBuffer.get();
      if (paramBitSet.get(i))
      {
        localStringBuilder.append((char)i);
      }
      else if ((paramBoolean) && (i == 32))
      {
        localStringBuilder.append('+');
      }
      else
      {
        localStringBuilder.append("%");
        char c1 = Character.toUpperCase(Character.forDigit(0xF & i >> 4, 16));
        char c2 = Character.toUpperCase(Character.forDigit(i & 0xF, 16));
        localStringBuilder.append(c1);
        localStringBuilder.append(c2);
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.utils.URLEncodedUtils
 * JD-Core Version:    0.6.2
 */