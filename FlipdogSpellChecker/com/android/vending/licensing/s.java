package com.android.vending.licensing;

import android.util.Log;

class s
  implements Runnable
{
  s(c paramc)
  {
  }

  public void run()
  {
    Log.i("LicenseChecker", "Check timed out.");
    v.a(c.c(this.a), c.a(this.a));
    v.b(c.c(this.a), c.a(this.a));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.vending.licensing.s
 * JD-Core Version:    0.6.2
 */