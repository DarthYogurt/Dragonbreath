package com.yoc.android.yocperformance.adsdk;

import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.SystemClock;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

class f extends Drawable
  implements Animatable, Runnable
{
  private final b a = new b();
  private boolean b = false;
  private int c = 0;
  private long d = 0L;
  private final Paint e;

  private f(InputStream paramInputStream)
    throws IOException
  {
    this.a.a(Bitmap.Config.RGB_565);
    switch (this.a.a(paramInputStream))
    {
    default:
      this.e = new Paint(4);
      return;
    case 1:
      throw new IOException("Could not decode GIF image: format error");
    case 2:
    }
    throw new IOException("Could not decode GIF image: open error");
  }

  public static f a(Uri paramUri)
    throws IOException
  {
    f localf;
    try
    {
      URL localURL = new URL(paramUri.toString());
      InputStream localInputStream = null;
      try
      {
        localInputStream = localURL.openStream();
        localf = a(localInputStream);
        return localf;
      }
      finally
      {
        if (localInputStream != null)
          localInputStream.close();
      }
    }
    catch (MalformedURLException localMalformedURLException)
    {
      throw new RuntimeException(localMalformedURLException);
    }
    return localf;
  }

  public static f a(InputStream paramInputStream)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    byte[] arrayOfByte = new byte[1024];
    while (true)
    {
      int i = paramInputStream.read(arrayOfByte);
      if (i <= -1)
        return new f(new ByteArrayInputStream(localByteArrayOutputStream.toByteArray()));
      localByteArrayOutputStream.write(arrayOfByte, 0, i);
    }
  }

  public void draw(Canvas paramCanvas)
  {
    paramCanvas.drawBitmap(this.a.b(this.c), null, getBounds(), this.e);
  }

  public int getIntrinsicHeight()
  {
    if (this.a != null)
      return this.a.h;
    return -1;
  }

  public int getIntrinsicWidth()
  {
    if (this.a != null)
      return this.a.g;
    return -1;
  }

  public int getOpacity()
  {
    return -1;
  }

  public boolean isRunning()
  {
    return this.b;
  }

  public void run()
  {
    if (this.a.a() > 1)
    {
      long l = SystemClock.uptimeMillis();
      if (l >= this.d)
      {
        invalidateSelf();
        this.d = (l + this.a.a(this.c));
        this.c = (1 + this.c);
        if (this.c >= this.a.a())
          this.c = 0;
      }
      scheduleSelf(this, this.d);
    }
  }

  public void setAlpha(int paramInt)
  {
  }

  public void setColorFilter(ColorFilter paramColorFilter)
  {
  }

  public boolean setVisible(boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool = super.setVisible(paramBoolean1, paramBoolean2);
    if (!paramBoolean1)
      unscheduleSelf(this);
    return bool;
  }

  public void start()
  {
    if (!isRunning())
    {
      this.c = 0;
      this.d = (SystemClock.uptimeMillis() + this.a.a(0));
      this.b = true;
      run();
    }
  }

  public void stop()
  {
    if (isRunning())
    {
      this.b = false;
      unscheduleSelf(this);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.f
 * JD-Core Version:    0.6.2
 */