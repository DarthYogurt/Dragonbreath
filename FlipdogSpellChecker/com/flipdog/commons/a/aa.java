package com.flipdog.commons.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;

class aa
  implements DialogInterface.OnDismissListener
{
  aa(Runnable paramRunnable)
  {
  }

  public void onDismiss(DialogInterface paramDialogInterface)
  {
    this.a.run();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.aa
 * JD-Core Version:    0.6.2
 */