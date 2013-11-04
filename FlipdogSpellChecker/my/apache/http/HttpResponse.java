package my.apache.http;

import java.util.Locale;

public abstract interface HttpResponse extends HttpMessage
{
  public abstract HttpEntity getEntity();

  public abstract Locale getLocale();

  public abstract StatusLine getStatusLine();

  public abstract void setEntity(HttpEntity paramHttpEntity);

  public abstract void setLocale(Locale paramLocale);

  public abstract void setReasonPhrase(String paramString)
    throws IllegalStateException;

  public abstract void setStatusCode(int paramInt)
    throws IllegalStateException;

  public abstract void setStatusLine(ProtocolVersion paramProtocolVersion, int paramInt);

  public abstract void setStatusLine(ProtocolVersion paramProtocolVersion, int paramInt, String paramString);

  public abstract void setStatusLine(StatusLine paramStatusLine);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.HttpResponse
 * JD-Core Version:    0.6.2
 */