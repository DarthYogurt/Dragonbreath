package com.adwhirl.adapters;

import com.adwhirl.AdWhirlLayout;
import com.adwhirl.obj.Ration;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;

public class EventAdapter extends AdWhirlAdapter
{
  public EventAdapter(AdWhirlLayout paramAdWhirlLayout, Ration paramRation)
  {
    super(paramAdWhirlLayout, paramRation);
  }

  public void handle()
  {
    track("Event notification request initiated");
    AdWhirlLayout localAdWhirlLayout = (AdWhirlLayout)this.adWhirlLayoutReference.get();
    if (localAdWhirlLayout == null)
      return;
    if (localAdWhirlLayout.adWhirlInterface != null)
    {
      String str1 = this.ration.key;
      if (str1 == null)
      {
        track("Event key is null");
        localAdWhirlLayout.rollover();
        return;
      }
      int i = str1.indexOf("|;|");
      if (i < 0)
      {
        track("Event key separator not found");
        localAdWhirlLayout.rollover();
        return;
      }
      String str2 = str1.substring(i + 3);
      Class localClass = localAdWhirlLayout.adWhirlInterface.getClass();
      try
      {
        localClass.getMethod(str2, null).invoke(localAdWhirlLayout.adWhirlInterface, null);
        return;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        localAdWhirlLayout.rollover();
        return;
      }
      catch (Exception localException)
      {
        track("Caught exception in handle()", localException);
        localAdWhirlLayout.rollover();
        return;
      }
    }
    track("Event notification would be sent, but no interface is listening");
    localAdWhirlLayout.rollover();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.adwhirl.adapters.EventAdapter
 * JD-Core Version:    0.6.2
 */