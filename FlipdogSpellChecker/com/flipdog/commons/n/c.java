package com.flipdog.commons.n;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import java.util.ArrayList;
import java.util.Iterator;

public class c
{
  private ArrayList<d> a = new ArrayList();

  private d d(int paramInt)
  {
    Iterator localIterator = this.a.iterator();
    int i = 0;
    while (true)
    {
      if (!localIterator.hasNext())
        throw new IndexOutOfBoundsException(paramInt);
      d locald = (d)localIterator.next();
      int j = locald.b();
      if ((paramInt >= i) && (paramInt < i + j))
      {
        locald.c = i;
        return locald;
      }
      i += j;
    }
  }

  public int a()
  {
    Iterator localIterator = this.a.iterator();
    int i = 0;
    while (true)
    {
      if (!localIterator.hasNext())
        return i;
      i += ((d)localIterator.next()).b();
    }
  }

  public View a(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    return d(paramInt).a(paramInt, paramView, paramViewGroup);
  }

  public Object a(int paramInt)
  {
    return d(paramInt).b(paramInt);
  }

  public void a(View paramView)
  {
    this.a.add(new d(paramView));
  }

  public void a(BaseAdapter paramBaseAdapter)
  {
    this.a.add(new d(paramBaseAdapter));
  }

  public int b()
  {
    Iterator localIterator = this.a.iterator();
    int i = 0;
    while (true)
    {
      if (!localIterator.hasNext())
        return i;
      i += ((d)localIterator.next()).a();
    }
  }

  public long b(int paramInt)
  {
    return d(paramInt).c(paramInt);
  }

  public int c(int paramInt)
  {
    Iterator localIterator = this.a.iterator();
    int i = 0;
    int k;
    for (int j = 0; ; j = k)
    {
      if (!localIterator.hasNext())
        throw new RuntimeException("Unexpected " + paramInt);
      d locald = (d)localIterator.next();
      k = j + locald.b();
      if (paramInt < k)
        return i + locald.a(paramInt - j);
      i += locald.a();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.n.c
 * JD-Core Version:    0.6.2
 */