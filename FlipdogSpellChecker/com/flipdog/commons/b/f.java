package com.flipdog.commons.b;

import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.widget.MyPopupWindow;

class f
  implements View.OnKeyListener
{
  f(d paramd, MyPopupWindow paramMyPopupWindow)
  {
  }

  public boolean onKey(View paramView, int paramInt, KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 1) && (paramInt == 82) && (this.a.a()))
    {
      this.b.r();
      return true;
    }
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.b.f
 * JD-Core Version:    0.6.2
 */