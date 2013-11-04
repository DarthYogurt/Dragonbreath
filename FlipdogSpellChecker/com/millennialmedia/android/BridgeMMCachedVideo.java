package com.millennialmedia.android;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.json.JSONArray;

class BridgeMMCachedVideo extends MMJSObject
  implements AdCache.AdCacheTaskListener
{
  private boolean success;

  private VideoPlayerActivity getVPA()
  {
    WeakReference localWeakReference = this.contextRef;
    VideoPlayerActivity localVideoPlayerActivity = null;
    if (localWeakReference != null)
    {
      boolean bool1 = this.contextRef.get() instanceof MMActivity;
      localVideoPlayerActivity = null;
      if (bool1)
      {
        MMActivity localMMActivity = (MMActivity)this.contextRef.get();
        MMBaseActivity localMMBaseActivity = localMMActivity.getWrappedActivity();
        localVideoPlayerActivity = null;
        if (localMMBaseActivity != null)
        {
          boolean bool2 = localMMActivity.getWrappedActivity() instanceof VideoPlayerActivity;
          localVideoPlayerActivity = null;
          if (bool2)
            localVideoPlayerActivity = (VideoPlayerActivity)localMMActivity.getWrappedActivity();
        }
      }
    }
    return localVideoPlayerActivity;
  }

  public MMJSResponse availableCachedVideos(HashMap<String, String> paramHashMap)
  {
    final Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      final JSONArray localJSONArray = new JSONArray();
      AdCache.iterateCachedAds(localContext, 2, new AdCache.Iterator()
      {
        boolean callback(CachedAd paramAnonymousCachedAd)
        {
          if (((paramAnonymousCachedAd instanceof VideoAd)) && (paramAnonymousCachedAd.isOnDisk(localContext)) && (!paramAnonymousCachedAd.isExpired()))
            localJSONArray.put(paramAnonymousCachedAd.getId());
          return true;
        }
      });
      MMJSResponse localMMJSResponse = new MMJSResponse();
      localMMJSResponse.result = 1;
      localMMJSResponse.response = localJSONArray;
      return localMMJSResponse;
    }
    return null;
  }

  // ERROR //
  public MMJSResponse cacheVideo(HashMap<String, String> paramHashMap)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 18	com/millennialmedia/android/BridgeMMCachedVideo:contextRef	Ljava/lang/ref/WeakReference;
    //   6: invokevirtual 24	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   9: checkcast 36	android/content/Context
    //   12: astore_3
    //   13: aload_1
    //   14: ldc 72
    //   16: invokevirtual 77	java/util/HashMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   19: checkcast 79	java/lang/String
    //   22: astore 4
    //   24: aconst_null
    //   25: astore 5
    //   27: aload 4
    //   29: ifnull +42 -> 71
    //   32: aconst_null
    //   33: astore 5
    //   35: aload_3
    //   36: ifnull +35 -> 71
    //   39: getstatic 85	java/lang/System:out	Ljava/io/PrintStream;
    //   42: aload 4
    //   44: invokevirtual 91	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   47: new 93	com/millennialmedia/android/HttpGetRequest
    //   50: dup
    //   51: invokespecial 94	com/millennialmedia/android/HttpGetRequest:<init>	()V
    //   54: aload 4
    //   56: invokevirtual 97	com/millennialmedia/android/HttpGetRequest:get	(Ljava/lang/String;)Lorg/apache/http/HttpResponse;
    //   59: astore 7
    //   61: aload 7
    //   63: ifnonnull +13 -> 76
    //   66: ldc 99
    //   68: invokestatic 104	com/millennialmedia/android/MMSDK$Log:i	(Ljava/lang/String;)V
    //   71: aload_0
    //   72: monitorexit
    //   73: aload 5
    //   75: areturn
    //   76: aload 7
    //   78: invokeinterface 110 1 0
    //   83: astore 8
    //   85: aload 8
    //   87: ifnonnull +53 -> 140
    //   90: ldc 112
    //   92: invokestatic 115	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   95: aconst_null
    //   96: astore 5
    //   98: goto -27 -> 71
    //   101: astore_2
    //   102: aload_0
    //   103: monitorexit
    //   104: aload_2
    //   105: athrow
    //   106: astore 6
    //   108: new 117	java/lang/StringBuilder
    //   111: dup
    //   112: invokespecial 118	java/lang/StringBuilder:<init>	()V
    //   115: ldc 120
    //   117: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   120: aload 6
    //   122: invokevirtual 128	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   125: invokevirtual 124	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   128: invokevirtual 131	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   131: invokestatic 115	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   134: aconst_null
    //   135: astore 5
    //   137: goto -66 -> 71
    //   140: aload 8
    //   142: invokeinterface 137 1 0
    //   147: lconst_0
    //   148: lcmp
    //   149: ifne +14 -> 163
    //   152: ldc 139
    //   154: invokestatic 115	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   157: aconst_null
    //   158: astore 5
    //   160: goto -89 -> 71
    //   163: aload 8
    //   165: invokeinterface 143 1 0
    //   170: astore 9
    //   172: aconst_null
    //   173: astore 5
    //   175: aload 9
    //   177: ifnull -106 -> 71
    //   180: aload 9
    //   182: invokeinterface 148 1 0
    //   187: astore 10
    //   189: aconst_null
    //   190: astore 5
    //   192: aload 10
    //   194: ifnull -123 -> 71
    //   197: aload 9
    //   199: invokeinterface 148 1 0
    //   204: ldc 150
    //   206: invokevirtual 154	java/lang/String:equalsIgnoreCase	(Ljava/lang/String;)Z
    //   209: istore 11
    //   211: aconst_null
    //   212: astore 5
    //   214: iload 11
    //   216: ifeq -145 -> 71
    //   219: new 156	com/millennialmedia/android/VideoAd
    //   222: dup
    //   223: aload 8
    //   225: invokeinterface 160 1 0
    //   230: invokestatic 164	com/millennialmedia/android/HttpGetRequest:convertStreamToString	(Ljava/io/InputStream;)Ljava/lang/String;
    //   233: invokespecial 166	com/millennialmedia/android/VideoAd:<init>	(Ljava/lang/String;)V
    //   236: astore 12
    //   238: aconst_null
    //   239: astore 5
    //   241: aload 12
    //   243: ifnull -172 -> 71
    //   246: aload 12
    //   248: invokevirtual 170	com/millennialmedia/android/VideoAd:isValid	()Z
    //   251: istore 13
    //   253: aconst_null
    //   254: astore 5
    //   256: iload 13
    //   258: ifeq -187 -> 71
    //   261: aload 12
    //   263: iconst_3
    //   264: putfield 173	com/millennialmedia/android/VideoAd:downloadPriority	I
    //   267: aload_3
    //   268: aconst_null
    //   269: aload 12
    //   271: aload_0
    //   272: invokestatic 177	com/millennialmedia/android/AdCache:startDownloadTask	(Landroid/content/Context;Ljava/lang/String;Lcom/millennialmedia/android/CachedAd;Lcom/millennialmedia/android/AdCache$AdCacheTaskListener;)Z
    //   275: istore 14
    //   277: iload 14
    //   279: ifeq +138 -> 417
    //   282: aload_0
    //   283: invokevirtual 182	java/lang/Object:wait	()V
    //   286: aload_0
    //   287: getfield 184	com/millennialmedia/android/BridgeMMCachedVideo:success	Z
    //   290: ifeq +69 -> 359
    //   293: ldc 186
    //   295: iconst_1
    //   296: anewarray 179	java/lang/Object
    //   299: dup
    //   300: iconst_0
    //   301: aload 4
    //   303: aastore
    //   304: invokestatic 190	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   307: invokestatic 194	com/millennialmedia/android/MMJSResponse:responseWithSuccess	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   310: astore 19
    //   312: aload 19
    //   314: astore 5
    //   316: aload_0
    //   317: invokevirtual 197	java/lang/Object:notify	()V
    //   320: goto -249 -> 71
    //   323: astore 21
    //   325: aload 21
    //   327: invokevirtual 200	java/lang/IllegalStateException:printStackTrace	()V
    //   330: ldc 202
    //   332: invokestatic 115	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   335: aconst_null
    //   336: astore 5
    //   338: goto -267 -> 71
    //   341: astore 20
    //   343: aload 20
    //   345: invokevirtual 203	java/io/IOException:printStackTrace	()V
    //   348: ldc 202
    //   350: invokestatic 115	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   353: aconst_null
    //   354: astore 5
    //   356: goto -285 -> 71
    //   359: aload_0
    //   360: invokevirtual 197	java/lang/Object:notify	()V
    //   363: aconst_null
    //   364: astore 5
    //   366: goto -295 -> 71
    //   369: astore 17
    //   371: aload 17
    //   373: invokestatic 207	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/Throwable;)V
    //   376: iconst_1
    //   377: anewarray 179	java/lang/Object
    //   380: astore 18
    //   382: aload 18
    //   384: iconst_0
    //   385: aload 17
    //   387: invokevirtual 208	java/lang/InterruptedException:getMessage	()Ljava/lang/String;
    //   390: aastore
    //   391: ldc 210
    //   393: aload 18
    //   395: invokestatic 213	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   398: aload_0
    //   399: invokevirtual 197	java/lang/Object:notify	()V
    //   402: aconst_null
    //   403: astore 5
    //   405: goto -334 -> 71
    //   408: astore 16
    //   410: aload_0
    //   411: invokevirtual 197	java/lang/Object:notify	()V
    //   414: aload 16
    //   416: athrow
    //   417: ldc 215
    //   419: iconst_1
    //   420: anewarray 179	java/lang/Object
    //   423: dup
    //   424: iconst_0
    //   425: aload 4
    //   427: aastore
    //   428: invokestatic 190	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
    //   431: invokestatic 218	com/millennialmedia/android/MMJSResponse:responseWithError	(Ljava/lang/String;)Lcom/millennialmedia/android/MMJSResponse;
    //   434: astore 15
    //   436: aload 15
    //   438: astore 5
    //   440: goto -369 -> 71
    //
    // Exception table:
    //   from	to	target	type
    //   2	24	101	finally
    //   39	47	101	finally
    //   47	61	101	finally
    //   66	71	101	finally
    //   76	85	101	finally
    //   90	95	101	finally
    //   108	134	101	finally
    //   140	157	101	finally
    //   163	172	101	finally
    //   180	189	101	finally
    //   197	211	101	finally
    //   219	238	101	finally
    //   246	253	101	finally
    //   261	277	101	finally
    //   316	320	101	finally
    //   325	335	101	finally
    //   343	353	101	finally
    //   359	363	101	finally
    //   398	402	101	finally
    //   410	417	101	finally
    //   417	436	101	finally
    //   47	61	106	java/lang/Exception
    //   66	71	106	java/lang/Exception
    //   76	85	106	java/lang/Exception
    //   219	238	323	java/lang/IllegalStateException
    //   219	238	341	java/io/IOException
    //   282	312	369	java/lang/InterruptedException
    //   282	312	408	finally
    //   371	398	408	finally
  }

  public void downloadCompleted(CachedAd paramCachedAd, boolean paramBoolean)
  {
    try
    {
      Context localContext = (Context)this.contextRef.get();
      if ((paramBoolean) && (localContext != null))
        AdCache.save(localContext, paramCachedAd);
      this.success = paramBoolean;
      notify();
      return;
    }
    finally
    {
    }
  }

  public void downloadStart(CachedAd paramCachedAd)
  {
  }

  public MMJSResponse endVideo(HashMap<String, String> paramHashMap)
  {
    final VideoPlayerActivity localVideoPlayerActivity = getVPA();
    if (localVideoPlayerActivity != null)
      return runOnUiThreadFuture(new Callable()
      {
        public MMJSResponse call()
        {
          localVideoPlayerActivity.endVideo();
          return MMJSResponse.responseWithSuccess();
        }
      });
    return null;
  }

  public MMJSResponse pauseVideo(HashMap<String, String> paramHashMap)
  {
    final VideoPlayerActivity localVideoPlayerActivity = getVPA();
    if (localVideoPlayerActivity != null)
      return runOnUiThreadFuture(new Callable()
      {
        public MMJSResponse call()
        {
          localVideoPlayerActivity.pauseVideoByUser();
          return MMJSResponse.responseWithSuccess();
        }
      });
    return null;
  }

  public MMJSResponse playCachedVideo(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramHashMap.get("videoId");
    MMJSResponse localMMJSResponse = null;
    if (str != null)
    {
      localMMJSResponse = null;
      if (localContext != null)
      {
        VideoAd localVideoAd = (VideoAd)AdCache.load(localContext, str);
        localMMJSResponse = null;
        if (localVideoAd != null)
        {
          boolean bool = localVideoAd.canShow(localContext, null, false);
          localMMJSResponse = null;
          if (bool)
          {
            localVideoAd.show(localContext, getAdImplId((String)paramHashMap.get("PROPERTY_EXPANDING")));
            localMMJSResponse = MMJSResponse.responseWithSuccess(String.format("Playing Video(%s)", new Object[] { str }));
          }
        }
      }
    }
    return localMMJSResponse;
  }

  public MMJSResponse playVideo(HashMap<String, String> paramHashMap)
  {
    final VideoPlayerActivity localVideoPlayerActivity = getVPA();
    if (localVideoPlayerActivity != null)
      return runOnUiThreadFuture(new Callable()
      {
        public MMJSResponse call()
        {
          localVideoPlayerActivity.resumeVideo();
          return MMJSResponse.responseWithSuccess();
        }
      });
    return null;
  }

  public MMJSResponse restartVideo(HashMap<String, String> paramHashMap)
  {
    final VideoPlayerActivity localVideoPlayerActivity = getVPA();
    if (localVideoPlayerActivity != null)
      return runOnUiThreadFuture(new Callable()
      {
        public MMJSResponse call()
        {
          localVideoPlayerActivity.restartVideo();
          return MMJSResponse.responseWithSuccess();
        }
      });
    return null;
  }

  @Deprecated
  public MMJSResponse videoIdExists(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramHashMap.get("videoId");
    if ((str != null) && (localContext != null))
    {
      VideoAd localVideoAd = (VideoAd)AdCache.load(localContext, str);
      if ((localVideoAd != null) && (localVideoAd.isOnDisk(localContext)) && (!localVideoAd.isExpired()))
        return MMJSResponse.responseWithSuccess(str);
    }
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMCachedVideo
 * JD-Core Version:    0.6.2
 */