package com.flipdog.spellchecker.a;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class g
  implements DialogInterface.OnClickListener
{
  g(Runnable paramRunnable)
  {
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (this.a != null)
      this.a.run();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.a.g
 * JD-Core Version:    0.6.2
 */