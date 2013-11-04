package my.apache.http;

import java.util.Iterator;

public abstract interface TokenIterator extends Iterator<Object>
{
  public abstract boolean hasNext();

  public abstract String nextToken();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.TokenIterator
 * JD-Core Version:    0.6.2
 */