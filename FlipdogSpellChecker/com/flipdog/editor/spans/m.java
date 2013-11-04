package com.flipdog.editor.spans;

import android.text.Spannable;
import com.flipdog.commons.a.g;
import java.util.Comparator;

class m
  implements Comparator<Object>
{
  m(Spannable paramSpannable)
  {
  }

  public int compare(Object paramObject1, Object paramObject2)
  {
    return g.a(this.a.getSpanStart(paramObject1), this.a.getSpanStart(paramObject2));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.spans.m
 * JD-Core Version:    0.6.2
 */