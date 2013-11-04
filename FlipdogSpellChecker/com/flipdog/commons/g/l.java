package com.flipdog.commons.g;

import android.content.Context;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class l
{
  private String a = new File(((Context)com.flipdog.commons.i.b.a(Context.class)).getFilesDir(), "license.dat").getPath();

  public b a()
    throws IOException
  {
    try
    {
      b localb = new b();
      BufferedReader localBufferedReader = new BufferedReader(new FileReader(new File(this.a)));
      try
      {
        localb.a = localBufferedReader.readLine();
        localb.b = localBufferedReader.readLine();
        localBufferedReader.close();
        return localb;
      }
      finally
      {
        localObject1 = finally;
        localBufferedReader.close();
        throw localObject1;
      }
    }
    finally
    {
    }
  }

  // ERROR //
  public void a(b paramb)
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_1
    //   3: getfield 57	com/flipdog/commons/g/b:a	Ljava/lang/String;
    //   6: ifnull +12 -> 18
    //   9: aload_1
    //   10: getfield 60	com/flipdog/commons/g/b:b	Ljava/lang/String;
    //   13: astore_3
    //   14: aload_3
    //   15: ifnonnull +6 -> 21
    //   18: aload_0
    //   19: monitorexit
    //   20: return
    //   21: new 66	java/io/FileWriter
    //   24: dup
    //   25: new 12	java/io/File
    //   28: dup
    //   29: aload_0
    //   30: getfield 34	com/flipdog/commons/g/l:a	Ljava/lang/String;
    //   33: invokespecial 47	java/io/File:<init>	(Ljava/lang/String;)V
    //   36: invokespecial 67	java/io/FileWriter:<init>	(Ljava/io/File;)V
    //   39: astore 4
    //   41: aload 4
    //   43: aload_1
    //   44: getfield 57	com/flipdog/commons/g/b:a	Ljava/lang/String;
    //   47: invokevirtual 70	java/io/FileWriter:write	(Ljava/lang/String;)V
    //   50: aload 4
    //   52: ldc 72
    //   54: invokevirtual 70	java/io/FileWriter:write	(Ljava/lang/String;)V
    //   57: aload 4
    //   59: aload_1
    //   60: getfield 60	com/flipdog/commons/g/b:b	Ljava/lang/String;
    //   63: invokevirtual 70	java/io/FileWriter:write	(Ljava/lang/String;)V
    //   66: aload 4
    //   68: ldc 72
    //   70: invokevirtual 70	java/io/FileWriter:write	(Ljava/lang/String;)V
    //   73: aload 4
    //   75: invokevirtual 73	java/io/FileWriter:close	()V
    //   78: goto -60 -> 18
    //   81: astore_2
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_2
    //   85: athrow
    //   86: astore 5
    //   88: aload 4
    //   90: invokevirtual 73	java/io/FileWriter:close	()V
    //   93: aload 5
    //   95: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	14	81	finally
    //   21	41	81	finally
    //   73	78	81	finally
    //   88	96	81	finally
    //   41	73	86	finally
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.g.l
 * JD-Core Version:    0.6.2
 */