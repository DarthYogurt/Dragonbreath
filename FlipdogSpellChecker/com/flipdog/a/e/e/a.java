package com.flipdog.a.e.e;

import com.flipdog.a.b.b.b;
import com.flipdog.commons.a.ax;

public class a extends b
{
  public String f;

  public a(String paramString)
  {
    super(paramString);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof a))
    {
      a locala = (a)paramObject;
      return (super.equals(paramObject)) && (ax.c(this.f, locala.f));
    }
    return false;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = super.toString();
    arrayOfObject[1] = this.f;
    return String.format("%s. Hash: %s", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.e.e.a
 * JD-Core Version:    0.6.2
 */