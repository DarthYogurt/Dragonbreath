package com.flipdog.filebrowser.b.b;

import java.io.File;
import java.io.FileFilter;

class b
  implements FileFilter
{
  b(d paramd)
  {
  }

  public boolean accept(File paramFile)
  {
    boolean bool = paramFile.isDirectory();
    String str = paramFile.getName();
    return com.flipdog.filebrowser.i.b.a(d.a(this.a), bool, str);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.b.b.b
 * JD-Core Version:    0.6.2
 */