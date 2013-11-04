package com.flipdog.filebrowser.l;

import com.flipdog.activity.MyActivity;
import java.io.File;
import java.util.Date;

public class c extends b
{
  private com.flipdog.filebrowser.l.a.a b;
  private final Object c;

  public c(Object paramObject1, Object paramObject2, MyActivity paramMyActivity, com.flipdog.filebrowser.c.a parama)
  {
    super(paramObject2, paramMyActivity, parama);
    this.c = paramObject1;
  }

  private void a(File paramFile)
  {
    if (paramFile.isDirectory())
    {
      if (c());
      while (true)
      {
        return;
        com.flipdog.filebrowser.l.a.a locala3 = this.b;
        locala3.e = (1 + locala3.e);
        String[] arrayOfString = paramFile.list();
        if (arrayOfString != null)
          for (int i = 0; i < arrayOfString.length; i++)
            a(new File(paramFile, arrayOfString[i]));
      }
    }
    com.flipdog.filebrowser.l.a.a locala1 = this.b;
    locala1.a = (1 + locala1.a);
    com.flipdog.filebrowser.l.a.a locala2 = this.b;
    locala2.f += paramFile.length();
  }

  protected com.flipdog.filebrowser.l.a.b.a b()
    throws Exception
  {
    this.b = new com.flipdog.filebrowser.l.a.a(this);
    File localFile = (File)this.c;
    this.b.h = localFile.getName();
    this.b.g = com.flipdog.filebrowser.i.c.a(new Date(localFile.lastModified()));
    if (localFile.isFile())
    {
      this.b.e = 0;
      this.b.a = 1;
      this.b.f = localFile.length();
    }
    while (true)
    {
      return this.b;
      a(localFile);
      com.flipdog.filebrowser.l.a.a locala = this.b;
      locala.e = (-1 + locala.e);
    }
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.c;
    arrayOfObject[1] = this.b;
    return String.format("Item: %s. Info: %s", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.l.c
 * JD-Core Version:    0.6.2
 */