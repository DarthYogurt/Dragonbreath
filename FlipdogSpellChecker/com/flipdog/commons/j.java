package com.flipdog.commons;

import android.content.Context;
import android.os.Environment;
import java.io.File;

class j
{
  public String a;
  public String b;
  public String c;
  public String d;

  public j()
  {
    Context localContext = c.a();
    File localFile1 = c.b(null);
    File localFile2 = localContext.getFilesDir();
    File localFile3 = Environment.getExternalStorageDirectory();
    String str = localContext.getPackageName();
    this.a = (localFile1.getParent() + "/");
    this.b = (localFile2.getParent() + "/");
    this.c = (new File(localFile3, str).getPath() + "/");
    this.d = String.format("content://%s/", new Object[] { str });
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.j
 * JD-Core Version:    0.6.2
 */