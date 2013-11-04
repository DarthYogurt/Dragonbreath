package com.flipdog.ads.c;

import com.flipdog.commons.m.c;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;

public class a extends c
{
  private String a;
  private ArrayList<String> b = new ArrayList();

  public ArrayList<String> a()
  {
    return this.b;
  }

  protected void a(XmlPullParser paramXmlPullParser)
  {
    this.a = paramXmlPullParser.getName();
  }

  protected void b(XmlPullParser paramXmlPullParser)
  {
    String str = paramXmlPullParser.getText();
    if ("postalcode".equals(this.a))
      this.b.add(str);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.ads.c.a
 * JD-Core Version:    0.6.2
 */