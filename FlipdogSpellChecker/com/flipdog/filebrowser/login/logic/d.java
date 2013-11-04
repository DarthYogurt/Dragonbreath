package com.flipdog.filebrowser.login.logic;

import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

class d
  implements CompoundButton.OnCheckedChangeListener
{
  d(BaseLoginActivity paramBaseLoginActivity)
  {
  }

  public void onCheckedChanged(CompoundButton paramCompoundButton, boolean paramBoolean)
  {
    BaseLoginActivity.a(this.a, paramBoolean);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.login.logic.d
 * JD-Core Version:    0.6.2
 */