package com.flipdog.editor.spans;

import android.text.Spannable;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.UnderlineSpan;
import com.flipdog.commons.a.as;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class j
{
  protected static StyleSpan a(int paramInt)
  {
    return new StyleSpan(paramInt);
  }

  public static List<Object> a(Spannable paramSpannable, int paramInt1, int paramInt2, k[] paramArrayOfk)
  {
    List localList = as.b();
    Object[] arrayOfObject = paramSpannable.getSpans(paramInt1, paramInt2, Object.class);
    int i = arrayOfObject.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localList;
      Object localObject = arrayOfObject[j];
      if (a(localObject, paramSpannable.getSpanFlags(localObject), paramArrayOfk))
        localList.add(localObject);
    }
  }

  public static List<Object> a(Spanned paramSpanned, int paramInt1, int paramInt2, Class<?>[] paramArrayOfClass)
  {
    List localList = as.b();
    Object[] arrayOfObject = paramSpanned.getSpans(paramInt1, paramInt2, Object.class);
    int i = arrayOfObject.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localList;
      Object localObject = arrayOfObject[j];
      if (a(localObject, paramArrayOfClass))
        localList.add(localObject);
    }
  }

  public static List<Object> a(Spanned paramSpanned, Class<?>[] paramArrayOfClass)
  {
    return a(paramSpanned, 0, paramSpanned.length(), paramArrayOfClass);
  }

  public static void a(Spannable paramSpannable, int paramInt1, int paramInt2, StyleSpan paramStyleSpan)
  {
    Object[] arrayOfObject = paramSpannable.getSpans(paramInt1, paramInt2, StyleSpan.class);
    if (arrayOfObject.length != 0)
    {
      paramInt1 = b(paramSpannable, arrayOfObject);
      paramInt2 = c(paramSpannable, arrayOfObject);
    }
    a(paramSpannable, arrayOfObject);
    paramSpannable.setSpan(paramStyleSpan, paramInt1, paramInt2, 34);
  }

  public static <T> void a(Spannable paramSpannable, int paramInt1, int paramInt2, Class<T> paramClass)
  {
    Object[] arrayOfObject = paramSpannable.getSpans(paramInt1, paramInt2, paramClass);
    int i = arrayOfObject.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      paramSpannable.removeSpan(arrayOfObject[j]);
    }
  }

  public static void a(Spannable paramSpannable, int paramInt1, int paramInt2, Class<?>[] paramArrayOfClass)
  {
    int i = paramArrayOfClass.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      b(paramSpannable, a(paramSpannable, paramInt1, paramInt2, new Class[] { paramArrayOfClass[j] }), paramInt1, paramInt2);
    }
  }

  public static <T> void a(Spannable paramSpannable, Class<T> paramClass)
  {
    a(paramSpannable, 0, paramSpannable.length(), paramClass);
  }

  public static void a(Spannable paramSpannable, Object[] paramArrayOfObject)
  {
    int i = paramArrayOfObject.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      paramSpannable.removeSpan(paramArrayOfObject[j]);
    }
  }

  public static boolean a(Spannable paramSpannable, int paramInt1, int paramInt2, k paramk)
  {
    return a(paramSpannable, a(paramSpannable, paramInt1, paramInt2, new k[] { paramk }), paramInt1, paramInt2);
  }

  public static boolean a(Spannable paramSpannable, int paramInt, k paramk)
  {
    boolean bool = true;
    k[] arrayOfk = new k[bool];
    arrayOfk[0] = paramk;
    Iterator localIterator = a(paramSpannable, paramInt, paramInt, arrayOfk).iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        bool = false;
      while (paramSpannable.getSpanStart(localIterator.next()) != paramInt)
        return bool;
    }
  }

  public static boolean a(Spannable paramSpannable, Object paramObject)
  {
    return b(paramSpannable.getSpanFlags(paramObject));
  }

  public static boolean a(Spannable paramSpannable, List<Object> paramList, int paramInt1, int paramInt2)
  {
    if (paramList.size() == 0)
      return false;
    Collections.sort(paramList, new m(paramSpannable));
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (paramInt1 < paramInt2)
          break;
        return true;
      }
      Object localObject = localIterator.next();
      int i = paramSpannable.getSpanStart(localObject);
      int j = paramSpannable.getSpanEnd(localObject);
      if (paramInt1 < i)
        break;
      paramInt1 = Math.max(paramInt1, j);
    }
  }

  public static boolean a(Spanned paramSpanned, Object paramObject)
  {
    return paramSpanned.getSpanStart(paramObject) != -1;
  }

  static boolean a(Object paramObject, int paramInt)
  {
    if (!(paramObject instanceof StyleSpan));
    while ((paramInt & ((StyleSpan)paramObject).getStyle()) == 0)
      return false;
    return true;
  }

  private static boolean a(Object paramObject, int paramInt, k[] paramArrayOfk)
  {
    int i = paramArrayOfk.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return false;
      if (paramArrayOfk[j].a(paramObject, paramInt))
        return true;
    }
  }

  private static boolean a(Object paramObject, Class<?>[] paramArrayOfClass)
  {
    int i = paramArrayOfClass.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return false;
      if (paramArrayOfClass[j].isInstance(paramObject))
        return true;
    }
  }

  public static Object[] a(CharSequence paramCharSequence)
  {
    Spanned localSpanned = (Spanned)paramCharSequence;
    return localSpanned.getSpans(0, localSpanned.length(), Object.class);
  }

  private static int b(Spannable paramSpannable, Object[] paramArrayOfObject)
  {
    int i = 2147483647;
    int j = paramArrayOfObject.length;
    for (int k = 0; ; k++)
    {
      if (k >= j)
        return i;
      i = Math.min(i, paramSpannable.getSpanStart(paramArrayOfObject[k]));
    }
  }

  public static void b(Spannable paramSpannable, int paramInt1, int paramInt2, k[] paramArrayOfk)
  {
    int i = paramArrayOfk.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      b(paramSpannable, a(paramSpannable, paramInt1, paramInt2, new k[] { paramArrayOfk[j] }), paramInt1, paramInt2);
    }
  }

  public static <T> void b(Spannable paramSpannable, List<T> paramList, int paramInt1, int paramInt2)
  {
    Iterator localIterator = paramList.iterator();
    Object localObject1;
    int i;
    int j;
    do
      while (true)
      {
        if (!localIterator.hasNext())
          return;
        localObject1 = localIterator.next();
        i = paramSpannable.getSpanStart(localObject1);
        j = paramSpannable.getSpanEnd(localObject1);
        if ((i >= paramInt1) && (j <= paramInt2))
        {
          paramSpannable.removeSpan(localObject1);
        }
        else if ((i >= paramInt1) && (j > paramInt2))
        {
          paramSpannable.setSpan(localObject1, paramInt2, j, 34);
        }
        else
        {
          if ((i >= paramInt1) || (j > paramInt2))
            break;
          paramSpannable.setSpan(localObject1, i, paramInt1, 34);
        }
      }
    while ((i >= paramInt1) || (j <= paramInt2));
    Class localClass = localObject1.getClass();
    Object localObject2;
    Object localObject3;
    if ((localObject1 instanceof StyleSpan))
    {
      int i1 = ((StyleSpan)localObject1).getStyle();
      localObject2 = new StyleSpan(i1);
      localObject3 = new StyleSpan(i1);
    }
    while (true)
    {
      paramSpannable.setSpan(localObject2, i, paramInt1, 34);
      paramSpannable.setSpan(localObject3, paramInt2, j, 34);
      paramSpannable.removeSpan(localObject1);
      break;
      if (localClass.equals(UnderlineSpan.class))
      {
        localObject2 = new UnderlineSpan();
        localObject3 = new UnderlineSpan();
      }
      else if (localClass.equals(ForegroundColorSpan.class))
      {
        int n = ((ForegroundColorSpan)localObject1).getForegroundColor();
        localObject2 = new ForegroundColorSpan(n);
        localObject3 = new ForegroundColorSpan(n);
      }
      else if (localClass.equals(BackgroundColorSpan.class))
      {
        int m = ((BackgroundColorSpan)localObject1).getBackgroundColor();
        localObject2 = new BackgroundColorSpan(m);
        localObject3 = new BackgroundColorSpan(m);
      }
      else if (localClass.equals(SubscriptSpan.class))
      {
        localObject2 = new SubscriptSpan();
        localObject3 = new SubscriptSpan();
      }
      else if (localClass.equals(SuperscriptSpan.class))
      {
        localObject2 = new SuperscriptSpan();
        localObject3 = new SuperscriptSpan();
      }
      else if (localClass.equals(AbsoluteSizeSpan.class))
      {
        int k = ((AbsoluteSizeSpan)localObject1).getSize();
        localObject2 = new AbsoluteSizeSpan(k);
        localObject3 = new AbsoluteSizeSpan(k);
      }
      else if (localClass.equals(RelativeSizeSpan.class))
      {
        float f = ((RelativeSizeSpan)localObject1).getSizeChange();
        localObject2 = new RelativeSizeSpan(f);
        localObject3 = new RelativeSizeSpan(f);
      }
      else
      {
        if (!localClass.equals(TypefaceSpan.class))
          break label541;
        String str = ((TypefaceSpan)localObject1).getFamily();
        localObject2 = new TypefaceSpan(str);
        localObject3 = new TypefaceSpan(str);
      }
    }
    label541: throw new RuntimeException("Unexpected style: " + localObject1.getClass().getName());
  }

  public static boolean b(int paramInt)
  {
    return (paramInt & 0x100) != 0;
  }

  private static int c(Spannable paramSpannable, Object[] paramArrayOfObject)
  {
    int i = 0;
    int j = paramArrayOfObject.length;
    int k = 0;
    while (true)
    {
      if (i >= j)
        return k;
      k = Math.max(k, paramSpannable.getSpanEnd(paramArrayOfObject[i]));
      i++;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.spans.j
 * JD-Core Version:    0.6.2
 */