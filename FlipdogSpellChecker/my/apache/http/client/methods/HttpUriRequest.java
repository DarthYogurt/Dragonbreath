package my.apache.http.client.methods;

import java.net.URI;
import my.apache.http.HttpRequest;

public abstract interface HttpUriRequest extends HttpRequest
{
  public abstract void abort()
    throws UnsupportedOperationException;

  public abstract String getMethod();

  public abstract URI getURI();

  public abstract boolean isAborted();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.client.methods.HttpUriRequest
 * JD-Core Version:    0.6.2
 */