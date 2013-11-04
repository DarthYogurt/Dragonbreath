package com.flipdog.speller;

import android.app.Activity;
import android.content.res.Resources;

public class c
{
  private Resources a;
  private String[] b;

  public c(Activity paramActivity)
  {
    this.a = paramActivity.getResources();
    this.b = this.a.getStringArray(com.flipdog.c.language_values);
  }

  public int a(String paramString)
  {
    for (int i = 0; ; i++)
    {
      if (i >= this.b.length)
        i = -1;
      while (this.b[i].equals(paramString))
        return i;
    }
  }

  public String a(int paramInt)
  {
    return this.b[paramInt];
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.c
 * JD-Core Version:    0.6.2
 */