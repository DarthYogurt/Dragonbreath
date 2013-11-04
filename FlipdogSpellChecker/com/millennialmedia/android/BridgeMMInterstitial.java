package com.millennialmedia.android;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import java.lang.ref.WeakReference;
import java.util.HashMap;

class BridgeMMInterstitial extends MMJSObject
{
  private Intent getExpandExtrasIntent(String paramString, OverlaySettings paramOverlaySettings)
  {
    Intent localIntent = new Intent();
    if (paramString != null)
      localIntent.setData(Uri.parse(paramString));
    localIntent.putExtra("settings", paramOverlaySettings);
    localIntent.putExtra("internalId", paramOverlaySettings.creatorAdImplId);
    return localIntent;
  }

  private boolean isForcingOrientation(MMJSResponse paramMMJSResponse)
  {
    int i = paramMMJSResponse.result;
    boolean bool1 = false;
    if (i == 1)
    {
      boolean bool2 = paramMMJSResponse.response instanceof String;
      bool1 = false;
      if (bool2)
      {
        String str = (String)paramMMJSResponse.response;
        if (!str.contains("portrait"))
        {
          boolean bool3 = str.contains("landscape");
          bool1 = false;
          if (!bool3);
        }
        else
        {
          bool1 = true;
        }
      }
    }
    return bool1;
  }

  private MMJSResponse setAllowOrientationChange(HashMap<String, String> paramHashMap)
  {
    String str = (String)paramHashMap.get("allowOrientationChange");
    if (str != null)
    {
      AdViewOverlayActivity localAdViewOverlayActivity = getBaseActivity();
      if (localAdViewOverlayActivity != null)
      {
        localAdViewOverlayActivity.setAllowOrientationChange(Boolean.parseBoolean(str));
        return MMJSResponse.responseWithSuccess();
      }
    }
    return null;
  }

  private MMJSResponse setForceOrientation(HashMap<String, String> paramHashMap)
  {
    String str = (String)paramHashMap.get("forceOrientation");
    if (!"none".equals(str))
    {
      AdViewOverlayActivity localAdViewOverlayActivity = getBaseActivity();
      if (localAdViewOverlayActivity != null)
      {
        if ("portrait".equals(str))
        {
          localAdViewOverlayActivity.setRequestedOrientationPortrait();
          return MMJSResponse.responseWithSuccess("portrait");
        }
        if ("landscape".equals(str))
        {
          localAdViewOverlayActivity.setRequestedOrientationLandscape();
          return MMJSResponse.responseWithSuccess("landscape");
        }
      }
    }
    return null;
  }

  public MMJSResponse close(HashMap<String, String> paramHashMap)
  {
    MMWebView localMMWebView = (MMWebView)this.mmWebViewRef.get();
    if (localMMWebView != null)
    {
      localMMWebView.getMMLayout().closeAreaTouched();
      return MMJSResponse.responseWithSuccess();
    }
    return null;
  }

  public MMJSResponse expandToExternalBrowser(HashMap<String, String> paramHashMap)
  {
    return open(paramHashMap);
  }

