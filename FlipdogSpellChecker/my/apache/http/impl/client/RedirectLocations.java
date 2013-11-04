package my.apache.http.impl.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class RedirectLocations
{
  private final List<URI> all = new ArrayList();
  private final Set<URI> unique = new HashSet();

  public void add(URI paramURI)
  {
    this.unique.add(paramURI);
    this.all.add(paramURI);
  }

  public boolean contains(URI paramURI)
  {
    return this.unique.contains(paramURI);
  }

  public List<URI> getAll()
  {
    return new ArrayList(this.all);
  }

  public boolean remove(URI paramURI)
  {
    boolean bool = this.unique.remove(paramURI);
    Iterator localIterator;
    if (bool)
      localIterator = this.all.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return bool;
      if (((URI)localIterator.next()).equals(paramURI))
        localIterator.remove();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.client.RedirectLocations
 * JD-Core Version:    0.6.2
 */