package com.adwhirl.adapters;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.obj.Ration;
import com.flipdog.commons.diagnostic.Track;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public abstract class AdWhirlAdapter
{
  protected static String googleAdSenseAppName;
  protected static String googleAdSenseChannel;
  protected static String googleAdSenseCompanyName;
  protected static String googleAdSenseExpandDirection;
  protected final WeakReference<AdWhirlLayout> adWhirlLayoutReference;
  protected Ration ration;

  public AdWhirlAdapter(AdWhirlLayout paramAdWhirlLayout, Ration paramRation)
  {
    this.adWhirlLayoutReference = new WeakReference(paramAdWhirlLayout);
    this.ration = paramRation;
  }

  private static AdWhirlAdapter getAdapter(AdWhirlLayout paramAdWhirlLayout, Ration paramRation)
  {
    try
    {
      switch (paramRation.type)
      {
      case 2:
      case 3:
      case 4:
      case 5:
      case 7:
      case 10:
      case 11:
      case 13:
      case 15:
      case 19:
      case 21:
      case 22:
      default:
        return unknownAdNetwork(paramAdWhirlLayout, paramRation);
      case 1:
        if (Class.forName("com.google.ads.AdView") != null)
          return getNetworkAdapter("com.adwhirl.adapters.GoogleAdMobAdsAdapter", paramAdWhirlLayout, paramRation);
        return unknownAdNetwork(paramAdWhirlLayout, paramRation);
      case 18:
        if (Class.forName("com.inmobi.androidsdk.IMAdView") != null)
          return getNetworkAdapter("com.adwhirl.adapters.InMobiAdapter", paramAdWhirlLayout, paramRation);
        return unknownAdNetwork(paramAdWhirlLayout, paramRation);
      case 8:
        if (Class.forName("com.qwapi.adclient.android.view.QWAdView") != null)
          return getNetworkAdapter("com.adwhirl.adapters.QuattroAdapter", paramAdWhirlLayout, paramRation);
        return unknownAdNetwork(paramAdWhirlLayout, paramRation);
      case 6:
        if (Class.forName("com.millennialmedia.android.MMAdView") != null)
          return getNetworkAdapter("com.adwhirl.adapters.MillennialAdapter", paramAdWhirlLayout, paramRation);
        return unknownAdNetwork(paramAdWhirlLayout, paramRation);
      case 14:
        if (Class.forName("com.google.ads.GoogleAdView") != null)
          return getNetworkAdapter("com.adwhirl.adapters.AdSenseAdapter", paramAdWhirlLayout, paramRation);
        return unknownAdNetwork(paramAdWhirlLayout, paramRation);
      case 20:
        if (Class.forName("com.zestadz.android.ZestADZAdView") != null)
          return getNetworkAdapter("com.adwhirl.adapters.ZestAdzAdapter", paramAdWhirlLayout, paramRation);
        return unknownAdNetwork(paramAdWhirlLayout, paramRation);
      case 12:
        return getNetworkAdapter("com.adwhirl.adapters.MdotMAdapter", paramAdWhirlLayout, paramRation);
      case 23:
        return getNetworkAdapter("com.adwhirl.adapters.OneRiotAdapter", paramAdWhirlLayout, paramRation);
      case 24:
        return getNetworkAdapter("com.adwhirl.adapters.NexageAdapter", paramAdWhirlLayout, paramRation);
      case 9:
        CustomAdapter localCustomAdapter = new CustomAdapter(paramAdWhirlLayout, paramRation);
        return localCustomAdapter;
      case 16:
      case 17:
      }
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      return unknownAdNetwork(paramAdWhirlLayout, paramRation);
      GenericAdapter localGenericAdapter = new GenericAdapter(paramAdWhirlLayout, paramRation);
      return localGenericAdapter;
    }
    catch (VerifyError localVerifyError)
    {
      track("Caught VerifyError", localVerifyError);
      return unknownAdNetwork(paramAdWhirlLayout, paramRation);
    }
    EventAdapter localEventAdapter = new EventAdapter(paramAdWhirlLayout, paramRation);
    return localEventAdapter;
  }

  private static AdWhirlAdapter getNetworkAdapter(String paramString, AdWhirlLayout paramAdWhirlLayout, Ration paramRation)
  {
    try
    {
      AdWhirlAdapter localAdWhirlAdapter = (AdWhirlAdapter)Class.forName(paramString).getConstructor(new Class[] { AdWhirlLayout.class, Ration.class }).newInstance(new Object[] { paramAdWhirlLayout, paramRation });
      return localAdWhirlAdapter;
    }
    catch (InstantiationException localInstantiationException)
    {
      return null;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      return null;
    }
    catch (NoSuchMethodException localNoSuchMethodException)
    {
      return null;
    }
    catch (SecurityException localSecurityException)
    {
      return null;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    return null;
  }

  public static AdWhirlAdapter handle(AdWhirlLayout paramAdWhirlLayout, Ration paramRation)
    throws Throwable
  {
    AdWhirlAdapter localAdWhirlAdapter = getAdapter(paramAdWhirlLayout, paramRation);
    if (localAdWhirlAdapter != null)
    {
      track("Valid adapter, calling handle()");
      localAdWhirlAdapter.handle();
      return localAdWhirlAdapter;
    }
    throw new Exception("Invalid adapter");
  }

  public static void setGoogleAdSenseAppName(String paramString)
  {
    googleAdSenseAppName = paramString;
  }

  public static void setGoogleAdSenseChannel(String paramString)
  {
    googleAdSenseChannel = paramString;
  }

  public static void setGoogleAdSenseCompanyName(String paramString)
  {
    googleAdSenseCompanyName = paramString;
  }

  public static void setGoogleAdSenseExpandDirection(String paramString)
  {
    googleAdSenseExpandDirection = paramString;
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

  private static AdWhirlAdapter unknownAdNetwork(AdWhirlLayout paramAdWhirlLayout, Ration paramRation)
  {
    track("Unsupported ration type: " + paramRation.type);
    return null;
  }

  public abstract void handle();

  public void willDestroy()
  {
    track("Generic adapter will get destroyed");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.adwhirl.adapters.AdWhirlAdapter
 * JD-Core Version:    0.6.2
 */