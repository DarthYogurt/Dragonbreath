package com.google.ads.mediation.millennial;

import android.app.Activity;
import android.content.res.Resources;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import com.google.ads.AdRequest.ErrorCode;
import com.google.ads.AdSize;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.mediation.MediationBannerAdapter;
import com.google.ads.mediation.MediationBannerListener;
import com.google.ads.mediation.MediationInterstitialAdapter;
import com.google.ads.mediation.MediationInterstitialListener;
import com.millennialmedia.android.MMAd;
import com.millennialmedia.android.MMAdView;
import com.millennialmedia.android.MMException;
import com.millennialmedia.android.MMInterstitial;
import com.millennialmedia.android.MMRequest;
import com.millennialmedia.android.MMSDK;
import com.millennialmedia.android.RequestListener;
import java.util.Hashtable;

public final class MillennialAdapter
  implements MediationBannerAdapter<MillennialAdapterExtras, MillennialAdapterServerParameters>, MediationInterstitialAdapter<MillennialAdapterExtras, MillennialAdapterServerParameters>
{
  private MMAdView adView;
  private MediationBannerListener bannerListener;
  private MediationInterstitialListener interstitialListener;
  private MMInterstitial interstitialView;
  private FrameLayout wrappedAdView;

  private void populateAdViewParameters(MMAd paramMMAd, Hashtable<String, String> paramHashtable, MediationAdRequest paramMediationAdRequest, MillennialAdapterExtras paramMillennialAdapterExtras)
  {
    if (paramMillennialAdapterExtras == null)
      paramMillennialAdapterExtras = new MillennialAdapterExtras();
    if (paramMediationAdRequest.getKeywords() != null)
      paramHashtable.put("keywords", TextUtils.join(",", paramMediationAdRequest.getKeywords()));
    String str;
    MMRequest localMMRequest;
    if (paramMillennialAdapterExtras.getChildren() != null)
    {
      if (paramMillennialAdapterExtras.getChildren().booleanValue())
      {
        str = "true";
        paramHashtable.put("children", str);
      }
    }
    else
    {
      localMMRequest = new MMRequest();
      if (paramMediationAdRequest.getAgeInYears() != null)
        localMMRequest.setAge(paramMediationAdRequest.getAgeInYears().toString());
      if (paramMediationAdRequest.getGender() != null)
        switch ($SWITCH_TABLE$com$google$ads$AdRequest$Gender()[paramMediationAdRequest.getGender().ordinal()])
        {
        default:
        case 2:
        case 3:
        }
    }
    while (true)
    {
      if (paramMillennialAdapterExtras.getIncomeInUsDollars() != null)
        localMMRequest.setIncome(paramMillennialAdapterExtras.getIncomeInUsDollars().toString());
      if (paramMediationAdRequest.getLocation() != null)
        MMRequest.setUserLocation(paramMediationAdRequest.getLocation());
      if (paramMillennialAdapterExtras.getPostalCode() != null)
        localMMRequest.setZip(paramMillennialAdapterExtras.getPostalCode());
      if (paramMillennialAdapterExtras.getMaritalStatus() != null)
        localMMRequest.setMarital(paramMillennialAdapterExtras.getMaritalStatus().getDescription());
      if (paramMillennialAdapterExtras.getEthnicity() != null)
        localMMRequest.setEthnicity(paramMillennialAdapterExtras.getEthnicity().getDescription());
      if (paramMillennialAdapterExtras.getOrientation() != null)
        localMMRequest.setOrientation(paramMillennialAdapterExtras.getOrientation().getDescription());
      if (paramMillennialAdapterExtras.getPolitics() != null)
        localMMRequest.setPolitics(paramMillennialAdapterExtras.getPolitics().getDescription());
      if (paramMillennialAdapterExtras.getEducation() != null)
        localMMRequest.setEducation(paramMillennialAdapterExtras.getEducation().getDescription());
      if (paramHashtable != null)
        localMMRequest.setMetaValues(paramHashtable);
      paramMMAd.setMMRequest(localMMRequest);
      return;
      str = "false";
      break;
      localMMRequest.setGender("male");
      continue;
      localMMRequest.setGender("female");
    }
  }

  public void destroy()
  {
  }

  public Class<MillennialAdapterExtras> getAdditionalParametersType()
  {
    return MillennialAdapterExtras.class;
  }

  public View getBannerView()
  {
    return this.wrappedAdView;
  }

  public Class<MillennialAdapterServerParameters> getServerParametersType()
  {
    return MillennialAdapterServerParameters.class;
  }

  public void requestBannerAd(MediationBannerListener paramMediationBannerListener, Activity paramActivity, MillennialAdapterServerParameters paramMillennialAdapterServerParameters, AdSize paramAdSize, MediationAdRequest paramMediationAdRequest, MillennialAdapterExtras paramMillennialAdapterExtras)
  {
    this.bannerListener = paramMediationBannerListener;
    Hashtable localHashtable = new Hashtable();
    FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(paramAdSize.getWidthInPixels(paramActivity), paramAdSize.getHeightInPixels(paramActivity));
    this.adView = new MMAdView(paramActivity);
    this.adView.setApid(paramMillennialAdapterServerParameters.apid);
    this.adView.setHeight(paramAdSize.getHeight());
    this.adView.setWidth(paramAdSize.getWidth());
    this.adView.setId(MMSDK.getDefaultAdId());
    populateAdViewParameters(this.adView, localHashtable, paramMediationAdRequest, paramMillennialAdapterExtras);
    this.adView.setListener(new BannerListener(null));
    this.wrappedAdView = new FrameLayout(paramActivity);
    this.wrappedAdView.setLayoutParams(localLayoutParams);
    this.wrappedAdView.addView(this.adView);
    if (paramAdSize.getWidthInPixels(paramActivity) <= paramActivity.getResources().getDisplayMetrics().widthPixels)
    {
      this.adView.getAd();
      return;
    }
    this.bannerListener.onFailedToReceiveAd(this, AdRequest.ErrorCode.NO_FILL);
  }

  public void requestInterstitialAd(MediationInterstitialListener paramMediationInterstitialListener, Activity paramActivity, MillennialAdapterServerParameters paramMillennialAdapterServerParameters, MediationAdRequest paramMediationAdRequest, MillennialAdapterExtras paramMillennialAdapterExtras)
  {
    this.interstitialListener = paramMediationInterstitialListener;
    Hashtable localHashtable = new Hashtable();
    this.interstitialView = new MMInterstitial(paramActivity);
    this.interstitialView.setApid(paramMillennialAdapterServerParameters.apid);
    populateAdViewParameters(this.interstitialView, localHashtable, paramMediationAdRequest, paramMillennialAdapterExtras);
    this.interstitialView.setListener(new InterstitialListener(null));
    this.interstitialView.fetch();
  }

  public void showInterstitial()
  {
    this.interstitialView.display();
  }

  private class BannerListener
    implements RequestListener
  {
    private BannerListener()
    {
    }

    public void MMAdOverlayLaunched(MMAd paramMMAd)
    {
      MillennialAdapter.this.bannerListener.onPresentScreen(MillennialAdapter.this);
    }

    public void MMAdRequestIsCaching(MMAd paramMMAd)
    {
    }

    public void requestCompleted(MMAd paramMMAd)
    {
      MillennialAdapter.this.bannerListener.onReceivedAd(MillennialAdapter.this);
    }

    public void requestFailed(MMAd paramMMAd, MMException paramMMException)
    {
      if (11 == paramMMException.getCode())
      {
        MillennialAdapter.this.bannerListener.onFailedToReceiveAd(MillennialAdapter.this, AdRequest.ErrorCode.NETWORK_ERROR);
        return;
      }
      MillennialAdapter.this.bannerListener.onFailedToReceiveAd(MillennialAdapter.this, AdRequest.ErrorCode.NO_FILL);
    }
  }

  private class InterstitialListener
    implements RequestListener
  {
    private InterstitialListener()
    {
    }

    public void MMAdOverlayLaunched(MMAd paramMMAd)
    {
      MillennialAdapter.this.interstitialListener.onPresentScreen(MillennialAdapter.this);
    }

    public void MMAdRequestIsCaching(MMAd paramMMAd)
    {
    }

    public void requestCompleted(MMAd paramMMAd)
    {
      MillennialAdapter.this.interstitialListener.onReceivedAd(MillennialAdapter.this);
    }

    public void requestFailed(MMAd paramMMAd, MMException paramMMException)
    {
      if (11 == paramMMException.getCode())
      {
        MillennialAdapter.this.interstitialListener.onFailedToReceiveAd(MillennialAdapter.this, AdRequest.ErrorCode.NETWORK_ERROR);
        return;
      }
      MillennialAdapter.this.interstitialListener.onFailedToReceiveAd(MillennialAdapter.this, AdRequest.ErrorCode.NO_FILL);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.mediation.millennial.MillennialAdapter
 * JD-Core Version:    0.6.2
 */