package com.flipdog.commons.a;

import android.widget.TextView;

public class av
{
  public static void a(TextView paramTextView, int paramInt)
  {
    paramTextView.setPadding(paramInt, paramTextView.getPaddingTop(), paramTextView.getPaddingRight(), paramTextView.getPaddingBottom());
  }

  public static void b(TextView paramTextView, int paramInt)
  {
    paramTextView.setPadding(paramTextView.getPaddingLeft(), paramInt, paramTextView.getPaddingRight(), paramTextView.getPaddingBottom());
  }

  public static void c(TextView paramTextView, int paramInt)
  {
    paramTextView.setPadding(paramTextView.getPaddingLeft(), paramTextView.getPaddingTop(), paramInt, paramTextView.getPaddingBottom());
  }

  public static void d(TextView paramTextView, int paramInt)
  {
    paramTextView.setPadding(paramTextView.getPaddingLeft(), paramTextView.getPaddingTop(), paramTextView.getPaddingRight(), paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.av
 * JD-Core Version:    0.6.2
 */