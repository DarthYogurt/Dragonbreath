package com.flipdog.a.c.b;

import com.flipdog.commons.diagnostic.Track;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import my.apache.http.client.methods.HttpRequestBase;
import my.apache.http.entity.mime.content.FileBody;

public class a extends FileBody
{
  private final com.flipdog.a.a.b a;
  private final com.flipdog.commons.l.b b;
  private final HttpRequestBase c;
  private final String d;

  public a(File paramFile, String paramString)
  {
    super(paramFile, paramString);
    throw new RuntimeException();
  }

  public a(File paramFile, String paramString1, String paramString2, String paramString3)
  {
    super(paramFile, paramString1, paramString2, paramString3);
    throw new RuntimeException();
  }

  public a(HttpRequestBase paramHttpRequestBase, File paramFile, com.flipdog.a.a.b paramb, com.flipdog.commons.l.b paramb1, String paramString)
  {
    super(paramFile);
    this.a = paramb;
    this.b = paramb1;
    this.c = paramHttpRequestBase;
    this.d = paramString;
  }

  public a(HttpRequestBase paramHttpRequestBase, File paramFile, String paramString1, com.flipdog.a.a.b paramb, com.flipdog.commons.l.b paramb1, String paramString2)
  {
    this(paramHttpRequestBase, paramFile, paramString1, "application/octet-stream", paramb, paramb1, paramString2);
  }

  public a(HttpRequestBase paramHttpRequestBase, File paramFile, String paramString1, String paramString2, com.flipdog.a.a.b paramb, com.flipdog.commons.l.b paramb1, String paramString3)
  {
    super(paramFile, paramString1, paramString2, null);
    this.a = paramb;
    this.b = paramb1;
    this.c = paramHttpRequestBase;
    this.d = paramString3;
  }

  private void a(String paramString, Object[] paramArrayOfObject)
  {
    Track.me(this.d, paramString, paramArrayOfObject);
  }

  public InputStream getInputStream()
    throws IOException
  {
    a("CounterFileBody - getInputStream", new Object[0]);
    return new d(this.c, getFile(), this.a, this.b, this.d);
  }

  public void writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    a("CounterFileBody - writeTo %s", new Object[] { paramOutputStream });
    if (paramOutputStream == null)
      throw new IllegalArgumentException("Output stream may not be null");
    InputStream localInputStream = getInputStream();
    try
    {
      byte[] arrayOfByte = new byte[4096];
      while (true)
      {
        int i = localInputStream.read(arrayOfByte);
        if (i == -1)
        {
          paramOutputStream.flush();
          return;
        }
        paramOutputStream.write(arrayOfByte, 0, i);
      }
    }
    finally
    {
      localInputStream.close();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.c.b.a
 * JD-Core Version:    0.6.2
 */