package com.android.vending.licensing;

import android.util.Log;
import java.util.Set;

class t
  implements Runnable
{
  t(c paramc, int paramInt, String paramString1, String paramString2)
  {
  }

  public void run()
  {
    Log.i("LicenseChecker", "Received response.");
    if (v.b(c.c(this.a)).contains(c.a(this.a)))
    {
      c.b(this.a);
      c.a(this.a).a(v.c(c.c(this.a)), this.b, this.c, this.d);
      v.b(c.c(this.a), c.a(this.a));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.vending.licensing.t
 * JD-Core Version:    0.6.2
 */