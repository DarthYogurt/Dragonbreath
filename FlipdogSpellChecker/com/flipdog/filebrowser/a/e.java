package com.flipdog.filebrowser.a;

import android.graphics.Bitmap;
import android.view.View.OnClickListener;
import com.flipdog.filebrowser.h.b;
import com.flipdog.filebrowser.h.d;
import com.flipdog.filebrowser.i.c;
import com.flipdog.filebrowser.k.f;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;

public class e extends d
{
  public static final int b = 0;
  public static final int c = -1;
  public View.OnClickListener a;
  private File d;
  private Date e;
  private LinkedHashMap<String, Bitmap> f = new LinkedHashMap();
  private Bitmap g;

  public final String a()
  {
    if (this.d.isDirectory())
      return null;
    return a(this.d.length());
  }

  public final String a(long paramLong)
  {
    return f.a(paramLong);
  }

  public final void a(Bitmap paramBitmap, File paramFile)
  {
    try
    {
      f();
      this.f.put(paramFile.getPath(), paramBitmap);
      return;
    }
    finally
    {
      g();
    }
  }

  public final void a(com.flipdog.filebrowser.h.a.a parama)
  {
    f();
    this.f.put(this.d.getPath(), null);
    g();
    com.flipdog.filebrowser.f.a.b.a(this.d, parama);
  }

  public final void a(File paramFile)
  {
    this.d = paramFile;
    this.e = new Date(this.d.lastModified());
  }

  public final void a(boolean paramBoolean)
  {
    try
    {
      f();
      if (paramBoolean)
      {
        int i = this.f.size();
        if (i < 300)
          return;
      }
      Iterator localIterator = this.f.keySet().iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          this.f.clear();
          return;
        }
        String str = (String)localIterator.next();
        Bitmap localBitmap = (Bitmap)this.f.get(str);
        if (localBitmap != null)
          localBitmap.recycle();
      }
    }
    finally
    {
      g();
    }
  }

  public final String b()
  {
    return c.c(this.e);
  }

  public final void b(File paramFile)
  {
    try
    {
      f();
      this.f.remove(paramFile.getPath());
      return;
    }
    finally
    {
      g();
    }
  }

  public final int c(File paramFile)
  {
    int i = 1;
    if (paramFile.isDirectory())
      return com.flipdog.a.fbrowse_type_folder;
    String str1 = f.a(paramFile);
    if (str1 == null)
    {
      String str3 = f.a(paramFile.getName());
      if ((!"bmp".equals(str3)) && (!"tif".equals(str3)) && (!"tiff".equals(str3)) && (!"png".equals(str3)) && (!"jpg".equals(str3)) && (!"jpeg".equals(str3)))
        i = 0;
    }
    while (i != 0)
      if (!com.flipdog.filebrowser.preference.a.b().g())
      {
        return com.flipdog.a.fbrowse_type_image;
        if (str1.indexOf("image") == -1)
          i = 0;
      }
      else
      {
        String str2 = paramFile.getPath();
        try
        {
          f();
          if (this.f.containsKey(str2))
          {
            this.g = ((Bitmap)this.f.get(str2));
            Bitmap localBitmap = this.g;
            if (localBitmap == null)
              return -1;
            return 0;
          }
          int j = com.flipdog.a.fbrowse_type_image;
          return j;
        }
        finally
        {
          g();
        }
      }
    return com.flipdog.a.fbrowse_type_unknown;
  }

  public final String c()
  {
    return c.d(this.e);
  }

  public final Bitmap d()
  {
    return this.g;
  }

  public final int e()
  {
    return c(this.d);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.a.e
 * JD-Core Version:    0.6.2
 */