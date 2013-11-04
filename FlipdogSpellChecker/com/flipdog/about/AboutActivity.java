package com.flipdog.about;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.ar;
import com.flipdog.commons.a.ax;
import com.flipdog.errors.activity.ErrorActivity;
import com.flipdog.p;

public class AboutActivity extends MyActivity
{
  private h c = new h();
  private l d = new l();
  private a e = new a();

  public static void a(Context paramContext, String paramString, int paramInt)
  {
    Intent localIntent = new Intent(paramContext, AboutActivity.class);
    localIntent.putExtra("Mode", 2);
    localIntent.putExtra("Title", paramString);
    localIntent.putExtra("ResId", paramInt);
    paramContext.startActivity(localIntent);
  }

  public static void a(Context paramContext, String paramString, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Intent localIntent = new Intent(paramContext, AboutActivity.class);
    localIntent.putExtra("Mode", 1);
    localIntent.putExtra("Title", paramString);
    localIntent.putExtra("ResId", paramInt1);
    localIntent.putExtra("ReleaseNotesResId", paramInt2);
    localIntent.putExtra("EulaResId", paramInt3);
    localIntent.putExtra("EulaVersion", paramInt4);
    paramContext.startActivity(localIntent);
  }

  private void g()
  {
    this.d.a = ((WebView)findViewById(com.flipdog.m.web_view));
    this.d.b = ((Button)findViewById(com.flipdog.m.next_button));
    this.d.c = ((Button)findViewById(com.flipdog.m.eula_button));
    this.d.d = ((Button)findViewById(com.flipdog.m.release_notes_button));
    if (this.c.b == 2)
    {
      this.d.c.setVisibility(8);
      this.d.d.setVisibility(8);
    }
  }

  private void h()
  {
    if (!ax.a(this.c.a))
      setTitle(this.c.a);
    this.e.a = ar.a(this, this.c.c);
    this.d.a.loadData(this.e.a, "text/html", "utf-8");
  }

  private void i()
  {
    Intent localIntent = getIntent();
    this.c.b = localIntent.getIntExtra("Mode", -1);
    this.c.a = localIntent.getStringExtra("Title");
    this.c.c = localIntent.getIntExtra("ResId", -1);
    this.c.d = localIntent.getIntExtra("ReleaseNotesResId", -1);
    this.c.e = localIntent.getIntExtra("EulaResId", -1);
    this.c.f = localIntent.getIntExtra("EulaVersion", -1);
  }

  void d()
  {
    this.d.b.setOnClickListener(new m(this));
    this.d.c.setOnClickListener(new n(this));
    this.d.d.setOnClickListener(new o(this));
  }

  protected void e()
  {
    ReleaseNotesActivity.a(this, this.c.d);
  }

  protected void f()
  {
    EulaActivity.a(this, this.c.e, this.c.f);
  }

  protected void onCreate(Bundle paramBundle)
  {
    com.flipdog.errors.a.a(this);
    try
    {
      setTheme(16973836);
      super.onCreate(paramBundle);
      setContentView(p.about);
      i();
      g();
      h();
      d();
      return;
    }
    catch (Exception localException)
    {
      ErrorActivity.a(this, localException);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.about.AboutActivity
 * JD-Core Version:    0.6.2
 */