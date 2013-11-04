package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.location.Location;
import android.net.Uri;
import android.view.Display;
import android.view.WindowManager;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

class BridgeMMDevice extends MMJSObject
{
  // ERROR //
  static JSONObject getDeviceInfo(Context paramContext)
  {
    // Byte code:
    //   0: new 14	org/json/JSONObject
    //   3: dup
    //   4: invokespecial 15	org/json/JSONObject:<init>	()V
    //   7: astore_1
    //   8: aload_1
    //   9: ldc 17
    //   11: ldc 19
    //   13: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   16: pop
    //   17: aload_1
    //   18: ldc 25
    //   20: ldc 19
    //   22: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   25: pop
    //   26: aload_1
    //   27: ldc 27
    //   29: aload_0
    //   30: invokestatic 33	com/millennialmedia/android/MMSDK:getConnectionType	(Landroid/content/Context;)Ljava/lang/String;
    //   33: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   36: pop
    //   37: aload_1
    //   38: ldc 35
    //   40: ldc 37
    //   42: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   45: pop
    //   46: getstatic 43	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   49: ifnull +13 -> 62
    //   52: aload_1
    //   53: ldc 45
    //   55: getstatic 43	android/os/Build$VERSION:RELEASE	Ljava/lang/String;
    //   58: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   61: pop
    //   62: getstatic 50	android/os/Build:MODEL	Ljava/lang/String;
    //   65: ifnull +13 -> 78
    //   68: aload_1
    //   69: ldc 52
    //   71: getstatic 50	android/os/Build:MODEL	Ljava/lang/String;
    //   74: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   77: pop
    //   78: aload_1
    //   79: ldc 54
    //   81: aload_0
    //   82: invokestatic 57	com/millennialmedia/android/MMSDK:getMMdid	(Landroid/content/Context;)Ljava/lang/String;
    //   85: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   88: pop
    //   89: aload_0
    //   90: invokevirtual 63	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   93: invokevirtual 69	android/content/res/Resources:getDisplayMetrics	()Landroid/util/DisplayMetrics;
    //   96: astore 8
    //   98: aload_1
    //   99: ldc 71
    //   101: new 73	java/lang/Float
    //   104: dup
    //   105: aload 8
    //   107: getfield 78	android/util/DisplayMetrics:density	F
    //   110: invokespecial 81	java/lang/Float:<init>	(F)V
    //   113: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   116: pop
    //   117: aload_1
    //   118: ldc 83
    //   120: new 85	java/lang/Integer
    //   123: dup
    //   124: aload 8
    //   126: getfield 89	android/util/DisplayMetrics:heightPixels	I
    //   129: invokespecial 92	java/lang/Integer:<init>	(I)V
    //   132: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   135: pop
    //   136: aload_1
    //   137: ldc 94
    //   139: new 85	java/lang/Integer
    //   142: dup
    //   143: aload 8
    //   145: getfield 97	android/util/DisplayMetrics:widthPixels	I
    //   148: invokespecial 92	java/lang/Integer:<init>	(I)V
    //   151: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   154: pop
    //   155: invokestatic 103	java/util/Locale:getDefault	()Ljava/util/Locale;
    //   158: astore 12
    //   160: aload 12
    //   162: ifnull +27 -> 189
    //   165: aload_1
    //   166: ldc 105
    //   168: aload 12
    //   170: invokevirtual 109	java/util/Locale:getLanguage	()Ljava/lang/String;
    //   173: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   176: pop
    //   177: aload_1
    //   178: ldc 111
    //   180: aload 12
    //   182: invokevirtual 114	java/util/Locale:getCountry	()Ljava/lang/String;
    //   185: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   188: pop
    //   189: new 14	org/json/JSONObject
    //   192: dup
    //   193: invokespecial 15	org/json/JSONObject:<init>	()V
    //   196: astore 15
    //   198: aload 15
    //   200: ldc 116
    //   202: ldc 118
    //   204: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   207: pop
    //   208: aload 15
    //   210: ldc 120
    //   212: ldc 122
    //   214: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   217: pop
    //   218: aload 15
    //   220: ldc 124
    //   222: getstatic 127	com/millennialmedia/android/MMSDK:macId	Ljava/lang/String;
    //   225: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   228: pop
    //   229: new 129	org/json/JSONArray
    //   232: dup
    //   233: invokespecial 130	org/json/JSONArray:<init>	()V
    //   236: astore 20
    //   238: aload 20
    //   240: aload 15
    //   242: invokevirtual 133	org/json/JSONArray:put	(Ljava/lang/Object;)Lorg/json/JSONArray;
    //   245: pop
    //   246: aload_1
    //   247: ldc 135
    //   249: aload 20
    //   251: invokevirtual 23	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   254: pop
    //   255: aload_1
    //   256: areturn
    //   257: astore 26
    //   259: aconst_null
    //   260: areturn
    //   261: astore_2
    //   262: aload_1
    //   263: areturn
    //   264: astore 16
    //   266: aload_1
    //   267: areturn
    //   268: astore 21
    //   270: aload_1
    //   271: areturn
    //
    // Exception table:
    //   from	to	target	type
    //   0	8	257	org/json/JSONException
    //   8	62	261	org/json/JSONException
    //   62	78	261	org/json/JSONException
    //   78	160	261	org/json/JSONException
    //   165	189	261	org/json/JSONException
    //   189	198	261	org/json/JSONException
    //   198	238	264	org/json/JSONException
    //   238	255	268	org/json/JSONException
  }

