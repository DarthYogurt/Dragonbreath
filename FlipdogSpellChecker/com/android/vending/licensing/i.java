package com.android.vending.licensing;

import android.content.Context;
import android.util.Log;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

public class i
  implements k
{
  private static final String a = "ServerManagedPolicy";
  private static final String b = "com.android.vending.licensing.ServerManagedPolicy";
  private static final String c = "lastResponse";
  private static final String d = "validityTimestamp";
  private static final String e = "retryUntil";
  private static final String f = "maxRetries";
  private static final String g = "retryCount";
  private static final String h = "0";
  private static final String i = "0";
  private static final String j = "0";
  private static final String k = "0";
  private static final long l = 60000L;
  private long m;
  private long n;
  private long o;
  private long p;
  private long q = 0L;
  private f r;
  private h s;

  public i(Context paramContext, d paramd)
  {
    this.s = new h(paramContext.getSharedPreferences("com.android.vending.licensing.ServerManagedPolicy", 0), paramd);
    this.r = f.valueOf(this.s.b("lastResponse", f.c.toString()));
    this.m = Long.parseLong(this.s.b("validityTimestamp", "0"));
    this.n = Long.parseLong(this.s.b("retryUntil", "0"));
    this.o = Long.parseLong(this.s.b("maxRetries", "0"));
    this.p = Long.parseLong(this.s.b("retryCount", "0"));
  }

  private void a(long paramLong)
  {
    this.p = paramLong;
    this.s.a("retryCount", Long.toString(paramLong));
  }

  private void a(f paramf)
  {
    this.q = System.currentTimeMillis();
    this.r = paramf;
    this.s.a("lastResponse", paramf.toString());
  }

  private void a(String paramString)
  {
    try
    {
      Long localLong2 = Long.valueOf(Long.parseLong(paramString));
      localLong1 = localLong2;
      this.m = localLong1.longValue();
      this.s.a("validityTimestamp", paramString);
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
      {
        Log.w("ServerManagedPolicy", "License validity timestamp (VT) missing, caching for a minute");
        Long localLong1 = Long.valueOf(60000L + System.currentTimeMillis());
        paramString = Long.toString(localLong1.longValue());
      }
    }
  }

  private void b(String paramString)
  {
    try
    {
      Long localLong2 = Long.valueOf(Long.parseLong(paramString));
      localLong1 = localLong2;
      this.n = localLong1.longValue();
      this.s.a("retryUntil", paramString);
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
      {
        Log.w("ServerManagedPolicy", "License retry timestamp (GT) missing, grace period disabled");
        paramString = "0";
        Long localLong1 = Long.valueOf(0L);
      }
    }
  }

  private void c(String paramString)
  {
    try
    {
      Long localLong2 = Long.valueOf(Long.parseLong(paramString));
      localLong1 = localLong2;
      this.o = localLong1.longValue();
      this.s.a("maxRetries", paramString);
      return;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      while (true)
      {
        Log.w("ServerManagedPolicy", "Licence retry count (GR) missing, grace period disabled");
        paramString = "0";
        Long localLong1 = Long.valueOf(0L);
      }
    }
  }

  private Map<String, String> d(String paramString)
  {
    HashMap localHashMap = new HashMap();
    try
    {
      Iterator localIterator = URLEncodedUtils.parse(new URI("?" + paramString), "UTF-8").iterator();
      while (true)
      {
        if (!localIterator.hasNext())
          return localHashMap;
        NameValuePair localNameValuePair = (NameValuePair)localIterator.next();
        localHashMap.put(localNameValuePair.getName(), localNameValuePair.getValue());
      }
    }
    catch (URISyntaxException localURISyntaxException)
    {
      Log.w("ServerManagedPolicy", "Invalid syntax error while decoding extras data from server.");
    }
    return localHashMap;
  }

  public long a()
  {
    return this.p;
  }

  public void a(f paramf, u paramu)
  {
    if (paramf != f.c)
    {
      a(0L);
      if (paramf != f.a)
        break label104;
      Map localMap = d(paramu.g);
      this.r = paramf;
      a((String)localMap.get("VT"));
      b((String)localMap.get("GT"));
      c((String)localMap.get("GR"));
    }
    while (true)
    {
      a(paramf);
      this.s.a();
      return;
      a(1L + this.p);
      break;
      label104: if (paramf == f.b)
      {
        a("0");
        b("0");
        c("0");
      }
    }
  }

  public long b()
  {
    return this.m;
  }

  public long c()
  {
    return this.n;
  }

  public long d()
  {
    return this.o;
  }

  public boolean e()
  {
    long l1 = System.currentTimeMillis();
    if (this.r == f.a)
    {
      if (l1 > this.m);
    }
    else
    {
      do
      {
        return true;
        if ((this.r != f.c) || (l1 >= 60000L + this.q))
          break;
      }
      while ((l1 <= this.n) || (this.p <= this.o));
      return false;
    }
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.android.vending.licensing.i
 * JD-Core Version:    0.6.2
 */