package com.flipdog.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import com.b.h;

public class MyActivity extends FragmentActivity
  implements f
{
  protected h a = new h();
  protected boolean b;

  public static <TView extends View> TView a(View paramView, int paramInt)
  {
    return paramView.findViewById(paramInt);
  }

  public h a()
  {
    return this.a;
  }

  public void a(Runnable paramRunnable)
  {
    runOnUiThread(new b(this, paramRunnable));
  }

  public boolean b()
  {
    return this.b;
  }

  public Context c()
  {
    return this;
  }

  public void finish()
  {
    this.b = true;
    super.finish();
  }

  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    ((a)this.a.a(a.class)).a(paramInt1, paramInt2, paramIntent);
  }

  protected void onDestroy()
  {
    super.onDestroy();
    this.b = true;
    ((com.b.c.b)this.a.a(com.b.c.b.class)).a();
  }

  public void onWindowFocusChanged(boolean paramBoolean)
  {
    ((com.b.c.a)this.a.a(com.b.c.a.class)).a(paramBoolean);
    super.onWindowFocusChanged(paramBoolean);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.activity.MyActivity
 * JD-Core Version:    0.6.2
 */