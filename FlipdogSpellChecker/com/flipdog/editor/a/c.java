package com.flipdog.editor.a;

public class c
  implements f
{
  private int a;
  private int b;
  private int c = -1;
  private i[] d;

  public c(int paramInt)
  {
    this.a = paramInt;
    this.d = new i[paramInt];
  }

  private int e()
  {
    return this.c % this.a;
  }

  private int f()
  {
    this.b = Math.min(1 + this.b, this.a);
    this.c = (1 + this.c);
    return e();
  }

  private void g()
  {
    if (this.b == 0)
      return;
    this.b = (-1 + this.b);
    this.c = (-1 + this.c);
  }

  public void a()
  {
    this.b = 0;
  }

  public void a(i parami)
  {
    int i = f();
    this.d[i] = parami;
  }

  public boolean b()
  {
    return this.b > 0;
  }

  public i c()
  {
    return this.d[e()];
  }

  public void d()
  {
    g();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.a.c
 * JD-Core Version:    0.6.2
 */