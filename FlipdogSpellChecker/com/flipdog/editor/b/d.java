package com.flipdog.editor.b;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.text.TextWatcher;
import android.text.style.BulletSpan;
import com.flipdog.commons.a.as;
import com.flipdog.commons.a.w;
import com.flipdog.editor.ag;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class d
  implements TextWatcher, com.flipdog.commons.s.a
{
  private static int a = 18;
  private List<Integer> b = as.b();
  private boolean c;
  private boolean d;
  private boolean e;
  private e f;
  private com.flipdog.editor.f g;
  private com.b.b.h h = new com.b.b.h();

  public d(e parame)
  {
    this.f = parame;
  }

  private static int a(CharSequence paramCharSequence, char paramChar, int paramInt)
  {
    return a(paramCharSequence, paramChar, paramInt, paramCharSequence.length());
  }

  private static int a(CharSequence paramCharSequence, char paramChar, int paramInt1, int paramInt2)
  {
    int i = Math.min(paramInt2, paramCharSequence.length());
    for (int j = paramInt1; ; j++)
    {
      if (j >= i)
        j = -1;
      while (paramCharSequence.charAt(j) == paramChar)
        return j;
    }
  }

  private static BulletSpan a(Spannable paramSpannable, int paramInt)
  {
    if (b(paramSpannable, paramInt))
      return null;
    BulletSpan[] arrayOfBulletSpan = (BulletSpan[])paramSpannable.getSpans(paramInt, paramInt, BulletSpan.class);
    int i = arrayOfBulletSpan.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return null;
      BulletSpan localBulletSpan = arrayOfBulletSpan[j];
      if (paramSpannable.getSpanStart(localBulletSpan) == paramInt)
        return localBulletSpan;
    }
  }

  private Character a(int paramInt)
  {
    return b(paramInt - 1);
  }

  private <T> T a(int paramInt1, int paramInt2, Class<T> paramClass)
  {
    Spannable localSpannable = m();
    Object[] arrayOfObject = localSpannable.getSpans(paramInt1, paramInt2, paramClass);
    int i = arrayOfObject.length;
    for (int j = 0; ; j++)
    {
      Object localObject;
      if (j >= i)
        localObject = null;
      int k;
      int m;
      do
      {
        return localObject;
        localObject = arrayOfObject[j];
        k = localSpannable.getSpanStart(localObject);
        m = localSpannable.getSpanEnd(localObject);
      }
      while ((k == paramInt1) && (m == paramInt2));
    }
  }

  private static List<f> a(Spannable paramSpannable)
  {
    List localList1 = as.b();
    localList1.add(Integer.valueOf(-1));
    int i = a(paramSpannable, '\n', 0);
    List localList2;
    if (i == -1)
    {
      localList1.add(Integer.valueOf(paramSpannable.length()));
      localList2 = as.b();
    }
    for (int j = 0; ; j++)
    {
      if (j >= -1 + localList1.size())
      {
        return localList2;
        localList1.add(Integer.valueOf(i));
        i = a(paramSpannable, '\n', i + 1);
        break;
      }
      f localf = new f(null);
      localf.a = (1 + ((Integer)localList1.get(j)).intValue());
      localf.b = ((Integer)localList1.get(j + 1)).intValue();
      localList2.add(localf);
    }
  }

  private void a(int paramInt1, int paramInt2, CharSequence paramCharSequence)
  {
    this.f.a(paramInt1, paramInt2, paramCharSequence);
  }

  private void a(int paramInt1, int paramInt2, Object paramObject, int paramInt3)
  {
    m().setSpan(paramObject, paramInt1, paramInt2, paramInt3);
  }

  private void a(Spannable paramSpannable, int paramInt1, int paramInt2)
  {
    if (b(paramSpannable, paramInt1));
    while (b(paramSpannable, paramInt2))
      return;
    Selection.setSelection(m(), paramInt1, paramInt2);
  }

  public static void a(e parame)
  {
    int i = 0;
    Editable localEditable = parame.b();
    List localList1 = com.flipdog.commons.c.c.a(localEditable, new Class[] { BulletSpan.class });
    a locala = new a();
    Collections.sort(localList1, locala);
    Iterator localIterator1 = localList1.iterator();
    Iterator localIterator2;
    int m;
    label88: List localList3;
    int i1;
    Iterator localIterator3;
    if (!localIterator1.hasNext())
    {
      List localList2 = com.flipdog.commons.c.c.a(localEditable, new Class[] { BulletSpan.class });
      Collections.sort(localList2, locala);
      localIterator2 = localList2.iterator();
      m = 0;
      if (localIterator2.hasNext())
        break label273;
      localList3 = a(localEditable);
      i1 = 0;
      if (i1 < localList3.size())
        break label329;
      List localList4 = com.flipdog.commons.c.c.a(localEditable, new Class[] { c.class });
      Collections.sort(localList4, locala);
      localIterator3 = localList4.iterator();
    }
    while (true)
    {
      if (!localIterator3.hasNext())
      {
        return;
        com.flipdog.commons.c.e locale = (com.flipdog.commons.c.e)localIterator1.next();
        int j = a(localEditable, '\n', locale.b, locale.c);
        int k = locale.b;
        while (j != -1)
        {
          localEditable.setSpan(new BulletSpan(), k, j, a);
          localEditable.setSpan(locale.a, j + 1, locale.c, a);
          k = j + 1;
          j = a(localEditable, '\n', j + 1, locale.c);
        }
        break;
        label273: int n = m + ((com.flipdog.commons.c.e)localIterator2.next()).b;
        if ((n < localEditable.length()) && (localEditable.charAt(n) == ' '))
          break label88;
        parame.a(n, " ");
        m++;
        break label88;
        label329: f localf1;
        label337: f localf2;
        if (i1 == 0)
        {
          localf1 = null;
          localf2 = (f)localList3.get(i1);
          if (c(localEditable, localf2))
          {
            if (localf1 != null)
              break label396;
            b(localEditable, localf2);
          }
        }
        while (true)
        {
          i1++;
          break;
          localf1 = (f)localList3.get(i1 - 1);
          break label337;
          label396: if (a(localEditable, localf1))
          {
            if (c(localEditable, localf1))
            {
              b(localEditable, localf1);
              b(localEditable, localf2);
            }
          }
          else
            b(localEditable, localf2);
        }
      }
      int i2 = i + ((com.flipdog.commons.c.e)localIterator3.next()).b;
      if (localEditable.charAt(i2) == ' ')
      {
        parame.b(i2, i2 + 1);
        i--;
      }
    }
  }

  private static boolean a(Spannable paramSpannable, f paramf)
  {
    BulletSpan[] arrayOfBulletSpan = (BulletSpan[])paramSpannable.getSpans(paramf.a, paramf.b, BulletSpan.class);
    int i = arrayOfBulletSpan.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return false;
      BulletSpan localBulletSpan = arrayOfBulletSpan[j];
      if ((paramf.a >= paramSpannable.getSpanStart(localBulletSpan)) && (paramf.b <= paramSpannable.getSpanEnd(localBulletSpan)))
        return true;
    }
  }

  private boolean a(Character paramCharacter)
  {
    return a(paramCharacter, ' ');
  }

  private boolean a(Character paramCharacter, char paramChar)
  {
    if (paramCharacter == null);
    while (paramChar != paramCharacter.charValue())
      return false;
    return true;
  }

  private int b(Spannable paramSpannable)
  {
    return c(Selection.getSelectionStart(paramSpannable), Selection.getSelectionEnd(paramSpannable));
  }

  private Character b(int paramInt)
  {
    Spannable localSpannable = m();
    if (c(localSpannable, paramInt))
      return null;
    return Character.valueOf(localSpannable.charAt(paramInt));
  }

  private void b(Spannable paramSpannable, int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
      paramInt1 = 0;
    if (paramInt2 > paramSpannable.length())
      paramInt2 = paramSpannable.length();
    Selection.setSelection(paramSpannable, paramInt1, paramInt2);
  }

  private static void b(Spannable paramSpannable, f paramf)
  {
    BulletSpan localBulletSpan = a(paramSpannable, paramf.a);
    if (localBulletSpan == null)
      return;
    paramSpannable.removeSpan(localBulletSpan);
    paramSpannable.setSpan(new c(null), paramf.a, paramf.b, 33);
  }

  private static boolean b(Spannable paramSpannable, int paramInt)
  {
    if (paramInt < 0);
    while (paramInt > paramSpannable.length())
      return true;
    return false;
  }

  private char c(int paramInt)
  {
    return m().charAt(paramInt);
  }

  private int c(int paramInt1, int paramInt2)
  {
    return Math.min(paramInt1, paramInt2);
  }

  private int c(Spannable paramSpannable)
  {
    return d(Selection.getSelectionStart(paramSpannable), Selection.getSelectionEnd(paramSpannable));
  }

  private boolean c(Spannable paramSpannable, int paramInt)
  {
    if (paramInt < 0);
    while (paramInt >= paramSpannable.length())
      return true;
    return false;
  }

  private static boolean c(Spannable paramSpannable, f paramf)
  {
    if (paramf.a() == 1)
      if (paramSpannable.charAt(paramf.a) == ' ');
    while (paramf.a() != 0)
    {
      return false;
      return a(paramSpannable, paramf);
    }
    return a(paramSpannable, paramf);
  }

  private int d(int paramInt)
  {
    Spannable localSpannable = m();
    for (int i = Math.min(paramInt - 1, -1 + localSpannable.length()); ; i--)
    {
      if (i < 0)
        return 0;
      if (localSpannable.charAt(i) == '\n')
        return i + 1;
    }
  }

  private int d(int paramInt1, int paramInt2)
  {
    return Math.max(paramInt1, paramInt2);
  }

  private int e(int paramInt)
  {
    return ag.a(m(), paramInt);
  }

  private void e(int paramInt1, int paramInt2)
  {
    this.f.b(paramInt1, paramInt2);
  }

  private boolean f(int paramInt)
  {
    if (c(m(), paramInt))
      return false;
    return a(Character.valueOf(c(paramInt)), ' ');
  }

  private void g()
  {
    com.flipdog.editor.a.h localh = this.g.c();
    if (localh == null)
      return;
    localh.c();
  }

  private void h()
  {
    com.flipdog.editor.a.h localh = this.g.c();
    if (localh == null)
      return;
    localh.d();
  }

  private int i()
  {
    return d(n());
  }

  private int j()
  {
    return e(n());
  }

  private void k()
  {
    if (this.e)
    {
      this.e = false;
      a(this.f);
    }
  }

  private boolean l()
  {
    if (this.g == null)
      return false;
    return this.g.a();
  }

  private Spannable m()
  {
    return this.f.b();
  }

  private int n()
  {
    return Selection.getSelectionStart(m());
  }

  private void o()
  {
    this.g.b().a(this.h, new b(this));
  }

  public void a()
  {
    g();
    while (true)
    {
      int i1;
      try
      {
        this.c = true;
        try
        {
          Spannable localSpannable = m();
          int i = b(localSpannable);
          int j = c(localSpannable);
          int k = d(i);
          int m = e(j);
          int n = k;
          if (n > m)
          {
            this.c = false;
            return;
          }
          i1 = e(n);
          if (as.f((BulletSpan[])localSpannable.getSpans(n, n, BulletSpan.class)))
          {
            a(n, i1, new BulletSpan(), a);
            a(n, n, " ");
            i2 = i1 + 1;
            n = i2 + 1;
            continue;
          }
        }
        finally
        {
          this.c = false;
        }
      }
      finally
      {
        h();
      }
      int i2 = i1;
    }
  }

  public void a(int paramInt1, int paramInt2)
  {
    Character localCharacter = b(paramInt2);
    if ((localCharacter == null) || (localCharacter.charValue() != ' '));
    int i;
    int j;
    do
    {
      return;
      i = d(paramInt2);
      j = e(paramInt2);
    }
    while ((paramInt2 != i) || ((BulletSpan)a(i, j, BulletSpan.class) == null));
    if (paramInt1 != paramInt2)
    {
      if (i == paramInt2)
      {
        b(m(), paramInt1, paramInt2 + 1);
        return;
      }
      b(m(), paramInt1 + 1, paramInt2);
      return;
    }
    b(m(), paramInt1 + 1, paramInt2 + 1);
  }

  public void a(com.flipdog.editor.f paramf)
  {
    this.g = paramf;
    o();
  }

  public void afterTextChanged(Editable paramEditable)
  {
    if (this.c);
    while (l())
      return;
    this.c = true;
    while (true)
    {
      int i;
      try
      {
        Iterator localIterator;
        if (this.b.size() != 0)
        {
          List localList = this.b;
          this.b = as.b();
          Collections.sort(localList, w.c);
          localIterator = localList.iterator();
          if (localIterator.hasNext());
        }
        else
        {
          if (this.d)
          {
            this.d = false;
            i = a(paramEditable, '\n', 0);
            break label190;
          }
          k();
          return;
        }
        Integer localInteger = (Integer)localIterator.next();
        e(localInteger.intValue(), 1 + localInteger.intValue());
        continue;
      }
      finally
      {
        this.c = false;
      }
      label190: 
      while (i != -1)
      {
        int j = i + 1;
        if ((a(paramEditable, j) != null) && (!f(j)))
          a(j, j, " ");
        int k = a(paramEditable, '\n', j);
        i = k;
      }
    }
  }

  public void b()
  {
    int i = 0;
    g();
    try
    {
      this.c = true;
      try
      {
        int j = i();
        int k = j();
        Spannable localSpannable = m();
        BulletSpan[] arrayOfBulletSpan = (BulletSpan[])localSpannable.getSpans(j, k, BulletSpan.class);
        int m = arrayOfBulletSpan.length;
        while (true)
        {
          if (i >= m)
          {
            this.c = false;
            return;
          }
          BulletSpan localBulletSpan = arrayOfBulletSpan[i];
          int n = localSpannable.getSpanStart(localBulletSpan);
          int i1 = localSpannable.getSpanEnd(localBulletSpan);
          if ((n == j) && (i1 == k))
          {
            localSpannable.removeSpan(localBulletSpan);
            e(j, j + 1);
          }
          i++;
        }
      }
      finally
      {
        this.c = false;
      }
    }
    finally
    {
      h();
    }
  }

  public boolean b(int paramInt1, int paramInt2)
  {
    if ((paramInt2 == 0) && (paramInt1 == 21))
    {
      Spannable localSpannable = m();
      int i = Selection.getSelectionStart(localSpannable);
      int j = Selection.getSelectionEnd(localSpannable);
      if (a(a(j)))
      {
        int k = d(j);
        if (((BulletSpan)a(k, e(j), BulletSpan.class) != null) && (k == j - 1))
        {
          if (i != j)
            a(m(), i, j - 2);
          while (true)
          {
            return true;
            a(m(), j - 2, j - 2);
          }
        }
      }
    }
    return false;
  }

  public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.c);
    label7: Spannable localSpannable;
    int i;
    int k;
    label161: int i3;
    BulletSpan localBulletSpan3;
    do
    {
      do
      {
        BulletSpan localBulletSpan2;
        do
        {
          int j;
          do
          {
            break label7;
            break label7;
            do
              return;
            while ((l()) || (paramInt2 <= 0));
            localSpannable = m();
            i = d(paramInt1);
            j = d(paramInt1 + paramInt2);
            k = e(paramInt1 + paramInt2);
            if (i != j)
              break;
          }
          while (paramInt1 != j);
          localBulletSpan2 = (BulletSpan)a(j, k, BulletSpan.class);
          if (localBulletSpan2 == null)
            break label161;
          Character localCharacter = b(paramInt1 - 2);
          if ((localCharacter != null) && (localCharacter.charValue() != '\n'))
            break;
        }
        while (paramInt1 - 1 < 0);
        this.b.add(Integer.valueOf(paramInt1 - 1));
        return;
        localSpannable.removeSpan(localBulletSpan2);
        if (paramInt1 - 1 > 0)
          this.b.add(Integer.valueOf(paramInt1 - 1));
        i3 = d(paramInt1 - 1);
      }
      while (i3 == -1);
      localBulletSpan3 = a(localSpannable, i3);
    }
    while (localBulletSpan3 == null);
    localSpannable.setSpan(localBulletSpan3, i3, k, a);
    return;
    BulletSpan[] arrayOfBulletSpan = (BulletSpan[])localSpannable.getSpans(i, k, BulletSpan.class);
    int m = arrayOfBulletSpan.length;
    for (int n = 0; ; n++)
    {
      if (n >= m)
      {
        localSpannable.setSpan(a(localSpannable, i), i, k, a);
        return;
      }
      BulletSpan localBulletSpan1 = arrayOfBulletSpan[n];
      int i1 = localSpannable.getSpanStart(localBulletSpan1);
      int i2 = localSpannable.getSpanEnd(localBulletSpan1);
      if ((i1 > i) && (i2 <= k))
        localSpannable.removeSpan(localBulletSpan1);
    }
  }

  public Spanned c()
  {
    return m();
  }

  public boolean d()
  {
    return this.c;
  }

  protected void e()
  {
    if (this.c);
    while (l())
      return;
    this.c = true;
    try
    {
      this.e = true;
      k();
      return;
    }
    finally
    {
      this.c = false;
    }
  }

  public void f()
  {
    Spannable localSpannable = m();
    if (as.f((BulletSpan[])localSpannable.getSpans(b(localSpannable), c(localSpannable), BulletSpan.class)))
    {
      a();
      return;
    }
    b();
  }

  public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.c);
    while (l())
      return;
    Spannable localSpannable = m();
    int i = paramInt1 + paramInt3;
    BulletSpan[] arrayOfBulletSpan = (BulletSpan[])localSpannable.getSpans(paramInt1, i, BulletSpan.class);
    int j = arrayOfBulletSpan.length;
    int k = 0;
    int m = 0;
    int i2;
    if (k >= j)
      if (m != 0)
        i2 = arrayOfBulletSpan.length;
    for (int i3 = 0; ; i3++)
    {
      if (i3 >= i2)
      {
        this.e = true;
        return;
        BulletSpan localBulletSpan1 = arrayOfBulletSpan[k];
        int n = localSpannable.getSpanStart(localBulletSpan1);
        int i1 = localSpannable.getSpanEnd(localBulletSpan1);
        if ((n <= paramInt1) && (i1 >= i) && ((n != paramInt1) || (i1 != i)))
          m = 1;
        k++;
        break;
      }
      BulletSpan localBulletSpan2 = arrayOfBulletSpan[i3];
      int i4 = localSpannable.getSpanStart(localBulletSpan2);
      int i5 = localSpannable.getSpanEnd(localBulletSpan2);
      if ((i4 >= paramInt1) && (i5 <= i))
        localSpannable.removeSpan(localBulletSpan2);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.b.d
 * JD-Core Version:    0.6.2
 */