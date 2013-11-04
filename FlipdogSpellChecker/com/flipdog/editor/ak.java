package com.flipdog.editor;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Checkable;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.a;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.editor.b.d;
import com.flipdog.editor.spans.MyBackgroundColorSpan;
import com.flipdog.editor.spans.i;
import com.flipdog.editor.spans.j;
import com.flipdog.editor.spans.k;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ak
  implements View.OnClickListener, an
{
  protected static final int a = 50;
  protected static final int b = 33;
  private static final int c = 20480;
  private d A;
  private com.flipdog.editor.b.e B;
  private int C = -1;
  private w d = new w();
  private Context e;
  private Boolean f;
  private aa g;
  private String h;
  private Boolean i;
  private Boolean j;
  private Integer k;
  private Integer l;
  private Boolean m;
  private Boolean n;
  private com.flipdog.editor.a.h o;
  private MyEditText p;
  private av q;
  private av r;
  private av s;
  private aj t;
  private aj u;
  private com.b.b.h v = new com.b.b.h();
  private com.b.h w;
  private MyActivity x;
  private Activity y;
  private f z;

  private boolean A()
  {
    return b(B(), C());
  }

  private int B()
  {
    return Math.min(this.p.getSelectionStart(), this.p.getSelectionEnd());
  }

  private int C()
  {
    return Math.max(this.p.getSelectionStart(), this.p.getSelectionEnd());
  }

  private boolean D()
  {
    return Track.isEnabled("Editor");
  }

  private void E()
  {
    this.o.a();
  }

  private void F()
  {
    this.o.b();
  }

  private void G()
  {
    b(this.d.f);
    d();
  }

  private void H()
  {
    b(this.d.g);
    e();
  }

  private void I()
  {
    b(this.d.h);
    f();
  }

  private void a(Context paramContext, com.b.h paramh, MyEditText paramMyEditText, w paramw)
  {
    this.e = paramContext;
    this.w = paramh;
    this.p = paramMyEditText;
    this.z = f.a(this.p, 50);
    this.B = this.z.e();
    this.A = this.z.d();
    this.o = this.z.c();
    this.d = paramw;
    n();
    o();
    if (this.d.a != null)
      aw.a(this.d.b, this.d.c);
    a(this.d.l, 8);
    a(this.d.n, 0);
    this.t = g.a;
    this.u = g.b;
    this.q = new av();
    this.r = new av();
    this.s = new av();
  }

  private static <T> void a(Context paramContext, String paramString, List<T> paramList, com.b.e<T, String> parame, al<T> paramal)
  {
    String[] arrayOfString = (String[])com.flipdog.commons.a.as.a(com.flipdog.commons.a.as.c(paramList, parame), String.class);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(paramString);
    localBuilder.setItems(arrayOfString, new at(paramal, paramList));
    localBuilder.setNegativeButton(17039360, new as());
    localBuilder.show();
  }

  private void a(Uri paramUri)
  {
    if (paramUri == null);
    String str;
    do
    {
      return;
      str = paramUri.toString();
    }
    while (ax.a(str));
    com.b.f localf = new com.b.f();
    localf.a(str);
    if (!localf.a())
    {
      com.flipdog.commons.a.e.a("Invalid image.");
      return;
    }
    a("image", str);
  }

  private void a(Spannable paramSpannable)
  {
    if (!D())
      return;
    com.flipdog.commons.c.c.b(paramSpannable);
  }

  private void a(Spannable paramSpannable, int paramInt1, int paramInt2, aa paramaa)
  {
    j.a(paramSpannable, paramInt1, paramInt2, new Class[] { AbsoluteSizeSpan.class });
    paramSpannable.setSpan(new AbsoluteSizeSpan((int)com.flipdog.commons.a.h.c(paramaa.a)), paramInt1, paramInt2, 33);
  }

  private void a(Spannable paramSpannable, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    k[] arrayOfk = new k[2];
    arrayOfk[0] = i.g;
    arrayOfk[1] = i.h;
    j.b(paramSpannable, paramInt1, paramInt2, arrayOfk);
    if (paramBoolean)
      paramSpannable.setSpan(new SubscriptSpan(), paramInt1, paramInt2, 33);
  }

  private void a(Spannable paramSpannable, Object paramObject, int paramInt)
  {
    paramSpannable.setSpan(paramObject, paramSpannable.getSpanStart(paramObject), paramSpannable.getSpanEnd(paramObject), paramInt);
  }

  private void a(View paramView, int paramInt)
  {
    paramView.setVisibility(paramInt);
  }

  private void a(View paramView, View.OnClickListener paramOnClickListener)
  {
    paramView.setOnClickListener(paramOnClickListener);
  }

  private void a(View paramView, boolean paramBoolean)
  {
    ((Checkable)paramView).setChecked(paramBoolean);
  }

  private void a(Checkable paramCheckable, Spannable paramSpannable, int paramInt1, int paramInt2, k paramk)
  {
    if (paramInt1 == paramInt2);
    for (boolean bool = a(paramk); ; bool = j.a(paramSpannable, paramInt1, paramInt2, paramk))
    {
      paramCheckable.setChecked(bool);
      return;
    }
  }

  private void a(MyHorizontalScrollView paramMyHorizontalScrollView, View paramView1, View paramView2)
  {
    aw.a(paramMyHorizontalScrollView, paramView1, paramView2);
  }

  private void a(String paramString, int paramInt)
  {
    a(paramString, com.flipdog.commons.a.ar.a(q(), paramInt).toString());
  }

  private void a(String paramString1, String paramString2)
  {
    int i1 = B();
    int i2 = C();
    Editable localEditable = this.p.getText();
    String str = String.format("{{image}}%s{{/image}}", new Object[] { paramString2 });
    if (b(i1, i2))
      str = " " + str;
    localEditable.replace(i1, i2, str);
    this.p.setSelection(i1 + str.length());
  }

  private void a(String paramString, Object[] paramArrayOfObject)
  {
    if (!D())
      return;
    Track.it(String.format(paramString, paramArrayOfObject), new String[] { "Editor" });
  }

  private boolean a(KeyEvent paramKeyEvent)
  {
    return (0x5000 & paramKeyEvent.getMetaState()) != 0;
  }

  private boolean a(View paramView)
  {
    return ((Checkable)paramView).isChecked();
  }

  private void b(Spannable paramSpannable, int paramInt1, int paramInt2, k paramk, boolean paramBoolean)
  {
    List localList = j.a(paramSpannable, paramInt1, paramInt2, new k[] { paramk });
    if (paramBoolean)
    {
      paramSpannable.setSpan(paramk.a(), paramInt1, paramInt2, 33);
      return;
    }
    j.b(paramSpannable, localList, paramInt1, paramInt2);
  }

  private void b(Spannable paramSpannable, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    k[] arrayOfk = new k[2];
    arrayOfk[0] = i.g;
    arrayOfk[1] = i.h;
    j.b(paramSpannable, paramInt1, paramInt2, arrayOfk);
    if (paramBoolean)
      paramSpannable.setSpan(new SuperscriptSpan(), paramInt1, paramInt2, 33);
  }

  private void b(View paramView)
  {
    ((Checkable)paramView).toggle();
  }

  private boolean b(int paramInt)
  {
    if (this.p.a() == 16908322);
    while (paramInt > 1)
      return true;
    return false;
  }

  private boolean b(int paramInt1, int paramInt2)
  {
    return paramInt1 == paramInt2;
  }

  private void n()
  {
    this.w.a(this.v, new q(this));
    this.w.a(this.v, new p(this));
  }

  private void o()
  {
    if (this.d.a != null)
      this.d.a.setOnScrollChanged(this);
    a(this.d.f, this);
    a(this.d.g, this);
    a(this.d.h, this);
    a(this.d.n, this);
    a(this.d.p, this);
    a(this.d.q, this);
    a(this.d.i, this);
    a(this.d.r, this);
    a(this.d.j, this);
    a(this.d.k, this);
    a(this.d.l, this);
    a(this.d.m, this);
    a(this.d.n, this);
    a(this.d.o, this);
    if (this.d.d != null)
      this.d.d.setOnClickListener(this);
    if (this.d.e != null)
      this.d.e.setOnClickListener(this);
    this.p.setOnKeyListener(new m(this));
    this.p.a(new l(this));
    o localo = new o(this);
    this.B.a(this.A);
    this.B.a(this.o.b);
    this.B.a(this.A);
    this.B.a(localo);
    this.B.a(new ao());
    this.B.a(new ab());
    this.B.a(this.o.c);
  }

  private void p()
  {
    ai localai = ai.a(this.p);
    x localx = new x(s());
    am localam = new am(s(), localx);
    localam.setOnDismissListener(new n(this, localai, localam));
    a.a(localam);
    localam.show();
  }

  private Resources q()
  {
    return this.e.getResources();
  }

  private void r()
  {
    ai localai = ai.a(this.p);
    ad localad = new ad(s(), this.s);
    y localy = new y(s(), com.flipdog.p.editor_font_size_grid, localad);
    localy.a(this.s);
    localy.setOnDismissListener(new s(this, localai, localy));
    a.a(localy);
    localy.show();
  }

  private Context s()
  {
    return this.e;
  }

  private void t()
  {
    Spannable localSpannable = y();
    int i1;
    if (A())
      i1 = 0;
    List localList;
    for (int i2 = localSpannable.length(); ; i2 = C())
    {
      localList = j.a(localSpannable, i1, i2, ag.a);
      if (localList.size() != 0)
        break;
      return;
      i1 = B();
    }
    this.o.c();
    try
    {
      Iterator localIterator = localList.iterator();
      while (true)
      {
        boolean bool = localIterator.hasNext();
        if (!bool)
          return;
        localSpannable.removeSpan(localIterator.next());
      }
    }
    finally
    {
      this.o.d();
    }
  }

  private void u()
  {
    ai localai = ai.a(this.p);
    aq localaq = new aq(s(), this.u);
    y localy = new y(s(), com.flipdog.p.editor_colors_grid, localaq);
    localy.setOnDismissListener(new t(this, localai, localy));
    a.a(localy);
    localy.show();
  }

  private void v()
  {
    this.A.f();
  }

  private void w()
  {
    String str = com.flipdog.filebrowser.k.c.a(com.flipdog.s.typeface);
    List localList = com.flipdog.commons.a.as.b(new String[] { "Normal", "Serif", "Monospace" });
    u localu = new u(this);
    au localau = new au(this);
    a(this.e, str, localList, localu, localau);
  }

  private void x()
  {
    ar localar = new ar(this, ai.a(this.p));
    v.a(s(), this.t, this.q, localar);
  }

  private Spannable y()
  {
    return this.p.getText();
  }

  private int z()
  {
    return this.p.getSelectionStart();
  }

  public w a()
  {
    return this.d;
  }

  protected List<Object> a(Spanned paramSpanned, int paramInt, Class<?>[] paramArrayOfClass)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = j.a(paramSpanned, paramInt, paramInt, paramArrayOfClass).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      Object localObject = localIterator.next();
      if (paramSpanned.getSpanEnd(localObject) == paramInt)
        localArrayList.add(localObject);
    }
  }

  protected <T> List<T> a(T[] paramArrayOfT)
  {
    return Arrays.asList(paramArrayOfT);
  }

  public void a(int paramInt)
  {
    this.C = paramInt;
  }

  public void a(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == 0);
    while (true)
    {
      return;
      if (paramInt1 == 4097)
      {
        Iterator localIterator = com.flipdog.f.b(paramInt2, paramIntent).iterator();
        while (localIterator.hasNext())
          a((Uri)localIterator.next());
      }
    }
  }

  public void a(int paramInt, KeyEvent paramKeyEvent)
  {
    if (a(paramKeyEvent))
    {
      if (paramInt != 30)
        break label19;
      G();
    }
    label19: 
    do
    {
      return;
      if (paramInt == 37)
      {
        H();
        return;
      }
      if (paramInt == 49)
      {
        I();
        return;
      }
      if (paramInt == 54)
      {
        E();
        return;
      }
    }
    while (paramInt != 53);
    F();
  }

  public void a(Dialog paramDialog, Activity paramActivity, com.b.h paramh, MyEditText paramMyEditText, w paramw)
  {
    this.y = paramActivity;
    a(paramDialog.getContext(), paramh, paramMyEditText, paramw);
  }

  protected void a(Spannable paramSpannable, int paramInt1, int paramInt2)
  {
    Object[] arrayOfObject = paramSpannable.getSpans(paramInt1, paramInt2, Object.class);
    int i1 = arrayOfObject.length;
    int i2 = 0;
    if (i2 >= i1)
      return;
    Object localObject = arrayOfObject[i2];
    if (!a(localObject));
    while (true)
    {
      i2++;
      break;
      a(paramSpannable, localObject, 33);
    }
  }

  protected void a(Spannable paramSpannable, int paramInt1, int paramInt2, int paramInt3)
  {
    k[] arrayOfk = new k[1];
    arrayOfk[0] = i.e;
    j.b(paramSpannable, paramInt1, paramInt2, arrayOfk);
    paramSpannable.setSpan(new MyBackgroundColorSpan(paramInt3), paramInt1, paramInt2, 33);
  }

  protected void a(Spannable paramSpannable, int paramInt1, int paramInt2, k paramk)
  {
    paramSpannable.setSpan(paramk.a(), paramInt1, paramInt2, 33);
  }

  public void a(Spannable paramSpannable, int paramInt1, int paramInt2, k paramk, boolean paramBoolean)
  {
    this.o.c();
    try
    {
      b(paramSpannable, paramInt1, paramInt2, paramk, paramBoolean);
      return;
    }
    finally
    {
      this.o.d();
    }
  }

  protected void a(Spannable paramSpannable, int paramInt1, int paramInt2, String paramString)
  {
    k[] arrayOfk = new k[1];
    arrayOfk[0] = i.f;
    j.b(paramSpannable, paramInt1, paramInt2, arrayOfk);
    paramSpannable.setSpan(new TypefaceSpan(paramString), paramInt1, paramInt2, 33);
  }

  public void a(View paramView, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (paramView == this.d.a)
      a(this.d.a, this.d.b, this.d.c);
  }

  public void a(MyActivity paramMyActivity, MyEditText paramMyEditText, w paramw)
  {
    this.x = paramMyActivity;
    a(paramMyActivity, paramMyActivity.a(), paramMyEditText, paramw);
  }

  protected void a(aa paramaa)
  {
    if (paramaa == null)
      return;
    if (A())
    {
      this.g = paramaa;
      return;
    }
    this.o.c();
    try
    {
      a(y(), B(), C(), paramaa);
      return;
    }
    finally
    {
      this.o.d();
    }
  }

  protected void a(ae paramae)
  {
    if (paramae == null)
      return;
    a(paramae.a, paramae.c);
  }

  protected void a(Boolean paramBoolean, Spannable paramSpannable, int paramInt1, int paramInt2, k paramk)
  {
    if (paramBoolean != null)
    {
      if (paramBoolean.booleanValue())
        a(paramSpannable, paramInt1, paramInt2, paramk);
    }
    else
      return;
    j.b(paramSpannable, paramInt1, paramInt2, new k[] { paramk });
  }

  protected void a(Integer paramInteger)
  {
  }

  protected void a(String paramString)
  {
    if (paramString == null)
      return;
    if (A())
    {
      this.h = paramString;
      return;
    }
    this.o.c();
    try
    {
      a(y(), B(), C(), paramString);
      return;
    }
    finally
    {
      this.o.d();
    }
  }

  protected void a(boolean paramBoolean)
  {
    if (this.d.a != null)
      a(this.d.a, this.d.b, this.d.c);
  }

  protected void a(String[] paramArrayOfString)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(B());
    arrayOfObject[1] = Integer.valueOf(C());
    a("Selection: %s, %s", arrayOfObject);
  }

  protected boolean a(int paramInt1, int paramInt2)
  {
    if (b(paramInt2));
    while ((this.p.a() == 16908320) || ((paramInt1 == 1) && (paramInt2 == 0)))
      return true;
    return false;
  }

  protected boolean a(k paramk)
  {
    return j.a(y(), z(), paramk);
  }

  protected boolean a(Object paramObject)
  {
    return (paramObject instanceof StyleSpan);
  }

  protected List<Object> b(Spannable paramSpannable, int paramInt1, int paramInt2)
  {
    ArrayList localArrayList = new ArrayList();
    Object[] arrayOfObject = paramSpannable.getSpans(paramInt1, paramInt2, Object.class);
    int i1 = arrayOfObject.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return localArrayList;
      Object localObject = arrayOfObject[i2];
      int i3 = paramSpannable.getSpanStart(localObject);
      int i4 = paramSpannable.getSpanEnd(localObject);
      if ((i3 < paramInt1) || (i4 > paramInt2))
        localArrayList.add(localObject);
    }
  }

  public void b()
  {
    this.o.a();
  }

  protected void b(Spannable paramSpannable, int paramInt1, int paramInt2, int paramInt3)
  {
    k[] arrayOfk = new k[1];
    arrayOfk[0] = i.d;
    j.b(paramSpannable, paramInt1, paramInt2, arrayOfk);
    if (paramInt3 == -16777216)
      return;
    paramSpannable.setSpan(new ForegroundColorSpan(paramInt3), paramInt1, paramInt2, 33);
  }

  protected void b(Integer paramInteger)
  {
    if (paramInteger == null)
      return;
    if (A())
    {
      this.l = paramInteger;
      return;
    }
    this.o.c();
    try
    {
      b(y(), B(), C(), paramInteger.intValue());
      return;
    }
    finally
    {
      this.o.d();
    }
  }

  public void c()
  {
    this.o.b();
  }

  protected void d()
  {
    boolean bool = a(this.d.f);
    if (A())
    {
      this.f = Boolean.valueOf(bool);
      return;
    }
    a(y(), B(), C(), i.a, bool);
  }

  protected void e()
  {
    boolean bool = a(this.d.g);
    if (A())
    {
      this.m = Boolean.valueOf(bool);
      return;
    }
    a(y(), B(), C(), i.b, bool);
  }

  protected void f()
  {
    boolean bool = a(this.d.h);
    if (A())
    {
      this.n = Boolean.valueOf(bool);
      return;
    }
    a(y(), B(), C(), i.c, bool);
  }

  protected void g()
  {
    if (this.x != null);
    for (Object localObject = this.x; ; localObject = this.y)
    {
      new com.flipdog.f((Activity)localObject).d(4097);
      return;
    }
  }

  protected void h()
  {
    if (a(this.d.p))
      a(this.d.p, false);
    if (A())
    {
      this.j = Boolean.valueOf(a(this.d.q));
      this.i = Boolean.valueOf(a(this.d.p));
      return;
    }
    this.o.c();
    try
    {
      a(y(), B(), C(), a(this.d.q));
      return;
    }
    finally
    {
      this.o.d();
    }
  }

  protected void i()
  {
    if (a(this.d.q))
      a(this.d.q, false);
    if (A())
    {
      this.j = Boolean.valueOf(a(this.d.q));
      this.i = Boolean.valueOf(a(this.d.p));
      return;
    }
    this.o.c();
    try
    {
      b(y(), B(), C(), a(this.d.p));
      return;
    }
    finally
    {
      this.o.d();
    }
  }

  protected void j()
  {
    a(this.p.getText());
    Spannable localSpannable = y();
    int i1 = B();
    int i2 = C();
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(i1);
    arrayOfObject[1] = Integer.valueOf(i2);
    a("handleOnSelectionChanged, start = %s, end = %s", arrayOfObject);
    a((Checkable)this.d.f, localSpannable, i1, i2, i.a);
    a((Checkable)this.d.g, localSpannable, i1, i2, i.b);
    a((Checkable)this.d.h, localSpannable, i1, i2, i.c);
    a((Checkable)this.d.q, localSpannable, i1, i2, i.g);
    a((Checkable)this.d.p, localSpannable, i1, i2, i.h);
    this.f = null;
    this.m = null;
    this.n = null;
    this.k = null;
    this.l = null;
    this.g = null;
    this.h = null;
    this.i = null;
    this.j = null;
  }

  protected Spannable k()
  {
    return this.p.getText();
  }

  public void l()
  {
    this.o.e();
  }

  public void m()
  {
    this.o.f();
  }

  public void onClick(View paramView)
  {
    if (paramView == this.d.m)
      p();
    do
    {
      return;
      if (paramView == this.d.n)
      {
        g();
        return;
      }
      if (paramView == this.d.f)
      {
        d();
        return;
      }
      if (paramView == this.d.g)
      {
        e();
        return;
      }
      if (paramView == this.d.h)
      {
        f();
        return;
      }
      if (paramView == this.d.k)
      {
        x();
        return;
      }
      if (paramView == this.d.i)
      {
        w();
        return;
      }
      if (paramView == this.d.r)
      {
        v();
        return;
      }
      if (paramView == this.d.p)
      {
        i();
        return;
      }
      if (paramView == this.d.q)
      {
        h();
        return;
      }
      if (paramView == this.d.l)
      {
        u();
        return;
      }
      if (paramView == this.d.o)
      {
        t();
        return;
      }
      if (paramView == this.d.j)
      {
        r();
        return;
      }
      if (paramView == this.d.d)
      {
        b();
        return;
      }
    }
    while (paramView != this.d.e);
    c();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.ak
 * JD-Core Version:    0.6.2
 */