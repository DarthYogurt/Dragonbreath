package com.flipdog.speller;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.flipdog.commons.i.b;

public class d
{
  private static final String b = "com.flipdog.speller";
  public String a;

  public static d a()
  {
    SharedPreferences localSharedPreferences = c();
    d locald = new d();
    locald.a = localSharedPreferences.getString("spellLanguage", locald.a);
    return locald;
  }

  private static SharedPreferences c()
  {
    return d().getSharedPreferences("com.flipdog.speller", 0);
  }

  private static Context d()
  {
    return (Context)b.a(Context.class);
  }

  public void b()
  {
    SharedPreferences.Editor localEditor = c().edit();
    try
    {
      localEditor.putString("spellLanguage", this.a);
      return;
    }
    finally
    {
      localEditor.commit();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.d
 * JD-Core Version:    0.6.2
 */