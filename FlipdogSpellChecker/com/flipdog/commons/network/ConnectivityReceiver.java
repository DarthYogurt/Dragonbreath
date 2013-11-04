package com.flipdog.commons.network;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.os.Bundle;
import com.flipdog.commons.i.b;

public class ConnectivityReceiver extends BroadcastReceiver
{
  private a a;
  private ConnectivityManager b;

  public ConnectivityReceiver(a parama)
  {
    this.a = parama;
  }

  private ConnectivityManager a()
  {
    if (this.b == null)
      this.b = ((ConnectivityManager)((Context)b.a(Context.class)).getSystemService("connectivity"));
    return this.b;
  }

  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Bundle localBundle = paramIntent.getExtras();
    NetworkInfo localNetworkInfo = null;
    if (localBundle != null)
      localNetworkInfo = (NetworkInfo)localBundle.get("networkInfo");
    if (localNetworkInfo == null)
      localNetworkInfo = a().getActiveNetworkInfo();
    if (localNetworkInfo == null)
      this.a.a();
    NetworkInfo.State localState;
    do
    {
      return;
      localState = localNetworkInfo.getState();
      if (localState == NetworkInfo.State.CONNECTED)
      {
        this.a.a(localNetworkInfo.getType());
        return;
      }
    }
    while (localState != NetworkInfo.State.DISCONNECTED);
    this.a.a();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.network.ConnectivityReceiver
 * JD-Core Version:    0.6.2
 */