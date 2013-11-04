package com.a.a.a;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class j extends c<Object>
{
  private static final HashMap<Class, String> d;

  static
  {
    if (!j.class.desiredAssertionStatus());
    for (boolean bool = true; ; bool = false)
    {
      c = bool;
      d = new HashMap();
      d.put(String.class, "a string");
      d.put(Number.class, "a number");
      d.put(Boolean.class, "a boolean");
      d.put(Map.class, "an object");
      d.put(List.class, "an array");
      return;
    }
  }

  public j(Object paramObject)
  {
    super(paramObject, null);
  }

  public j(Object paramObject, String paramString)
  {
    super(paramObject, paramString);
  }

  private static String a(Class<?> paramClass)
  {
    String str;
    if (paramClass == null)
      str = "null";
    do
    {
      return str;
      str = (String)d.get(paramClass);
    }
    while ((c) || (str != null));
    throw new AssertionError();
  }

  private static String a(Object paramObject)
  {
    if (paramObject == null)
      return "null";
    if ((paramObject instanceof Number))
      return "a number";
    if ((paramObject instanceof String))
      return "a string";
    if ((paramObject instanceof Boolean))
      return "a boolean";
    if ((paramObject instanceof Map))
      return "an object";
    if ((paramObject instanceof List))
      return "an array";
    throw new IllegalArgumentException("not a valid org.json.simple type: " + paramObject.getClass().getName());
  }

  static String a(String paramString1, String paramString2)
  {
    if (paramString1 == null)
      return paramString2;
    return paramString1 + "/" + paramString2;
  }

  private boolean b(Class<?> paramClass)
  {
    if ((!c) && (paramClass == null))
      throw new AssertionError();
    return paramClass.isInstance(this.a);
  }

  private <T> T c(Class<T> paramClass)
    throws b
  {
    if ((!c) && (paramClass == null))
      throw new AssertionError();
    if (paramClass.isInstance(this.a))
      return this.a;
    throw a("expecting " + a(paramClass) + ", found " + a(this.a));
  }

  public <T> T a(f<T> paramf)
    throws b
  {
    if (b())
      return null;
    return paramf.a(this);
  }

  public void a()
    throws b
  {
    if (this.a != null)
      throw a("expecting null");
  }

  public boolean b()
  {
    return this.a == null;
  }

  public e c()
    throws b
  {
    return new e((Map)c(Map.class), this.b);
  }

  public boolean d()
  {
    return b(Map.class);
  }

  public g e()
    throws b
  {
    return new g((List)c(List.class), this.b);
  }

  public boolean f()
  {
    return b(List.class);
  }

  public Number g()
    throws b
  {
    return (Number)c(Number.class);
  }

  public boolean h()
  {
    return b(Number.class);
  }

  public long i()
    throws b
  {
    if ((this.a instanceof Number))
      return ((Number)this.a).longValue();
    if ((this.a instanceof String))
    {
      String str = (String)this.a;
      try
      {
        long l = Long.parseLong(str, 16);
        return l;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        throw a("couldn't parse string as hex (expecting a 64-bit signed integer value)");
      }
    }
    throw a("expecting an integer (or a hex string), found " + a(this.a));
  }

  public boolean j()
  {
    try
    {
      i();
      return true;
    }
    catch (b localb)
    {
    }
    return false;
  }

  public String k()
    throws b
  {
    return (String)c(String.class);
  }

  public String l()
    throws b
  {
    if (this.a == null)
      return null;
    return (String)c(String.class);
  }

  public boolean m()
  {
    return b(String.class);
  }

  public boolean n()
    throws b
  {
    return ((Boolean)c(Boolean.class)).booleanValue();
  }

  public boolean o()
  {
    return b(Boolean.class);
  }

  public b p()
  {
    return a("unexpected type: " + a(this.a));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.a.j
 * JD-Core Version:    0.6.2
 */