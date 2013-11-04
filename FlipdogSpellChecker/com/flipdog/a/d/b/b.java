package com.flipdog.a.d.b;

import com.flipdog.a.c.c.d;
import com.flipdog.a.g.g;
import my.apache.http.HttpResponse;
import my.apache.http.StatusLine;
import my.apache.http.client.methods.HttpDelete;
import my.apache.http.client.methods.HttpPost;
import my.apache.http.entity.StringEntity;
import org.json.JSONObject;

public class b extends com.flipdog.a.f.e
{
  private final com.flipdog.a.d.a a;

  public b(com.flipdog.a.d.a parama)
  {
    super("Box.com");
    this.a = parama;
  }

  public com.flipdog.a.b.b.b a(com.flipdog.a.b.b.b paramb, String paramString)
    throws com.flipdog.a.g.b
  {
    a("Create folder: %s. Parent: %s", new Object[] { paramString, paramb });
    while (true)
    {
      JSONObject localJSONObject;
      try
      {
        String str = String.format("%sfolders", new Object[] { "https://api.box.com/2.0/" });
        HttpPost localHttpPost = new HttpPost(str);
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramString;
        arrayOfObject[1] = paramb.d;
        localHttpPost.setEntity(new StringEntity(String.format("{\"name\":\"%s\", \"parent\": {\"id\": \"%s\"}}", arrayOfObject), "UTF-8"));
        HttpResponse localHttpResponse = com.flipdog.a.c.b.e.a(this.a, localHttpPost, str);
        int i = localHttpResponse.getStatusLine().getStatusCode();
        if ((i != 201) && (i != 409))
          break;
        localJSONObject = d.a(com.flipdog.a.c.b.b.a(localHttpResponse));
        com.flipdog.a.c.b.e.b(localJSONObject);
        if (i != 409)
          break label213;
        localb = com.flipdog.a.c.b.b(new c(this.a).b(paramb.e()).e, paramString);
        if (localb != null)
          localb.c = paramb;
        if (localb == null)
          throw new g();
      }
      catch (Exception localException)
      {
        com.flipdog.a.c.b.e.b(localException);
        localb = null;
      }
      return localb;
      label213: com.flipdog.a.b.b.b localb = (com.flipdog.a.b.b.b)com.flipdog.a.d.a.a.a(localJSONObject, paramb);
    }
    throw new g();
  }

  protected com.flipdog.a.f.c a()
  {
    return new c(this.a);
  }

  public void a(com.flipdog.a.b.b.b paramb)
    throws com.flipdog.a.g.b
  {
    a("Delete folder: %s", new Object[] { paramb });
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = "https://api.box.com/2.0/";
      arrayOfObject[1] = paramb.d;
      String str = String.format("%sfolders/%s?recursive=true", arrayOfObject);
      HttpDelete localHttpDelete = new HttpDelete(str);
      if (com.flipdog.a.c.b.e.a(this.a, localHttpDelete, str).getStatusLine().getStatusCode() != 204)
        throw new g();
    }
    catch (Exception localException)
    {
      com.flipdog.a.c.b.e.b(localException);
    }
  }

  public void b(com.flipdog.a.b.b.c paramc)
    throws com.flipdog.a.g.b
  {
    a("Delete file: %s", new Object[] { paramc });
    try
    {
      Object[] arrayOfObject = new Object[2];
      arrayOfObject[0] = "https://api.box.com/2.0/";
      arrayOfObject[1] = paramc.d;
      String str = String.format("%sfiles/%s", arrayOfObject);
      HttpDelete localHttpDelete = new HttpDelete(str);
      if (com.flipdog.a.c.b.e.a(this.a, localHttpDelete, str).getStatusLine().getStatusCode() != 204)
        throw new g();
    }
    catch (Exception localException)
    {
      com.flipdog.a.c.b.e.b(localException);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.d.b.b
 * JD-Core Version:    0.6.2
 */