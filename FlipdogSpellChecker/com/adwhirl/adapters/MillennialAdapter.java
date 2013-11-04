package com.adwhirl.adapters;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.obj.Ration;
import com.flipdog.ads.MMUtils;
import java.lang.ref.WeakReference;

public class MillennialAdapter extends AdWhirlAdapter
{
  public MillennialAdapter(AdWhirlLayout paramAdWhirlLayout, Ration paramRation)
  {
    super(paramAdWhirlLayout, paramRation);
  }

  public void handle()
  {
    AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.adWhirlLayoutReference.get();
    if (localAdWhirlLayout == null)
      return;
    MMUtils.handle(localAdWhirlLayout, this.ration.key);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.adwhirl.adapters.MillennialAdapter
 * JD-Core Version:    0.6.2
 */