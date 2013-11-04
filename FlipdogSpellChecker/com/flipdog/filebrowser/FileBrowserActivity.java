package com.flipdog.filebrowser;

import android.content.ComponentName;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import com.flipdog.activity.MyActivity;
import com.flipdog.ads.k;
import com.flipdog.commons.a.as;
import com.flipdog.errors.activity.ErrorActivity;
import com.flipdog.filebrowser.activity.ActivityWithMenu;
import com.flipdog.filebrowser.d.h;
import com.flipdog.filebrowser.k.j;
import com.flipdog.filebrowser.login.b.c;
import com.flipdog.m;
import com.flipdog.p;
import com.flipdog.q;

public class FileBrowserActivity extends ActivityWithMenu
{
  public static final int c = 1;
  public static final int d = 2;
  public static final int e = 3;
  public static final int f = 1;
  public static final int g = 2;
  public static final int h = 3;
  public static final String i = "com.flipdog.filebrowser.extra.SELECTED_PATHS";
  public static final String j = "com.flipdog.filebrowser.extra.OK_BUTTON_TEXT";
  public static final String k = "com.flipdog.filebrowser.extra.SELECTION_MODE";
  public static final String l = "com.flipdog.filebrowser.extra.DISPLAY_MODE";
  public static final String m = "com.flipdog.filebrowser.extra.PREFERENCES_ENABLED";
  public static final String n = "com.flipdog.filebrowser.extra.START_FOLDER";
  public static final String o = "com.flipdog.filebrowser.extra.MULTI_SELECT";
  public static final String p = "com.flipdog.filebrowser.extra.DISABLE_CLOUDS";
  public static final String q = "com.flipdog.filebrowser.extra.FILTER_BY_CONTENT_TYPE";
  public static final String r = "com.flipdog.filebrowser.extra.FILTER_BY_EXTENSION";
  public c s = new a(this);
  private h u;
  private com.flipdog.filebrowser.a.f v;

  public static final void a(MyActivity paramMyActivity, Intent paramIntent, int paramInt)
  {
    if (paramIntent == null)
      paramIntent = new Intent();
    paramIntent.setComponent(new ComponentName(paramMyActivity, FileBrowserActivity.class));
    paramMyActivity.startActivityForResult(paramIntent, paramInt);
  }

  private void d()
  {
    this.u.d();
    finish();
  }

  private void h()
  {
    as.a(this, m.fbrowse_logging_button).setOnClickListener(new b(this));
  }

  public void onConfigurationChanged(Configuration paramConfiguration)
  {
    super.onConfigurationChanged(paramConfiguration);
    com.flipdog.filebrowser.e.b.f = j.c();
  }

  public void onCreate(Bundle paramBundle)
  {
    setTheme(q.a);
    super.onCreate(paramBundle);
    a(true);
    setResult(0);
    try
    {
      setContentView(p.fbrowse_listview_main);
      ListView localListView = (ListView)as.a(this, m.selaccount_listview);
      com.flipdog.filebrowser.e.b.j();
      this.u = new h(this, localListView);
      this.v = new com.flipdog.filebrowser.a.f(this.u, this.s);
      this.u.a();
      h();
      k.a(this, m.fbrowse_layout_main);
      return;
    }
    catch (Exception localException)
    {
      ErrorActivity.a(this, localException);
    }
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    this.v.b(paramMenu);
    return super.onCreateOptionsMenu(paramMenu);
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      com.flipdog.filebrowser.e.b localb = this.u.b();
      if ((localb == null) || ((localb instanceof com.flipdog.filebrowser.e.f)))
        d();
      while (true)
      {
        return false;
        if ((!this.u.b().g()) && (!this.u.a(h.a)))
          d();
      }
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    this.v.a(paramMenuItem);
    return super.onOptionsItemSelected(paramMenuItem);
  }

  public boolean onPrepareOptionsMenu(Menu paramMenu)
  {
    this.v.a(paramMenu);
    return super.onPrepareOptionsMenu(paramMenu);
  }

  protected void onResume()
  {
    super.onResume();
    this.u.b().notifyDataSetChanged();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.FileBrowserActivity
 * JD-Core Version:    0.6.2
 */