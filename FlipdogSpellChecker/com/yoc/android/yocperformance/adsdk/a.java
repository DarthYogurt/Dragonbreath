package com.yoc.android.yocperformance.adsdk;

import android.os.Parcel;
import android.os.Parcelable.Creator;

class a
  implements Parcelable.Creator<Ad>
{
  public Ad a(Parcel paramParcel)
  {
    return new Ad(paramParcel);
  }

  public Ad[] a(int paramInt)
  {
    return new Ad[paramInt];
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.a
 * JD-Core Version:    0.6.2
 */