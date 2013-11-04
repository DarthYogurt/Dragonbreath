package com.flipdog.a.f;

import com.flipdog.a.a.a.d;
import com.flipdog.a.a.b;

public abstract class g extends f
  implements d
{
  public g(String paramString)
  {
    super(paramString);
  }

  protected void a(b paramb, long paramLong1, long paramLong2)
  {
    if (paramb == null)
      return;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Long.valueOf(paramLong1);
    arrayOfObject[1] = Long.valueOf(paramLong2);
    a("Uploaded: %d / %d", arrayOfObject);
    paramb.a(paramLong1, paramLong2);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.f.g
 * JD-Core Version:    0.6.2
 */