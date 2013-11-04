package com.yoc.android.yocperformance.adsdk;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Movie;
import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.SystemClock;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

class e extends Drawable
  implements Animatable, Runnable
{
  private static final long a = 50L;
  private final Movie b;
  private long c = 0L;
  private boolean d = false;

  public e(Movie paramMovie)
  {
    this.b = paramMovie;
  }

  public static e a(Uri paramUri)
    throws IOException
  {
    e locale;
    try
    {
      URL localURL = new URL(paramUri.toString());
      InputStream localInputStream = null;
      try
      {
        localInputStream = localURL.openStream();
        locale = a(localInputStream);
        return locale;
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
    return locale;
  }

  public static e a(InputStream paramInputStream)
  {
    return new e(Movie.decodeStream(paramInputStream));
  }

  public static e a(String paramString)
  {
    return new e(Movie.decodeFile(paramString));
  }

  public static e a(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new e(Movie.decodeByteArray(paramArrayOfByte, paramInt1, paramInt2));
  }

  public void draw(Canvas paramCanvas)
  {
    long l = SystemClock.uptimeMillis();
    if (this.c == 0L)
      this.c = l;
    if (this.b != null)
    {
      int i = this.b.duration();
      if (i == 0)
        i = 1000;
      int j = (int)((l - this.c) % i);
      this.b.setTime(j);
      this.b.draw(paramCanvas, getBounds().left, getBounds().right);
    }
  }

  public int getOpacity()
  {
    return -1;
  }

  public boolean isRunning()
  {
    return this.d;
  }

  public void run()
  {
    invalidateSelf();
    scheduleSelf(this, 50L + SystemClock.uptimeMillis());
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
      this.d = true;
      run();
    }
  }

  public void stop()
  {
    if (isRunning())
    {
      this.d = false;
      unscheduleSelf(this);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.e
 * JD-Core Version:    0.6.2
 */