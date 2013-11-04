package com.a.a;

import java.util.Map;

public final class w
{
  private final String a;
  private final long b;

  public w(Map<String, Object> paramMap)
  {
    this.a = ((String)paramMap.get("upload_id"));
    this.b = ((Long)paramMap.get("offset")).longValue();
  }

  public String a()
  {
    return this.a;
  }

  public long b()
  {
    return this.b;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.w
 * JD-Core Version:    0.6.2
 */