package com.flipdog.commons.a;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.flipdog.filebrowser.d.g;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class b
{
  private static final String a = "application/octet-stream";

  private static ContentResolver a()
  {
    return b().getContentResolver();
  }

  public static q a(Uri paramUri)
  {
    q localq = new q();
    if (b(paramUri))
    {
      com.flipdog.filebrowser.b.b localb = h(paramUri);
      localq.a = localb.b;
      localq.c = localb.c;
      localq.b = localb.a;
      return localq;
    }
    localq.a = c(paramUri);
    localq.c = d(paramUri);
    localq.b = j(paramUri);
    return localq;
  }

  public static boolean a(String paramString)
  {
    if (paramString == null)
      return false;
    try
    {
      boolean bool = b(Uri.parse(paramString));
      return bool;
    }
    catch (Exception localException)
    {
    }
    return false;
  }

  private static Context b()
  {
    return (Context)com.flipdog.commons.i.b.a(Context.class);
  }

  public static String b(String paramString)
  {
    return MimeTypeMap.getSingleton().getExtensionFromMimeType(paramString);
  }

  public static boolean b(Uri paramUri)
  {
    if (paramUri == null)
      return false;
    return ax.c(paramUri.getScheme(), "cloud");
  }

  public static String c(Uri paramUri)
  {
    if (e(paramUri))
      return f(paramUri).getName();
    return i(paramUri).a;
  }

  public static String c(String paramString)
  {
    if (paramString == null);
    String str4;
    do
    {
      String str1;
      do
      {
        return null;
        str1 = d.b(paramString);
      }
      while (ax.a(str1));
      String str2 = str1.toLowerCase().substring(1);
      String str3 = MimeTypeMap.getSingleton().getMimeTypeFromExtension(str2);
      if (!ax.a(str3))
        return str3;
      str4 = aj.a(paramString);
    }
    while (as.a(str4, "application/octet-stream"));
    return str4;
  }

  public static long d(Uri paramUri)
  {
    if (e(paramUri))
      return f(paramUri).length();
    return i(paramUri).b;
  }

  public static boolean e(Uri paramUri)
  {
    if (paramUri.getScheme() == null)
      return true;
    return paramUri.getScheme().equals("file");
  }

  public static File f(Uri paramUri)
  {
    return new File(paramUri.getPath());
  }

  public static InputStream g(Uri paramUri)
    throws IOException
  {
    return a().openInputStream(paramUri);
  }

  private static com.flipdog.filebrowser.b.b h(Uri paramUri)
  {
    try
    {
      com.flipdog.filebrowser.b.b localb1 = g.a(paramUri);
      return localb1;
    }
    catch (com.flipdog.a.g.b localb)
    {
      throw new RuntimeException(localb);
    }
  }

  // ERROR //
  private static at i(Uri paramUri)
  {
    // Byte code:
    //   0: new 109	com/flipdog/commons/a/at
    //   3: dup
    //   4: aconst_null
    //   5: invokespecial 182	com/flipdog/commons/a/at:<init>	(Lcom/flipdog/commons/a/at;)V
    //   8: astore_1
    //   9: invokestatic 161	com/flipdog/commons/a/b:a	()Landroid/content/ContentResolver;
    //   12: astore_2
    //   13: iconst_2
    //   14: anewarray 118	java/lang/String
    //   17: dup
    //   18: iconst_0
    //   19: ldc 184
    //   21: aastore
    //   22: dup
    //   23: iconst_1
    //   24: ldc 186
    //   26: aastore
    //   27: astore_3
    //   28: aload_2
    //   29: aload_0
    //   30: aload_3
    //   31: aconst_null
    //   32: aconst_null
    //   33: aconst_null
    //   34: invokevirtual 190	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   37: astore 5
    //   39: aload 5
    //   41: ifnull +39 -> 80
    //   44: aload 5
    //   46: invokeinterface 196 1 0
    //   51: ifeq +22 -> 73
    //   54: iconst_0
    //   55: istore 7
    //   57: aload 5
    //   59: invokeinterface 200 1 0
    //   64: istore 8
    //   66: iload 7
    //   68: iload 8
    //   70: if_icmplt +21 -> 91
    //   73: aload 5
    //   75: invokeinterface 203 1 0
    //   80: aload_1
    //   81: areturn
    //   82: astore 4
    //   84: aload 4
    //   86: invokestatic 208	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/Throwable;)V
    //   89: aload_1
    //   90: areturn
    //   91: new 210	com/flipdog/commons/a/ao
    //   94: dup
    //   95: aload 5
    //   97: iload 7
    //   99: invokespecial 213	com/flipdog/commons/a/ao:<init>	(Landroid/database/Cursor;I)V
    //   102: astore 9
    //   104: aload 9
    //   106: ldc 184
    //   108: invokevirtual 214	com/flipdog/commons/a/ao:a	(Ljava/lang/String;)Z
    //   111: ifeq +15 -> 126
    //   114: aload_1
    //   115: aload 9
    //   117: invokevirtual 216	com/flipdog/commons/a/ao:a	()Ljava/lang/String;
    //   120: putfield 110	com/flipdog/commons/a/at:a	Ljava/lang/String;
    //   123: goto +37 -> 160
    //   126: aload 9
    //   128: ldc 186
    //   130: invokevirtual 214	com/flipdog/commons/a/ao:a	(Ljava/lang/String;)Z
    //   133: ifeq +27 -> 160
    //   136: aload_1
    //   137: aload 9
    //   139: invokevirtual 218	com/flipdog/commons/a/ao:b	()J
    //   142: putfield 143	com/flipdog/commons/a/at:b	J
    //   145: goto +15 -> 160
    //   148: astore 6
    //   150: aload 5
    //   152: invokeinterface 203 1 0
    //   157: aload 6
    //   159: athrow
    //   160: iinc 7 1
    //   163: goto -106 -> 57
    //
    // Exception table:
    //   from	to	target	type
    //   28	39	82	java/lang/NullPointerException
    //   44	54	148	finally
    //   57	66	148	finally
    //   91	123	148	finally
    //   126	145	148	finally
  }

  private static String j(Uri paramUri)
  {
    String str = a().getType(paramUri);
    if (!ax.a(str));
    do
    {
      do
      {
        return str;
        if (paramUri.getScheme().equals("android.resource"))
        {
          if (as.a((String)paramUri.getPathSegments().get(0), "drawable"))
            return "image/png";
          return null;
        }
      }
      while (!paramUri.getScheme().equals("file"));
      str = c(paramUri.getLastPathSegment());
    }
    while (ax.a(str));
    return str;
  }

  private static String k(Uri paramUri)
  {
    String str = l(paramUri);
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
  }

  private static String l(Uri paramUri)
  {
    return MimeTypeMap.getFileExtensionFromUrl(paramUri.toString());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.b
 * JD-Core Version:    0.6.2
 */