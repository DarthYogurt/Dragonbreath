package com.flipdog.commons.diagnostic;

import com.flipdog.commons.a.ac;
import com.flipdog.commons.a.as;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class d
  implements e
{
  private static final SimpleDateFormat f = new SimpleDateFormat("yyyy-mm-dd H:mm:ss");
  private File a;
  private File b = new File("/sdcard/flipdog-logs");
  private long c;
  private BufferedWriter d;
  private int e;

  public d(int paramInt1, int paramInt2)
  {
    this.c = paramInt1;
    this.e = (paramInt2 - 1);
    b();
  }

  private BufferedWriter a(File paramFile)
  {
    as.d(this.b);
    try
    {
      BufferedWriter localBufferedWriter = new BufferedWriter(new FileWriter(paramFile, true));
      return localBufferedWriter;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
    return null;
  }

  private File a()
  {
    int i = 0;
    List localList1 = as.a(this.b);
    List localList2 = as.b();
    Iterator localIterator = localList1.iterator();
    int j = 0;
    while (true)
    {
      String str2;
      if (!localIterator.hasNext())
      {
        Collections.sort(localList2);
        if (i >= localList2.size() - this.e)
        {
          File localFile = new File(this.b, a(j));
          if (localFile.length() > this.c)
            localFile = new File(this.b, a(j + 1));
          return localFile;
        }
      }
      else
      {
        String str1 = ((File)localIterator.next()).getName();
        if (!str1.startsWith("md-"))
          continue;
        str2 = str1.replace("md-", "").replace(".txt", "");
      }
      try
      {
        int k = Integer.parseInt(str2);
        localList2.add(Integer.valueOf(k));
        int m = Math.max(k, j);
        j = m;
        continue;
        int n = ((Integer)localList2.get(i)).intValue();
        new File(this.b, a(n)).delete();
        i++;
      }
      catch (NumberFormatException localNumberFormatException)
      {
      }
    }
  }

  private String a(int paramInt)
  {
    Locale localLocale = Locale.US;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    return String.format(localLocale, "md-%d.txt", arrayOfObject);
  }

  private void b()
  {
    this.a = a();
    this.d = a(this.a);
  }

  public void a(String paramString1, String paramString2)
  {
    if (this.a.length() > this.c)
      b();
    if (!this.a.exists())
      b();
    if (this.d == null)
      return;
    while (true)
    {
      long l;
      String str;
      try
      {
        l = Thread.currentThread().getId();
        str = f.format(new Date());
        if (paramString1 != null)
        {
          BufferedWriter localBufferedWriter1 = this.d;
          Object[] arrayOfObject1 = new Object[3];
          arrayOfObject1[0] = Long.valueOf(l);
          arrayOfObject1[1] = str;
          arrayOfObject1[2] = paramString1;
          localBufferedWriter1.write(String.format("[%3s][%s] [%s] | ", arrayOfObject1));
          this.d.write(paramString2);
          this.d.write("\n");
          this.d.flush();
          return;
        }
      }
      catch (IOException localIOException)
      {
        localIOException.printStackTrace();
        return;
      }
      BufferedWriter localBufferedWriter2 = this.d;
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = Long.valueOf(l);
      arrayOfObject2[1] = str;
      localBufferedWriter2.write(String.format("[%3s][%s] | ", arrayOfObject2));
    }
  }

  public void a(Throwable paramThrowable)
  {
    a(null, ac.a(paramThrowable));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.diagnostic.d
 * JD-Core Version:    0.6.2
 */