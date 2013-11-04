package com.flipdog.commons.a;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.StateListDrawable;
import android.util.StateSet;
import com.flipdog.commons.i.b;
import com.flipdog.commons.t.a;

public class s
{
  private static Context a()
  {
    return (Context)b.a(Context.class);
  }

  public static Drawable a(int paramInt)
  {
    StateListDrawable localStateListDrawable = new StateListDrawable();
    Drawable localDrawable1 = b(paramInt);
    Drawable localDrawable2 = a(e(b(paramInt).mutate()));
    localStateListDrawable.addState(new int[] { 16842910 }, localDrawable1);
    localStateListDrawable.addState(StateSet.WILD_CARD, localDrawable2);
    return localStateListDrawable;
  }

  public static Drawable a(Drawable paramDrawable)
  {
    return new a(paramDrawable);
  }

  public static Drawable a(Drawable paramDrawable, int paramInt)
  {
    paramDrawable.setAlpha(paramInt);
    return paramDrawable;
  }

  public static Drawable b(int paramInt)
  {
    return a().getResources().getDrawable(paramInt);
  }

  public static Drawable b(Drawable paramDrawable)
  {
    ColorMatrix localColorMatrix = new ColorMatrix();
    localColorMatrix.setSaturation(0.0F);
    paramDrawable.setColorFilter(new ColorMatrixColorFilter(localColorMatrix));
    return paramDrawable;
  }

  public static Drawable c(Drawable paramDrawable)
  {
    Drawable localDrawable = a(e(d(paramDrawable)));
    StateListDrawable localStateListDrawable = new StateListDrawable();
    localStateListDrawable.addState(new int[] { 16842910 }, paramDrawable);
    localStateListDrawable.addState(StateSet.WILD_CARD, localDrawable);
    return localStateListDrawable;
  }

  public static Drawable d(Drawable paramDrawable)
  {
    return paramDrawable.getConstantState().newDrawable().mutate();
  }

  public static Drawable e(Drawable paramDrawable)
  {
    return a(paramDrawable, 127);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.s
 * JD-Core Version:    0.6.2
 */