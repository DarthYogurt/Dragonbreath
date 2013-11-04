package com.flipdog.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.ar;
import com.flipdog.errors.a;
import com.flipdog.errors.activity.ErrorActivity;
import com.flipdog.m;
import com.flipdog.p;

public class ReleaseNotesActivity extends MyActivity
{
  private f c = new f();
  private d d = new d();
  private c e = new c();

  public static void a(Context paramContext, int paramInt)
  {
    Intent localIntent = new Intent(paramContext, ReleaseNotesActivity.class);
    localIntent.putExtra("ResId", paramInt);
    paramContext.startActivity(localIntent);
  }

  private void d()
  {
    Intent localIntent = getIntent();
    this.c.a = localIntent.getIntExtra("ResId", -1);
  }

  protected void onCreate(Bundle paramBundle)
  {
    a.a(this);
    try
    {
      setTheme(16973836);
      super.onCreate(paramBundle);
      setContentView(p.release_notes);
      d();
      b.b();
      this.d.a = ((WebView)findViewById(m.web_view));
      this.e.a = ar.a(this, this.c.a);
      this.d.a.loadData(this.e.a, "text/html", "utf-8");
      return;
    }
    catch (Exception localException)
    {
      ErrorActivity.a(this, localException);
    }
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.about.ReleaseNotesActivity
 * JD-Core Version:    0.6.2
 */