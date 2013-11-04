package com.flipdog.commons.h;

import com.b.a.h;
import com.flipdog.commons.a.ad;
import com.flipdog.commons.a.as;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class a
{
  public static final int a = 500;

  private static <T> List<T> a(T[] paramArrayOfT)
  {
    return ad.b(paramArrayOfT);
  }

  public static <T> void a(h paramh, Collection<T> paramCollection, int paramInt, b<List<T>> paramb)
  {
    if ((paramCollection instanceof List));
    for (List localList = (List)paramCollection; ; localList = as.a(paramCollection))
    {
      a(paramh, localList, paramInt, paramb);
      return;
    }
  }

  public static <T> void a(h paramh, Collection<T> paramCollection, b<List<T>> paramb)
  {
    a(paramh, paramCollection, 500, paramb);
  }

  public static <T> void a(h paramh, List<T> paramList, int paramInt, b<List<T>> paramb)
  {
    paramh.a();
    try
    {
      a(paramList, paramInt, paramb);
      paramh.b();
      return;
    }
    finally
    {
      paramh.c();
    }
  }

  public static <T> void a(h paramh, List<T> paramList, b<List<T>> paramb)
  {
    a(paramh, paramList, 500, paramb);
  }

  public static <T> void a(h paramh, T[] paramArrayOfT, int paramInt, b<List<T>> paramb)
  {
    a(paramh, a(paramArrayOfT), paramInt, paramb);
  }

  public static <T> void a(h paramh, T[] paramArrayOfT, b<List<T>> paramb)
  {
    a(paramh, paramArrayOfT, 500, paramb);
  }

  public static <T> void a(List<T> paramList, int paramInt, b<List<T>> paramb)
  {
    if (paramb == null)
      throw new RuntimeException("The block can't be null");
    if (paramList == null)
    {
      paramb.a(Collections.emptyList());
      return;
    }
    int i = paramList.size();
    int j;
    label46: int k;
    label49: int m;
    if (i % paramInt == 0)
    {
      j = i / paramInt;
      k = 0;
      if (k < j)
      {
        m = k * paramInt;
        if (paramInt * (k + 1) <= i)
          break label112;
      }
    }
    label112: for (int n = i % paramInt; ; n = paramInt)
    {
      paramb.a(paramList.subList(m, n + m));
      k++;
      break label49;
      break;
      j = 1 + i / paramInt;
      break label46;
    }
  }

  public static <T> void a(T[] paramArrayOfT, int paramInt, b<List<T>> paramb)
  {
    a(a(paramArrayOfT), paramInt, paramb);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.h.a
 * JD-Core Version:    0.6.2
 */