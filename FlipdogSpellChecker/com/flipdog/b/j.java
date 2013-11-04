package com.flipdog.b;

import android.text.Html.ImageGetter;
import android.text.Html.TagHandler;
import android.text.Layout.Alignment;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.AlignmentSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.ParagraphStyle;
import android.text.style.QuoteSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import com.flipdog.commons.a.as;
import java.util.Set;
import org.ccil.cowan.tagsoup.Parser;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;

public class j
{
  public static Spanned a(String paramString)
  {
    return a(paramString, null, null);
  }

  public static Spanned a(String paramString, Html.ImageGetter paramImageGetter, Html.TagHandler paramTagHandler)
  {
    Parser localParser = new Parser();
    try
    {
      localParser.setProperty("http://www.ccil.org/~cowan/tagsoup/properties/schema", q.a());
      return new l(paramString, paramImageGetter, paramTagHandler, localParser).a();
    }
    catch (SAXNotRecognizedException localSAXNotRecognizedException)
    {
      throw new RuntimeException(localSAXNotRecognizedException);
    }
    catch (SAXNotSupportedException localSAXNotSupportedException)
    {
      throw new RuntimeException(localSAXNotSupportedException);
    }
  }

  public static String a(Spanned paramSpanned)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    a(localStringBuilder, paramSpanned);
    return localStringBuilder.toString();
  }

  public static String a(CharSequence paramCharSequence)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    a(localStringBuilder, paramCharSequence, 0, paramCharSequence.length());
    return localStringBuilder.toString();
  }

  private static void a(StringBuilder paramStringBuilder, Spanned paramSpanned)
  {
    int i = paramSpanned.length();
    int k;
    ParagraphStyle[] arrayOfParagraphStyle;
    String str;
    int m;
    int n;
    for (int j = 0; ; j = k)
    {
      if (j >= paramSpanned.length())
        return;
      k = paramSpanned.nextSpanTransition(j, i, ParagraphStyle.class);
      arrayOfParagraphStyle = (ParagraphStyle[])paramSpanned.getSpans(j, k, ParagraphStyle.class);
      str = " ";
      m = 0;
      n = 0;
      if (m < arrayOfParagraphStyle.length)
        break;
      if (n != 0)
        paramStringBuilder.append("<div " + str + ">");
      b(paramStringBuilder, paramSpanned, j, k);
      if (n != 0)
        paramStringBuilder.append("</div>");
    }
    Layout.Alignment localAlignment;
    if ((arrayOfParagraphStyle[m] instanceof AlignmentSpan))
    {
      localAlignment = ((AlignmentSpan)arrayOfParagraphStyle[m]).getAlignment();
      n = 1;
      if (localAlignment != Layout.Alignment.ALIGN_CENTER)
        break label186;
      str = "align=\"center\" " + str;
    }
    while (true)
    {
      m++;
      break;
      label186: if (localAlignment == Layout.Alignment.ALIGN_OPPOSITE)
        str = "align=\"right\" " + str;
      else
        str = "align=\"left\" " + str;
    }
  }

  public static void a(StringBuilder paramStringBuilder, Spanned paramSpanned, int paramInt1, int paramInt2)
  {
    BulletSpan[] arrayOfBulletSpan = (BulletSpan[])paramSpanned.getSpans(paramInt1, paramInt2, BulletSpan.class);
    while (true)
    {
      if (paramInt1 >= paramInt2)
      {
        a(paramStringBuilder, paramSpanned, arrayOfBulletSpan, paramInt2);
        return;
      }
      int i = paramSpanned.nextSpanTransition(paramInt1, paramInt2, BulletSpan.class);
      a(paramStringBuilder, paramSpanned, arrayOfBulletSpan, paramInt1);
      c(paramStringBuilder, paramSpanned, paramInt1, i);
      paramInt1 = i;
    }
  }

  private static void a(StringBuilder paramStringBuilder, Spanned paramSpanned, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    Set localSet = as.c();
    int i = paramInt1;
    if (i >= paramInt2);
    for (int i3 = 0; ; i3++)
    {
      if (i3 >= paramInt3)
      {
        return;
        int j = paramSpanned.nextSpanTransition(i, paramInt2, CharacterStyle.class);
        CharacterStyle[] arrayOfCharacterStyle = (CharacterStyle[])paramSpanned.getSpans(i, j, CharacterStyle.class);
        int k = i;
        int m = 0;
        int i1;
        if (m >= arrayOfCharacterStyle.length)
        {
          a(paramStringBuilder, paramSpanned, k, j);
          i1 = -1 + arrayOfCharacterStyle.length;
          if (i1 >= 0)
            break label679;
          i = j;
          break;
        }
        if ((arrayOfCharacterStyle[m] instanceof StyleSpan))
        {
          int n = ((StyleSpan)arrayOfCharacterStyle[m]).getStyle();
          if ((n & 0x1) != 0)
            paramStringBuilder.append("<b>");
          if ((n & 0x2) != 0)
            paramStringBuilder.append("<i>");
        }
        String str3;
        label191: String str2;
        if ((arrayOfCharacterStyle[m] instanceof TypefaceSpan))
        {
          str3 = ((TypefaceSpan)arrayOfCharacterStyle[m]).getFamily();
          if (str3.equalsIgnoreCase("monospace"))
            paramStringBuilder.append("<tt>");
        }
        else
        {
          if ((arrayOfCharacterStyle[m] instanceof SuperscriptSpan))
            paramStringBuilder.append("<sup>");
          if ((arrayOfCharacterStyle[m] instanceof SubscriptSpan))
            paramStringBuilder.append("<sub>");
          if ((arrayOfCharacterStyle[m] instanceof UnderlineSpan))
            paramStringBuilder.append("<u>");
          if ((arrayOfCharacterStyle[m] instanceof StrikethroughSpan))
            paramStringBuilder.append("<strike>");
          if ((arrayOfCharacterStyle[m] instanceof URLSpan))
          {
            paramStringBuilder.append("<a href=\"");
            paramStringBuilder.append(((URLSpan)arrayOfCharacterStyle[m]).getURL());
            paramStringBuilder.append("\">");
          }
          if ((arrayOfCharacterStyle[m] instanceof ImageSpan))
          {
            ImageSpan localImageSpan = (ImageSpan)arrayOfCharacterStyle[m];
            if (!localSet.contains(localImageSpan))
            {
              localSet.add(localImageSpan);
              paramStringBuilder.append("<img src=\"");
              paramStringBuilder.append(localImageSpan.getSource());
              paramStringBuilder.append("\">");
            }
            k = j;
          }
          if ((arrayOfCharacterStyle[m] instanceof AbsoluteSizeSpan))
          {
            paramStringBuilder.append("<font size=\"");
            paramStringBuilder.append(((AbsoluteSizeSpan)arrayOfCharacterStyle[m]).getSize());
            paramStringBuilder.append("\">");
          }
          if ((arrayOfCharacterStyle[m] instanceof RelativeSizeSpan))
          {
            paramStringBuilder.append("<font size=\"");
            paramStringBuilder.append(((RelativeSizeSpan)arrayOfCharacterStyle[m]).getSizeChange());
            paramStringBuilder.append("em");
            paramStringBuilder.append("\">");
          }
          if ((arrayOfCharacterStyle[m] instanceof ForegroundColorSpan))
          {
            paramStringBuilder.append("<font color=\"#");
            str2 = Integer.toHexString(16777216 + ((ForegroundColorSpan)arrayOfCharacterStyle[m]).getForegroundColor());
            label501: if (str2.length() < 6)
              break label633;
            paramStringBuilder.append(str2);
            paramStringBuilder.append("\">");
          }
          if ((arrayOfCharacterStyle[m] instanceof BackgroundColorSpan))
            paramStringBuilder.append("<font background=\"#");
        }
        for (String str1 = Integer.toHexString(16777216 + ((BackgroundColorSpan)arrayOfCharacterStyle[m]).getBackgroundColor()); ; str1 = "0" + str1)
          if (str1.length() >= 6)
          {
            paramStringBuilder.append(str1);
            paramStringBuilder.append("\">");
            m++;
            break;
            if (str3.equalsIgnoreCase("sans"))
            {
              paramStringBuilder.append("<font face=\"sans\">");
              break label191;
            }
            if (!str3.equalsIgnoreCase("serif"))
              break label191;
            paramStringBuilder.append("<font face=\"serif\">");
            break label191;
            label633: str2 = "0" + str2;
            break label501;
          }
        label679: if ((arrayOfCharacterStyle[i1] instanceof ForegroundColorSpan))
          paramStringBuilder.append("</font>");
        if ((arrayOfCharacterStyle[i1] instanceof BackgroundColorSpan))
          paramStringBuilder.append("</font>");
        if ((arrayOfCharacterStyle[i1] instanceof AbsoluteSizeSpan))
          paramStringBuilder.append("</font>");
        if ((arrayOfCharacterStyle[i1] instanceof RelativeSizeSpan))
          paramStringBuilder.append("</font>");
        if ((arrayOfCharacterStyle[i1] instanceof URLSpan))
          paramStringBuilder.append("</a>");
        if ((arrayOfCharacterStyle[i1] instanceof StrikethroughSpan))
          paramStringBuilder.append("</strike>");
        if ((arrayOfCharacterStyle[i1] instanceof UnderlineSpan))
          paramStringBuilder.append("</u>");
        if ((arrayOfCharacterStyle[i1] instanceof SubscriptSpan))
          paramStringBuilder.append("</sub>");
        if ((arrayOfCharacterStyle[i1] instanceof SuperscriptSpan))
          paramStringBuilder.append("</sup>");
        String str4;
        if ((arrayOfCharacterStyle[i1] instanceof TypefaceSpan))
        {
          str4 = ((TypefaceSpan)arrayOfCharacterStyle[i1]).getFamily();
          if (!str4.equalsIgnoreCase("monospace"))
            break label952;
          paramStringBuilder.append("</tt>");
        }
        while (true)
        {
          if ((arrayOfCharacterStyle[i1] instanceof StyleSpan))
          {
            int i2 = ((StyleSpan)arrayOfCharacterStyle[i1]).getStyle();
            if ((i2 & 0x1) != 0)
              paramStringBuilder.append("</b>");
            if ((i2 & 0x2) != 0)
              paramStringBuilder.append("</i>");
          }
          i1--;
          break;
          label952: if (str4.equalsIgnoreCase("sans"))
            paramStringBuilder.append("</font>");
          else if (str4.equalsIgnoreCase("serif"))
            paramStringBuilder.append("</font>");
        }
      }
      paramStringBuilder.append("<br/>\n");
    }
  }

  private static void a(StringBuilder paramStringBuilder, Spanned paramSpanned, BulletSpan[] paramArrayOfBulletSpan, int paramInt)
  {
    int i = 0;
    int j = paramArrayOfBulletSpan.length;
    int k = 0;
    int m;
    if (k >= j)
      m = paramArrayOfBulletSpan.length;
    while (true)
    {
      if (i >= m)
      {
        return;
        if (paramSpanned.getSpanEnd(paramArrayOfBulletSpan[k]) == paramInt)
          paramStringBuilder.append("</li>");
        k++;
        break;
      }
      if (paramSpanned.getSpanStart(paramArrayOfBulletSpan[i]) == paramInt)
        paramStringBuilder.append("<li>");
      i++;
    }
  }

  private static void a(StringBuilder paramStringBuilder, CharSequence paramCharSequence, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (i >= paramInt2)
      return;
    char c = paramCharSequence.charAt(i);
    if (c == '<')
      paramStringBuilder.append("&lt;");
    while (true)
    {
      i++;
      break;
      if (c == '>')
      {
        paramStringBuilder.append("&gt;");
      }
      else if (c == '&')
      {
        paramStringBuilder.append("&amp;");
      }
      else if ((c > '~') || (c < ' '))
      {
        paramStringBuilder.append("&#" + c + ";");
      }
      else if (c == ' ')
      {
        while (true)
        {
          if ((i + 1 >= paramInt2) || (paramCharSequence.charAt(i + 1) != ' '))
          {
            int j = 0;
            if (i > 0)
            {
              int k = paramCharSequence.charAt(i - 1);
              j = 0;
              if (k == 10)
                j = 1;
            }
            if (j == 0)
              break label216;
            paramStringBuilder.append("&nbsp;");
            break;
          }
          paramStringBuilder.append("&nbsp;");
          i++;
        }
        label216: paramStringBuilder.append(' ');
      }
      else
      {
        paramStringBuilder.append(c);
      }
    }
  }

  private static void b(StringBuilder paramStringBuilder, Spanned paramSpanned, int paramInt1, int paramInt2)
  {
    if (paramInt1 >= paramInt2)
      return;
    int i = paramSpanned.nextSpanTransition(paramInt1, paramInt2, QuoteSpan.class);
    QuoteSpan[] arrayOfQuoteSpan = (QuoteSpan[])paramSpanned.getSpans(paramInt1, i, QuoteSpan.class);
    int j = arrayOfQuoteSpan.length;
    int k = 0;
    label44: int m;
    if (k >= j)
    {
      a(paramStringBuilder, paramSpanned, paramInt1, i);
      m = arrayOfQuoteSpan.length;
    }
    for (int n = 0; ; n++)
    {
      if (n >= m)
      {
        paramInt1 = i;
        break;
        arrayOfQuoteSpan[k];
        paramStringBuilder.append("<div>");
        k++;
        break label44;
      }
      arrayOfQuoteSpan[n];
      paramStringBuilder.append("</div>\n");
    }
  }

  private static void c(StringBuilder paramStringBuilder, Spanned paramSpanned, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    if (i >= paramInt2)
      return;
    int j = TextUtils.indexOf(paramSpanned, '\n', i, paramInt2);
    if (j < 0)
      j = paramInt2;
    int k = 0;
    int m = j;
    label36: int n;
    if ((m >= paramInt2) || (paramSpanned.charAt(m) != '\n'))
    {
      n = m - k;
      if (m != paramInt2)
        break label100;
    }
    label100: for (boolean bool = true; ; bool = false)
    {
      a(paramStringBuilder, paramSpanned, i, n, k, bool);
      i = m;
      break;
      k++;
      m++;
      break label36;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.b.j
 * JD-Core Version:    0.6.2
 */