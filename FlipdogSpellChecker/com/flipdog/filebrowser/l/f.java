package com.flipdog.filebrowser.l;

import com.flipdog.activity.MyActivity;
import com.flipdog.filebrowser.a.g;
import com.flipdog.filebrowser.d;
import com.flipdog.s;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class f extends b
{
  private final com.flipdog.a.b.b.c b;

  public f(com.flipdog.a.b.b.c paramc, com.flipdog.a.b paramb, MyActivity paramMyActivity, com.flipdog.filebrowser.c.a parama)
  {
    super(paramb, paramMyActivity, parama);
    this.b = paramc;
  }

  private void a(File paramFile, InputStream paramInputStream)
    throws IOException
  {
    int i = 0;
    d.a("Downloading file: %s", new Object[] { paramFile });
    try
    {
      localFileOutputStream = new FileOutputStream(paramFile);
    }
    finally
    {
      try
      {
        byte[] arrayOfByte = new byte[16384];
        while (true)
        {
          int j = paramInputStream.read(arrayOfByte, 0, arrayOfByte.length);
          if (j == -1)
          {
            Object[] arrayOfObject = new Object[1];
            arrayOfObject[0] = Integer.valueOf(i);
            d.a("File size: %d", arrayOfObject);
            if (localFileOutputStream != null)
              localFileOutputStream.close();
            return;
          }
          localFileOutputStream.write(arrayOfByte, 0, j);
          i += j;
        }
        localObject1 = finally;
        FileOutputStream localFileOutputStream = null;
        label105: if (localFileOutputStream != null)
          localFileOutputStream.close();
        throw localObject1;
      }
      finally
      {
        break label105;
      }
    }
  }

  private boolean a(File paramFile, com.flipdog.a.b.b.c paramc)
  {
    boolean bool = paramFile.exists();
    if (bool)
      if (paramFile.length() != paramc.e)
        break label51;
    label51: for (bool = true; ; bool = false)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = paramFile;
      arrayOfObject[1] = Boolean.valueOf(bool);
      d.a("Local file %s exists: %b", arrayOfObject);
      return bool;
    }
  }

  protected com.flipdog.filebrowser.l.a.b.a b()
    throws Exception
  {
    com.flipdog.filebrowser.l.a.a.a locala = new com.flipdog.filebrowser.l.a.a.a(this);
    InputStream localInputStream = null;
    try
    {
      com.flipdog.a.b localb1 = f();
      int i = com.flipdog.a.h.c.a(localb1);
      locala.a = g.a().a(i, this.b.a());
      boolean bool = a(locala.a, this.b);
      localInputStream = null;
      if (!bool)
      {
        localInputStream = localb1.a(this.b);
        a(locala.a, localInputStream);
      }
      return locala;
    }
    catch (com.flipdog.a.g.b localb)
    {
      locala.c = localb;
      return locala;
    }
    finally
    {
      if (localInputStream != null)
        localInputStream.close();
    }
  }

  protected String e()
  {
    return com.flipdog.filebrowser.k.c.a(s.fbrowse_clouds_load_file);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.l.f
 * JD-Core Version:    0.6.2
 */