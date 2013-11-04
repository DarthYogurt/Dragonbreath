package com.flipdog.spellchecker.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class j
  implements DialogInterface.OnDismissListener
{
  j(Runnable paramRunnable)
  {
  }

  public void onDismiss(DialogInterface paramDialogInterface)
  {
    this.a.run();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.a.j
 * JD-Core Version:    0.6.2
 */