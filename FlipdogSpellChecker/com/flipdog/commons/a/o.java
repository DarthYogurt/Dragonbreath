package com.flipdog.commons.a;

import android.text.Html.ImageGetter;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ImageSpan;
import com.flipdog.b.j;
import com.flipdog.commons.c.c;
import com.flipdog.commons.c.e;

public class o
{
  public static Html.ImageGetter a()
  {
    return new p();
  }

  public static Spannable a(String paramString)
  {
    Object localObject = null;
    if (paramString == null)
      return localObject;
    Spanned localSpanned = j.a(paramString, a(), null);
    label29: ImageSpan[] arrayOfImageSpan;
    int i;
    if ((localSpanned instanceof SpannableStringBuilder))
    {
      localObject = (SpannableStringBuilder)localSpanned;
      arrayOfImageSpan = (ImageSpan[])((SpannableStringBuilder)localObject).getSpans(0, localSpanned.length(), ImageSpan.class);
      i = arrayOfImageSpan.length;
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        b((Spanned)localObject);
        for (int n = as.a((CharSequence)localObject, ' '); n != -1; n = as.a(localSpanned, ' ', n))
          ((SpannableStringBuilder)localObject).replace(n, n + 1, " ");
        break;
        localObject = new SpannableStringBuilder(localSpanned);
        break label29;
      }
      ImageSpan localImageSpan = arrayOfImageSpan[j];
      int k = localSpanned.getSpanStart(localImageSpan);
      int m = localSpanned.getSpanEnd(localImageSpan);
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = localImageSpan.getSource();
      ((SpannableStringBuilder)localObject).replace(k, m, String.format("{{image}}%s{{/image}}", arrayOfObject));
    }
  }

  public static String a(Spanned paramSpanned)
  {
    if (paramSpanned == null)
      return null;
    return j.a(paramSpanned);
  }

  public static String b(String paramString)
  {
    if (paramString == null)
      return null;
    return c.a(a(paramString));
  }

  private static void b(Spanned paramSpanned)
  {
    int i = 0;
    ImageSpan[] arrayOfImageSpan = (ImageSpan[])paramSpanned.getSpans(0, paramSpanned.length(), ImageSpan.class);
    SpannableStringBuilder localSpannableStringBuilder;
    int j;
    if (arrayOfImageSpan.length != 0)
    {
      localSpannableStringBuilder = new SpannableStringBuilder(paramSpanned);
      j = arrayOfImageSpan.length;
    }
    while (true)
    {
      if (i >= j)
        return;
      ImageSpan localImageSpan = arrayOfImageSpan[i];
      if (localImageSpan.getSource().startsWith("android.resource://"))
      {
        e locale = c.a(localSpannableStringBuilder, localImageSpan);
        localSpannableStringBuilder.removeSpan(localImageSpan);
        localSpannableStringBuilder.setSpan(new ImageSpan(localImageSpan.getDrawable(), localImageSpan.getSource(), 1), locale.b, locale.c, locale.d);
      }
      i++;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.o
 * JD-Core Version:    0.6.2
 */