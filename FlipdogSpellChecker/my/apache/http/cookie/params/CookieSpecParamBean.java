package my.apache.http.cookie.params;

import java.util.Collection;
import my.apache.http.annotation.NotThreadSafe;
import my.apache.http.params.HttpAbstractParamBean;
import my.apache.http.params.HttpParams;

@NotThreadSafe
public class CookieSpecParamBean extends HttpAbstractParamBean
{
  public CookieSpecParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setDatePatterns(Collection<String> paramCollection)
  {
    this.params.setParameter("http.protocol.cookie-datepatterns", paramCollection);
  }

  public void setSingleHeader(boolean paramBoolean)
  {
    this.params.setBooleanParameter("http.protocol.single-cookie-header", paramBoolean);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.cookie.params.CookieSpecParamBean
 * JD-Core Version:    0.6.2
 */