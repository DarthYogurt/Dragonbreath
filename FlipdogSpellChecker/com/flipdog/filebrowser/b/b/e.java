package com.flipdog.filebrowser.b.b;

public class e<T>
{
  public T a;
  public int b;

  public e(T paramT)
  {
    this.a = paramT;
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = this.a;
    arrayOfObject[1] = Integer.valueOf(this.b);
    return String.format("Path: %s. ListIndex: %d", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.b.b.e
 * JD-Core Version:    0.6.2
 */