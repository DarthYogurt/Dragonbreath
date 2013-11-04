package my.apache.http.message;

import java.util.ArrayList;
import java.util.List;
import my.apache.http.HeaderElement;
import my.apache.http.NameValuePair;
import my.apache.http.ParseException;
import my.apache.http.annotation.Immutable;
import my.apache.http.protocol.HTTP;
import my.apache.http.util.CharArrayBuffer;

@Immutable
public class BasicHeaderValueParser
  implements HeaderValueParser
{
  private static final char[] ALL_DELIMITERS = { 59, 44 };
  public static final BasicHeaderValueParser DEFAULT = new BasicHeaderValueParser();
  private static final char ELEM_DELIMITER = ',';
  private static final char PARAM_DELIMITER = ';';

  private static boolean isOneOf(char paramChar, char[] paramArrayOfChar)
  {
    if (paramArrayOfChar != null);
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfChar.length)
        return false;
      if (paramChar == paramArrayOfChar[i])
        return true;
    }
  }

  public static final HeaderElement[] parseElements(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    if (paramHeaderValueParser == null)
      paramHeaderValueParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramHeaderValueParser.parseElements(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  public static final HeaderElement parseHeaderElement(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    if (paramHeaderValueParser == null)
      paramHeaderValueParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramHeaderValueParser.parseHeaderElement(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  public static final NameValuePair parseNameValuePair(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    if (paramHeaderValueParser == null)
      paramHeaderValueParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramHeaderValueParser.parseNameValuePair(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  public static final NameValuePair[] parseParameters(String paramString, HeaderValueParser paramHeaderValueParser)
    throws ParseException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Value to parse may not be null");
    if (paramHeaderValueParser == null)
      paramHeaderValueParser = DEFAULT;
    CharArrayBuffer localCharArrayBuffer = new CharArrayBuffer(paramString.length());
    localCharArrayBuffer.append(paramString);
    return paramHeaderValueParser.parseParameters(localCharArrayBuffer, new ParserCursor(0, paramString.length()));
  }

  protected HeaderElement createHeaderElement(String paramString1, String paramString2, NameValuePair[] paramArrayOfNameValuePair)
  {
    return new BasicHeaderElement(paramString1, paramString2, paramArrayOfNameValuePair);
  }

  protected NameValuePair createNameValuePair(String paramString1, String paramString2)
  {
    return new BasicNameValuePair(paramString1, paramString2);
  }

  public HeaderElement[] parseElements(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      if (paramParserCursor.atEnd())
        return (HeaderElement[])localArrayList.toArray(new HeaderElement[localArrayList.size()]);
      HeaderElement localHeaderElement = parseHeaderElement(paramCharArrayBuffer, paramParserCursor);
      if ((localHeaderElement.getName().length() != 0) || (localHeaderElement.getValue() != null))
        localArrayList.add(localHeaderElement);
    }
  }

  public HeaderElement parseHeaderElement(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    NameValuePair localNameValuePair = parseNameValuePair(paramCharArrayBuffer, paramParserCursor);
    NameValuePair[] arrayOfNameValuePair = (NameValuePair[])null;
    if ((!paramParserCursor.atEnd()) && (paramCharArrayBuffer.charAt(-1 + paramParserCursor.getPos()) != ','))
      arrayOfNameValuePair = parseParameters(paramCharArrayBuffer, paramParserCursor);
    return createHeaderElement(localNameValuePair.getName(), localNameValuePair.getValue(), arrayOfNameValuePair);
  }

  public NameValuePair parseNameValuePair(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    return parseNameValuePair(paramCharArrayBuffer, paramParserCursor, ALL_DELIMITERS);
  }

  public NameValuePair parseNameValuePair(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor, char[] paramArrayOfChar)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getPos();
    int k = paramParserCursor.getUpperBound();
    int m = 0;
    label56: String str1;
    if (i >= k)
    {
      if (i != k)
        break label134;
      m = 1;
      str1 = paramCharArrayBuffer.substringTrimmed(j, k);
    }
    while (true)
    {
      if (m == 0)
        break label150;
      paramParserCursor.updatePos(i);
      return createNameValuePair(str1, null);
      char c1 = paramCharArrayBuffer.charAt(i);
      m = 0;
      if (c1 == '=')
        break label56;
      if (isOneOf(c1, paramArrayOfChar))
      {
        m = 1;
        break label56;
      }
      i++;
      break;
      label134: str1 = paramCharArrayBuffer.substringTrimmed(j, i);
      i++;
    }
    label150: int n = i;
    int i1 = 0;
    int i2 = 0;
    int i3;
    if (i >= k)
    {
      i3 = i;
      label171: if ((n < i3) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(n))))
        break label380;
    }
    while (true)
    {
      if ((i3 <= n) || (!HTTP.isWhitespace(paramCharArrayBuffer.charAt(i3 - 1))))
      {
        if ((i3 - n >= 2) && (paramCharArrayBuffer.charAt(n) == '"') && (paramCharArrayBuffer.charAt(i3 - 1) == '"'))
        {
          n++;
          i3--;
        }
        String str2 = paramCharArrayBuffer.substring(n, i3);
        if (m != 0)
          i++;
        paramParserCursor.updatePos(i);
        return createNameValuePair(str1, str2);
        char c2 = paramCharArrayBuffer.charAt(i);
        if ((c2 == '"') && (i2 == 0))
          if (i1 == 0)
            break label336;
        label336: for (i1 = 0; ; i1 = 1)
        {
          if ((i1 != 0) || (i2 != 0) || (!isOneOf(c2, paramArrayOfChar)))
            break label342;
          m = 1;
          break;
        }
        label342: if (i2 != 0)
        {
          i2 = 0;
          i++;
          break;
        }
        if ((i1 != 0) && (c2 == '\\'));
        for (i2 = 1; ; i2 = 0)
          break;
        label380: n++;
        break label171;
      }
      i3--;
    }
  }

  public NameValuePair[] parseParameters(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getUpperBound();
    while (true)
    {
      if (i >= j);
      while (!HTTP.isWhitespace(paramCharArrayBuffer.charAt(i)))
      {
        paramParserCursor.updatePos(i);
        if (!paramParserCursor.atEnd())
          break;
        return new NameValuePair[0];
      }
      i++;
    }
    ArrayList localArrayList = new ArrayList();
    if (paramParserCursor.atEnd());
    while (true)
    {
      return (NameValuePair[])localArrayList.toArray(new NameValuePair[localArrayList.size()]);
      localArrayList.add(parseNameValuePair(paramCharArrayBuffer, paramParserCursor));
      if (paramCharArrayBuffer.charAt(-1 + paramParserCursor.getPos()) != ',')
        break;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicHeaderValueParser
 * JD-Core Version:    0.6.2
 */