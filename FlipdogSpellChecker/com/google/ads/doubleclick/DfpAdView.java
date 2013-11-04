package com.google.ads.doubleclick;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.AppEventListener;
import com.google.ads.internal.AdWebView;
import com.google.ads.internal.d;
import com.google.ads.internal.h;
import com.google.ads.n;
import com.google.ads.util.i.b;
import com.google.ads.util.i.c;

public class DfpAdView extends AdView
{
  public DfpAdView(Activity paramActivity, AdSize paramAdSize, String paramString)
  {
    super(paramActivity, paramAdSize, paramString);
  }

  public DfpAdView(Activity paramActivity, AdSize[] paramArrayOfAdSize, String paramString)
  {
    super(paramActivity, paramArrayOfAdSize, paramString);
  }

  public DfpAdView(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }

  public DfpAdView(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
  }

  public void enableManualImpressions(boolean paramBoolean)
  {
    this.a.i().t.a(Boolean.valueOf(paramBoolean));
  }

  public void recordImpression()
  {
    this.a.z();
  }

  public void resize(AdSize paramAdSize)
  {
    this.a.l().setAdSize(paramAdSize);
    ((h)this.a.i().g.a()).b(paramAdSize);
  }

  public void setAppEventListener(AppEventListener paramAppEventListener)
  {
    super.setAppEventListener(paramAppEventListener);
  }

  public void setSupportedAdSizes(AdSize[] paramArrayOfAdSize)
  {
    super.setSupportedAdSizes(paramArrayOfAdSize);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.doubleclick.DfpAdView
 * JD-Core Version:    0.6.2
 */