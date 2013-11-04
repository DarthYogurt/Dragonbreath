package my.apache.http;

import java.util.Iterator;

public abstract interface HeaderIterator extends Iterator<Object>
{
  public abstract boolean hasNext();

  public abstract Header nextHeader();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.HeaderIterator
 * JD-Core Version:    0.6.2
 */