package com.flipdog.filebrowser.h;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.filebrowser.a.e;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class b extends d
{
  private static final int b = 40;
  private static final int c = 20;
  private BlockingQueue<com.flipdog.filebrowser.h.b.a> a = c();
  private int d = 0;
  private final int e = 24;

  private Bitmap a(File paramFile)
    throws FileNotFoundException
  {
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = paramFile.getName();
    com.flipdog.filebrowser.d.a("Try decode image %s", arrayOfObject1);
    BitmapFactory.Options localOptions1 = new BitmapFactory.Options();
    localOptions1.inJustDecodeBounds = true;
    BitmapFactory.decodeStream(new FileInputStream(paramFile), null, localOptions1);
    int i = localOptions1.outWidth;
    int j = localOptions1.outHeight;
    int k = i;
    int m = j;
    int n = 1;
    Bitmap localBitmap;
    while (true)
    {
      if ((k < 24) || (m < 24))
      {
        BitmapFactory.Options localOptions2 = new BitmapFactory.Options();
        localOptions2.inSampleSize = n;
        localBitmap = BitmapFactory.decodeStream(new FileInputStream(paramFile), null, localOptions2);
        if (localBitmap != null)
          break;
        Object[] arrayOfObject3 = new Object[5];
        arrayOfObject3[0] = paramFile.getName();
        arrayOfObject3[1] = Integer.valueOf(localOptions1.outWidth);
        arrayOfObject3[2] = Integer.valueOf(localOptions1.outHeight);
        arrayOfObject3[3] = Integer.valueOf(n);
        arrayOfObject3[4] = localOptions1.outMimeType;
        com.flipdog.filebrowser.d.a("Error decode image: %s (source: %d x %d) scale = %d mime = %s", arrayOfObject3);
        return localBitmap;
      }
      k /= 2;
      m /= 2;
      n *= 2;
    }
    Object[] arrayOfObject2 = new Object[5];
    arrayOfObject2[0] = Integer.valueOf(localBitmap.getWidth());
    arrayOfObject2[1] = Integer.valueOf(localBitmap.getHeight());
    arrayOfObject2[2] = Integer.valueOf(localOptions1.outWidth);
    arrayOfObject2[3] = Integer.valueOf(localOptions1.outHeight);
    arrayOfObject2[4] = Integer.valueOf(n);
    com.flipdog.filebrowser.d.a("Result image size: %d x %d (source: %d x %d) scale = %d", arrayOfObject2);
    return localBitmap;
  }

  private void a(com.flipdog.filebrowser.h.b.c paramc)
  {
    try
    {
      Bitmap localBitmap = a(paramc.c);
      if (localBitmap != null)
        com.flipdog.filebrowser.f.a.a.a(localBitmap, paramc.c);
      this.d = (1 + this.d);
      if (this.d == 6)
        this.d = 0;
      int i = this.a.size();
      if ((i == 0) || (this.d == 0))
      {
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i);
        com.flipdog.filebrowser.d.a("Update listview from background (%d)", arrayOfObject);
        paramc.b.a(paramc);
      }
      return;
    }
    catch (Exception localException)
    {
      Track.it(localException);
    }
  }

  private void b(com.flipdog.filebrowser.h.b.a parama)
  {
    try
    {
      f();
      com.flipdog.filebrowser.d.a("Task scheduled: %s", new Object[] { parama });
      this.a.add(parama);
      return;
    }
    finally
    {
      g();
    }
  }

  private BlockingQueue<com.flipdog.filebrowser.h.b.a> c()
  {
    return new PriorityBlockingQueue(200, new a());
  }

  private com.flipdog.filebrowser.h.b.a d()
    throws InterruptedException
  {
    int i = this.a.size();
    if (i < 3)
      Thread.sleep(300L);
    while (i <= 40)
      return (com.flipdog.filebrowser.h.b.a)this.a.take();
    while (true)
    {
      BlockingQueue localBlockingQueue;
      int j;
      com.flipdog.filebrowser.h.b.a locala;
      try
      {
        f();
        Object[] arrayOfObject = new Object[1];
        arrayOfObject[0] = Integer.valueOf(i);
        com.flipdog.filebrowser.d.a("Clean background queue. Current size: %d", arrayOfObject);
        localBlockingQueue = c();
        j = 0;
        break label201;
        if (this.a.isEmpty())
        {
          this.a = localBlockingQueue;
          g();
          break;
          localBlockingQueue.put((com.flipdog.filebrowser.h.b.a)this.a.take());
          j = k;
          break label201;
        }
        locala = (com.flipdog.filebrowser.h.b.a)this.a.take();
        if ((locala instanceof com.flipdog.filebrowser.h.b.c))
        {
          com.flipdog.filebrowser.h.b.c localc = (com.flipdog.filebrowser.h.b.c)locala;
          com.flipdog.filebrowser.f.a.a.b(localc.c);
          com.flipdog.filebrowser.d.a("Drop task: %s", new Object[] { locala });
          continue;
        }
      }
      finally
      {
        g();
      }
      localBlockingQueue.put(locala);
      continue;
      label201: int k = j + 1;
      if (j < 20);
    }
  }

  public void a()
  {
    com.flipdog.commons.f.c.a(new c(this));
  }

  public void a(com.flipdog.filebrowser.h.b.a parama)
  {
    try
    {
      b(parama);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  public void a(File paramFile, com.flipdog.filebrowser.h.a.a parama)
  {
    try
    {
      b(new com.flipdog.filebrowser.h.b.c(paramFile, parama));
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }

  protected void b()
  {
    com.flipdog.filebrowser.d.a("BackgroundProcessor started", new Object[0]);
    com.flipdog.filebrowser.h.b.a locala;
    while (true)
    {
      try
      {
        locala = d();
        com.flipdog.filebrowser.d.a("Start task: %s", new Object[] { locala });
        if (!(locala instanceof com.flipdog.filebrowser.h.b.c))
          break label53;
        a((com.flipdog.filebrowser.h.b.c)locala);
        continue;
      }
      catch (Exception localException)
      {
        Track.it(localException);
      }
      continue;
      label53: if (!(locala instanceof com.flipdog.filebrowser.h.b.b))
        break;
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(this.a.size());
      com.flipdog.filebrowser.d.a("Clear background queue (%d)", arrayOfObject2);
      this.a.clear();
    }
    Object[] arrayOfObject1 = new Object[1];
    arrayOfObject1[0] = locala.toString();
    throw new RuntimeException(String.format("Unknown task: %s", arrayOfObject1));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.h.b
 * JD-Core Version:    0.6.2
 */