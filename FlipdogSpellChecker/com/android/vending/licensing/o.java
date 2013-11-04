package com.android.vending.licensing;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class o extends Binder
  implements ILicensingService
{
  static final int a = 1;
  private static final String b = "com.android.vending.licensing.ILicensingService";

  public o()
  {
    attachInterface(this, "com.android.vending.licensing.ILicensingService");
  }

  public static ILicensingService a(IBinder paramIBinder)
  {
    if (paramIBinder == null)
      return null;
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.android.vending.licensing.ILicensingService");
    if ((localIInterface != null) && ((localIInterface instanceof ILicensingService)))
      return (ILicensingService)localIInterface;
    return new q(paramIBinder);
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
      paramParcel2.writeString("com.android.vending.licensing.ILicensingService");
      return true;
    case 1:
    }
    paramParcel1.enforceInterface("com.android.vending.licensing.ILicensingService");
    a(paramParcel1.readLong(), paramParcel1.readString(), l.a(paramParcel1.readStrongBinder()));
    return true;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.vending.licensing.o
 * JD-Core Version:    0.6.2
 */