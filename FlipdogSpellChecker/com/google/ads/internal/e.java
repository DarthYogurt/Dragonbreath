package com.google.ads.internal;

import android.os.Bundle;
import java.io.Serializable;
import java.util.HashMap;

public class e
{
  private final String a;
  private HashMap<String, String> b;

  public e(Bundle paramBundle)
  {
    this.a = paramBundle.getString("action");
    this.b = a(paramBundle.getSerializable("params"));
  }

  public e(String paramString)
  {
    this.a = paramString;
  }

  public e(String paramString, HashMap<String, String> paramHashMap)
  {
    this(paramString);
    this.b = paramHashMap;
  }

  private HashMap<String, String> a(Serializable paramSerializable)
  {
    if ((paramSerializable instanceof HashMap))
      return (HashMap)paramSerializable;
    return null;
  }

  public Bundle a()
  {
    Bundle localBundle = new Bundle();
    localBundle.putString("action", this.a);
    localBundle.putSerializable("params", this.b);
    return localBundle;
  }

  public String b()
  {
    return this.a;
  }

  public HashMap<String, String> c()
  {
    return this.b;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.internal.e
 * JD-Core Version:    0.6.2
 */