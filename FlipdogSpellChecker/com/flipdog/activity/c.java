package com.flipdog.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.view.View;
import android.widget.ListView;
import com.b.c.a;
import com.flipdog.commons.a.as;

public class c extends Dialog
{
  protected com.b.h a = new com.b.h();
  protected com.flipdog.commons.s.b b = (com.flipdog.commons.s.b)com.flipdog.commons.i.b.a(com.flipdog.commons.s.b.class);
  protected Activity c;
  protected com.b.h d;
  protected com.b.b.h e = new com.b.b.h();

  public c(Context paramContext)
  {
    super(paramContext);
    a(paramContext);
  }

  public c(Context paramContext, int paramInt)
  {
    super(paramContext, paramInt);
    a(paramContext);
  }

  protected c(Context paramContext, boolean paramBoolean, DialogInterface.OnCancelListener paramOnCancelListener)
  {
    super(paramContext, paramBoolean, paramOnCancelListener);
    a(paramContext);
  }

  private void a(Context paramContext)
  {
    if ((paramContext instanceof MyActivity))
    {
      this.c = ((Activity)paramContext);
      this.d = ((MyActivity)paramContext).a();
    }
    while (!(paramContext instanceof MyPreferenceActivity))
      return;
    this.c = ((Activity)paramContext);
    this.d = ((MyPreferenceActivity)paramContext).a();
  }

  protected <T extends View> T a(int paramInt)
  {
    return as.a(this, paramInt);
  }

  public com.b.h a()
  {
    return this.a;
  }

  protected void a(ListView paramListView, View paramView, int paramInt, long paramLong)
  {
  }

  protected ListView b()
  {
    ListView localListView = (ListView)findViewById(16908298);
    if (localListView != null)
      localListView.setOnItemClickListener(new e(this, localListView));
    return localListView;
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    ((a)this.a.a(a.class)).a(paramBoolean);
    super.onWindowFocusChanged(paramBoolean);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.activity.c
 * JD-Core Version:    0.6.2
 */