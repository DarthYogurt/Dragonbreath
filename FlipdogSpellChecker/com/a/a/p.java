package com.a.a;

import java.io.Serializable;
import java.util.Map;

public class p
  implements Serializable
{
  private static final long h = 2097522622341535732L;
  public final String a;
  public final String b;
  public final long c;
  public final long d;
  public final long e;
  public final long f;
  public final String g;

  protected p(String paramString1, String paramString2, long paramLong1, String paramString3, long paramLong2, long paramLong3, long paramLong4)
  {
    this.a = paramString1;
    this.b = paramString2;
    this.f = paramLong1;
    this.g = paramString3;
    this.c = paramLong2;
    this.d = paramLong3;
    this.e = paramLong4;
  }

  protected p(Map<String, Object> paramMap)
  {
    this.a = ((String)paramMap.get("country"));
    this.b = ((String)paramMap.get("display_name"));
    this.f = a.b(paramMap, "uid");
    this.g = ((String)paramMap.get("referral_link"));
    Map localMap = (Map)paramMap.get("quota_info");
    this.c = a.b(localMap, "quota");
    this.d = a.b(localMap, "normal");
    this.e = a.b(localMap, "shared");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.p
 * JD-Core Version:    0.6.2
 */