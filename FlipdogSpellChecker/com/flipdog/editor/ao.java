package com.flipdog.editor;

import android.text.Editable;
import android.text.TextWatcher;
import android.text.style.ImageSpan;
import com.flipdog.commons.c.g;
import java.util.Iterator;
import java.util.List;

public class ao
  implements TextWatcher
{
  private String a = "{{image}}";
  private String b = "{{/image}}";

  public void afterTextChanged(Editable paramEditable)
  {
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    Editable localEditable;
    Iterator localIterator;
    if (paramInt3 > 1)
    {
      localEditable = (Editable)paramCharSequence;
      localIterator = g.a(paramCharSequence.subSequence(paramInt1, paramInt1 + paramInt3).toString(), this.a, this.b).iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      g localg = (g)localIterator.next();
      if (((ImageSpan[])localEditable.getSpans(paramInt1 + localg.a, paramInt1 + localg.b, ImageSpan.class)).length == 0)
      {
        ImageSpan localImageSpan = ah.a(localg.d);
        if (localImageSpan != null)
          localEditable.setSpan(localImageSpan, paramInt1 + localg.a, paramInt1 + localg.b, 33);
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.ao
 * JD-Core Version:    0.6.2
 */