  public MMJSResponse expandWithProperties(HashMap<String, String> paramHashMap)
  {
    String str1 = (String)paramHashMap.get("PROPERTY_BANNER_TYPE");
    if ((str1 != null) && (!Boolean.parseBoolean(str1)))
      return MMJSResponse.responseWithError("Cannot expand a non banner ad");
    String str2 = (String)paramHashMap.get("url");
    String str3 = (String)paramHashMap.get("enableNativeAccelerometer");
    String str4 = (String)paramHashMap.get("transparent");
    String str5 = (String)paramHashMap.get("useCustomClose");
    String str6 = (String)paramHashMap.get("transition");
    String str7 = (String)paramHashMap.get("orientation");
    String str8 = (String)paramHashMap.get("transitionDuration");
    String str9 = (String)paramHashMap.get("height");
    String str10 = (String)paramHashMap.get("width");
    String str11 = (String)paramHashMap.get("modal");
    String str12 = (String)paramHashMap.get("PROPERTY_EXPANDING");
    String str13 = (String)paramHashMap.get("allowOrientationChange");
    Context localContext = (Context)this.contextRef.get();
    OverlaySettings localOverlaySettings;
    if (localContext != null)
    {
      localOverlaySettings = new OverlaySettings();
      if (str2 != null)
        localOverlaySettings.urlToLoad = str2;
      if (str12 != null)
        localOverlaySettings.creatorAdImplId = ((int)Float.parseFloat(str12));
      if (str3 != null)
        localOverlaySettings.canAccelerate = Boolean.parseBoolean(str3);
      if (str4 != null)
        localOverlaySettings.setIsTransparent(Boolean.parseBoolean(str4));
      if (str5 != null)
        localOverlaySettings.setUseCustomClose(Boolean.parseBoolean(str5));
      if (str6 != null)
        localOverlaySettings.setTransition(str6);
      if (str13 != null)
        localOverlaySettings.allowOrientationChange = Boolean.parseBoolean(str13);
      if (str7 == null)
        str7 = (String)paramHashMap.get("forceOrientation");
      if (str7 != null)
        localOverlaySettings.orientation = str7;
      if (str9 != null)
        localOverlaySettings.height = ((int)Float.parseFloat(str9));
      if (str10 != null)
        localOverlaySettings.width = ((int)Float.parseFloat(str10));
      if (str11 != null)
        localOverlaySettings.modal = Boolean.parseBoolean(str11);
      if (str8 == null);
    }
    try
    {
      localOverlaySettings.setTransitionDurationInMillis(1000L * Long.parseLong(str8));
      label377: Utils.IntentUtils.startAdViewOverlayActivity(localContext, getExpandExtrasIntent(str2, localOverlaySettings));
      MMSDK.Event.overlayOpenedBroadCast(localContext, getAdImplId((String)paramHashMap.get("PROPERTY_EXPANDING")));
      return MMJSResponse.responseWithSuccess();
      return null;
    }
    catch (Exception localException)
    {
      break label377;
    }
  }

  public MMJSResponse open(HashMap<String, String> paramHashMap)
  {
    String str = (String)paramHashMap.get("url");
    Context localContext = (Context)this.contextRef.get();
    if ((str != null) && (localContext != null))
    {
      Intent localIntent = new Intent("android.intent.action.VIEW", Uri.parse(str));
      MMSDK.Event.intentStarted(localContext, "browser", getAdImplId((String)paramHashMap.get("PROPERTY_EXPANDING")));
      Utils.IntentUtils.startActivity(localContext, localIntent);
      return MMJSResponse.responseWithSuccess();
    }
    return null;
  }

  public MMJSResponse setOrientation(HashMap<String, String> paramHashMap)
  {
    MMJSResponse localMMJSResponse = setForceOrientation(paramHashMap);
    if ((localMMJSResponse == null) || (!isForcingOrientation(localMMJSResponse)))
      localMMJSResponse = setAllowOrientationChange(paramHashMap);
    return localMMJSResponse;
  }

  public MMJSResponse show(HashMap<String, String> paramHashMap)
  {
    String str = (String)paramHashMap.get("url");
    Context localContext = (Context)this.contextRef.get();
    if ((str != null) && (localContext != null))
    {
      Utils.IntentUtils.startAdViewOverlayActivityWithData(localContext, str);
      return MMJSResponse.responseWithSuccess();
    }
    return null;
  }

  public MMJSResponse useCustomClose(HashMap<String, String> paramHashMap)
  {
    MMWebView localMMWebView = (MMWebView)this.mmWebViewRef.get();
    String str = (String)paramHashMap.get("useCustomClose");
    if ((str != null) && (localMMWebView != null))
    {
      AdViewOverlayView localAdViewOverlayView = localMMWebView.getAdViewOverlayView();
      if (localAdViewOverlayView != null)
      {
        localAdViewOverlayView.setUseCustomClose(Boolean.parseBoolean(str));
        return MMJSResponse.responseWithSuccess();
      }
    }
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMInterstitial
 * JD-Core Version:    0.6.2
 */