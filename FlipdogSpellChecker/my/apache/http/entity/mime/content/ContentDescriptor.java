package my.apache.http.entity.mime.content;

public abstract interface ContentDescriptor
{
  public abstract String getCharset();

  public abstract long getContentLength();

  public abstract String getMediaType();

  public abstract String getMimeType();

  public abstract String getSubType();

  public abstract String getTransferEncoding();
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.entity.mime.content.ContentDescriptor
 * JD-Core Version:    0.6.2
 */