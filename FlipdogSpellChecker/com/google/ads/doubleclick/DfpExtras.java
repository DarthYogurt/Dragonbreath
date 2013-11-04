package com.google.ads.doubleclick;

import com.google.ads.mediation.admob.AdMobAdapterExtras;
import java.util.Map;

public class DfpExtras extends AdMobAdapterExtras
{
  private String a;

  public DfpExtras()
  {
  }

  public DfpExtras(DfpExtras paramDfpExtras)
  {
    super(paramDfpExtras);
    if (paramDfpExtras != null)
      this.a = paramDfpExtras.a;
  }

  public DfpExtras addExtra(String paramString, Object paramObject)
  {
    super.addExtra(paramString, paramObject);
    return this;
  }

  public DfpExtras clearExtras()
  {
    super.clearExtras();
    return this;
  }

  public String getPublisherProvidedId()
  {
    return this.a;
  }

  public DfpExtras setExtras(Map<String, Object> paramMap)
  {
    super.setExtras(paramMap);
    return this;
  }

  public DfpExtras setPlusOneOptOut(boolean paramBoolean)
  {
    super.setPlusOneOptOut(paramBoolean);
    return this;
  }

  public DfpExtras setPublisherProvidedId(String paramString)
  {
    this.a = paramString;
    return this;
  }

  public DfpExtras setUseExactAdSize(boolean paramBoolean)
  {
    super.setUseExactAdSize(paramBoolean);
    return this;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.doubleclick.DfpExtras
 * JD-Core Version:    0.6.2
 */