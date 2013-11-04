package com.flipdog.editor;

import android.text.Spannable;
import android.text.TextWatcher;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BackgroundColorSpan;
import android.text.style.BulletSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import com.flipdog.commons.c.c;
import com.flipdog.editor.a.h;
import com.flipdog.editor.spans.j;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ag
{
  public static final Class<?>[] a = { StyleSpan.class, UnderlineSpan.class, ForegroundColorSpan.class, BackgroundColorSpan.class, AbsoluteSizeSpan.class, RelativeSizeSpan.class, BulletSpan.class };

  public static int a(CharSequence paramCharSequence, int paramInt)
  {
    int i = paramCharSequence.length();
    for (int j = paramInt; ; j++)
    {
      if (j >= i)
        j = i;
      while (paramCharSequence.charAt(j) == '\n')
        return j;
    }
  }

  public static f a(MyEditText paramMyEditText, int paramInt, TextWatcher[] paramArrayOfTextWatcher)
  {
    f localf = f.a(paramMyEditText, paramInt);
    com.flipdog.editor.b.e locale = localf.e();
    h localh = localf.c();
    locale.a(localh.b);
    int i = paramArrayOfTextWatcher.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        locale.a(localh.c);
        return localf;
      }
      locale.a(paramArrayOfTextWatcher[j]);
    }
  }

  public static List<com.flipdog.commons.c.e> a(Spannable paramSpannable)
  {
    return a(paramSpannable, j.a(paramSpannable, 0, paramSpannable.length(), a));
  }

  private static List<com.flipdog.commons.c.e> a(Spannable paramSpannable, List<Object> paramList)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      localArrayList.add(c.a(paramSpannable, localIterator.next()));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.ag
 * JD-Core Version:    0.6.2
 */