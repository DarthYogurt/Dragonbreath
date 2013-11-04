package my.apache.http.protocol;

import java.util.List;
import my.apache.http.HttpResponseInterceptor;

public abstract interface HttpResponseInterceptorList
{
  public abstract void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor);

  public abstract void addResponseInterceptor(HttpResponseInterceptor paramHttpResponseInterceptor, int paramInt);

  public abstract void clearResponseInterceptors();

  public abstract HttpResponseInterceptor getResponseInterceptor(int paramInt);

  public abstract int getResponseInterceptorCount();

  public abstract void removeResponseInterceptorByClass(Class<? extends HttpResponseInterceptor> paramClass);

  public abstract void setInterceptors(List<?> paramList);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.protocol.HttpResponseInterceptorList
 * JD-Core Version:    0.6.2
 */