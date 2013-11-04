package my.apache.http.auth;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import my.apache.http.annotation.ThreadSafe;
import my.apache.http.params.HttpParams;

@ThreadSafe
public final class AuthSchemeRegistry
{
  private final ConcurrentHashMap<String, AuthSchemeFactory> registeredSchemes = new ConcurrentHashMap();

  public AuthScheme getAuthScheme(String paramString, HttpParams paramHttpParams)
    throws IllegalStateException
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    AuthSchemeFactory localAuthSchemeFactory = (AuthSchemeFactory)this.registeredSchemes.get(paramString.toLowerCase(Locale.ENGLISH));
    if (localAuthSchemeFactory != null)
      return localAuthSchemeFactory.newInstance(paramHttpParams);
    throw new IllegalStateException("Unsupported authentication scheme: " + paramString);
  }

  public List<String> getSchemeNames()
  {
    return new ArrayList(this.registeredSchemes.keySet());
  }

  public void register(String paramString, AuthSchemeFactory paramAuthSchemeFactory)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    if (paramAuthSchemeFactory == null)
      throw new IllegalArgumentException("Authentication scheme factory may not be null");
    this.registeredSchemes.put(paramString.toLowerCase(Locale.ENGLISH), paramAuthSchemeFactory);
  }

  public void setItems(Map<String, AuthSchemeFactory> paramMap)
  {
    if (paramMap == null)
      return;
    this.registeredSchemes.clear();
    this.registeredSchemes.putAll(paramMap);
  }

  public void unregister(String paramString)
  {
    if (paramString == null)
      throw new IllegalArgumentException("Name may not be null");
    this.registeredSchemes.remove(paramString.toLowerCase(Locale.ENGLISH));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.AuthSchemeRegistry
 * JD-Core Version:    0.6.2
 */