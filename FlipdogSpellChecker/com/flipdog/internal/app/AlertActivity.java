package com.flipdog.internal.app;

import android.app.Activity;
import android.os.Bundle;

public class AlertActivity extends Activity
{
  protected a a;

  protected void a()
  {
    m localm = new m(this.a.e, this.a.d);
    localm.a(this.a.b);
    localm.b(new f(this));
    localm.a(new e(this));
    localm.a();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.a = new a(this);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.internal.app.AlertActivity
 * JD-Core Version:    0.6.2
 */