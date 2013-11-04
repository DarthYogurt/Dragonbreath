package com.flipdog.filebrowser.i;

import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewParent;
import com.flipdog.commons.a.as;
import com.flipdog.commons.a.ax;
import com.flipdog.filebrowser.b.d;
import com.flipdog.filebrowser.k.f;
import com.flipdog.filebrowser.k.j;
import com.flipdog.filebrowser.k.k;
import com.flipdog.m;
import java.io.File;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class b
{
  private static Comparator<File> a = null;

  public static int a(View paramView)
  {
    while (true)
    {
      ViewParent localViewParent = paramView.getParent();
      if ((!(localViewParent instanceof View)) || (localViewParent == null));
      View localView;
      while (true)
      {
        int i = paramView.getTop();
        int j = 0;
        if (i > 0)
        {
          int k = j.d().heightPixels;
          int m = paramView.getMeasuredHeight();
          j = 0;
          if (i > 0)
          {
            j = 0;
            if (k > m)
              j = k - m;
          }
        }
        return j;
        localView = (View)localViewParent;
        if (localView.getId() != m.fbrowse_layout_main)
          break;
        paramView = localView;
      }
      paramView = localView;
    }
  }

  public static int a(String paramString1, String paramString2)
  {
    int i = Math.min(paramString1.length(), paramString2.length());
    String str1 = paramString1.toLowerCase();
    String str2 = paramString2.toLowerCase();
    int n;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        n = str1.length() - str2.length();
        if (n <= 0)
          break;
      }
      int k;
      int m;
      do
      {
        return 1;
        k = str1.charAt(j);
        m = str2.charAt(j);
        if (k < m)
          return -1;
      }
      while (k > m);
    }
    if (n < 0)
      return -1;
    return n;
  }

  public static Point a(List<String> paramList)
  {
    Paint localPaint = new Paint();
    localPaint.setTypeface(Typeface.DEFAULT_BOLD);
    localPaint.setTextSize(com.flipdog.filebrowser.preference.a.b().f());
    Point localPoint = new Point();
    Rect localRect = new Rect();
    Iterator localIterator = paramList.iterator();
    int i = 0;
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localPoint.x = (i + k.a(73));
        int j = j.e();
        int k = 35;
        if (j == 120)
          k = 36;
        localPoint.y = k.a(k);
        return localPoint;
      }
      String str = (String)localIterator.next();
      localPaint.getTextBounds(str, 0, str.length(), localRect);
      localPoint.x = localRect.width();
      if (localPoint.x > i)
        i = localPoint.x;
    }
  }

  public static Comparator<File> a()
  {
    if (a == null)
      a = new a();
    return a;
  }

  public static void a(d paramd, Intent paramIntent)
  {
    if (paramd.j == 2)
    {
      paramd.e = null;
      paramd.f = null;
    }
    String str1;
    String[] arrayOfString;
    do
    {
      String str2;
      do
      {
        return;
        str1 = paramIntent.getStringExtra("com.flipdog.filebrowser.extra.FILTER_BY_CONTENT_TYPE");
        if (!ax.a(str1))
        {
          if (!"*/*".equals(str1))
            break;
          str1 = null;
        }
        paramd.e = str1;
        str2 = paramIntent.getStringExtra("com.flipdog.filebrowser.extra.FILTER_BY_EXTENSION");
        paramd.f = null;
      }
      while (ax.a(str2));
      arrayOfString = ax.a(str2, ",");
    }
    while (arrayOfString.length == 0);
    List localList = as.b();
    int i = arrayOfString.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        paramd.f = localList;
        return;
        if (!str1.endsWith("/*"))
          break;
        str1 = str1.substring(0, -1 + str1.length());
        break;
      }
      String str3 = arrayOfString[j];
      localList.add("." + str3.trim());
    }
  }

  public static boolean a(com.flipdog.a.b.b.b paramb1, com.flipdog.a.b.b.b paramb2)
  {
    if ((paramb1 == null) && (paramb2 == null))
      return true;
    if ((paramb1 == null) || (paramb2 == null))
      return false;
    return paramb1.a.equals(paramb2.a);
  }

  public static boolean a(d paramd, boolean paramBoolean, String paramString)
  {
    if (paramBoolean)
      return (paramd.j == 3) || (paramd.j == 2);
    if ((paramd.e == null) && (paramd.f == null))
      return true;
    Iterator localIterator;
    if (paramd.f != null)
      localIterator = paramd.f.iterator();
    String str;
    do
      if (!localIterator.hasNext())
      {
        if (paramd.e == null)
          break label117;
        str = f.f(paramString);
        if (!ax.a(str))
          break;
        return false;
      }
    while (!paramString.endsWith((String)localIterator.next()));
    return true;
    return str.contains(paramd.e);
    label117: return false;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.i.b
 * JD-Core Version:    0.6.2
 */