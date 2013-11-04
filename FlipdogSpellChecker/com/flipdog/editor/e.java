package com.flipdog.editor;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.drawable.Drawable;

public class e extends Drawable
{
  private final String a;
  private final Paint b;
  private int c;
  private int d;

  public e(String paramString)
  {
    this.a = paramString;
    this.b = new Paint();
    this.b.setColor(-1);
    this.b.setTextSize(12.0F);
    this.b.setAntiAlias(true);
    this.b.setFakeBoldText(true);
    this.b.setShadowLayer(6.0F, 0.0F, 0.0F, -16777216);
    this.b.setStyle(Paint.Style.FILL);
    this.b.setTextAlign(Paint.Align.RIGHT);
  }

  public Paint a()
  {
    return this.b;
  }

  public void a(int paramInt1, int paramInt2)
  {
    this.c = paramInt1;
    this.d = paramInt2;
  }

  public void draw(Canvas paramCanvas)
  {
    paramCanvas.drawText(this.a, this.c, this.d, this.b);
  }

  public int getOpacity()
  {
    return -3;
  }

  public void setAlpha(int paramInt)
  {
    this.b.setAlpha(paramInt);
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
    this.b.setColorFilter(paramColorFilter);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.editor.e
 * JD-Core Version:    0.6.2
 */