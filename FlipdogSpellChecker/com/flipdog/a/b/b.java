package com.flipdog.a.b;

import com.flipdog.a.b.c.a;
import com.flipdog.commons.a.ax;

public class b
  implements Cloneable
{
  public final String a;
  public final String b;
  public String c;
  public String d;

  public b(String paramString1, String paramString2)
  {
    this.a = paramString1;
    this.b = paramString2;
  }

  public b(String paramString1, String paramString2, a parama)
  {
    this(paramString1, paramString2);
    this.c = parama.a;
    this.d = parama.d;
  }

  public b a()
  {
    try
    {
      b localb = (b)super.clone();
      return localb;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException(localCloneNotSupportedException);
    }
  }

  public boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof b;
    boolean bool2 = false;
    if (bool1)
    {
      b localb = (b)paramObject;
      boolean bool3 = ax.c(this.a, localb.a);
      bool2 = false;
      if (bool3)
      {
        boolean bool4 = ax.c(this.b, localb.b);
        bool2 = false;
        if (bool4)
        {
          boolean bool5 = ax.c(this.c, localb.c);
          bool2 = false;
          if (bool5)
          {
            boolean bool6 = ax.c(this.d, localb.d);
            bool2 = false;
            if (bool6)
              bool2 = true;
          }
        }
      }
    }
    return bool2;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = this.a;
    arrayOfObject[1] = this.b;
    arrayOfObject[2] = this.c;
    arrayOfObject[3] = this.d;
    return String.format("user: %s. pwd: %s. token: %s. refresh: %s", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.b.b
 * JD-Core Version:    0.6.2
 */