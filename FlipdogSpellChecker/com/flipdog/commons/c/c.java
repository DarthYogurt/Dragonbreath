package com.flipdog.commons.c;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.flipdog.commons.a.o;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class c
{
  protected static final int a = 33;

  public static SpannableString a(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null)
      return new SpannableString("");
    StyleSpan localStyleSpan = new StyleSpan(1);
    SpannableString localSpannableString = new SpannableString(paramCharSequence);
    localSpannableString.setSpan(localStyleSpan, 0, paramCharSequence.length(), 33);
    return localSpannableString;
  }

  public static ForegroundColorSpan a(int paramInt)
  {
    return new ForegroundColorSpan(paramInt);
  }

  public static RelativeSizeSpan a(float paramFloat)
  {
    return new RelativeSizeSpan(paramFloat);
  }

  public static StyleSpan a()
  {
    return c(1);
  }

  public static TypefaceSpan a(String paramString)
  {
    return new TypefaceSpan(paramString);
  }

  public static b a(Spannable paramSpannable, Class<?>[] paramArrayOfClass)
  {
    List localList = b(paramSpannable, paramArrayOfClass);
    try
    {
      a(paramSpannable, localList);
      b localb = new b();
      localb.a = a(paramSpannable);
      localb.b = o.a(paramSpannable);
      return localb;
    }
    finally
    {
      b(paramSpannable, localList);
    }
  }

  public static e a(Spanned paramSpanned, Class<?> paramClass)
  {
    Object[] arrayOfObject = paramSpanned.getSpans(0, paramSpanned.length(), paramClass);
    if (arrayOfObject.length == 0)
      return null;
    if (arrayOfObject.length > 1)
      throw new RuntimeException("More spans than expected.");
    return a(paramSpanned, arrayOfObject[0]);
  }

  public static e a(Spanned paramSpanned, Object paramObject)
  {
    e locale = new e();
    locale.a = paramObject;
    locale.b = paramSpanned.getSpanStart(paramObject);
    locale.c = paramSpanned.getSpanEnd(paramObject);
    locale.d = paramSpanned.getSpanFlags(paramObject);
    return locale;
  }

  public static e a(Object paramObject, int paramInt1, int paramInt2, int paramInt3)
  {
    e locale = new e();
    locale.a = paramObject;
    locale.b = paramInt1;
    locale.c = paramInt2;
    locale.d = paramInt3;
    return locale;
  }

  public static String a(Spanned paramSpanned)
  {
    List localList = a(paramSpanned, new Class[] { ImageSpan.class });
    Collections.sort(localList, new d());
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = localList.iterator();
    int i = 0;
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localStringBuilder.append(paramSpanned.subSequence(i, paramSpanned.length()));
        return localStringBuilder.toString();
      }
      e locale = (e)localIterator.next();
      if (i > locale.b)
      {
        i = locale.c;
      }
      else
      {
        localStringBuilder.append(paramSpanned.subSequence(i, locale.b));
        i = locale.c;
      }
    }
  }

  public static List<e> a(Spanned paramSpanned, int paramInt1, int paramInt2, Class<?>[] paramArrayOfClass)
  {
    return b(paramSpanned, paramInt1, paramInt2, paramArrayOfClass);
  }

  public static List<e> a(Spanned paramSpanned, Class<?>[] paramArrayOfClass)
  {
    return b(paramSpanned, paramArrayOfClass);
  }

  public static void a(Spannable paramSpannable, Object paramObject, int paramInt)
  {
    paramSpannable.setSpan(paramObject, 0, paramSpannable.length(), paramInt);
  }

  private static void a(Spannable paramSpannable, List<e> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      paramSpannable.removeSpan(((e)localIterator.next()).a);
    }
  }

  public static void a(SpannableStringBuilder paramSpannableStringBuilder, CharSequence paramCharSequence, Object[] paramArrayOfObject)
  {
    int i = paramSpannableStringBuilder.length();
    int j = i + paramCharSequence.length();
    paramSpannableStringBuilder.append(paramCharSequence);
    int k = paramArrayOfObject.length;
    for (int m = 0; ; m++)
    {
      if (m >= k)
        return;
      paramSpannableStringBuilder.setSpan(paramArrayOfObject[m], i, j, 33);
    }
  }

  private static void a(Spanned paramSpanned, int paramInt1, int paramInt2, List<e> paramList, Class<?> paramClass)
  {
    Object[] arrayOfObject = paramSpanned.getSpans(paramInt1, paramInt2, paramClass);
    int i = arrayOfObject.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      paramList.add(a(paramSpanned, arrayOfObject[j]));
    }
  }

  public static BackgroundColorSpan b(int paramInt)
  {
    return new BackgroundColorSpan(paramInt);
  }

  public static StyleSpan b()
  {
    return c(2);
  }

  private static List<e> b(Spanned paramSpanned, int paramInt1, int paramInt2, Class<?>[] paramArrayOfClass)
  {
    ArrayList localArrayList = new ArrayList();
    int i = paramArrayOfClass.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localArrayList;
      a(paramSpanned, paramInt1, paramInt2, localArrayList, paramArrayOfClass[j]);
    }
  }

  private static List<e> b(Spanned paramSpanned, Class<?>[] paramArrayOfClass)
  {
    return b(paramSpanned, 0, paramSpanned.length(), paramArrayOfClass);
  }

  private static void b(Spannable paramSpannable, List<e> paramList)
  {
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      e locale = (e)localIterator.next();
      paramSpannable.setSpan(locale.a, locale.b, locale.c, 33);
    }
  }

  public static void b(Spanned paramSpanned)
  {
    a.a(paramSpanned);
  }

  public static StyleSpan c(int paramInt)
  {
    return new StyleSpan(paramInt);
  }

  public static UnderlineSpan c()
  {
    return new UnderlineSpan();
  }

  public static AbsoluteSizeSpan d(int paramInt)
  {
    return new AbsoluteSizeSpan(paramInt);
  }

  public static StrikethroughSpan d()
  {
    return new StrikethroughSpan();
  }

  public static SubscriptSpan e()
  {
    return new SubscriptSpan();
  }

  public static SuperscriptSpan f()
  {
    return new SuperscriptSpan();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.c.c
 * JD-Core Version:    0.6.2
 */