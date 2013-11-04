package com.b.b;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class f
{
  private BlockingQueue<e> a = new ArrayBlockingQueue(100);

  // ERROR //
  protected void a()
  {
    // Byte code:
    //   0: ldc 22
    //   2: iconst_1
    //   3: anewarray 24	java/lang/String
    //   6: dup
    //   7: iconst_0
    //   8: ldc 26
    //   10: aastore
    //   11: invokestatic 32	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/String;[Ljava/lang/String;)V
    //   14: aload_0
    //   15: getfield 18	com/b/b/f:a	Ljava/util/concurrent/BlockingQueue;
    //   18: invokeinterface 38 1 0
    //   23: checkcast 40	com/b/b/e
    //   26: astore_2
    //   27: ldc 42
    //   29: iconst_1
    //   30: anewarray 24	java/lang/String
    //   33: dup
    //   34: iconst_0
    //   35: ldc 26
    //   37: aastore
    //   38: invokestatic 32	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/String;[Ljava/lang/String;)V
    //   41: aload_2
    //   42: invokeinterface 44 1 0
    //   47: ldc 46
    //   49: iconst_1
    //   50: anewarray 24	java/lang/String
    //   53: dup
    //   54: iconst_0
    //   55: ldc 26
    //   57: aastore
    //   58: invokestatic 32	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/String;[Ljava/lang/String;)V
    //   61: goto -47 -> 14
    //   64: astore_1
    //   65: ldc 48
    //   67: iconst_1
    //   68: anewarray 24	java/lang/String
    //   71: dup
    //   72: iconst_0
    //   73: ldc 26
    //   75: aastore
    //   76: invokestatic 32	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/String;[Ljava/lang/String;)V
    //   79: aload_1
    //   80: invokestatic 51	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/Throwable;)V
    //   83: goto -69 -> 14
    //   86: astore_3
    //   87: ldc 46
    //   89: iconst_1
    //   90: anewarray 24	java/lang/String
    //   93: dup
    //   94: iconst_0
    //   95: ldc 26
    //   97: aastore
    //   98: invokestatic 32	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/String;[Ljava/lang/String;)V
    //   101: aload_3
    //   102: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   14	41	64	java/lang/Exception
    //   47	61	64	java/lang/Exception
    //   87	103	64	java/lang/Exception
    //   41	47	86	finally
  }

  public void a(e parame)
  {
    this.a.add(parame);
  }

  public void b()
  {
    new Thread(new d(this)).start();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.b.f
 * JD-Core Version:    0.6.2
 */