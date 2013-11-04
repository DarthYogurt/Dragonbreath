package com.flipdog.ads;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import com.flipdog.commons.a.c;

public class l
{
  private static final Class<?> a = a("com.flipdog.ads.AdsCore2");

  private static Class<?> a(String paramString)
  {
    try
    {
      Class localClass = Class.forName(paramString);
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
    }
    return null;
  }

  public static void a(Activity paramActivity, int paramInt)
  {
    if (a == null)
      return;
    a(paramActivity, (ViewGroup)paramActivity.findViewById(paramInt));
  }

  private static void a(Activity paramActivity, ViewGroup paramViewGroup)
  {
    c.a(a, "append", new Object[] { Activity.class, paramActivity, ViewGroup.class, paramViewGroup });
  }

  public static void a(Application paramApplication)
  {
    if (a == null)
      return;
    b(paramApplication);
  }

  public static void a(Fragment paramFragment, int paramInt)
  {
    if (a == null)
      return;
    a(paramFragment.getActivity(), (ViewGroup)paramFragment.getView().findViewById(paramInt));
  }

  private static void b(Application paramApplication)
  {
    c.a(a, "onCreateApplication", new Object[] { Application.class, paramApplication });
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.l
 * JD-Core Version:    0.6.2
 */