package com.a.a;

import java.io.IOException;
import java.io.OutputStream;
import my.apache.http.HttpEntity;
import my.apache.http.entity.HttpEntityWrapper;

public class s extends HttpEntityWrapper
{
  private final h a;
  private final long b;

  public s(HttpEntity paramHttpEntity, h paramh)
  {
    super(paramHttpEntity);
    this.a = paramh;
    this.b = paramHttpEntity.getContentLength();
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    this.wrappedEntity.writeTo(new b(this, paramOutputStream));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.s
 * JD-Core Version:    0.6.2
 */