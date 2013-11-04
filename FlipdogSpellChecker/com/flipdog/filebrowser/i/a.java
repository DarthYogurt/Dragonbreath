package com.flipdog.filebrowser.i;

import java.io.File;
import java.util.Comparator;

class a
  implements Comparator<File>
{
  public int a(File paramFile1, File paramFile2)
  {
    boolean bool1 = paramFile2.isDirectory();
    boolean bool2 = paramFile1.isDirectory();
    if ((bool1) && (!bool2))
      return 1;
    if (bool1 == bool2)
      return b.a(paramFile1.getName(), paramFile2.getName());
    return -1;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.i.a
 * JD-Core Version:    0.6.2
 */