package com.a.a.b;

import com.a.a.c.h;
import com.a.a.d;
import com.a.a.m;
import java.util.Locale;
import java.util.Map;

public class g extends k
{
  public g(i parami, f paramf)
  {
    super(parami, paramf);
  }

  public g(i parami, f paramf, c paramc)
  {
    super(parami, paramf, paramc);
  }

  private Map<String, String> b(String paramString)
    throws com.a.a.c.i
  {
    com.a.a.k localk = com.a.a.k.a;
    String str = j();
    String[] arrayOfString = new String[2];
    arrayOfString[0] = "locale";
    arrayOfString[1] = e().toString();
    Map localMap = m.b(m.b(localk, str, paramString, 1, arrayOfString, this).b);
    if ((!localMap.containsKey("oauth_token")) || (!localMap.containsKey("oauth_token_secret")))
      throw new h("Did not get tokens from Dropbox");
    a(new c((String)localMap.get("oauth_token"), (String)localMap.get("oauth_token_secret")));
    return localMap;
  }

  public l a()
    throws com.a.a.c.i
  {
    return a(null);
  }

  public l a(String paramString)
    throws com.a.a.c.i
  {
    b("/oauth/request_token");
    c localc = c();
    s locals = new s(localc.a, localc.b);
    String[] arrayOfString;
    if (paramString != null)
    {
      arrayOfString = new String[6];
      arrayOfString[0] = "oauth_token";
      arrayOfString[1] = locals.a;
      arrayOfString[2] = "oauth_callback";
      arrayOfString[3] = paramString;
      arrayOfString[4] = "locale";
      arrayOfString[5] = e().toString();
    }
    while (true)
    {
      return new l(m.a(l(), 1, "/oauth/authorize", arrayOfString), locals, null);
      arrayOfString = new String[4];
      arrayOfString[0] = "oauth_token";
      arrayOfString[1] = locals.a;
      arrayOfString[2] = "locale";
      arrayOfString[3] = e().toString();
    }
  }

  public String a(s params)
    throws com.a.a.c.i
  {
    a(params);
    return (String)b("/oauth/access_token").get("uid");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.a.a.b.g
 * JD-Core Version:    0.6.2
 */