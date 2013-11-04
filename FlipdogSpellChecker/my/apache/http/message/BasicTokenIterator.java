package my.apache.http.message;

import java.util.NoSuchElementException;
import my.apache.http.Header;
import my.apache.http.HeaderIterator;
import my.apache.http.ParseException;
import my.apache.http.TokenIterator;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class BasicTokenIterator
  implements TokenIterator
{
  public static final String HTTP_SEPARATORS = " ,;=()<>@:\\\"/[]?{}\t";
  protected String currentHeader;
  protected String currentToken;
  protected final HeaderIterator headerIt;
  protected int searchPos;

  public BasicTokenIterator(HeaderIterator paramHeaderIterator)
  {
    if (paramHeaderIterator == null)
      throw new IllegalArgumentException("Header iterator must not be null.");
    this.headerIt = paramHeaderIterator;
    this.searchPos = findNext(-1);
  }

  protected String createToken(String paramString, int paramInt1, int paramInt2)
  {
    return paramString.substring(paramInt1, paramInt2);
  }

  protected int findNext(int paramInt)
    throws ParseException
  {
    if (paramInt < 0)
    {
      if (!this.headerIt.hasNext())
        return -1;
      this.currentHeader = this.headerIt.nextHeader().getValue();
    }
    int j;
    for (int i = 0; ; i = findTokenSeparator(paramInt))
    {
      j = findTokenStart(i);
      if (j >= 0)
        break;
      this.currentToken = null;
      return -1;
    }
    int k = findTokenEnd(j);
    this.currentToken = createToken(this.currentHeader, j, k);
    return k;
  }

  protected int findTokenEnd(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Token start position must not be negative: " + paramInt);
    int i = this.currentHeader.length();
    for (int j = paramInt + 1; ; j++)
      if ((j >= i) || (!isTokenChar(this.currentHeader.charAt(j))))
        return j;
  }

  protected int findTokenSeparator(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Search position must not be negative: " + paramInt);
    int i = 0;
    int j = this.currentHeader.length();
    char c;
    while (true)
    {
      if ((i != 0) || (paramInt >= j))
        return paramInt;
      c = this.currentHeader.charAt(paramInt);
      if (isTokenSeparator(c))
      {
        i = 1;
      }
      else
      {
        if (!isWhitespace(c))
          break;
        paramInt++;
      }
    }
    if (isTokenChar(c))
      throw new ParseException("Tokens without separator (pos " + paramInt + "): " + this.currentHeader);
    throw new ParseException("Invalid character after token (pos " + paramInt + "): " + this.currentHeader);
  }

  protected int findTokenStart(int paramInt)
  {
    if (paramInt < 0)
      throw new IllegalArgumentException("Search position must not be negative: " + paramInt);
    int i = 0;
    while (true)
    {
      if ((i != 0) || (this.currentHeader == null))
      {
        if (i == 0)
          break;
        return paramInt;
      }
      int j = this.currentHeader.length();
      while (true)
      {
        if ((i != 0) || (paramInt >= j))
        {
          if (i != 0)
            break;
          if (!this.headerIt.hasNext())
            break label193;
          this.currentHeader = this.headerIt.nextHeader().getValue();
          paramInt = 0;
          break;
        }
        char c = this.currentHeader.charAt(paramInt);
        if ((isTokenSeparator(c)) || (isWhitespace(c)))
        {
          paramInt++;
        }
        else
        {
          if (!isTokenChar(this.currentHeader.charAt(paramInt)))
            break label157;
          i = 1;
        }
      }
      label157: throw new ParseException("Invalid character before token (pos " + paramInt + "): " + this.currentHeader);
      label193: this.currentHeader = null;
    }
    return -1;
  }

  public boolean hasNext()
  {
    return this.currentToken != null;
  }

  protected boolean isHttpSeparator(char paramChar)
  {
    return " ,;=()<>@:\\\"/[]?{}\t".indexOf(paramChar) >= 0;
  }

  protected boolean isTokenChar(char paramChar)
  {
    if (Character.isLetterOrDigit(paramChar));
    do
    {
      return true;
      if (Character.isISOControl(paramChar))
        return false;
    }
    while (!isHttpSeparator(paramChar));
    return false;
  }

  protected boolean isTokenSeparator(char paramChar)
  {
    return paramChar == ',';
  }

  protected boolean isWhitespace(char paramChar)
  {
    return (paramChar == '\t') || (Character.isSpaceChar(paramChar));
  }

  public final Object next()
    throws NoSuchElementException, ParseException
  {
    return nextToken();
  }

  public String nextToken()
    throws NoSuchElementException, ParseException
  {
    if (this.currentToken == null)
      throw new NoSuchElementException("Iteration already finished.");
    String str = this.currentToken;
    this.searchPos = findNext(this.searchPos);
    return str;
  }

  public final void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Removing tokens is not supported.");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicTokenIterator
 * JD-Core Version:    0.6.2
 */