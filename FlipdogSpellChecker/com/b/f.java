package com.b;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Rect;
import android.net.Uri;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.commons.i.b;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class f
{
  private float a;
  private float b;
  private String c;
  private byte[] d;
  private BitmapFactory.Options e;
  private Context f;
  private int g = -1;
  private String h;

  private Bitmap a(BitmapFactory.Options paramOptions)
  {
    if (this.c != null)
      return a(this.c, paramOptions);
    if (this.d != null)
      return BitmapFactory.decodeByteArray(this.d, 0, this.d.length, paramOptions);
    if (this.g != -1)
      return BitmapFactory.decodeResource(this.f.getResources(), this.g);
    if (this.h != null)
      return b(this.h, paramOptions);
    throw new RuntimeException("No data to decode.");
  }

  private Bitmap a(String paramString, BitmapFactory.Options paramOptions)
  {
    return BitmapFactory.decodeFile(paramString, paramOptions);
  }

  private Bitmap b(String paramString, BitmapFactory.Options paramOptions)
  {
    Uri localUri = Uri.parse(paramString);
    if (ax.b(paramString, new String[] { "file://", "content://", "android.resource://" }))
    {
      Context localContext = (Context)b.a(Context.class);
      InputStream localInputStream;
      try
      {
        localInputStream = localContext.getContentResolver().openInputStream(localUri);
        if (localInputStream == null)
          return null;
      }
      catch (FileNotFoundException localFileNotFoundException)
      {
        Track.it(localFileNotFoundException);
        return null;
      }
      try
      {
        Bitmap localBitmap = BitmapFactory.decodeStream(localInputStream, new Rect(), paramOptions);
        try
        {
          localInputStream.close();
          return localBitmap;
        }
        catch (IOException localIOException2)
        {
          Track.it(localIOException2);
          return localBitmap;
        }
      }
      finally
      {
      }
      try
      {
        localInputStream.close();
        throw localObject;
      }
      catch (IOException localIOException1)
      {
        while (true)
          Track.it(localIOException1);
      }
    }
    throw new RuntimeException(String.format("Unexpected `%s`", new Object[] { paramString }));
  }

  private BitmapFactory.Options b(BitmapFactory.Options paramOptions)
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inSampleSize = c(paramOptions);
    return localOptions;
  }

  private int c(BitmapFactory.Options paramOptions)
  {
    int i = 1;
    int j = (int)Math.ceil(paramOptions.outHeight / this.a);
    int k = (int)Math.ceil(paramOptions.outWidth / this.b);
    if ((j > i) || (k > i))
    {
      if (j > k)
        i = j;
    }
    else
      return i;
    return k;
  }

  private BitmapFactory.Options e()
  {
    if (this.e == null)
      this.e = f();
    return this.e;
  }

  private BitmapFactory.Options f()
  {
    BitmapFactory.Options localOptions = new BitmapFactory.Options();
    localOptions.inJustDecodeBounds = true;
    a(localOptions);
    return localOptions;
  }

  public f a(float paramFloat1, float paramFloat2)
  {
    this.b = paramFloat1;
    this.a = paramFloat2;
    return this;
  }

  public f a(Context paramContext, int paramInt)
  {
    this.f = paramContext;
    this.g = paramInt;
    return this;
  }

  public f a(String paramString)
  {
    this.h = paramString;
    return this;
  }

  public f a(byte[] paramArrayOfByte)
  {
    this.d = paramArrayOfByte;
    return this;
  }

  public boolean a()
  {
    return f().outWidth != -1;
  }

  public Bitmap b()
  {
    if (this.b != 0.0F);
    for (BitmapFactory.Options localOptions = b(e()); ; localOptions = new BitmapFactory.Options())
      return a(localOptions);
  }

  public f b(String paramString)
  {
    this.c = paramString;
    return this;
  }

  public int c()
  {
    return e().outWidth;
  }

  public int d()
  {
    return e().outHeight;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.f
 * JD-Core Version:    0.6.2
 */