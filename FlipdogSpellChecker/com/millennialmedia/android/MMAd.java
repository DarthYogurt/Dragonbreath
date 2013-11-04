package com.millennialmedia.android;

public abstract interface MMAd
{
  public static final String BANNER = "b";
  public static final String INTERSTITIAL = "i";

  public abstract String getApid();

  public abstract boolean getIgnoresDensityScaling();

  public abstract RequestListener getListener();

  public abstract MMRequest getMMRequest();

  public abstract void setApid(String paramString);

  public abstract void setIgnoresDensityScaling(boolean paramBoolean);

  public abstract void setListener(RequestListener paramRequestListener);

  public abstract void setMMRequest(MMRequest paramMMRequest);
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.MMAd
 * JD-Core Version:    0.6.2
 */