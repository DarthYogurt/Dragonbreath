package com.flipdog.commons.i;

import com.google.inject.Injector;
import com.google.inject.Key;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class b
{
  private static a a;
  private static ThreadLocal<a> b = new ThreadLocal();
  private static c c;

  public static a a()
  {
    if (a != null)
      return a;
    if (b.get() != null)
      return (a)b.get();
    return null;
  }

  public static <T> T a(Class<T> paramClass)
  {
    a locala = a();
    if (locala == null)
      System.out.println("huh... type = " + paramClass.getName());
    c.b(paramClass);
    return locala.a().getInstance(paramClass);
  }

  public static void a(a parama)
  {
    a = parama;
  }

  public static void a(c paramc)
  {
    c = paramc;
  }

  public static void b()
  {
    Injector localInjector = a().a();
    Iterator localIterator = localInjector.getBindings().keySet().iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      localInjector.getInstance((Key)localIterator.next());
    }
  }

  public static void b(a parama)
  {
    b.set(parama);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.i.b
 * JD-Core Version:    0.6.2
 */