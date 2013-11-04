package com.adwhirl.adapters;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.AdWhirlLayout.AdWhirlInterface;
import com.adwhirl.AdWhirlManager;
import com.adwhirl.obj.Ration;
import java.lang.ref.WeakReference;

public class GenericAdapter extends AdWhirlAdapter
{
  public GenericAdapter(AdWhirlLayout paramAdWhirlLayout, Ration paramRation)
  {
    super(paramAdWhirlLayout, paramRation);
  }

  public void handle()
  {
    track("Generic notification request initiated");
    AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.adWhirlLayoutReference.get();
    if (localAdWhirlLayout == null)
      return;
    if (localAdWhirlLayout.adWhirlInterface != null)
      localAdWhirlLayout.adWhirlInterface.adWhirlGeneric();
    while (true)
    {
      localAdWhirlLayout.adWhirlManager.resetRollover();
      localAdWhirlLayout.rotateThreadedDelayed();
      return;
      track("Generic notification sent, but no interface is listening");
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.adwhirl.adapters.GenericAdapter
 * JD-Core Version:    0.6.2
 */