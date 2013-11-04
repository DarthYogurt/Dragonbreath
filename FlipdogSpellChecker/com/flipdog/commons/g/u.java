package com.flipdog.commons.g;

import android.os.Handler;
import com.android.vending.licensing.l;

class u extends l
{
  private final m c;

  public u(s params, m paramm)
  {
    this.c = paramm;
  }

  public void a(int paramInt, String paramString1, String paramString2)
  {
    s.a(this.a).post(new g(this, paramInt, paramString1, paramString2));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.g.u
 * JD-Core Version:    0.6.2
 */