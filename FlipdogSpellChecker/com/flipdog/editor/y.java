package com.flipdog.editor;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListAdapter;
import com.flipdog.m;

public class y extends Dialog
{
  private z a = new z(null);
  private ListAdapter b;
  private int c = -1;
  private av d;
  private int e;

  public y(Context paramContext, int paramInt, ListAdapter paramListAdapter)
  {
    super(paramContext);
    this.b = paramListAdapter;
    this.e = paramInt;
  }

  private void b()
  {
    this.a.a.setOnItemClickListener(new c(this));
  }

  private void c()
  {
    this.a.a = ((GridView)findViewById(m.grid));
  }

  public Object a()
  {
    if (this.c == -1)
      return null;
    return this.b.getItem(this.c);
  }

  protected void a(int paramInt)
  {
    this.c = paramInt;
    if (this.d != null)
      this.d.a(paramInt);
    dismiss();
  }

  public void a(av paramav)
  {
    this.d = paramav;
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(this.e);
    setCanceledOnTouchOutside(true);
    c();
    b();
    this.a.a.setAdapter(this.b);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.y
 * JD-Core Version:    0.6.2
 */