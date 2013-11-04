package com.flipdog.commons;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.os.Environment;
import com.flipdog.commons.i.b;
import java.io.File;

public class c
  implements e
{
  private j a = new j();

  public static Context a()
  {
    return (Context)b.a(Context.class);
  }

  public static ContentResolver b()
  {
    return a().getContentResolver();
  }

  public static final File b(String paramString)
  {
    String str = a().getPackageName();
    return new File(Environment.getExternalStorageDirectory(), String.format("Android/data/%s/files", new Object[] { str }));
  }

  private boolean d(File paramFile)
  {
    int i = 0;
    File[] arrayOfFile;
    if (paramFile.exists())
    {
      arrayOfFile = paramFile.listFiles();
      if (arrayOfFile == null)
        arrayOfFile = new File[0];
      int j = arrayOfFile.length;
      if (i < j);
    }
    else
    {
      return paramFile.delete();
    }
    File localFile = arrayOfFile[i];
    if (localFile.isDirectory())
      d(localFile);
    while (true)
    {
      i++;
      break;
      localFile.delete();
    }
  }

  public int a(String paramString)
  {
    Uri localUri = Uri.parse(paramString);
    if (!a(localUri))
      return 0;
    return b().delete(localUri, null, null);
  }

  protected boolean a(Uri paramUri)
  {
    return paramUri.toString().startsWith(this.a.d);
  }

  public boolean a(File paramFile)
  {
    if (!c(paramFile))
      return false;
    return paramFile.delete();
  }

  public boolean b(File paramFile)
  {
    if (!c(paramFile))
      return false;
    return d(paramFile);
  }

  protected boolean c(File paramFile)
  {
    String str = paramFile.getAbsolutePath();
    if (str.startsWith(this.a.b));
    while ((str.startsWith(this.a.a)) || (str.startsWith(this.a.c)))
      return true;
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.c
 * JD-Core Version:    0.6.2
 */