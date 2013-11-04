package com.flipdog.commons.e;

import android.content.Context;
import com.flipdog.commons.a.as;
import java.io.File;

public class b
{
  private static File a;

  public static void a()
  {
    as.g(c());
  }

  public static File b()
  {
    File localFile = new File(c(), as.f());
    as.c(localFile);
    return localFile;
  }

  private static File c()
  {
    if (a == null)
      a = new File(d().getFilesDir(), "tmp");
    return a;
  }

  private static Context d()
  {
    return (Context)com.flipdog.commons.i.b.a(Context.class);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.e.b
 * JD-Core Version:    0.6.2
 */