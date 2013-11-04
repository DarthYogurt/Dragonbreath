package com.a.a.b;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;
import my.apache.http.HttpEntity;
import my.apache.http.entity.HttpEntityWrapper;

class a extends HttpEntityWrapper
{
  public a(HttpEntity paramHttpEntity)
  {
    super(paramHttpEntity);
  }

  public InputStream getContent()
    throws IOException, IllegalStateException
  {
    return new GZIPInputStream(this.wrappedEntity.getContent());
  }

  public long getContentLength()
  {
    return -1L;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.b.a
 * JD-Core Version:    0.6.2
 */