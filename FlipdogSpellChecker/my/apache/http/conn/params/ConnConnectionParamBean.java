package my.apache.http.conn.params;

import my.apache.http.params.HttpAbstractParamBean;
import my.apache.http.params.HttpParams;

@Deprecated
public class ConnConnectionParamBean extends HttpAbstractParamBean
{
  public ConnConnectionParamBean(HttpParams paramHttpParams)
  {
    super(paramHttpParams);
  }

  public void setMaxStatusLineGarbage(int paramInt)
  {
    this.params.setIntParameter("http.connection.max-status-line-garbage", paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.conn.params.ConnConnectionParamBean
 * JD-Core Version:    0.6.2
 */