package com.flipdog.ads.a;

public class c
{
  private static final b a = new b();
  private b[] b;
  private int c = 0;
  private int d = 0;

  public c(int paramInt)
  {
    this.b = new b[paramInt];
  }

  public b a()
  {
    if ((this.d >= this.b.length) || (this.b[this.d] == null))
      this.d = 0;
    if (this.b[this.d] == null)
      return a;
    b[] arrayOfb = this.b;
    int i = this.d;
    this.d = (i + 1);
    return arrayOfb[i];
  }

  public void a(b paramb)
  {
    b[] arrayOfb = this.b;
    int i = this.c;
    this.c = (i + 1);
    arrayOfb[i] = paramb;
    if (this.c >= this.b.length)
      this.c = 0;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.a.c
 * JD-Core Version:    0.6.2
 */