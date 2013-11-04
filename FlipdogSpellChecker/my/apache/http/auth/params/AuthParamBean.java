package my.apache.http.auth.params;

import my.apache.http.params.HttpAbstractParamBean;
import my.apache.http.params.HttpParams;

public class AuthParamBean extends HttpAbstractParamBean
{
  public AuthParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setCredentialCharset(String paramString)
  {
    AuthParams.setCredentialCharset(this.params, paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.auth.params.AuthParamBean
 * JD-Core Version:    0.6.2
 */