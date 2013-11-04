package com.b.a;

import com.flipdog.commons.a.ax;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class k
{
  private String a;
  private ArrayList<String> b = new ArrayList();
  private ArrayList<String> c = new ArrayList();

  public k(String paramString)
  {
    this.a = paramString;
  }

  public void a()
  {
    this.b.add("id INTEGER PRIMARY KEY AUTOINCREMENT");
  }

  public void a(String paramString)
  {
    b(paramString, "INTEGER");
  }

  public void a(String paramString, double paramDouble)
  {
    a(paramString, "DOUBLE", Double.valueOf(paramDouble));
  }

  public void a(String paramString, int paramInt)
  {
    a(paramString, "INTEGER", Integer.valueOf(paramInt));
  }

  public void a(String paramString, long paramLong)
  {
    a(paramString, "INTEGER", Long.valueOf(paramLong));
  }

  public void a(String paramString1, String paramString2)
  {
    a(paramString1, "TEXT", paramString2);
  }

  public void a(String paramString1, String paramString2, Object paramObject)
  {
    this.b.add(String.format("[%s] %s DEFAULT '%s'", new Object[] { paramString1, paramString2, paramObject }));
  }

  public void a(String paramString, boolean paramBoolean)
  {
    a(paramString, "BOOLEAN", Boolean.valueOf(paramBoolean));
  }

  public void a(String[] paramArrayOfString)
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = this.a;
    arrayOfObject[1] = ax.a(paramArrayOfString, "_");
    arrayOfObject[2] = this.a;
    arrayOfObject[3] = ax.a(paramArrayOfString, ", ");
    String str = String.format("CREATE INDEX %s_%s ON %s(%s)", arrayOfObject);
    this.c.add(str);
  }

  public String b()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.a;
    arrayOfObject[1] = ax.a(this.b, ", ");
    return String.format("CREATE TABLE %s(%s)", arrayOfObject);
  }

  public void b(String paramString)
  {
    b(paramString, "INTEGER");
  }

  public void b(String paramString1, String paramString2)
  {
    this.b.add(String.format("[%s] %s", new Object[] { paramString1, paramString2 }));
  }

  public List<String> c()
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(b());
    localArrayList.addAll(this.c);
    return localArrayList;
  }

  public void c(String paramString)
  {
    b(paramString, "INTEGER");
  }

  public List<String> d()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.b.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localArrayList.addAll(this.c);
        return localArrayList;
      }
      String str = (String)localIterator.next();
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = this.a;
      arrayOfObject[1] = str;
      localArrayList.add(String.format("ALTER TABLE %s ADD %s", arrayOfObject));
    }
  }

  public void d(String paramString)
  {
    b(paramString, "DOUBLE");
  }

  public void e(String paramString)
  {
    b(paramString, "TEXT");
  }

  public void f(String paramString)
  {
    b(paramString, "TEXT COLLATE NOCASE");
  }

  public void g(String paramString)
  {
    b(paramString, "BOOLEAN");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.k
 * JD-Core Version:    0.6.2
 */