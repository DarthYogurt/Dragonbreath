package com.flipdog.a.d.d;

import com.flipdog.a.c.b.e;
import com.flipdog.commons.a.ax;
import java.io.IOException;
import my.apache.http.client.ClientProtocolException;
import my.apache.http.client.methods.HttpPost;
import my.apache.http.entity.mime.MultipartEntity;

public class a extends com.flipdog.a.i.a
{
  private static String c = "https://test.com";

  public a()
  {
    super("Box.com");
  }

  private String a(String paramString1, String paramString2)
    throws IOException, com.flipdog.a.g.a
  {
    String str1 = b();
    String str2 = b(a(str1));
    String str3 = a(str2, str1, paramString1, paramString2);
    d(str3);
    String str4 = a(str2, com.flipdog.a.c.b.b.b(str3, "ic"), str1);
    a("RedirectUrl: %s", new Object[] { str4 });
    int i = str4.indexOf("code=");
    if (i != -1)
    {
      int j = i + 5;
      for (int k = j; ; k++)
      {
        if (k >= str4.length())
          return str4.substring(j);
        if (!Character.isLetterOrDigit(str4.charAt(k)))
          return str4.substring(j, k);
      }
    }
    throw new RuntimeException();
  }

  private String a(String paramString1, String paramString2, String paramString3)
    throws IOException
  {
    MultipartEntity localMultipartEntity = new MultipartEntity();
    com.flipdog.a.c.b.b.a(localMultipartEntity, "doconsent", "doconsent");
    com.flipdog.a.c.b.b.a(localMultipartEntity, "consent_accept", "Accept");
    com.flipdog.a.c.b.b.a(localMultipartEntity, "ic", paramString2);
    return a(paramString1, paramString3, localMultipartEntity);
  }

  private String a(String paramString1, String paramString2, String paramString3, String paramString4)
    throws ClientProtocolException, IOException
  {
    MultipartEntity localMultipartEntity = new MultipartEntity();
    com.flipdog.a.c.b.b.a(localMultipartEntity, "login", paramString3);
    com.flipdog.a.c.b.b.a(localMultipartEntity, "password", paramString4);
    com.flipdog.a.c.b.b.a(localMultipartEntity, "__login", "1");
    com.flipdog.a.c.b.b.a(localMultipartEntity, "dologin", "1");
    com.flipdog.a.c.b.b.a(localMultipartEntity, "submit1", "1");
    com.flipdog.a.c.b.b.a(localMultipartEntity, "skip_framework_login", "1");
    com.flipdog.a.c.b.b.a(localMultipartEntity, "login_or_register_mode", "login");
    com.flipdog.a.c.b.b.a(localMultipartEntity, "login_or_register_mode", "login");
    return a(paramString1, paramString2, localMultipartEntity);
  }

  private String a(String paramString1, String paramString2, MultipartEntity paramMultipartEntity)
    throws IOException
  {
    HttpPost localHttpPost = new HttpPost(paramString2);
    com.flipdog.a.c.b.b.a(paramMultipartEntity, "client_id", com.flipdog.a.d.c.a.a);
    com.flipdog.a.c.b.b.a(paramMultipartEntity, "response_type", "code");
    com.flipdog.a.c.b.b.a(paramMultipartEntity, "redirect_uri", c);
    com.flipdog.a.c.b.b.a(paramMultipartEntity, "scope", "[\"root_readwrite\"]");
    com.flipdog.a.c.b.b.a(paramMultipartEntity, "state", "authenticated");
    com.flipdog.a.c.b.b.a(paramMultipartEntity, "request_token", paramString1);
    localHttpPost.setEntity(paramMultipartEntity);
    return a(localHttpPost, paramString2);
  }

  private String b()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("https://api.box.com/oauth2/authorize?");
    localStringBuilder.append("response_type=code&");
    localStringBuilder.append("client_id=").append(com.flipdog.a.d.c.a.a).append("&");
    localStringBuilder.append("redirect_uri=").append(c).append("&");
    localStringBuilder.append("state=authenticated");
    return localStringBuilder.toString();
  }

  private com.flipdog.a.b.c.a c(String paramString)
    throws IOException, com.flipdog.a.g.a
  {
    HttpPost localHttpPost = new HttpPost("https://api.box.com/oauth2/token");
    MultipartEntity localMultipartEntity = new MultipartEntity();
    com.flipdog.a.c.b.b.a(localMultipartEntity, "grant_type", "authorization_code");
    com.flipdog.a.c.b.b.a(localMultipartEntity, "code", paramString);
    com.flipdog.a.c.b.b.a(localMultipartEntity, "client_id", com.flipdog.a.d.c.a.a);
    com.flipdog.a.c.b.b.a(localMultipartEntity, "client_secret", com.flipdog.a.d.c.a.b);
    com.flipdog.a.c.b.b.a(localMultipartEntity, "redirect_uri", c);
    localHttpPost.setEntity(localMultipartEntity);
    return new com.flipdog.a.b.c.a(a(localHttpPost, "https://api.box.com/oauth2/token"));
  }

  private void d(String paramString)
    throws com.flipdog.a.g.a
  {
    String str = com.flipdog.a.c.b.b.d(paramString, "platform_wrong_credentials");
    if (ax.a(str))
      return;
    throw new com.flipdog.a.g.a(str);
  }

  public com.flipdog.a.b.b a(com.flipdog.a.b.a parama)
    throws com.flipdog.a.g.b
  {
    super.a(parama);
    try
    {
      com.flipdog.a.b.c.a locala = c(a(parama.a, parama.b));
      a("Box.com account: %s", new Object[] { locala });
      com.flipdog.a.b.b localb = locala.a(parama.a, parama.b);
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
    return com.flipdog.a.d.c.a.d;
  }

  protected String b(String paramString)
  {
    int i = paramString.indexOf("request_token");
    if (i == -1)
      throw new RuntimeException();
    int j = i + 14;
    int k = -1;
    if (j >= paramString.length())
      throw new RuntimeException();
    if (Character.isLetterOrDigit(paramString.charAt(j)))
      if (k == -1)
        k = j;
    while (k == -1)
    {
      j++;
      break;
    }
    return paramString.substring(k, j);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.a.d.d.a
 * JD-Core Version:    0.6.2
 */