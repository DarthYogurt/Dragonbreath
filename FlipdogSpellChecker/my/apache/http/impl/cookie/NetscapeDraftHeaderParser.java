package my.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.List;
import my.apache.http.HeaderElement;
import my.apache.http.NameValuePair;
import my.apache.http.ParseException;
import my.apache.http.annotation.Immutable;
import my.apache.http.message.BasicHeaderElement;
import my.apache.http.message.BasicNameValuePair;
import my.apache.http.message.ParserCursor;
import my.apache.http.protocol.HTTP;
import my.apache.http.util.CharArrayBuffer;

@Immutable
public class NetscapeDraftHeaderParser
{
  public static final NetscapeDraftHeaderParser DEFAULT = new NetscapeDraftHeaderParser();

  private NameValuePair parseNameValuePair(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
  {
    int i = paramParserCursor.getPos();
    int j = paramParserCursor.getPos();
    int k = paramParserCursor.getUpperBound();
    int m = 0;
    label26: String str1;
    if (i >= k)
    {
      if (i != k)
        break label102;
      m = 1;
      str1 = paramCharArrayBuffer.substringTrimmed(j, k);
    }
    while (true)
    {
      if (m == 0)
        break label117;
      paramParserCursor.updatePos(i);
      return new BasicNameValuePair(str1, null);
      int n = paramCharArrayBuffer.charAt(i);
      m = 0;
      if (n == 61)
        break label26;
      if (n == 59)
      {
        m = 1;
        break label26;
      }
      i++;
      break;
      label102: str1 = paramCharArrayBuffer.substringTrimmed(j, i);
      i++;
    }
    label117: int i1 = i;
    label126: int i2;
    if (i >= k)
    {
      i2 = i;
      label129: if ((i1 < i2) && (HTTP.isWhitespace(paramCharArrayBuffer.charAt(i1))))
        break label226;
    }
    while (true)
    {
      if ((i2 <= i1) || (!HTTP.isWhitespace(paramCharArrayBuffer.charAt(i2 - 1))))
      {
        String str2 = paramCharArrayBuffer.substring(i1, i2);
        if (m != 0)
          i++;
        paramParserCursor.updatePos(i);
        return new BasicNameValuePair(str1, str2);
        if (paramCharArrayBuffer.charAt(i) == ';')
        {
          m = 1;
          break label126;
        }
        i++;
        break;
        label226: i1++;
        break label129;
      }
      i2--;
    }
  }

  public HeaderElement parseHeader(CharArrayBuffer paramCharArrayBuffer, ParserCursor paramParserCursor)
    throws ParseException
  {
    if (paramCharArrayBuffer == null)
      throw new IllegalArgumentException("Char array buffer may not be null");
    if (paramParserCursor == null)
      throw new IllegalArgumentException("Parser cursor may not be null");
    NameValuePair localNameValuePair = parseNameValuePair(paramCharArrayBuffer, paramParserCursor);
    ArrayList localArrayList = new ArrayList();
    while (true)
    {
      if (paramParserCursor.atEnd())
        return new BasicHeaderElement(localNameValuePair.getName(), localNameValuePair.getValue(), (NameValuePair[])localArrayList.toArray(new NameValuePair[localArrayList.size()]));
      localArrayList.add(parseNameValuePair(paramCharArrayBuffer, paramParserCursor));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.NetscapeDraftHeaderParser
 * JD-Core Version:    0.6.2
 */