package my.apache.http.message;

import java.util.Locale;
import my.apache.http.HttpEntity;
import my.apache.http.HttpResponse;
import my.apache.http.ProtocolVersion;
import my.apache.http.ReasonPhraseCatalog;
import my.apache.http.StatusLine;
import my.apache.http.annotation.NotThreadSafe;

@NotThreadSafe
public class BasicHttpResponse extends AbstractHttpMessage
  implements HttpResponse
{
  private HttpEntity entity;
  private Locale locale;
  private ReasonPhraseCatalog reasonCatalog;
  private StatusLine statusline;

  public BasicHttpResponse(ProtocolVersion paramProtocolVersion, int paramInt, String paramString)
  {
    this(new BasicStatusLine(paramProtocolVersion, paramInt, paramString), null, null);
  }

  public BasicHttpResponse(StatusLine paramStatusLine)
  {
    this(paramStatusLine, null, null);
  }

  public BasicHttpResponse(StatusLine paramStatusLine, ReasonPhraseCatalog paramReasonPhraseCatalog, Locale paramLocale)
  {
    if (paramStatusLine == null)
      throw new IllegalArgumentException("Status line may not be null.");
    this.statusline = paramStatusLine;
    this.reasonCatalog = paramReasonPhraseCatalog;
    if (paramLocale != null);
    while (true)
    {
      this.locale = paramLocale;
      return;
      paramLocale = Locale.getDefault();
    }
  }

  public HttpEntity getEntity()
  {
    return this.entity;
  }

  public Locale getLocale()
  {
    return this.locale;
  }

  public ProtocolVersion getProtocolVersion()
  {
    return this.statusline.getProtocolVersion();
  }

  protected String getReason(int paramInt)
  {
    if (this.reasonCatalog == null)
      return null;
    return this.reasonCatalog.getReason(paramInt, this.locale);
  }

  public StatusLine getStatusLine()
  {
    return this.statusline;
  }

  public void setEntity(HttpEntity paramHttpEntity)
  {
    this.entity = paramHttpEntity;
  }

  public void setLocale(Locale paramLocale)
  {
    if (paramLocale == null)
      throw new IllegalArgumentException("Locale may not be null.");
    this.locale = paramLocale;
    int i = this.statusline.getStatusCode();
    this.statusline = new BasicStatusLine(this.statusline.getProtocolVersion(), i, getReason(i));
  }

  public void setReasonPhrase(String paramString)
  {
    if ((paramString != null) && ((paramString.indexOf('\n') >= 0) || (paramString.indexOf('\r') >= 0)))
      throw new IllegalArgumentException("Line break in reason phrase.");
    this.statusline = new BasicStatusLine(this.statusline.getProtocolVersion(), this.statusline.getStatusCode(), paramString);
  }

  public void setStatusCode(int paramInt)
  {
    this.statusline = new BasicStatusLine(this.statusline.getProtocolVersion(), paramInt, getReason(paramInt));
  }

  public void setStatusLine(ProtocolVersion paramProtocolVersion, int paramInt)
  {
    this.statusline = new BasicStatusLine(paramProtocolVersion, paramInt, getReason(paramInt));
  }

  public void setStatusLine(ProtocolVersion paramProtocolVersion, int paramInt, String paramString)
  {
    this.statusline = new BasicStatusLine(paramProtocolVersion, paramInt, paramString);
  }

  public void setStatusLine(StatusLine paramStatusLine)
  {
    if (paramStatusLine == null)
      throw new IllegalArgumentException("Status line may not be null");
    this.statusline = paramStatusLine;
  }

  public String toString()
  {
    return this.statusline + " " + this.headergroup;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.message.BasicHttpResponse
 * JD-Core Version:    0.6.2
 */