package my.apache.http;

import java.util.Iterator;

public abstract interface HeaderElementIterator extends Iterator<Object>
{
  public abstract boolean hasNext();

  public abstract HeaderElement nextElement();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.HeaderElementIterator
 * JD-Core Version:    0.6.2
 */