package com.flipdog.filebrowser.h.b;

import java.io.File;

public class c extends a
{
  public File c;

  public c(File paramFile, com.flipdog.filebrowser.h.a.a parama)
  {
    this.c = paramFile;
    this.b = parama;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.c;
    arrayOfObject[1] = super.toString();
    return String.format("Image path: %s %s", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.h.b.c
 * JD-Core Version:    0.6.2
 */