package com.flipdog.commons.e;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

class p
  implements DialogInterface.OnClickListener
{
  p(d paramd)
  {
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    paramDialogInterface.cancel();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.e.p
 * JD-Core Version:    0.6.2
 */