package android.widget;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.IBinder;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import com.flipdog.e;
import java.lang.ref.WeakReference;

public class MyPopupWindow
{
  private static final int[] P = { 16842922 };
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  private int A;
  private int B;
  private int C;
  private int D;
  private int[] E = new int[2];
  private int[] F = new int[2];
  private Rect G = new Rect();
  private Drawable H;
  private Drawable I;
  private Drawable J;
  private boolean K;
  private int L = 1000;
  private c M;
  private boolean N = false;
  private int O = -1;
  private WeakReference<View> Q;
  private ViewTreeObserver.OnScrollChangedListener R = new b(this);
  private int S;
  private int T;
  private Context d;
  private WindowManager e;
  private boolean f;
  private boolean g;
  private View h;
  private View i;
  private boolean j;
  private int k = 0;
  private int l = 1;
  private boolean m = true;
  private boolean n = false;
  private boolean o = true;
  private int p = -1;
  private boolean q;
  private boolean r;
  private boolean s = true;
  private boolean t = false;
  private boolean u;
  private View.OnTouchListener v;
  private int w;
  private int x;
  private int y;
  private int z;

  public MyPopupWindow()
  {
    this(null, 0, 0);
  }

  public MyPopupWindow(int paramInt1, int paramInt2)
  {
    this(null, paramInt1, paramInt2);
  }

  public MyPopupWindow(Context paramContext)
  {
    this(paramContext, null);
  }

