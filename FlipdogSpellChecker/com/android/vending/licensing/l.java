package com.android.vending.licensing;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class l extends Binder
  implements p
{
  private static final String a = "com.android.vending.licensing.ILicenseResultListener";
  static final int b = 1;

  public l()
  {
    attachInterface(this, "com.android.vending.licensing.ILicenseResultListener");
  }

  public static p a(IBinder paramIBinder)
  {
    if (paramIBinder == null)
      return null;
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.vending.licensing.ILicenseResultListener");
    if ((localIInterface != null) && ((localIInterface instanceof p)))
      return (p)localIInterface;
    return new r(paramIBinder);
  }

  public IBinder asBinder()
  {
    return this;
  }

  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    switch (paramInt1)
    {
    default:
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902:
      paramParcel2.writeString("com.android.vending.licensing.ILicenseResultListener");
      return true;
    case 1:
    }
    paramParcel1.enforceInterface("com.android.vending.licensing.ILicenseResultListener");
    a(paramParcel1.readInt(), paramParcel1.readString(), paramParcel1.readString());
    return true;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.vending.licensing.l
 * JD-Core Version:    0.6.2
 */