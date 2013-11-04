package com.flipdog.commons.k;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Environment;
import com.flipdog.commons.diagnostic.Track;
import com.google.inject.Inject;

public class d
{
  private BroadcastReceiver a;
  private e b = (e)com.flipdog.commons.i.b.a(e.class);

  private void a(Intent paramIntent)
  {
    Track.it("onReceiveBroadcast: " + paramIntent.getAction(), new String[] { "Sdcard" });
    Track.it("  action: " + paramIntent.getAction(), new String[] { "Sdcard" });
    Track.it("  mounted path: " + b(paramIntent), new String[] { "Sdcard" });
    b();
  }

  private String b(Intent paramIntent)
  {
    if (paramIntent.getData() != null)
      return paramIntent.getData().getPath();
    return null;
  }

  public void a()
  {
    Track.it("Register broadcast receiver", new String[] { "Sdcard" });
    this.a = new a(this);
    IntentFilter localIntentFilter = new IntentFilter();
    localIntentFilter.addAction("android.intent.action.MEDIA_MOUNTED");
    localIntentFilter.addAction("android.intent.action.MEDIA_UNMOUNTED");
    localIntentFilter.addDataScheme("file");
    ((Context)com.flipdog.commons.i.b.a(Context.class)).registerReceiver(this.a, localIntentFilter);
    b();
  }

  void b()
  {
    String str = Environment.getExternalStorageState();
    b localb = new b();
    if ("mounted".equals(str))
    {
      localb.a = true;
      localb.b = true;
    }
    while (true)
    {
      this.b.a(localb);
      return;
      if ("mounted_ro".equals(str))
      {
        localb.a = true;
        localb.b = false;
      }
      else
      {
        localb.a = false;
        localb.b = false;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.k.d
 * JD-Core Version:    0.6.2
 */