package my.apache.http.impl.cookie;

import java.util.Collection;
import my.apache.http.annotation.Immutable;
import my.apache.http.cookie.CookieSpec;
import my.apache.http.cookie.CookieSpecFactory;
import my.apache.http.params.HttpParams;

@Immutable
public class RFC2965SpecFactory
  implements CookieSpecFactory
{
  public CookieSpec newInstance(HttpParams paramHttpParams)
  {
    if (paramHttpParams != null)
    {
      String[] arrayOfString = (String[])null;
      Collection localCollection = (Collection)paramHttpParams.getParameter("http.protocol.cookie-datepatterns");
      if (localCollection != null)
        arrayOfString = (String[])localCollection.toArray(new String[localCollection.size()]);
      return new RFC2965Spec(arrayOfString, paramHttpParams.getBooleanParameter("http.protocol.single-cookie-header", false));
    }
    return new RFC2965Spec();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.impl.cookie.RFC2965SpecFactory
 * JD-Core Version:    0.6.2
 */