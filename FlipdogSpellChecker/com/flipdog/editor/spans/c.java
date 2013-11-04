package com.flipdog.editor.spans;

import android.text.style.UnderlineSpan;

class c
  implements k
{
  public Object a()
  {
    return new UnderlineSpan();
  }

  public boolean a(Object paramObject, int paramInt)
  {
    if (i.a(paramInt))
      return false;
    return paramObject instanceof UnderlineSpan;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.spans.c
 * JD-Core Version:    0.6.2
 */