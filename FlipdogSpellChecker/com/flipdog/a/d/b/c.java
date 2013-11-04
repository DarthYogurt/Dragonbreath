package com.flipdog.a.d.b;

import com.flipdog.a.c.b.e;
import com.flipdog.a.c.c.d;
import com.flipdog.commons.a.as;
import java.util.List;
import my.apache.http.client.methods.HttpGet;
import org.json.JSONArray;
import org.json.JSONObject;

public class c extends com.flipdog.a.f.c
{
  private final com.flipdog.a.d.a a;

  public c(com.flipdog.a.d.a parama)
  {
    super("Box.com");
    this.a = parama;
  }

  private String a(com.flipdog.a.b.b.b paramb)
  {
    StringBuilder localStringBuilder = new StringBuilder("https://api.box.com/2.0/");
    localStringBuilder.append("folders/");
    localStringBuilder.append(paramb.d);
    localStringBuilder.append("/items?fields=id,name,modified_at,size&limit=1000");
    return localStringBuilder.toString();
  }

  protected List<com.flipdog.a.b.b.a> a(JSONObject paramJSONObject, com.flipdog.a.b.b.b paramb)
  {
    List localList = as.b();
    if (paramJSONObject.has("item_collection"))
      paramJSONObject = d.a(paramJSONObject, "item_collection");
    if (!paramJSONObject.has("entries"))
      return localList;
    JSONArray localJSONArray = d.b(paramJSONObject, "entries");
    for (int i = 0; ; i++)
    {
      if (i >= localJSONArray.length())
        return localList;
      com.flipdog.a.b.b.a locala = com.flipdog.a.d.a.a.a(d.a(localJSONArray, i), paramb);
      if (locala != null)
        localList.add(locala);
    }
  }

  public com.flipdog.a.b.b.b b(com.flipdog.a.b.b.b paramb)
    throws com.flipdog.a.g.b
  {
    a("Get content folder: %s", new Object[] { paramb });
    if (paramb == null)
      paramb = new com.flipdog.a.b.b.b("", "0");
    try
    {
      String str = a(paramb);
      HttpGet localHttpGet = new HttpGet(str);
      paramb.e = a(e.b(this.a, localHttpGet, str), paramb);
      return paramb;
    }
    catch (Exception localException)
    {
      e.b(localException);
    }
    return paramb;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.d.b.c
 * JD-Core Version:    0.6.2
 */