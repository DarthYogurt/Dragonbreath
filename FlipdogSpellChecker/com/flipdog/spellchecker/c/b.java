package com.flipdog.spellchecker.c;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.g.ae;
import com.flipdog.commons.g.j;
import com.flipdog.spellchecker.u;
import com.flipdog.spellchecker.y;

public class b
{
  private com.flipdog.commons.s.b a;
  private View b;
  private com.b.b.h c = new com.b.b.h();
  private MyActivity d;

  public b(MyActivity paramMyActivity)
  {
    this.d = paramMyActivity;
    this.a = ((com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class));
    b();
  }

  private void b()
  {
    this.a.a(this.c, new e(this));
  }

  protected void a()
  {
    if ((((com.flipdog.commons.g.h)com.flipdog.commons.i.b.a(com.flipdog.commons.g.h.class)).e() == j.b) && (this.b != null))
      this.b.setVisibility(8);
  }

  public void a(int paramInt, String paramString)
  {
    this.b = this.d.getLayoutInflater().inflate(u.invalid_license_notification, null);
    LinearLayout localLinearLayout = (LinearLayout)this.d.findViewById(paramInt);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-1, -2);
    localLinearLayout.addView(this.b, localLayoutParams);
    localLinearLayout.invalidate();
    Button localButton1 = (Button)this.b.findViewById(y.buy);
    Button localButton2 = (Button)this.b.findViewById(y.check_license);
    localButton1.setOnClickListener(new c(this));
    localButton2.setOnClickListener(new d(this));
  }

  protected void a(MyActivity paramMyActivity)
  {
    new ae(paramMyActivity).a();
  }

  protected void b(MyActivity paramMyActivity)
  {
    paramMyActivity.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("http://market.android.com/search?q=pname:com.maildroid.pro")));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.c.b
 * JD-Core Version:    0.6.2
 */