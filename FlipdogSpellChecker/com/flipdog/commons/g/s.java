package com.flipdog.commons.g;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.android.vending.licensing.ILicensingService;
import com.android.vending.licensing.o;
import com.android.vending.licensing.w;
import com.flipdog.commons.i.b;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class s
  implements ServiceConnection
{
  private static final String a = "LicenseServer";
  private ILicensingService b;
  private final Context c = (Context)b.a(Context.class);
  private Handler d;
  private final Set<m> e = new HashSet();
  private final Queue<m> f = new LinkedList();

  public s()
  {
    HandlerThread localHandlerThread = new HandlerThread("background thread");
    localHandlerThread.start();
    this.d = new Handler(localHandlerThread.getLooper());
  }

  private void b()
  {
    while (true)
    {
      m localm = (m)this.f.poll();
      if (localm == null)
        return;
      try
      {
        Log.i("LicenseServer", "Calling checkLicense on service for " + localm.a);
        this.b.a(localm.b, localm.a, new u(this, localm));
        this.e.add(localm);
      }
      catch (RemoteException localRemoteException)
      {
        Log.w("LicenseServer", "RemoteException in checkLicense call.", localRemoteException);
        localm.c.a(localRemoteException);
      }
    }
  }

  private void b(m paramm)
  {
    try
    {
      this.e.remove(paramm);
      if (this.e.isEmpty())
        c();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void c()
  {
    if (this.b != null);
    try
    {
      this.c.unbindService(this);
      this.b = null;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
        Log.e("LicenseServer", "Unable to unbind from licensing service (already unbound)");
    }
  }

  public void a()
  {
    try
    {
      c();
      this.d.getLooper().quit();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(m paramm)
  {
    while (true)
    {
      try
      {
        if (this.b == null)
        {
          Log.i("LicenseServer", "Binding to licensing service.");
          try
          {
            if (this.c.bindService(new Intent(ILicensingService.class.getName()), this, 1))
            {
              this.f.offer(paramm);
              return;
            }
            Log.e("LicenseServer", "Could not bind to service.");
            paramm.c.a();
            continue;
          }
          catch (SecurityException localSecurityException)
          {
            paramm.c.a(w.f);
            continue;
          }
        }
      }
      finally
      {
      }
      this.f.offer(paramm);
      b();
    }
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    try
    {
      this.b = o.a(paramIBinder);
      b();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    try
    {
      Log.w("LicenseServer", "Service unexpectedly disconnected.");
      this.b = null;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.g.s
 * JD-Core Version:    0.6.2
 */