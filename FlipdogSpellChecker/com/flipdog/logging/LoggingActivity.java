package com.flipdog.logging;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;
import android.widget.ListView;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.actionbar.ActionBarActivity;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.errors.activity.ErrorActivity;
import com.flipdog.m;
import com.flipdog.p;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoggingActivity extends ActionBarActivity
  implements View.OnClickListener
{
  private static final int c = 300;
  private static final int d = 3000;
  private static final int e = 1;
  private static final int f = 2;
  private static final int g = 3;
  private static final int h = 4;
  private ArrayList<String> i = new ArrayList();
  private e j;
  private Process k;
  private final n l = new n(null);
  private final o m = new o(null);
  private MenuItem n;

  private Process a(String[] paramArrayOfString)
    throws IOException
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add("logcat");
    int i1 = paramArrayOfString.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return Runtime.getRuntime().exec((String[])localArrayList.toArray(new String[0]));
      localArrayList.add(paramArrayOfString[i2]);
    }
  }

  public static void a(Activity paramActivity)
  {
    paramActivity.startActivity(new Intent(paramActivity, LoggingActivity.class));
  }

  private void a(String paramString)
  {
    a(new h(this, paramString));
  }

  private boolean a(MenuItem paramMenuItem)
  {
    try
    {
      switch (paramMenuItem.getItemId())
      {
      case 4:
        r();
        return true;
      case 1:
      case 2:
      case 3:
      }
    }
    catch (Exception localException)
    {
      ErrorActivity.a(this, localException);
      break label69;
      t();
      return true;
      d();
      return true;
      s();
      return true;
    }
    label69: return false;
  }

  private void b(String paramString)
  {
    if (this.i.size() > 3000);
    for (int i1 = 0; ; i1++)
    {
      if (i1 >= 300)
      {
        this.i.add(paramString);
        this.j.notifyDataSetChanged();
        if (!this.m.b)
          break;
        this.l.b.setSelectionFromTop(-1 + this.i.size(), 0);
        return;
      }
      this.i.remove(0);
    }
    this.l.b.requestLayout();
  }

  private void b(boolean paramBoolean)
  {
    this.m.a = paramBoolean;
    n();
    l();
    if (paramBoolean)
    {
      o();
      return;
    }
    u();
  }

  private String[] b(String[] paramArrayOfString)
  {
    return paramArrayOfString;
  }

  private void h()
  {
    c localc = c.b();
    this.m.a = localc.e();
    this.m.b = localc.g();
  }

  private void i()
  {
    this.l.b = ((ListView)findViewById(m.list));
    this.l.a = ((CheckBox)findViewById(m.auto_scroll));
  }

  private void j()
  {
    this.l.a.setOnClickListener(this);
  }

  private void k()
  {
    this.m.b = this.l.a.isChecked();
    n();
    l();
  }

  private void l()
  {
    this.l.a.setChecked(this.m.b);
    this.l.a.setEnabled(this.m.a);
    this.l.b.setFastScrollEnabled(m());
  }

  private boolean m()
  {
    if (!this.m.a);
    while (!this.m.b)
      return true;
    return false;
  }

  private void n()
  {
    c localc = c.b();
    localc.a(this.m.a);
    localc.b(this.m.b);
    localc.d();
  }

  private void o()
  {
    com.flipdog.commons.f.c.a(new i(this));
  }

  private void p()
  {
    try
    {
      this.k = a(new String[] { "-v", "time" });
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(this.k.getInputStream()));
      while (true)
      {
        String str = localBufferedReader.readLine();
        if (str == null)
          return;
        a(str);
      }
    }
    catch (IOException localIOException)
    {
      Track.it(localIOException);
    }
  }

  private void q()
  {
    if (this.m.a)
    {
      this.n.setTitle("Pause");
      this.n.setIcon(com.flipdog.a.logging_pause);
      return;
    }
    this.n.setTitle("Play");
    this.n.setIcon(com.flipdog.a.logging_play);
  }

  private void r()
  {
    if (this.m.a);
    for (boolean bool = false; ; bool = true)
    {
      b(bool);
      q();
      return;
    }
  }

  private void s()
  {
    new a(this).show();
  }

  private void t()
  {
    u();
    v();
    this.i.clear();
    this.j.notifyDataSetChanged();
    if (this.m.a)
      o();
  }

  private void u()
  {
    if (this.k == null)
      return;
    this.k.destroy();
    this.k = null;
  }

  private void v()
  {
    try
    {
      a(new String[] { "-c" });
      return;
    }
    catch (IOException localIOException)
    {
      localIOException.printStackTrace();
    }
  }

  private void w()
  {
    u();
  }

  private String x()
  {
    return ax.a(this.i, "\n");
  }

  protected void d()
  {
    String str = x();
    Intent localIntent1 = new Intent("android.intent.action.SEND");
    localIntent1.setType("text/plain");
    localIntent1.putExtra("android.intent.extra.EMAIL", b(new String[] { "maildroiddev@gmail.com" }));
    localIntent1.putExtra("android.intent.extra.SUBJECT", "Log");
    File localFile = new File(((com.flipdog.commons.r.b)com.flipdog.commons.i.b.a(com.flipdog.commons.r.b.class)).c(), "flipdog-log.txt");
    localFile.getParentFile().mkdirs();
    try
    {
      com.flipdog.commons.a.n.a(str, localFile);
      localIntent1.putExtra("android.intent.extra.STREAM", Uri.parse("file://" + localFile.getPath()));
      localIntent2 = Intent.createChooser(localIntent1, "Send");
    }
    catch (IOException localIOException)
    {
      try
      {
        Intent localIntent2;
        startActivity(localIntent2);
        return;
        localIOException = localIOException;
        Track.it(localIOException);
        localIntent1.putExtra("android.intent.extra.TEXT", str);
      }
      catch (ActivityNotFoundException localActivityNotFoundException)
      {
        com.flipdog.commons.a.e.a("No application found.");
      }
    }
  }

  public void onClick(View paramView)
  {
    if (paramView == this.l.a)
      k();
  }

  public boolean onContextItemSelected(MenuItem paramMenuItem)
  {
    return a(paramMenuItem);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(p.logcat);
    i();
    j();
    h();
    this.j = new e(this, this.i);
    this.l.b.setAdapter(this.j);
    this.j.notifyDataSetChanged();
    if (this.m.a)
      o();
    l();
  }

  public boolean onCreateOptionsMenu(Menu paramMenu)
  {
    if (paramMenu == null)
      return true;
    paramMenu.clear();
    this.n = paramMenu.add(0, 4, 0, "");
    q();
    paramMenu.add(0, 2, 0, "Send");
    paramMenu.add(0, 1, 0, "Clear");
    paramMenu.add(0, 3, 0, "Tags");
    return true;
  }

  public boolean onOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (a(paramMenuItem))
      return true;
    return super.onOptionsItemSelected(paramMenuItem);
  }

  protected void onPause()
  {
    w();
    super.onPause();
  }

  protected void onStop()
  {
    w();
    super.onStop();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.logging.LoggingActivity
 * JD-Core Version:    0.6.2
 */