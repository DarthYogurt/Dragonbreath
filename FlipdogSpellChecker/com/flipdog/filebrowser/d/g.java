package com.flipdog.filebrowser.d;

import android.net.Uri;
import com.flipdog.a.g.h;
import com.flipdog.commons.a.ax;
import com.flipdog.filebrowser.b.a.d;
import com.flipdog.filebrowser.e;
import com.flipdog.s;
import java.io.File;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class g
{
  private static final String a = "cloud";

  public static com.flipdog.filebrowser.b.b a(Uri paramUri)
    throws com.flipdog.a.g.b
  {
    com.flipdog.filebrowser.b.b localb = new com.flipdog.filebrowser.b.b();
    i locali = b(paramUri, c(paramUri));
    if (locali.b == null);
    String str2;
    for (localb.b = com.flipdog.filebrowser.k.f.b(locali.a); ; localb.b = locali.a)
    {
      String str1 = locali.c.getQueryParameter("mimeType");
      if (ax.a(str1))
        str1 = null;
      localb.a = str1;
      str2 = locali.c.getQueryParameter("length");
      if (!ax.a(str2))
        break;
      localb.c = -1L;
      return localb;
    }
    localb.c = Long.parseLong(str2);
    return localb;
  }

  private static i a(Uri paramUri, com.flipdog.a.b paramb)
    throws h, com.flipdog.a.g.b
  {
    i locali = b(paramUri, paramb);
    Uri localUri = f(paramUri);
    a(paramb, localUri.getQueryParameter("username"), localUri.getQueryParameter("password"));
    return locali;
  }

  public static String a(int paramInt)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = "cloud";
    arrayOfObject[1] = com.flipdog.a.h.a.a(paramInt);
    return String.format("%s://%s", arrayOfObject);
  }

  public static void a()
  {
    b();
    com.flipdog.a.h.c.a(com.flipdog.filebrowser.b.a.a.c());
    com.flipdog.a.h.c.a(d.c());
    com.flipdog.a.h.c.a(com.flipdog.filebrowser.b.a.b.c());
    com.flipdog.a.h.c.a(com.flipdog.filebrowser.b.a.c.a());
  }

  private static void a(com.flipdog.a.b paramb, String paramString1, String paramString2)
    throws com.flipdog.a.g.b
  {
    com.flipdog.a.a.a locala = paramb.h();
    Iterator localIterator = locala.b_().iterator();
    label28: com.flipdog.a.b.b localb;
    if (!localIterator.hasNext())
      localb = null;
    while (true)
      if (localb == null)
      {
        if (paramb.g())
        {
          throw new com.flipdog.a.g.f();
          localb = (com.flipdog.a.b.b)localIterator.next();
          if (!localb.a.equals(paramString1))
            break;
          if (!localb.b.equals(paramString2))
            break label28;
          continue;
        }
        locala.a(paramb.a(new com.flipdog.a.b.a(paramString1, paramString2)));
        return;
      }
    paramb.a(localb);
  }

  public static boolean a(Uri paramUri, File paramFile, com.flipdog.commons.l.b paramb, com.flipdog.a.a.b paramb1)
    throws com.flipdog.a.g.b
  {
    return a(paramUri, paramFile, null, paramb, paramb1);
  }

  public static boolean a(Uri paramUri, File paramFile, String paramString, com.flipdog.commons.l.b paramb, com.flipdog.a.a.b paramb1)
    throws com.flipdog.a.g.b
  {
    com.flipdog.a.b localb = c(paramUri);
    i locali = a(paramUri, localb);
    return localb.a(paramFile, paramString, new com.flipdog.a.b.b.b(locali.a, locali.b), paramb1, paramb) != null;
  }

  private static i b(Uri paramUri, com.flipdog.a.b paramb)
    throws h
  {
    i locali = new i(null);
    locali.c = f(paramUri);
    String str1 = paramUri.toString();
    int i = str1.indexOf("?");
    if (i == -1)
      throw new h();
    int j = str1.indexOf("/", 3 + "cloud".length());
    boolean bool = paramb instanceof com.flipdog.a.e.b;
    if (!bool)
      j++;
    String str2 = str1.substring(j, i);
    if (bool)
    {
      locali.a = str2;
      locali.b = null;
      return locali;
    }
    locali.a = locali.c.getQueryParameter("name");
    locali.b = str2;
    return locali;
  }

  public static InputStream b(Uri paramUri)
    throws com.flipdog.a.g.b
  {
    com.flipdog.a.b localb = c(paramUri);
    i locali = a(paramUri, localb);
    return localb.a(new com.flipdog.a.b.b.c(locali.a, locali.b));
  }

  private static void b()
  {
    com.flipdog.a.e.a.b.a = e.a;
    com.flipdog.a.e.a.b.b = e.b;
    com.flipdog.a.d.c.a.a = e.c;
    com.flipdog.a.d.c.a.b = e.d;
    com.flipdog.clouds.gdrive.config.GDriveKeys.ApiKey = e.e;
    com.flipdog.clouds.gdrive.config.GDriveKeys.ApiSecret = e.f;
    com.flipdog.a.i.a.a = s.fbrowse_clouds_login_wait;
  }

  private static com.flipdog.a.b c(Uri paramUri)
    throws h
  {
    return com.flipdog.a.h.c.a(d(paramUri)).a();
  }

  private static int d(Uri paramUri)
    throws h
  {
    a();
    int i = e(paramUri);
    if (i == -1)
      throw new h();
    return i;
  }

  private static int e(Uri paramUri)
  {
    int i = 0;
    if (!"cloud".equals(paramUri.getScheme()));
    while (true)
    {
      return -1;
      String str = paramUri.getHost();
      String[] arrayOfString = com.flipdog.a.h.a.a();
      int j = arrayOfString.length;
      int k = 0;
      while (k < j)
      {
        if (arrayOfString[k].equals(str))
          return i;
        int m = i + 1;
        k++;
        i = m;
      }
    }
  }

  private static Uri f(Uri paramUri)
    throws h
  {
    String str1 = paramUri.toString();
    int i = str1.indexOf("?");
    if (i == -1)
      throw new h();
    String str2 = str1.substring(i + 1);
    StringBuilder localStringBuilder = new StringBuilder("cloud");
    localStringBuilder.append("://host/test?");
    localStringBuilder.append(str2);
    return Uri.parse(localStringBuilder.toString());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.d.g
 * JD-Core Version:    0.6.2
 */