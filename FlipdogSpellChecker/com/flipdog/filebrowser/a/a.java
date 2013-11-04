package com.flipdog.filebrowser.a;

import java.io.File;
import java.util.Comparator;

class a
  implements Comparator<File>
{
  a(g paramg)
  {
  }

  public int a(File paramFile1, File paramFile2)
  {
    return com.flipdog.commons.a.g.a(paramFile1.lastModified(), paramFile2.lastModified());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.a.a
 * JD-Core Version:    0.6.2
 */