package com.google.ads;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.ads.internal.AdWebView;
import com.google.ads.internal.a;
import com.google.ads.internal.d;
import com.google.ads.internal.h;
import com.google.ads.internal.k;
import com.google.ads.util.AdUtil;
import com.google.ads.util.f;
import com.google.ads.util.i.b;
import com.google.ads.util.i.c;
import java.util.HashSet;
import java.util.Set;

public class AdView extends RelativeLayout
  implements Ad
{
  private static final a b = (a)a.a.b();
  protected d a;

  public AdView(Activity paramActivity, AdSize paramAdSize, String paramString)
  {
    super(paramActivity.getApplicationContext());
    try
    {
      a(paramActivity, paramAdSize, null);
      b(paramActivity, paramAdSize, null);
      a(paramActivity, paramAdSize, paramString);
      return;
    }
    catch (com.google.ads.internal.b localb)
    {
      a(paramActivity, localb.c("Could not initialize AdView"), paramAdSize, null);
      localb.a("Could not initialize AdView");
    }
  }

  protected AdView(Activity paramActivity, AdSize[] paramArrayOfAdSize, String paramString)
  {
    this(paramActivity, new AdSize(0, 0), paramString);
    a(paramArrayOfAdSize);
  }

  public AdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    a(paramContext, paramAttributeSet);
  }

  public AdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet);
  }

  private void a(Activity paramActivity, AdSize paramAdSize, String paramString)
    throws com.google.ads.internal.b
  {
    FrameLayout localFrameLayout = new FrameLayout(paramActivity);
    localFrameLayout.setFocusable(false);
    this.a = new d(this, paramActivity, paramAdSize, paramString, localFrameLayout, false);
    setGravity(17);
    try
    {
      ViewGroup localViewGroup = k.a(paramActivity, this.a);
      if (localViewGroup != null)
      {
        localViewGroup.addView(localFrameLayout, -2, -2);
        addView(localViewGroup, -2, -2);
        return;
      }
      addView(localFrameLayout, -2, -2);
      return;
    }
    catch (VerifyError localVerifyError)
    {
      com.google.ads.util.b.a("Gestures disabled: Not supported on this version of Android.", localVerifyError);
      addView(localFrameLayout, -2, -2);
    }
  }

  private void a(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet == null);
    while (true)
    {
      return;
      try
      {
        String str2 = b("adSize", paramContext, paramAttributeSet, true);
        AdSize[] arrayOfAdSize2 = a(str2);
        if (arrayOfAdSize2 != null);
        try
        {
          if (arrayOfAdSize2.length != 0)
            break label132;
          throw new com.google.ads.internal.b("Attribute \"adSize\" invalid: " + str2, true);
        }
        catch (com.google.ads.internal.b localb2)
        {
          localObject = localb2;
          arrayOfAdSize1 = arrayOfAdSize2;
        }
        String str1 = ((com.google.ads.internal.b)localObject).c("Could not initialize AdView");
        if ((arrayOfAdSize1 != null) && (arrayOfAdSize1.length > 0));
        for (AdSize localAdSize = arrayOfAdSize1[0]; ; localAdSize = AdSize.BANNER)
        {
          a(paramContext, str1, localAdSize, paramAttributeSet);
          ((com.google.ads.internal.b)localObject).a("Could not initialize AdView");
          if (isInEditMode())
            break;
          ((com.google.ads.internal.b)localObject).b("Could not initialize AdView");
          return;
          label132: if (!a("adUnitId", paramAttributeSet))
            throw new com.google.ads.internal.b("Required XML attribute \"adUnitId\" missing", true);
          if (isInEditMode())
          {
            a(paramContext, "Ads by Google", -1, arrayOfAdSize2[0], paramAttributeSet);
            return;
          }
          String str3 = b("adUnitId", paramContext, paramAttributeSet, true);
          boolean bool = a("loadAdOnCreate", paramContext, paramAttributeSet, false);
          if ((paramContext instanceof Activity))
          {
            Activity localActivity = (Activity)paramContext;
            a(localActivity, arrayOfAdSize2[0], paramAttributeSet);
            b(localActivity, arrayOfAdSize2[0], paramAttributeSet);
            if (arrayOfAdSize2.length == 1)
              a(localActivity, arrayOfAdSize2[0], str3);
            while (bool)
            {
              Set localSet = c("testDevices", paramContext, paramAttributeSet, false);
              if (localSet.contains("TEST_EMULATOR"))
              {
                localSet.remove("TEST_EMULATOR");
                localSet.add(AdRequest.TEST_EMULATOR);
              }
              loadAd(new AdRequest().setTestDevices(localSet).setKeywords(c("keywords", paramContext, paramAttributeSet, false)));
              return;
              a(localActivity, new AdSize(0, 0), str3);
              a(arrayOfAdSize2);
            }
          }
          throw new com.google.ads.internal.b("AdView was initialized with a Context that wasn't an Activity.", true);
        }
      }
      catch (com.google.ads.internal.b localb1)
      {
        while (true)
        {
          Object localObject = localb1;
          AdSize[] arrayOfAdSize1 = null;
        }
      }
    }
  }

  private void a(Context paramContext, String paramString, AdSize paramAdSize, AttributeSet paramAttributeSet)
  {
    com.google.ads.util.b.b(paramString);
    a(paramContext, paramString, -65536, paramAdSize, paramAttributeSet);
  }

  private void a(AdSize[] paramArrayOfAdSize)
  {
    AdSize[] arrayOfAdSize = new AdSize[paramArrayOfAdSize.length];
    for (int i = 0; i < paramArrayOfAdSize.length; i++)
      arrayOfAdSize[i] = AdSize.createAdSize(paramArrayOfAdSize[i], getContext());
    this.a.i().n.a(arrayOfAdSize);
  }

  private boolean a(Context paramContext, AdSize paramAdSize, AttributeSet paramAttributeSet)
  {
    if (!AdUtil.c(paramContext))
    {
      a(paramContext, "You must have AdActivity declared in AndroidManifest.xml with configChanges.", paramAdSize, paramAttributeSet);
      return false;
    }
    return true;
  }

  private boolean a(String paramString, Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean)
    throws com.google.ads.internal.b
  {
    String str1 = paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.google.ads", paramString);
    boolean bool = paramAttributeSet.getAttributeBooleanValue("http://schemas.android.com/apk/lib/com.google.ads", paramString, paramBoolean);
    if (str1 != null)
    {
      String str2 = paramContext.getPackageName();
      if (str1.matches("^@([^:]+)\\:(.*)$"))
      {
        str2 = str1.replaceFirst("^@([^:]+)\\:(.*)$", "$1");
        str1 = str1.replaceFirst("^@([^:]+)\\:(.*)$", "@$2");
      }
      if (str1.startsWith("@bool/"))
      {
        String str3 = str1.substring("@bool/".length());
        TypedValue localTypedValue = new TypedValue();
        try
        {
          getResources().getValue(str2 + ":bool/" + str3, localTypedValue, true);
          if (localTypedValue.type != 18)
            break label207;
          if (localTypedValue.data != 0)
            return true;
        }
        catch (Resources.NotFoundException localNotFoundException)
        {
          throw new com.google.ads.internal.b("Could not find resource for " + paramString + ": " + str1, true, localNotFoundException);
        }
        return false;
        label207: throw new com.google.ads.internal.b("Resource " + paramString + " was not a boolean: " + localTypedValue, true);
      }
    }
    return bool;
  }

  private boolean a(String paramString, AttributeSet paramAttributeSet)
  {
    return paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.google.ads", paramString) != null;
  }

  private String b(String paramString, Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean)
    throws com.google.ads.internal.b
  {
    String str1 = paramAttributeSet.getAttributeValue("http://schemas.android.com/apk/lib/com.google.ads", paramString);
    String str2;
    String str3;
    TypedValue localTypedValue;
    if (str1 != null)
    {
      str2 = paramContext.getPackageName();
      if (str1.matches("^@([^:]+)\\:(.*)$"))
      {
        str2 = str1.replaceFirst("^@([^:]+)\\:(.*)$", "$1");
        str1 = str1.replaceFirst("^@([^:]+)\\:(.*)$", "@$2");
      }
      if (str1.startsWith("@string/"))
      {
        str3 = str1.substring("@string/".length());
        localTypedValue = new TypedValue();
      }
    }
    try
    {
      getResources().getValue(str2 + ":string/" + str3, localTypedValue, true);
      if (localTypedValue.string != null)
      {
        str1 = localTypedValue.string.toString();
        if ((!paramBoolean) || (str1 != null))
          break label275;
        throw new com.google.ads.internal.b("Required XML attribute \"" + paramString + "\" missing", true);
      }
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      throw new com.google.ads.internal.b("Could not find resource for " + paramString + ": " + str1, true, localNotFoundException);
    }
    throw new com.google.ads.internal.b("Resource " + paramString + " was not a string: " + localTypedValue, true);
    label275: return str1;
  }

  private boolean b(Context paramContext, AdSize paramAdSize, AttributeSet paramAttributeSet)
  {
    if (!AdUtil.b(paramContext))
    {
      a(paramContext, "You must have INTERNET and ACCESS_NETWORK_STATE permissions in AndroidManifest.xml.", paramAdSize, paramAttributeSet);
      return false;
    }
    return true;
  }

  private Set<String> c(String paramString, Context paramContext, AttributeSet paramAttributeSet, boolean paramBoolean)
    throws com.google.ads.internal.b
  {
    String str1 = b(paramString, paramContext, paramAttributeSet, paramBoolean);
    HashSet localHashSet = new HashSet();
    if (str1 != null)
    {
      String[] arrayOfString = str1.split(",");
      int i = arrayOfString.length;
      for (int j = 0; j < i; j++)
      {
        String str2 = arrayOfString[j].trim();
        if (str2.length() != 0)
          localHashSet.add(str2);
      }
    }
    return localHashSet;
  }

  void a(Context paramContext, String paramString, int paramInt, AdSize paramAdSize, AttributeSet paramAttributeSet)
  {
    if (paramAdSize == null)
      paramAdSize = AdSize.BANNER;
    AdSize localAdSize = AdSize.createAdSize(paramAdSize, paramContext.getApplicationContext());
    TextView localTextView;
    LinearLayout localLinearLayout1;
    if (getChildCount() == 0)
    {
      if (paramAttributeSet != null)
        break label175;
      localTextView = new TextView(paramContext);
      localTextView.setGravity(17);
      localTextView.setText(paramString);
      localTextView.setTextColor(paramInt);
      localTextView.setBackgroundColor(-16777216);
      if (paramAttributeSet != null)
        break label190;
      localLinearLayout1 = new LinearLayout(paramContext);
      label85: localLinearLayout1.setGravity(17);
      if (paramAttributeSet != null)
        break label205;
    }
    label175: label190: label205: for (LinearLayout localLinearLayout2 = new LinearLayout(paramContext); ; localLinearLayout2 = new LinearLayout(paramContext, paramAttributeSet))
    {
      localLinearLayout2.setGravity(17);
      localLinearLayout2.setBackgroundColor(paramInt);
      int i = AdUtil.a(paramContext, localAdSize.getWidth());
      int j = AdUtil.a(paramContext, localAdSize.getHeight());
      localLinearLayout1.addView(localTextView, i - 2, j - 2);
      localLinearLayout2.addView(localLinearLayout1);
      addView(localLinearLayout2, i, j);
      return;
      localTextView = new TextView(paramContext, paramAttributeSet);
      break;
      localLinearLayout1 = new LinearLayout(paramContext, paramAttributeSet);
      break label85;
    }
  }

  AdSize[] a(String paramString)
  {
    String[] arrayOfString1 = paramString.split(",");
    AdSize[] arrayOfAdSize = new AdSize[arrayOfString1.length];
    int i = 0;
    while (true)
    {
      String str;
      String[] arrayOfString2;
      if (i < arrayOfString1.length)
      {
        str = arrayOfString1[i].trim();
        if (str.matches("^(\\d+|FULL_WIDTH)\\s*[xX]\\s*(\\d+|AUTO_HEIGHT)$"))
        {
          arrayOfString2 = str.split("[xX]");
          arrayOfString2[0] = arrayOfString2[0].trim();
          arrayOfString2[1] = arrayOfString2[1].trim();
        }
      }
      try
      {
        int j;
        int m;
        label113: AdSize localAdSize;
        if ("FULL_WIDTH".equals(arrayOfString2[0]))
        {
          j = -1;
          boolean bool = "AUTO_HEIGHT".equals(arrayOfString2[1]);
          if (!bool)
            break label145;
          m = -2;
          localAdSize = new AdSize(j, m);
        }
        while (true)
        {
          if (localAdSize != null)
            break label281;
          return null;
          j = Integer.parseInt(arrayOfString2[0]);
          break;
          label145: int k = Integer.parseInt(arrayOfString2[1]);
          m = k;
          break label113;
          if ("BANNER".equals(str))
            localAdSize = AdSize.BANNER;
          else if ("SMART_BANNER".equals(str))
            localAdSize = AdSize.SMART_BANNER;
          else if ("IAB_MRECT".equals(str))
            localAdSize = AdSize.IAB_MRECT;
          else if ("IAB_BANNER".equals(str))
            localAdSize = AdSize.IAB_BANNER;
          else if ("IAB_LEADERBOARD".equals(str))
            localAdSize = AdSize.IAB_LEADERBOARD;
          else if ("IAB_WIDE_SKYSCRAPER".equals(str))
            localAdSize = AdSize.IAB_WIDE_SKYSCRAPER;
          else
            localAdSize = null;
        }
        label281: arrayOfAdSize[i] = localAdSize;
        i++;
        continue;
        return arrayOfAdSize;
      }
      catch (NumberFormatException localNumberFormatException)
      {
      }
    }
    return null;
  }

  public void destroy()
  {
    this.a.b();
  }

  public boolean isReady()
  {
    if (this.a == null)
      return false;
    return this.a.s();
  }

  public boolean isRefreshing()
  {
    if (this.a == null)
      return false;
    return this.a.t();
  }

  public void loadAd(AdRequest paramAdRequest)
  {
    if (this.a != null)
    {
      if (isRefreshing())
        this.a.f();
      this.a.a(paramAdRequest);
    }
  }

  protected void onMeasure(int paramInt1, int paramInt2)
  {
    if (!isInEditMode())
    {
      AdWebView localAdWebView = this.a.l();
      if (localAdWebView != null)
        localAdWebView.setVisibility(0);
    }
    super.onMeasure(paramInt1, paramInt2);
  }

  protected void onWindowVisibilityChanged(int paramInt)
  {
    super.onWindowVisibilityChanged(paramInt);
    if (isInEditMode());
    while ((!((h)this.a.i().g.a()).b()) || (paramInt == 0) || (this.a.i().l.a() == null) || (this.a.i().e.a() == null))
      return;
    if ((AdActivity.isShowing()) && (!AdActivity.leftApplication()))
    {
      b.a((WebView)this.a.i().e.a(), "onopeninapp", null);
      return;
    }
    b.a((WebView)this.a.i().e.a(), "onleaveapp", null);
  }

  public void setAdListener(AdListener paramAdListener)
  {
    this.a.i().o.a(paramAdListener);
  }

  protected void setAppEventListener(AppEventListener paramAppEventListener)
  {
    this.a.i().p.a(paramAppEventListener);
  }

  protected void setSupportedAdSizes(AdSize[] paramArrayOfAdSize)
  {
    if (this.a.i().n.a() == null)
    {
      com.google.ads.util.b.e("Warning: Tried to set supported ad sizes on a single-size AdView. AdSizes ignored. To create a multi-sized AdView, use an AdView constructor that takes in an AdSize[] array.");
      return;
    }
    a(paramArrayOfAdSize);
  }

  protected void setSwipeableEventListener(SwipeableAdListener paramSwipeableAdListener)
  {
    this.a.i().q.a(paramSwipeableAdListener);
  }

  public void stopLoading()
  {
    if (this.a != null)
      this.a.C();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.AdView
 * JD-Core Version:    0.6.2
 */