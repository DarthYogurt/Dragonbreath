package com.a.a;

import java.util.Date;
import java.util.Map;

public class e
{
  public final String a;
  public final Date b;

  private e(String paramString, boolean paramBoolean)
  {
    if ((!paramBoolean) && (paramString.startsWith("https://")))
      paramString = paramString.replaceFirst("https://", "http://").replaceFirst(":443/", "/");
    this.a = paramString;
    this.b = null;
  }

  private e(Map<String, Object> paramMap)
  {
    this(paramMap, true);
  }

  private e(Map<String, Object> paramMap, boolean paramBoolean)
  {
    String str1 = (String)paramMap.get("url");
    String str2 = (String)paramMap.get("expires");
    if (str2 != null);
    for (this.b = m.a(str2); ; this.b = null)
    {
      if ((!paramBoolean) && (str1.startsWith("https://")))
        str1 = str1.replaceFirst("https://", "http://").replaceFirst(":443/", "/");
      this.a = str1;
      return;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.e
 * JD-Core Version:    0.6.2
 */