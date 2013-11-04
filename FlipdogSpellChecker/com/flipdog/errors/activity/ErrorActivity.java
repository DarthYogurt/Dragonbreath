package com.flipdog.errors.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.ClipboardManager;
import android.text.method.ScrollingMovementMethod;
import android.widget.Button;
import android.widget.TextView;
import com.flipdog.commons.a.ac;
import com.flipdog.commons.a.ay;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.m;
import com.flipdog.p;

public class ErrorActivity extends Activity
{
  private f a = new f();
  private e b = new e();
  private com.flipdog.errors.c c;

  public static void a(Context paramContext, Exception paramException)
  {
    a(paramContext, paramException, null);
  }

  public static void a(Context paramContext, Exception paramException, String paramString)
  {
    Track.it(paramException);
    Intent localIntent = new Intent(paramContext, ErrorActivity.class);
    localIntent.putExtra("Error", ac.a(paramException));
    localIntent.putExtra("Details", paramString);
    paramContext.startActivity(localIntent);
  }

  public static void a(Context paramContext, Exception paramException, String paramString1, String paramString2)
  {
    a(paramContext, paramException, paramString1, paramString2, null);
  }

  public static void a(Context paramContext, Exception paramException, String paramString1, String paramString2, String paramString3)
  {
    Track.it(paramException);
    Intent localIntent = new Intent(paramContext, ErrorActivity.class);
    localIntent.putExtra("Error", ac.a(paramException));
    localIntent.putExtra("Protocol", paramString1);
    localIntent.putExtra("Domain", paramString2);
    localIntent.putExtra("Details", paramString3);
    paramContext.startActivity(localIntent);
  }

  public static void a(Context paramContext, String paramString)
  {
    Intent localIntent = new Intent(paramContext, ErrorActivity.class);
    localIntent.putExtra("Error", paramString);
    paramContext.startActivity(localIntent);
  }

  public static void a(Context paramContext, String paramString1, String paramString2)
  {
    Intent localIntent = new Intent(paramContext, ErrorActivity.class);
    localIntent.putExtra("Error", paramString1);
    localIntent.putExtra("Details", paramString2);
    paramContext.startActivity(localIntent);
  }

  private static ay b()
  {
    return ay.a();
  }

  private com.flipdog.errors.c c()
  {
    return com.flipdog.errors.d.a(this.b.a, this.b.b, this.b.c, this.b.d);
  }

  private void d()
  {
    Intent localIntent = getIntent();
    this.b.a = localIntent.getStringExtra("Protocol");
    this.b.b = localIntent.getStringExtra("Domain");
    this.b.c = localIntent.getStringExtra("Details");
    this.b.d = localIntent.getStringExtra("Error");
  }

  private void e()
  {
    com.flipdog.commons.f.c.a(new c(this, a()));
    finish();
  }

  protected String a()
  {
    try
    {
      String str = com.flipdog.errors.d.a(this.c);
      return str;
    }
    catch (Exception localException)
    {
      Track.it(localException);
    }
    return this.a.b.getText().toString() + "\nuuid: " + this.c.l;
  }

  protected void a(String paramString)
  {
    try
    {
      new com.flipdog.errors.a.a().a(this.c.j, paramString);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }

  protected void b(String paramString)
  {
    com.flipdog.commons.a.f.a(this).setText(paramString);
    com.flipdog.commons.a.e.a("done");
  }

  protected void onCreate(Bundle paramBundle)
  {
    setTheme(16973836);
    super.onCreate(paramBundle);
    setContentView(p.error_activity);
    d();
    this.c = c();
    this.a.a = ((TextView)findViewById(m.version));
    this.a.b = ((TextView)findViewById(m.error_message));
    this.a.c = ((Button)findViewById(m.submit));
    this.a.d = ((Button)findViewById(m.copy));
    this.a.e = ((Button)findViewById(m.cancel));
    this.a.b.setMovementMethod(ScrollingMovementMethod.getInstance());
    this.a.c.setOnClickListener(new b(this));
    this.a.d.setOnClickListener(new a(this));
    this.a.e.setOnClickListener(new d(this));
    this.a.a.setText("Version: " + b().a);
    this.a.b.setText(com.flipdog.errors.d.b(this.c));
  }

  protected void onDestroy()
  {
    super.onDestroy();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.errors.activity.ErrorActivity
 * JD-Core Version:    0.6.2
 */