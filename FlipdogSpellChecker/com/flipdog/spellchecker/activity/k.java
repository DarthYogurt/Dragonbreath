package com.flipdog.spellchecker.activity;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.flipdog.spellchecker.a.a;
import java.util.List;

class k
  implements DialogInterface.OnClickListener
{
  k(SpellCheckerActivity paramSpellCheckerActivity, List paramList)
  {
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    a.a((CharSequence)this.b.get(paramInt));
    SpellCheckerActivity.a(this.a, (String)this.b.get(paramInt) + " ");
    paramDialogInterface.dismiss();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.activity.k
 * JD-Core Version:    0.6.2
 */