  public MMJSResponse call(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramHashMap.get("number");
    if ((localContext != null) && (str != null))
    {
      MMSDK.Log.d("Dialing Phone: %s", new Object[] { str });
      if ((Boolean.parseBoolean((String)paramHashMap.get("dial"))) && (localContext.checkCallingOrSelfPermission("android.permission.CALL_PHONE") == 0));
      for (Intent localIntent = new Intent("android.intent.action.CALL", Uri.parse("tel:" + str)); ; localIntent = new Intent("android.intent.action.VIEW", Uri.parse("tel:" + str)))
      {
        Utils.IntentUtils.startActivity(localContext, localIntent);
        MMSDK.Event.intentStarted(localContext, "tel", getAdImplId((String)paramHashMap.get("PROPERTY_EXPANDING")));
        return MMJSResponse.responseWithSuccess();
      }
    }
    return null;
  }

  public MMJSResponse composeEmail(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str1 = (String)paramHashMap.get("recipients");
    String str2 = (String)paramHashMap.get("subject");
    String str3 = (String)paramHashMap.get("message");
    if (localContext != null)
    {
      MMSDK.Log.d("Creating email");
      Intent localIntent = new Intent("android.intent.action.SEND");
      localIntent.setType("plain/text");
      if (str1 != null)
        localIntent.putExtra("android.intent.extra.EMAIL", str1.split(","));
      if (str2 != null)
        localIntent.putExtra("android.intent.extra.SUBJECT", str2);
      if (str3 != null)
        localIntent.putExtra("android.intent.extra.TEXT", str3);
      Utils.IntentUtils.startActivity(localContext, localIntent);
      MMSDK.Event.intentStarted(localContext, "email", getAdImplId((String)paramHashMap.get("PROPERTY_EXPANDING")));
      return MMJSResponse.responseWithSuccess();
    }
    return null;
  }

