package com.flipdog.ads;

import android.os.Handler;
import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.ViewAdRunnable;
import com.adwhirl.AdWhirlManager;
import com.amazon.device.ads.AdError;
import com.amazon.device.ads.AdLayout;
import com.amazon.device.ads.AdListener;
import com.amazon.device.ads.AdProperties;
import java.lang.ref.WeakReference;

class b
  implements AdListener
{
  b(WeakReference paramWeakReference)
  {
  }

  public void a(AdLayout paramAdLayout)
  {
  }

  public void a(AdLayout paramAdLayout, AdError paramAdError)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramAdError.getMessage();
    arrayOfObject[1] = paramAdError.getCode();
    r.a("Amazon error: %s (%s)", arrayOfObject);
    paramAdLayout.setListener(null);
    paramAdLayout.destroy();
    AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.a.get();
    if (localAdWhirlLayout == null)
      return;
    localAdWhirlLayout.rollover();
  }

  public void a(AdLayout paramAdLayout, AdProperties paramAdProperties)
  {
    paramAdLayout.setListener(null);
    AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.a.get();
    if (localAdWhirlLayout == null)
      return;
    localAdWhirlLayout.adWhirlManager.resetRollover();
    localAdWhirlLayout.handler.post(new AdWhirlLayout.ViewAdRunnable(localAdWhirlLayout, paramAdLayout));
    localAdWhirlLayout.rotateThreadedDelayed();
  }

  public void b(AdLayout paramAdLayout)
  {
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.b
 * JD-Core Version:    0.6.2
 */