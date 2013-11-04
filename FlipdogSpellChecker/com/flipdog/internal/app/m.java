package com.flipdog.internal.app;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.widget.Adapter;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.flipdog.commons.a.ah;

public class m
{
  Adapter a;
  private AlertDialog.Builder b;
  private AlertDialog c;
  private ListView d;
  private DialogInterface.OnClickListener e;

  public m(Context paramContext, DialogInterface.OnClickListener paramOnClickListener)
  {
    this.e = paramOnClickListener;
    a(paramContext);
  }

  private void a(Context paramContext)
  {
    Context localContext = ah.a(paramContext);
    this.b = new AlertDialog.Builder(localContext);
    this.b.setCancelable(true);
    this.b.setNegativeButton("Cancel", null);
    this.d = new ListView(localContext);
    this.d.setLayoutParams(new LinearLayout.LayoutParams(-1, 400));
    LinearLayout localLinearLayout = new LinearLayout(localContext);
    localLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(50, 400));
    localLinearLayout.setPadding(1, 1, 1, 1);
    localLinearLayout.addView(this.d, new LinearLayout.LayoutParams(-1, -1));
    this.d.setBackgroundColor(-986896);
    this.b.setView(localLinearLayout);
  }

  public void a()
  {
    if (this.c == null)
      this.c = this.b.create();
    this.c.show();
  }

  public void a(DialogInterface.OnCancelListener paramOnCancelListener)
  {
    this.b.setOnCancelListener(paramOnCancelListener);
  }

  public void a(DialogInterface.OnClickListener paramOnClickListener)
  {
    this.b.setPositiveButton("OK", paramOnClickListener);
  }

  public void a(ListAdapter paramListAdapter)
  {
    this.a = paramListAdapter;
    this.d.setAdapter(paramListAdapter);
    this.d.setOnItemClickListener(new h(this));
  }

  public void a(CharSequence paramCharSequence)
  {
    this.b.setTitle(paramCharSequence);
  }

  public void b(DialogInterface.OnClickListener paramOnClickListener)
  {
    this.b.setNegativeButton("Cancel", paramOnClickListener);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.internal.app.m
 * JD-Core Version:    0.6.2
 */