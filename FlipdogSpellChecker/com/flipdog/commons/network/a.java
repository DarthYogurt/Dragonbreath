package com.flipdog.commons.network;

import android.content.Context;
import android.content.IntentFilter;
import com.flipdog.commons.diagnostic.Track;
import com.google.inject.Inject;

public class a
{
  private static final String a = "Network";
  private ConnectivityReceiver b;
  private com.flipdog.commons.s.b c = (com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class);
  private e d;

  @Inject
  public a()
  {
    Context localContext = (Context)com.flipdog.commons.i.b.a(Context.class);
    this.b = new ConnectivityReceiver(this);
    this.d = ((e)com.flipdog.commons.i.b.a(e.class));
    IntentFilter localIntentFilter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
    localContext.registerReceiver(this.b, localIntentFilter);
  }

  protected void a()
  {
    Track.it("disconnected", new String[] { "Network" });
    this.d.a();
    ((c)this.c.a(c.class)).a();
  }

  protected void a(int paramInt)
  {
    Track.it("connected", new String[] { "Network" });
    this.d.a(paramInt);
    ((d)this.c.a(d.class)).a();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.network.a
 * JD-Core Version:    0.6.2
 */