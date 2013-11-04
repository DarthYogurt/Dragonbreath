package com.flipdog.filebrowser.k;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.filebrowser.b.b.a;
import java.io.File;

public class f
{
  private static long[] a;
  private static String[] b;

  static
  {
    long[] arrayOfLong = new long[4];
    arrayOfLong[0] = 1073741824L;
    arrayOfLong[1] = 1048576L;
    arrayOfLong[2] = 1024L;
    a = arrayOfLong;
    b = new String[4];
    b[0] = a.a("GB");
    b[1] = a.a("MB");
    b[2] = a.a("KB");
    b[3] = a.a("B");
  }

  public static final String a(long paramLong)
  {
    if (paramLong < a[2])
    {
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Long.valueOf(paramLong);
      arrayOfObject2[1] = b[3];
      return String.format("%d %s", arrayOfObject2);
    }
    for (int i = 0; ; i++)
    {
      if (i >= a.length)
        return null;
      if (paramLong >= a[i])
      {
        Object[] arrayOfObject1 = new Object[2];
        arrayOfObject1[0] = Float.valueOf((float)paramLong / (float)a[i]);
        arrayOfObject1[1] = b[i];
        return String.format("%.2f %s", arrayOfObject1);
      }
    }
  }

  public static final String a(Context paramContext, String paramString)
  {
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = paramContext.getFileStreamPath(paramString).getAbsolutePath();
    return String.format("file://%s", arrayOfObject);
  }

  public static final String a(File paramFile)
  {
    return f(paramFile.getName());
  }

  public static final String a(String paramString)
  {
    int i = paramString.lastIndexOf(".");
    if (i == -1)
      return null;
    return paramString.substring(i + 1);
  }

  public static final void a(MyActivity paramMyActivity, String paramString)
  {
    a(paramMyActivity, new File(paramString));
  }

  public static final boolean a(MyActivity paramMyActivity, File paramFile)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    String str = g(a(paramFile.getName()));
    localIntent.setDataAndType(Uri.fromFile(new File(paramFile.getPath().toLowerCase())), str);
    try
    {
      paramMyActivity.startActivity(localIntent);
      return true;
    }
    catch (Exception localException)
    {
      Track.it(localException);
    }
    return false;
  }

  public static final String b(String paramString)
  {
    int i = paramString.lastIndexOf("/");
    if (i == -1)
      i = paramString.lastIndexOf("\\");
    if (i == -1)
      return paramString;
    return paramString.substring(i + 1, paramString.length());
  }

  public static final String c(String paramString)
  {
    int i = paramString.lastIndexOf("/");
    if (i == -1)
      i = paramString.lastIndexOf("\\");
    int j = i + 1;
    int k = paramString.lastIndexOf(".");
    if ((k == -1) || (k < j))
      return paramString.substring(j);
    return paramString.substring(j, k);
  }

  public static final String d(String paramString)
  {
    if (ax.a(paramString))
      return null;
    return new File(paramString).getParent();
  }

  public static final Uri e(String paramString)
  {
    if (paramString.startsWith("/"));
    for (Uri localUri = Uri.parse(String.format("file://%s", new Object[] { paramString })); localUri == null; localUri = Uri.parse(paramString))
      throw new RuntimeException(paramString);
    return localUri;
  }

  public static final String f(String paramString)
  {
    return g(a(paramString));
  }

  public static final String g(String paramString)
  {
    String str;
    if (paramString == null)
      str = null;
    do
    {
      return str;
      MimeTypeMap localMimeTypeMap = MimeTypeMap.getSingleton();
      str = localMimeTypeMap.getMimeTypeFromExtension(paramString);
      if (str == null)
        str = localMimeTypeMap.getMimeTypeFromExtension(paramString.toLowerCase());
      if (str == null)
        str = localMimeTypeMap.getMimeTypeFromExtension(paramString.toUpperCase());
    }
    while (str != null);
    return b.a().b(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.k.f
 * JD-Core Version:    0.6.2
 */