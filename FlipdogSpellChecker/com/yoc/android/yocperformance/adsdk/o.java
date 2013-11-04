package com.yoc.android.yocperformance.adsdk;

import android.net.Uri;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

class o extends DefaultHandler
{
  private static final Pattern a = Pattern.compile("\\*YAP");
  private static final Pattern b = Pattern.compile("URL:(.+)");
  private final Ad c = new Ad(null);

  private static String a(Pattern paramPattern, String paramString)
  {
    Matcher localMatcher = paramPattern.matcher(paramString);
    if (localMatcher.find())
      return localMatcher.group(1);
    return null;
  }

  private void a(String paramString)
  {
    if ((paramString == null) || (paramString.length() == 0))
      return;
    String[] arrayOfString = paramString.split(";");
    int i = arrayOfString.length;
    int j = 0;
    label25: String str1;
    if (j < i)
    {
      str1 = arrayOfString[j];
      if (!a.matcher(str1).matches())
        break label70;
      Ad.a(this.c, true);
      Ad.b("asd");
    }
    while (true)
    {
      j++;
      break label25;
      break;
      label70: String str2 = a(b, str1);
      if (str2 != null)
        Ad.a(this.c).add(Uri.parse(str2));
      if (str1.startsWith("http://"))
      {
        Ad.a(this.c).add(Uri.parse(str1));
        Ad.b(str1);
      }
    }
  }

  public Ad a()
  {
    return this.c;
  }

  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    try
    {
      if (paramString2.equals("a"))
      {
        if ("AdMobileLink".equals(paramAttributes.getValue("id")))
          Ad.a(this.c, Uri.parse(paramAttributes.getValue("href")));
      }
      else if ((paramString2.equals("img")) && ("AdMobileImg".equals(paramAttributes.getValue("id"))))
      {
        Ad.b(this.c, Uri.parse(paramAttributes.getValue("src")));
        a(paramAttributes.getValue("alt"));
        return;
      }
    }
    catch (NullPointerException localNullPointerException)
    {
      throw new SAXException("Invalid snipplet format");
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.o
 * JD-Core Version:    0.6.2
 */