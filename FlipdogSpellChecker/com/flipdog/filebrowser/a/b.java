package com.flipdog.filebrowser.a;

import java.io.File;
import java.io.FileFilter;

class b
  implements FileFilter
{
  b(g paramg)
  {
  }

  public boolean accept(File paramFile)
  {
    return paramFile.isDirectory();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.a.b
 * JD-Core Version:    0.6.2
 */