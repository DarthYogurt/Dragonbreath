package com.b.a;

import android.database.Cursor;
import com.b.a.b.b;
import com.flipdog.commons.a.ad;
import com.flipdog.commons.a.as;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.a.c;
import com.flipdog.commons.a.r;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class j
{
  private l a = l.a;
  private com.b.a.b.e<?> b;
  private String c;
  private String d;
  private StringBuilder e = new StringBuilder();
  private ArrayList<String> f = new ArrayList();
  private ArrayList<String> g = new ArrayList();
  private ArrayList<String> h = new ArrayList();
  private String i;
  private String[] j;
  private StringBuilder k = new StringBuilder();
  private List<String> l = null;
  private List<String> m = null;
  private Integer n;
  private Integer o;
  private h p;
  private j q;
  private String r;
  private StringBuilder s = new StringBuilder();
  private j t;
  private String u;
  private String v;
  private List<String> w;
  private List<String> x;

  public j()
  {
  }

  public j(h paramh)
  {
    this.p = paramh;
  }

  public j(h paramh, com.b.a.b.e<?> parame)
  {
    this.p = paramh;
    this.b = parame;
  }

  public j(h paramh, String paramString)
  {
    this.p = paramh;
    this.d = paramString;
  }

  public j(h paramh, String paramString, com.b.a.b.e<?> parame)
  {
    this.p = paramh;
    this.d = paramString;
    this.b = parame;
  }

  private String a(Object paramObject)
  {
    if (paramObject == null)
      return null;
    if ((paramObject instanceof String))
      return (String)paramObject;
    if ((paramObject instanceof Long))
      return paramObject;
    if ((paramObject instanceof Integer))
      return paramObject;
    if ((paramObject instanceof Float))
      return paramObject;
    if ((paramObject instanceof Double))
      return paramObject;
    if ((paramObject instanceof Boolean))
      return paramObject;
    if ((paramObject instanceof Date))
      return r.a((Date)paramObject);
    throw new RuntimeException("Unexpected type " + paramObject.getClass().getName());
  }

  private j c(String paramString, String[] paramArrayOfString)
  {
    if (this.e.length() != 0)
    {
      if (this.v == null)
        break label52;
      this.e.append(this.v);
    }
    while (true)
    {
      this.e.append(paramString);
      this.f.addAll(Arrays.asList(paramArrayOfString));
      return this;
      label52: this.e.append(" AND ");
    }
  }

  private static String c(String paramString, Collection<String> paramCollection)
  {
    return String.format("%s IN (%s)", new Object[] { paramString, c(paramCollection.size()) });
  }

  public static StringBuilder c(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    for (int i1 = 0; ; i1++)
    {
      if (i1 >= paramInt)
        return localStringBuilder;
      if (localStringBuilder.length() != 0)
        localStringBuilder.append(", ");
      localStringBuilder.append("?");
    }
  }

  private List<String> c(String[] paramArrayOfString)
  {
    return ad.b(paramArrayOfString);
  }

  private static String d(String paramString, Collection<String> paramCollection)
  {
    return String.format("%s NOT IN (%s)", new Object[] { paramString, c(paramCollection.size()) });
  }

  private <T> e<T> e(com.b.a.b.e<T> parame)
  {
    return new e(n(), parame);
  }

  private Cursor n()
  {
    String str = e();
    String[] arrayOfString = d();
    return new p(this.p.a(str, arrayOfString));
  }

  public j a(int paramInt)
  {
    this.n = Integer.valueOf(paramInt);
    return this;
  }

  public j a(j paramj)
  {
    return b(paramj, null);
  }

  public j a(j paramj, String paramString)
  {
    this.q = paramj;
    this.r = paramString;
    return this;
  }

  public j a(String paramString)
  {
    this.a = l.b;
    this.c = paramString;
    return this;
  }

  public j a(String paramString, j paramj)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = paramj.e();
    return c(String.format("%s IN (%s)", arrayOfObject), paramj.d());
  }

  public j a(String paramString, Object paramObject)
  {
    if (paramObject == null)
      return c(paramString + " IS NULL", new String[0]);
    String str = paramString + " = ?";
    String[] arrayOfString = new String[1];
    arrayOfString[0] = a(paramObject);
    return c(str, arrayOfString);
  }

  public j a(String paramString1, String paramString2)
  {
    if (this.k.length() != 0)
      this.k.append(", ");
    this.k.append(paramString1);
    if (paramString2 != null)
    {
      this.k.append(" AS ");
      this.k.append(paramString2);
    }
    return this;
  }

  public j a(String paramString, Collection<String> paramCollection)
  {
    return c(c(paramString, paramCollection), ad.a(paramCollection));
  }

  public j a(String paramString, Object[] paramArrayOfObject)
  {
    String[] arrayOfString = new String[paramArrayOfObject.length];
    for (int i1 = 0; ; i1++)
    {
      if (i1 >= paramArrayOfObject.length)
        return c(String.format("(%s)", new Object[] { paramString }), arrayOfString);
      arrayOfString[i1] = a(paramArrayOfObject[i1]);
    }
  }

  public j a(String paramString, String[] paramArrayOfString)
  {
    return c(c(paramString, c(paramArrayOfString)), paramArrayOfString);
  }

  public j a(String[] paramArrayOfString)
  {
    this.a = l.b;
    this.c = ax.a(paramArrayOfString, ", ");
    return this;
  }

  public j a(String[] paramArrayOfString, Object paramObject)
  {
    if ((paramArrayOfString == null) || (paramArrayOfString.length == 0));
    while (paramObject == null)
      return this;
    StringBuilder localStringBuilder = new StringBuilder();
    String[] arrayOfString = new String[paramArrayOfString.length];
    localStringBuilder.append("(");
    for (int i1 = 0; ; i1++)
    {
      if (i1 >= paramArrayOfString.length)
      {
        localStringBuilder.append(")");
        return c(localStringBuilder.toString(), arrayOfString);
      }
      if (i1 != 0)
        localStringBuilder.append(" OR ");
      localStringBuilder.append(paramArrayOfString[i1] + " LIKE ?");
      arrayOfString[i1] = a(paramObject);
    }
  }

  public <T> ArrayList<T> a()
  {
    return b(this.b);
  }

  public <T> HashSet<T> a(com.b.a.b.e<T> parame)
  {
    HashSet localHashSet = new HashSet();
    Cursor localCursor = n();
    try
    {
      while (true)
      {
        boolean bool = localCursor.moveToNext();
        if (!bool)
          return localHashSet;
        localHashSet.add(parame.b(localCursor));
      }
    }
    finally
    {
      localCursor.close();
    }
  }

  public <T> T[] a(Class<T> paramClass, com.b.a.b.e<T> parame)
  {
    Cursor localCursor = n();
    try
    {
      Object[] arrayOfObject = c.a(paramClass, localCursor.getCount());
      int i2;
      for (int i1 = 0; ; i1 = i2)
      {
        boolean bool = localCursor.moveToNext();
        if (!bool)
          return arrayOfObject;
        Object localObject2 = parame.b(localCursor);
        i2 = i1 + 1;
        arrayOfObject[i1] = localObject2;
      }
    }
    finally
    {
      localCursor.close();
    }
  }

  public j b(int paramInt)
  {
    this.o = Integer.valueOf(paramInt);
    return this;
  }

  public j b(j paramj, String paramString)
  {
    this.t = paramj;
    this.u = paramString;
    return this;
  }

  public j b(String paramString)
  {
    return a(paramString, null);
  }

  public j b(String paramString, j paramj)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramString;
    arrayOfObject[1] = paramj.e();
    return c(String.format("%s NOT IN (%s)", arrayOfObject), paramj.d());
  }

  public j b(String paramString, Object paramObject)
  {
    if (paramObject == null)
      return c(paramString + " IS NOT NULL", new String[0]);
    String str = paramString + " != ?";
    String[] arrayOfString = new String[1];
    arrayOfString[0] = a(paramObject);
    return c(str, arrayOfString);
  }

  public j b(String paramString1, String paramString2)
  {
    if (this.s.length() != 0)
      this.s.append(" AND ");
    this.s.append(String.format("%s = %s", new Object[] { paramString1, paramString2 }));
    return this;
  }

  public j b(String paramString, Collection<String> paramCollection)
  {
    return c(d(paramString, paramCollection), ad.a(paramCollection));
  }

  public j b(String paramString, Object[] paramArrayOfObject)
  {
    if (this.w == null)
    {
      this.w = as.b();
      this.x = as.b();
    }
    this.w.add(paramString);
    int i1 = paramArrayOfObject.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return this;
      Object localObject = paramArrayOfObject[i2];
      this.x.add(a(localObject));
    }
  }

  public j b(String paramString, String[] paramArrayOfString)
  {
    this.i = paramString;
    this.j = paramArrayOfString;
    return this;
  }

  public j b(String[] paramArrayOfString)
  {
    if (this.l == null)
      this.l = as.b();
    int i1 = paramArrayOfString.length;
    for (int i2 = 0; ; i2++)
    {
      if (i2 >= i1)
        return this;
      String str = paramArrayOfString[i2];
      this.l.add(str);
    }
  }

  public <T> T b()
  {
    return c(this.b);
  }

  public <T> ArrayList<T> b(com.b.a.b.e<T> parame)
  {
    ArrayList localArrayList = new ArrayList();
    Cursor localCursor = n();
    try
    {
      while (true)
      {
        boolean bool = localCursor.moveToNext();
        if (!bool)
          return localArrayList;
        localArrayList.add(parame.b(localCursor));
      }
    }
    finally
    {
      localCursor.close();
    }
  }

  public j c(String paramString)
  {
    return c(paramString + " IS NOT NULL", new String[0]);
  }

  public j c(String paramString, Object paramObject)
  {
    if (paramObject == null)
      return this;
    String str = paramString + " LIKE ?";
    String[] arrayOfString = new String[1];
    arrayOfString[0] = a(paramObject);
    return c(str, arrayOfString);
  }

  public Integer c()
  {
    return (Integer)c(new q(this));
  }

  public <T> T c(com.b.a.b.e<T> parame)
  {
    Cursor localCursor = n();
    try
    {
      if (localCursor.moveToNext())
      {
        Object localObject2 = parame.b(localCursor);
        return localObject2;
      }
      return null;
    }
    finally
    {
      localCursor.close();
    }
  }

  public j d(String paramString)
  {
    this.d = paramString;
    return this;
  }

  public j d(String paramString, Object paramObject)
  {
    this.g.add(paramString);
    this.h.add(a(paramObject));
    return this;
  }

  public <T> void d(com.b.a.b.e<T> parame)
  {
    e locale = e(parame);
    try
    {
      while (true)
      {
        boolean bool = locale.hasNext();
        if (!bool)
          return;
        locale.next();
      }
    }
    finally
    {
      locale.a();
    }
  }

  public String[] d()
  {
    if (this.j != null)
      return this.j;
    ArrayList localArrayList = new ArrayList();
    if (this.q != null)
      localArrayList.addAll(c(this.q.d()));
    if (this.t != null)
      localArrayList.addAll(c(this.t.d()));
    localArrayList.addAll(this.h);
    if (this.x != null)
      localArrayList.addAll(this.x);
    localArrayList.addAll(this.f);
    return ad.a(localArrayList);
  }

  public j e(String paramString)
  {
    this.i = paramString;
    return this;
  }

  public j e(String paramString, Object paramObject)
  {
    this.g.add(paramString);
    this.h.add(a(paramObject));
    return this;
  }

  public String e()
  {
    if (this.i != null)
      return this.i;
    String str1 = this.k.toString();
    if (str1.length() == 0)
      str1 = this.d;
    StringBuilder localStringBuilder1 = new StringBuilder();
    if (this.a == l.e)
    {
      localStringBuilder1.append(String.format("DELETE FROM %s", new Object[] { str1 }));
      if (this.e.length() != 0)
      {
        Object[] arrayOfObject2 = new Object[1];
        arrayOfObject2[0] = this.e;
        localStringBuilder1.append(String.format(" WHERE %s", arrayOfObject2));
      }
      if (this.l != null)
      {
        localStringBuilder1.append(" GROUP BY ");
        localStringBuilder1.append(ax.a(this.l, ", "));
      }
      if (this.m != null)
      {
        localStringBuilder1.append(" ORDER BY ");
        localStringBuilder1.append(ax.a(this.m, ", "));
      }
      if (this.n != null)
      {
        localStringBuilder1.append(" LIMIT ");
        localStringBuilder1.append(this.n);
      }
      if (this.o != null)
      {
        localStringBuilder1.append(" OFFSET ");
        localStringBuilder1.append(this.o);
      }
      return localStringBuilder1.toString();
    }
    if (this.a == l.d)
    {
      StringBuilder localStringBuilder2 = new StringBuilder();
      Iterator localIterator1 = this.g.iterator();
      label244: Iterator localIterator2;
      if (!localIterator1.hasNext())
        if (this.w != null)
          localIterator2 = this.w.iterator();
      while (true)
      {
        if (!localIterator2.hasNext())
        {
          Object[] arrayOfObject1 = new Object[2];
          arrayOfObject1[0] = this.d;
          arrayOfObject1[1] = localStringBuilder2.toString();
          localStringBuilder1.append(String.format("UPDATE %s SET %s", arrayOfObject1));
          break;
          String str2 = (String)localIterator1.next();
          if (localStringBuilder2.length() != 0)
            localStringBuilder2.append(", ");
          localStringBuilder2.append(str2 + " = ?");
          break label244;
        }
        String str3 = (String)localIterator2.next();
        if (localStringBuilder2.length() != 0)
          localStringBuilder2.append(", ");
        localStringBuilder2.append(str3);
      }
    }
    if (this.a == l.c)
    {
      StringBuilder localStringBuilder3 = new StringBuilder();
      Iterator localIterator3 = this.g.iterator();
      while (true)
      {
        if (!localIterator3.hasNext())
        {
          String str4 = ax.a(this.g, ", ");
          Object[] arrayOfObject3 = new Object[3];
          arrayOfObject3[0] = this.d;
          arrayOfObject3[1] = str4;
          arrayOfObject3[2] = localStringBuilder3.toString();
          localStringBuilder1.append(String.format("INSERT INTO %s(%s) VALUES(%s)", arrayOfObject3));
          break;
        }
        ((String)localIterator3.next());
        if (localStringBuilder3.length() != 0)
          localStringBuilder3.append(", ");
        localStringBuilder3.append("?");
      }
    }
    if (this.a == l.b)
    {
      if (this.t != null)
      {
        Object[] arrayOfObject7 = new Object[2];
        arrayOfObject7[0] = this.c;
        arrayOfObject7[1] = this.t.e();
        localStringBuilder1.append(String.format("SELECT %s FROM (%s)", arrayOfObject7));
      }
      while (true)
      {
        if (this.u != null)
        {
          Object[] arrayOfObject6 = new Object[1];
          arrayOfObject6[0] = this.u;
          localStringBuilder1.append(String.format(" as %s", arrayOfObject6));
        }
        if (this.q == null)
          break;
        Object[] arrayOfObject5 = new Object[3];
        arrayOfObject5[0] = this.q.e();
        arrayOfObject5[1] = this.r;
        arrayOfObject5[2] = this.s;
        localStringBuilder1.append(String.format(" INNER JOIN (%s) as %s ON %s", arrayOfObject5));
        break;
        Object[] arrayOfObject4 = new Object[2];
        arrayOfObject4[0] = this.c;
        arrayOfObject4[1] = str1;
        localStringBuilder1.append(String.format("SELECT %s FROM %s", arrayOfObject4));
      }
    }
    throw new RuntimeException("Query type is unknown.");
  }

  public j f(String paramString)
  {
    if (this.m == null)
      this.m = as.b();
    this.m.add(paramString);
    return this;
  }

  public String f()
  {
    return this.d;
  }

  public j g()
  {
    this.a = l.e;
    return this;
  }

  public j g(String paramString)
  {
    if (this.m == null)
      this.m = as.b();
    this.m.add(paramString + " DESC");
    return this;
  }

  public j h(String paramString)
  {
    if (this.m == null)
      this.m = as.b();
    this.m.add(paramString + " ASC");
    return this;
  }

  public void h()
  {
    String str = e();
    String[] arrayOfString = d();
    this.p.a(str, arrayOfString);
  }

  public j i()
  {
    this.a = l.c;
    return this;
  }

  public j i(String paramString)
  {
    this.a = l.e;
    return a(paramString, null);
  }

  public j j()
  {
    return a("COUNT(*)");
  }

  public j j(String paramString)
  {
    this.a = l.d;
    this.d = paramString;
    return this;
  }

  public int k()
  {
    return ((Integer)j().c(b.a)).intValue();
  }

  public j k(String paramString)
  {
    this.a = l.c;
    this.d = paramString;
    return this;
  }

  public j l(String paramString)
  {
    return e(String.format("DROP TABLE [%s]", new Object[] { paramString }));
  }

  public boolean l()
  {
    return ((Integer)j().c(b.a)).intValue() != 0;
  }

  public void m(String paramString)
  {
    l(paramString).h();
  }

  public boolean m()
  {
    return k() != 0;
  }

  public j n(String paramString)
  {
    this.v = String.format(" %s ", new Object[] { paramString });
    return this;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.j
 * JD-Core Version:    0.6.2
 */