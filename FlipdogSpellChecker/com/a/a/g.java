package com.a.a;

import com.a.a.c.b;
import com.a.a.c.i;
import java.io.FilterInputStream;
import java.io.IOException;
import my.apache.http.HttpEntity;
import my.apache.http.HttpResponse;
import my.apache.http.client.methods.HttpUriRequest;

public class g extends FilterInputStream
{
  private final HttpUriRequest a;
  private final f b;

  public g(HttpUriRequest paramHttpUriRequest, HttpResponse paramHttpResponse)
    throws i
  {
    super(null);
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    if (localHttpEntity == null)
      throw new i("Didn't get entity from HttpResponse");
    try
    {
      this.in = localHttpEntity.getContent();
      this.a = paramHttpUriRequest;
      this.b = new f(paramHttpResponse, null);
      return;
    }
    catch (IOException localIOException)
    {
      throw new b(localIOException);
    }
  }

  public f a()
  {
    return this.b;
  }

  // ERROR //
  public void a(java.io.OutputStream paramOutputStream, h paramh)
    throws b, com.a.a.c.e, com.a.a.c.f
  {
    // Byte code:
    //   0: lconst_0
    //   1: lstore_3
    //   2: aload_0
    //   3: getfield 47	com/a/a/g:b	Lcom/a/a/f;
    //   6: invokevirtual 64	com/a/a/f:c	()J
    //   9: lstore 5
    //   11: new 66	java/io/BufferedOutputStream
    //   14: dup
    //   15: aload_1
    //   16: invokespecial 69	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   19: astore 7
    //   21: sipush 4096
    //   24: newarray byte
    //   26: astore 14
    //   28: lconst_0
    //   29: lstore 15
    //   31: aload_0
    //   32: aload 14
    //   34: invokevirtual 73	com/a/a/g:read	([B)I
    //   37: istore 17
    //   39: iload 17
    //   41: ifge +85 -> 126
    //   44: lload 5
    //   46: lconst_0
    //   47: lcmp
    //   48: iflt +130 -> 178
    //   51: lload_3
    //   52: lload 5
    //   54: lcmp
    //   55: ifge +123 -> 178
    //   58: new 56	com/a/a/c/e
    //   61: dup
    //   62: lload_3
    //   63: invokespecial 76	com/a/a/c/e:<init>	(J)V
    //   66: athrow
    //   67: astore 12
    //   69: aload 12
    //   71: invokevirtual 80	java/io/IOException:getMessage	()Ljava/lang/String;
    //   74: astore 13
    //   76: aload 13
    //   78: ifnull +149 -> 227
    //   81: aload 13
    //   83: ldc 82
    //   85: invokevirtual 88	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   88: ifeq +139 -> 227
    //   91: new 58	com/a/a/c/f
    //   94: dup
    //   95: invokespecial 91	com/a/a/c/f:<init>	()V
    //   98: athrow
    //   99: astore 8
    //   101: aload 7
    //   103: ifnull +8 -> 111
    //   106: aload 7
    //   108: invokevirtual 94	java/io/BufferedOutputStream:close	()V
    //   111: aload_1
    //   112: ifnull +7 -> 119
    //   115: aload_1
    //   116: invokevirtual 97	java/io/OutputStream:close	()V
    //   119: aload_0
    //   120: invokevirtual 98	com/a/a/g:close	()V
    //   123: aload 8
    //   125: athrow
    //   126: aload 7
    //   128: aload 14
    //   130: iconst_0
    //   131: iload 17
    //   133: invokevirtual 102	java/io/BufferedOutputStream:write	([BII)V
    //   136: lload_3
    //   137: iload 17
    //   139: i2l
    //   140: ladd
    //   141: lstore_3
    //   142: aload_2
    //   143: ifnull -112 -> 31
    //   146: invokestatic 107	java/lang/System:currentTimeMillis	()J
    //   149: lstore 18
    //   151: lload 18
    //   153: lload 15
    //   155: lsub
    //   156: aload_2
    //   157: invokevirtual 111	com/a/a/h:a	()J
    //   160: lcmp
    //   161: ifle -130 -> 31
    //   164: aload_2
    //   165: lload_3
    //   166: lload 5
    //   168: invokevirtual 114	com/a/a/h:a	(JJ)V
    //   171: lload 18
    //   173: lstore 15
    //   175: goto -144 -> 31
    //   178: aload 7
    //   180: invokevirtual 117	java/io/BufferedOutputStream:flush	()V
    //   183: aload_1
    //   184: invokevirtual 118	java/io/OutputStream:flush	()V
    //   187: aload_1
    //   188: instanceof 120
    //   191: ifeq +13 -> 204
    //   194: aload_1
    //   195: checkcast 120	java/io/FileOutputStream
    //   198: invokevirtual 124	java/io/FileOutputStream:getFD	()Ljava/io/FileDescriptor;
    //   201: invokevirtual 129	java/io/FileDescriptor:sync	()V
    //   204: aload 7
    //   206: ifnull +8 -> 214
    //   209: aload 7
    //   211: invokevirtual 94	java/io/BufferedOutputStream:close	()V
    //   214: aload_1
    //   215: ifnull +7 -> 222
    //   218: aload_1
    //   219: invokevirtual 97	java/io/OutputStream:close	()V
    //   222: aload_0
    //   223: invokevirtual 98	com/a/a/g:close	()V
    //   226: return
    //   227: new 56	com/a/a/c/e
    //   230: dup
    //   231: lload_3
    //   232: invokespecial 76	com/a/a/c/e:<init>	(J)V
    //   235: athrow
    //   236: astore 11
    //   238: goto -127 -> 111
    //   241: astore 10
    //   243: goto -124 -> 119
    //   246: astore 9
    //   248: goto -125 -> 123
    //   251: astore 23
    //   253: goto -39 -> 214
    //   256: astore 22
    //   258: goto -36 -> 222
    //   261: astore 21
    //   263: return
    //   264: astore 8
    //   266: aconst_null
    //   267: astore 7
    //   269: goto -168 -> 101
    //   272: astore 12
    //   274: aconst_null
    //   275: astore 7
    //   277: goto -208 -> 69
    //   280: astore 20
    //   282: goto -78 -> 204
    //
    // Exception table:
    //   from	to	target	type
    //   21	28	67	java/io/IOException
    //   31	39	67	java/io/IOException
    //   58	67	67	java/io/IOException
    //   126	136	67	java/io/IOException
    //   146	171	67	java/io/IOException
    //   178	187	67	java/io/IOException
    //   187	204	67	java/io/IOException
    //   21	28	99	finally
    //   31	39	99	finally
    //   58	67	99	finally
    //   69	76	99	finally
    //   81	99	99	finally
    //   126	136	99	finally
    //   146	171	99	finally
    //   178	187	99	finally
    //   187	204	99	finally
    //   227	236	99	finally
    //   106	111	236	java/io/IOException
    //   115	119	241	java/io/IOException
    //   119	123	246	java/io/IOException
    //   209	214	251	java/io/IOException
    //   218	222	256	java/io/IOException
    //   222	226	261	java/io/IOException
    //   11	21	264	finally
    //   11	21	272	java/io/IOException
    //   187	204	280	java/io/SyncFailedException
  }

  public void close()
    throws IOException
  {
    this.a.abort();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.g
 * JD-Core Version:    0.6.2
 */