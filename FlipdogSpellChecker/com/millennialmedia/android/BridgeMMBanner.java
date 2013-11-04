package com.millennialmedia.android;

import android.content.Context;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import java.lang.ref.WeakReference;
import java.util.HashMap;

class BridgeMMBanner extends MMJSObject
{
  int getScreenHeight(Context paramContext)
  {
    return Integer.parseInt(MMSDK.getDpiHeight(paramContext));
  }

  int getScreenWidth(Context paramContext)
  {
    return Integer.parseInt(MMSDK.getDpiWidth(paramContext));
  }

  public MMJSResponse resize(HashMap<String, String> paramHashMap)
  {
    MMWebView localMMWebView = (MMWebView)this.mmWebViewRef.get();
    if (localMMWebView != null)
    {
      if (localMMWebView.isMraidResized())
        return MMJSResponse.responseWithError("State is currently resized");
      String str1 = (String)paramHashMap.get("width");
      String str2 = (String)paramHashMap.get("height");
      boolean bool1 = TextUtils.isEmpty(str1);
      int i = 0;
      if (!bool1)
        i = (int)Float.parseFloat(str1);
      boolean bool2 = TextUtils.isEmpty(str2);
      int j = 0;
      if (!bool2)
        j = (int)Float.parseFloat(str2);
      String str3 = (String)paramHashMap.get("customClosePosition");
      String str4 = (String)paramHashMap.get("offsetX");
      String str5 = (String)paramHashMap.get("offsetY");
      boolean bool3 = TextUtils.isEmpty(str5);
      int k = 0;
      if (!bool3)
        k = (int)Float.parseFloat(str5);
      boolean bool4 = TextUtils.isEmpty(str4);
      int m = 0;
      if (!bool4)
        m = (int)Float.parseFloat(str4);
      boolean bool5 = Boolean.parseBoolean((String)paramHashMap.get("allowOffscreen"));
      Context localContext = localMMWebView.getContext();
      DisplayMetrics localDisplayMetrics = MMSDK.getMetrics(localContext);
      int n = getScreenWidth(localContext);
      int i1 = getScreenHeight(localContext);
      localMMWebView.setMraidResize(new DTOResizeParameters(localDisplayMetrics.density, i, j, str3, m, k, bool5, n, i1));
      return MMJSResponse.responseWithSuccess();
    }
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.BridgeMMBanner
 * JD-Core Version:    0.6.2
 */