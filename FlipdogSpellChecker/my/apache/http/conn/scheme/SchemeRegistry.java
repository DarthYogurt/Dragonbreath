package my.apache.http.conn.scheme;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import my.apache.http.HttpHost;
import my.apache.http.annotation.ThreadSafe;

@ThreadSafe
public final class SchemeRegistry
{
  private final ConcurrentHashMap<String, Scheme> registeredSchemes = new ConcurrentHashMap();

  public final Scheme get(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name must not be null.");
    return (Scheme)this.registeredSchemes.get(paramString);
  }

  public final Scheme getScheme(String paramString)
  {
    Scheme localScheme = get(paramString);
    if (localScheme == null)
      throw new IllegalStateException("Scheme '" + paramString + "' not registered.");
    return localScheme;
  }

  public final Scheme getScheme(HttpHost paramHttpHost)
  {
    if (paramHttpHost == null)
      throw new IllegalArgumentException("Host must not be null.");
    return getScheme(paramHttpHost.getSchemeName());
  }

  public final List<String> getSchemeNames()
  {
    return new ArrayList(this.registeredSchemes.keySet());
  }

  public final Scheme register(Scheme paramScheme)
  {
    if (paramScheme == null)
      throw new IllegalArgumentException("Scheme must not be null.");
    return (Scheme)this.registeredSchemes.put(paramScheme.getName(), paramScheme);
  }

  public void setItems(Map<String, Scheme> paramMap)
  {
    if (paramMap == null)
      return;
    this.registeredSchemes.clear();
    this.registeredSchemes.putAll(paramMap);
  }

  public final Scheme unregister(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name must not be null.");
    return (Scheme)this.registeredSchemes.remove(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.scheme.SchemeRegistry
 * JD-Core Version:    0.6.2
 */