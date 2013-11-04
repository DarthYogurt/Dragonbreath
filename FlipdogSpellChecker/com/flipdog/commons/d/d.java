package com.flipdog.commons.d;

public class d
{
  private long a;
  private boolean b;
  private long c;
  private long d;

  public void a()
  {
    this.a = System.currentTimeMillis();
    this.b = true;
  }

  public void b()
  {
    if (!this.b)
      return;
    this.d = (System.currentTimeMillis() - this.a);
    this.c += this.d;
    this.b = false;
  }

  public long c()
  {
    return this.c;
  }

  public long d()
  {
    return this.d;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.d.d
 * JD-Core Version:    0.6.2
 */