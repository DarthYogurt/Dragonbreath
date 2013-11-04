package com.flipdog.b;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Html.ImageGetter;
import android.text.Html.TagHandler;
import android.text.Layout.Alignment;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan.Standard;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TextAppearanceSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

class l
  implements ContentHandler
{
  private static final float[] a = { 1.5F, 1.4F, 1.3F, 1.2F, 1.1F, 1.0F };
  private static HashMap<String, Integer> g = b();
  private String b;
  private XMLReader c;
  private SpannableStringBuilder d;
  private Html.ImageGetter e;
  private Html.TagHandler f;

  public l(String paramString, Html.ImageGetter paramImageGetter, Html.TagHandler paramTagHandler, Parser paramParser)
  {
    this.b = paramString;
    this.d = new SpannableStringBuilder();
    this.e = paramImageGetter;
    this.f = paramTagHandler;
    this.c = paramParser;
  }

  private static Object a(Spanned paramSpanned, Class<?> paramClass)
  {
    Object[] arrayOfObject = paramSpanned.getSpans(0, paramSpanned.length(), paramClass);
    if (arrayOfObject.length == 0)
      return null;
    return arrayOfObject[(-1 + arrayOfObject.length)];
  }

  private static void a(SpannableStringBuilder paramSpannableStringBuilder)
  {
    int i = paramSpannableStringBuilder.length();
    if ((i >= 1) && (paramSpannableStringBuilder.charAt(i - 1) == '\n'))
      if ((i < 2) || (paramSpannableStringBuilder.charAt(i - 2) != '\n'));
    while (i == 0)
    {
      return;
      paramSpannableStringBuilder.append("\n");
      return;
    }
    paramSpannableStringBuilder.append("\n\n");
  }

  private static void a(SpannableStringBuilder paramSpannableStringBuilder, Class<?> paramClass, Object paramObject)
  {
    int i = paramSpannableStringBuilder.length();
    Object localObject = a(paramSpannableStringBuilder, paramClass);
    int j = paramSpannableStringBuilder.getSpanStart(localObject);
    paramSpannableStringBuilder.removeSpan(localObject);
    if (j != i)
      paramSpannableStringBuilder.setSpan(paramObject, j, i, 33);
  }

  private static void a(SpannableStringBuilder paramSpannableStringBuilder, Object paramObject)
  {
    int i = paramSpannableStringBuilder.length();
    paramSpannableStringBuilder.setSpan(paramObject, i, i, 17);
  }

  private static void a(SpannableStringBuilder paramSpannableStringBuilder, Attributes paramAttributes)
  {
    String str1 = paramAttributes.getValue("", "color");
    String str2 = paramAttributes.getValue("", "face");
    String str3 = paramAttributes.getValue("", "size");
    String str4 = paramAttributes.getValue("", "background");
    int i = paramSpannableStringBuilder.length();
    paramSpannableStringBuilder.setSpan(new e(str1, str2, str3, str4), i, i, 17);
  }

  private static void a(SpannableStringBuilder paramSpannableStringBuilder, Attributes paramAttributes, Html.ImageGetter paramImageGetter)
  {
    String str = paramAttributes.getValue("", "src");
    Drawable localDrawable = null;
    if (paramImageGetter != null)
      localDrawable = paramImageGetter.getDrawable(str);
    int i = paramSpannableStringBuilder.length();
    paramSpannableStringBuilder.append("￼");
    if (localDrawable != null)
      paramSpannableStringBuilder.setSpan(new ImageSpan(localDrawable, str), i, paramSpannableStringBuilder.length(), 33);
  }

  private void a(String paramString)
  {
    if (paramString.equalsIgnoreCase("br"))
      b(this.d);
    do
    {
      return;
      if (paramString.equalsIgnoreCase("p"))
      {
        a(this.d);
        d(this.d);
        return;
      }
      if (paramString.equalsIgnoreCase("div"))
      {
        a(this.d);
        d(this.d);
        return;
      }
      if (paramString.equalsIgnoreCase("strong"))
      {
        a(this.d, b.class, new StyleSpan(1));
        return;
      }
      if (paramString.equalsIgnoreCase("b"))
      {
        a(this.d, b.class, new StyleSpan(1));
        return;
      }
      if (paramString.equalsIgnoreCase("em"))
      {
        a(this.d, n.class, new StyleSpan(2));
        return;
      }
      if (paramString.equalsIgnoreCase("cite"))
      {
        a(this.d, n.class, new StyleSpan(2));
        return;
      }
      if (paramString.equalsIgnoreCase("dfn"))
      {
        a(this.d, n.class, new StyleSpan(2));
        return;
      }
      if (paramString.equalsIgnoreCase("i"))
      {
        a(this.d, n.class, new StyleSpan(2));
        return;
      }
      if (paramString.equalsIgnoreCase("big"))
      {
        a(this.d, f.class, new RelativeSizeSpan(1.25F));
        return;
      }
      if (paramString.equalsIgnoreCase("small"))
      {
        a(this.d, m.class, new RelativeSizeSpan(0.8F));
        return;
      }
      if (paramString.equalsIgnoreCase("font"))
      {
        c(this.d);
        return;
      }
      if (paramString.equalsIgnoreCase("blockquote"))
      {
        a(this.d);
        a(this.d, i.class, new QuoteSpan());
        return;
      }
      if (paramString.equalsIgnoreCase("tt"))
      {
        a(this.d, d.class, new TypefaceSpan("monospace"));
        return;
      }
      if (paramString.equalsIgnoreCase("a"))
      {
        e(this.d);
        return;
      }
      if (paramString.equalsIgnoreCase("u"))
      {
        a(this.d, o.class, new UnderlineSpan());
        return;
      }
      if (paramString.equalsIgnoreCase("sup"))
      {
        a(this.d, r.class, new SuperscriptSpan());
        return;
      }
      if (paramString.equalsIgnoreCase("sub"))
      {
        a(this.d, p.class, new SubscriptSpan());
        return;
      }
      if (paramString.equalsIgnoreCase("strike"))
      {
        a(this.d, h.class, new StrikethroughSpan());
        return;
      }
      if (paramString.equalsIgnoreCase("li"))
      {
        a(this.d, k.class, new BulletSpan());
        return;
      }
      if ((paramString.length() == 2) && (Character.toLowerCase(paramString.charAt(0)) == 'h') && (paramString.charAt(1) >= '1') && (paramString.charAt(1) <= '6'))
      {
        a(this.d);
        f(this.d);
        return;
      }
    }
    while (this.f == null);
    this.f.handleTag(false, paramString, this.d, this.c);
  }

  private void a(String paramString, Attributes paramAttributes)
  {
    if (!paramString.equalsIgnoreCase("br"))
    {
      if (!paramString.equalsIgnoreCase("p"))
        break label34;
      a(this.d);
      b(this.d, paramAttributes);
    }
    label34: 
    do
    {
      return;
      if (paramString.equalsIgnoreCase("div"))
      {
        a(this.d);
        b(this.d, paramAttributes);
        return;
      }
      if (paramString.equalsIgnoreCase("strong"))
      {
        a(this.d, new b(null));
        return;
      }
      if (paramString.equalsIgnoreCase("b"))
      {
        a(this.d, new b(null));
        return;
      }
      if (paramString.equalsIgnoreCase("em"))
      {
        a(this.d, new n(null));
        return;
      }
      if (paramString.equalsIgnoreCase("cite"))
      {
        a(this.d, new n(null));
        return;
      }
      if (paramString.equalsIgnoreCase("dfn"))
      {
        a(this.d, new n(null));
        return;
      }
      if (paramString.equalsIgnoreCase("i"))
      {
        a(this.d, new n(null));
        return;
      }
      if (paramString.equalsIgnoreCase("big"))
      {
        a(this.d, new f(null));
        return;
      }
      if (paramString.equalsIgnoreCase("small"))
      {
        a(this.d, new m(null));
        return;
      }
      if (paramString.equalsIgnoreCase("font"))
      {
        a(this.d, paramAttributes);
        return;
      }
      if (paramString.equalsIgnoreCase("blockquote"))
      {
        a(this.d);
        a(this.d, new i(null));
        return;
      }
      if (paramString.equalsIgnoreCase("tt"))
      {
        a(this.d, new d(null));
        return;
      }
      if (paramString.equalsIgnoreCase("a"))
      {
        c(this.d, paramAttributes);
        return;
      }
      if (paramString.equalsIgnoreCase("u"))
      {
        a(this.d, new o(null));
        return;
      }
      if (paramString.equalsIgnoreCase("sup"))
      {
        a(this.d, new r(null));
        return;
      }
      if (paramString.equalsIgnoreCase("sub"))
      {
        a(this.d, new p(null));
        return;
      }
      if (paramString.equalsIgnoreCase("strike"))
      {
        a(this.d, new h(null));
        return;
      }
      if (paramString.equalsIgnoreCase("li"))
      {
        a(this.d, new k(null));
        return;
      }
      if ((paramString.length() == 2) && (Character.toLowerCase(paramString.charAt(0)) == 'h') && (paramString.charAt(1) >= '1') && (paramString.charAt(1) <= '6'))
      {
        a(this.d);
        a(this.d, new c('ￏ' + paramString.charAt(1)));
        return;
      }
      if (paramString.equalsIgnoreCase("img"))
      {
        a(this.d, paramAttributes, this.e);
        return;
      }
    }
    while (this.f == null);
    this.f.handleTag(true, paramString, this.d, this.c);
  }

  private static Layout.Alignment b(String paramString)
  {
    if (paramString == null);
    String str;
    do
    {
      return null;
      str = paramString.trim();
      if (str.equalsIgnoreCase("center"))
        return Layout.Alignment.ALIGN_CENTER;
      if (str.equalsIgnoreCase("right"))
        return Layout.Alignment.ALIGN_OPPOSITE;
    }
    while (!str.equalsIgnoreCase("left"));
    return Layout.Alignment.ALIGN_NORMAL;
  }

  private static HashMap<String, Integer> b()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("aqua", Integer.valueOf(65535));
    localHashMap.put("black", Integer.valueOf(0));
    localHashMap.put("blue", Integer.valueOf(255));
    localHashMap.put("fuchsia", Integer.valueOf(16711935));
    localHashMap.put("green", Integer.valueOf(32768));
    localHashMap.put("grey", Integer.valueOf(8421504));
    localHashMap.put("lime", Integer.valueOf(65280));
    localHashMap.put("maroon", Integer.valueOf(8388608));
    localHashMap.put("navy", Integer.valueOf(128));
    localHashMap.put("olive", Integer.valueOf(8421376));
    localHashMap.put("purple", Integer.valueOf(8388736));
    localHashMap.put("red", Integer.valueOf(16711680));
    localHashMap.put("silver", Integer.valueOf(12632256));
    localHashMap.put("teal", Integer.valueOf(32896));
    localHashMap.put("white", Integer.valueOf(16777215));
    localHashMap.put("yellow", Integer.valueOf(16776960));
    return localHashMap;
  }

  private static void b(SpannableStringBuilder paramSpannableStringBuilder)
  {
    paramSpannableStringBuilder.append("\n");
  }

  private static void b(SpannableStringBuilder paramSpannableStringBuilder, Attributes paramAttributes)
  {
    String str = paramAttributes.getValue("", "align");
    int i = paramSpannableStringBuilder.length();
    paramSpannableStringBuilder.setSpan(new a(str), i, i, 17);
  }

  private static Integer c(String paramString)
  {
    try
    {
      Integer localInteger = Integer.valueOf(Integer.parseInt(paramString));
      return localInteger;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return null;
  }

  private static void c(SpannableStringBuilder paramSpannableStringBuilder)
  {
    Integer localInteger1 = null;
    int i = paramSpannableStringBuilder.length();
    Object localObject = a(paramSpannableStringBuilder, e.class);
    int j = paramSpannableStringBuilder.getSpanStart(localObject);
    paramSpannableStringBuilder.removeSpan(localObject);
    e locale;
    Float localFloat;
    Integer localInteger2;
    if (j != i)
    {
      locale = (e)localObject;
      if (!TextUtils.isEmpty(locale.a))
      {
        if (!locale.a.startsWith("@"))
          break label271;
        Resources localResources = Resources.getSystem();
        int n = localResources.getIdentifier(locale.a.substring(1), "color", "android");
        if (n != 0)
          paramSpannableStringBuilder.setSpan(new TextAppearanceSpan(null, 0, 0, localResources.getColorStateList(n), null), j, i, 33);
      }
      if (!TextUtils.isEmpty(locale.d))
      {
        int k = e(locale.d);
        if (k != -1)
          paramSpannableStringBuilder.setSpan(new BackgroundColorSpan(k | 0xFF000000), j, i, 33);
      }
      String str = locale.a();
      if (str != null)
        paramSpannableStringBuilder.setSpan(new TypefaceSpan(str), j, i, 33);
      if (!TextUtils.isEmpty(locale.c))
      {
        if (!locale.c.endsWith("em"))
          break label312;
        localFloat = d(locale.c.replace("em", ""));
        localInteger2 = null;
        label244: if (localInteger2 == null)
          break label463;
        paramSpannableStringBuilder.setSpan(new AbsoluteSizeSpan(localInteger2.intValue()), j, i, 33);
      }
    }
    label271: label312: label463: 
    do
    {
      return;
      int m = e(locale.a);
      if (m == -1)
        break;
      paramSpannableStringBuilder.setSpan(new ForegroundColorSpan(m | 0xFF000000), j, i, 33);
      break;
      if (locale.c.endsWith("px"))
      {
        localInteger2 = c(locale.c.replace("px", ""));
        localInteger1 = null;
        localFloat = null;
        break label244;
      }
      if (locale.c.endsWith("pt"))
      {
        localInteger1 = c(locale.c.replace("pt", ""));
        localInteger2 = null;
        localFloat = null;
        break label244;
      }
      if (locale.c.endsWith("%"))
      {
        localFloat = Float.valueOf(1.0F * c(locale.c.replace("%", "")).intValue() / 100.0F);
        localInteger2 = null;
        localInteger1 = null;
        break label244;
      }
      localInteger2 = c(locale.c);
      localInteger1 = null;
      localFloat = null;
      break label244;
      if (localInteger1 != null)
      {
        paramSpannableStringBuilder.setSpan(new AbsoluteSizeSpan(localInteger1.intValue()), j, i, 33);
        return;
      }
    }
    while (localFloat == null);
    paramSpannableStringBuilder.setSpan(new RelativeSizeSpan(localFloat.floatValue()), j, i, 33);
  }

  private static void c(SpannableStringBuilder paramSpannableStringBuilder, Attributes paramAttributes)
  {
    String str = paramAttributes.getValue("", "href");
    int i = paramSpannableStringBuilder.length();
    paramSpannableStringBuilder.setSpan(new s(str), i, i, 17);
  }

  private static Float d(String paramString)
  {
    try
    {
      Float localFloat = Float.valueOf(Float.parseFloat(paramString));
      return localFloat;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return null;
  }

  private static void d(SpannableStringBuilder paramSpannableStringBuilder)
  {
    int i = paramSpannableStringBuilder.length();
    Object localObject = a(paramSpannableStringBuilder, a.class);
    int j = paramSpannableStringBuilder.getSpanStart(localObject);
    paramSpannableStringBuilder.removeSpan(localObject);
    if (j != i)
    {
      a locala = (a)localObject;
      if (locala.a != null)
      {
        Layout.Alignment localAlignment = b(locala.a);
        if (localAlignment != null)
          paramSpannableStringBuilder.setSpan(new AlignmentSpan.Standard(localAlignment), j, i, 33);
      }
    }
  }

  private static int e(String paramString)
  {
    Integer localInteger = (Integer)g.get(paramString.toLowerCase());
    if (localInteger != null)
      return localInteger.intValue();
    try
    {
      int i = com.android.a.a.b.a(paramString, -1);
      return i;
    }
    catch (NumberFormatException localNumberFormatException)
    {
    }
    return -1;
  }

  private static void e(SpannableStringBuilder paramSpannableStringBuilder)
  {
    int i = paramSpannableStringBuilder.length();
    Object localObject = a(paramSpannableStringBuilder, s.class);
    int j = paramSpannableStringBuilder.getSpanStart(localObject);
    paramSpannableStringBuilder.removeSpan(localObject);
    if (j != i)
    {
      s locals = (s)localObject;
      if (locals.a != null)
        paramSpannableStringBuilder.setSpan(new URLSpan(locals.a), j, i, 33);
    }
  }

  private static void f(SpannableStringBuilder paramSpannableStringBuilder)
  {
    int i = paramSpannableStringBuilder.length();
    Object localObject = a(paramSpannableStringBuilder, c.class);
    int j = paramSpannableStringBuilder.getSpanStart(localObject);
    paramSpannableStringBuilder.removeSpan(localObject);
    while (true)
    {
      if ((i <= j) || (paramSpannableStringBuilder.charAt(i - 1) != '\n'))
      {
        if (j != i)
        {
          c localc = (c)localObject;
          paramSpannableStringBuilder.setSpan(new RelativeSizeSpan(a[c.a(localc)]), j, i, 33);
          paramSpannableStringBuilder.setSpan(new StyleSpan(1), j, i, 33);
        }
        return;
      }
      i--;
    }
  }

  public Spanned a()
  {
    this.c.setContentHandler(this);
    try
    {
      this.c.parse(new InputSource(new StringReader(this.b)));
      return this.d;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
    catch (SAXException localSAXException)
    {
      throw new RuntimeException(localSAXException);
    }
  }

  public void characters(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SAXException
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    if (i >= paramInt2)
    {
      this.d.append(localStringBuilder);
      return;
    }
    char c1 = paramArrayOfChar[(i + paramInt1)];
    int j;
    int m;
    int k;
    if ((c1 == ' ') || (c1 == '\n'))
    {
      j = localStringBuilder.length();
      if (j == 0)
      {
        m = this.d.length();
        if (m == 0)
        {
          k = 10;
          label81: if ((k != 32) && (k != 10))
            localStringBuilder.append(' ');
        }
      }
    }
    while (true)
    {
      i++;
      break;
      k = this.d.charAt(m - 1);
      break label81;
      k = localStringBuilder.charAt(j - 1);
      break label81;
      localStringBuilder.append(c1);
    }
  }

  public void endDocument()
    throws SAXException
  {
  }

  public void endElement(String paramString1, String paramString2, String paramString3)
    throws SAXException
  {
    a(paramString2);
  }

  public void endPrefixMapping(String paramString)
    throws SAXException
  {
  }

  public void ignorableWhitespace(char[] paramArrayOfChar, int paramInt1, int paramInt2)
    throws SAXException
  {
  }

  public void processingInstruction(String paramString1, String paramString2)
    throws SAXException
  {
  }

  public void setDocumentLocator(Locator paramLocator)
  {
  }

  public void skippedEntity(String paramString)
    throws SAXException
  {
  }

  public void startDocument()
    throws SAXException
  {
  }

  public void startElement(String paramString1, String paramString2, String paramString3, Attributes paramAttributes)
    throws SAXException
  {
    a(paramString2, paramAttributes);
  }

  public void startPrefixMapping(String paramString1, String paramString2)
    throws SAXException
  {
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.b.l
 * JD-Core Version:    0.6.2
 */