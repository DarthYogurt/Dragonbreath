package com.android.vending.licensing;

import android.os.IInterface;
import android.os.RemoteException;

public abstract interface ILicensingService extends IInterface
{
  public abstract void a(long paramLong, String paramString, p paramp)
    throws RemoteException;
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.vending.licensing.ILicensingService
 * JD-Core Version:    0.6.2
 */