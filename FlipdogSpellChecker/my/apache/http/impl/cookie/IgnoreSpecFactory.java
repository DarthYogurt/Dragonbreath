package my.apache.http.impl.cookie;

import my.apache.http.annotation.Immutable;
import my.apache.http.cookie.CookieSpec;
import my.apache.http.cookie.CookieSpecFactory;
import my.apache.http.params.HttpParams;

@Immutable
public class IgnoreSpecFactory
  implements CookieSpecFactory
{
  public CookieSpec newInstance(HttpParams paramHttpParams)
  {
    return new IgnoreSpec();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.IgnoreSpecFactory
 * JD-Core Version:    0.6.2
 */