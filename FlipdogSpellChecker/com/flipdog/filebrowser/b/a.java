package com.flipdog.filebrowser.b;

import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import com.flipdog.filebrowser.c.b;

public class a extends ClickableSpan
{
  public static b a;
  private int b;

  public a(int paramInt)
  {
    this.b = paramInt;
  }

  public void onClick(View paramView)
  {
    a.a(this.b);
  }

  public void updateDrawState(TextPaint paramTextPaint)
  {
    super.updateDrawState(paramTextPaint);
    paramTextPaint.setUnderlineText(false);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.b.a
 * JD-Core Version:    0.6.2
 */