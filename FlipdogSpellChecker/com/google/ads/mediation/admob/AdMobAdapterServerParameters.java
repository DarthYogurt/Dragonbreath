package com.google.ads.mediation.admob;

import com.google.ads.mediation.MediationServerParameters;
import com.google.ads.mediation.MediationServerParameters.Parameter;

public final class AdMobAdapterServerParameters extends MediationServerParameters
{

  @MediationServerParameters.Parameter(name="pubid")
  public String adUnitId;

  @MediationServerParameters.Parameter(name="mad_hac", required=false)
  public String allowHouseAds = null;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.mediation.admob.AdMobAdapterServerParameters
 * JD-Core Version:    0.6.2
 */