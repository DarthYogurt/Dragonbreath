package com.b.a.a;

import com.b.a.h;
import com.b.a.j;

public abstract class a
{
  private void a(h paramh, int paramInt)
  {
    new j(paramh).g().b("version").h();
    new j(paramh).k("version").d("version", paramInt).h();
  }

  // ERROR //
  public void a(h paramh, int paramInt1, int paramInt2)
  {
    // Byte code:
    //   0: iload_2
    //   1: iload_3
    //   2: if_icmplt +4 -> 6
    //   5: return
    //   6: iconst_2
    //   7: anewarray 4	java/lang/Object
    //   10: astore 5
    //   12: aload 5
    //   14: iconst_0
    //   15: aload_0
    //   16: invokevirtual 59	java/lang/Object:getClass	()Ljava/lang/Class;
    //   19: invokevirtual 65	java/lang/Class:getPackage	()Ljava/lang/Package;
    //   22: invokevirtual 70	java/lang/Package:getName	()Ljava/lang/String;
    //   25: aastore
    //   26: aload 5
    //   28: iconst_1
    //   29: iload_2
    //   30: iconst_1
    //   31: iadd
    //   32: invokestatic 75	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   35: aastore
    //   36: ldc 77
    //   38: aload 5
    //   40: invokestatic 81	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   43: astore 6
    //   45: aload 6
    //   47: invokestatic 85	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   50: astore 8
    //   52: aload 8
    //   54: iconst_1
    //   55: anewarray 61	java/lang/Class
    //   58: dup
    //   59: iconst_0
    //   60: ldc 87
    //   62: aastore
    //   63: invokevirtual 91	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   66: iconst_1
    //   67: anewarray 4	java/lang/Object
    //   70: dup
    //   71: iconst_0
    //   72: aload_1
    //   73: aastore
    //   74: invokevirtual 97	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   77: astore 9
    //   79: aload 8
    //   81: ldc 99
    //   83: iconst_0
    //   84: anewarray 61	java/lang/Class
    //   87: invokevirtual 103	java/lang/Class:getMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   90: aload 9
    //   92: iconst_0
    //   93: anewarray 4	java/lang/Object
    //   96: invokevirtual 109	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   99: pop
    //   100: iinc 2 1
    //   103: goto -103 -> 0
    //   106: astore 4
    //   108: aload 4
    //   110: invokestatic 115	com/flipdog/commons/diagnostic/Track:it	(Ljava/lang/Throwable;)V
    //   113: new 117	java/lang/RuntimeException
    //   116: dup
    //   117: aload 4
    //   119: invokespecial 119	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   122: athrow
    //   123: astore 7
    //   125: goto -25 -> 100
    //
    // Exception table:
    //   from	to	target	type
    //   6	45	106	java/lang/Exception
    //   45	52	106	java/lang/Exception
    //   52	100	106	java/lang/Exception
    //   45	52	123	java/lang/ClassNotFoundException
  }

  public void b(h paramh, int paramInt1, int paramInt2)
  {
    try
    {
      paramh.a();
      try
      {
        a(paramh, paramInt1, paramInt2);
        a(paramh, paramInt2);
        paramh.b();
        return;
      }
      finally
      {
      }
    }
    finally
    {
      paramh.d();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.a.a
 * JD-Core Version:    0.6.2
 */