package com.android.vending.licensing;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.android.vending.licensing.a.a;
import com.android.vending.licensing.a.b;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class v
  implements ServiceConnection
{
  private static final String a = "LicenseChecker";
  private static final String b = "RSA";
  private static final int c = 10000;
  private static final SecureRandom d = new SecureRandom();
  private ILicensingService e;
  private PublicKey f;
  private final Context g;
  private final k h;
  private Handler i;
  private final String j;
  private final String k;
  private final Set<n> l = new HashSet();
  private final Queue<n> m = new LinkedList();

  public v(Context paramContext, k paramk, String paramString)
  {
    this.g = paramContext;
    this.h = paramk;
    this.f = a(paramString);
    this.j = this.g.getPackageName();
    this.k = a(paramContext, this.j);
    HandlerThread localHandlerThread = new HandlerThread("background thread");
    localHandlerThread.start();
    this.i = new Handler(localHandlerThread.getLooper());
  }

  private static String a(Context paramContext, String paramString)
  {
    try
    {
      String str = String.valueOf(paramContext.getPackageManager().getPackageInfo(paramString, 0).versionCode);
      return str;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Log.e("LicenseChecker", "Package not found. could not get version code.");
    }
    return "";
  }

  private static PublicKey a(String paramString)
  {
    try
    {
      byte[] arrayOfByte = a.a(paramString);
      PublicKey localPublicKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(arrayOfByte));
      return localPublicKey;
    }
    catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
    {
      throw new RuntimeException(localNoSuchAlgorithmException);
    }
    catch (b localb)
    {
      Log.e("LicenseChecker", "Could not decode from Base64.");
      throw new IllegalArgumentException(localb);
    }
    catch (InvalidKeySpecException localInvalidKeySpecException)
    {
      Log.e("LicenseChecker", "Invalid key specification.");
      throw new IllegalArgumentException(localInvalidKeySpecException);
    }
  }

  private void a(n paramn)
  {
    try
    {
      this.l.remove(paramn);
      if (this.l.isEmpty())
        c();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  private void b()
  {
    while (true)
    {
      n localn = (n)this.m.poll();
      if (localn == null)
        return;
      try
      {
        Log.i("LicenseChecker", "Calling checkLicense on service for " + localn.c());
        this.e.a(localn.b(), localn.c(), new c(this, localn));
        this.l.add(localn);
      }
      catch (RemoteException localRemoteException)
      {
        Log.w("LicenseChecker", "RemoteException in checkLicense call.", localRemoteException);
        b(localn);
      }
    }
  }

  private void b(n paramn)
  {
    try
    {
      this.h.a(f.c, null);
      if (this.h.e())
        paramn.a().a();
      while (true)
      {
        return;
        paramn.a().b();
      }
    }
    finally
    {
    }
  }

  private void c()
  {
    if (this.e != null);
    try
    {
      this.g.unbindService(this);
      this.e = null;
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      while (true)
        Log.e("LicenseChecker", "Unable to unbind from licensing service (already unbound)");
    }
  }

  private int d()
  {
    return d.nextInt();
  }

  public void a()
  {
    try
    {
      c();
      this.i.getLooper().quit();
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(e parame)
  {
    while (true)
    {
      n localn;
      try
      {
        localn = new n(this.h, new j(), parame, d(), this.j, this.k);
        if (this.e == null)
        {
          Log.i("LicenseChecker", "Binding to licensing service.");
          try
          {
            if (this.g.bindService(new Intent(ILicensingService.class.getName()), this, 1))
            {
              this.m.offer(localn);
              return;
            }
            Log.e("LicenseChecker", "Could not bind to service.");
            b(localn);
            continue;
          }
          catch (SecurityException localSecurityException)
          {
            parame.a(w.f);
            continue;
          }
        }
      }
      finally
      {
      }
      this.m.offer(localn);
      b();
    }
  }

  public void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    try
    {
      this.e = o.a(paramIBinder);
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
      Log.w("LicenseChecker", "Service unexpectedly disconnected.");
      this.e = null;
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
 * Qualified Name:     com.android.vending.licensing.v
 * JD-Core Version:    0.6.2
 */