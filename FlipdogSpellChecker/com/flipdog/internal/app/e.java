package com.flipdog.internal.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;

class e
  implements DialogInterface.OnCancelListener
{
  e(AlertActivity paramAlertActivity)
  {
  }

  public void onCancel(DialogInterface paramDialogInterface)
  {
    this.a.finish();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.internal.app.e
 * JD-Core Version:    0.6.2
 */