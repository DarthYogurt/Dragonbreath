package com.flipdog.commons.a;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.commons.i.b;
import java.io.IOException;
import java.io.InputStream;

public class ar
{
  public static Uri a(Resources paramResources, int paramInt)
  {
    return Uri.parse(String.format("android.resource://%s/%s/%s", new Object[] { paramResources.getResourcePackageName(paramInt), paramResources.getResourceTypeName(paramInt), paramResources.getResourceEntryName(paramInt) }));
  }

  public static String a(int paramInt1, int paramInt2, Object paramObject)
  {
    Context localContext = (Context)b.a(Context.class);
    String[] arrayOfString1 = localContext.getResources().getStringArray(paramInt1);
    String[] arrayOfString2 = localContext.getResources().getStringArray(paramInt2);
    for (int i = 0; ; i++)
    {
      if (i >= arrayOfString1.length)
        throw new RuntimeException("Can't find label for value: " + paramObject);
      if (arrayOfString1[i].equals(paramObject))
        return arrayOfString2[i];
    }
  }

  public static String a(Context paramContext, int paramInt)
  {
    InputStream localInputStream = paramContext.getResources().openRawResource(paramInt);
    try
    {
      String str = n.a(localInputStream, "utf-8");
      return str;
    }
    catch (IOException localIOException)
    {
      Track.it(localIOException);
    }
    return null;
  }

  public static String a(Context paramContext, int paramInt, String paramString)
  {
    InputStream localInputStream = paramContext.getResources().openRawResource(paramInt);
    try
    {
      String str = n.a(localInputStream, paramString);
      return str;
    }
    catch (IOException localIOException)
    {
      Track.it(localIOException);
    }
    return null;
  }

  public static String b(Context paramContext, int paramInt)
  {
    return a(paramContext, paramInt, "utf-8");
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.ar
 * JD-Core Version:    0.6.2
 */