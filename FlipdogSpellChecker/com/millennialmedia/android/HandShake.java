package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.WindowManager.BadTokenException;
import android.widget.Toast;
import com.millennialmedia.google.gson.Gson;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class HandShake
{
  static final String BASE_URL = "http://androidsdk.ads.mp.mydas.mobi/";
  static final String BASE_URL_PATH = "getAd.php5?";
  private static final String HANDSHAKE_FALLBACK_URL = "http://ads.mp.mydas.mobi/appConfigServlet?apid=";
  private static final String HANDSHAKE_HTTPS_SCHEME = "https://";
  private static final String HANDSHAKE_HTTP_SCHEME = "http://";
  private static final String HANDSHAKE_URL_HOST = "ads.mp.mydas.mobi/";
  private static final String HANDSHAKE_URL_OVERRIDE_PARMS = "?apid=";
  private static final String HANDSHAKE_URL_PARMS = "appConfigServlet?apid=";
  private static final String KEY_CACHED_VIDEOS = "handshake_cachedvideos5.0";
  private static String adUrl = "http://androidsdk.ads.mp.mydas.mobi/getAd.php5?";
  static String apid = "28913";
  private static boolean forceRefresh;
  private static String handShakeURL = "https://ads.mp.mydas.mobi/appConfigServlet?apid=";
  private static HandShake sharedInstance;
  long adRefreshSecs;
  private LinkedHashMap<String, AdTypeHandShake> adTypeHandShakes = new LinkedHashMap();
  private WeakReference<Context> appContextRef;
  DTOCachedVideo[] cachedVideos;
  private WeakReference<Context> contextRef;
  long creativeCacheTimeout = 259200000L;
  private long deferredViewTimeout = 3600000L;
  String endSessionURL;
  private long handShakeCallback = 86400000L;
  private final Handler handler = new Handler(Looper.getMainLooper());
  boolean hardwareAccelerationEnabled;
  boolean kill = false;
  private long lastHandShake;
  String mmdid;
  String mmjsUrl;
  private String noVideosToCacheURL;
  private ArrayList<Scheme> schemes = new ArrayList();
  private String schemesList;
  String startSessionURL;
  private Runnable updateHandShakeRunnable = new Runnable()
  {
    public void run()
    {
      Context localContext = (Context)HandShake.this.contextRef.get();
      if (localContext == null)
        localContext = (Context)HandShake.this.appContextRef.get();
      if (localContext != null)
        HandShake.sharedHandShake(localContext);
    }
  };

  private HandShake()
  {
  }

  private HandShake(Context paramContext)
  {
    this.contextRef = new WeakReference(paramContext);
    this.appContextRef = new WeakReference(paramContext.getApplicationContext());
    if ((forceRefresh) || (!loadHandShake(paramContext)) || (System.currentTimeMillis() - this.lastHandShake > this.handShakeCallback))
    {
      forceRefresh = false;
      this.lastHandShake = System.currentTimeMillis();
      requestHandshake(false);
    }
  }

  private void deserializeFromObj(JSONObject paramJSONObject)
  {
    final Context localContext = (Context)this.contextRef.get();
    if (paramJSONObject == null)
      return;
    int k;
    try
    {
      JSONArray localJSONArray1 = paramJSONObject.optJSONArray("errors");
      if (localJSONArray1 != null)
      {
        k = 0;
        label32: if (k < localJSONArray1.length())
        {
          JSONObject localJSONObject4 = localJSONArray1.optJSONObject(k);
          if (localJSONObject4 == null)
            break label523;
          final String str1 = localJSONObject4.optString("message", null);
          String str2 = localJSONObject4.optString("type", null);
          if ((str1 == null) || (str2 == null))
            break label523;
          if (str2.equalsIgnoreCase("log"))
            MMSDK.Log.e(str1);
          else if (str2.equalsIgnoreCase("prompt"))
            this.handler.post(new Runnable()
            {
              public void run()
              {
                try
                {
                  Toast.makeText(localContext, "Error: " + str1, 1).show();
                  return;
                }
                catch (WindowManager.BadTokenException localBadTokenException)
                {
                  localBadTokenException.printStackTrace();
                }
              }
            });
        }
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      return;
    }
    JSONObject localJSONObject1 = paramJSONObject.optJSONObject("adtypes");
    int i;
    if (localJSONObject1 != null)
    {
      String[] arrayOfString = MMAdImpl.getAdTypes();
      i = 0;
      label164: if (i < arrayOfString.length)
      {
        JSONObject localJSONObject3 = localJSONObject1.optJSONObject(arrayOfString[i]);
        if (localJSONObject3 == null)
          break label529;
        AdTypeHandShake localAdTypeHandShake = new AdTypeHandShake();
        localAdTypeHandShake.deserializeFromObj(localJSONObject3);
        localAdTypeHandShake.loadLastVideo(localContext, arrayOfString[i]);
        this.adTypeHandShakes.put(arrayOfString[i], localAdTypeHandShake);
        break label529;
      }
    }
    while (true)
    {
      try
      {
        JSONArray localJSONArray2 = paramJSONObject.optJSONArray("schemes");
        if (localJSONArray2 != null)
        {
          if ((this.schemes == null) || (this.schemes.size() <= 0))
            break label535;
          this.schemes.removeAll(this.schemes);
          break label535;
          if (j < localJSONArray2.length())
          {
            JSONObject localJSONObject2 = localJSONArray2.optJSONObject(j);
            if (localJSONObject2 == null)
              break label541;
            Scheme localScheme = new Scheme();
            localScheme.deserializeFromObj(localJSONObject2);
            this.schemes.add(localScheme);
            break label541;
          }
        }
        this.adRefreshSecs = paramJSONObject.optLong("adrefresh", 0L);
        this.deferredViewTimeout = (1000L * paramJSONObject.optLong("deferredviewtimeout", 3600L));
        this.kill = paramJSONObject.optBoolean("kill");
        setAdUrl(localContext, paramJSONObject.optString("baseURL"));
        this.handShakeCallback = (1000L * paramJSONObject.optLong("handshakecallback", 86400L));
        this.creativeCacheTimeout = (1000L * paramJSONObject.optLong("creativeCacheTimeout", 259200L));
        this.hardwareAccelerationEnabled = paramJSONObject.optBoolean("hardwareAccelerationEnabled");
        this.startSessionURL = paramJSONObject.optString("startSessionURL");
        this.endSessionURL = paramJSONObject.optString("endSessionURL");
        this.mmjsUrl = paramJSONObject.optString("mmjs");
        handleCachedVideos(paramJSONObject, localContext);
        if ((TextUtils.isEmpty(this.mmjsUrl)) || (MRaid.isMRaidUpdated(localContext, this.mmjsUrl)))
          break;
        MRaid.downloadMraidJs((Context)this.appContextRef.get(), this.mmjsUrl);
        return;
      }
      finally
      {
      }
      label523: k++;
      break label32;
      label529: i++;
      break label164;
      label535: int j = 0;
      continue;
      label541: j++;
    }
  }

  static String getAdUrl()
  {
    return adUrl;
  }

  private void handleCachedVideos(JSONObject paramJSONObject, Context paramContext)
  {
    JSONArray localJSONArray = paramJSONObject.optJSONArray("cachedVideos");
    if (localJSONArray != null)
    {
      this.cachedVideos = ((DTOCachedVideo[])new Gson().fromJson(localJSONArray.toString(), [Lcom.millennialmedia.android.DTOCachedVideo.class));
      MMSDK.Log.d(this.cachedVideos.toString());
    }
    this.noVideosToCacheURL = paramJSONObject.optString("noVideosToCacheURL");
    if (this.cachedVideos != null)
      PreCacheWorker.preCacheVideos(this.cachedVideos, paramContext, this.noVideosToCacheURL);
  }

  private boolean isFirstLaunch(Context paramContext)
  {
    if (paramContext == null)
      return false;
    return paramContext.getSharedPreferences("MillennialMediaSettings", 0).getBoolean("firstlaunchHandshake", true);
  }

  private boolean loadHandShake(Context paramContext)
  {
    SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("MillennialMediaSettings", 0);
    if (localSharedPreferences == null)
      return false;
    boolean bool1 = localSharedPreferences.contains("handshake_deferredviewtimeout");
    boolean bool2 = false;
    if (bool1)
    {
      this.deferredViewTimeout = localSharedPreferences.getLong("handshake_deferredviewtimeout", this.deferredViewTimeout);
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_baseUrl"))
    {
      adUrl = localSharedPreferences.getString("handshake_baseUrl", adUrl);
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_callback"))
    {
      this.handShakeCallback = localSharedPreferences.getLong("handshake_callback", this.handShakeCallback);
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_hardwareAccelerationEnabled"))
    {
      this.hardwareAccelerationEnabled = localSharedPreferences.getBoolean("handshake_hardwareAccelerationEnabled", false);
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_startSessionURL"))
    {
      this.startSessionURL = localSharedPreferences.getString("handshake_startSessionURL", "");
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_endSessionURL"))
    {
      this.endSessionURL = localSharedPreferences.getString("handshake_endSessionURL", "");
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_mmdid"))
    {
      setMMdid(paramContext, localSharedPreferences.getString("handshake_mmdid", this.mmdid), false);
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_creativecachetimeout"))
    {
      this.creativeCacheTimeout = localSharedPreferences.getLong("handshake_creativecachetimeout", this.creativeCacheTimeout);
      bool2 = true;
    }
    if (localSharedPreferences.contains("handshake_mmjs"))
    {
      this.mmjsUrl = localSharedPreferences.getString("handshake_mmjs", this.mmjsUrl);
      bool2 = true;
    }
    String[] arrayOfString1 = MMAdImpl.getAdTypes();
    for (int i = 0; i < arrayOfString1.length; i++)
    {
      AdTypeHandShake localAdTypeHandShake = new AdTypeHandShake();
      if (localAdTypeHandShake.load(localSharedPreferences, arrayOfString1[i]))
      {
        bool2 = true;
        this.adTypeHandShakes.put(arrayOfString1[i], localAdTypeHandShake);
      }
    }
    while (true)
    {
      int k;
      try
      {
        if (localSharedPreferences.contains("handshake_schemes"))
        {
          String str2 = localSharedPreferences.getString("handshake_schemes", "");
          if (str2.length() > 0)
          {
            String[] arrayOfString2 = str2.split("\n");
            int j = arrayOfString2.length;
            k = 0;
            if (k >= j)
              break label673;
            String[] arrayOfString3 = arrayOfString2[k].split("\t");
            if (arrayOfString3.length < 2)
              break label667;
            Scheme localScheme = new Scheme(arrayOfString3[0], Integer.parseInt(arrayOfString3[1]));
            this.schemes.add(localScheme);
            break label667;
          }
        }
        if (localSharedPreferences.contains("handshake_cachedvideos5.0"))
        {
          String str1 = localSharedPreferences.getString("handshake_cachedvideos5.0", "");
          if (str1.length() > 0)
            this.cachedVideos = ((DTOCachedVideo[])new Gson().fromJson(str1, [Lcom.millennialmedia.android.DTOCachedVideo.class));
        }
        if (localSharedPreferences.contains("handshake_lasthandshake"))
        {
          this.lastHandShake = localSharedPreferences.getLong("handshake_lasthandshake", this.lastHandShake);
          bool2 = true;
        }
        if (bool2)
        {
          MMSDK.Log.d("Handshake successfully loaded from shared preferences.");
          if (System.currentTimeMillis() - this.lastHandShake < this.handShakeCallback)
            this.handler.postDelayed(this.updateHandShakeRunnable, this.handShakeCallback - (System.currentTimeMillis() - this.lastHandShake));
          this.noVideosToCacheURL = localSharedPreferences.getString("handshake_novideostocacheurl", "");
          if (this.cachedVideos != null)
            PreCacheWorker.preCacheVideos(this.cachedVideos, paramContext, this.noVideosToCacheURL);
        }
        return bool2;
      }
      finally
      {
      }
      label667: k++;
      continue;
      label673: bool2 = true;
    }
  }

  private JSONObject parseJson(String paramString)
  {
    MMSDK.Log.d("JSON String: %s", new Object[] { paramString });
    if (paramString != null)
      try
      {
        JSONObject localJSONObject1 = new JSONObject(paramString);
        MMSDK.Log.v(localJSONObject1.toString());
        if (localJSONObject1.has("mmishake"))
        {
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject("mmishake");
          return localJSONObject2;
        }
      }
      catch (JSONException localJSONException)
      {
        localJSONException.printStackTrace();
      }
    return null;
  }

  private void requestHandshake(final boolean paramBoolean)
  {
    Utils.ThreadUtils.execute(new Runnable()
    {
      // ERROR //
      public void run()
      {
        // Byte code:
        //   0: aload_0
        //   1: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   4: invokestatic 33	com/millennialmedia/android/HandShake:access$000	(Lcom/millennialmedia/android/HandShake;)Ljava/lang/ref/WeakReference;
        //   7: invokevirtual 39	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
        //   10: checkcast 41	android/content/Context
        //   13: astore_1
        //   14: aload_1
        //   15: ifnonnull +17 -> 32
        //   18: aload_0
        //   19: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   22: invokestatic 44	com/millennialmedia/android/HandShake:access$100	(Lcom/millennialmedia/android/HandShake;)Ljava/lang/ref/WeakReference;
        //   25: invokevirtual 39	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
        //   28: checkcast 41	android/content/Context
        //   31: astore_1
        //   32: aload_1
        //   33: ifnonnull +4 -> 37
        //   36: return
        //   37: iconst_0
        //   38: istore_2
        //   39: new 46	java/lang/StringBuilder
        //   42: dup
        //   43: invokespecial 47	java/lang/StringBuilder:<init>	()V
        //   46: astore_3
        //   47: new 49	java/util/TreeMap
        //   50: dup
        //   51: invokespecial 50	java/util/TreeMap:<init>	()V
        //   54: astore 4
        //   56: aload 4
        //   58: ldc 52
        //   60: new 46	java/lang/StringBuilder
        //   63: dup
        //   64: invokespecial 47	java/lang/StringBuilder:<init>	()V
        //   67: ldc 54
        //   69: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   72: getstatic 64	android/os/Build:MODEL	Ljava/lang/String;
        //   75: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   78: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   81: invokeinterface 74 3 0
        //   86: pop
        //   87: aload_0
        //   88: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   91: aload_1
        //   92: invokestatic 78	com/millennialmedia/android/HandShake:access$200	(Lcom/millennialmedia/android/HandShake;Landroid/content/Context;)Z
        //   95: istore_2
        //   96: iload_2
        //   97: ifeq +15 -> 112
        //   100: aload 4
        //   102: ldc 80
        //   104: ldc 82
        //   106: invokeinterface 74 3 0
        //   111: pop
        //   112: aload_0
        //   113: getfield 21	com/millennialmedia/android/HandShake$1:val$isInitialize	Z
        //   116: ifeq +15 -> 131
        //   119: aload 4
        //   121: ldc 84
        //   123: ldc 82
        //   125: invokeinterface 74 3 0
        //   130: pop
        //   131: aload_1
        //   132: aload 4
        //   134: invokestatic 90	com/millennialmedia/android/MMSDK:insertUrlCommonValues	(Landroid/content/Context;Ljava/util/Map;)V
        //   137: aload 4
        //   139: invokeinterface 94 1 0
        //   144: invokeinterface 100 1 0
        //   149: astore 12
        //   151: aload 12
        //   153: invokeinterface 106 1 0
        //   158: ifeq +103 -> 261
        //   161: aload 12
        //   163: invokeinterface 109 1 0
        //   168: checkcast 111	java/util/Map$Entry
        //   171: astore 30
        //   173: iconst_2
        //   174: anewarray 4	java/lang/Object
        //   177: astore 31
        //   179: aload 31
        //   181: iconst_0
        //   182: aload 30
        //   184: invokeinterface 114 1 0
        //   189: aastore
        //   190: aload 31
        //   192: iconst_1
        //   193: aload 30
        //   195: invokeinterface 117 1 0
        //   200: checkcast 119	java/lang/String
        //   203: ldc 121
        //   205: invokestatic 127	java/net/URLEncoder:encode	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   208: aastore
        //   209: aload_3
        //   210: ldc 129
        //   212: aload 31
        //   214: invokestatic 133	java/lang/String:format	(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   217: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: pop
        //   221: goto -70 -> 151
        //   224: astore 8
        //   226: iconst_1
        //   227: anewarray 4	java/lang/Object
        //   230: astore 9
        //   232: aload 9
        //   234: iconst_0
        //   235: aload 8
        //   237: invokevirtual 136	java/io/IOException:getMessage	()Ljava/lang/String;
        //   240: aastore
        //   241: ldc 138
        //   243: aload 9
        //   245: invokestatic 144	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   248: iconst_0
        //   249: ifeq -213 -> 36
        //   252: aload_0
        //   253: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   256: aload_1
        //   257: invokestatic 148	com/millennialmedia/android/HandShake:access$1000	(Lcom/millennialmedia/android/HandShake;Landroid/content/Context;)V
        //   260: return
        //   261: new 46	java/lang/StringBuilder
        //   264: dup
        //   265: invokespecial 47	java/lang/StringBuilder:<init>	()V
        //   268: invokestatic 151	com/millennialmedia/android/HandShake:access$300	()Ljava/lang/String;
        //   271: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   274: getstatic 154	com/millennialmedia/android/HandShake:apid	Ljava/lang/String;
        //   277: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   280: aload_3
        //   281: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   284: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   287: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   290: astore 13
        //   292: ldc 156
        //   294: iconst_1
        //   295: anewarray 4	java/lang/Object
        //   298: dup
        //   299: iconst_0
        //   300: aload 13
        //   302: aastore
        //   303: invokestatic 159	com/millennialmedia/android/MMSDK$Log:v	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   306: new 161	com/millennialmedia/android/HttpGetRequest
        //   309: dup
        //   310: invokespecial 162	com/millennialmedia/android/HttpGetRequest:<init>	()V
        //   313: aload 13
        //   315: invokevirtual 165	com/millennialmedia/android/HttpGetRequest:get	(Ljava/lang/String;)Lorg/apache/http/HttpResponse;
        //   318: astore 29
        //   320: aload 29
        //   322: astore 16
        //   324: aload 16
        //   326: ifnull +25 -> 351
        //   329: aload 16
        //   331: invokeinterface 171 1 0
        //   336: invokeinterface 177 1 0
        //   341: istore 28
        //   343: iload 28
        //   345: sipush 200
        //   348: if_icmpeq +80 -> 428
        //   351: invokestatic 151	com/millennialmedia/android/HandShake:access$300	()Ljava/lang/String;
        //   354: ldc 179
        //   356: ldc 181
        //   358: invokevirtual 184	java/lang/String:replaceFirst	(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   361: invokestatic 188	com/millennialmedia/android/HandShake:access$302	(Ljava/lang/String;)Ljava/lang/String;
        //   364: pop
        //   365: new 46	java/lang/StringBuilder
        //   368: dup
        //   369: invokespecial 47	java/lang/StringBuilder:<init>	()V
        //   372: invokestatic 151	com/millennialmedia/android/HandShake:access$300	()Ljava/lang/String;
        //   375: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   378: getstatic 154	com/millennialmedia/android/HandShake:apid	Ljava/lang/String;
        //   381: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   384: aload_3
        //   385: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   388: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   391: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   394: astore 26
        //   396: ldc 190
        //   398: iconst_1
        //   399: anewarray 4	java/lang/Object
        //   402: dup
        //   403: iconst_0
        //   404: aload 26
        //   406: aastore
        //   407: invokestatic 159	com/millennialmedia/android/MMSDK$Log:v	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   410: new 161	com/millennialmedia/android/HttpGetRequest
        //   413: dup
        //   414: invokespecial 162	com/millennialmedia/android/HttpGetRequest:<init>	()V
        //   417: aload 26
        //   419: invokevirtual 165	com/millennialmedia/android/HttpGetRequest:get	(Ljava/lang/String;)Lorg/apache/http/HttpResponse;
        //   422: astore 27
        //   424: aload 27
        //   426: astore 16
        //   428: aload 16
        //   430: ifnull +25 -> 455
        //   433: aload 16
        //   435: invokeinterface 171 1 0
        //   440: invokeinterface 177 1 0
        //   445: istore 24
        //   447: iload 24
        //   449: sipush 200
        //   452: if_icmpeq +65 -> 517
        //   455: new 46	java/lang/StringBuilder
        //   458: dup
        //   459: invokespecial 47	java/lang/StringBuilder:<init>	()V
        //   462: ldc 192
        //   464: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   467: getstatic 154	com/millennialmedia/android/HandShake:apid	Ljava/lang/String;
        //   470: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   473: aload_3
        //   474: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   477: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   480: invokevirtual 68	java/lang/StringBuilder:toString	()Ljava/lang/String;
        //   483: astore 22
        //   485: ldc 194
        //   487: iconst_1
        //   488: anewarray 4	java/lang/Object
        //   491: dup
        //   492: iconst_0
        //   493: aload 22
        //   495: aastore
        //   496: invokestatic 159	com/millennialmedia/android/MMSDK$Log:v	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   499: new 161	com/millennialmedia/android/HttpGetRequest
        //   502: dup
        //   503: invokespecial 162	com/millennialmedia/android/HttpGetRequest:<init>	()V
        //   506: aload 22
        //   508: invokevirtual 165	com/millennialmedia/android/HttpGetRequest:get	(Ljava/lang/String;)Lorg/apache/http/HttpResponse;
        //   511: astore 23
        //   513: aload 23
        //   515: astore 16
        //   517: aload 16
        //   519: ifnull +239 -> 758
        //   522: aload 16
        //   524: invokeinterface 171 1 0
        //   529: invokeinterface 177 1 0
        //   534: sipush 200
        //   537: if_icmpne +221 -> 758
        //   540: aload_0
        //   541: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   544: aload_0
        //   545: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   548: aload 16
        //   550: invokeinterface 198 1 0
        //   555: invokeinterface 204 1 0
        //   560: invokestatic 208	com/millennialmedia/android/HttpGetRequest:convertStreamToString	(Ljava/io/InputStream;)Ljava/lang/String;
        //   563: invokestatic 212	com/millennialmedia/android/HandShake:access$400	(Lcom/millennialmedia/android/HandShake;Ljava/lang/String;)Lorg/json/JSONObject;
        //   566: invokestatic 216	com/millennialmedia/android/HandShake:access$500	(Lcom/millennialmedia/android/HandShake;Lorg/json/JSONObject;)V
        //   569: aload_0
        //   570: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   573: aload_1
        //   574: invokestatic 219	com/millennialmedia/android/HandShake:access$600	(Lcom/millennialmedia/android/HandShake;Landroid/content/Context;)V
        //   577: aload_0
        //   578: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   581: invokestatic 223	com/millennialmedia/android/HandShake:access$900	(Lcom/millennialmedia/android/HandShake;)Landroid/os/Handler;
        //   584: aload_0
        //   585: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   588: invokestatic 227	com/millennialmedia/android/HandShake:access$700	(Lcom/millennialmedia/android/HandShake;)Ljava/lang/Runnable;
        //   591: aload_0
        //   592: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   595: invokestatic 231	com/millennialmedia/android/HandShake:access$800	(Lcom/millennialmedia/android/HandShake;)J
        //   598: invokevirtual 237	android/os/Handler:postDelayed	(Ljava/lang/Runnable;J)Z
        //   601: pop
        //   602: ldc 239
        //   604: invokestatic 242	com/millennialmedia/android/MMSDK$Log:v	(Ljava/lang/String;)V
        //   607: iload_2
        //   608: ifeq -572 -> 36
        //   611: aload_0
        //   612: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   615: aload_1
        //   616: invokestatic 148	com/millennialmedia/android/HandShake:access$1000	(Lcom/millennialmedia/android/HandShake;Landroid/content/Context;)V
        //   619: return
        //   620: astore 14
        //   622: iconst_1
        //   623: anewarray 4	java/lang/Object
        //   626: astore 15
        //   628: aload 15
        //   630: iconst_0
        //   631: aload 14
        //   633: invokevirtual 136	java/io/IOException:getMessage	()Ljava/lang/String;
        //   636: aastore
        //   637: ldc 138
        //   639: aload 15
        //   641: invokestatic 144	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   644: aconst_null
        //   645: astore 16
        //   647: goto -323 -> 324
        //   650: astore 6
        //   652: iconst_1
        //   653: anewarray 4	java/lang/Object
        //   656: astore 7
        //   658: aload 7
        //   660: iconst_0
        //   661: aload 6
        //   663: invokevirtual 243	java/lang/Exception:getMessage	()Ljava/lang/String;
        //   666: aastore
        //   667: ldc 138
        //   669: aload 7
        //   671: invokestatic 144	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   674: iconst_0
        //   675: ifeq -639 -> 36
        //   678: aload_0
        //   679: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   682: aload_1
        //   683: invokestatic 148	com/millennialmedia/android/HandShake:access$1000	(Lcom/millennialmedia/android/HandShake;Landroid/content/Context;)V
        //   686: return
        //   687: astore 17
        //   689: iconst_1
        //   690: anewarray 4	java/lang/Object
        //   693: astore 18
        //   695: aload 18
        //   697: iconst_0
        //   698: aload 17
        //   700: invokevirtual 136	java/io/IOException:getMessage	()Ljava/lang/String;
        //   703: aastore
        //   704: ldc 138
        //   706: aload 18
        //   708: invokestatic 144	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   711: goto -283 -> 428
        //   714: astore 5
        //   716: iload_2
        //   717: ifeq +11 -> 728
        //   720: aload_0
        //   721: getfield 19	com/millennialmedia/android/HandShake$1:this$0	Lcom/millennialmedia/android/HandShake;
        //   724: aload_1
        //   725: invokestatic 148	com/millennialmedia/android/HandShake:access$1000	(Lcom/millennialmedia/android/HandShake;Landroid/content/Context;)V
        //   728: aload 5
        //   730: athrow
        //   731: astore 19
        //   733: iconst_1
        //   734: anewarray 4	java/lang/Object
        //   737: astore 20
        //   739: aload 20
        //   741: iconst_0
        //   742: aload 19
        //   744: invokevirtual 136	java/io/IOException:getMessage	()Ljava/lang/String;
        //   747: aastore
        //   748: ldc 138
        //   750: aload 20
        //   752: invokestatic 144	com/millennialmedia/android/MMSDK$Log:e	(Ljava/lang/String;[Ljava/lang/Object;)V
        //   755: goto -238 -> 517
        //   758: iconst_0
        //   759: istore_2
        //   760: goto -153 -> 607
        //
        // Exception table:
        //   from	to	target	type
        //   39	96	224	java/io/IOException
        //   100	112	224	java/io/IOException
        //   112	131	224	java/io/IOException
        //   131	151	224	java/io/IOException
        //   151	221	224	java/io/IOException
        //   261	306	224	java/io/IOException
        //   329	343	224	java/io/IOException
        //   433	447	224	java/io/IOException
        //   522	607	224	java/io/IOException
        //   622	644	224	java/io/IOException
        //   689	711	224	java/io/IOException
        //   733	755	224	java/io/IOException
        //   306	320	620	java/io/IOException
        //   39	96	650	java/lang/Exception
        //   100	112	650	java/lang/Exception
        //   112	131	650	java/lang/Exception
        //   131	151	650	java/lang/Exception
        //   151	221	650	java/lang/Exception
        //   261	306	650	java/lang/Exception
        //   306	320	650	java/lang/Exception
        //   329	343	650	java/lang/Exception
        //   351	424	650	java/lang/Exception
        //   433	447	650	java/lang/Exception
        //   455	513	650	java/lang/Exception
        //   522	607	650	java/lang/Exception
        //   622	644	650	java/lang/Exception
        //   689	711	650	java/lang/Exception
        //   733	755	650	java/lang/Exception
        //   351	424	687	java/io/IOException
        //   39	96	714	finally
        //   100	112	714	finally
        //   112	131	714	finally
        //   131	151	714	finally
        //   151	221	714	finally
        //   226	248	714	finally
        //   261	306	714	finally
        //   306	320	714	finally
        //   329	343	714	finally
        //   351	424	714	finally
        //   433	447	714	finally
        //   455	513	714	finally
        //   522	607	714	finally
        //   622	644	714	finally
        //   652	674	714	finally
        //   689	711	714	finally
        //   733	755	714	finally
        //   455	513	731	java/io/IOException
      }
    });
  }

  private void saveHandShake(Context paramContext)
  {
    SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("MillennialMediaSettings", 0).edit();
    localEditor.putLong("handshake_deferredviewtimeout", this.deferredViewTimeout);
    localEditor.putBoolean("handshake_kill", this.kill);
    localEditor.putString("handshake_baseUrl", adUrl);
    localEditor.putLong("handshake_callback", this.handShakeCallback);
    localEditor.putBoolean("handshake_hardwareAccelerationEnabled", this.hardwareAccelerationEnabled);
    localEditor.putString("handshake_startSessionURL", this.startSessionURL);
    localEditor.putString("handshake_endSessionURL", this.endSessionURL);
    localEditor.putLong("handshake_creativecaetimeout", this.creativeCacheTimeout);
    localEditor.putString("handshake_mmjs", this.mmjsUrl);
    Iterator localIterator = this.adTypeHandShakes.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      ((AdTypeHandShake)this.adTypeHandShakes.get(str)).save(localEditor, (String)str);
    }
    try
    {
      StringBuilder localStringBuilder = new StringBuilder();
      for (int i = 0; i < this.schemes.size(); i++)
      {
        Scheme localScheme = (Scheme)this.schemes.get(i);
        if (i > 0)
          localStringBuilder.append("\n");
        localStringBuilder.append(localScheme.scheme + "\t" + localScheme.id);
      }
      localEditor.putString("handshake_schemes", localStringBuilder.toString());
      if (this.cachedVideos != null)
        localEditor.putString("handshake_cachedvideos5.0", new Gson().toJson(this.cachedVideos));
      localEditor.putString("handshake_novideostocacheurl", this.noVideosToCacheURL);
      localEditor.putLong("handshake_lasthandshake", this.lastHandShake);
      localEditor.commit();
      return;
    }
    finally
    {
    }
  }

  private void sentFirstLaunch(Context paramContext)
  {
    if (paramContext != null)
    {
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("MillennialMediaSettings", 0).edit();
      localEditor.putBoolean("firstlaunchHandshake", false);
      localEditor.commit();
    }
  }

  static void setAdUrl(Context paramContext, String paramString)
  {
    if (!TextUtils.isEmpty(paramString))
    {
      if (!paramString.endsWith("/"))
        adUrl = paramString + "/" + "getAd.php5?";
    }
    else
      return;
    adUrl = paramString + "getAd.php5?";
  }

  static void setHandShakeURL(Context paramContext, String paramString)
  {
    try
    {
      if (!TextUtils.isEmpty(paramString))
      {
        if (paramString.startsWith("http://"))
          paramString = paramString.replaceFirst("http://", "https://");
        handShakeURL = paramString + "?apid=";
        forceRefresh = true;
        sharedInstance = new HandShake(paramContext);
      }
      return;
    }
    finally
    {
    }
  }

  static HandShake sharedHandShake(Context paramContext)
  {
    try
    {
      HandShake localHandShake;
      if (apid == null)
      {
        MMSDK.Log.e("No apid set for the handshake.");
        localHandShake = null;
        return localHandShake;
      }
      if (sharedInstance == null)
        sharedInstance = new HandShake(paramContext);
      while (true)
      {
        localHandShake = sharedInstance;
        break;
        if (System.currentTimeMillis() - sharedInstance.lastHandShake > sharedInstance.handShakeCallback)
        {
          MMSDK.Log.d("Handshake expired, requesting new handshake from the server.");
          sharedInstance = new HandShake(paramContext);
        }
      }
    }
    finally
    {
    }
  }

  boolean canDisplayCachedAd(String paramString, long paramLong)
  {
    try
    {
      AdTypeHandShake localAdTypeHandShake = (AdTypeHandShake)this.adTypeHandShakes.get(paramString);
      boolean bool1;
      if (localAdTypeHandShake != null)
        bool1 = localAdTypeHandShake.canDisplayCachedAd(paramLong);
      for (boolean bool2 = bool1; ; bool2 = true)
        return bool2;
    }
    finally
    {
    }
  }

  boolean canRequestVideo(Context paramContext, String paramString)
  {
    try
    {
      AdTypeHandShake localAdTypeHandShake = (AdTypeHandShake)this.adTypeHandShakes.get(paramString);
      boolean bool1;
      if (localAdTypeHandShake != null)
        bool1 = localAdTypeHandShake.canRequestVideo(paramContext, paramString);
      for (boolean bool2 = bool1; ; bool2 = true)
        return bool2;
    }
    finally
    {
    }
  }

  void endSession()
  {
    if (!TextUtils.isEmpty(this.endSessionURL))
      Utils.HttpUtils.executeUrl(this.endSessionURL);
  }

  JSONArray getSchemesJSONArray(Context paramContext)
  {
    try
    {
      JSONArray localJSONArray = new JSONArray();
      if (this.schemes.size() > 0)
      {
        Iterator localIterator = this.schemes.iterator();
        while (localIterator.hasNext())
        {
          Scheme localScheme = (Scheme)localIterator.next();
          boolean bool = localScheme.checkAvailability(paramContext);
          if (bool)
            try
            {
              JSONObject localJSONObject = new JSONObject();
              localJSONObject.put("scheme", localScheme.scheme);
              localJSONObject.put("schemeid", localScheme.id);
              localJSONArray.put(localJSONObject);
            }
            catch (JSONException localJSONException)
            {
            }
        }
      }
      return localJSONArray;
    }
    finally
    {
    }
  }

  String getSchemesList(Context paramContext)
  {
    StringBuilder localStringBuilder;
    while (true)
    {
      Scheme localScheme;
      try
      {
        if ((this.schemesList != null) || (this.schemes.size() <= 0))
          break label148;
        localStringBuilder = new StringBuilder();
        Iterator localIterator = this.schemes.iterator();
        if (!localIterator.hasNext())
          break;
        localScheme = (Scheme)localIterator.next();
        if (!localScheme.checkAvailability(paramContext))
          continue;
        if (localStringBuilder.length() > 0)
        {
          localStringBuilder.append("," + localScheme.id);
          continue;
        }
      }
      finally
      {
      }
      localStringBuilder.append(Integer.toString(localScheme.id));
    }
    if (localStringBuilder.length() > 0)
      this.schemesList = localStringBuilder.toString();
    label148: String str = this.schemesList;
    return str;
  }

  boolean isAdTypeDownloading(String paramString)
  {
    try
    {
      AdTypeHandShake localAdTypeHandShake = (AdTypeHandShake)this.adTypeHandShakes.get(paramString);
      if (localAdTypeHandShake != null);
      for (boolean bool = localAdTypeHandShake.downloading; ; bool = false)
        return bool;
    }
    finally
    {
    }
  }

  void lockAdTypeDownload(String paramString)
  {
    try
    {
      AdTypeHandShake localAdTypeHandShake = (AdTypeHandShake)this.adTypeHandShakes.get(paramString);
      if (localAdTypeHandShake != null)
        localAdTypeHandShake.downloading = true;
      return;
    }
    finally
    {
    }
  }

  void sendInitRequest()
  {
    requestHandshake(true);
  }

  void setMMdid(Context paramContext, String paramString)
  {
    setMMdid(paramContext, paramString, true);
  }

  void setMMdid(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramString != null);
    try
    {
      if ((paramString.length() == 0) || (paramString.equals("NULL")));
      for (this.mmdid = null; ; this.mmdid = paramString)
      {
        MMSDK.setMMdid(this.mmdid);
        if (paramBoolean)
        {
          SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("MillennialMediaSettings", 0).edit();
          localEditor.putString("handshake_mmdid", this.mmdid);
          localEditor.commit();
        }
        return;
      }
    }
    finally
    {
    }
  }

  void startSession()
  {
    if (!TextUtils.isEmpty(this.startSessionURL))
      Utils.HttpUtils.executeUrl(this.startSessionURL);
  }

  void unlockAdTypeDownload(String paramString)
  {
    try
    {
      AdTypeHandShake localAdTypeHandShake = (AdTypeHandShake)this.adTypeHandShakes.get(paramString);
      if (localAdTypeHandShake != null)
        localAdTypeHandShake.downloading = false;
      return;
    }
    finally
    {
    }
  }

  void updateLastVideoViewedTime(Context paramContext, String paramString)
  {
    try
    {
      AdTypeHandShake localAdTypeHandShake = (AdTypeHandShake)this.adTypeHandShakes.get(paramString);
      if (localAdTypeHandShake != null)
        localAdTypeHandShake.updateLastVideoViewedTime(paramContext, paramString);
      return;
    }
    finally
    {
    }
  }

  private class AdTypeHandShake
  {
    boolean downloading;
    long lastVideo = 0L;
    long videoInterval = 0L;

    AdTypeHandShake()
    {
    }

    boolean canDisplayCachedAd(long paramLong)
    {
      return System.currentTimeMillis() - paramLong < HandShake.this.deferredViewTimeout;
    }

    boolean canRequestVideo(Context paramContext, String paramString)
    {
      Object[] arrayOfObject = new Object[4];
      arrayOfObject[0] = Long.valueOf(System.currentTimeMillis());
      arrayOfObject[1] = Long.valueOf(this.lastVideo / 1000L);
      arrayOfObject[2] = Long.valueOf((System.currentTimeMillis() - this.lastVideo) / 1000L);
      arrayOfObject[3] = Long.valueOf(this.videoInterval / 1000L);
      MMSDK.Log.d("canRequestVideo() Current Time: %d Last Video: %d  Diff: %d  Video interval: %d", arrayOfObject);
      return System.currentTimeMillis() - this.lastVideo > this.videoInterval;
    }

    void deserializeFromObj(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null)
        return;
      this.videoInterval = (1000L * paramJSONObject.optLong("videointerval"));
    }

    boolean load(SharedPreferences paramSharedPreferences, String paramString)
    {
      boolean bool1 = paramSharedPreferences.contains("handshake_lastvideo_" + paramString);
      boolean bool2 = false;
      if (bool1)
      {
        this.lastVideo = paramSharedPreferences.getLong("handshake_lastvideo_" + paramString, this.lastVideo);
        bool2 = true;
      }
      if (paramSharedPreferences.contains("handshake_videointerval_" + paramString))
      {
        this.videoInterval = paramSharedPreferences.getLong("handshake_videointerval_" + paramString, this.videoInterval);
        bool2 = true;
      }
      return bool2;
    }

    void loadLastVideo(Context paramContext, String paramString)
    {
      SharedPreferences localSharedPreferences = paramContext.getSharedPreferences("MillennialMediaSettings", 0);
      if ((localSharedPreferences != null) && (localSharedPreferences.contains("handshake_lastvideo_" + paramString)))
        this.lastVideo = localSharedPreferences.getLong("handshake_lastvideo_" + paramString, this.lastVideo);
    }

    void save(Context paramContext, String paramString)
    {
      SharedPreferences.Editor localEditor = paramContext.getSharedPreferences("MillennialMediaSettings", 0).edit();
      save(localEditor, paramString);
      localEditor.commit();
    }

    void save(SharedPreferences.Editor paramEditor, String paramString)
    {
      paramEditor.putLong("handshake_lastvideo_" + paramString, this.lastVideo);
      paramEditor.putLong("handshake_videointerval_" + paramString, this.videoInterval);
    }

    void updateLastVideoViewedTime(Context paramContext, String paramString)
    {
      this.lastVideo = System.currentTimeMillis();
      save(paramContext, paramString);
    }
  }

  private class Scheme
  {
    int id;
    String scheme;

    Scheme()
    {
    }

    Scheme(String paramInt, int arg3)
    {
      this.scheme = paramInt;
      int i;
      this.id = i;
    }

    boolean checkAvailability(Context paramContext)
    {
      if (this.scheme.contains("://"));
      for (Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(this.scheme)); paramContext.getPackageManager().queryIntentActivities(localIntent, 65536).size() > 0; localIntent = new Intent("android.intent.action.VIEW", Uri.parse(this.scheme + "://")))
        return true;
      return false;
    }

    void deserializeFromObj(JSONObject paramJSONObject)
    {
      if (paramJSONObject == null)
        return;
      this.scheme = paramJSONObject.optString("scheme", null);
      this.id = paramJSONObject.optInt("schemeid");
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.HandShake
 * JD-Core Version:    0.6.2
 */