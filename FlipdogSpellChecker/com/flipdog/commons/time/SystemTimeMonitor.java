package com.flipdog.commons.time;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

public class SystemTimeMonitor extends BroadcastReceiver
{
  public void a(Context paramContext)
  {
    paramContext.registerReceiver(this, new IntentFilter("android.intent.action.TIME_SET"));
    paramContext.registerReceiver(this, new IntentFilter("android.intent.action.TIMEZONE_CHANGED"));
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    ((a)((com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class)).a(a.class)).a();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.time.SystemTimeMonitor
 * JD-Core Version:    0.6.2
 */