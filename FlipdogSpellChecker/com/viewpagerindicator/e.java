package com.viewpagerindicator;

import android.content.Context;
import android.view.View.MeasureSpec;
import android.widget.TextView;
import android.widget.TextView.BufferType;
import com.flipdog.d;

class e extends TextView
{
  private int b;

  public e(TabPageIndicator paramTabPageIndicator, Context paramContext)
  {
    super(paramContext, null, d.vpiTabPageIndicatorStyle);
  }

  public int a()
  {
    return this.b;
  }

  public void onMeasure(int paramInt1, int paramInt2)
  {
    super.onMeasure(paramInt1, paramInt2);
    if (TabPageIndicator.c(this.a) > 0)
      super.onMeasure(View.MeasureSpec.makeMeasureSpec(TabPageIndicator.c(this.a), 1073741824), paramInt2);
  }

  public void setText(CharSequence paramCharSequence, TextView.BufferType paramBufferType)
  {
    if ((paramCharSequence instanceof String))
    {
      super.setText(((String)paramCharSequence).toUpperCase(), paramBufferType);
      return;
    }
    super.setText(paramCharSequence, paramBufferType);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.viewpagerindicator.e
 * JD-Core Version:    0.6.2
 */