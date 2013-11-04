package com.flipdog.a.b.b;

import com.flipdog.commons.a.ax;

public class c extends a
{
  public long e;
  public String f;

  public c(String paramString)
  {
    super(paramString);
  }

  public c(String paramString1, String paramString2)
  {
    super(paramString1, paramString2);
  }

  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof c))
    {
      c localc = (c)paramObject;
      return (super.equals(paramObject)) && (ax.c(this.f, localc.f)) && (this.e == localc.e);
    }
    return false;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = super.toString();
    arrayOfObject[1] = Long.valueOf(this.e);
    arrayOfObject[2] = this.f;
    return String.format("%s. Size: %d. MimeType: %s", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.b.b.c
 * JD-Core Version:    0.6.2
 */