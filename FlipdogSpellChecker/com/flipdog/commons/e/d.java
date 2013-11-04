package com.flipdog.commons.e;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.View;
import android.widget.ProgressBar;
import com.flipdog.a.a.b;
import com.flipdog.activity.f;
import com.flipdog.commons.a.as;
import com.flipdog.m;

public abstract class d
{
  private n a = new n(null);
  private b b;
  private AlertDialog c;

  public d(Context paramContext, f paramf, String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramString);
    View localView = View.inflate(paramContext, com.flipdog.p.saving_layout, null);
    localBuilder.setView(localView);
    this.a.a = ((ProgressBar)as.a(localView, m.progress_bar));
    this.a.a.setVisibility(8);
    this.a.b = ((ProgressBar)as.a(localView, m.progress_circle));
    this.a.b.setVisibility(8);
    localBuilder.setNegativeButton(17039360, new p(this));
    this.b = new q(this, paramf);
    this.c = localBuilder.create();
    this.c.setOnCancelListener(new r(this));
    this.c.show();
  }

  private void a(long paramLong1, long paramLong2)
  {
    if (paramLong2 == -1L)
    {
      this.a.a.setVisibility(8);
      this.a.b.setVisibility(0);
      return;
    }
    this.a.a.setVisibility(0);
    this.a.b.setVisibility(8);
    this.a.a.setMax((int)paramLong2);
    this.a.a.setProgress((int)paramLong1);
  }

  protected abstract void a();

  public b b()
  {
    return this.b;
  }

  public void c()
  {
    this.c.dismiss();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.e.d
 * JD-Core Version:    0.6.2
 */