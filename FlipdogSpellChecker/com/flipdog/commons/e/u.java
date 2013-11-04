package com.flipdog.commons.e;

import android.content.Context;
import android.net.Uri;
import com.flipdog.activity.f;
import com.flipdog.commons.a.as;
import com.flipdog.commons.a.n;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.filebrowser.d.g;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class u
{
  private static File a()
  {
    return b.b();
  }

  public static void a(Context paramContext, f paramf, String paramString1, Uri paramUri, String paramString2)
  {
    a.a(paramContext, paramf, as.e("Save"), new c(paramString1, paramUri, paramString2));
  }

  private static void a(String paramString1, Uri paramUri, String paramString2)
    throws IOException
  {
    String str = paramUri.getPath() + "/" + paramString2;
    as.b(str);
    Track.it("Save, taretPath = " + str, new String[] { "Save" });
    ByteArrayInputStream localByteArrayInputStream = new ByteArrayInputStream(paramString1.getBytes("UTF-8"));
    try
    {
      FileOutputStream localFileOutputStream = new FileOutputStream(str);
      try
      {
        n.a(localByteArrayInputStream, localFileOutputStream);
        localFileOutputStream.close();
        localByteArrayInputStream.close();
        Track.it("Save, done.", new String[] { "Save" });
        return;
      }
      finally
      {
      }
    }
    finally
    {
      localByteArrayInputStream.close();
    }
  }

  protected static void a(String paramString1, Uri paramUri, String paramString2, com.flipdog.commons.l.b paramb, com.flipdog.a.a.b paramb1)
    throws IOException, com.flipdog.a.g.b
  {
    if (com.flipdog.commons.a.b.b(paramUri))
    {
      b(paramString1, paramUri, paramString2, paramb, paramb1);
      return;
    }
    a(paramString1, paramUri, paramString2);
  }

  private static void b(String paramString1, Uri paramUri, String paramString2, com.flipdog.commons.l.b paramb, com.flipdog.a.a.b paramb1)
    throws IOException, com.flipdog.a.g.b
  {
    File localFile = a();
    try
    {
      n.a(paramString1, localFile);
      g.a(paramUri, localFile, paramString2, paramb, paramb1);
      return;
    }
    finally
    {
      as.f(localFile);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.e.u
 * JD-Core Version:    0.6.2
 */