package com.flipdog.editor;

import java.util.ArrayList;
import java.util.List;

public class av
{
  private List<Integer> a = new ArrayList();
  private int b;

  public av()
  {
    this(3);
  }

  public av(int paramInt)
  {
    this.b = paramInt;
  }

  public void a(int paramInt)
  {
    if (this.a == null);
    while (true)
    {
      return;
      if (!this.a.contains(Integer.valueOf(paramInt)))
      {
        this.a.add(Integer.valueOf(paramInt));
        while (this.a.size() > this.b)
          this.a.remove(0);
      }
    }
  }

  public boolean b(int paramInt)
  {
    return this.a.contains(Integer.valueOf(paramInt));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.av
 * JD-Core Version:    0.6.2
 */