package com.flipdog.editor.a;

import android.text.Editable;
import android.text.TextWatcher;

class e
  implements TextWatcher
{
  e(h paramh)
  {
  }

  public void afterTextChanged(Editable paramEditable)
  {
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (h.a(this.a));
    while (h.b(this.a))
      return;
    this.a.c();
    this.a.a(paramCharSequence, paramInt1, paramInt2, paramInt3);
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.a.e
 * JD-Core Version:    0.6.2
 */