package my.apache.http.message;

import java.util.List;
import java.util.NoSuchElementException;
import my.apache.http.Header;
import my.apache.http.HeaderIterator;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class BasicListHeaderIterator
  implements HeaderIterator
{
  protected final List<Header> allHeaders;
  protected int currentIndex;
  protected String headerName;
  protected int lastIndex;

  public BasicListHeaderIterator(List<Header> paramList, String paramString)
  {
    if (paramList == null)
      throw new IllegalArgumentException("Header list must not be null.");
    this.allHeaders = paramList;
    this.headerName = paramString;
    this.currentIndex = findNext(-1);
    this.lastIndex = -1;
  }

  protected boolean filterHeader(int paramInt)
  {
    if (this.headerName == null)
      return true;
    String str = ((Header)this.allHeaders.get(paramInt)).getName();
    return this.headerName.equalsIgnoreCase(str);
  }

  protected int findNext(int paramInt)
  {
    if (paramInt < -1)
      return -1;
    int i = -1 + this.allHeaders.size();
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
    this.lastIndex = i;
    this.currentIndex = findNext(i);
    return (Header)this.allHeaders.get(i);
  }

  public void remove()
    throws UnsupportedOperationException
  {
    if (this.lastIndex < 0)
      throw new IllegalStateException("No header to remove.");
    this.allHeaders.remove(this.lastIndex);
    this.lastIndex = -1;
    this.currentIndex = (-1 + this.currentIndex);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicListHeaderIterator
 * JD-Core Version:    0.6.2
 */