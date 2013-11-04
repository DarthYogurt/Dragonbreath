package com.android.vending.licensing;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

class q
  implements ILicensingService
{
  private IBinder a;

  q(IBinder paramIBinder)
  {
    this.a = paramIBinder;
  }

  public String a()
  {
    return "com.android.vending.licensing.ILicensingService";
  }

  public void a(long paramLong, String paramString, p paramp)
    throws RemoteException
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      localParcel.writeInterfaceToken("com.android.vending.licensing.ILicensingService");
      localParcel.writeLong(paramLong);
      localParcel.writeString(paramString);
      IBinder localIBinder = null;
      if (paramp != null)
        localIBinder = paramp.asBinder();
      localParcel.writeStrongBinder(localIBinder);
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
 * Qualified Name:     com.android.vending.licensing.q
 * JD-Core Version:    0.6.2
 */