  public MyPopupWindow(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 16842870);
  }

  public MyPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    this(paramContext, paramAttributeSet, paramInt, 0);
  }

  public MyPopupWindow(Context paramContext, AttributeSet paramAttributeSet, int paramInt1, int paramInt2)
  {
    this.d = paramContext;
    this.e = ((WindowManager)paramContext.getSystemService("window"));
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, e.PopupWindow, paramInt1, paramInt2);
    this.H = localTypedArray.getDrawable(0);
    localTypedArray.getResourceId(1, -1);
    this.O = -1;
    localTypedArray.recycle();
  }

  public MyPopupWindow(View paramView)
  {
    this(paramView, 0, 0);
  }

  public MyPopupWindow(View paramView, int paramInt1, int paramInt2)
  {
    this(paramView, paramInt1, paramInt2, false);
  }

  public MyPopupWindow(View paramView, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (paramView != null)
    {
      this.d = paramView.getContext();
      this.e = ((WindowManager)this.d.getSystemService("window"));
    }
    a(paramView);
    f(paramInt1);
    e(paramInt2);
    a(paramBoolean);
  }

  private WindowManager.LayoutParams a(IBinder paramIBinder)
  {
    WindowManager.LayoutParams localLayoutParams = new WindowManager.LayoutParams();
    localLayoutParams.gravity = 51;
    int i1 = this.x;
    this.y = i1;
    localLayoutParams.width = i1;
    int i2 = this.A;
    this.B = i2;
    localLayoutParams.height = i2;
    if (this.H != null);
    for (localLayoutParams.format = this.H.getOpacity(); ; localLayoutParams.format = -3)
    {
      localLayoutParams.flags = g(localLayoutParams.flags);
      localLayoutParams.type = this.L;
      localLayoutParams.token = paramIBinder;
      localLayoutParams.softInputMode = this.l;
      localLayoutParams.setTitle("PopupWindow:" + Integer.toHexString(hashCode()));
      return localLayoutParams;
    }
  }

  private void a(View paramView, boolean paramBoolean1, int paramInt1, int paramInt2, boolean paramBoolean2, int paramInt3, int paramInt4)
  {
    if ((!p()) || (this.h == null))
      return;
    WeakReference localWeakReference = this.Q;
    int i1;
    label79: WindowManager.LayoutParams localLayoutParams;
    int i3;
    label108: int i2;
    if ((paramBoolean1) && ((this.S != paramInt1) || (this.T != paramInt2)))
    {
      i1 = 1;
      if ((localWeakReference != null) && (localWeakReference.get() == paramView) && ((i1 == 0) || (this.g)))
        break label210;
      c(paramView, paramInt1, paramInt2);
      localLayoutParams = (WindowManager.LayoutParams)this.i.getLayoutParams();
      if (!paramBoolean2)
        break label283;
      if (paramInt3 != -1)
        break label229;
      i3 = this.C;
      if (paramInt4 != -1)
        break label242;
      i2 = this.D;
    }
    while (true)
    {
      label120: int i4 = localLayoutParams.x;
      int i5 = localLayoutParams.y;
      label152: int i6;
      int i7;
      if (paramBoolean1)
      {
        k(a(paramView, localLayoutParams, paramInt1, paramInt2));
        i6 = localLayoutParams.x;
        i7 = localLayoutParams.y;
        if ((i4 != localLayoutParams.x) || (i5 != localLayoutParams.y))
          break label277;
      }
      label277: for (boolean bool = false; ; bool = true)
      {
        a(i6, i7, i3, i2, bool);
        return;
        i1 = 0;
        break;
        label210: if (i1 == 0)
          break label79;
        this.S = paramInt1;
        this.T = paramInt2;
        break label79;
        label229: this.C = paramInt3;
        i3 = paramInt3;
        break label108;
        label242: this.D = paramInt4;
        i2 = paramInt4;
        break label120;
        k(a(paramView, localLayoutParams, this.S, this.T));
        break label152;
      }
      label283: i2 = paramInt4;
      i3 = paramInt3;
    }
  }

  private void a(WindowManager.LayoutParams paramLayoutParams)
  {
    int i1 = -2;
    if ((this.h == null) || (this.d == null) || (this.e == null))
      throw new IllegalStateException("You must specify a valid content view by calling setContentView() before attempting to show the popup.");
    if (this.H != null)
    {
      ViewGroup.LayoutParams localLayoutParams = this.h.getLayoutParams();
      if ((localLayoutParams == null) || (localLayoutParams.height != i1))
        break label141;
    }
    while (true)
    {
      a locala = new a(this, this.d);
      FrameLayout.LayoutParams localLayoutParams1 = new FrameLayout.LayoutParams(-1, i1);
      locala.setBackgroundDrawable(this.H);
      locala.addView(this.h, localLayoutParams1);
      for (this.i = locala; ; this.i = this.h)
      {
        this.C = paramLayoutParams.width;
        this.D = paramLayoutParams.height;
        return;
      }
      label141: i1 = -1;
    }
  }

  private boolean a(View paramView, WindowManager.LayoutParams paramLayoutParams, int paramInt1, int paramInt2)
  {
    int i1 = paramView.getHeight();
    paramView.getLocationInWindow(this.E);
    paramLayoutParams.x = (paramInt1 + this.E[0]);
    paramLayoutParams.y = (paramInt2 + (i1 + this.E[1]));
    paramLayoutParams.gravity = 51;
    paramView.getLocationOnScreen(this.F);
    Rect localRect = new Rect();
    paramView.getWindowVisibleDisplayFrame(localRect);
    int i2 = paramInt2 + (i1 + this.F[1]);
    View localView = paramView.getRootView();
    boolean bool;
    if (i2 + this.D <= localRect.bottom)
    {
      int i10 = paramLayoutParams.x + this.C - localView.getWidth();
      bool = false;
      if (i10 <= 0);
    }
    else
    {
      if (this.s)
      {
        int i8 = paramView.getScrollX();
        int i9 = paramView.getScrollY();
        paramView.requestRectangleOnScreen(new Rect(i8, i9, paramInt1 + (i8 + this.C), paramInt2 + (i9 + this.D + paramView.getHeight())), true);
      }
      paramView.getLocationInWindow(this.E);
      paramLayoutParams.x = (paramInt1 + this.E[0]);
      paramLayoutParams.y = (paramInt2 + (this.E[1] + paramView.getHeight()));
      paramView.getLocationOnScreen(this.F);
      int i3 = localRect.bottom - this.F[1] - paramView.getHeight() - paramInt2;
      int i4 = this.F[1] - paramInt2 - localRect.top;
      bool = false;
      if (i3 < i4)
        bool = true;
      if (!bool)
        break label457;
      paramLayoutParams.gravity = 83;
      paramLayoutParams.y = (paramInt2 + (localView.getHeight() - this.E[1]));
    }
    int i7;
    if (this.r)
    {
      int i5 = localRect.right - localRect.left;
      int i6 = paramLayoutParams.x + paramLayoutParams.width;
      if (i6 > i5)
        paramLayoutParams.x -= i6 - i5;
      if (paramLayoutParams.x < localRect.left)
      {
        paramLayoutParams.x = localRect.left;
        paramLayoutParams.width = Math.min(paramLayoutParams.width, i5);
      }
      if (!bool)
        break label478;
      i7 = paramInt2 + this.F[1] - this.D;
      if (i7 >= 0);
    }
    label457: label478: for (paramLayoutParams.y = (i7 + paramLayoutParams.y); ; paramLayoutParams.y = Math.max(paramLayoutParams.y, localRect.top))
    {
      paramLayoutParams.gravity = (0x10000000 | paramLayoutParams.gravity);
      return bool;
      paramLayoutParams.y = (paramInt2 + (this.E[1] + paramView.getHeight()));
      break;
    }
  }

  private void b(WindowManager.LayoutParams paramLayoutParams)
  {
    if (this.d != null)
      paramLayoutParams.packageName = this.d.getPackageName();
    this.e.addView(this.i, paramLayoutParams);
  }

  private void c(View paramView, int paramInt1, int paramInt2)
  {
    v();
    this.Q = new WeakReference(paramView);
    ViewTreeObserver localViewTreeObserver = paramView.getViewTreeObserver();
    if (localViewTreeObserver != null)
      localViewTreeObserver.addOnScrollChangedListener(this.R);
    this.S = paramInt1;
    this.T = paramInt2;
  }

  private int g(int paramInt)
  {
    int i1 = 0xFF797DE7 & paramInt;
    if (this.N)
      i1 |= 32768;
    if (!this.j)
    {
      i1 |= 8;
      if (this.k == 1)
        i1 |= 131072;
    }
    while (true)
    {
      if (!this.m)
        i1 |= 16;
      if (this.n)
        i1 |= 262144;
      if (!this.o)
        i1 |= 512;
      if (k())
        i1 |= 8388608;
      if (this.q)
        i1 |= 256;
      if (this.t)
        i1 |= 65536;
      if (this.u)
        i1 |= 32;
      return i1;
      if (this.k == 2)
        i1 |= 131072;
    }
  }

  private void k(boolean paramBoolean)
  {
    if (paramBoolean != this.K)
    {
      this.K = paramBoolean;
      if (this.H != null)
      {
        if (this.I == null)
          break label58;
        if (!this.K)
          break label46;
        this.i.setBackgroundDrawable(this.I);
      }
    }
    return;
    label46: this.i.setBackgroundDrawable(this.J);
    return;
    label58: this.i.refreshDrawableState();
  }

  private int u()
  {
    if (this.O == -1)
      return 0;
    return this.O;
  }

  private void v()
  {
    WeakReference localWeakReference = this.Q;
    if (localWeakReference != null);
    for (View localView = (View)localWeakReference.get(); ; localView = null)
    {
      if (localView != null)
        localView.getViewTreeObserver().removeOnScrollChangedListener(this.R);
      this.Q = null;
      return;
    }
  }

  public int a(View paramView, int paramInt)
  {
    return a(paramView, paramInt, false);
  }

  public int a(View paramView, int paramInt, boolean paramBoolean)
  {
    Rect localRect = new Rect();
    paramView.getWindowVisibleDisplayFrame(localRect);
    int[] arrayOfInt = this.E;
    paramView.getLocationOnScreen(arrayOfInt);
    int i1 = localRect.bottom;
    if (paramBoolean)
      i1 = paramView.getContext().getResources().getDisplayMetrics().heightPixels;
    int i2 = Math.max(i1 - (arrayOfInt[1] + paramView.getHeight()) - paramInt, paramInt + (arrayOfInt[1] - localRect.top));
    if (this.H != null)
    {
      this.H.getPadding(this.G);
      i2 -= this.G.top + this.G.bottom;
    }
    return i2;
  }

  public Drawable a()
  {
    return this.H;
  }

  public void a(int paramInt)
  {
    this.O = paramInt;
  }

  public void a(int paramInt1, int paramInt2)
  {
    this.w = paramInt1;
    this.z = paramInt2;
  }

  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    a(paramInt1, paramInt2, paramInt3, paramInt4, false);
  }

  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean)
  {
    boolean bool = true;
    if (paramInt3 != -1)
    {
      this.y = paramInt3;
      f(paramInt3);
    }
    if (paramInt4 != -1)
    {
      this.B = paramInt4;
      e(paramInt4);
    }
    if ((!p()) || (this.h == null));
    label277: label286: label291: 
    while (true)
    {
      return;
      WindowManager.LayoutParams localLayoutParams = (WindowManager.LayoutParams)this.i.getLayoutParams();
      int i1;
      int i2;
      if (this.w < 0)
      {
        i1 = this.w;
        if ((paramInt3 != -1) && (localLayoutParams.width != i1))
        {
          this.y = i1;
          localLayoutParams.width = i1;
          paramBoolean = bool;
        }
        if (this.z >= 0)
          break label277;
        i2 = this.z;
        label121: if ((paramInt4 != -1) && (localLayoutParams.height != i2))
        {
          this.B = i2;
          localLayoutParams.height = i2;
          paramBoolean = bool;
        }
        if (localLayoutParams.x != paramInt1)
        {
          localLayoutParams.x = paramInt1;
          paramBoolean = bool;
        }
        if (localLayoutParams.y != paramInt2)
        {
          localLayoutParams.y = paramInt2;
          paramBoolean = bool;
        }
        int i3 = u();
        if (i3 != localLayoutParams.windowAnimations)
        {
          localLayoutParams.windowAnimations = i3;
          paramBoolean = bool;
        }
        int i4 = g(localLayoutParams.flags);
        if (i4 == localLayoutParams.flags)
          break label286;
        localLayoutParams.flags = i4;
      }
      while (true)
      {
        if (!bool)
          break label291;
        this.e.updateViewLayout(this.i, localLayoutParams);
        return;
        i1 = this.y;
        break;
        i2 = this.B;
        break label121;
        bool = paramBoolean;
      }
    }
  }

  public void a(Drawable paramDrawable)
  {
    this.H = paramDrawable;
  }

  public void a(IBinder paramIBinder, int paramInt1, int paramInt2, int paramInt3)
  {
    if ((p()) || (this.h == null))
      return;
    v();
    this.f = true;
    this.g = false;
    WindowManager.LayoutParams localLayoutParams = a(paramIBinder);
    localLayoutParams.windowAnimations = u();
    a(localLayoutParams);
    if (paramInt1 == 0)
      paramInt1 = 51;
    localLayoutParams.gravity = paramInt1;
    localLayoutParams.x = paramInt2;
    localLayoutParams.y = paramInt3;
    if (this.z < 0)
    {
      int i2 = this.z;
      this.B = i2;
      localLayoutParams.height = i2;
    }
    if (this.w < 0)
    {
      int i1 = this.w;
      this.y = i1;
      localLayoutParams.width = i1;
    }
    b(localLayoutParams);
  }

  public void a(View.OnTouchListener paramOnTouchListener)
  {
    this.v = paramOnTouchListener;
  }

  public void a(View paramView)
  {
    if (p());
    do
    {
      return;
      this.h = paramView;
      if ((this.d == null) && (this.h != null))
        this.d = this.h.getContext();
    }
    while ((this.e != null) || (this.h == null));
    this.e = ((WindowManager)this.d.getSystemService("window"));
  }

  public void a(View paramView, int paramInt1, int paramInt2)
  {
    if ((p()) || (this.h == null))
      return;
    c(paramView, paramInt1, paramInt2);
    this.f = true;
    this.g = true;
    WindowManager.LayoutParams localLayoutParams = a(paramView.getWindowToken());
    a(localLayoutParams);
    k(a(paramView, localLayoutParams, paramInt1, paramInt2));
    if (this.z < 0)
    {
      int i2 = this.z;
      this.B = i2;
      localLayoutParams.height = i2;
    }
    if (this.w < 0)
    {
      int i1 = this.w;
      this.y = i1;
      localLayoutParams.width = i1;
    }
    localLayoutParams.windowAnimations = u();
    b(localLayoutParams);
  }

  public void a(View paramView, int paramInt1, int paramInt2, int paramInt3)
  {
    a(paramView.getWindowToken(), paramInt1, paramInt2, paramInt3);
  }

  public void a(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    a(paramView, true, paramInt1, paramInt2, true, paramInt3, paramInt4);
  }

  public void a(c paramc)
  {
    this.M = paramc;
  }

  public void a(boolean paramBoolean)
  {
    this.j = paramBoolean;
  }

  public int b()
  {
    return this.O;
  }

  public void b(int paramInt)
  {
    this.k = paramInt;
  }

  public void b(int paramInt1, int paramInt2)
  {
    WindowManager.LayoutParams localLayoutParams = (WindowManager.LayoutParams)this.i.getLayoutParams();
    a(localLayoutParams.x, localLayoutParams.y, paramInt1, paramInt2, false);
  }

  public void b(View paramView)
  {
    a(paramView, 0, 0);
  }

  public void b(View paramView, int paramInt1, int paramInt2)
  {
    a(paramView, false, 0, 0, true, paramInt1, paramInt2);
  }

  public void b(boolean paramBoolean)
  {
    this.m = paramBoolean;
  }

  public int c(View paramView)
  {
    return a(paramView, 0);
  }

  public void c()
  {
    this.N = true;
  }

  public void c(int paramInt)
  {
    this.l = paramInt;
  }

  public void c(boolean paramBoolean)
  {
    this.n = paramBoolean;
  }

  public View d()
  {
    return this.h;
  }

  public void d(int paramInt)
  {
    this.L = paramInt;
  }

  public void d(boolean paramBoolean)
  {
    this.o = paramBoolean;
  }

  public void e(int paramInt)
  {
    this.A = paramInt;
  }

  public void e(boolean paramBoolean)
  {
    this.r = paramBoolean;
    if (paramBoolean);
    for (boolean bool = false; ; bool = true)
    {
      d(bool);
      return;
    }
  }

  public boolean e()
  {
    return this.j;
  }

  public int f()
  {
    return this.k;
  }

  public void f(int paramInt)
  {
    this.x = paramInt;
  }

  void f(boolean paramBoolean)
  {
    this.s = paramBoolean;
  }

  public int g()
  {
    return this.l;
  }

  public void g(boolean paramBoolean)
  {
    if (paramBoolean);
    for (int i1 = 1; ; i1 = 0)
    {
      this.p = i1;
      return;
    }
  }

  public void h(boolean paramBoolean)
  {
    this.q = paramBoolean;
  }

  public boolean h()
  {
    return this.m;
  }

  public void i(boolean paramBoolean)
  {
    this.t = paramBoolean;
  }

  public boolean i()
  {
    return this.n;
  }

  public void j(boolean paramBoolean)
  {
    if (paramBoolean);
    for (boolean bool = false; ; bool = true)
    {
      this.u = bool;
      return;
    }
  }

  public boolean j()
  {
    return this.o;
  }

  public boolean k()
  {
    return this.p == 1;
  }

  public boolean l()
  {
    return this.q;
  }

  public int m()
  {
    return this.L;
  }

  public int n()
  {
    return this.A;
  }

  public int o()
  {
    return this.x;
  }

  public boolean p()
  {
    return this.f;
  }

  public boolean q()
  {
    return this.K;
  }

  public void r()
  {
    if ((p()) && (this.i != null))
    {
      this.f = false;
      v();
    }
    try
    {
      this.e.removeView(this.i);
      return;
    }
    finally
    {
      if ((this.i != this.h) && ((this.i instanceof ViewGroup)))
        ((ViewGroup)this.i).removeView(this.h);
      this.i = null;
      if (this.M != null)
        this.M.a();
    }
  }

  public void s()
  {
    int i1 = 1;
    if ((!p()) || (this.h == null));
    while (true)
    {
      return;
      WindowManager.LayoutParams localLayoutParams = (WindowManager.LayoutParams)this.i.getLayoutParams();
      int i2 = u();
      int i3 = localLayoutParams.windowAnimations;
      int i4 = 0;
      if (i2 != i3)
      {
        localLayoutParams.windowAnimations = i2;
        i4 = i1;
      }
      int i5 = g(localLayoutParams.flags);
      if (i5 != localLayoutParams.flags)
        localLayoutParams.flags = i5;
      while (i1 != 0)
      {
        this.e.updateViewLayout(this.i, localLayoutParams);
        return;
        i1 = i4;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     android.widget.MyPopupWindow
 * JD-Core Version:    0.6.2
 */