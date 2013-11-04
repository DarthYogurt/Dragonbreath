package com.flipdog.editor.a;

import android.text.Editable;
import android.text.TextWatcher;

class d
  implements TextWatcher
{
  d(h paramh)
  {
  }

  public void afterTextChanged(Editable paramEditable)
  {
    if (h.a(this.a));
    while (h.b(this.a))
      return;
    this.a.d();
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.a.d
 * JD-Core Version:    0.6.2
 */