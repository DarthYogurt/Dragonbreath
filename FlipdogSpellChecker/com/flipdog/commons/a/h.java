package com.flipdog.commons.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import com.flipdog.commons.i.b;

public class h
{
  public static float a(float paramFloat)
  {
    Paint localPaint = new Paint();
    localPaint.setTextSize(paramFloat);
    return a(localPaint);
  }

  public static float a(int paramInt, float paramFloat)
  {
    return TypedValue.applyDimension(paramInt, paramFloat, a());
  }

  public static float a(Paint paramPaint)
  {
    Paint.FontMetrics localFontMetrics = paramPaint.getFontMetrics();
    return localFontMetrics.bottom - localFontMetrics.top;
  }

  public static int a(String paramString, Paint paramPaint)
  {
    Rect localRect = b(paramString, paramPaint);
    return localRect.right - localRect.left;
  }

  private static DisplayMetrics a()
  {
    return ((Context)b.a(Context.class)).getResources().getDisplayMetrics();
  }

  public static int b(float paramFloat)
  {
    return (int)(0.5F + paramFloat);
  }

  public static Rect b(String paramString, Paint paramPaint)
  {
    char[] arrayOfChar = ax.e(paramString);
    Rect localRect = new Rect();
    paramPaint.getTextBounds(arrayOfChar, 0, arrayOfChar.length, localRect);
    return localRect;
  }

  public static float c(float paramFloat)
  {
    return a(1, paramFloat);
  }

  public static float d(float paramFloat)
  {
    return paramFloat / a().density;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.h
 * JD-Core Version:    0.6.2
 */