package com.yoc.android.yocperformance.adsdk;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public final class g
  implements DialogInterface.OnDismissListener, View.OnClickListener
{
  private final Ad a;
  private final Context b;
  private final AdView c;
  private final String d = "AdClickListener";

  public g(AdView paramAdView, Ad paramAd)
  {
    this.b = paramAdView.getContext();
    this.a = paramAd;
    this.c = paramAdView;
  }

  public void onClick(View paramView)
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(this.a.a());
    this.b.startActivity(localIntent);
  }

  public void onDismiss(DialogInterface paramDialogInterface)
  {
    this.c.e();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.g
 * JD-Core Version:    0.6.2
 */