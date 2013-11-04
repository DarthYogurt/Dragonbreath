package com.flipdog.filebrowser.a;

import com.flipdog.filebrowser.d;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class g
{
  private static g a;
  private final File b = new File(((com.flipdog.commons.r.b)com.flipdog.commons.i.b.a(com.flipdog.commons.r.b.class)).a(null), "filebrowse");
  private Comparator<File> c = new a(this);

  protected g()
  {
    b();
  }

  public static g a()
  {
    if (a == null)
      a = new g();
    return a;
  }

  private File a(int paramInt)
  {
    File localFile = new File(this.b, com.flipdog.a.h.a.a(paramInt));
    localFile.mkdirs();
    return localFile;
  }

  private void a(File paramFile)
  {
    if (paramFile == null);
    long l;
    do
    {
      return;
      l = c();
    }
    while (l == 0L);
    a(paramFile, l);
  }

  private void a(File paramFile, long paramLong)
  {
    File[] arrayOfFile = paramFile.listFiles(new c(this));
    if (arrayOfFile == null);
    long l1;
    int j;
    do
    {
      return;
      l1 = 0L;
      int i = arrayOfFile.length;
      j = 0;
      if (j < i)
        break;
    }
    while (l1 < paramLong);
    Arrays.sort(arrayOfFile, this.c);
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = paramFile;
    arrayOfObject1[1] = Long.valueOf(l1);
    d.a("Too large store: %s. Current: %d", arrayOfObject1);
    int k = arrayOfFile.length;
    long l2 = l1;
    for (int m = 0; ; m++)
    {
      if (m >= k);
      do
      {
        Object[] arrayOfObject3 = new Object[1];
        arrayOfObject3[0] = Long.valueOf(l2);
        d.a("Store size after deleting: %d", arrayOfObject3);
        return;
        l1 += arrayOfFile[j].length();
        j++;
        break;
        File localFile = arrayOfFile[m];
        boolean bool = localFile.delete();
        long l3 = localFile.length();
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = localFile;
        arrayOfObject2[1] = Boolean.valueOf(bool);
        d.a("Delete file: %s - %b", arrayOfObject2);
        if (bool)
          l2 -= l3;
      }
      while (l2 < paramLong);
    }
  }

  private void b()
  {
    long l = c();
    if (l == 0L);
    while (true)
    {
      return;
      File[] arrayOfFile = this.b.listFiles(new b(this));
      if (arrayOfFile != null)
      {
        int i = arrayOfFile.length;
        for (int j = 0; j < i; j++)
          a(arrayOfFile[j], l);
      }
    }
  }

  private long c()
  {
    long l = com.flipdog.filebrowser.preference.a.b().j();
    if (l == 0L)
      return 0L;
    return 1024L * (l * 1024L);
  }

  public File a(int paramInt, String paramString)
  {
    File localFile = a(paramInt);
    a(localFile.getParentFile());
    return new File(localFile, paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.a.g
 * JD-Core Version:    0.6.2
 */