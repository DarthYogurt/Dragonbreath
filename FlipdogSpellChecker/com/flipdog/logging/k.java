package com.flipdog.logging;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.b.b.h;
import com.flipdog.p;

public class k
{
  private Activity a;
  private int b;
  private com.flipdog.commons.s.b c;
  private h d = new h();
  private View e;

  public k(Activity paramActivity, int paramInt)
  {
    this.a = paramActivity;
    this.b = paramInt;
    this.c = ((com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class));
    b();
    b(paramActivity, paramInt);
    c();
  }

  private void a(Activity paramActivity, int paramInt)
  {
    if (d())
    {
      this.e.setVisibility(0);
      return;
    }
    this.e.setVisibility(8);
  }

  private void b()
  {
    this.c.a(this.d, new q(this));
  }

  private void b(Activity paramActivity, int paramInt)
  {
    this.e = paramActivity.getLayoutInflater().inflate(p.logging_notification, null);
    LinearLayout localLinearLayout = (LinearLayout)paramActivity.findViewById(paramInt);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    localLinearLayout.addView(this.e, localLayoutParams);
    localLinearLayout.invalidate();
    this.e.setOnClickListener(new t(this, paramActivity));
  }

  private void c()
  {
    a(this.a, this.b);
  }

  private boolean d()
  {
    return c.b().e();
  }

  protected void a()
  {
    this.a.runOnUiThread(new s(this));
  }

  protected void a(Activity paramActivity)
  {
    paramActivity.startActivity(new Intent(paramActivity, LoggingActivity.class));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.logging.k
 * JD-Core Version:    0.6.2
 */