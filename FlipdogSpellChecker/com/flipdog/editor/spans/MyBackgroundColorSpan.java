package com.flipdog.editor.spans;

import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.BackgroundColorSpan;

public class MyBackgroundColorSpan extends BackgroundColorSpan
  implements l
{
  public MyBackgroundColorSpan(int paramInt)
  {
    super(paramInt);
  }

  public MyBackgroundColorSpan(Parcel paramParcel)
  {
    super(paramParcel);
  }

  public void updateDrawState(TextPaint paramTextPaint)
  {
    paramTextPaint.bgColor = getBackgroundColor();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.spans.MyBackgroundColorSpan
 * JD-Core Version:    0.6.2
 */