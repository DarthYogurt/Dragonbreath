package com.flipdog.commons.m;

import com.flipdog.commons.a.as;
import com.flipdog.commons.diagnostic.Track;
import java.io.IOException;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class c
{
  private String a(int paramInt)
  {
    if (paramInt == 0)
      return "START_DOCUMENT";
    if (paramInt == 1)
      return "END_DOCUMENT";
    if (paramInt == 2)
      return "START_TAG";
    if (paramInt == 3)
      return "END_TAG";
    if (paramInt == 4)
      return "TEXT";
    return paramInt;
  }

  private void a(XmlPullParser paramXmlPullParser, int paramInt)
  {
    if (Track.isDisabled("XmlParser"))
      return;
    if (paramInt == 4)
    {
      Object[] arrayOfObject2 = new Object[2];
      arrayOfObject2[0] = a(paramInt);
      arrayOfObject2[1] = paramXmlPullParser.getText();
      Track.me("XmlParser", "eventType = %s, '%s'", arrayOfObject2);
      return;
    }
    Object[] arrayOfObject1 = new Object[2];
    arrayOfObject1[0] = a(paramInt);
    arrayOfObject1[1] = paramXmlPullParser.getName();
    Track.me("XmlParser", "eventType = %s, %s", arrayOfObject1);
  }

  protected void a(XmlPullParser paramXmlPullParser)
  {
  }

  protected void b(XmlPullParser paramXmlPullParser)
  {
  }

  public void c(XmlPullParser paramXmlPullParser)
    throws IOException, XmlPullParserException
  {
    int i = paramXmlPullParser.getEventType();
    if (i == 0)
      d(paramXmlPullParser);
    while (true)
    {
      i = paramXmlPullParser.next();
      a(paramXmlPullParser, i);
      if (i != 1)
        break;
      return;
      if (i == 1)
        e(paramXmlPullParser);
      else if (i == 2)
        a(paramXmlPullParser);
      else if (i == 3)
        f(paramXmlPullParser);
      else if (i == 4)
        b(paramXmlPullParser);
    }
  }

  protected void d(XmlPullParser paramXmlPullParser)
  {
  }

  protected void e(XmlPullParser paramXmlPullParser)
  {
  }

  protected void f(XmlPullParser paramXmlPullParser)
  {
  }

  protected Map<String, String> g(XmlPullParser paramXmlPullParser)
  {
    Map localMap = as.d();
    int i = 0;
    int j = paramXmlPullParser.getAttributeCount();
    while (true)
    {
      if (i >= j)
        return localMap;
      localMap.put(paramXmlPullParser.getAttributeName(i), paramXmlPullParser.getAttributeValue(i));
      i++;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.m.c
 * JD-Core Version:    0.6.2
 */