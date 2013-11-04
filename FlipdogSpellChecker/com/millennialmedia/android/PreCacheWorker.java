package com.millennialmedia.android;

import android.content.Context;
import android.text.TextUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;

class PreCacheWorker extends Thread
{
  private static boolean busy;
  private Context appContext;
  private DTOCachedVideo[] cachedVideos;
  private boolean downloadedNewVideos;
  private String noVideosToCacheURL;

  private PreCacheWorker(DTOCachedVideo[] paramArrayOfDTOCachedVideo, Context paramContext, String paramString)
  {
    this.cachedVideos = paramArrayOfDTOCachedVideo;
    this.noVideosToCacheURL = paramString;
    this.appContext = paramContext.getApplicationContext();
  }

  private void handleContent(DTOCachedVideo paramDTOCachedVideo, HttpEntity paramHttpEntity)
  {
    Header localHeader = paramHttpEntity.getContentType();
    String str;
    if (localHeader != null)
    {
      str = localHeader.getValue();
      if ((str == null) || (!str.equalsIgnoreCase("application/json")))
        break label41;
      handleJson(paramDTOCachedVideo, paramHttpEntity);
    }
    label41: 
    while ((str == null) || (!str.startsWith("video/")))
      return;
    handleVideoFile(paramDTOCachedVideo, paramHttpEntity);
  }

  // ERROR //
  private void handleJson(final DTOCachedVideo paramDTOCachedVideo, HttpEntity paramHttpEntity)
  {
    // Byte code:
    //   0: aload_2
    //   1: invokeinterface 79 1 0
    //   6: invokestatic 85	com/millennialmedia/android/HttpGetRequest:convertStreamToString	(Ljava/io/InputStream;)Ljava/lang/String;
    //   9: astore 5
    //   11: aload 5
    //   13: invokestatic 91	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   16: istore 6
    //   18: aconst_null
    //   19: astore 7
    //   21: iload 6
    //   23: ifne +18 -> 41
    //   26: new 93	com/millennialmedia/android/VideoAd
    //   29: dup
    //   30: aload 5
    //   32: invokespecial 96	com/millennialmedia/android/VideoAd:<init>	(Ljava/lang/String;)V
    //   35: astore 8
    //   37: aload 8
    //   39: astore 7
    //   41: aload 7
    //   43: ifnull +43 -> 86
    //   46: aload 7
    //   48: invokevirtual 100	com/millennialmedia/android/VideoAd:isValid	()Z
    //   51: ifeq +35 -> 86
    //   54: aload 7
    //   56: iconst_1
    //   57: putfield 104	com/millennialmedia/android/VideoAd:downloadPriority	I
    //   60: aload_0
    //   61: getfield 30	com/millennialmedia/android/PreCacheWorker:appContext	Landroid/content/Context;
    //   64: aconst_null
    //   65: aload 7
    //   67: new 106	com/millennialmedia/android/PreCacheWorker$1
    //   70: dup
    //   71: aload_0
    //   72: aload_1
    //   73: invokespecial 109	com/millennialmedia/android/PreCacheWorker$1:<init>	(Lcom/millennialmedia/android/PreCacheWorker;Lcom/millennialmedia/android/DTOCachedVideo;)V
    //   76: invokestatic 115	com/millennialmedia/android/AdCache:startDownloadTask	(Landroid/content/Context;Ljava/lang/String;Lcom/millennialmedia/android/CachedAd;Lcom/millennialmedia/android/AdCache$AdCacheTaskListener;)Z
    //   79: ifeq +32 -> 111
    //   82: aload_0
    //   83: invokevirtual 120	java/lang/Object:wait	()V
    //   86: return
    //   87: astore 4
    //   89: aload 4
    //   91: invokevirtual 123	java/lang/IllegalStateException:printStackTrace	()V
    //   94: ldc 125
    //   96: invokestatic 130	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   99: return
    //   100: astore_3
    //   101: aload_3
    //   102: invokevirtual 131	java/io/IOException:printStackTrace	()V
    //   105: ldc 125
    //   107: invokestatic 130	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   110: return
    //   111: aload_1
    //   112: getfield 136	com/millennialmedia/android/DTOCachedVideo:preCacheStartURL	Ljava/lang/String;
    //   115: invokestatic 141	com/millennialmedia/android/MMSDK$Event:logEvent	(Ljava/lang/String;)V
    //   118: aload_1
    //   119: getfield 144	com/millennialmedia/android/DTOCachedVideo:preCacheFailedURL	Ljava/lang/String;
    //   122: invokestatic 141	com/millennialmedia/android/MMSDK$Event:logEvent	(Ljava/lang/String;)V
    //   125: return
    //   126: astore 9
    //   128: aload 9
    //   130: invokevirtual 145	java/lang/InterruptedException:printStackTrace	()V
    //   133: iconst_1
    //   134: anewarray 117	java/lang/Object
    //   137: astore 10
    //   139: aload 10
    //   141: iconst_0
    //   142: aload 9
    //   144: invokevirtual 148	java/lang/InterruptedException:getMessage	()Ljava/lang/String;
    //   147: aastore
    //   148: ldc 150
    //   150: aload 10
    //   152: invokestatic 154	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   155: return
    //
    // Exception table:
    //   from	to	target	type
    //   0	18	87	java/lang/IllegalStateException
    //   26	37	87	java/lang/IllegalStateException
    //   0	18	100	java/io/IOException
    //   26	37	100	java/io/IOException
    //   54	86	126	java/lang/InterruptedException
    //   111	125	126	java/lang/InterruptedException
  }

