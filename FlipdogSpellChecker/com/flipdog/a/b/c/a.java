package com.flipdog.a.b.c;

import com.flipdog.a.b.b;
import com.flipdog.a.c.b.e;
import com.flipdog.a.c.c.d;
import org.json.JSONObject;

public class a
{
  public String a;
  public int b;
  public String c;
  public String d;

  public a()
  {
  }

  public a(String paramString)
    throws com.flipdog.a.g.a
  {
    a(paramString);
  }

  public b a(String paramString1, String paramString2)
  {
    b localb = new b(paramString1, paramString2);
    localb.c = this.a;
    localb.d = this.d;
    return localb;
  }

  public void a(String paramString)
    throws com.flipdog.a.g.a
  {
    JSONObject localJSONObject = d.a(paramString);
    e.a(localJSONObject);
    this.a = d.c(localJSONObject, "access_token");
    this.b = d.e(localJSONObject, "expires_in");
    this.c = d.c(localJSONObject, "token_type");
    this.d = d.d(localJSONObject, "refresh_token");
  }

  public String toString()
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = this.a;
    arrayOfObject[1] = Integer.valueOf(this.b);
    arrayOfObject[2] = this.c;
    arrayOfObject[3] = this.d;
    return String.format("Access: %s. Expires: %d. Type: %s. Refresh: %s", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.b.c.a
 * JD-Core Version:    0.6.2
 */