package com.flipdog.commons.k;

public class b
  implements Cloneable
{
  public boolean a = false;
  public boolean b = false;

  private boolean a(b paramb)
  {
    if (this.a != paramb.a);
    while (this.b != paramb.b)
      return false;
    return true;
  }

  public Object clone()
  {
    try
    {
      Object localObject = super.clone();
      return localObject;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new RuntimeException(localCloneNotSupportedException);
    }
  }

  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof b))
      return false;
    return a((b)paramObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.k.b
 * JD-Core Version:    0.6.2
 */