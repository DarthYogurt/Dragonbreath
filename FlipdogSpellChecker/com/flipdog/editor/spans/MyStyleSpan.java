package com.flipdog.editor.spans;

import android.os.Parcel;
import android.text.style.StyleSpan;

public class MyStyleSpan extends StyleSpan
  implements l
{
  public MyStyleSpan(int paramInt)
  {
    super(paramInt);
  }

  public MyStyleSpan(Parcel paramParcel)
  {
    super(paramParcel);
  }

  public MyStyleSpan(StyleSpan paramStyleSpan)
  {
    this(paramStyleSpan.getStyle());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.spans.MyStyleSpan
 * JD-Core Version:    0.6.2
 */