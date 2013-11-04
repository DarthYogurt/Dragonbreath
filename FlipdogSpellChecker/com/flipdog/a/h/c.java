package com.flipdog.a.h;

import com.flipdog.a.b;
import com.flipdog.a.b.a.a;
import com.flipdog.commons.a.as;
import java.util.Iterator;
import java.util.List;

public class c
{
  private static final List<a> a = as.b();

  public static int a(b paramb)
  {
    Class localClass1 = paramb.getClass();
    Iterator localIterator = a.iterator();
    a locala;
    Class localClass2;
    do
    {
      if (!localIterator.hasNext())
        throw new RuntimeException(paramb.getClass().getSimpleName());
      locala = (a)localIterator.next();
      localClass2 = locala.b();
    }
    while ((localClass2 == null) || (!localClass2.equals(localClass1)));
    return locala.a;
  }

  public static a a(int paramInt)
  {
    return b(paramInt);
  }

  public static List<a> a()
  {
    return as.a(a);
  }

  public static void a(a parama)
  {
    try
    {
      b(parama.a);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      a.add(parama);
    }
  }

  private static a b(int paramInt)
  {
    Iterator localIterator = a.iterator();
    a locala;
    do
    {
      if (!localIterator.hasNext())
        throw new IllegalArgumentException(Integer.toString(paramInt));
      locala = (a)localIterator.next();
    }
    while (locala.a != paramInt);
    return locala;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.h.c
 * JD-Core Version:    0.6.2
 */