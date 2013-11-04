package com.flipdog.commons.c;

import android.text.Spanned;
import com.flipdog.commons.a.as;
import com.flipdog.commons.diagnostic.Track;
import java.util.Iterator;
import java.util.List;

public class a
{
  private static List<f> a = a();

  private static String a(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = a.iterator();
    int i = 0;
    while (true)
    {
      if (!localIterator.hasNext())
      {
        int j = paramInt & (i ^ 0xFFFFFFFF);
        if (j != 0)
        {
          Object[] arrayOfObject = new Object[1];
          arrayOfObject[0] = Integer.toString(j, 16);
          a(localStringBuilder, String.format("0x%s", arrayOfObject));
        }
        return localStringBuilder.toString();
      }
      f localf = (f)localIterator.next();
      a(localStringBuilder, paramInt, f.a(localf), f.b(localf));
      i |= f.a(localf);
    }
  }

  private static List<f> a()
  {
    List localList = as.b();
    localList.add(new f(256, "SPAN_COMPOSING"));
    localList.add(new f(33, "SPAN_EXCLUSIVE_EXCLUSIVE"));
    localList.add(new f(34, "SPAN_EXCLUSIVE_INCLUSIVE"));
    localList.add(new f(17, "SPAN_INCLUSIVE_EXCLUSIVE"));
    localList.add(new f(18, "SPAN_INCLUSIVE_INCLUSIVE"));
    localList.add(new f(512, "SPAN_INTERMEDIATE"));
    localList.add(new f(17, "SPAN_MARK_MARK"));
    localList.add(new f(18, "SPAN_MARK_POINT"));
    localList.add(new f(51, "SPAN_PARAGRAPH"));
    localList.add(new f(33, "SPAN_POINT_MARK"));
    localList.add(new f(51, "SPAN_POINT_MARK_MASK"));
    localList.add(new f(34, "SPAN_POINT_POINT"));
    localList.add(new f(16711680, "SPAN_PRIORITY"));
    localList.add(new f(16, "SPAN_PRIORITY_SHIFT"));
    localList.add(new f(-16777216, "SPAN_USER"));
    localList.add(new f(24, "SPAN_USER_SHIFT"));
    return localList;
  }

  public static void a(Spanned paramSpanned)
  {
    Object[] arrayOfObject1 = a(paramSpanned);
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Integer.valueOf(arrayOfObject1.length);
    a("Spans (count = %s)", arrayOfObject2);
    int i = arrayOfObject1.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return;
      Object localObject = arrayOfObject1[j];
      int k = paramSpanned.getSpanFlags(localObject);
      Object[] arrayOfObject3 = new Object[4];
      arrayOfObject3[0] = localObject.getClass().getName();
      arrayOfObject3[1] = Integer.valueOf(paramSpanned.getSpanStart(localObject));
      arrayOfObject3[2] = Integer.valueOf(paramSpanned.getSpanEnd(localObject));
      arrayOfObject3[3] = a(k);
      a("  %s (%s, %s), %s", arrayOfObject3);
    }
  }

  private static void a(String paramString, Object[] paramArrayOfObject)
  {
    Track.it(String.format(paramString, paramArrayOfObject), new String[] { "Editor" });
  }

  private static void a(StringBuilder paramStringBuilder, int paramInt1, int paramInt2, String paramString)
  {
    if (a(paramInt1, paramInt2))
      a(paramStringBuilder, paramString);
  }

  private static void a(StringBuilder paramStringBuilder, String paramString)
  {
    if (paramStringBuilder.length() != 0)
      paramStringBuilder.append(" | ");
    paramStringBuilder.append(paramString);
  }

  private static boolean a(int paramInt1, int paramInt2)
  {
    return (paramInt1 & paramInt2) == paramInt2;
  }

  public static Object[] a(CharSequence paramCharSequence)
  {
    Spanned localSpanned = (Spanned)paramCharSequence;
    return localSpanned.getSpans(0, localSpanned.length(), Object.class);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.c.a
 * JD-Core Version:    0.6.2
 */