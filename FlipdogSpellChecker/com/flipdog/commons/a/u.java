package com.flipdog.commons.a;

import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;

public class u
{
  public static TypedArray a(Resources.Theme paramTheme, TypedValue paramTypedValue, int paramInt)
  {
    return paramTheme.obtainStyledAttributes(paramTypedValue.resourceId, new int[] { paramInt });
  }

  public static Drawable a(Resources.Theme paramTheme, int paramInt1, int paramInt2)
  {
    TypedArray localTypedArray = paramTheme.obtainStyledAttributes(null, new int[] { paramInt2 }, paramInt1, 0);
    try
    {
      Drawable localDrawable = localTypedArray.getDrawable(localTypedArray.getIndex(0));
      return localDrawable;
    }
    finally
    {
      localTypedArray.recycle();
    }
  }

  public static TypedValue a(Resources.Theme paramTheme, int paramInt)
  {
    TypedValue localTypedValue = new TypedValue();
    paramTheme.resolveAttribute(paramInt, localTypedValue, true);
    return localTypedValue;
  }

  public static int b(Resources.Theme paramTheme, int paramInt1, int paramInt2)
  {
    TypedArray localTypedArray = paramTheme.obtainStyledAttributes(null, new int[] { paramInt2 }, paramInt1, 0);
    try
    {
      int i = localTypedArray.getResourceId(localTypedArray.getIndex(0), -1);
      return i;
    }
    finally
    {
      localTypedArray.recycle();
    }
  }

  public static float c(Resources.Theme paramTheme, int paramInt1, int paramInt2)
  {
    TypedArray localTypedArray = paramTheme.obtainStyledAttributes(null, new int[] { paramInt2 }, paramInt1, 0);
    try
    {
      float f = localTypedArray.getDimension(0, -1.0F);
      return f;
    }
    finally
    {
      localTypedArray.recycle();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.u
 * JD-Core Version:    0.6.2
 */