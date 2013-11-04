package com.flipdog.editor;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import com.flipdog.editor.spans.j;

public class ab
  implements TextWatcher
{
  public void afterTextChanged(Editable paramEditable)
  {
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 > 1)
      j.a((Editable)paramCharSequence, paramInt1, paramInt1 + paramInt2, ImageSpan.class);
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.ab
 * JD-Core Version:    0.6.2
 */