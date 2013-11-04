package com.flipdog.spellchecker.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.flipdog.speller.d;

class m
  implements DialogInterface.OnClickListener
{
  m(SpellCheckerActivity paramSpellCheckerActivity, String[] paramArrayOfString)
  {
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    d locald = d.a();
    locald.a = this.b[paramInt];
    locald.b();
    paramDialogInterface.dismiss();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.activity.m
 * JD-Core Version:    0.6.2
 */