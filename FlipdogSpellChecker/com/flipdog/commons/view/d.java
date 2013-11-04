package com.flipdog.commons.view;

import android.os.Handler;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout.LayoutParams;
import com.flipdog.commons.diagnostic.Track;

public class d
{
  public static final int a = 1;
  public static final int b = 2;
  public static final int c = 3;
  private View d;
  private View e;
  private View f;
  private int g;
  private int h;

  public d(View paramView1, View paramView2, View paramView3, int paramInt)
  {
    this.d = paramView1;
    this.e = paramView2;
    this.f = paramView3;
    this.g = paramInt;
  }

  private void a(int paramInt)
  {
    RelativeLayout.LayoutParams localLayoutParams = (RelativeLayout.LayoutParams)this.f.getLayoutParams();
    localLayoutParams.topMargin = paramInt;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = Integer.valueOf(paramInt);
    a("setLayoutParams(params.topMargin = %s)", arrayOfObject);
    this.f.setLayoutParams(localLayoutParams);
  }

  private void a(String paramString, Object[] paramArrayOfObject)
  {
    if (Track.isDisabled("Diagnostic"))
      return;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = String.format(paramString, paramArrayOfObject);
    Track.me("Diagnostic", "[Overlay] %s", arrayOfObject);
  }

  private void c()
  {
    int i = this.d.getScrollY();
    int j = this.f.getLayoutParams().height;
    int k = this.d.getHeight();
    int m;
    int n;
    if (this.g == 2)
    {
      m = this.e.getBottom();
      n = Math.max(0, m + -i);
    }
    while (true)
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(i);
      a("scrollY: %s", arrayOfObject1);
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(m);
      a("dockY: %s", arrayOfObject2);
      Object[] arrayOfObject3 = new Object[1];
      arrayOfObject3[0] = Integer.valueOf(j);
      a("overlayHeight: %s", arrayOfObject3);
      Object[] arrayOfObject4 = new Object[1];
      arrayOfObject4[0] = Integer.valueOf(k);
      a("scrollHeight: %s", arrayOfObject4);
      Object[] arrayOfObject5 = new Object[1];
      arrayOfObject5[0] = Integer.valueOf(k - j);
      a("scrollHeight - overlayHeight: %s", arrayOfObject5);
      Object[] arrayOfObject6 = new Object[1];
      arrayOfObject6[0] = Integer.valueOf(m + -i - j);
      a("-scrollY + dockY - overlayHeight: %s", arrayOfObject6);
      int i1 = Math.max(0, Math.min(k - j, n));
      if (i1 != this.h)
      {
        this.h = i1;
        a(i1);
      }
      return;
      if (this.g == 1)
      {
        m = this.e.getTop();
        n = m + -i - j;
      }
      else
      {
        if (this.g != 3)
          break;
        m = this.e.getTop();
        n = m + -i;
      }
    }
    throw new RuntimeException("Unexpected" + this.g);
  }

  public void a()
  {
    new Handler().post(new c(this));
  }

  public void b()
  {
    new Handler().post(new b(this));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.view.d
 * JD-Core Version:    0.6.2
 */