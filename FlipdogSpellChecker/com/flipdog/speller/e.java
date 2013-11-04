package com.flipdog.speller;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.PopupWindow;

class e
  implements View.OnKeyListener
{
  e(r paramr)
  {
  }

  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      r.b(this.a).dismiss();
      return true;
    }
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.e
 * JD-Core Version:    0.6.2
 */