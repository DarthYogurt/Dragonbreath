package com.flipdog.editor.a;

import android.text.Editable;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.BulletSpan;
import com.flipdog.commons.a.as;
import com.flipdog.commons.c.c;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.editor.ag;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class h
{
  protected static final int a = 33;
  public final TextWatcher b = new e(this);
  public final TextWatcher c = new d(this);
  private boolean d;
  private f e;
  private f f;
  private com.flipdog.editor.f g;
  private a h;
  private com.flipdog.editor.b.e i;
  private boolean j;

  public h(com.flipdog.editor.b.e parame, f paramf1, f paramf2)
  {
    this.i = parame;
    this.e = paramf1;
    this.f = paramf2;
  }

  private i a(Editable paramEditable, i parami)
  {
    return a(paramEditable, parami.a, parami.b - parami.a, parami.c.length());
  }

  private i a(Spannable paramSpannable, int paramInt1, int paramInt2, int paramInt3)
  {
    i locali = new i();
    locali.a = paramInt1;
    locali.b = (paramInt1 + paramInt3);
    locali.c = paramSpannable.subSequence(paramInt1, paramInt1 + paramInt2);
    a((Spannable)locali.c);
    return locali;
  }

  private i a(List<i> paramList)
  {
    i locali;
    if (paramList.size() == 0)
      locali = (i)as.e(paramList);
    while (true)
    {
      return locali;
      locali = (i)as.e(paramList);
      for (int k = 1; k < paramList.size(); k++)
        locali.a((i)paramList.get(k));
    }
  }

  private List<i> a(i parami)
  {
    if (parami.d == null)
      return as.c(new i[] { parami });
    List localList = as.b();
    localList.add(parami);
    Iterator localIterator = parami.d.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localList;
      localList.add((i)localIterator.next());
    }
  }

  private void a(Editable paramEditable)
  {
    Iterator localIterator = c.a(paramEditable, new Class[] { BulletSpan.class }).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      com.flipdog.commons.c.e locale = (com.flipdog.commons.c.e)localIterator.next();
      if (locale.b == locale.c)
        paramEditable.removeSpan(locale.a);
    }
  }

  private void a(Spannable paramSpannable)
  {
    if (!g())
      return;
    c.b(paramSpannable);
  }

  private void a(f paramf1, f paramf2)
  {
    this.d = true;
    this.g.a(true);
    while (true)
    {
      Editable localEditable;
      List localList2;
      Iterator localIterator2;
      Iterator localIterator3;
      try
      {
        boolean bool = paramf1.b();
        if (!bool)
          return;
        i locali1 = paramf1.c();
        localEditable = h();
        List localList1 = a(locali1);
        Collections.reverse(localList1);
        localList2 = as.b();
        List localList3 = ag.a(localEditable);
        Iterator localIterator1 = localList1.iterator();
        if (!localIterator1.hasNext())
        {
          i locali3 = a(localList2);
          locali3.e = localList3;
          paramf2.a(locali3);
          if (locali1.e != null)
          {
            localIterator2 = c.a(localEditable, ag.a).iterator();
            if (localIterator2.hasNext())
              break label342;
            localIterator3 = locali1.e.iterator();
            if (localIterator3.hasNext())
              break label365;
          }
          a(localEditable);
          paramf1.d();
          return;
        }
        i locali2 = (i)localIterator1.next();
        if (locali1.c != null)
        {
          localList2.add(a(localEditable, locali2));
          Object[] arrayOfObject = new Object[2];
          arrayOfObject[0] = Integer.valueOf(locali2.a);
          arrayOfObject[1] = Integer.valueOf(locali2.b);
          a("undo.start = %s, undo.end = %s", arrayOfObject);
          localEditable.replace(locali2.a, locali2.b, locali2.c);
          a(localEditable);
          continue;
        }
      }
      finally
      {
        this.d = false;
        this.g.a(false);
      }
      localList2.add(new i());
      continue;
      label342: localEditable.removeSpan(((com.flipdog.commons.c.e)localIterator2.next()).a);
      continue;
      label365: com.flipdog.commons.c.e locale = (com.flipdog.commons.c.e)localIterator3.next();
      localEditable.setSpan(locale.a, locale.b, locale.c, locale.d);
    }
  }

  private void a(String paramString, Object[] paramArrayOfObject)
  {
    if (!g())
      return;
    Track.it(String.format(paramString, paramArrayOfObject), new String[] { "Editor" });
  }

  private Object[] a(Spanned paramSpanned)
  {
    return paramSpanned.getSpans(0, paramSpanned.length(), Object.class);
  }

  private boolean g()
  {
    return Track.isEnabled("Editor");
  }

  private Editable h()
  {
    return this.i.b();
  }

  private Spannable i()
  {
    return h();
  }

  public void a()
  {
    if (this.j)
      return;
    a(this.e, this.f);
  }

  public void a(com.flipdog.editor.f paramf)
  {
    this.g = paramf;
    this.g.a(false);
  }

  public void a(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.j);
    while ((this.d) || (this.h == null))
      return;
    i locali = a((Spannable)paramCharSequence, paramInt1, paramInt2, paramInt3);
    if (this.h.a == null)
    {
      this.h.a = locali;
      return;
    }
    this.h.a.a(locali);
  }

  public void b()
  {
    if (this.j)
      return;
    a(this.f, this.e);
  }

  public void c()
  {
    if (this.j)
      return;
    if (this.h == null)
    {
      this.h = new a(null);
      this.h.b = ag.a(i());
      this.h.c = 0;
    }
    a locala = this.h;
    locala.c = (1 + locala.c);
  }

  public void d()
  {
    if (this.j);
    do
    {
      return;
      a locala = this.h;
      locala.c = (-1 + locala.c);
    }
    while (this.h.c > 0);
    i locali = this.h.a;
    if (locali == null)
      locali = new i();
    locali.e = this.h.b;
    this.e.a(locali);
    this.f.a();
    this.h = null;
  }

  public void e()
  {
    this.j = true;
  }

  public void f()
  {
    this.j = false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.a.h
 * JD-Core Version:    0.6.2
 */