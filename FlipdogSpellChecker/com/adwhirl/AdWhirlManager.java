package com.adwhirl;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationManager;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.adwhirl.obj.Custom;
import com.adwhirl.obj.Extra;
import com.adwhirl.obj.Ration;
import com.adwhirl.util.AdWhirlUtil;
import com.flipdog.commons.diagnostic.Track;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AdWhirlManager
{
  private static final String PREFS_STRING_CONFIG = "config";
  private static final String PREFS_STRING_TIMESTAMP = "timestamp";
  private static long configExpireTimeout = 1800000L;
  private WeakReference<Context> contextReference;
  public String deviceIDHash;
  private Extra extra;
  public String keyAdWhirl;
  public String localeString;
  public Location location;
  private List<Ration> rationsList;
  Iterator<Ration> rollovers;
  private double totalWeight = 0.0D;

  public AdWhirlManager(WeakReference<Context> paramWeakReference, String paramString)
  {
    track("Creating adWhirlManager...");
    this.contextReference = paramWeakReference;
    this.keyAdWhirl = paramString;
    this.localeString = Locale.getDefault().toString();
    track("Locale is: " + this.localeString);
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance("MD5");
      StringBuffer localStringBuffer = new StringBuffer("android_id");
      localStringBuffer.append("AdWhirl");
      this.deviceIDHash = AdWhirlUtil.convertToHex(localMessageDigest.digest(localStringBuffer.toString().getBytes()));
      track("Hashed device ID is: " + this.deviceIDHash);
      track("Finished creating adWhirlManager");
      return;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      while (true)
        this.deviceIDHash = "00000000000000000000000000000000";
    }
  }

  // ERROR //
  private String convertStreamToString(InputStream paramInputStream)
  {
    // Byte code:
    //   0: new 127	java/io/BufferedReader
    //   3: dup
    //   4: new 129	java/io/InputStreamReader
    //   7: dup
    //   8: aload_1
    //   9: invokespecial 132	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   12: sipush 8192
    //   15: invokespecial 135	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   18: astore_2
    //   19: new 69	java/lang/StringBuilder
    //   22: dup
    //   23: invokespecial 136	java/lang/StringBuilder:<init>	()V
    //   26: astore_3
    //   27: aload_2
    //   28: invokevirtual 139	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   31: astore 8
    //   33: aload 8
    //   35: ifnonnull +12 -> 47
    //   38: aload_1
    //   39: invokevirtual 144	java/io/InputStream:close	()V
    //   42: aload_3
    //   43: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   46: areturn
    //   47: aload_3
    //   48: new 69	java/lang/StringBuilder
    //   51: dup
    //   52: aload 8
    //   54: invokestatic 148	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   57: invokespecial 73	java/lang/StringBuilder:<init>	(Ljava/lang/String;)V
    //   60: ldc 150
    //   62: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: invokevirtual 78	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   68: invokevirtual 77	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   71: pop
    //   72: goto -45 -> 27
    //   75: astore 6
    //   77: ldc 152
    //   79: aload 6
    //   81: invokestatic 155	com/adwhirl/AdWhirlManager:track	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   84: aload_1
    //   85: invokevirtual 144	java/io/InputStream:close	()V
    //   88: aconst_null
    //   89: areturn
    //   90: astore 7
    //   92: ldc 152
    //   94: aload 7
    //   96: invokestatic 155	com/adwhirl/AdWhirlManager:track	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   99: aconst_null
    //   100: areturn
    //   101: astore 4
    //   103: aload_1
    //   104: invokevirtual 144	java/io/InputStream:close	()V
    //   107: aload 4
    //   109: athrow
    //   110: astore 5
    //   112: ldc 152
    //   114: aload 5
    //   116: invokestatic 155	com/adwhirl/AdWhirlManager:track	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   119: aconst_null
    //   120: areturn
    //   121: astore 10
    //   123: ldc 152
    //   125: aload 10
    //   127: invokestatic 155	com/adwhirl/AdWhirlManager:track	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   130: aconst_null
    //   131: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   27	33	75	java/io/IOException
    //   47	72	75	java/io/IOException
    //   84	88	90	java/io/IOException
    //   27	33	101	finally
    //   47	72	101	finally
    //   77	84	101	finally
    //   103	107	110	java/io/IOException
    //   38	42	121	java/io/IOException
  }

  private Drawable fetchImage(String paramString)
  {
    try
    {
      Drawable localDrawable = Drawable.createFromStream((InputStream)new URL(paramString).getContent(), "src");
      return localDrawable;
    }
    catch (Exception localException)
    {
      track("Unable to fetchImage(): ", localException);
    }
    return null;
  }

  private void parseConfigurationString(String paramString)
  {
    track("Received jsonString: " + paramString);
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      parseExtraJson(localJSONObject.getJSONObject("extra"));
      parseRationsJson(localJSONObject.getJSONArray("rations"));
      return;
    }
    catch (JSONException localJSONException)
    {
      track("Unable to parse response from JSON. This may or may not be fatal.", localJSONException);
      this.extra = new Extra();
      return;
    }
    catch (NullPointerException localNullPointerException)
    {
      track("Unable to parse response from JSON. This may or may not be fatal.", localNullPointerException);
      this.extra = new Extra();
    }
  }

  private Custom parseCustomJsonString(String paramString)
  {
    track("Received custom jsonString: " + paramString);
    Custom localCustom = new Custom();
    try
    {
      JSONObject localJSONObject = new JSONObject(paramString);
      localCustom.type = localJSONObject.getInt("ad_type");
      localCustom.imageLink = localJSONObject.getString("img_url");
      localCustom.link = localJSONObject.getString("redirect_url");
      localCustom.description = localJSONObject.getString("ad_text");
      try
      {
        localCustom.imageLink480x75 = localJSONObject.getString("img_url_480x75");
        DisplayMetrics localDisplayMetrics = new DisplayMetrics();
        ((WindowManager)((Context)this.contextReference.get()).getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
        if ((localDisplayMetrics.density == 1.5D) && (localCustom.type == 1) && (localCustom.imageLink480x75 != null) && (localCustom.imageLink480x75.length() != 0))
        {
          localCustom.image = fetchImage(localCustom.imageLink480x75);
          return localCustom;
        }
      }
      catch (JSONException localJSONException2)
      {
        while (true)
          localCustom.imageLink480x75 = null;
      }
    }
    catch (JSONException localJSONException1)
    {
      track("Caught JSONException in parseCustomJsonString()", localJSONException1);
      return null;
    }
    localCustom.image = fetchImage(localCustom.imageLink);
    return localCustom;
  }

  private void parseExtraJson(JSONObject paramJSONObject)
  {
    Extra localExtra = new Extra();
    try
    {
      localExtra.cycleTime = paramJSONObject.getInt("cycle_time");
      localExtra.locationOn = paramJSONObject.getInt("location_on");
      localExtra.transition = paramJSONObject.getInt("transition");
      JSONObject localJSONObject1 = paramJSONObject.getJSONObject("background_color_rgb");
      localExtra.bgRed = localJSONObject1.getInt("red");
      localExtra.bgGreen = localJSONObject1.getInt("green");
      localExtra.bgBlue = localJSONObject1.getInt("blue");
      localExtra.bgAlpha = (255 * localJSONObject1.getInt("alpha"));
      JSONObject localJSONObject2 = paramJSONObject.getJSONObject("text_color_rgb");
      localExtra.fgRed = localJSONObject2.getInt("red");
      localExtra.fgGreen = localJSONObject2.getInt("green");
      localExtra.fgBlue = localJSONObject2.getInt("blue");
      localExtra.fgAlpha = (255 * localJSONObject2.getInt("alpha"));
      this.extra = localExtra;
      return;
    }
    catch (JSONException localJSONException)
    {
      while (true)
        track("Exception in parsing config.extra JSON. This may or may not be fatal.", localJSONException);
    }
  }

  private void parseRationsJson(JSONArray paramJSONArray)
  {
    ArrayList localArrayList = new ArrayList();
    this.totalWeight = 0.0D;
    for (int i = 0; ; i++)
      try
      {
        int j = paramJSONArray.length();
        if (i >= j)
        {
          Collections.sort(localArrayList);
          this.rationsList = localArrayList;
          this.rollovers = this.rationsList.iterator();
          return;
        }
        localJSONObject1 = paramJSONArray.getJSONObject(i);
        if (localJSONObject1 != null)
        {
          localRation = new Ration();
          localRation.nid = localJSONObject1.getString("nid");
          localRation.type = localJSONObject1.getInt("type");
          localRation.name = localJSONObject1.getString("nname");
          localRation.weight = localJSONObject1.getInt("weight");
          localRation.priority = localJSONObject1.getInt("priority");
          switch (localRation.type)
          {
          default:
            localRation.key = localJSONObject1.getString("key");
            this.totalWeight += localRation.weight;
            localArrayList.add(localRation);
          case 8:
          case 24:
          }
        }
      }
      catch (JSONException localJSONException)
      {
        while (true)
        {
          JSONObject localJSONObject1;
          Ration localRation;
          track("JSONException in parsing config.rations JSON. This may or may not be fatal.", localJSONException);
          continue;
          JSONObject localJSONObject3 = localJSONObject1.getJSONObject("key");
          localRation.key = localJSONObject3.getString("siteID");
          localRation.key2 = localJSONObject3.getString("publisherID");
          continue;
          JSONObject localJSONObject2 = localJSONObject1.getJSONObject("key");
          localRation.key = localJSONObject2.getString("dcn");
          localRation.key2 = localJSONObject2.getString("position");
        }
      }
  }

  public static void setConfigExpireTimeout(long paramLong)
  {
    configExpireTimeout = paramLong;
  }

  protected static void track(String paramString)
  {
    Track.it(paramString, new String[] { "AdWhirl" });
  }

  protected static void track(String paramString, Throwable paramThrowable)
  {
    Track.it(paramString, new String[] { "AdWhirl" });
    Track.it(paramThrowable, new String[] { "AdWhirl" });
  }

  public void fetchConfig()
  {
    Context localContext = (Context)this.contextReference.get();
    if (localContext == null)
      return;
    SharedPreferences localSharedPreferences = localContext.getSharedPreferences(this.keyAdWhirl, 0);
    String str = localSharedPreferences.getString("config", null);
    long l = localSharedPreferences.getLong("timestamp", -1L);
    track("Prefs{" + this.keyAdWhirl + "}: {\"" + "config" + "\": \"" + str + "\", \"" + "timestamp" + "\": " + l + "}");
    DefaultHttpClient localDefaultHttpClient;
    HttpGet localHttpGet;
    if ((str == null) || (configExpireTimeout == -1L) || (System.currentTimeMillis() >= l + configExpireTimeout))
    {
      track("Stored config info not present or expired, fetching fresh data");
      localDefaultHttpClient = new DefaultHttpClient();
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.keyAdWhirl;
      arrayOfObject[1] = Integer.valueOf(311);
      localHttpGet = new HttpGet(String.format("http://mob.adwhirl.com/getInfo.php?appid=%s&appver=%d&client=2", arrayOfObject));
    }
    while (true)
    {
      try
      {
        HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpGet);
        track(localHttpResponse.getStatusLine().toString());
        HttpEntity localHttpEntity = localHttpResponse.getEntity();
        if (localHttpEntity != null)
        {
          str = convertStreamToString(localHttpEntity.getContent());
          SharedPreferences.Editor localEditor = localSharedPreferences.edit();
          localEditor.putString("config", str);
          localEditor.putLong("timestamp", System.currentTimeMillis());
          localEditor.commit();
        }
        parseConfigurationString(str);
        return;
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        track("Caught ClientProtocolException in fetchConfig()", localClientProtocolException);
        continue;
      }
      catch (IOException localIOException)
      {
        track("Caught IOException in fetchConfig()", localIOException);
        continue;
      }
      track("Using stored config data");
    }
  }

  public Custom getCustom(String paramString)
  {
    DefaultHttpClient localDefaultHttpClient = new DefaultHttpClient();
    String str;
    if (this.extra.locationOn == 1)
    {
      this.location = getLocation();
      if (this.location != null)
      {
        Object[] arrayOfObject2 = new Object[3];
        arrayOfObject2[0] = Double.valueOf(this.location.getLatitude());
        arrayOfObject2[1] = Double.valueOf(this.location.getLongitude());
        arrayOfObject2[2] = Long.valueOf(this.location.getTime());
        str = String.format("&location=%f,%f&location_timestamp=%d", arrayOfObject2);
      }
    }
    while (true)
    {
      Object[] arrayOfObject1 = new Object[6];
      arrayOfObject1[0] = this.keyAdWhirl;
      arrayOfObject1[1] = paramString;
      arrayOfObject1[2] = this.deviceIDHash;
      arrayOfObject1[3] = this.localeString;
      arrayOfObject1[4] = str;
      arrayOfObject1[5] = Integer.valueOf(311);
      HttpGet localHttpGet = new HttpGet(String.format("http://cus.adwhirl.com/custom.php?appid=%s&nid=%s&uuid=%s&country_code=%s%s&appver=%d&client=2", arrayOfObject1));
      try
      {
        HttpResponse localHttpResponse = localDefaultHttpClient.execute(localHttpGet);
        track(localHttpResponse.getStatusLine().toString());
        HttpEntity localHttpEntity = localHttpResponse.getEntity();
        if (localHttpEntity != null)
        {
          Custom localCustom = parseCustomJsonString(convertStreamToString(localHttpEntity.getContent()));
          return localCustom;
          str = "";
          continue;
          this.location = null;
          str = "";
        }
      }
      catch (ClientProtocolException localClientProtocolException)
      {
        track("Caught ClientProtocolException in getCustom()", localClientProtocolException);
        return null;
      }
      catch (IOException localIOException)
      {
        while (true)
          track("Caught IOException in getCustom()", localIOException);
      }
    }
  }

  public Extra getExtra()
  {
    if (this.totalWeight <= 0.0D)
    {
      track("Sum of ration weights is 0 - no ads to be shown");
      return null;
    }
    return this.extra;
  }

  public Location getLocation()
  {
    if (this.contextReference == null);
    Context localContext;
    do
    {
      do
      {
        return null;
        localContext = (Context)this.contextReference.get();
      }
      while (localContext == null);
      if (localContext.checkCallingOrSelfPermission("android.permission.ACCESS_FINE_LOCATION") == 0)
        return ((LocationManager)localContext.getSystemService("location")).getLastKnownLocation("gps");
    }
    while (localContext.checkCallingOrSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0);
    return ((LocationManager)localContext.getSystemService("location")).getLastKnownLocation("network");
  }

  public Ration getRation()
  {
    double d1 = new Random().nextDouble() * this.totalWeight;
    double d2 = 0.0D;
    track("Dart is <" + d1 + "> of <" + this.totalWeight + ">");
    Iterator localIterator = this.rationsList.iterator();
    Ration localRation = null;
    do
    {
      if (!localIterator.hasNext())
        return localRation;
      localRation = (Ration)localIterator.next();
      d2 += localRation.weight;
    }
    while (d2 < d1);
    return localRation;
  }

  public Ration getRollover()
  {
    if (this.rollovers == null);
    while (!this.rollovers.hasNext())
      return null;
    return (Ration)this.rollovers.next();
  }

  public void resetRollover()
  {
    this.rollovers = this.rationsList.iterator();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.adwhirl.AdWhirlManager
 * JD-Core Version:    0.6.2
 */