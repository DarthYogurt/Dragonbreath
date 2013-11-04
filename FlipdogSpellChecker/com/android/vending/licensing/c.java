package com.android.vending.licensing;

import android.os.Handler;
import android.util.Log;

class c extends l
{
  private final n c;
  private Runnable d;

  public c(v paramv, n paramn)
  {
    this.c = paramn;
    this.d = new s(this);
    a();
  }

  private void a()
  {
    Log.i("LicenseChecker", "Start monitoring timeout.");
    v.a(this.a).postDelayed(this.d, 10000L);
  }

  private void b()
  {
    Log.i("LicenseChecker", "Clearing timeout.");
    v.a(this.a).removeCallbacks(this.d);
  }

  public void a(int paramInt, String paramString1, String paramString2)
  {
    v.a(this.a).post(new t(this, paramInt, paramString1, paramString2));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.vending.licensing.c
 * JD-Core Version:    0.6.2
 */