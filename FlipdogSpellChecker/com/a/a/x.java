package com.a.a;

import com.a.a.b.j;
import com.a.a.c.b;
import com.a.a.c.e;
import com.a.a.c.i;
import java.util.Map;
import my.apache.http.HttpResponse;
import my.apache.http.client.methods.HttpUriRequest;

public final class x
{
  private final HttpUriRequest a;
  private final j b;

  protected x(HttpUriRequest paramHttpUriRequest, j paramj)
  {
    this.a = paramHttpUriRequest;
    this.b = paramj;
  }

  public void a()
  {
    this.a.abort();
  }

  public w b()
    throws i
  {
    try
    {
      HttpResponse localHttpResponse = m.a(this.b, this.a, 180000);
      return new w((Map)m.a(localHttpResponse));
    }
    catch (b localb)
    {
      if (this.a.isAborted())
        throw new e(-1L);
      throw localb;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.x
 * JD-Core Version:    0.6.2
 */