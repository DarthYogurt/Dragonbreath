package com.flipdog.editor;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.text.style.ImageSpan;
import com.b.f;
import com.flipdog.commons.a.as;

public class ah
{
  public static ImageSpan a(String paramString)
  {
    Drawable localDrawable = b(paramString);
    if (localDrawable == null)
      return null;
    return new ImageSpan(localDrawable, paramString, 1);
  }

  public static int[] a(int[] paramArrayOfInt)
  {
    return paramArrayOfInt;
  }

  public static Drawable b(String paramString)
  {
    f localf = new f().a(paramString);
    int i = localf.c();
    int j = localf.d();
    if ((j < 200) && (i < 200))
    {
      Bitmap localBitmap2 = localf.b();
      if (localBitmap2 == null)
        return null;
      BitmapDrawable localBitmapDrawable2 = new BitmapDrawable(localBitmap2);
      localBitmapDrawable2.setBounds(0, 0, localBitmap2.getWidth(), localBitmap2.getHeight());
      return localBitmapDrawable2;
    }
    Bitmap localBitmap1 = localf.a('È', 'È').b();
    int k = localBitmap1.getWidth();
    int m = localBitmap1.getHeight();
    BitmapDrawable localBitmapDrawable1 = new BitmapDrawable(localBitmap1);
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(i);
    arrayOfObject[1] = Integer.valueOf(j);
    e locale = new e(String.format("%sx%s", arrayOfObject));
    locale.a(k - 10, 20);
    LayerDrawable localLayerDrawable = new LayerDrawable((Drawable[])as.a(new Drawable[] { localBitmapDrawable1, locale }));
    localLayerDrawable.setBounds(0, 0, Math.max(k, 100), m);
    localBitmapDrawable1.setBounds(0, 0, k, m);
    return localLayerDrawable;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.ah
 * JD-Core Version:    0.6.2
 */