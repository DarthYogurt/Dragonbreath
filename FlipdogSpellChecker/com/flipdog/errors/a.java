package com.flipdog.errors;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import com.flipdog.commons.a.ac;
import com.flipdog.commons.a.n;
import com.flipdog.errors.activity.ErrorActivity;
import java.io.File;
import java.io.IOException;

public class a
{
  private static String a(Throwable paramThrowable, String paramString)
    throws PackageManager.NameNotFoundException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Crash Version: " + paramString);
    localStringBuilder.append("\n\n");
    localStringBuilder.append(ac.a(paramThrowable));
    return localStringBuilder.toString();
  }

  public static void a(Context paramContext)
  {
    File localFile = b();
    if (!localFile.exists())
      return;
    try
    {
      String str = n.a(localFile);
      a(localFile);
      ErrorActivity.a(paramContext, str);
      return;
    }
    catch (IOException localIOException)
    {
      ErrorActivity.a(paramContext, localIOException);
    }
  }

  public static void a(File paramFile, Thread paramThread, Throwable paramThrowable, String paramString)
    throws IOException, PackageManager.NameNotFoundException
  {
    String str = a(paramThrowable, paramString);
    a(paramFile);
    n.a(str, paramFile);
  }

  public static void a(Thread paramThread, Throwable paramThrowable, String paramString)
    throws IOException, PackageManager.NameNotFoundException
  {
    a(b(), paramThread, paramThrowable, paramString);
  }

  public static boolean a()
  {
    return b().exists();
  }

  public static boolean a(File paramFile)
  {
    return paramFile.delete();
  }

  private static File b()
  {
    return new File(c().b() + "/crash_report.txt");
  }

  private static com.flipdog.commons.r.b c()
  {
    return (com.flipdog.commons.r.b)com.flipdog.commons.i.b.a(com.flipdog.commons.r.b.class);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.errors.a
 * JD-Core Version:    0.6.2
 */