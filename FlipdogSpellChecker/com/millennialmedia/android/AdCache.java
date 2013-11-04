package com.millennialmedia.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Environment;
import java.io.File;
import java.io.FileFilter;
import java.io.ObjectInputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

final class AdCache
{
  private static final String CACHED_AD_FILE = "ad.dat";
  private static final String CACHE_INCOMPLETE_PREFIX = "incompleteDownload_";
  private static final String CACHE_PREFIX = "nextCachedAd_";
  private static final String CACHE_PREFIX_APID = "nextCachedAd_apids";
  private static final String LOCK_FILE = "ad.lock";
  static final int PRIORITY_FETCH = 3;
  static final int PRIORITY_PRECACHE = 1;
  static final int PRIORITY_REFRESH = 2;
  static final String PRIVATE_CACHE_DIR = ".mmsyscache";
  private static Set<String> apidListSet;
  private static String cachedVideoList;
  private static boolean cachedVideoListLoaded;
  private static Set<String> cachedVideoSet;
  private static Map<String, String> incompleteDownloadHashMap;
  private static boolean incompleteDownloadHashMapLoaded;
  static boolean isExternalEnabled = true;
  private static Map<String, String> nextCachedAdHashMap;
  private static boolean nextCachedAdHashMapLoaded;

  static void cachedVideoWasAdded(Context paramContext, String paramString)
  {
    if (paramString != null);
    try
    {
      if (!cachedVideoListLoaded)
        getCachedVideoList(paramContext);
      if (cachedVideoSet == null)
        cachedVideoSet = new HashSet();
      cachedVideoSet.add(paramString);
      cachedVideoList = null;
      return;
    }
    finally
    {
    }
  }

  static void cachedVideoWasRemoved(Context paramContext, String paramString)
  {
    if (paramString != null);
    try
    {
      if (!cachedVideoListLoaded)
        getCachedVideoList(paramContext);
      if (cachedVideoSet != null)
      {
        cachedVideoSet.remove(paramString);
        cachedVideoList = null;
      }
      return;
    }
    finally
    {
    }
  }

  static void cleanCache(Context paramContext)
  {
    Utils.ThreadUtils.execute(new Runnable()
    {
      public void run()
      {
        MMSDK.Log.d("AdCache");
        AdCache.cleanUpExpiredAds(this.val$context);
        AdCache.cleanupCache(this.val$context);
      }
    });
  }

  static void cleanUpExpiredAds(Context paramContext)
  {
    iterateCachedAds(paramContext, 1, new Iterator()
    {
      boolean callback(String paramAnonymousString1, int paramAnonymousInt, Date paramAnonymousDate, String paramAnonymousString2, long paramAnonymousLong, ObjectInputStream paramAnonymousObjectInputStream)
      {
        if ((paramAnonymousDate != null) && (paramAnonymousDate.getTime() <= System.currentTimeMillis()));
        try
        {
          CachedAd localCachedAd = (CachedAd)paramAnonymousObjectInputStream.readObject();
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = localCachedAd.getId();
          MMSDK.Log.d("Deleting expired ad %s.", arrayOfObject);
          localCachedAd.delete(this.val$context);
          return true;
        }
        catch (Exception localException)
        {
          MMSDK.Log.d("There was a problem reading the cached ad %s.", new Object[] { paramAnonymousString1 });
          MMSDK.Log.d(localException);
        }
        return true;
      }
    });
  }

  static void cleanupCache(Context paramContext)
  {
    cleanupInternalCache(paramContext);
    if (isExternalStorageAvailable(paramContext))
      cleanupExternalCache(paramContext);
  }

  static void cleanupDirectory(File paramFile, long paramLong)
  {
    File[] arrayOfFile = paramFile.listFiles();
    int i = arrayOfFile.length;
    int j = 0;
    if (j < i)
    {
      File localFile = arrayOfFile[j];
      if (localFile.isFile())
        if (System.currentTimeMillis() - localFile.lastModified() > paramLong)
          localFile.delete();
      while (true)
      {
        j++;
        break;
        if (localFile.isDirectory())
          try
          {
            cleanupDirectory(localFile, paramLong);
            if (localFile.list().length == 0)
              localFile.delete();
          }
          catch (SecurityException localSecurityException)
          {
          }
      }
    }
  }

  private static void cleanupExternalCache(Context paramContext)
  {
    File localFile = getExternalCacheDirectory(paramContext);
    if (localFile == null);
    while ((!localFile.exists()) || (!localFile.isDirectory()))
      return;
    cleanupDirectory(localFile, HandShake.sharedHandShake(paramContext).creativeCacheTimeout);
  }

  private static void cleanupInternalCache(Context paramContext)
  {
    File localFile = getInternalCacheDirectory(paramContext);
    if (localFile == null);
    while ((!localFile.exists()) || (!localFile.isDirectory()))
      return;
    cleanupDirectory(localFile, HandShake.sharedHandShake(paramContext).creativeCacheTimeout);
  }

  static boolean deleteFile(Context paramContext, String paramString)
  {
    File localFile = getCachedAdFile(paramContext, paramString);
    if (localFile != null)
      return localFile.delete();
    return false;
  }

