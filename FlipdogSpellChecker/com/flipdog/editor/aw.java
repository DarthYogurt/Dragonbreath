package com.flipdog.editor;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.GradientDrawable.Orientation;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.PathShape;
import android.view.View;
import com.flipdog.commons.diagnostic.Track;

public class aw
{
  private static LayerDrawable a(int[] paramArrayOfInt, GradientDrawable.Orientation paramOrientation)
  {
    int i = 5;
    GradientDrawable localGradientDrawable = new GradientDrawable(paramOrientation, paramArrayOfInt);
    int j;
    if (paramOrientation == GradientDrawable.Orientation.RIGHT_LEFT)
      j = i;
    while (true)
    {
      return new LayerDrawable(new Drawable[] { localGradientDrawable, a(12, 40, j, 17, i, 4) });
      if (paramOrientation != GradientDrawable.Orientation.LEFT_RIGHT)
        break;
      j = 7;
      i = -5;
    }
    throw new RuntimeException("Unexpected " + paramOrientation);
  }

  private static ShapeDrawable a(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    Path localPath = new Path();
    localPath.setLastPoint(paramInt3, paramInt4);
    localPath.lineTo(paramInt3 + paramInt5, paramInt4 + paramInt6);
    localPath.lineTo(paramInt3, paramInt4 + paramInt6 * 2);
    ShapeDrawable localShapeDrawable = new ShapeDrawable(new PathShape(localPath, paramInt1, paramInt2));
    localShapeDrawable.getPaint().setColor(-1608375553);
    return localShapeDrawable;
  }

  public static void a(View paramView1, View paramView2)
  {
    int[] arrayOfInt = a(new int[] { 1344414463, 270672639 });
    LayerDrawable localLayerDrawable1 = a(arrayOfInt, GradientDrawable.Orientation.LEFT_RIGHT);
    LayerDrawable localLayerDrawable2 = a(arrayOfInt, GradientDrawable.Orientation.RIGHT_LEFT);
    paramView1.setBackgroundDrawable(localLayerDrawable1);
    paramView2.setBackgroundDrawable(localLayerDrawable2);
  }

  public static void a(MyHorizontalScrollView paramMyHorizontalScrollView, View paramView1, View paramView2)
  {
    a("--", new Object[0]);
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = Integer.valueOf(paramMyHorizontalScrollView.getScrollX());
    a("getScrollX() = %s", arrayOfObject1);
    Object[] arrayOfObject2 = new Object[1];
    arrayOfObject2[0] = Integer.valueOf(paramMyHorizontalScrollView.getWidth());
    a("scroll.getWidth() = %s", arrayOfObject2);
    View localView = paramMyHorizontalScrollView.getChildAt(0);
    Object[] arrayOfObject3 = new Object[1];
    arrayOfObject3[0] = Integer.valueOf(localView.getWidth());
    a("view.getWidth() = %s", arrayOfObject3);
    Object[] arrayOfObject4 = new Object[1];
    arrayOfObject4[0] = Integer.valueOf(localView.getRight());
    a("view.getRight() = %s", arrayOfObject4);
    Object[] arrayOfObject5 = new Object[1];
    arrayOfObject5[0] = Integer.valueOf(localView.getLeft());
    a("view.getLeft() = %s", arrayOfObject5);
    int i = localView.getRight() - (paramMyHorizontalScrollView.getWidth() + paramMyHorizontalScrollView.getScrollX());
    Object[] arrayOfObject6 = new Object[1];
    arrayOfObject6[0] = Integer.valueOf(i);
    a("diff = %s", arrayOfObject6);
    if (paramMyHorizontalScrollView.getScrollX() < 20)
      paramView1.setVisibility(8);
    while (i < 20)
    {
      paramView2.setVisibility(8);
      return;
      paramView1.setVisibility(0);
    }
    paramView2.setVisibility(0);
  }

  private static void a(String paramString, Object[] paramArrayOfObject)
  {
    if (Track.isDisabled("Editor"))
      return;
    Track.me("Editor", paramString, paramArrayOfObject);
  }

  private static int[] a(int[] paramArrayOfInt)
  {
    return paramArrayOfInt;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.aw
 * JD-Core Version:    0.6.2
 */