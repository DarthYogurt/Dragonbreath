package com.flipdog.commons.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import com.google.inject.Inject;

public class BackgroundDataSettingMonitor extends BroadcastReceiver
{
  private com.flipdog.commons.s.b a;
  private e b;

  @Inject
  public BackgroundDataSettingMonitor()
  {
    Context localContext = (Context)com.flipdog.commons.i.b.a(Context.class);
    this.a = ((com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class));
    this.b = ((e)com.flipdog.commons.i.b.a(e.class));
    localContext.registerReceiver(this, new IntentFilter("android.net.conn.BACKGROUND_DATA_SETTING_CHANGED"));
    a(localContext);
  }

  private void a(Context paramContext)
  {
    if (b(paramContext).getBackgroundDataSetting())
    {
      a(true);
      return;
    }
    a(true);
  }

  private void a(boolean paramBoolean)
  {
    this.b.a(paramBoolean);
    ((b)this.a.a(b.class)).a(paramBoolean);
  }

  private ConnectivityManager b(Context paramContext)
  {
    return (ConnectivityManager)paramContext.getSystemService("connectivity");
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    a(paramContext);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.network.BackgroundDataSettingMonitor
 * JD-Core Version:    0.6.2
 */