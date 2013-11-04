package com.flipdog.commons.g;

import android.os.RemoteException;
import com.android.vending.licensing.w;
import java.util.concurrent.CountDownLatch;

class v
  implements af
{
  v(h paramh, CountDownLatch paramCountDownLatch)
  {
  }

  public void a()
  {
    this.b.countDown();
    throw new RuntimeException();
  }

  public void a(int paramInt, String paramString1, String paramString2)
  {
    this.b.countDown();
  }

  public void a(RemoteException paramRemoteException)
  {
    this.b.countDown();
  }

  public void a(w paramw)
  {
    this.b.countDown();
    throw new RuntimeException(paramw.toString());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.g.v
 * JD-Core Version:    0.6.2
 */