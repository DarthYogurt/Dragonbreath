package com.a.a;

import com.a.a.b.j;
import com.a.a.b.r;
import com.a.a.c.b;
import com.a.a.c.h;
import com.a.a.c.i;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;
import my.apache.http.HttpEntity;
import my.apache.http.HttpHost;
import my.apache.http.HttpRequest;
import my.apache.http.HttpResponse;
import my.apache.http.client.HttpClient;
import my.apache.http.client.entity.UrlEncodedFormEntity;
import my.apache.http.client.methods.HttpGet;
import my.apache.http.client.methods.HttpPost;
import my.apache.http.client.methods.HttpUriRequest;
import my.apache.http.message.BasicNameValuePair;
import my.apache.http.params.HttpParams;

public class m
{
  private static final DateFormat a = new SimpleDateFormat("EEE, dd MMM yyyy kk:mm:ss ZZZZZ", Locale.US);

  public static Object a(k paramk, String paramString1, String paramString2, int paramInt, String[] paramArrayOfString, j paramj)
    throws i
  {
    return a(b(paramk, paramString1, paramString2, paramInt, paramArrayOfString, paramj).b);
  }

  // ERROR //
  public static Object a(HttpResponse paramHttpResponse)
    throws i
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: invokeinterface 53 1 0
    //   8: astore 8
    //   10: aconst_null
    //   11: astore_1
    //   12: aload 8
    //   14: ifnull +211 -> 225
    //   17: new 55	java/io/BufferedReader
    //   20: dup
    //   21: new 57	java/io/InputStreamReader
    //   24: dup
    //   25: aload 8
    //   27: invokeinterface 63 1 0
    //   32: invokespecial 66	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   35: sipush 16384
    //   38: invokespecial 69	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   41: astore 7
    //   43: aload 7
    //   45: sipush 16384
    //   48: invokevirtual 73	java/io/BufferedReader:mark	(I)V
    //   51: new 75	org/json/simple/parser/JSONParser
    //   54: dup
    //   55: invokespecial 76	org/json/simple/parser/JSONParser:<init>	()V
    //   58: aload 7
    //   60: invokevirtual 80	org/json/simple/parser/JSONParser:parse	(Ljava/io/Reader;)Ljava/lang/Object;
    //   63: astore 10
    //   65: aload 10
    //   67: astore 11
    //   69: aload 7
    //   71: ifnull +8 -> 79
    //   74: aload 7
    //   76: invokevirtual 83	java/io/BufferedReader:close	()V
    //   79: aload_0
    //   80: invokeinterface 87 1 0
    //   85: invokeinterface 93 1 0
    //   90: istore 12
    //   92: iload 12
    //   94: sipush 200
    //   97: if_icmpeq +105 -> 202
    //   100: iload 12
    //   102: sipush 401
    //   105: if_icmpne +76 -> 181
    //   108: new 95	com/a/a/c/g
    //   111: dup
    //   112: invokespecial 96	com/a/a/c/g:<init>	()V
    //   115: athrow
    //   116: astore 6
    //   118: aconst_null
    //   119: astore 7
    //   121: new 98	com/a/a/c/b
    //   124: dup
    //   125: aload 6
    //   127: invokespecial 101	com/a/a/c/b:<init>	(Ljava/io/IOException;)V
    //   130: athrow
    //   131: astore_3
    //   132: aload 7
    //   134: astore_1
    //   135: aload_1
    //   136: ifnull +7 -> 143
    //   139: aload_1
    //   140: invokevirtual 83	java/io/BufferedReader:close	()V
    //   143: aload_3
    //   144: athrow
    //   145: astore 5
    //   147: aload_0
    //   148: invokestatic 106	com/a/a/c/j:a	(Lmy/apache/http/HttpResponse;)Z
    //   151: ifeq +12 -> 163
    //   154: new 103	com/a/a/c/j
    //   157: dup
    //   158: aload_0
    //   159: invokespecial 109	com/a/a/c/j:<init>	(Lmy/apache/http/HttpResponse;)V
    //   162: athrow
    //   163: new 111	com/a/a/c/h
    //   166: dup
    //   167: aload_1
    //   168: invokespecial 114	com/a/a/c/h:<init>	(Ljava/io/BufferedReader;)V
    //   171: athrow
    //   172: new 29	com/a/a/c/i
    //   175: dup
    //   176: aload_2
    //   177: invokespecial 117	com/a/a/c/i:<init>	(Ljava/lang/Throwable;)V
    //   180: athrow
    //   181: new 103	com/a/a/c/j
    //   184: dup
    //   185: aload_0
    //   186: aload 11
    //   188: invokespecial 120	com/a/a/c/j:<init>	(Lmy/apache/http/HttpResponse;Ljava/lang/Object;)V
    //   191: athrow
    //   192: astore 4
    //   194: goto -51 -> 143
    //   197: astore 13
    //   199: goto -120 -> 79
    //   202: aload 11
    //   204: areturn
    //   205: astore_2
    //   206: aload 7
    //   208: astore_1
    //   209: goto -37 -> 172
    //   212: astore 9
    //   214: aload 7
    //   216: astore_1
    //   217: goto -70 -> 147
    //   220: astore 6
    //   222: goto -101 -> 121
    //   225: aconst_null
    //   226: astore 7
    //   228: aconst_null
    //   229: astore 11
    //   231: goto -162 -> 69
    //   234: astore_3
    //   235: goto -100 -> 135
    //   238: astore_2
    //   239: aconst_null
    //   240: astore_1
    //   241: goto -69 -> 172
    //
    // Exception table:
    //   from	to	target	type
    //   2	10	116	java/io/IOException
    //   17	43	116	java/io/IOException
    //   43	65	131	finally
    //   121	131	131	finally
    //   2	10	145	org/json/simple/parser/ParseException
    //   17	43	145	org/json/simple/parser/ParseException
    //   139	143	192	java/io/IOException
    //   74	79	197	java/io/IOException
    //   43	65	205	java/lang/OutOfMemoryError
    //   43	65	212	org/json/simple/parser/ParseException
    //   43	65	220	java/io/IOException
    //   2	10	234	finally
    //   17	43	234	finally
    //   147	163	234	finally
    //   163	172	234	finally
    //   172	181	234	finally
    //   2	10	238	java/lang/OutOfMemoryError
    //   17	43	238	java/lang/OutOfMemoryError
  }

  public static String a(String paramString1, int paramInt, String paramString2, String[] paramArrayOfString)
  {
    if (!paramString2.startsWith("/"))
      paramString2 = "/" + paramString2;
    try
    {
      String str1 = URLEncoder.encode("/" + paramInt + paramString2, "UTF-8").replace("%2F", "/");
      if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
        str1 = str1 + "?" + a(paramArrayOfString);
      String str2 = str1.replace("+", "%20").replace("*", "%2A");
      return "https://" + paramString1 + ":443" + str2;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
    }
    return null;
  }

  private static String a(String[] paramArrayOfString)
  {
    if (paramArrayOfString.length % 2 != 0)
      throw new IllegalArgumentException("Params must have an even number of elements.");
    Object localObject = "";
    int i = 1;
    for (int j = 0; ; j += 2)
      try
      {
        if (j >= paramArrayOfString.length)
        {
          ((String)localObject).replace("*", "%2A");
          return localObject;
        }
        if (paramArrayOfString[(j + 1)] != null)
        {
          if (i != 0)
            i = 0;
          while (true)
          {
            localObject = localObject + URLEncoder.encode(paramArrayOfString[j], "UTF-8") + "=" + URLEncoder.encode(paramArrayOfString[(j + 1)], "UTF-8");
            break;
            String str = localObject + "&";
            localObject = str;
          }
        }
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        return null;
      }
  }

  public static Date a(String paramString)
  {
    try
    {
      Date localDate = a.parse(paramString);
      return localDate;
    }
    catch (ParseException localParseException)
    {
    }
    return null;
  }

  public static HttpResponse a(j paramj, HttpUriRequest paramHttpUriRequest)
    throws i
  {
    return a(paramj, paramHttpUriRequest, -1);
  }

  // ERROR //
  public static HttpResponse a(j paramj, HttpUriRequest paramHttpUriRequest, int paramInt)
    throws i
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 211	com/a/a/m:a	(Lcom/a/a/b/j;)Lmy/apache/http/client/HttpClient;
    //   4: astore_3
    //   5: aload_0
    //   6: aload_1
    //   7: invokeinterface 216 2 0
    //   12: iload_2
    //   13: iflt +13 -> 26
    //   16: aload_1
    //   17: invokeinterface 222 1 0
    //   22: iload_2
    //   23: invokestatic 228	my/apache/http/params/HttpConnectionParams:setSoTimeout	(Lmy/apache/http/params/HttpParams;I)V
    //   26: aconst_null
    //   27: astore 4
    //   29: iconst_0
    //   30: istore 5
    //   32: aload 4
    //   34: ifnonnull +9 -> 43
    //   37: iload 5
    //   39: iconst_5
    //   40: if_icmplt +30 -> 70
    //   43: aload 4
    //   45: ifnonnull +51 -> 96
    //   48: new 98	com/a/a/c/b
    //   51: dup
    //   52: ldc 230
    //   54: invokespecial 231	com/a/a/c/b:<init>	(Ljava/lang/String;)V
    //   57: athrow
    //   58: astore 10
    //   60: new 233	com/a/a/c/d
    //   63: dup
    //   64: aload 10
    //   66: invokespecial 236	com/a/a/c/d:<init>	(Ljavax/net/ssl/SSLException;)V
    //   69: athrow
    //   70: aload_3
    //   71: aload_1
    //   72: invokeinterface 242 2 0
    //   77: astore 12
    //   79: aload 12
    //   81: astore 4
    //   83: aload 4
    //   85: ifnonnull +79 -> 164
    //   88: aload_3
    //   89: aload_0
    //   90: invokestatic 245	com/a/a/m:a	(Lmy/apache/http/client/HttpClient;Lcom/a/a/b/j;)V
    //   93: goto +71 -> 164
    //   96: aload 4
    //   98: invokeinterface 87 1 0
    //   103: invokeinterface 93 1 0
    //   108: istore 8
    //   110: iload 8
    //   112: sipush 200
    //   115: if_icmpeq +17 -> 132
    //   118: iload 8
    //   120: sipush 206
    //   123: if_icmpeq +9 -> 132
    //   126: aload 4
    //   128: invokestatic 41	com/a/a/m:a	(Lmy/apache/http/HttpResponse;)Ljava/lang/Object;
    //   131: pop
    //   132: aload 4
    //   134: areturn
    //   135: astore 7
    //   137: new 98	com/a/a/c/b
    //   140: dup
    //   141: aload 7
    //   143: invokespecial 101	com/a/a/c/b:<init>	(Ljava/io/IOException;)V
    //   146: athrow
    //   147: astore 6
    //   149: new 29	com/a/a/c/i
    //   152: dup
    //   153: aload 6
    //   155: invokespecial 117	com/a/a/c/i:<init>	(Ljava/lang/Throwable;)V
    //   158: athrow
    //   159: astore 11
    //   161: goto -78 -> 83
    //   164: iinc 5 1
    //   167: goto -135 -> 32
    //
    // Exception table:
    //   from	to	target	type
    //   48	58	58	javax/net/ssl/SSLException
    //   70	79	58	javax/net/ssl/SSLException
    //   88	93	58	javax/net/ssl/SSLException
    //   96	110	58	javax/net/ssl/SSLException
    //   126	132	58	javax/net/ssl/SSLException
    //   48	58	135	java/io/IOException
    //   70	79	135	java/io/IOException
    //   88	93	135	java/io/IOException
    //   96	110	135	java/io/IOException
    //   126	132	135	java/io/IOException
    //   48	58	147	java/lang/OutOfMemoryError
    //   70	79	147	java/lang/OutOfMemoryError
    //   88	93	147	java/lang/OutOfMemoryError
    //   96	110	147	java/lang/OutOfMemoryError
    //   126	132	147	java/lang/OutOfMemoryError
    //   70	79	159	java/lang/NullPointerException
  }

  private static HttpClient a(j paramj)
  {
    try
    {
      HttpClient localHttpClient = paramj.i();
      a(localHttpClient, paramj);
      return localHttpClient;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private static void a(HttpClient paramHttpClient, j paramj)
  {
    r localr = paramj.h();
    if ((localr != null) && (localr.a != null) && (!localr.a.equals("")))
    {
      if (localr.b < 0);
      for (HttpHost localHttpHost = new HttpHost(localr.a); ; localHttpHost = new HttpHost(localr.a, localr.b))
      {
        paramHttpClient.getParams().setParameter("http.route.default-proxy", localHttpHost);
        return;
      }
    }
    paramHttpClient.getParams().removeParameter("http.route.default-proxy");
  }

  public static d b(k paramk, String paramString1, String paramString2, int paramInt, String[] paramArrayOfString, j paramj)
    throws i
  {
    Object localObject;
    if (paramk == k.a)
      localObject = new HttpGet(a(paramString1, paramInt, paramString2, paramArrayOfString));
    while (true)
    {
      paramj.a((HttpRequest)localObject);
      return new d((HttpUriRequest)localObject, a(paramj, (HttpUriRequest)localObject));
      HttpPost localHttpPost = new HttpPost(a(paramString1, paramInt, paramString2, null));
      ArrayList localArrayList;
      int i;
      if ((paramArrayOfString != null) && (paramArrayOfString.length >= 2))
      {
        if (paramArrayOfString.length % 2 != 0)
          throw new IllegalArgumentException("Params must have an even number of elements.");
        localArrayList = new ArrayList();
        i = 0;
        if (i < paramArrayOfString.length)
          break label139;
      }
      try
      {
        localHttpPost.setEntity(new UrlEncodedFormEntity(localArrayList, "UTF-8"));
        localObject = localHttpPost;
        continue;
        label139: if (paramArrayOfString[(i + 1)] != null)
          localArrayList.add(new BasicNameValuePair(paramArrayOfString[i], paramArrayOfString[(i + 1)]));
        i += 2;
      }
      catch (UnsupportedEncodingException localUnsupportedEncodingException)
      {
        throw new i(localUnsupportedEncodingException);
      }
    }
  }

  public static Map<String, String> b(HttpResponse paramHttpResponse)
    throws i
  {
    HttpEntity localHttpEntity = paramHttpResponse.getEntity();
    if (localHttpEntity == null)
      throw new h("Bad response from Dropbox.");
    while (true)
    {
      Scanner localScanner;
      HashMap localHashMap;
      try
      {
        localScanner = new Scanner(localHttpEntity.getContent()).useDelimiter("&");
        localHashMap = new HashMap();
        if (!localScanner.hasNext())
          return localHashMap;
      }
      catch (IOException localIOException)
      {
        throw new b(localIOException);
      }
      String[] arrayOfString = localScanner.next().split("=");
      if (arrayOfString.length != 2)
        throw new h("Bad query string from Dropbox.");
      localHashMap.put(arrayOfString[0], arrayOfString[1]);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.m
 * JD-Core Version:    0.6.2
 */