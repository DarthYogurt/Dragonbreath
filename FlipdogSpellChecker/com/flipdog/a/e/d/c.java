package com.flipdog.a.e.d;

import com.a.a.a;
import com.a.a.r;
import java.io.File;

public class c extends com.flipdog.a.f.g
{
  protected final a<com.a.a.b.g> a;
  private r b;

  public c(a<com.a.a.b.g> parama)
  {
    super("Dropbox");
    this.a = parama;
  }

  public com.flipdog.a.b.b.c a(File paramFile, String paramString, com.flipdog.a.b.b.b paramb, com.flipdog.a.a.b paramb1, com.flipdog.commons.l.b paramb2)
    throws com.flipdog.a.g.b
  {
    try
    {
      com.flipdog.a.b.b.c localc = a(paramFile, paramString, paramb, paramb1, paramb2, 0L);
      return localc;
    }
    catch (Exception localException)
    {
      com.flipdog.a.e.c.b.b(localException);
    }
    return null;
  }

  // ERROR //
  public com.flipdog.a.b.b.c a(File paramFile, String paramString, com.flipdog.a.b.b.b paramb, com.flipdog.a.a.b paramb1, com.flipdog.commons.l.b paramb2, long paramLong)
    throws java.io.FileNotFoundException, com.a.a.c.i
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore 8
    //   3: aload_1
    //   4: invokevirtual 56	java/io/File:length	()J
    //   7: lstore 16
    //   9: new 58	java/io/FileInputStream
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 61	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   17: astore 10
    //   19: aload_3
    //   20: getfield 66	com/flipdog/a/b/b/b:a	Ljava/lang/String;
    //   23: astore 18
    //   25: aload_3
    //   26: getfield 66	com/flipdog/a/b/b/b:a	Ljava/lang/String;
    //   29: ldc 68
    //   31: invokevirtual 74	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   34: ifne +25 -> 59
    //   37: new 76	java/lang/StringBuilder
    //   40: dup
    //   41: aload 18
    //   43: invokestatic 80	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   46: invokespecial 81	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   49: ldc 68
    //   51: invokevirtual 85	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   54: invokevirtual 89	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   57: astore 18
    //   59: aload_2
    //   60: ifnonnull +8 -> 68
    //   63: aload_1
    //   64: invokevirtual 92	java/io/File:getName	()Ljava/lang/String;
    //   67: astore_2
    //   68: ldc 94
    //   70: iconst_2
    //   71: anewarray 96	java/lang/Object
    //   74: dup
    //   75: iconst_0
    //   76: aload 18
    //   78: aastore
    //   79: dup
    //   80: iconst_1
    //   81: aload_2
    //   82: aastore
    //   83: invokestatic 100	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   86: astore 19
    //   88: iconst_3
    //   89: anewarray 96	java/lang/Object
    //   92: astore 20
    //   94: aload 20
    //   96: iconst_0
    //   97: aload_2
    //   98: aastore
    //   99: aload 20
    //   101: iconst_1
    //   102: lload 16
    //   104: invokestatic 105	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   107: aastore
    //   108: aload 20
    //   110: iconst_2
    //   111: aload_3
    //   112: getfield 66	com/flipdog/a/b/b/b:a	Ljava/lang/String;
    //   115: aastore
    //   116: aload_0
    //   117: ldc 107
    //   119: aload 20
    //   121: invokevirtual 29	com/flipdog/a/e/d/c:a	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   124: aload_0
    //   125: getfield 18	com/flipdog/a/e/d/c:a	Lcom/a/a/a;
    //   128: astore 21
    //   130: new 109	com/flipdog/a/e/d/d
    //   133: dup
    //   134: aload_0
    //   135: aload 5
    //   137: aload 4
    //   139: lload 6
    //   141: invokespecial 112	com/flipdog/a/e/d/d:<init>	(Lcom/flipdog/a/e/d/c;Lcom/flipdog/commons/l/b;Lcom/flipdog/a/a/b;J)V
    //   144: astore 22
    //   146: aload_0
    //   147: aload 21
    //   149: aload 19
    //   151: aload 10
    //   153: lload 16
    //   155: aload 22
    //   157: invokevirtual 117	com/a/a/a:b	(Ljava/lang/String;Ljava/io/InputStream;JLcom/a/a/h;)Lcom/a/a/r;
    //   160: putfield 21	com/flipdog/a/e/d/c:b	Lcom/a/a/r;
    //   163: aload_0
    //   164: getfield 21	com/flipdog/a/e/d/c:b	Lcom/a/a/r;
    //   167: invokeinterface 122 1 0
    //   172: astore 23
    //   174: aload_0
    //   175: aload 4
    //   177: lload 16
    //   179: lload 16
    //   181: invokevirtual 25	com/flipdog/a/e/d/c:a	(Lcom/flipdog/a/a/b;JJ)V
    //   184: aload 23
    //   186: aload_3
    //   187: invokestatic 125	com/flipdog/a/e/c/b:a	(Lcom/a/a/c;Lcom/flipdog/a/b/b/b;)Lcom/flipdog/a/b/b/a;
    //   190: checkcast 127	com/flipdog/a/b/b/c
    //   193: astore 24
    //   195: aload 10
    //   197: ifnull +8 -> 205
    //   200: aload 10
    //   202: invokevirtual 133	java/io/InputStream:close	()V
    //   205: aload 24
    //   207: areturn
    //   208: astore 25
    //   210: aload 25
    //   212: invokestatic 139	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/Throwable;)V
    //   215: aload 24
    //   217: areturn
    //   218: astore 12
    //   220: aload 12
    //   222: getfield 142	com/a/a/c/e:a	J
    //   225: lstore 13
    //   227: lload 13
    //   229: ldc2_w 143
    //   232: lcmp
    //   233: ifne +25 -> 258
    //   236: aload 8
    //   238: ifnull +8 -> 246
    //   241: aload 8
    //   243: invokevirtual 133	java/io/InputStream:close	()V
    //   246: aconst_null
    //   247: areturn
    //   248: astore 15
    //   250: aload 15
    //   252: invokestatic 139	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/Throwable;)V
    //   255: goto -9 -> 246
    //   258: aload 12
    //   260: athrow
    //   261: astore 9
    //   263: aload 8
    //   265: astore 10
    //   267: aload 10
    //   269: ifnull +8 -> 277
    //   272: aload 10
    //   274: invokevirtual 133	java/io/InputStream:close	()V
    //   277: aload 9
    //   279: athrow
    //   280: astore 11
    //   282: aload 11
    //   284: invokestatic 139	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/Throwable;)V
    //   287: goto -10 -> 277
    //   290: astore 9
    //   292: aconst_null
    //   293: astore 10
    //   295: goto -28 -> 267
    //   298: astore 9
    //   300: goto -33 -> 267
    //   303: astore 12
    //   305: aload 10
    //   307: astore 8
    //   309: goto -89 -> 220
    //
    // Exception table:
    //   from	to	target	type
    //   200	205	208	java/io/IOException
    //   3	19	218	com/a/a/c/e
    //   241	246	248	java/io/IOException
    //   220	227	261	finally
    //   258	261	261	finally
    //   272	277	280	java/io/IOException
    //   3	19	290	finally
    //   19	59	298	finally
    //   63	68	298	finally
    //   68	195	298	finally
    //   19	59	303	com/a/a/c/e
    //   63	68	303	com/a/a/c/e
    //   68	195	303	com/a/a/c/e
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.e.d.c
 * JD-Core Version:    0.6.2
 */