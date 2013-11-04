package com.flipdog.a.c.a;

import com.flipdog.commons.a.ax;
import com.flipdog.commons.a.n;
import com.flipdog.commons.diagnostic.Track;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class a
{
  private static a c;
  protected File a = new File(((com.flipdog.commons.r.b)com.flipdog.commons.i.b.a(com.flipdog.commons.r.b.class)).c());
  protected File b;

  protected a()
  {
    this.a.mkdirs();
    this.b = new File(this.a, "response");
    this.b.mkdirs();
  }

  public static a a()
  {
    if (c == null)
      c = new a();
    return c;
  }

  private void a(File paramFile, String paramString)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramFile;
    arrayOfObject[1] = Integer.valueOf(paramString.length());
    Track.me("Dev", "Write file %s. Size: %d", arrayOfObject);
    try
    {
      n.a(paramString, paramFile);
      return;
    }
    catch (IOException localIOException)
    {
      Track.it(localIOException);
    }
  }

  public void a(String paramString)
  {
    if (ax.a(paramString))
      return;
    File localFile = this.b;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Long.valueOf(new Date().getTime());
    a(new File(localFile, String.format("%d.htm", arrayOfObject)), paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.c.a.a
 * JD-Core Version:    0.6.2
 */