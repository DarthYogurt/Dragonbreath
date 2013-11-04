package my.apache.http.message;

import java.util.NoSuchElementException;
import my.apache.http.Header;
import my.apache.http.HeaderIterator;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class BasicHeaderIterator
  implements HeaderIterator
{
  protected final Header[] allHeaders;
  protected int currentIndex;
  protected String headerName;

  public BasicHeaderIterator(Header[] paramArrayOfHeader, String paramString)
  {
    if (paramArrayOfHeader == null)
      throw new IllegalArgumentException("Header array must not be null.");
    this.allHeaders = paramArrayOfHeader;
    this.headerName = paramString;
    this.currentIndex = findNext(-1);
  }

  protected boolean filterHeader(int paramInt)
  {
    return (this.headerName == null) || (this.headerName.equalsIgnoreCase(this.allHeaders[paramInt].getName()));
  }

  protected int findNext(int paramInt)
  {
    if (paramInt < -1)
      return -1;
    int i = -1 + this.allHeaders.length;
    for (boolean bool = false; ; bool = filterHeader(paramInt))
    {
      if ((bool) || (paramInt >= i))
      {
        if (!bool)
          break;
        return paramInt;
      }
      paramInt++;
    }
  }

  public boolean hasNext()
  {
    return this.currentIndex >= 0;
  }

  public final Object next()
    throws NoSuchElementException
  {
    return nextHeader();
  }

  public Header nextHeader()
    throws NoSuchElementException
  {
    int i = this.currentIndex;
    if (i < 0)
      throw new NoSuchElementException("Iteration already finished.");
    this.currentIndex = findNext(i);
    return this.allHeaders[i];
  }

  public void remove()
    throws UnsupportedOperationException
  {
    throw new UnsupportedOperationException("Removing headers is not supported.");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicHeaderIterator
 * JD-Core Version:    0.6.2
 */