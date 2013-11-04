package my.org.json;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

public class JSONTokener
{
  private long character;
  private boolean eof;
  private long index;
  private long line;
  private char previous;
  private Reader reader;
  private boolean usePrevious;

  public JSONTokener(InputStream paramInputStream)
    throws JSONException
  {
    this(new InputStreamReader(paramInputStream));
  }

  public JSONTokener(Reader paramReader)
  {
    if (paramReader.markSupported());
    while (true)
    {
      this.reader = paramReader;
      this.eof = false;
      this.usePrevious = false;
      this.previous = '\000';
      this.index = 0L;
      this.character = 1L;
      this.line = 1L;
      return;
      paramReader = new BufferedReader(paramReader);
    }
  }

  public JSONTokener(String paramString)
  {
    this(new StringReader(paramString));
  }

  public static int dehexchar(char paramChar)
  {
    if ((paramChar >= '0') && (paramChar <= '9'))
      return paramChar - '0';
    if ((paramChar >= 'A') && (paramChar <= 'F'))
      return paramChar - '7';
    if ((paramChar >= 'a') && (paramChar <= 'f'))
      return paramChar - 'W';
    return -1;
  }

  public void back()
    throws JSONException
  {
    if ((this.usePrevious) || (this.index <= 0L))
      throw new JSONException("Stepping back two steps is not supported");
    this.index -= 1L;
    this.character -= 1L;
    this.usePrevious = true;
    this.eof = false;
  }

  public boolean end()
  {
    return (this.eof) && (!this.usePrevious);
  }

  public boolean more()
    throws JSONException
  {
    next();
    if (end())
      return false;
    back();
    return true;
  }

  public char next()
    throws JSONException
  {
    int j;
    int k;
    if (this.usePrevious)
    {
      this.usePrevious = false;
      j = this.previous;
      this.index = (1L + this.index);
      if (this.previous != '\r')
        break label113;
      this.line = (1L + this.line);
      k = 0;
      if (j != 10)
        break label107;
      label55: this.character = k;
    }
    while (true)
    {
      while (true)
      {
        this.previous = ((char)j);
        return this.previous;
        try
        {
          int i = this.reader.read();
          j = i;
          if (j > 0)
            break;
          this.eof = true;
          j = 0;
        }
        catch (IOException localIOException)
        {
          throw new JSONException(localIOException);
        }
      }
      label107: k = 1;
      break label55;
      label113: if (j == 10)
      {
        this.line = (1L + this.line);
        this.character = 0L;
      }
      else
      {
        this.character = (1L + this.character);
      }
    }
  }

  public char next(char paramChar)
    throws JSONException
  {
    char c = next();
    if (c != paramChar)
      throw syntaxError("Expected '" + paramChar + "' and instead saw '" + c + "'");
    return c;
  }

  public String next(int paramInt)
    throws JSONException
  {
    if (paramInt == 0)
      return "";
    char[] arrayOfChar = new char[paramInt];
    for (int i = 0; ; i++)
    {
      if (i >= paramInt)
        return new String(arrayOfChar);
      arrayOfChar[i] = next();
      if (end())
        throw syntaxError("Substring bounds error");
    }
  }

  public char nextClean()
    throws JSONException
  {
    char c;
    do
      c = next();
    while ((c != 0) && (c <= ' '));
    return c;
  }

  public String nextString(char paramChar)
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    while (true)
    {
      char c1 = next();
      switch (c1)
      {
      default:
        if (c1 == paramChar)
          return localStringBuffer.toString();
        break;
      case '\000':
      case '\n':
      case '\r':
        throw syntaxError("Unterminated string");
      case '\\':
        char c2 = next();
        switch (c2)
        {
        default:
          throw syntaxError("Illegal escape.");
        case 'b':
          localStringBuffer.append('\b');
          break;
        case 't':
          localStringBuffer.append('\t');
          break;
        case 'n':
          localStringBuffer.append('\n');
          break;
        case 'f':
          localStringBuffer.append('\f');
          break;
        case 'r':
          localStringBuffer.append('\r');
          break;
        case 'u':
          localStringBuffer.append((char)Integer.parseInt(next(4), 16));
          break;
        case '"':
        case '\'':
        case '/':
        case '\\':
          localStringBuffer.append(c2);
        }
        break;
      }
      localStringBuffer.append(c1);
    }
  }

  public String nextTo(char paramChar)
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    while (true)
    {
      char c = next();
      if ((c == paramChar) || (c == 0) || (c == '\n') || (c == '\r'))
      {
        if (c != 0)
          back();
        return localStringBuffer.toString().trim();
      }
      localStringBuffer.append(c);
    }
  }

  public String nextTo(String paramString)
    throws JSONException
  {
    StringBuffer localStringBuffer = new StringBuffer();
    while (true)
    {
      char c = next();
      if ((paramString.indexOf(c) >= 0) || (c == 0) || (c == '\n') || (c == '\r'))
      {
        if (c != 0)
          back();
        return localStringBuffer.toString().trim();
      }
      localStringBuffer.append(c);
    }
  }

  public Object nextValue()
    throws JSONException
  {
    char c = nextClean();
    StringBuffer localStringBuffer;
    switch (c)
    {
    default:
      localStringBuffer = new StringBuffer();
    case '"':
    case '\'':
    case '{':
    case '[':
    }
    String str;
    while (true)
    {
      if ((c < ' ') || (",:]}/\\\"[{;=#".indexOf(c) >= 0))
      {
        back();
        str = localStringBuffer.toString().trim();
        if (!"".equals(str))
          break;
        throw syntaxError("Missing value");
        return nextString(c);
        back();
        return new JSONObject(this);
        back();
        return new JSONArray(this);
      }
      localStringBuffer.append(c);
      c = next();
    }
    return JSONObject.stringToValue(str);
  }

  public char skipTo(char paramChar)
    throws JSONException
  {
    try
    {
      long l1 = this.index;
      long l2 = this.character;
      long l3 = this.line;
      this.reader.mark(1000000);
      char c;
      do
      {
        c = next();
        if (c == 0)
        {
          this.reader.reset();
          this.index = l1;
          this.character = l2;
          this.line = l3;
          return c;
        }
      }
      while (c != paramChar);
      back();
      return c;
    }
    catch (IOException localIOException)
    {
      throw new JSONException(localIOException);
    }
  }

  public JSONException syntaxError(String paramString)
  {
    return new JSONException(paramString + toString());
  }

  public String toString()
  {
    return " at " + this.index + " [character " + this.character + " line " + this.line + "]";
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.org.json.JSONTokener
 * JD-Core Version:    0.6.2
 */