package com.flipdog.filebrowser.k;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.webkit.MimeTypeMap;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.i.b;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class l
{
  private static ContentResolver a()
  {
    return ((Context)b.a(Context.class)).getContentResolver();
  }

  public static String a(Uri paramUri)
  {
    if (d(paramUri))
      return i(paramUri).getName();
    i locali = f(paramUri);
    if (locali == null)
      return null;
    return locali.a;
  }

  public static String a(String paramString)
  {
    return MimeTypeMap.getSingleton().getExtensionFromMimeType(paramString);
  }

  public static long b(Uri paramUri)
  {
    if (d(paramUri))
      return i(paramUri).length();
    i locali = f(paramUri);
    if (locali == null)
      return 0L;
    return locali.b;
  }

  public static String b(String paramString)
  {
    if (paramString == null);
    int i;
    do
    {
      return null;
      i = paramString.lastIndexOf(".");
    }
    while (i == -1);
    String str = paramString.substring(i).toLowerCase();
    return c(Uri.fromFile(new File("foo" + str)));
  }

  public static String c(Uri paramUri)
  {
    String str = a().getType(paramUri);
    if (!ax.a(str));
    do
    {
      do
        return str;
      while (!paramUri.getScheme().equals("file"));
      str = g(paramUri);
    }
    while (ax.a(str));
    return str;
  }

  public static boolean d(Uri paramUri)
  {
    if (paramUri.getScheme() == null)
      return true;
    return paramUri.getScheme().equals("file");
  }

  public static InputStream e(Uri paramUri)
    throws IOException
  {
    return a().openInputStream(paramUri);
  }

  // ERROR //
  private static i f(Uri paramUri)
  {
    // Byte code:
    //   0: new 41	com/flipdog/filebrowser/k/i
    //   3: dup
    //   4: invokespecial 136	com/flipdog/filebrowser/k/i:<init>	()V
    //   7: astore_1
    //   8: invokestatic 104	com/flipdog/filebrowser/k/l:a	()Landroid/content/ContentResolver;
    //   11: astore_2
    //   12: iconst_2
    //   13: anewarray 67	java/lang/String
    //   16: dup
    //   17: iconst_0
    //   18: ldc 138
    //   20: aastore
    //   21: dup
    //   22: iconst_1
    //   23: ldc 140
    //   25: aastore
    //   26: astore_3
    //   27: aload_2
    //   28: ifnonnull +15 -> 43
    //   31: new 142	java/lang/RuntimeException
    //   34: dup
    //   35: aload_0
    //   36: invokevirtual 143	android/net/Uri:toString	()Ljava/lang/String;
    //   39: invokespecial 144	java/lang/RuntimeException:<init>	(Ljava/lang/String;)V
    //   42: athrow
    //   43: aload_2
    //   44: aload_0
    //   45: aload_3
    //   46: aconst_null
    //   47: aconst_null
    //   48: aconst_null
    //   49: invokevirtual 148	android/content/ContentResolver:query	(Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;)Landroid/database/Cursor;
    //   52: astore 5
    //   54: aload 5
    //   56: ifnull +44 -> 100
    //   59: aload 5
    //   61: invokeinterface 154 1 0
    //   66: ifeq +27 -> 93
    //   69: aload_1
    //   70: aload 5
    //   72: iconst_0
    //   73: invokeinterface 157 2 0
    //   78: putfield 44	com/flipdog/filebrowser/k/i:a	Ljava/lang/String;
    //   81: aload_1
    //   82: aload 5
    //   84: iconst_1
    //   85: invokeinterface 161 2 0
    //   90: putfield 63	com/flipdog/filebrowser/k/i:b	J
    //   93: aload 5
    //   95: invokeinterface 164 1 0
    //   100: aload_1
    //   101: areturn
    //   102: astore 4
    //   104: aconst_null
    //   105: areturn
    //   106: astore 6
    //   108: aload 5
    //   110: invokeinterface 164 1 0
    //   115: aload 6
    //   117: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   43	54	102	java/lang/Exception
    //   59	93	106	finally
  }

  private static String g(Uri paramUri)
  {
    String str = h(paramUri);
    return MimeTypeMap.getSingleton().getMimeTypeFromExtension(str);
  }

  private static String h(Uri paramUri)
  {
    return MimeTypeMap.getFileExtensionFromUrl(paramUri.toString());
  }

  private static File i(Uri paramUri)
  {
    return new File(paramUri.getPath());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.k.l
 * JD-Core Version:    0.6.2
 */