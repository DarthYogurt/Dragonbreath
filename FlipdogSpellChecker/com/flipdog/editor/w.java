package com.flipdog.editor;

import android.view.View;
import android.widget.ToggleButton;
import com.flipdog.commons.a.as;
import com.flipdog.commons.d;
import com.flipdog.m;

public class w
{
  public MyHorizontalScrollView a;
  public View b;
  public View c;
  public View d;
  public View e;
  public View f;
  public View g;
  public View h;
  public View i;
  public View j;
  public View k;
  public View l;
  public View m;
  public View n;
  public View o;
  public View p;
  public View q;
  public View r;
  private d s;

  private <T extends View> T a(int paramInt)
  {
    return this.s.a(paramInt);
  }

  public void a(Object paramObject, int paramInt)
  {
    this.s = d.a(paramObject);
    View localView = a(paramInt);
    if (localView != null)
    {
      this.a = ((MyHorizontalScrollView)as.a(localView, m.horizontal_scroll));
      this.b = as.a(localView, m.scrollable_to_the_left);
      this.c = as.a(localView, m.scrollable_to_the_right);
    }
    this.f = ((ToggleButton)a(m.bold));
    this.g = ((ToggleButton)a(m.italic));
    this.h = ((ToggleButton)a(m.underline));
    this.i = ((ToggleButton)a(m.font));
    this.r = ((ToggleButton)a(m.bullets));
    this.p = ((ToggleButton)a(m.superscript));
    this.q = ((ToggleButton)a(m.subscript));
    this.j = ((ToggleButton)a(m.size));
    this.k = ((ToggleButton)a(m.color));
    this.l = ((ToggleButton)a(m.highlight));
    this.m = ((ToggleButton)a(m.smile));
    this.n = ((ToggleButton)a(m.image));
    this.o = ((ToggleButton)a(m.clear_formatting));
    this.d = a(m.undo);
    this.e = a(m.redo);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.w
 * JD-Core Version:    0.6.2
 */