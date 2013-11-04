package com.flipdog.a.b.b;

import com.flipdog.commons.a.as;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class b extends a
{
  private static final List<a> f = Collections.unmodifiableList(new ArrayList());
  public List<a> e = f;

  public b(String paramString)
  {
    super(paramString);
  }

  public b(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public boolean c()
  {
    return this.e != f;
  }

  public void d()
  {
    this.e = f;
  }

  public b e()
  {
    b localb = (b)super.b();
    if (localb.e != f)
      localb.e = as.a(localb.e);
    return localb;
  }

  public boolean equals(Object paramObject)
  {
    b localb;
    Iterator localIterator;
    if ((paramObject instanceof b))
    {
      localb = (b)paramObject;
      if (!super.equals(paramObject))
        return false;
      if (this.e == localb.e)
        return true;
      if ((this.e == null) || (localb.e == null))
        return false;
      if (localb.e.size() == this.e.size())
        localIterator = this.e.iterator();
    }
    a locala1;
    a locala2;
    do
    {
      if (!localIterator.hasNext())
        return false;
      locala1 = (a)localIterator.next();
      locala2 = com.flipdog.a.c.b.c(localb.e, locala1.a());
      if (locala2 == null)
        return false;
    }
    while (locala1.equals(locala2));
    return false;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = super.toString();
    arrayOfObject[1] = Integer.valueOf(this.e.size());
    arrayOfObject[2] = Boolean.valueOf(c());
    return String.format("%s. Contents: %d [%b]", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.b.b.b
 * JD-Core Version:    0.6.2
 */