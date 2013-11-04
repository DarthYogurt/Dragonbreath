package com.flipdog.commons.a;

import android.text.Layout;
import android.widget.TextView;

public class au
{
  public static int a(float paramFloat, TextView paramTextView)
  {
    return (int)paramFloat + paramTextView.getScrollY() - paramTextView.getTotalPaddingTop();
  }

  public static int a(TextView paramTextView, int paramInt)
  {
    return paramTextView.getLayout().getLineForVertical(paramInt);
  }

  public static int b(float paramFloat, TextView paramTextView)
  {
    return (int)paramFloat + paramTextView.getScrollX() - paramTextView.getTotalPaddingLeft();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.au
 * JD-Core Version:    0.6.2
 */