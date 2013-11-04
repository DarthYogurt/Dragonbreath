package com.android.vending.licensing;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class r
  implements p
{
  private IBinder a;

  r(IBinder paramIBinder)
  {
    this.a = paramIBinder;
  }

  public String a()
  {
    return "com.android.vending.licensing.ILicenseResultListener";
  }

  public void a(int paramInt, String paramString1, String paramString2)
    throws RemoteException
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.writeInterfaceToken("com.android.vending.licensing.ILicenseResultListener");
      localParcel.writeInt(paramInt);
      localParcel.writeString(paramString1);
      localParcel.writeString(paramString2);
      this.a.transact(1, localParcel, null, 1);
      return;
    }
    finally
    {
      localParcel.recycle();
    }
  }

  public IBinder asBinder()
  {
    return this.a;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.vending.licensing.r
 * JD-Core Version:    0.6.2
 */