  public MMJSResponse composeSms(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str1 = (String)paramHashMap.get("number");
    String str2 = (String)paramHashMap.get("message");
    if ((localContext != null) && (str1 != null))
    {
      MMSDK.Log.d("Creating sms: %s", new Object[] { str1 });
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse("sms:" + str1));
      if (str2 != null)
        localIntent.putExtra("sms_body", str2);
      Utils.IntentUtils.startActivity(localContext, localIntent);
      MMSDK.Event.intentStarted(localContext, "sms", getAdImplId((String)paramHashMap.get("PROPERTY_EXPANDING")));
      return MMJSResponse.responseWithSuccess("SMS Sent");
    }
    return null;
  }

  public MMJSResponse enableHardwareAcceleration(HashMap<String, String> paramHashMap)
  {
    MMSDK.Log.d("hardware accel call" + paramHashMap);
    String str = (String)paramHashMap.get("enabled");
    MMWebView localMMWebView = (MMWebView)this.mmWebViewRef.get();
    if ((localMMWebView != null) && (localMMWebView != null))
    {
      if (Boolean.parseBoolean(str))
        localMMWebView.enableHardwareAcceleration();
      while (true)
      {
        return MMJSResponse.responseWithSuccess();
        localMMWebView.disableAllAcceleration();
      }
    }
    return null;
  }

  public MMJSResponse getAvailableSchemes(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      HandShake localHandShake = HandShake.sharedHandShake(localContext);
      MMJSResponse localMMJSResponse = new MMJSResponse();
      localMMJSResponse.result = 1;
      localMMJSResponse.response = localHandShake.getSchemesJSONArray(localContext);
      return localMMJSResponse;
    }
    return null;
  }

  public MMJSResponse getInfo(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      MMJSResponse localMMJSResponse = new MMJSResponse();
      localMMJSResponse.result = 1;
      localMMJSResponse.response = getDeviceInfo(localContext);
      return localMMJSResponse;
    }
    return null;
  }

  public MMJSResponse getLocation(HashMap<String, String> paramHashMap)
  {
    if (MMRequest.location != null);
    try
    {
      localJSONObject1 = new JSONObject();
    }
    catch (JSONException localJSONException2)
    {
      try
      {
        localJSONObject1.put("lat", Double.toString(MMRequest.location.getLatitude()));
        localJSONObject1.put("long", Double.toString(MMRequest.location.getLongitude()));
        if (MMRequest.location.hasAccuracy())
        {
          localJSONObject1.put("ha", Float.toString(MMRequest.location.getAccuracy()));
          localJSONObject1.put("va", Float.toString(MMRequest.location.getAccuracy()));
        }
        if (MMRequest.location.hasSpeed())
          localJSONObject1.put("spd", Float.toString(MMRequest.location.getSpeed()));
        if (MMRequest.location.hasBearing())
          localJSONObject1.put("brg", Float.toString(MMRequest.location.getBearing()));
        if (MMRequest.location.hasAltitude())
          localJSONObject1.put("alt", Double.toString(MMRequest.location.getAltitude()));
        localJSONObject1.put("tslr", Long.toString(MMRequest.location.getTime()));
        for (localJSONObject2 = localJSONObject1; ; localJSONObject2 = null)
        {
          MMJSResponse localMMJSResponse = new MMJSResponse();
          localMMJSResponse.result = 1;
          localMMJSResponse.response = localJSONObject2;
          return localMMJSResponse;
          return MMJSResponse.responseWithError("location object has not been set");
          localJSONException2 = localJSONException2;
        }
      }
      catch (JSONException localJSONException1)
      {
        while (true)
        {
          JSONObject localJSONObject1;
          JSONObject localJSONObject2 = localJSONObject1;
        }
      }
    }
  }

  public MMJSResponse getOrientation(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      int i = localContext.getResources().getConfiguration().orientation;
      if (i == 0)
        i = ((WindowManager)localContext.getSystemService("window")).getDefaultDisplay().getOrientation();
      MMJSResponse localMMJSResponse = new MMJSResponse();
      localMMJSResponse.result = 1;
      switch (i)
      {
      default:
        localMMJSResponse.response = "portrait";
        return localMMJSResponse;
      case 2:
      }
      localMMJSResponse.response = "landscape";
      return localMMJSResponse;
    }
    return null;
  }

  public MMJSResponse isSchemeAvailable(HashMap<String, String> paramHashMap)
  {
    String str = (String)paramHashMap.get("scheme");
    if (!str.contains(":"))
      str = str + ":";
    Context localContext = (Context)this.contextRef.get();
    if ((str != null) && (localContext != null))
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str));
      if (localContext.getPackageManager().queryIntentActivities(localIntent, 65536).size() > 0)
        return MMJSResponse.responseWithSuccess(str);
    }
    return MMJSResponse.responseWithError(str);
  }

  public MMJSResponse openAppStore(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str1 = (String)paramHashMap.get("appId");
    String str2 = (String)paramHashMap.get("referrer");
    if ((localContext != null) && (str1 != null))
    {
      MMSDK.Log.d("Opening marketplace: %s", new Object[] { str1 });
      Intent localIntent = new Intent("android.intent.action.VIEW");
      if (str2 != null)
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = str1;
        arrayOfObject[1] = URLEncoder.encode(str2);
        localIntent.setData(Uri.parse(String.format("market://details?id=%s&referrer=%s", arrayOfObject)));
      }
      while (true)
      {
        MMSDK.Event.intentStarted(localContext, "market", getAdImplId((String)paramHashMap.get("PROPERTY_EXPANDING")));
        Utils.IntentUtils.startActivity(localContext, localIntent);
        return MMJSResponse.responseWithSuccess();
        localIntent.setData(Uri.parse("market://details?id=" + str1));
      }
    }
    return null;
  }

  public MMJSResponse openUrl(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramHashMap.get("url");
    if ((localContext != null) && (str != null))
    {
      MMSDK.Log.d("Opening: %s", new Object[] { str });
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str));
      if ((localIntent.getScheme().startsWith("http")) || (localIntent.getScheme().startsWith("https")))
        MMSDK.Event.intentStarted(localContext, "browser", getAdImplId((String)paramHashMap.get("PROPERTY_EXPANDING")));
      Utils.IntentUtils.startActivity(localContext, localIntent);
      return MMJSResponse.responseWithSuccess("Overlay opened");
    }
    return MMJSResponse.responseWithError("URL could not be opened");
  }

  public MMJSResponse setMMDID(HashMap<String, String> paramHashMap)
  {
    String str = (String)paramHashMap.get("mmdid");
    Context localContext = (Context)this.contextRef.get();
    if (localContext != null)
    {
      HandShake.sharedHandShake(localContext).setMMdid(localContext, str);
      return MMJSResponse.responseWithSuccess("MMDID is set");
    }
    return null;
  }

  public MMJSResponse showMap(HashMap<String, String> paramHashMap)
  {
    Context localContext = (Context)this.contextRef.get();
    String str = (String)paramHashMap.get("location");
    if ((localContext != null) && (str != null))
    {
      MMSDK.Log.d("Launching Google Maps: %s", new Object[] { str });
      Utils.IntentUtils.startActivity(localContext, new Intent("android.intent.action.VIEW", Uri.parse("geo:" + str)));
      MMSDK.Event.intentStarted(localContext, "geo", getAdImplId((String)paramHashMap.get("PROPERTY_EXPANDING")));
      return MMJSResponse.responseWithSuccess("Map successfully opened");
    }
    return null;
  }

  public MMJSResponse tweet(HashMap<String, String> paramHashMap)
  {
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMDevice
 * JD-Core Version:    0.6.2
 */