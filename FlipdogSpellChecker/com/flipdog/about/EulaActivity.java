package com.flipdog.about;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import com.flipdog.activity.MyActivity;
import com.flipdog.c.b;
import com.flipdog.commons.a.ar;
import com.flipdog.commons.a.t;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.errors.a;
import com.flipdog.errors.activity.ErrorActivity;
import com.flipdog.m;
import com.flipdog.s;

public class EulaActivity extends MyActivity
{
  private r c = new r();
  private e d = new e();
  private p e = new p();

  public static void a(Activity paramActivity, int paramInt1, int paramInt2)
  {
    Intent localIntent = new Intent(paramActivity, EulaActivity.class);
    localIntent.putExtra("EulaResId", paramInt1);
    localIntent.putExtra("EulaVersion", paramInt2);
    paramActivity.startActivity(localIntent);
  }

  private void a(Context paramContext)
    throws PackageManager.NameNotFoundException
  {
    paramContext.startActivity(t.a(paramContext));
  }

  private void f()
  {
    Intent localIntent = getIntent();
    this.c.a = localIntent.getIntExtra("EulaResId", -1);
    this.c.b = localIntent.getIntExtra("EulaVersion", -1);
  }

  private void g()
  {
    this.d.a = ((TextView)findViewById(m.eula_header));
    this.d.b = ((WebView)findViewById(m.eula));
    this.d.c = ((Button)findViewById(m.accept));
    this.d.d = ((Button)findViewById(m.decline));
  }

  private void h()
  {
    this.d.c.setOnClickListener(new j(this));
    this.d.d.setOnClickListener(new k(this));
  }

  private void i()
  {
    b localb = b.b();
    localb.a(this.c.b);
    localb.c();
  }

  private boolean j()
  {
    return i.a(this.c.b);
  }

  protected void d()
  {
    finish();
  }

  protected void e()
  {
    Track.it("accept", new String[] { "Eula" });
    i();
    try
    {
      Track.it("about to startMainActivity", new String[] { "Eula" });
      a(this);
      Track.it("onAccept().finish", new String[] { "Eula" });
      finish();
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      while (true)
      {
        Track.it(localNameNotFoundException);
        ErrorActivity.a(this, localNameNotFoundException);
      }
    }
  }

  protected void onCreate(Bundle paramBundle)
  {
    a.a(this);
    try
    {
      setTheme(16973836);
      super.onCreate(paramBundle);
      setContentView(com.flipdog.p.eula);
      f();
      g();
      h();
      if (j())
      {
        this.d.c.setVisibility(8);
        this.d.d.setVisibility(8);
      }
      this.e.a = getString(s.eula_header);
      this.e.b = ar.a(this, this.c.a);
      this.d.a.setText(this.e.a);
      this.d.b.loadData(this.e.b, "text/html", "utf-8");
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
 * Qualified Name:     com.flipdog.about.EulaActivity
 * JD-Core Version:    0.6.2
 */