package com.flipdog.speller;

import android.widget.EditText;
import android.widget.PopupWindow.OnDismissListener;

class g
  implements PopupWindow.OnDismissListener
{
  g(r paramr)
  {
  }

  public void onDismiss()
  {
    r.c(this.a).setOnKeyListener(null);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.g
 * JD-Core Version:    0.6.2
 */