  // ERROR //
  static boolean downloadComponent(String paramString1, String paramString2, File paramFile, Context paramContext)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 180	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   4: ifeq +14 -> 18
    //   7: ldc 182
    //   9: invokestatic 188	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   12: iconst_0
    //   13: istore 22
    //   15: iload 22
    //   17: ireturn
    //   18: new 113	java/io/File
    //   21: dup
    //   22: aload_2
    //   23: aload_1
    //   24: invokespecial 191	java/io/File:<init>	(Ljava/io/File;Ljava/lang/String;)V
    //   27: astore 4
    //   29: ldc 193
    //   31: iconst_2
    //   32: anewarray 4	java/lang/Object
    //   35: dup
    //   36: iconst_0
    //   37: aload_1
    //   38: aastore
    //   39: dup
    //   40: iconst_1
    //   41: aload_0
    //   42: aastore
    //   43: invokestatic 197	com/millennialmedia/android/MMSDK$Log:v	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   46: aload 4
    //   48: invokevirtual 149	java/io/File:exists	()Z
    //   51: ifeq +37 -> 88
    //   54: aload 4
    //   56: invokevirtual 200	java/io/File:length	()J
    //   59: lconst_0
    //   60: lcmp
    //   61: ifle +27 -> 88
    //   64: new 202	java/lang/StringBuilder
    //   67: dup
    //   68: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   71: aload_1
    //   72: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: ldc 209
    //   77: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   80: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   83: invokestatic 215	com/millennialmedia/android/MMSDK$Log:v	(Ljava/lang/String;)V
    //   86: iconst_1
    //   87: ireturn
    //   88: aconst_null
    //   89: astore 5
    //   91: aconst_null
    //   92: astore 6
    //   94: ldc2_w 216
    //   97: lstore 7
    //   99: new 219	java/net/URL
    //   102: dup
    //   103: aload_0
    //   104: invokespecial 221	java/net/URL:<init>	(Ljava/lang/String;)V
    //   107: astore 9
    //   109: iconst_1
    //   110: invokestatic 227	java/net/HttpURLConnection:setFollowRedirects	(Z)V
    //   113: aload 9
    //   115: invokevirtual 231	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   118: checkcast 223	java/net/HttpURLConnection
    //   121: astore 20
    //   123: aload 20
    //   125: sipush 30000
    //   128: invokevirtual 235	java/net/HttpURLConnection:setConnectTimeout	(I)V
    //   131: aload 20
    //   133: ldc 237
    //   135: invokevirtual 240	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
    //   138: aload 20
    //   140: invokevirtual 243	java/net/HttpURLConnection:connect	()V
    //   143: aload 20
    //   145: invokevirtual 247	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   148: astore 5
    //   150: aload 20
    //   152: ldc 249
    //   154: invokevirtual 253	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   157: astore 21
    //   159: aconst_null
    //   160: astore 6
    //   162: aload 21
    //   164: ifnull +10 -> 174
    //   167: aload 21
    //   169: invokestatic 259	java/lang/Long:parseLong	(Ljava/lang/String;)J
    //   172: lstore 7
    //   174: aload 5
    //   176: ifnonnull +78 -> 254
    //   179: ldc_w 261
    //   182: iconst_1
    //   183: anewarray 4	java/lang/Object
    //   186: dup
    //   187: iconst_0
    //   188: aload_1
    //   189: aastore
    //   190: invokestatic 264	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   193: aload 5
    //   195: ifnull +8 -> 203
    //   198: aload 5
    //   200: invokevirtual 269	java/io/InputStream:close	()V
    //   203: iconst_0
    //   204: istore 22
    //   206: iconst_0
    //   207: ifeq -192 -> 15
    //   210: aconst_null
    //   211: invokevirtual 272	java/io/FileOutputStream:close	()V
    //   214: iconst_0
    //   215: ireturn
    //   216: astore 23
    //   218: iconst_1
    //   219: anewarray 4	java/lang/Object
    //   222: astore 24
    //   224: aload 24
    //   226: iconst_0
    //   227: aload 23
    //   229: invokevirtual 275	java/io/IOException:getMessage	()Ljava/lang/String;
    //   232: aastore
    //   233: ldc_w 277
    //   236: aload 24
    //   238: invokestatic 264	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   241: aload 4
    //   243: ifnull +9 -> 252
    //   246: aload 4
    //   248: invokevirtual 133	java/io/File:delete	()Z
    //   251: pop
    //   252: iconst_0
    //   253: ireturn
    //   254: aload_3
    //   255: aload_3
    //   256: invokestatic 280	com/millennialmedia/android/AdCache:getCacheDirectory	(Landroid/content/Context;)Ljava/io/File;
    //   259: invokestatic 284	com/millennialmedia/android/AdCache:isInternalDir	(Landroid/content/Context;Ljava/io/File;)Z
    //   262: istore 26
    //   264: aconst_null
    //   265: astore 6
    //   267: iload 26
    //   269: ifeq +112 -> 381
    //   272: aload_3
    //   273: aload 4
    //   275: invokevirtual 287	java/io/File:getName	()Ljava/lang/String;
    //   278: iconst_1
    //   279: invokevirtual 293	android/content/Context:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   282: astore 6
    //   284: sipush 4096
    //   287: newarray byte
    //   289: astore 27
    //   291: aload 5
    //   293: aload 27
    //   295: invokevirtual 297	java/io/InputStream:read	([B)I
    //   298: istore 28
    //   300: iload 28
    //   302: ifle +97 -> 399
    //   305: aload 6
    //   307: aload 27
    //   309: iconst_0
    //   310: iload 28
    //   312: invokevirtual 301	java/io/FileOutputStream:write	([BII)V
    //   315: goto -24 -> 291
    //   318: astore 14
    //   320: iconst_2
    //   321: anewarray 4	java/lang/Object
    //   324: astore 15
    //   326: aload 15
    //   328: iconst_0
    //   329: aload_1
    //   330: aastore
    //   331: aload 15
    //   333: iconst_1
    //   334: aload 14
    //   336: invokevirtual 302	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   339: aastore
    //   340: ldc_w 304
    //   343: aload 15
    //   345: invokestatic 264	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   348: aload 5
    //   350: ifnull +8 -> 358
    //   353: aload 5
    //   355: invokevirtual 269	java/io/InputStream:close	()V
    //   358: aload 6
    //   360: ifnull +8 -> 368
    //   363: aload 6
    //   365: invokevirtual 272	java/io/FileOutputStream:close	()V
    //   368: aload 4
    //   370: ifnull +9 -> 379
    //   373: aload 4
    //   375: invokevirtual 133	java/io/File:delete	()Z
    //   378: pop
    //   379: iconst_0
    //   380: ireturn
    //   381: new 271	java/io/FileOutputStream
    //   384: dup
    //   385: aload 4
    //   387: invokespecial 307	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   390: astore 39
    //   392: aload 39
    //   394: astore 6
    //   396: goto -112 -> 284
    //   399: aload 4
    //   401: ifnull +97 -> 498
    //   404: aload 4
    //   406: invokevirtual 200	java/io/File:length	()J
    //   409: lstore 34
    //   411: lload 34
    //   413: lload 7
    //   415: lcmp
    //   416: ifeq +12 -> 428
    //   419: lload 7
    //   421: ldc2_w 216
    //   424: lcmp
    //   425: ifne +67 -> 492
    //   428: iconst_1
    //   429: istore 22
    //   431: aload 5
    //   433: ifnull +8 -> 441
    //   436: aload 5
    //   438: invokevirtual 269	java/io/InputStream:close	()V
    //   441: aload 6
    //   443: ifnull -428 -> 15
    //   446: aload 6
    //   448: invokevirtual 272	java/io/FileOutputStream:close	()V
    //   451: iload 22
    //   453: ireturn
    //   454: astore 36
    //   456: iconst_1
    //   457: anewarray 4	java/lang/Object
    //   460: astore 37
    //   462: aload 37
    //   464: iconst_0
    //   465: aload 36
    //   467: invokevirtual 275	java/io/IOException:getMessage	()Ljava/lang/String;
    //   470: aastore
    //   471: ldc_w 277
    //   474: aload 37
    //   476: invokestatic 264	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   479: aload 4
    //   481: ifnull +9 -> 490
    //   484: aload 4
    //   486: invokevirtual 133	java/io/File:delete	()Z
    //   489: pop
    //   490: iconst_0
    //   491: ireturn
    //   492: ldc_w 309
    //   495: invokestatic 311	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;)V
    //   498: aload 5
    //   500: ifnull +8 -> 508
    //   503: aload 5
    //   505: invokevirtual 269	java/io/InputStream:close	()V
    //   508: aload 6
    //   510: ifnull -142 -> 368
    //   513: aload 6
    //   515: invokevirtual 272	java/io/FileOutputStream:close	()V
    //   518: goto -150 -> 368
    //   521: astore 29
    //   523: iconst_1
    //   524: anewarray 4	java/lang/Object
    //   527: astore 30
    //   529: aload 30
    //   531: iconst_0
    //   532: aload 29
    //   534: invokevirtual 275	java/io/IOException:getMessage	()Ljava/lang/String;
    //   537: aastore
    //   538: ldc_w 277
    //   541: aload 30
    //   543: invokestatic 264	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   546: aload 4
    //   548: ifnull +9 -> 557
    //   551: aload 4
    //   553: invokevirtual 133	java/io/File:delete	()Z
    //   556: pop
    //   557: iconst_0
    //   558: ireturn
    //   559: astore 32
    //   561: iconst_2
    //   562: anewarray 4	java/lang/Object
    //   565: astore 33
    //   567: aload 33
    //   569: iconst_0
    //   570: aload_1
    //   571: aastore
    //   572: aload 33
    //   574: iconst_1
    //   575: aload 32
    //   577: invokevirtual 312	java/lang/SecurityException:getMessage	()Ljava/lang/String;
    //   580: aastore
    //   581: ldc_w 304
    //   584: aload 33
    //   586: invokestatic 264	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   589: goto -91 -> 498
    //   592: astore 10
    //   594: aload 5
    //   596: ifnull +8 -> 604
    //   599: aload 5
    //   601: invokevirtual 269	java/io/InputStream:close	()V
    //   604: aload 6
    //   606: ifnull +8 -> 614
    //   609: aload 6
    //   611: invokevirtual 272	java/io/FileOutputStream:close	()V
    //   614: aload 10
    //   616: athrow
    //   617: astore 16
    //   619: iconst_1
    //   620: anewarray 4	java/lang/Object
    //   623: astore 17
    //   625: aload 17
    //   627: iconst_0
    //   628: aload 16
    //   630: invokevirtual 275	java/io/IOException:getMessage	()Ljava/lang/String;
    //   633: aastore
    //   634: ldc_w 277
    //   637: aload 17
    //   639: invokestatic 264	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   642: aload 4
    //   644: ifnull +9 -> 653
    //   647: aload 4
    //   649: invokevirtual 133	java/io/File:delete	()Z
    //   652: pop
    //   653: iconst_0
    //   654: ireturn
    //   655: astore 11
    //   657: iconst_1
    //   658: anewarray 4	java/lang/Object
    //   661: astore 12
    //   663: aload 12
    //   665: iconst_0
    //   666: aload 11
    //   668: invokevirtual 275	java/io/IOException:getMessage	()Ljava/lang/String;
    //   671: aastore
    //   672: ldc_w 277
    //   675: aload 12
    //   677: invokestatic 264	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   680: aload 4
    //   682: ifnull +9 -> 691
    //   685: aload 4
    //   687: invokevirtual 133	java/io/File:delete	()Z
    //   690: pop
    //   691: iconst_0
    //   692: ireturn
    //
    // Exception table:
    //   from	to	target	type
    //   198	203	216	java/io/IOException
    //   210	214	216	java/io/IOException
    //   99	159	318	java/lang/Exception
    //   167	174	318	java/lang/Exception
    //   179	193	318	java/lang/Exception
    //   254	264	318	java/lang/Exception
    //   272	284	318	java/lang/Exception
    //   284	291	318	java/lang/Exception
    //   291	300	318	java/lang/Exception
    //   305	315	318	java/lang/Exception
    //   381	392	318	java/lang/Exception
    //   404	411	318	java/lang/Exception
    //   492	498	318	java/lang/Exception
    //   561	589	318	java/lang/Exception
    //   436	441	454	java/io/IOException
    //   446	451	454	java/io/IOException
    //   503	508	521	java/io/IOException
    //   513	518	521	java/io/IOException
    //   404	411	559	java/lang/SecurityException
    //   492	498	559	java/lang/SecurityException
    //   99	159	592	finally
    //   167	174	592	finally
    //   179	193	592	finally
    //   254	264	592	finally
    //   272	284	592	finally
    //   284	291	592	finally
    //   291	300	592	finally
    //   305	315	592	finally
    //   320	348	592	finally
    //   381	392	592	finally
    //   404	411	592	finally
    //   492	498	592	finally
    //   561	589	592	finally
    //   353	358	617	java/io/IOException
    //   363	368	617	java/io/IOException
    //   599	604	655	java/io/IOException
    //   609	614	655	java/io/IOException
  }

  static boolean downloadComponentToCache(String paramString1, String paramString2, Context paramContext)
  {
    return downloadComponent(paramString1, paramString2, getCacheDirectory(paramContext), paramContext);
  }

  static File getCacheDirectory(Context paramContext)
  {
    if (isExternalStorageAvailable(paramContext))
      return getExternalCacheDirectory(paramContext);
    return getInternalCacheDirectory(paramContext);
  }

  private static File getCachedAdFile(Context paramContext, String paramString)
  {
    String str = paramString + "ad.dat";
    boolean bool1 = isExternalStorageAvailable(paramContext);
    File localFile1 = getCacheDirectory(paramContext);
    File localFile2 = null;
    if (localFile1 != null);
    try
    {
      boolean bool3 = localFile1.isDirectory();
      localFile2 = null;
      if (bool3)
        localFile2 = new File(localFile1, str);
      if (((localFile2 == null) || (!localFile2.exists())) && (!bool1))
      {
        File localFile3 = paramContext.getFilesDir();
        if (localFile3 != null)
        {
          File localFile4 = new File(localFile3, ".mmsyscache" + File.separator + str);
          if (localFile4.exists())
          {
            boolean bool2 = localFile4.isFile();
            if (bool2)
              return localFile4;
          }
        }
      }
    }
    catch (Exception localException)
    {
      MMSDK.Log.e(localException);
      return null;
    }
    return localFile2;
  }

  static String getCachedVideoList(Context paramContext)
  {
    StringBuilder localStringBuilder;
    while (true)
    {
      String str2;
      try
      {
        if (cachedVideoList != null)
          break label172;
        if (!cachedVideoListLoaded)
        {
          final HashSet localHashSet = new HashSet();
          iterateCachedAds(paramContext, 2, new Iterator()
          {
            boolean callback(CachedAd paramAnonymousCachedAd)
            {
              if ((paramAnonymousCachedAd.acid != null) && (paramAnonymousCachedAd.getType() == 1) && (paramAnonymousCachedAd.isOnDisk(this.val$context)))
                localHashSet.add(paramAnonymousCachedAd.acid);
              return true;
            }
          });
          cachedVideoSet = localHashSet;
          cachedVideoListLoaded = true;
        }
        if ((cachedVideoSet == null) || (cachedVideoSet.size() <= 0))
          break label172;
        localStringBuilder = new StringBuilder();
        Iterator localIterator = cachedVideoSet.iterator();
        if (!localIterator.hasNext())
          break;
        str2 = (String)localIterator.next();
        if (localStringBuilder.length() > 0)
        {
          localStringBuilder.append("," + (String)str2);
          continue;
        }
      }
      finally
      {
      }
      localStringBuilder.append((String)str2);
    }
    cachedVideoList = localStringBuilder.toString();
    label172: String str1 = cachedVideoList;
    return str1;
  }

  static File getDownloadFile(Context paramContext, String paramString)
  {
    File localFile1 = getCacheDirectory(paramContext);
    File localFile2 = null;
    if (localFile1 != null)
      localFile2 = new File(localFile1, paramString);
    return localFile2;
  }

  private static File getExternalCacheDirectory(Context paramContext)
  {
    File localFile1 = Environment.getExternalStorageDirectory();
    File localFile2 = null;
    if (localFile1 != null)
    {
      localFile2 = new File(localFile1, ".mmsyscache");
      if ((!localFile2.exists()) && (!localFile2.mkdirs()))
        localFile2 = null;
    }
    return localFile2;
  }

  static String getIncompleteDownload(Context paramContext, String paramString)
  {
    try
    {
      if (!incompleteDownloadHashMapLoaded)
        loadIncompleteDownloadHashMap(paramContext);
      if (paramString == null);
      for (String str = null; ; str = (String)incompleteDownloadHashMap.get(paramString))
        return str;
    }
    finally
    {
    }
  }

  private static File getInternalCacheDirectory(Context paramContext)
  {
    File localFile = paramContext.getFilesDir();
    if ((localFile != null) && (!localFile.exists()) && (!localFile.mkdirs()))
      localFile = null;
    return localFile;
  }

  static String getNextCachedAd(Context paramContext, String paramString)
  {
    try
    {
      if (!nextCachedAdHashMapLoaded)
        loadNextCachedAdHashMap(paramContext);
      if (paramString == null);
      for (String str = null; ; str = (String)nextCachedAdHashMap.get(paramString))
        return str;
    }
    finally
    {
    }
  }

  static boolean hasDownloadFile(Context paramContext, String paramString)
  {
    File localFile = getDownloadFile(paramContext, paramString);
    return (localFile != null) && (localFile.exists());
  }

  static boolean isExternalStorageAvailable(Context paramContext)
  {
    return (paramContext.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0) && (Environment.getExternalStorageState().equals("mounted")) && (isExternalEnabled);
  }

  static boolean isInternalDir(Context paramContext, File paramFile)
  {
    File localFile = getInternalCacheDirectory(paramContext);
    return (localFile != null) && (localFile.equals(paramFile));
  }

  // ERROR //
  static void iterateCachedAds(Context paramContext, int paramInt, Iterator paramIterator)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 280	com/millennialmedia/android/AdCache:getCacheDirectory	(Landroid/content/Context;)Ljava/io/File;
    //   4: astore_3
    //   5: aload_3
    //   6: ifnull +120 -> 126
    //   9: aload_3
    //   10: new 406	com/millennialmedia/android/AdCache$2
    //   13: dup
    //   14: invokespecial 407	com/millennialmedia/android/AdCache$2:<init>	()V
    //   17: invokevirtual 410	java/io/File:listFiles	(Ljava/io/FileFilter;)[Ljava/io/File;
    //   20: astore 4
    //   22: aload 4
    //   24: arraylength
    //   25: istore 5
    //   27: iconst_0
    //   28: istore 6
    //   30: aconst_null
    //   31: astore 7
    //   33: iload 6
    //   35: iload 5
    //   37: if_icmpge +380 -> 417
    //   40: aload 4
    //   42: iload 6
    //   44: aaload
    //   45: astore 9
    //   47: aload 9
    //   49: ifnull +15 -> 64
    //   52: aload 9
    //   54: invokevirtual 149	java/io/File:exists	()Z
    //   57: istore 17
    //   59: iload 17
    //   61: ifne +35 -> 96
    //   64: aload 7
    //   66: ifnull +344 -> 410
    //   69: aload 7
    //   71: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   74: aconst_null
    //   75: astore 10
    //   77: iinc 6 1
    //   80: aload 10
    //   82: astore 7
    //   84: goto -51 -> 33
    //   87: astore 11
    //   89: aload 7
    //   91: astore 10
    //   93: goto -16 -> 77
    //   96: iload_1
    //   97: ifne +67 -> 164
    //   100: aload_2
    //   101: aload 9
    //   103: invokevirtual 287	java/io/File:getName	()Ljava/lang/String;
    //   106: invokevirtual 419	com/millennialmedia/android/AdCache$Iterator:callback	(Ljava/lang/String;)Z
    //   109: istore 26
    //   111: iload 26
    //   113: ifne +26 -> 139
    //   116: aload 7
    //   118: ifnull +299 -> 417
    //   121: aload 7
    //   123: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   126: aload_2
    //   127: invokevirtual 422	com/millennialmedia/android/AdCache$Iterator:done	()V
    //   130: return
    //   131: astore 28
    //   133: aload 7
    //   135: pop
    //   136: goto -10 -> 126
    //   139: aload 7
    //   141: ifnull +269 -> 410
    //   144: aload 7
    //   146: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   149: aconst_null
    //   150: astore 10
    //   152: goto -75 -> 77
    //   155: astore 27
    //   157: aload 7
    //   159: astore 10
    //   161: goto -84 -> 77
    //   164: new 412	java/io/ObjectInputStream
    //   167: dup
    //   168: new 424	java/io/FileInputStream
    //   171: dup
    //   172: aload 9
    //   174: invokespecial 425	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   177: invokespecial 428	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   180: astore 10
    //   182: aload 10
    //   184: invokevirtual 431	java/io/ObjectInputStream:readInt	()I
    //   187: istore 18
    //   189: aload 10
    //   191: invokevirtual 434	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   194: checkcast 436	java/util/Date
    //   197: astore 19
    //   199: aload 10
    //   201: invokevirtual 434	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   204: checkcast 350	java/lang/String
    //   207: astore 20
    //   209: aload 10
    //   211: invokevirtual 439	java/io/ObjectInputStream:readLong	()J
    //   214: lstore 21
    //   216: iload_1
    //   217: iconst_1
    //   218: if_icmpne +41 -> 259
    //   221: aload_2
    //   222: aload 9
    //   224: invokevirtual 287	java/io/File:getName	()Ljava/lang/String;
    //   227: iload 18
    //   229: aload 19
    //   231: aload 20
    //   233: lload 21
    //   235: aload 10
    //   237: invokevirtual 442	com/millennialmedia/android/AdCache$Iterator:callback	(Ljava/lang/String;ILjava/util/Date;Ljava/lang/String;JLjava/io/ObjectInputStream;)Z
    //   240: ifne +50 -> 290
    //   243: aload 10
    //   245: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   248: iconst_0
    //   249: ifeq -123 -> 126
    //   252: aconst_null
    //   253: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   256: goto -130 -> 126
    //   259: aload_2
    //   260: aload 10
    //   262: invokevirtual 434	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   265: checkcast 444	com/millennialmedia/android/CachedAd
    //   268: invokevirtual 447	com/millennialmedia/android/AdCache$Iterator:callback	(Lcom/millennialmedia/android/CachedAd;)Z
    //   271: ifne +19 -> 290
    //   274: aload 10
    //   276: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   279: iconst_0
    //   280: ifeq -154 -> 126
    //   283: aconst_null
    //   284: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   287: goto -161 -> 126
    //   290: aload 10
    //   292: ifnull -215 -> 77
    //   295: aload 10
    //   297: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   300: aconst_null
    //   301: astore 10
    //   303: goto -226 -> 77
    //   306: astore 14
    //   308: aload 7
    //   310: astore 10
    //   312: iconst_1
    //   313: anewarray 4	java/lang/Object
    //   316: astore 15
    //   318: aload 15
    //   320: iconst_0
    //   321: aload 9
    //   323: invokevirtual 287	java/io/File:getName	()Ljava/lang/String;
    //   326: aastore
    //   327: ldc_w 449
    //   330: aload 15
    //   332: invokestatic 451	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   335: aload 14
    //   337: invokestatic 453	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/Throwable;)V
    //   340: aload 10
    //   342: ifnull -265 -> 77
    //   345: aload 10
    //   347: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   350: aconst_null
    //   351: astore 10
    //   353: goto -276 -> 77
    //   356: astore 12
    //   358: aload 7
    //   360: astore 10
    //   362: aload 10
    //   364: ifnull +8 -> 372
    //   367: aload 10
    //   369: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   372: aload 12
    //   374: athrow
    //   375: astore 24
    //   377: goto -251 -> 126
    //   380: astore 25
    //   382: goto -256 -> 126
    //   385: astore 23
    //   387: goto -310 -> 77
    //   390: astore 16
    //   392: goto -315 -> 77
    //   395: astore 13
    //   397: goto -25 -> 372
    //   400: astore 12
    //   402: goto -40 -> 362
    //   405: astore 14
    //   407: goto -95 -> 312
    //   410: aload 7
    //   412: astore 10
    //   414: goto -337 -> 77
    //   417: aload 7
    //   419: pop
    //   420: goto -294 -> 126
    //
    // Exception table:
    //   from	to	target	type
    //   69	74	87	java/io/IOException
    //   121	126	131	java/io/IOException
    //   144	149	155	java/io/IOException
    //   52	59	306	java/lang/Exception
    //   100	111	306	java/lang/Exception
    //   164	182	306	java/lang/Exception
    //   52	59	356	finally
    //   100	111	356	finally
    //   164	182	356	finally
    //   252	256	375	java/io/IOException
    //   283	287	380	java/io/IOException
    //   295	300	385	java/io/IOException
    //   345	350	390	java/io/IOException
    //   367	372	395	java/io/IOException
    //   182	216	400	finally
    //   221	248	400	finally
    //   259	279	400	finally
    //   312	340	400	finally
    //   182	216	405	java/lang/Exception
    //   221	248	405	java/lang/Exception
    //   259	279	405	java/lang/Exception
  }

  // ERROR //
  static CachedAd load(Context paramContext, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: ifnull +13 -> 14
    //   4: aload_1
    //   5: ldc_w 459
    //   8: invokevirtual 403	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   11: ifeq +5 -> 16
    //   14: aconst_null
    //   15: areturn
    //   16: aconst_null
    //   17: astore_2
    //   18: aload_0
    //   19: aload_1
    //   20: invokestatic 168	com/millennialmedia/android/AdCache:getCachedAdFile	(Landroid/content/Context;Ljava/lang/String;)Ljava/io/File;
    //   23: astore_3
    //   24: aload_3
    //   25: ifnonnull +5 -> 30
    //   28: aconst_null
    //   29: areturn
    //   30: new 412	java/io/ObjectInputStream
    //   33: dup
    //   34: new 424	java/io/FileInputStream
    //   37: dup
    //   38: aload_3
    //   39: invokespecial 425	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   42: invokespecial 428	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   45: astore 4
    //   47: aload 4
    //   49: invokevirtual 431	java/io/ObjectInputStream:readInt	()I
    //   52: pop
    //   53: aload 4
    //   55: invokevirtual 434	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   58: checkcast 436	java/util/Date
    //   61: pop
    //   62: aload 4
    //   64: invokevirtual 434	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   67: pop
    //   68: aload 4
    //   70: invokevirtual 439	java/io/ObjectInputStream:readLong	()J
    //   73: pop2
    //   74: aload 4
    //   76: invokevirtual 434	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   79: checkcast 444	com/millennialmedia/android/CachedAd
    //   82: astore 16
    //   84: aload 4
    //   86: ifnull +8 -> 94
    //   89: aload 4
    //   91: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   94: aload 16
    //   96: areturn
    //   97: astore 17
    //   99: aload 16
    //   101: areturn
    //   102: astore 18
    //   104: ldc_w 461
    //   107: iconst_1
    //   108: anewarray 4	java/lang/Object
    //   111: dup
    //   112: iconst_0
    //   113: aload_1
    //   114: aastore
    //   115: invokestatic 264	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   118: aload_2
    //   119: ifnull -105 -> 14
    //   122: aload_2
    //   123: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   126: aconst_null
    //   127: areturn
    //   128: astore 8
    //   130: aconst_null
    //   131: areturn
    //   132: astore 9
    //   134: ldc_w 463
    //   137: iconst_1
    //   138: anewarray 4	java/lang/Object
    //   141: dup
    //   142: iconst_0
    //   143: aload_1
    //   144: aastore
    //   145: invokestatic 264	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   148: aload 9
    //   150: invokevirtual 302	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   153: invokestatic 188	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   156: aload 9
    //   158: invokestatic 453	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/Throwable;)V
    //   161: aload_2
    //   162: ifnull -148 -> 14
    //   165: aload_2
    //   166: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   169: aconst_null
    //   170: areturn
    //   171: astore 10
    //   173: aconst_null
    //   174: areturn
    //   175: astore 6
    //   177: aload_2
    //   178: ifnull +7 -> 185
    //   181: aload_2
    //   182: invokevirtual 413	java/io/ObjectInputStream:close	()V
    //   185: aload 6
    //   187: athrow
    //   188: astore 7
    //   190: goto -5 -> 185
    //   193: astore 6
    //   195: aload 4
    //   197: astore_2
    //   198: goto -21 -> 177
    //   201: astore 9
    //   203: aload 4
    //   205: astore_2
    //   206: goto -72 -> 134
    //   209: astore 5
    //   211: aload 4
    //   213: astore_2
    //   214: goto -110 -> 104
    //
    // Exception table:
    //   from	to	target	type
    //   89	94	97	java/io/IOException
    //   30	47	102	java/io/FileNotFoundException
    //   122	126	128	java/io/IOException
    //   30	47	132	java/lang/Exception
    //   165	169	171	java/io/IOException
    //   30	47	175	finally
    //   104	118	175	finally
    //   134	161	175	finally
    //   181	185	188	java/io/IOException
    //   47	84	193	finally
    //   47	84	201	java/lang/Exception
    //   47	84	209	java/io/FileNotFoundException
  }

  private static void loadApidListSet(SharedPreferences paramSharedPreferences)
  {
    apidListSet = new HashSet();
    String str1 = paramSharedPreferences.getString("nextCachedAd_apids", null);
    if (str1 != null)
    {
      String[] arrayOfString = str1.split(MMSDK.COMMA);
      if ((arrayOfString != null) && (arrayOfString.length > 0))
      {
        int i = arrayOfString.length;
        for (int j = 0; j < i; j++)
        {
          String str2 = arrayOfString[j];
          apidListSet.add(str2);
        }
      }
    }
  }

  static CachedAd loadIncompleteDownload(Context paramContext, String paramString)
  {
    String str = getIncompleteDownload(paramContext, paramString);
    if (str == null)
      return null;
    return load(paramContext, str);
  }

  private static void loadIncompleteDownloadHashMap(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("MillennialMediaSettings", 0);
    incompleteDownloadHashMap = new ConcurrentHashMap();
    if (apidListSet == null)
      loadApidListSet(localSharedPreferences);
    Iterator localIterator = apidListSet.iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      for (String str2 : MMAdImpl.getAdTypes())
      {
        String str3 = localSharedPreferences.getString("incompleteDownload_" + str2 + '_' + str1, null);
        if (str3 != null)
          incompleteDownloadHashMap.put(str2 + '_' + str1, str3);
      }
    }
    incompleteDownloadHashMapLoaded = true;
  }

  static CachedAd loadNextCachedAd(Context paramContext, String paramString)
  {
    String str = getNextCachedAd(paramContext, paramString);
    if ((str == null) || (str.equals("")))
      return null;
    return load(paramContext, str);
  }

  private static void loadNextCachedAdHashMap(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("MillennialMediaSettings", 0);
    nextCachedAdHashMap = new ConcurrentHashMap();
    if (apidListSet == null)
      loadApidListSet(localSharedPreferences);
    Iterator localIterator = apidListSet.iterator();
    while (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      for (String str2 : MMAdImpl.getAdTypes())
      {
        String str3 = localSharedPreferences.getString("nextCachedAd_" + str2 + '_' + str1, null);
        if (str3 != null)
          nextCachedAdHashMap.put(str2 + '_' + str1, str3);
      }
    }
    nextCachedAdHashMapLoaded = true;
  }

  static void resetCache(Context paramContext)
  {
    iterateCachedAds(paramContext, 2, new Iterator()
    {
      boolean callback(CachedAd paramAnonymousCachedAd)
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = paramAnonymousCachedAd.getId();
        MMSDK.Log.d("Deleting ad %s.", arrayOfObject);
        paramAnonymousCachedAd.delete(this.val$context);
        return true;
      }
    });
    cachedVideoSet = null;
    cachedVideoList = null;
    cachedVideoListLoaded = false;
    if (nextCachedAdHashMap != null)
    {
      Iterator localIterator2 = nextCachedAdHashMap.keySet().iterator();
      while (localIterator2.hasNext())
        setNextCachedAd(paramContext, (String)localIterator2.next(), null);
    }
    if (incompleteDownloadHashMap != null)
    {
      Iterator localIterator1 = incompleteDownloadHashMap.keySet().iterator();
      while (localIterator1.hasNext())
        setIncompleteDownload(paramContext, (String)localIterator1.next(), null);
    }
  }

  // ERROR //
  static boolean save(Context paramContext, CachedAd paramCachedAd)
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aconst_null
    //   3: astore_3
    //   4: aload_1
    //   5: ifnonnull +5 -> 10
    //   8: iconst_0
    //   9: ireturn
    //   10: aload_0
    //   11: aload_1
    //   12: invokevirtual 533	com/millennialmedia/android/CachedAd:getId	()Ljava/lang/String;
    //   15: invokestatic 168	com/millennialmedia/android/AdCache:getCachedAdFile	(Landroid/content/Context;Ljava/lang/String;)Ljava/io/File;
    //   18: astore 4
    //   20: aload 4
    //   22: ifnull -14 -> 8
    //   25: iconst_2
    //   26: anewarray 4	java/lang/Object
    //   29: astore 5
    //   31: aload 5
    //   33: iconst_0
    //   34: aload_1
    //   35: invokevirtual 533	com/millennialmedia/android/CachedAd:getId	()Ljava/lang/String;
    //   38: aastore
    //   39: aload 5
    //   41: iconst_1
    //   42: aload 4
    //   44: aastore
    //   45: ldc_w 535
    //   48: aload 5
    //   50: invokestatic 197	com/millennialmedia/android/MMSDK$Log:v	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   53: new 113	java/io/File
    //   56: dup
    //   57: aload 4
    //   59: invokevirtual 538	java/io/File:getParent	()Ljava/lang/String;
    //   62: new 202	java/lang/StringBuilder
    //   65: dup
    //   66: invokespecial 203	java/lang/StringBuilder:<init>	()V
    //   69: aload_1
    //   70: invokevirtual 533	com/millennialmedia/android/CachedAd:getId	()Ljava/lang/String;
    //   73: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   76: ldc 20
    //   78: invokevirtual 207	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: invokevirtual 213	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   84: invokespecial 541	java/io/File:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   87: astore 6
    //   89: aload 6
    //   91: invokevirtual 544	java/io/File:createNewFile	()Z
    //   94: ifne +41 -> 135
    //   97: iconst_1
    //   98: anewarray 4	java/lang/Object
    //   101: astore 17
    //   103: aload 17
    //   105: iconst_0
    //   106: aload_1
    //   107: invokevirtual 533	com/millennialmedia/android/CachedAd:getId	()Ljava/lang/String;
    //   110: aastore
    //   111: ldc_w 546
    //   114: aload 17
    //   116: invokestatic 197	com/millennialmedia/android/MMSDK$Log:v	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   119: aload 6
    //   121: invokevirtual 133	java/io/File:delete	()Z
    //   124: pop
    //   125: iconst_0
    //   126: ifeq +7 -> 133
    //   129: aconst_null
    //   130: invokevirtual 549	java/io/ObjectOutputStream:close	()V
    //   133: iconst_0
    //   134: ireturn
    //   135: new 548	java/io/ObjectOutputStream
    //   138: dup
    //   139: new 271	java/io/FileOutputStream
    //   142: dup
    //   143: aload 4
    //   145: invokespecial 307	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   148: invokespecial 552	java/io/ObjectOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   151: astore 14
    //   153: aload 14
    //   155: aload_1
    //   156: invokevirtual 555	com/millennialmedia/android/CachedAd:getType	()I
    //   159: invokevirtual 558	java/io/ObjectOutputStream:writeInt	(I)V
    //   162: aload 14
    //   164: aload_1
    //   165: getfield 562	com/millennialmedia/android/CachedAd:expiration	Ljava/util/Date;
    //   168: invokevirtual 566	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   171: aload 14
    //   173: aload_1
    //   174: getfield 569	com/millennialmedia/android/CachedAd:acid	Ljava/lang/String;
    //   177: invokevirtual 566	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   180: aload 14
    //   182: aload_1
    //   183: getfield 572	com/millennialmedia/android/CachedAd:deferredViewStart	J
    //   186: invokevirtual 576	java/io/ObjectOutputStream:writeLong	(J)V
    //   189: aload 14
    //   191: aload_1
    //   192: invokevirtual 566	java/io/ObjectOutputStream:writeObject	(Ljava/lang/Object;)V
    //   195: aload 6
    //   197: invokevirtual 133	java/io/File:delete	()Z
    //   200: pop
    //   201: aload 14
    //   203: ifnull +8 -> 211
    //   206: aload 14
    //   208: invokevirtual 549	java/io/ObjectOutputStream:close	()V
    //   211: aload_1
    //   212: aload_0
    //   213: invokevirtual 579	com/millennialmedia/android/CachedAd:saveAssets	(Landroid/content/Context;)Z
    //   216: ifne +84 -> 300
    //   219: aload_1
    //   220: aload_0
    //   221: invokevirtual 581	com/millennialmedia/android/CachedAd:delete	(Landroid/content/Context;)V
    //   224: iconst_0
    //   225: ireturn
    //   226: astore 7
    //   228: iconst_1
    //   229: anewarray 4	java/lang/Object
    //   232: astore 11
    //   234: aload 11
    //   236: iconst_0
    //   237: aload_1
    //   238: invokevirtual 533	com/millennialmedia/android/CachedAd:getId	()Ljava/lang/String;
    //   241: aastore
    //   242: ldc_w 583
    //   245: aload 11
    //   247: invokestatic 264	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   250: aload 7
    //   252: invokevirtual 302	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   255: invokestatic 188	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/String;)V
    //   258: aload 7
    //   260: invokestatic 453	com/millennialmedia/android/MMSDK$Log:d	(Ljava/lang/Throwable;)V
    //   263: aload_2
    //   264: invokevirtual 133	java/io/File:delete	()Z
    //   267: pop
    //   268: aload_3
    //   269: ifnull -261 -> 8
    //   272: aload_3
    //   273: invokevirtual 549	java/io/ObjectOutputStream:close	()V
    //   276: iconst_0
    //   277: ireturn
    //   278: astore 12
    //   280: iconst_0
    //   281: ireturn
    //   282: astore 8
    //   284: aload_2
    //   285: invokevirtual 133	java/io/File:delete	()Z
    //   288: pop
    //   289: aload_3
    //   290: ifnull +7 -> 297
    //   293: aload_3
    //   294: invokevirtual 549	java/io/ObjectOutputStream:close	()V
    //   297: aload 8
    //   299: athrow
    //   300: iconst_1
    //   301: ireturn
    //   302: astore 9
    //   304: goto -7 -> 297
    //   307: astore 8
    //   309: aload 6
    //   311: astore_2
    //   312: aconst_null
    //   313: astore_3
    //   314: goto -30 -> 284
    //   317: astore 8
    //   319: aload 14
    //   321: astore_3
    //   322: aload 6
    //   324: astore_2
    //   325: goto -41 -> 284
    //   328: astore 7
    //   330: aload 6
    //   332: astore_2
    //   333: aconst_null
    //   334: astore_3
    //   335: goto -107 -> 228
    //   338: astore 7
    //   340: aload 14
    //   342: astore_3
    //   343: aload 6
    //   345: astore_2
    //   346: goto -118 -> 228
    //   349: astore 15
    //   351: goto -140 -> 211
    //   354: astore 18
    //   356: goto -223 -> 133
    //
    // Exception table:
    //   from	to	target	type
    //   53	89	226	java/lang/Exception
    //   263	268	278	java/io/IOException
    //   272	276	278	java/io/IOException
    //   53	89	282	finally
    //   228	263	282	finally
    //   284	289	302	java/io/IOException
    //   293	297	302	java/io/IOException
    //   89	119	307	finally
    //   135	153	307	finally
    //   153	195	317	finally
    //   89	119	328	java/lang/Exception
    //   135	153	328	java/lang/Exception
    //   153	195	338	java/lang/Exception
    //   195	201	349	java/io/IOException
    //   206	211	349	java/io/IOException
    //   119	125	354	java/io/IOException
    //   129	133	354	java/io/IOException
  }

  private static void saveApidListSet(SharedPreferences.Editor paramEditor, String paramString)
  {
    int i = paramString.indexOf('_');
    String str1;
    StringBuilder localStringBuilder1;
    StringBuilder localStringBuilder2;
    if ((i >= 0) && (i < paramString.length()))
    {
      str1 = paramString.substring(i + 1);
      if ((str1 != null) && (!apidListSet.contains(str1)))
      {
        boolean bool = apidListSet.isEmpty();
        localStringBuilder1 = null;
        if (!bool)
        {
          Iterator localIterator = apidListSet.iterator();
          localStringBuilder1 = new StringBuilder();
          while (localIterator.hasNext())
            localStringBuilder1.append((String)localIterator.next() + MMSDK.COMMA);
        }
        localStringBuilder2 = new StringBuilder();
        if (localStringBuilder1 != null)
          break label181;
      }
    }
    label181: for (String str2 = ""; ; str2 = localStringBuilder1.toString())
    {
      paramEditor.putString("nextCachedAd_apids", str2 + str1);
      apidListSet.add(str1);
      return;
    }
  }

  private static void saveIncompleteDownloadHashMap(Context paramContext, String paramString)
  {
    if (paramString != null)
    {
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("MillennialMediaSettings", 0).edit();
      saveApidListSet(localEditor, paramString);
      localEditor.putString("incompleteDownload_" + paramString, (String)incompleteDownloadHashMap.get(paramString));
      localEditor.commit();
    }
  }

  private static void saveNextCachedAdHashMapValue(Context paramContext, String paramString)
  {
    if (paramString != null)
    {
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("MillennialMediaSettings", 0).edit();
      saveApidListSet(localEditor, paramString);
      localEditor.putString("nextCachedAd_" + paramString, (String)nextCachedAdHashMap.get(paramString));
      localEditor.commit();
    }
  }

  static void setEnableExternalStorage(boolean paramBoolean)
  {
    isExternalEnabled = paramBoolean;
  }

  static void setIncompleteDownload(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      if (!incompleteDownloadHashMapLoaded)
        loadIncompleteDownloadHashMap(paramContext);
      if (paramString1 != null)
      {
        Map localMap = incompleteDownloadHashMap;
        if (paramString2 == null)
          paramString2 = "";
        localMap.put(paramString1, paramString2);
        saveIncompleteDownloadHashMap(paramContext, paramString1);
      }
      return;
    }
    finally
    {
    }
  }

  static void setNextCachedAd(Context paramContext, String paramString1, String paramString2)
  {
    try
    {
      if (!nextCachedAdHashMapLoaded)
        loadNextCachedAdHashMap(paramContext);
      if (paramString1 != null)
      {
        Map localMap = nextCachedAdHashMap;
        if (paramString2 == null)
          paramString2 = "";
        localMap.put(paramString1, paramString2);
        saveNextCachedAdHashMapValue(paramContext, paramString1);
      }
      return;
    }
    finally
    {
    }
  }

  static boolean startDownloadTask(Context paramContext, String paramString, CachedAd paramCachedAd, AdCacheTaskListener paramAdCacheTaskListener)
  {
    return AdCacheThreadPool.sharedThreadPool().startDownloadTask(paramContext, paramString, paramCachedAd, paramAdCacheTaskListener);
  }

  static abstract interface AdCacheTaskListener
  {
    public abstract void downloadCompleted(CachedAd paramCachedAd, boolean paramBoolean);

    public abstract void downloadStart(CachedAd paramCachedAd);
  }

  static class Iterator
  {
    static final int ITERATE_ID = 0;
    static final int ITERATE_INFO = 1;
    static final int ITERATE_OBJECT = 2;

    boolean callback(CachedAd paramCachedAd)
    {
      return false;
    }

    boolean callback(String paramString)
    {
      return false;
    }

    boolean callback(String paramString1, int paramInt, Date paramDate, String paramString2, long paramLong, ObjectInputStream paramObjectInputStream)
    {
      return false;
    }

    void done()
    {
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.AdCache
 * JD-Core Version:    0.6.2
 */