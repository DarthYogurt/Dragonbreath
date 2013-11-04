package com.flipdog.commons.p;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.flipdog.commons.a.m;
import java.util.Date;
import java.util.List;

public abstract class c
{
  protected SharedPreferences a = b();
  protected SharedPreferences.Editor b = this.a.edit();

  private void a(String paramString)
  {
  }

  private SharedPreferences b()
  {
    return ((b)com.flipdog.commons.i.b.a(b.class)).a(a(), 0);
  }

  protected abstract String a();

  protected void a(String paramString, float paramFloat)
  {
    this.b.putFloat(paramString, paramFloat);
  }

  protected void a(String paramString, int paramInt)
  {
    this.b.putInt(paramString, paramInt);
  }

  protected void a(String paramString, long paramLong)
  {
    this.b.putLong(paramString, paramLong);
  }

  protected void a(String paramString1, String paramString2)
  {
    this.b.putString(paramString1, paramString2);
  }

  protected void a(String paramString, Date paramDate)
  {
    a(paramString, m.a(paramDate));
  }

  protected void a(String paramString, List<String> paramList)
  {
    a(paramString, m.a(paramList));
  }

  protected void a(String paramString, boolean paramBoolean)
  {
    this.b.putBoolean(paramString, paramBoolean);
  }

  protected float b(String paramString, float paramFloat)
  {
    return this.a.getFloat(paramString, paramFloat);
  }

  protected int b(String paramString, int paramInt)
  {
    return this.a.getInt(paramString, paramInt);
  }

  protected long b(String paramString, long paramLong)
  {
    return this.a.getLong(paramString, paramLong);
  }

  protected String b(String paramString1, String paramString2)
  {
    return this.a.getString(paramString1, paramString2);
  }

  protected boolean b(String paramString, boolean paramBoolean)
  {
    return this.a.getBoolean(paramString, paramBoolean);
  }

  protected String c(String paramString)
  {
    return b(paramString, null);
  }

  public void c()
  {
    this.b.commit();
  }

  protected Integer d(String paramString)
  {
    if (!this.a.contains(paramString))
      return null;
    return Integer.valueOf(b(paramString, 0));
  }

  protected Boolean e(String paramString)
  {
    if (!this.a.contains(paramString))
      return null;
    return Boolean.valueOf(b(paramString, false));
  }

  protected Date f(String paramString)
  {
    return m.a(c(paramString));
  }

  protected List<String> g(String paramString)
  {
    return m.b(c(paramString));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.p.c
 * JD-Core Version:    0.6.2
 */