package com.millennialmedia.android;

import android.util.Log;

public abstract interface RequestListener
{
  public abstract void MMAdOverlayLaunched(MMAd paramMMAd);

  public abstract void MMAdRequestIsCaching(MMAd paramMMAd);

  public abstract void requestCompleted(MMAd paramMMAd);

  public abstract void requestFailed(MMAd paramMMAd, MMException paramMMException);

  public static class RequestListenerImpl
    implements RequestListener
  {
    public void MMAdOverlayLaunched(MMAd paramMMAd)
    {
      Log.i("MillennialMediaSDK", "Millennial Media Ad View overlay launched");
    }

    public void MMAdRequestIsCaching(MMAd paramMMAd)
    {
      Log.i("MillennialMediaSDK", "Millennial Media Ad View caching request");
    }

    public void requestCompleted(MMAd paramMMAd)
    {
      Log.i("MillennialMediaSDK", "Ad request succeeded");
    }

    public void requestFailed(MMAd paramMMAd, MMException paramMMException)
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramMMException.getCode());
      arrayOfObject[1] = paramMMException.getMessage();
      Log.i("MillennialMediaSDK", String.format("Ad request failed with error: %d %s.", arrayOfObject));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.RequestListener
 * JD-Core Version:    0.6.2
 */