  private void handleVideoFile(DTOCachedVideo paramDTOCachedVideo, HttpEntity paramHttpEntity)
  {
    if (!TextUtils.isEmpty(paramDTOCachedVideo.videoFileId))
    {
      MMSDK.Event.logEvent(paramDTOCachedVideo.preCacheStartURL);
      if (AdCache.downloadComponentToCache(paramDTOCachedVideo.url, paramDTOCachedVideo.videoFileId + "video.dat", this.appContext))
        MMSDK.Event.logEvent(paramDTOCachedVideo.preCacheCompleteURL);
    }
    else
    {
      return;
    }
    MMSDK.Event.logEvent(paramDTOCachedVideo.preCacheFailedURL);
  }

  static void preCacheVideos(DTOCachedVideo[] paramArrayOfDTOCachedVideo, Context paramContext, String paramString)
  {
    try
    {
      if (!busy)
      {
        busy = true;
        new PreCacheWorker(paramArrayOfDTOCachedVideo, paramContext, paramString).start();
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
    //   3: getfield 20	com/millennialmedia/android/PreCacheWorker:cachedVideos	[Lcom/millennialmedia/android/DTOCachedVideo;
    //   6: astore 4
    //   8: aload 4
    //   10: arraylength
    //   11: istore 5
    //   13: iconst_0
    //   14: istore 6
    //   16: iload 6
    //   18: iload 5
    //   20: if_icmpge +162 -> 182
    //   23: aload 4
    //   25: iload 6
    //   27: aaload
    //   28: astore 7
    //   30: new 81	com/millennialmedia/android/HttpGetRequest
    //   33: dup
    //   34: invokespecial 191	com/millennialmedia/android/HttpGetRequest:<init>	()V
    //   37: aload 7
    //   39: getfield 160	com/millennialmedia/android/DTOCachedVideo:url	Ljava/lang/String;
    //   42: invokevirtual 195	com/millennialmedia/android/HttpGetRequest:get	(Ljava/lang/String;)Lorg/apache/http/HttpResponse;
    //   45: astore 10
    //   47: aload 10
    //   49: ifnonnull +11 -> 60
    //   52: ldc 197
    //   54: invokestatic 130	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   57: goto +176 -> 233
    //   60: aload 10
    //   62: invokeinterface 203 1 0
    //   67: astore 11
    //   69: aload 11
    //   71: ifnonnull +80 -> 151
    //   74: ldc 205
    //   76: invokestatic 130	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   79: goto +154 -> 233
    //   82: astore_1
    //   83: ldc 2
    //   85: monitorenter
    //   86: iconst_0
    //   87: putstatic 182	com/millennialmedia/android/PreCacheWorker:busy	Z
    //   90: aload_0
    //   91: getfield 36	com/millennialmedia/android/PreCacheWorker:downloadedNewVideos	Z
    //   94: ifne +20 -> 114
    //   97: aload_0
    //   98: getfield 22	com/millennialmedia/android/PreCacheWorker:noVideosToCacheURL	Ljava/lang/String;
    //   101: invokestatic 91	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   104: ifne +10 -> 114
    //   107: aload_0
    //   108: getfield 22	com/millennialmedia/android/PreCacheWorker:noVideosToCacheURL	Ljava/lang/String;
    //   111: invokestatic 141	com/millennialmedia/android/MMSDK$Event:logEvent	(Ljava/lang/String;)V
    //   114: ldc 2
    //   116: monitorexit
    //   117: aload_1
    //   118: athrow
    //   119: astore_2
    //   120: aload_0
    //   121: monitorexit
    //   122: aload_2
    //   123: athrow
    //   124: astore 8
    //   126: iconst_1
    //   127: anewarray 117	java/lang/Object
    //   130: astore 9
    //   132: aload 9
    //   134: iconst_0
    //   135: aload 8
    //   137: invokevirtual 206	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   140: aastore
    //   141: ldc 208
    //   143: aload 9
    //   145: invokestatic 210	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   148: goto +85 -> 233
    //   151: aload 11
    //   153: invokeinterface 214 1 0
    //   158: lconst_0
    //   159: lcmp
    //   160: ifne +11 -> 171
    //   163: ldc 216
    //   165: invokestatic 130	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   168: goto +65 -> 233
    //   171: aload_0
    //   172: aload 7
    //   174: aload 11
    //   176: invokespecial 218	com/millennialmedia/android/PreCacheWorker:handleContent	(Lcom/millennialmedia/android/DTOCachedVideo;Lorg/apache/http/HttpEntity;)V
    //   179: goto +54 -> 233
    //   182: ldc 2
    //   184: monitorenter
    //   185: iconst_0
    //   186: putstatic 182	com/millennialmedia/android/PreCacheWorker:busy	Z
    //   189: aload_0
    //   190: getfield 36	com/millennialmedia/android/PreCacheWorker:downloadedNewVideos	Z
    //   193: ifne +20 -> 213
    //   196: aload_0
    //   197: getfield 22	com/millennialmedia/android/PreCacheWorker:noVideosToCacheURL	Ljava/lang/String;
    //   200: invokestatic 91	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   203: ifne +10 -> 213
    //   206: aload_0
    //   207: getfield 22	com/millennialmedia/android/PreCacheWorker:noVideosToCacheURL	Ljava/lang/String;
    //   210: invokestatic 141	com/millennialmedia/android/MMSDK$Event:logEvent	(Ljava/lang/String;)V
    //   213: ldc 2
    //   215: monitorexit
    //   216: aload_0
    //   217: monitorexit
    //   218: return
    //   219: astore 12
    //   221: ldc 2
    //   223: monitorexit
    //   224: aload 12
    //   226: athrow
    //   227: astore_3
    //   228: ldc 2
    //   230: monitorexit
    //   231: aload_3
    //   232: athrow
    //   233: iinc 6 1
    //   236: goto -220 -> 16
    //
    // Exception table:
    //   from	to	target	type
    //   2	13	82	finally
    //   23	30	82	finally
    //   30	47	82	finally
    //   52	57	82	finally
    //   60	69	82	finally
    //   74	79	82	finally
    //   126	148	82	finally
    //   151	168	82	finally
    //   171	179	82	finally
    //   83	86	119	finally
    //   117	119	119	finally
    //   182	185	119	finally
    //   224	227	119	finally
    //   231	233	119	finally
    //   30	47	124	java/lang/Exception
    //   52	57	124	java/lang/Exception
    //   60	69	124	java/lang/Exception
    //   185	213	219	finally
    //   213	216	219	finally
    //   221	224	219	finally
    //   86	114	227	finally
    //   114	117	227	finally
    //   228	231	227	finally
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.PreCacheWorker
 * JD-Core Version:    0.6.2
 */