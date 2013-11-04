package com.flipdog.speller;

import android.text.TextPaint;
import android.text.style.CharacterStyle;
import android.text.style.UpdateAppearance;
import android.view.MotionEvent;
import android.view.View;

public class o extends CharacterStyle
  implements UpdateAppearance
{
  private s a;

  public o(s params)
  {
    this.a = params;
  }

  private com.flipdog.commons.s.b b()
  {
    return (com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class);
  }

  public s a()
  {
    return this.a;
  }

  public void a(View paramView, MotionEvent paramMotionEvent)
  {
    ((q)b().a(q.class)).a(paramView, this, paramMotionEvent);
  }

  public void updateDrawState(TextPaint paramTextPaint)
  {
    paramTextPaint.setColor(-65536);
    paramTextPaint.setFlags(8);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.o
 * JD-Core Version:    0.6.2
 */