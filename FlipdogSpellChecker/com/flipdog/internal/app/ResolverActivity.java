package com.flipdog.internal.app;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import java.util.List;

public class ResolverActivity extends AlertActivity
  implements DialogInterface.OnClickListener
{
  private l b;
  private PackageManager c;
  private List<g> d;

  protected void a(Bundle paramBundle, CharSequence paramCharSequence, Intent[] paramArrayOfIntent, boolean paramBoolean)
  {
    super.onCreate(paramBundle);
    this.c = getPackageManager();
    a locala = this.a;
    locala.a = paramCharSequence;
    locala.d = this;
    this.d = new n(this, paramArrayOfIntent).a();
    this.b = new l(this, this, this.d);
    locala.b = this.b;
    a();
  }

  public void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    startActivity(this.b.a(paramInt));
    finish();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.internal.app.ResolverActivity
 * JD-Core Version:    0.6.2
 */