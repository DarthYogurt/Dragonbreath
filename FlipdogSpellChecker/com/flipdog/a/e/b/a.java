package com.flipdog.a.e.b;

import com.flipdog.a.c.b.e;
import com.flipdog.a.g.d;
import com.flipdog.commons.a.ax;
import java.io.IOException;
import my.apache.http.client.ClientProtocolException;
import my.apache.http.client.methods.HttpPost;
import my.apache.http.entity.mime.MultipartEntity;

public class a extends com.flipdog.a.i.a
{
  public a()
  {
    super("Dropbox");
  }

  private com.flipdog.a.b.b a(String paramString1, String paramString2, String paramString3)
    throws d
  {
    String str1 = com.flipdog.a.c.b.b.c(paramString3, "oauth_token");
    String str2 = com.flipdog.a.c.b.b.c(paramString3, "oauth_token_secret");
    if ((ax.a(str1)) || (ax.a(str2)))
      throw new d();
    com.flipdog.a.b.b localb = new com.flipdog.a.b.b(paramString1, paramString2);
    localb.c = str1;
    localb.d = str2;
    return localb;
  }

  private String a(String paramString1, String paramString2)
    throws ClientProtocolException, IOException, com.flipdog.a.g.a
  {
    HttpPost localHttpPost = new HttpPost(paramString1);
    String str1 = com.flipdog.a.c.b.b.b(paramString2, "t");
    String str2 = com.flipdog.a.c.b.b.b(paramString2, "s");
    if ((str1 == null) || (str2 == null))
      throw new com.flipdog.a.g.a();
    MultipartEntity localMultipartEntity = new MultipartEntity();
    com.flipdog.a.c.b.b.a(localMultipartEntity, "allow_access", "Allow");
    com.flipdog.a.c.b.b.a(localMultipartEntity, "t", str1);
    com.flipdog.a.c.b.b.a(localMultipartEntity, "k", com.flipdog.a.e.a.b.a);
    com.flipdog.a.c.b.b.a(localMultipartEntity, "s", str2);
    localHttpPost.setEntity(localMultipartEntity);
    return a(localHttpPost, paramString1);
  }

  private String a(String paramString1, String paramString2, String paramString3, String paramString4)
    throws ClientProtocolException, IOException
  {
    String str = com.flipdog.a.e.c.a.a(paramString1, com.flipdog.a.c.b.b.b(paramString2, "signup_data"));
    a("User: %s. Pwd: %s", new Object[] { paramString3, paramString4 });
    HttpPost localHttpPost = new HttpPost(str);
    MultipartEntity localMultipartEntity = new MultipartEntity();
    com.flipdog.a.c.b.b.a(localMultipartEntity, "special_login", "dauth");
    com.flipdog.a.c.b.b.a(localMultipartEntity, "login_email", paramString3);
    com.flipdog.a.c.b.b.a(localMultipartEntity, "login_password", paramString4);
    com.flipdog.a.c.b.b.a(localMultipartEntity, "t", com.flipdog.a.c.b.b.b(paramString2, "t"));
    localHttpPost.setEntity(localMultipartEntity);
    return a(localHttpPost, str);
  }

  private void b(String paramString)
    throws com.flipdog.a.g.a
  {
    int i = paramString.indexOf("<body>");
    if (i == -1);
    int k;
    int m;
    do
    {
      int j;
      do
      {
        return;
        j = paramString.indexOf("<div class=\"center\">", i + 4);
      }
      while (j == -1);
      k = j + "<div class=\"center\">".length();
      m = paramString.indexOf("</body>", k);
    }
    while (m - k > 300);
    throw new com.flipdog.a.g.a(paramString.substring(k, m).replace("<h1>", "").replace("</h1>", "").trim().replace("  ", ""));
  }

  private void c(String paramString)
    throws com.flipdog.a.g.a
  {
    int i = paramString.indexOf("<span class=\"error-message\">");
    if (i == -1)
      return;
    int j = i + "<span class=\"error-message\">".length();
    throw new com.flipdog.a.g.a(paramString.substring(j, paramString.indexOf("</span>", j)));
  }

  public com.flipdog.a.b.b a(com.flipdog.a.b.a parama)
    throws com.flipdog.a.g.b
  {
    super.a(parama);
    try
    {
      String str1 = com.flipdog.a.e.c.a.a();
      String str2 = a(com.flipdog.a.e.c.a.b(str1));
      String str3 = com.flipdog.a.e.c.a.a(str1);
      c(a(str3, str2, parama.a, parama.b));
      String str4 = a(str3);
      b(str4);
      String str5 = a(str3, str4);
      com.flipdog.a.b.b localb = a(parama.a, parama.b, str5);
      a("Login account: %s", new Object[] { localb });
      return localb;
    }
    catch (Exception localException)
    {
      e.b(localException);
      return null;
    }
    finally
    {
      if (this.b != null)
        e.a(this.b);
    }
  }

  protected String a()
  {
    return com.flipdog.a.e.a.b.d;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.e.b.a
 * JD-Core Version:    0.6.2
 */