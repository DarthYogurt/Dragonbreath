package com.flipdog.internal.app;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class j
  implements Parcelable.Creator<LabeledIntent>
{
  public LabeledIntent a(Parcel paramParcel)
  {
    return new LabeledIntent(paramParcel);
  }

  public LabeledIntent[] a(int paramInt)
  {
    return new LabeledIntent[paramInt];
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.internal.app.j
 * JD-Core Version:    0.6.2
 */