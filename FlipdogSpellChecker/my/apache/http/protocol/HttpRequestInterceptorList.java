package my.apache.http.protocol;

import java.util.List;
import my.apache.http.HttpRequestInterceptor;

public abstract interface HttpRequestInterceptorList
{
  public abstract void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor);

  public abstract void addRequestInterceptor(HttpRequestInterceptor paramHttpRequestInterceptor, int paramInt);

  public abstract void clearRequestInterceptors();

  public abstract HttpRequestInterceptor getRequestInterceptor(int paramInt);

  public abstract int getRequestInterceptorCount();

  public abstract void removeRequestInterceptorByClass(Class<? extends HttpRequestInterceptor> paramClass);

  public abstract void setInterceptors(List<?> paramList);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.HttpRequestInterceptorList
 * JD-Core Version:    0.6.2
 */