package com.flipdog.commons.network;

public class e
{
  private boolean a;
  private boolean b = true;
  private int c;

  public void a()
  {
    try
    {
      this.b = false;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(int paramInt)
  {
    try
    {
      this.b = true;
      this.c = paramInt;
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(boolean paramBoolean)
  {
    this.a = paramBoolean;
  }

  public boolean b()
  {
    try
    {
      boolean bool = this.b;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public boolean c()
  {
    int i = 1;
    try
    {
      if (this.b)
      {
        int j = this.c;
        if (j == i)
          return i;
      }
      i = 0;
    }
    finally
    {
    }
  }

  public boolean d()
  {
    try
    {
      boolean bool1 = b();
      if (bool1)
      {
        bool2 = false;
        return bool2;
      }
      boolean bool2 = true;
    }
    finally
    {
    }
  }

  public boolean e()
  {
    return this.a;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.network.e
 * JD-Core Version:    0.6.2
 */