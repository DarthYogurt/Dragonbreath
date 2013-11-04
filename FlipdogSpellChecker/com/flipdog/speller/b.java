package com.flipdog.speller;

import com.flipdog.commons.a.ax;
import com.flipdog.commons.m.c;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.xmlpull.v1.XmlPullParser;

public class b extends c
{
  private ArrayList<s> a = new ArrayList();
  private s b;

  public List<s> a()
  {
    return this.a;
  }

  protected void a(XmlPullParser paramXmlPullParser)
  {
    String str1 = paramXmlPullParser.getName();
    Map localMap = g(paramXmlPullParser);
    if (str1.equals("c"))
    {
      this.b = null;
      String str2 = (String)localMap.get("o");
      String str3 = (String)localMap.get("l");
      String str4 = (String)localMap.get("s");
      if ((str2 == null) || (str3 == null) || (str4 == null))
        throw new RuntimeException(String.format("Unexpected spell checker response: o = %s, l = %s, s = %s", new Object[] { str2, str3, str4 }));
      this.b = new s();
      this.b.a = Integer.parseInt(str2);
      this.b.b = Integer.parseInt(str3);
      this.b.c = Integer.parseInt(str4);
    }
  }

  protected void b(XmlPullParser paramXmlPullParser)
  {
    String str = paramXmlPullParser.getText();
    if (this.b != null)
    {
      this.b.d = ax.a(str, "\t");
      this.a.add(this.b);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.b
 * JD-Core Version:    0.6.2
 */