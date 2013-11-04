package com.flipdog.editor;

import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;
import com.flipdog.m;
import com.flipdog.p;

public class am extends Dialog
  implements TabHost.OnTabChangeListener
{
  private h a = new h(null);
  private x b;
  private int c = -1;
  private TabHost d;
  private ac e;

  public am(Context paramContext, x paramx)
  {
    super(paramContext);
    this.b = paramx;
  }

  private void a(String paramString, int paramInt)
  {
    TabHost.TabSpec localTabSpec = this.d.newTabSpec(paramString);
    localTabSpec.setContent(m.grid);
    localTabSpec.setIndicator(null, b(paramInt));
    this.d.addTab(localTabSpec);
  }

  private Drawable b(int paramInt)
  {
    return getContext().getResources().getDrawable(paramInt);
  }

  private void b()
  {
    this.a.a.setOnItemClickListener(new r(this));
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
    this.e.b.a(paramInt);
    this.c = paramInt;
    dismiss();
  }

  protected void onCreate(Bundle paramBundle)
  {
    requestWindowFeature(1);
    super.onCreate(paramBundle);
    setContentView(p.editor_smiles_grid);
    setCanceledOnTouchOutside(true);
    this.d = ((TabHost)findViewById(16908306));
    this.d.setup();
    this.d.setOnTabChangedListener(this);
    a("a", b.a.c);
    a("b", b.b.c);
    c();
    b();
    this.b.a(b.a);
    this.a.a.setAdapter(this.b);
    this.d.setCurrentTab(1);
    this.d.setCurrentTab(0);
  }

  public void onTabChanged(String paramString)
  {
    if ("a".equals(paramString))
      this.e = b.a;
    while (true)
    {
      this.b.a(this.e);
      this.b.notifyDataSetChanged();
      return;
      if ("b".equals(paramString))
        this.e = b.b;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.am
 * JD-Core Version:    0.6.2
 */