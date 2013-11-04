package com.flipdog.commons.actionbar;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;
import java.io.IOException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class h
{
  private static final String a = "http://schemas.android.com/apk/res/android";
  private static o b;

  private static int a(XmlPullParser paramXmlPullParser, String paramString1, String paramString2)
  {
    String str = paramXmlPullParser.getAttributeValue(paramString1, "logo");
    if (str == null)
      return 0;
    return Integer.parseInt(str.replace("@", ""));
  }

  private static XmlResourceParser a(Context paramContext)
  {
    try
    {
      XmlResourceParser localXmlResourceParser = paramContext.getAssets().openXmlResourceParser("AndroidManifest.xml");
      return localXmlResourceParser;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }

  public static o a()
  {
    try
    {
      if (b == null)
        b = b();
      o localo = b;
      return localo;
    }
    finally
    {
    }
  }

  private static o a(XmlResourceParser paramXmlResourceParser)
    throws XmlPullParserException, IOException
  {
    o localo = new o();
    paramXmlResourceParser.next();
    while (true)
    {
      if (paramXmlResourceParser.nextTag() == 1)
        return localo;
      if ("application".equals(paramXmlResourceParser.getName()))
        localo.a.a = a(paramXmlResourceParser, "http://schemas.android.com/apk/res/android", "logo");
    }
  }

  // ERROR //
  private static o b()
  {
    // Byte code:
    //   0: ldc 44
    //   2: invokestatic 106	com/flipdog/commons/i/b:a	(Ljava/lang/Class;)Ljava/lang/Object;
    //   5: checkcast 44	android/content/Context
    //   8: invokestatic 108	com/flipdog/commons/actionbar/h:a	(Landroid/content/Context;)Landroid/content/res/XmlResourceParser;
    //   11: astore_0
    //   12: aload_0
    //   13: invokestatic 110	com/flipdog/commons/actionbar/h:a	(Landroid/content/res/XmlResourceParser;)Lcom/flipdog/commons/actionbar/o;
    //   16: astore 4
    //   18: aload_0
    //   19: invokeinterface 113 1 0
    //   24: aload 4
    //   26: areturn
    //   27: astore_3
    //   28: new 58	java/lang/RuntimeException
    //   31: dup
    //   32: aload_3
    //   33: invokespecial 61	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   36: athrow
    //   37: astore_2
    //   38: aload_0
    //   39: invokeinterface 113 1 0
    //   44: aload_2
    //   45: athrow
    //   46: astore_1
    //   47: new 58	java/lang/RuntimeException
    //   50: dup
    //   51: aload_1
    //   52: invokespecial 61	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   55: athrow
    //
    // Exception table:
    //   from	to	target	type
    //   12	18	27	org/xmlpull/v1/XmlPullParserException
    //   12	18	37	finally
    //   28	37	37	finally
    //   47	56	37	finally
    //   12	18	46	java/io/IOException
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.actionbar.h
 * JD-Core Version:    0.6.2
 */