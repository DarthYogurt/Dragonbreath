package com.flipdog.filebrowser.b.b;

import android.text.SpannableStringBuilder;
import android.widget.TextView;
import com.flipdog.commons.a.as;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.filebrowser.k.j;
import java.io.File;
import java.util.List;
import java.util.Stack;

public class d
  implements com.flipdog.filebrowser.c.b
{
  public static final String a = "FOLDER_DIALOG";
  private List<e<File>> b = as.b();
  private int c;
  private SpannableStringBuilder d = new SpannableStringBuilder();
  private Stack<e<File>> e = new Stack();
  private com.flipdog.filebrowser.b.d f;
  private File g;
  private com.flipdog.filebrowser.e.a h;
  private final File i;

  public d(com.flipdog.filebrowser.b.d paramd, com.flipdog.filebrowser.e.a parama)
  {
    this.h = parama;
    this.f = paramd;
    com.flipdog.filebrowser.b.a.a = this;
    this.g = a(this.f.i);
    if (this.g == null)
      this.g = a(com.flipdog.filebrowser.preference.a.b().k());
    this.i = j.a();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.i;
    arrayOfObject[1] = this.g;
    Track.me("Dev", "Root: %s. Start: %s", arrayOfObject);
    if (this.g == null)
    {
      this.g = this.i;
      if (this.g == null)
        this.g = new File("/");
    }
    a(this.g);
  }

  private File a(String paramString)
  {
    File localFile;
    if (!ax.a(paramString))
    {
      localFile = new File(paramString);
      if (!localFile.exists())
        com.flipdog.filebrowser.d.a("Folder not exists: %s", new Object[] { null });
    }
    else
    {
      return null;
    }
    return localFile;
  }

  private void a(File paramFile)
  {
    Stack localStack = new Stack();
    localStack.add(paramFile);
    File localFile = paramFile.getParentFile();
    if (localFile == null)
    {
      this.c = (-1 + localStack.size());
      this.b.clear();
    }
    while (true)
    {
      if (localStack.empty())
      {
        return;
        localStack.push(localFile);
        localFile = localFile.getParentFile();
        break;
      }
      this.b.add(new e((File)localStack.pop()));
    }
  }

  public String a()
  {
    return b().getName();
  }

  public void a(int paramInt)
  {
    if (com.flipdog.filebrowser.preference.a.b().i())
      this.e.push((e)this.b.get(this.c));
    this.c = (-1 + this.b.size());
    for (int j = this.c - paramInt; ; j--)
    {
      if (j <= 0)
      {
        this.h.i();
        return;
      }
      List localList = this.b;
      int k = this.c;
      this.c = (k - 1);
      localList.remove(k);
    }
  }

  public void a(File paramFile, int paramInt)
  {
    if (this.b.size() > 0)
    {
      ((e)this.b.get(this.c)).b = paramInt;
      if (com.flipdog.filebrowser.preference.a.b().i())
        this.e.push((e)this.b.get(this.c));
    }
    this.b.add(new e(paramFile));
    this.c = (1 + this.c);
  }

  public File b()
  {
    return (File)((e)this.b.get(this.c)).a;
  }

  public boolean c()
  {
    if (this.c == 0)
      return true;
    if (this.i != null)
    {
      File localFile = b();
      return this.i.equals(localFile);
    }
    return false;
  }

  public void d()
  {
    if (com.flipdog.filebrowser.preference.a.b().i())
    {
      if (this.e.isEmpty());
      e locale;
      for (File localFile = null; ; localFile = (File)locale.a)
      {
        if (localFile == null)
          break label73;
        a(localFile);
        return;
        locale = (e)this.e.pop();
        if (!((File)locale.a).exists())
          break;
      }
    }
    label73: this.e.clear();
    List localList = this.b;
    int j = this.c;
    this.c = (j - 1);
    localList.remove(j);
  }

  public File[] e()
  {
    g();
    File localFile = (File)((e)this.b.get(this.c)).a;
    File[] arrayOfFile = localFile.listFiles(new b(this));
    if (arrayOfFile != null)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = localFile.getName();
      arrayOfObject[1] = Integer.valueOf(arrayOfFile.length);
      com.flipdog.filebrowser.d.a("List folder: %s. Count: %d", arrayOfObject);
    }
    if (arrayOfFile == null)
      return new File[0];
    return arrayOfFile;
  }

  public int f()
  {
    return ((e)this.b.get(this.c)).b;
  }

  public void g()
  {
    this.d.clear();
    this.d.append("/ ");
    int j = 2;
    for (int k = 0; ; k++)
    {
      if (k >= this.b.size())
      {
        this.f.h.setText(this.d);
        return;
      }
      String str = ((File)((e)this.b.get(k)).a).getName();
      if (k == 0)
        str = "root";
      this.d.append(str);
      int m = str.length();
      this.d.setSpan(new com.flipdog.filebrowser.b.a(k), j, j + m, 33);
      int n = m + j;
      this.d.append(" / ");
      j = n + 3;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.b.b.d
 * JD-Core Version:    0.6.2
 */