package com.flipdog.activity;

import android.content.Intent;
import android.preference.PreferenceActivity;
import com.b.h;

public class MyPreferenceActivity extends PreferenceActivity
{
  protected h a = new h();

  public h a()
  {
    return this.a;
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    ((a)this.a.a(a.class)).a(paramInt1, paramInt2, paramIntent);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.activity.MyPreferenceActivity
 * JD-Core Version:    0.6.2
 */