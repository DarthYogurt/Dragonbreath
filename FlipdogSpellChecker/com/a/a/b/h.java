package com.a.a.b;

class h extends Thread
{
  private static h d = null;
  private final b a;
  private final int b;
  private final int c;

  public h(b paramb, int paramInt1, int paramInt2)
  {
    this.a = paramb;
    this.b = paramInt1;
    this.c = (paramInt2 * 1000);
  }

  public static void a(b paramb, int paramInt1, int paramInt2)
  {
    try
    {
      if (d == null)
      {
        d = new h(paramb, paramInt1, paramInt2);
        d.start();
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  // ERROR //
  public void run()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_0
    //   4: getfield 25	com/a/a/b/h:c	I
    //   7: i2l
    //   8: invokevirtual 39	java/lang/Object:wait	(J)V
    //   11: aload_0
    //   12: monitorexit
    //   13: aload_0
    //   14: getfield 21	com/a/a/b/h:a	Lcom/a/a/b/b;
    //   17: invokevirtual 44	com/a/a/b/b:closeExpiredConnections	()V
    //   20: aload_0
    //   21: getfield 21	com/a/a/b/h:a	Lcom/a/a/b/b;
    //   24: aload_0
    //   25: getfield 23	com/a/a/b/h:b	I
    //   28: i2l
    //   29: getstatic 50	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   32: invokevirtual 54	com/a/a/b/b:closeIdleConnections	(JLjava/util/concurrent/TimeUnit;)V
    //   35: ldc 2
    //   37: monitorenter
    //   38: aload_0
    //   39: getfield 21	com/a/a/b/h:a	Lcom/a/a/b/b;
    //   42: invokevirtual 58	com/a/a/b/b:getConnectionsInPool	()I
    //   45: ifne +22 -> 67
    //   48: aconst_null
    //   49: putstatic 15	com/a/a/b/h:d	Lcom/a/a/b/h;
    //   52: ldc 2
    //   54: monitorexit
    //   55: return
    //   56: astore_2
    //   57: aload_0
    //   58: monitorexit
    //   59: aload_2
    //   60: athrow
    //   61: astore_1
    //   62: aconst_null
    //   63: putstatic 15	com/a/a/b/h:d	Lcom/a/a/b/h;
    //   66: return
    //   67: ldc 2
    //   69: monitorexit
    //   70: goto -70 -> 0
    //   73: astore_3
    //   74: ldc 2
    //   76: monitorexit
    //   77: aload_3
    //   78: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   2	13	56	finally
    //   57	59	56	finally
    //   0	2	61	java/lang/InterruptedException
    //   13	38	61	java/lang/InterruptedException
    //   59	61	61	java/lang/InterruptedException
    //   77	79	61	java/lang/InterruptedException
    //   38	55	73	finally
    //   67	70	73	finally
    //   74	77	73	finally
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.b.h
 * JD-Core Version:    0.6.2
 */