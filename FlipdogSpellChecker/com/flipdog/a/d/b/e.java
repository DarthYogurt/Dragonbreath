package com.flipdog.a.d.b;

import com.flipdog.a.b.b.c;
import com.flipdog.a.c.c.d;
import com.flipdog.commons.diagnostic.Track;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import my.apache.http.HttpResponse;
import my.apache.http.StatusLine;
import my.apache.http.client.methods.HttpPost;
import my.apache.http.entity.mime.HttpMultipartMode;
import my.apache.http.entity.mime.MultipartEntity;
import org.json.JSONObject;

public class e extends com.flipdog.a.f.g
{
  private final com.flipdog.a.d.a a;

  public e(com.flipdog.a.d.a parama)
  {
    super("Box.com");
    this.a = parama;
  }

  private c a(HttpResponse paramHttpResponse, com.flipdog.a.b.b.b paramb)
    throws IOException, com.flipdog.a.g.g
  {
    JSONObject localJSONObject = d.a(com.flipdog.a.c.b.b.a(paramHttpResponse));
    try
    {
      c localc = (c)com.flipdog.a.d.a.a.a(d.a(d.b(localJSONObject, "entries"), 0), paramb);
      return localc;
    }
    catch (RuntimeException localRuntimeException)
    {
      Track.it(localRuntimeException);
    }
    throw new com.flipdog.a.g.g();
  }

  private HttpResponse b(File paramFile, String paramString, com.flipdog.a.b.b.b paramb, com.flipdog.a.a.b paramb1, com.flipdog.commons.l.b paramb2)
    throws com.flipdog.a.g.b
  {
    while (true)
    {
      try
      {
        long l = paramFile.length();
        if (paramString == null)
        {
          str1 = paramFile.getName();
          Object[] arrayOfObject = new Object[3];
          arrayOfObject[0] = str1;
          arrayOfObject[1] = Long.valueOf(l);
          arrayOfObject[2] = paramb.d;
          a("Upload file: %s. Size: %d. Path: %s", arrayOfObject);
          String str2 = String.format("%sfiles/content", new Object[] { "https://api.box.com/2.0/" });
          HttpPost localHttpPost = new HttpPost(str2);
          MultipartEntity localMultipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE, null, Charset.forName("UTF-8"));
          com.flipdog.a.c.b.b.a(localMultipartEntity, "folder_id", paramb.d);
          localMultipartEntity.addPart("filename", new com.flipdog.a.c.b.a(localHttpPost, paramFile, str1, paramb1, paramb2, "Box.com"));
          localHttpPost.setEntity(localMultipartEntity);
          HttpResponse localHttpResponse = com.flipdog.a.c.b.e.a(this.a, localHttpPost, str2);
          return localHttpResponse;
        }
      }
      catch (Exception localException)
      {
        com.flipdog.a.c.b.e.b(localException);
        return null;
      }
      String str1 = paramString;
    }
  }

  public c a(File paramFile, String paramString, com.flipdog.a.b.b.b paramb, com.flipdog.a.a.b paramb1, com.flipdog.commons.l.b paramb2)
    throws com.flipdog.a.g.b
  {
    try
    {
      b localb = new b(this.a);
      localb.a(paramFile, paramString, paramb);
      HttpResponse localHttpResponse1 = b(paramFile, paramString, paramb, paramb1, paramb2);
      int i = localHttpResponse1.getStatusLine().getStatusCode();
      if (i == 201)
        return a(localHttpResponse1, paramb);
      if (i == 409)
      {
        localb.a(paramFile, paramString, paramb);
        HttpResponse localHttpResponse2 = b(paramFile, paramString, paramb, paramb1, paramb2);
        if (localHttpResponse2.getStatusLine().getStatusCode() == 201)
          return a(localHttpResponse2, paramb);
      }
      throw new com.flipdog.a.g.g();
    }
    catch (Exception localException)
    {
      if (!com.flipdog.a.c.b.e.a(localException))
        com.flipdog.a.c.b.e.b(localException);
    }
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.d.b.e
 * JD-Core Version:    0.6.2
 */