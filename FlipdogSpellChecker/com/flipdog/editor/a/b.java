package com.flipdog.editor.a;

import java.util.ArrayList;
import java.util.List;

public class b
  implements f
{
  private ArrayList<i> a = new ArrayList();
  private int b;

  public b(int paramInt)
  {
    this.b = paramInt;
  }

  private <T> T a(List<T> paramList)
  {
    return paramList.get(b(paramList));
  }

  private <T> int b(List<T> paramList)
  {
    return -1 + paramList.size();
  }

  private <T> T c(List<T> paramList)
  {
    return paramList.remove(b(paramList));
  }

  public void a()
  {
    this.a.clear();
  }

  public void a(i parami)
  {
    this.a.add(parami);
    while (true)
    {
      if (this.a.size() <= this.b)
        return;
      this.a.remove(0);
    }
  }

  public boolean b()
  {
    return this.a.size() != 0;
  }

  public i c()
  {
    return (i)a(this.a);
  }

  public void d()
  {
    c(this.a);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.a.b
 * JD-Core Version:    0.6.2
 */