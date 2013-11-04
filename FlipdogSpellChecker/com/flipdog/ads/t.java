package com.flipdog.ads;

import android.app.Activity;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.flipdog.commons.a.ay;
import com.flipdog.commons.diagnostic.Track;

public class t
  implements AdWhirlLayout.AdWhirlInterface
{
  private Activity activity;
  private AdWhirlLayout adWhirlLayout;

  public t(Activity paramActivity, AdWhirlLayout paramAdWhirlLayout)
  {
    this.activity = paramActivity;
    this.adWhirlLayout = paramAdWhirlLayout;
  }

  private static void handleMM(AdWhirlLayout paramAdWhirlLayout)
  {
    MMUtils.handle(paramAdWhirlLayout, g.d.a);
  }

  private static void handleMM(AdWhirlLayout paramAdWhirlLayout, String paramString)
  {
    MMUtils.handle(paramAdWhirlLayout, paramString);
  }

  public void AdFonic()
  {
    Track.it("Custom event - AdFonic", new String[] { "Ads" });
    if (ay.b() > 3)
    {
      q.a(this.activity, this.adWhirlLayout, "bottom");
      return;
    }
    this.adWhirlLayout.rollover();
  }

  public void Amazon()
  {
    Track.it("Custom event - Amazon", new String[] { "Ads" });
    r.a(this.activity, this.adWhirlLayout);
  }

  public void MMApiKey0()
  {
    Track.it("MM, ApiKey0", new String[] { "Ads" });
    handleMM(this.adWhirlLayout, g.d.a);
  }

  public void MMApiKey1()
  {
    Track.it("MM, ApiKey1", new String[] { "Ads" });
    handleMM(this.adWhirlLayout, g.d.b);
  }

  public void MMApiKey2()
  {
    Track.it("MM, ApiKey2", new String[] { "Ads" });
    handleMM(this.adWhirlLayout, g.d.c);
  }

  public void MMApiKey3()
  {
    Track.it("MM, ApiKey3", new String[] { "Ads" });
    handleMM(this.adWhirlLayout, g.d.d);
  }

  public void Yoc1()
  {
    Track.it("Custom event - Yoc1", new String[] { "Ads" });
    p.a(this.activity, this.adWhirlLayout, "bottom");
  }

  public void adWhirlGeneric()
  {
    Track.it("Generic", new String[] { "Ads" });
    handleMM(this.adWhirlLayout);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.t
 * JD-Core Version:    0.6.2
 */