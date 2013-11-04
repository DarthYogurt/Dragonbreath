package com.flipdog.a.c.b;

import com.flipdog.commons.diagnostic.Track;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import my.apache.http.client.methods.HttpRequestBase;

public class d extends FileInputStream
{
  private final com.flipdog.a.a.b a;
  private final com.flipdog.commons.l.b b;
  private final long c;
  private final HttpRequestBase d;
  private final String e;
  private long f = 0L;
  private int g = 0;

  public d(HttpRequestBase paramHttpRequestBase, File paramFile, com.flipdog.a.a.b paramb, com.flipdog.commons.l.b paramb1, String paramString)
    throws FileNotFoundException
  {
    super(paramFile);
    this.a = paramb;
    this.b = paramb1;
    this.c = paramFile.length();
    this.d = paramHttpRequestBase;
    this.e = paramString;
  }

  private void a(int paramInt)
    throws IOException
  {
    if (this.g > 0);
    do
    {
      do
      {
        return;
        if ((this.b != null) && (this.b.a()))
        {
          Track.me(this.e, "Upload aborted", new Object[0]);
          this.d.abort();
        }
      }
      while (paramInt == -1);
      this.f += paramInt;
      String str = this.e;
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Long.valueOf(this.f);
      arrayOfObject[1] = Long.valueOf(this.c);
      Track.me(str, "Uploaded: %d / %d", arrayOfObject);
    }
    while (this.a == null);
    this.a.a(this.f, this.c);
  }

  public int read()
    throws IOException
  {
    this.g = (1 + this.g);
    int i = super.read();
    this.g = (-1 + this.g);
    a(1);
    return i;
  }

  public int read(byte[] paramArrayOfByte)
    throws IOException
  {
    this.g = (1 + this.g);
    int i = super.read(paramArrayOfByte);
    this.g = (-1 + this.g);
    a(i);
    return i;
  }

  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    this.g = (1 + this.g);
    int i = super.read(paramArrayOfByte, paramInt1, paramInt2);
    this.g = (-1 + this.g);
    a(i);
    return i;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.c.b.d
 * JD-Core Version:    0.6.2
 */