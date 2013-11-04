package com.flipdog.errors;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import com.flipdog.commons.a.ay;
import com.flipdog.errors.b.a;
import java.util.Locale;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class d
{
  public static c a(String paramString)
  {
    c localc = new c();
    localc.a = paramString;
    if ((localc.a == null) || (localc.a.equals("")))
      localc.a = "Error was not specified.";
    a(localc, false);
    return localc;
  }

  public static c a(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    c localc = new c();
    localc.n = paramString1;
    localc.m = paramString2;
    localc.b = paramString3;
    localc.a = paramString4;
    if ((localc.a == null) || (localc.a.equals("")))
      localc.a = "Error was not specified.";
    a(localc, true);
    return localc;
  }

  public static String a()
  {
    return ((Context)com.flipdog.commons.i.b.a(Context.class)).getPackageName();
  }

  public static String a(c paramc)
    throws Exception
  {
    Document localDocument = com.flipdog.commons.m.b.a();
    Element localElement = localDocument.createElement("report");
    a(localDocument, localElement, "board", paramc.f);
    a(localDocument, localElement, "brand", paramc.g);
    a(localDocument, localElement, "details", paramc.b);
    a(localDocument, localElement, "domain", paramc.m);
    a(localDocument, localElement, "locale", paramc.k);
    a(localDocument, localElement, "model", paramc.h);
    a(localDocument, localElement, "package_name", paramc.i);
    a(localDocument, localElement, "package_version", paramc.j);
    a(localDocument, localElement, "protocol", paramc.n);
    a(localDocument, localElement, "stack", paramc.a);
    a(localDocument, localElement, "uuid", paramc.l);
    a(localDocument, localElement, "version_incremental", paramc.d);
    a(localDocument, localElement, "version_release", paramc.c);
    a(localDocument, localElement, "version_sdk", paramc.e);
    localDocument.appendChild(localElement);
    return com.flipdog.commons.m.b.b(localDocument);
  }

  private static Element a(Document paramDocument, Element paramElement, String paramString1, String paramString2)
  {
    Element localElement = paramDocument.createElement(paramString1);
    com.flipdog.commons.m.b.a(paramDocument, localElement, paramString2);
    paramElement.appendChild(localElement);
    return localElement;
  }

  private static void a(c paramc, boolean paramBoolean)
  {
    paramc.c = Build.VERSION.RELEASE;
    paramc.d = Build.VERSION.INCREMENTAL;
    paramc.e = Build.VERSION.SDK;
    paramc.f = Build.BOARD;
    paramc.g = Build.BRAND;
    paramc.h = Build.MODEL;
    if (paramBoolean)
      paramc.l = b();
    paramc.j = c().a;
    try
    {
      paramc.i = a();
      try
      {
        label70: paramc.k = d();
        return;
      }
      catch (Exception localException2)
      {
      }
    }
    catch (Exception localException1)
    {
      break label70;
    }
  }

  private static String b()
  {
    try
    {
      String str = a.a();
      return str;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return null;
  }

  public static StringBuilder b(c paramc)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramc.n == null)
      localStringBuilder.append(paramc.a);
    while (true)
    {
      if (paramc.b != null)
      {
        localStringBuilder.append("\n\nDetails: ");
        localStringBuilder.append(paramc.b);
      }
      localStringBuilder.append("\n\n");
      localStringBuilder.append(c(paramc));
      return localStringBuilder;
      if (paramc.m == null)
        paramc.m = "";
      localStringBuilder.append("Protocol: " + paramc.n);
      localStringBuilder.append("\nDomain: " + paramc.m);
      localStringBuilder.append("\n\n");
      localStringBuilder.append(paramc.a);
    }
  }

  private static ay c()
  {
    return ay.a();
  }

  private static StringBuffer c(c paramc)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    localStringBuffer.append("Version {" + paramc.j + "}");
    localStringBuffer.append("\nVersion.Release {" + paramc.c + "}");
    localStringBuffer.append("\nVersion.Incremental {" + paramc.d + "}");
    localStringBuffer.append("\nVersion.SDK {" + paramc.e + "}");
    localStringBuffer.append("\nBoard {" + paramc.f + "}");
    localStringBuffer.append("\nBrand {" + paramc.g + "}");
    localStringBuffer.append("\nModel {" + paramc.h + "}");
    localStringBuffer.append("\nPackage {" + paramc.i + "}");
    localStringBuffer.append("\nLocale {" + paramc.k + "}");
    return localStringBuffer;
  }

  private static String d()
  {
    return Locale.getDefault().getDisplayName();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.errors.d
 * JD-Core Version:    0.6.2
 */