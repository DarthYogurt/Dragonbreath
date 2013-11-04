package com.flipdog.about;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import com.flipdog.commons.a.ay;

public class b
{
  private static final String a = "version";

  public static boolean a()
  {
    String str = c().getString("version", null);
    return d().a.equals(str);
  }

  public static void b()
  {
    ay localay = d();
    SharedPreferences.Editor localEditor = c().edit();
    localEditor.putString("version", localay.a);
    localEditor.commit();
  }

  private static SharedPreferences c()
  {
    return ((Context)com.flipdog.commons.i.b.a(Context.class)).getSharedPreferences("my_prefs", 0);
  }

  private static ay d()
  {
    return ay.a();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.about.b
 * JD-Core Version:    0.6.2
 */