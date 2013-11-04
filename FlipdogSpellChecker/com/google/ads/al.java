package com.google.ads;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;

public class al
{
  private String a = "googleads.g.doubleclick.net";
  private String b = "/pagead/ads";
  private String[] c = { ".doubleclick.net", ".googleadservices.com", ".googlesyndication.com" };
  private ai d;
  private ah e = new ah();

  public al(ai paramai)
  {
    this.d = paramai;
  }

  private Uri a(Uri paramUri, Context paramContext, String paramString, boolean paramBoolean)
    throws am
  {
    try
    {
      if (paramUri.getQueryParameter("ms") != null)
        throw new am("Query parameter already exists: ms");
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
      throw new am("Provided Uri is not in a valid state");
    }
    if (paramBoolean);
    String str;
    for (Object localObject = this.d.a(paramContext, paramString); ; localObject = str)
    {
      return a(paramUri, "ms", (String)localObject);
      str = this.d.a(paramContext);
    }
  }

  private Uri a(Uri paramUri, String paramString1, String paramString2)
    throws UnsupportedOperationException
  {
    String str = paramUri.toString();
    int i = str.indexOf("&adurl");
    if (i == -1)
      i = str.indexOf("?adurl");
    if (i != -1)
      return Uri.parse(str.substring(0, i + 1) + paramString1 + "=" + paramString2 + "&" + str.substring(i + 1));
    return paramUri.buildUpon().appendQueryParameter(paramString1, paramString2).build();
  }

  public Uri a(Uri paramUri, Context paramContext)
    throws am
  {
    try
    {
      Uri localUri = a(paramUri, paramContext, paramUri.getQueryParameter("ai"), true);
      return localUri;
    }
    catch (UnsupportedOperationException localUnsupportedOperationException)
    {
    }
    throw new am("Provided Uri is not in a valid state");
  }

  public void a(String paramString)
  {
    this.c = paramString.split(",");
  }

  public boolean a(Uri paramUri)
  {
    if (paramUri == null)
      throw new NullPointerException();
    try
    {
      String str = paramUri.getHost();
      String[] arrayOfString = this.c;
      int i = arrayOfString.length;
      for (int j = 0; ; j++)
      {
        boolean bool1 = false;
        if (j < i)
        {
          boolean bool2 = str.endsWith(arrayOfString[j]);
          if (bool2)
            bool1 = true;
        }
        else
        {
          return bool1;
        }
      }
    }
    catch (NullPointerException localNullPointerException)
    {
    }
    return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.al
 * JD-Core Version:    0.6.2
 */