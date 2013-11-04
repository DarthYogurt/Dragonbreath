package com.millennialmedia.android;

import android.text.TextUtils;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

class HttpGetRequest
{
  private HttpClient client = new DefaultHttpClient();
  private HttpGet getRequest = new HttpGet();

  // ERROR //
  static String convertStreamToString(java.io.InputStream paramInputStream)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_1
    //   2: aload_0
    //   3: ifnonnull +13 -> 16
    //   6: new 26	java/io/IOException
    //   9: dup
    //   10: ldc 30
    //   12: invokespecial 33	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   15: athrow
    //   16: new 35	java/io/BufferedReader
    //   19: dup
    //   20: new 37	java/io/InputStreamReader
    //   23: dup
    //   24: aload_0
    //   25: invokespecial 40	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   28: sipush 4096
    //   31: invokespecial 43	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   34: astore_2
    //   35: new 45	java/lang/StringBuilder
    //   38: dup
    //   39: invokespecial 46	java/lang/StringBuilder:<init>	()V
    //   42: astore_3
    //   43: aload_2
    //   44: invokevirtual 50	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   47: astore 7
    //   49: aload 7
    //   51: ifnull +63 -> 114
    //   54: aload_3
    //   55: new 45	java/lang/StringBuilder
    //   58: dup
    //   59: invokespecial 46	java/lang/StringBuilder:<init>	()V
    //   62: aload 7
    //   64: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   67: ldc 56
    //   69: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   72: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   75: invokevirtual 54	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   78: pop
    //   79: goto -36 -> 43
    //   82: astore 6
    //   84: aload_2
    //   85: astore_1
    //   86: aload 6
    //   88: invokestatic 65	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/Throwable;)V
    //   91: new 26	java/io/IOException
    //   94: dup
    //   95: ldc 67
    //   97: invokespecial 33	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   100: athrow
    //   101: astore 4
    //   103: aload_1
    //   104: ifnull +7 -> 111
    //   107: aload_1
    //   108: invokevirtual 70	java/io/BufferedReader:close	()V
    //   111: aload 4
    //   113: athrow
    //   114: aload_2
    //   115: ifnull +7 -> 122
    //   118: aload_2
    //   119: invokevirtual 70	java/io/BufferedReader:close	()V
    //   122: aload_3
    //   123: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   126: areturn
    //   127: astore 9
    //   129: aload 9
    //   131: invokestatic 65	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/Throwable;)V
    //   134: goto -12 -> 122
    //   137: astore 5
    //   139: aload 5
    //   141: invokestatic 65	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/Throwable;)V
    //   144: goto -33 -> 111
    //   147: astore 4
    //   149: aload_2
    //   150: astore_1
    //   151: goto -48 -> 103
    //   154: astore 6
    //   156: aconst_null
    //   157: astore_1
    //   158: goto -72 -> 86
    //
    // Exception table:
    //   from	to	target	type
    //   35	43	82	java/lang/OutOfMemoryError
    //   43	49	82	java/lang/OutOfMemoryError
    //   54	79	82	java/lang/OutOfMemoryError
    //   16	35	101	finally
    //   86	101	101	finally
    //   118	122	127	java/io/IOException
    //   107	111	137	java/io/IOException
    //   35	43	147	finally
    //   43	49	147	finally
    //   54	79	147	finally
    //   16	35	154	java/lang/OutOfMemoryError
  }

  static void log(String[] paramArrayOfString)
  {
    if ((paramArrayOfString != null) && (paramArrayOfString.length > 0))
      Utils.ThreadUtils.execute(new Runnable()
      {
        public void run()
        {
          String[] arrayOfString = this.val$urls;
          int i = arrayOfString.length;
          int j = 0;
          while (true)
            if (j < i)
            {
              String str = arrayOfString[j];
              MMSDK.Log.v("Logging event to: %s", new Object[] { str });
              try
              {
                new HttpGetRequest().get(str);
                j++;
              }
              catch (Exception localException)
              {
                while (true)
                  MMSDK.Log.v(localException.getMessage());
              }
            }
        }
      });
  }

  HttpResponse get(String paramString)
    throws Exception
  {
    boolean bool = TextUtils.isEmpty(paramString);
    Object localObject = null;
    if (!bool);
    try
    {
      this.getRequest.setURI(new URI(paramString));
      HttpResponse localHttpResponse = this.client.execute(this.getRequest);
      localObject = localHttpResponse;
      return localObject;
    }
    catch (OutOfMemoryError localOutOfMemoryError)
    {
      MMSDK.Log.e("Out of memory!");
    }
    return null;
  }

  void trackConversion(String paramString, boolean paramBoolean, long paramLong, TreeMap<String, String> paramTreeMap)
    throws Exception
  {
    if (paramBoolean);
    StringBuilder localStringBuilder;
    for (int i = 1; ; i = 0)
      try
      {
        localStringBuilder = new StringBuilder("http://cvt.mydas.mobi/handleConversion?firstlaunch=" + i);
        if (paramString != null)
          localStringBuilder.append("&goalId=" + paramString);
        if (paramLong > 0L)
          localStringBuilder.append("&installtime=" + paramLong / 1000L);
        if (paramTreeMap == null)
          break;
        Iterator localIterator = paramTreeMap.entrySet().iterator();
        while (localIterator.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)localIterator.next();
          Object[] arrayOfObject4 = new Object[2];
          arrayOfObject4[0] = localEntry.getKey();
          arrayOfObject4[1] = URLEncoder.encode((String)localEntry.getValue(), "UTF-8");
          localStringBuilder.append(String.format("&%s=%s", arrayOfObject4));
        }
      }
      catch (IOException localIOException)
      {
        Object[] arrayOfObject1 = new Object[1];
        arrayOfObject1[0] = localIOException.getMessage();
        MMSDK.Log.e("Conversion tracking error: %s", arrayOfObject1);
        return;
      }
    String str = localStringBuilder.toString();
    MMSDK.Log.d("Sending conversion tracker report: %s", new Object[] { str });
    this.getRequest.setURI(new URI(str));
    HttpResponse localHttpResponse = this.client.execute(this.getRequest);
    if (localHttpResponse.getStatusLine().getStatusCode() == 200)
    {
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Integer.valueOf(localHttpResponse.getStatusLine().getStatusCode());
      MMSDK.Log.v("Successful conversion tracking event: %d", arrayOfObject3);
      return;
    }
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Integer.valueOf(localHttpResponse.getStatusLine().getStatusCode());
    MMSDK.Log.e("Conversion tracking error: %d", arrayOfObject2);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.HttpGetRequest
 * JD-Core Version:    0.6.2
 */