package com.flipdog.speller;

import android.content.Context;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

public class a
{
  public static List<s> a(Context paramContext, String paramString)
    throws IOException, XmlPullParserException, ParserConfigurationException
  {
    return a(paramContext, paramString, null);
  }

  public static List<s> a(Context paramContext, String paramString1, String paramString2)
    throws IOException, XmlPullParserException, ParserConfigurationException
  {
    String str = b(paramContext, paramString1);
    URL localURL;
    if (paramString2 == null)
      localURL = new URL("https://www.google.com/tbproxy/spell?lang=en&hl=en");
    while (true)
    {
      URLConnection localURLConnection = localURL.openConnection();
      localURLConnection.setDoOutput(true);
      OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(localURLConnection.getOutputStream());
      localOutputStreamWriter.write(str.toString());
      localOutputStreamWriter.close();
      InputStream localInputStream = localURLConnection.getInputStream();
      try
      {
        List localList = a(localInputStream);
        return localList;
        localURL = new URL("https://www.google.com/tbproxy/spell?lang=" + paramString2);
      }
      finally
      {
        localInputStream.close();
      }
    }
  }

  private static List<s> a(InputStream paramInputStream)
    throws XmlPullParserException, IOException
  {
    XmlPullParser localXmlPullParser = XmlPullParserFactory.newInstance().newPullParser();
    localXmlPullParser.setInput(paramInputStream, null);
    b localb = new b();
    localb.c(localXmlPullParser);
    return localb.a();
  }

  private static String b(Context paramContext, String paramString)
    throws FactoryConfigurationError, ParserConfigurationException, IOException
  {
    return com.flipdog.commons.m.b.b(c(paramContext, paramString));
  }

  private static Document c(Context paramContext, String paramString)
    throws FactoryConfigurationError, ParserConfigurationException
  {
    return SpellerNative.createRequestNative(paramContext, paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.a
 * JD-Core Version:    0.6.2
 */