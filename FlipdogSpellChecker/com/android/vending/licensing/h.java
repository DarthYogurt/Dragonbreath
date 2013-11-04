package com.android.vending.licensing;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;

public class h
{
  private static final String a = "PreferenceObfuscator";
  private final SharedPreferences b;
  private final d c;
  private SharedPreferences.Editor d;

  public h(SharedPreferences paramSharedPreferences, d paramd)
  {
    this.b = paramSharedPreferences;
    this.c = paramd;
    this.d = null;
  }

  public void a()
  {
    if (this.d != null)
    {
      this.d.commit();
      this.d = null;
    }
  }

  public void a(String paramString1, String paramString2)
  {
    if (this.d == null)
      this.d = this.b.edit();
    String str = this.c.a(paramString2);
    this.d.putString(paramString1, str);
  }

  public String b(String paramString1, String paramString2)
  {
    String str1 = this.b.getString(paramString1, null);
    if (str1 != null);
    try
    {
      String str2 = this.c.b(str1);
      paramString2 = str2;
      return paramString2;
    }
    catch (b localb)
    {
      Log.w("PreferenceObfuscator", "Validation error while reading preference: " + paramString1);
    }
    return paramString2;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.vending.licensing.h
 * JD-Core Version:    0.6.2
 */