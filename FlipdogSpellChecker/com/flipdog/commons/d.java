package com.flipdog.commons;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.view.View;

public class d
{
  private k a;

  public d(Activity paramActivity)
  {
    this.a = new h(this, paramActivity);
  }

  public d(Dialog paramDialog)
  {
    this.a = new g(this, paramDialog);
  }

  public d(Fragment paramFragment)
  {
    this.a = new f(this, paramFragment);
  }

  public d(View paramView)
  {
    this.a = new i(this, paramView);
  }

  public static d a(Object paramObject)
  {
    if ((paramObject instanceof Activity))
      return new d((Activity)paramObject);
    if ((paramObject instanceof Dialog))
      return new d((Dialog)paramObject);
    if ((paramObject instanceof Fragment))
      return new d((Fragment)paramObject);
    if ((paramObject instanceof View))
      return new d((View)paramObject);
    throw new RuntimeException("Unexpected " + paramObject.getClass().getSimpleName());
  }

  public <T extends View> T a(int paramInt)
  {
    return this.a.a(paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.d
 * JD-Core Version:    0.6.2
 */