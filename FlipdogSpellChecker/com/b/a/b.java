package com.b.a;

import android.database.Cursor;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.a.r;
import java.util.Date;

public class b
{
  private int a = 0;
  private Cursor b;

  public b(Cursor paramCursor)
  {
    this.b = paramCursor;
  }

  public int a()
  {
    Cursor localCursor = this.b;
    int i = this.a;
    this.a = (i + 1);
    return localCursor.getInt(i);
  }

  public int a(int paramInt)
  {
    Cursor localCursor = this.b;
    int i = this.a;
    this.a = (i + 1);
    return r.a(localCursor, i, paramInt);
  }

  public boolean a(boolean paramBoolean)
  {
    Cursor localCursor = this.b;
    int i = this.a;
    this.a = (i + 1);
    return r.a(localCursor.getString(i), Boolean.valueOf(paramBoolean)).booleanValue();
  }

  public long b()
  {
    Cursor localCursor = this.b;
    int i = this.a;
    this.a = (i + 1);
    return localCursor.getLong(i);
  }

  public String c()
  {
    Cursor localCursor = this.b;
    int i = this.a;
    this.a = (i + 1);
    return localCursor.getString(i);
  }

  public float d()
  {
    Cursor localCursor = this.b;
    int i = this.a;
    this.a = (i + 1);
    return localCursor.getFloat(i);
  }

  public double e()
  {
    Cursor localCursor = this.b;
    int i = this.a;
    this.a = (i + 1);
    return localCursor.getDouble(i);
  }

  public Date f()
  {
    Cursor localCursor = this.b;
    int i = this.a;
    this.a = (i + 1);
    return r.a(localCursor.getLong(i));
  }

  public void g()
  {
    this.a = (1 + this.a);
  }

  public String[] h()
  {
    Cursor localCursor = this.b;
    int i = this.a;
    this.a = (i + 1);
    String str = localCursor.getString(i);
    if (ax.a(str))
      return null;
    return r.a(str);
  }

  public Cursor i()
  {
    return this.b;
  }

  public void j()
  {
    this.a = 0;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a.b
 * JD-Core Version:    0.6.2
 */