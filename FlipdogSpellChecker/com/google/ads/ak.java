package com.google.ads;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ak extends aj
{
  static boolean c = false;
  private static Method d = null;
  private static Method e = null;
  private static Method f = null;
  private static Method g = null;
  private static Method h = null;
  private static String i = null;
  private static long j = 0L;

  protected ak(Context paramContext)
  {
    super(paramContext);
  }

  public static ak a(String paramString, Context paramContext)
  {
    b(paramString, paramContext);
    return new ak(paramContext);
  }

  static String a()
    throws ak.a
  {
    if (i == null)
      throw new a();
    return i;
  }

  static ArrayList<Long> a(MotionEvent paramMotionEvent, DisplayMetrics paramDisplayMetrics)
    throws ak.a
  {
    if ((g == null) || (paramMotionEvent == null))
      throw new a();
    try
    {
      ArrayList localArrayList = (ArrayList)g.invoke(null, new Object[] { paramMotionEvent, paramDisplayMetrics });
      return localArrayList;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }

  static Long b()
    throws ak.a
  {
    if (d == null)
      throw new a();
    try
    {
      Long localLong = (Long)d.invoke(null, new Object[0]);
      return localLong;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }

  private static String b(byte[] paramArrayOfByte, String paramString)
    throws ak.a
  {
    try
    {
      String str = new String(an.a(paramArrayOfByte, paramString), "UTF-8");
      return str;
    }
    catch (an.a locala)
    {
      throw new a(locala);
    }
    catch (ap localap)
    {
      throw new a(localap);
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      throw new a(localUnsupportedEncodingException);
    }
  }

  // ERROR //
  protected static void b(String paramString, Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: getstatic 34	com/google/ads/ak:c	Z
    //   6: istore_3
    //   7: iload_3
    //   8: ifne +24 -> 32
    //   11: aload_0
    //   12: putstatic 30	com/google/ads/ak:i	Ljava/lang/String;
    //   15: aload_1
    //   16: invokestatic 94	com/google/ads/ak:f	(Landroid/content/Context;)V
    //   19: invokestatic 96	com/google/ads/ak:b	()Ljava/lang/Long;
    //   22: invokevirtual 100	java/lang/Long:longValue	()J
    //   25: putstatic 32	com/google/ads/ak:j	J
    //   28: iconst_1
    //   29: putstatic 34	com/google/ads/ak:c	Z
    //   32: ldc 2
    //   34: monitorexit
    //   35: return
    //   36: astore_2
    //   37: ldc 2
    //   39: monitorexit
    //   40: aload_2
    //   41: athrow
    //   42: astore 5
    //   44: goto -12 -> 32
    //   47: astore 4
    //   49: goto -17 -> 32
    //
    // Exception table:
    //   from	to	target	type
    //   3	7	36	finally
    //   11	32	36	finally
    //   11	32	42	java/lang/UnsupportedOperationException
    //   11	32	47	com/google/ads/ak$a
  }

  static String c()
    throws ak.a
  {
    if (e == null)
      throw new a();
    try
    {
      String str = (String)e.invoke(null, new Object[0]);
      return str;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }

  static String d(Context paramContext)
    throws ak.a
  {
    if (h == null)
      throw new a();
    String str;
    try
    {
      str = (String)h.invoke(null, new Object[] { paramContext });
      if (str == null)
        throw new a();
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new a(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
    return str;
  }

  static String e(Context paramContext)
    throws ak.a
  {
    if (f == null)
      throw new a();
    try
    {
      localByteBuffer = (ByteBuffer)f.invoke(null, new Object[] { paramContext });
      if (localByteBuffer == null)
        throw new a();
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      ByteBuffer localByteBuffer;
      throw new a(localIllegalAccessException);
      String str = aq.a(localByteBuffer.array(), false);
      return str;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new a(localInvocationTargetException);
    }
  }

  private static void f(Context paramContext)
    throws ak.a
  {
    try
    {
      arrayOfByte1 = an.a(ao.a());
      arrayOfByte2 = an.a(arrayOfByte1, ao.b());
      localFile1 = paramContext.getCacheDir();
      if (localFile1 == null)
      {
        localFile1 = paramContext.getDir("dex", 0);
        if (localFile1 == null)
          throw new a();
      }
    }
    catch (ap localap)
    {
      byte[] arrayOfByte1;
      byte[] arrayOfByte2;
      File localFile1;
      throw new a(localap);
      File localFile2 = File.createTempFile("ads", ".jar", localFile1);
      FileOutputStream localFileOutputStream = new FileOutputStream(localFile2);
      localFileOutputStream.write(arrayOfByte2, 0, arrayOfByte2.length);
      localFileOutputStream.close();
      DexClassLoader localDexClassLoader = new DexClassLoader(localFile2.getAbsolutePath(), localFile1.getAbsolutePath(), null, paramContext.getClassLoader());
      Class localClass1 = localDexClassLoader.loadClass(b(arrayOfByte1, ao.c()));
      Class localClass2 = localDexClassLoader.loadClass(b(arrayOfByte1, ao.i()));
      Class localClass3 = localDexClassLoader.loadClass(b(arrayOfByte1, ao.g()));
      Class localClass4 = localDexClassLoader.loadClass(b(arrayOfByte1, ao.k()));
      Class localClass5 = localDexClassLoader.loadClass(b(arrayOfByte1, ao.e()));
      d = localClass1.getMethod(b(arrayOfByte1, ao.d()), new Class[0]);
      e = localClass2.getMethod(b(arrayOfByte1, ao.j()), new Class[0]);
      f = localClass3.getMethod(b(arrayOfByte1, ao.h()), new Class[] { Context.class });
      g = localClass4.getMethod(b(arrayOfByte1, ao.l()), new Class[] { MotionEvent.class, DisplayMetrics.class });
      h = localClass5.getMethod(b(arrayOfByte1, ao.f()), new Class[] { Context.class });
      String str = localFile2.getName();
      localFile2.delete();
      new File(localFile1, str.replace(".jar", ".dex")).delete();
      return;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      throw new a(localFileNotFoundException);
    }
    catch (IOException localIOException)
    {
      throw new a(localIOException);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new a(localClassNotFoundException);
    }
    catch (an.a locala)
    {
      throw new a(locala);
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      throw new a(localNoSuchMethodException);
    }
    catch (NullPointerException localNullPointerException)
    {
      throw new a(localNullPointerException);
    }
  }

  // ERROR //
  protected void b(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_1
    //   2: invokestatic 232	com/google/ads/ak:c	()Ljava/lang/String;
    //   5: invokevirtual 235	com/google/ads/ak:a	(ILjava/lang/String;)V
    //   8: aload_0
    //   9: iconst_2
    //   10: invokestatic 236	com/google/ads/ak:a	()Ljava/lang/String;
    //   13: invokevirtual 235	com/google/ads/ak:a	(ILjava/lang/String;)V
    //   16: aload_0
    //   17: bipush 25
    //   19: invokestatic 96	com/google/ads/ak:b	()Ljava/lang/Long;
    //   22: invokevirtual 100	java/lang/Long:longValue	()J
    //   25: invokevirtual 239	com/google/ads/ak:a	(IJ)V
    //   28: aload_0
    //   29: bipush 24
    //   31: aload_1
    //   32: invokestatic 241	com/google/ads/ak:d	(Landroid/content/Context;)Ljava/lang/String;
    //   35: invokevirtual 235	com/google/ads/ak:a	(ILjava/lang/String;)V
    //   38: return
    //   39: astore 6
    //   41: return
    //   42: astore 5
    //   44: return
    //   45: astore 4
    //   47: goto -19 -> 28
    //   50: astore_3
    //   51: goto -35 -> 16
    //   54: astore_2
    //   55: goto -47 -> 8
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	39	java/io/IOException
    //   8	16	39	java/io/IOException
    //   16	28	39	java/io/IOException
    //   28	38	39	java/io/IOException
    //   28	38	42	com/google/ads/ak$a
    //   16	28	45	com/google/ads/ak$a
    //   8	16	50	com/google/ads/ak$a
    //   0	8	54	com/google/ads/ak$a
  }

  // ERROR //
  protected void c(Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: iconst_2
    //   2: invokestatic 236	com/google/ads/ak:a	()Ljava/lang/String;
    //   5: invokevirtual 235	com/google/ads/ak:a	(ILjava/lang/String;)V
    //   8: aload_0
    //   9: iconst_1
    //   10: invokestatic 232	com/google/ads/ak:c	()Ljava/lang/String;
    //   13: invokevirtual 235	com/google/ads/ak:a	(ILjava/lang/String;)V
    //   16: invokestatic 96	com/google/ads/ak:b	()Ljava/lang/Long;
    //   19: invokevirtual 100	java/lang/Long:longValue	()J
    //   22: lstore 9
    //   24: aload_0
    //   25: bipush 25
    //   27: lload 9
    //   29: invokevirtual 239	com/google/ads/ak:a	(IJ)V
    //   32: getstatic 32	com/google/ads/ak:j	J
    //   35: lconst_0
    //   36: lcmp
    //   37: ifeq +24 -> 61
    //   40: aload_0
    //   41: bipush 17
    //   43: lload 9
    //   45: getstatic 32	com/google/ads/ak:j	J
    //   48: lsub
    //   49: invokevirtual 239	com/google/ads/ak:a	(IJ)V
    //   52: aload_0
    //   53: bipush 23
    //   55: getstatic 32	com/google/ads/ak:j	J
    //   58: invokevirtual 239	com/google/ads/ak:a	(IJ)V
    //   61: aload_0
    //   62: getfield 244	com/google/ads/ak:a	Landroid/view/MotionEvent;
    //   65: aload_0
    //   66: getfield 247	com/google/ads/ak:b	Landroid/util/DisplayMetrics;
    //   69: invokestatic 249	com/google/ads/ak:a	(Landroid/view/MotionEvent;Landroid/util/DisplayMetrics;)Ljava/util/ArrayList;
    //   72: astore 8
    //   74: aload_0
    //   75: bipush 14
    //   77: aload 8
    //   79: iconst_0
    //   80: invokevirtual 253	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   83: checkcast 71	java/lang/Long
    //   86: invokevirtual 100	java/lang/Long:longValue	()J
    //   89: invokevirtual 239	com/google/ads/ak:a	(IJ)V
    //   92: aload_0
    //   93: bipush 15
    //   95: aload 8
    //   97: iconst_1
    //   98: invokevirtual 253	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   101: checkcast 71	java/lang/Long
    //   104: invokevirtual 100	java/lang/Long:longValue	()J
    //   107: invokevirtual 239	com/google/ads/ak:a	(IJ)V
    //   110: aload 8
    //   112: invokevirtual 257	java/util/ArrayList:size	()I
    //   115: iconst_3
    //   116: if_icmplt +21 -> 137
    //   119: aload_0
    //   120: bipush 16
    //   122: aload 8
    //   124: iconst_2
    //   125: invokevirtual 253	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   128: checkcast 71	java/lang/Long
    //   131: invokevirtual 100	java/lang/Long:longValue	()J
    //   134: invokevirtual 239	com/google/ads/ak:a	(IJ)V
    //   137: aload_0
    //   138: bipush 27
    //   140: aload_1
    //   141: invokestatic 259	com/google/ads/ak:e	(Landroid/content/Context;)Ljava/lang/String;
    //   144: invokevirtual 235	com/google/ads/ak:a	(ILjava/lang/String;)V
    //   147: return
    //   148: astore 7
    //   150: return
    //   151: astore 6
    //   153: return
    //   154: astore 5
    //   156: goto -19 -> 137
    //   159: astore 4
    //   161: goto -100 -> 61
    //   164: astore_3
    //   165: goto -149 -> 16
    //   168: astore_2
    //   169: goto -161 -> 8
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	148	java/io/IOException
    //   8	16	148	java/io/IOException
    //   16	61	148	java/io/IOException
    //   61	137	148	java/io/IOException
    //   137	147	148	java/io/IOException
    //   137	147	151	com/google/ads/ak$a
    //   61	137	154	com/google/ads/ak$a
    //   16	61	159	com/google/ads/ak$a
    //   8	16	164	com/google/ads/ak$a
    //   0	8	168	com/google/ads/ak$a
  }

  static class a extends Exception
  {
    public a()
    {
    }

    public a(Throwable paramThrowable)
    {
      super();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.ak
 * JD-Core Version:    0.6.2
 */