package com.flipdog.filebrowser.k;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.commons.i.b;
import com.flipdog.filebrowser.d;
import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class j
{
  private static int a = -1;

  public static final int a(DisplayMetrics paramDisplayMetrics)
  {
    int i = (int)(0.5F + 160.0F * paramDisplayMetrics.density);
    Object[] arrayOfObject = new Object[7];
    arrayOfObject[0] = Integer.valueOf(i);
    arrayOfObject[1] = Float.valueOf(paramDisplayMetrics.density);
    arrayOfObject[2] = Integer.valueOf(paramDisplayMetrics.heightPixels);
    arrayOfObject[3] = Float.valueOf(paramDisplayMetrics.scaledDensity);
    arrayOfObject[4] = Integer.valueOf(paramDisplayMetrics.widthPixels);
    arrayOfObject[5] = Float.valueOf(paramDisplayMetrics.xdpi);
    arrayOfObject[6] = Float.valueOf(paramDisplayMetrics.ydpi);
    d.a("DensityDpi = %d (density = %f, heightPixels = %d, scaledDensity = %f, widthPixels = %d, xdpi = %f, ydpi = %f)", arrayOfObject);
    return i;
  }

  public static final File a()
  {
    if (b())
      return Environment.getExternalStorageDirectory();
    return null;
  }

  public static final File a(String paramString)
  {
    if (g() >= 8)
      try
      {
        Context localContext = (Context)b.a(Context.class);
        File localFile = (File)localContext.getClass().getMethod("getExternalFilesDir", new Class[] { String.class }).invoke(localContext, new Object[] { paramString });
        return localFile;
      }
      catch (Exception localException)
      {
        Track.it(localException);
      }
    String str = ((Context)b.a(Context.class)).getPackageName();
    return new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Android/data/" + str + "/files");
  }

  public static final long b(String paramString)
  {
    File localFile = a();
    if (localFile == null)
      return -1L;
    StatFs localStatFs = new StatFs(localFile.getPath());
    return localStatFs.getBlockSize() * localStatFs.getBlockCount();
  }

  public static final boolean b()
  {
    String str = Environment.getExternalStorageState();
    return ("mounted".equals(str)) && (!"mounted_ro".equals(str));
  }

  public static final int c()
  {
    Display localDisplay = ((WindowManager)((Context)b.a(Context.class)).getSystemService("window")).getDefaultDisplay();
    int i;
    if (localDisplay.getWidth() == localDisplay.getHeight())
      i = 3;
    while (true)
    {
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = Integer.valueOf(i);
      arrayOfObject[1] = Integer.valueOf(2);
      arrayOfObject[2] = Integer.valueOf(1);
      d.a("ScreenOrientation = %d (landscape = %d portrait = %d)", arrayOfObject);
      return i;
      if (localDisplay.getWidth() < localDisplay.getHeight())
        i = 1;
      else
        i = 2;
    }
  }

  public static final DisplayMetrics d()
  {
    DisplayMetrics localDisplayMetrics = new DisplayMetrics();
    ((WindowManager)((Context)b.a(Context.class)).getSystemService("window")).getDefaultDisplay().getMetrics(localDisplayMetrics);
    return localDisplayMetrics;
  }

  public static final int e()
  {
    return a(d());
  }

  public static final int f()
  {
    int i = 25;
    int j = e();
    switch (j)
    {
    default:
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = Integer.valueOf(j);
      d.a("Unknown density value: %d", arrayOfObject2);
    case 160:
    case 320:
    case 240:
    case 120:
    }
    while (true)
    {
      Object[] arrayOfObject1 = new Object[1];
      arrayOfObject1[0] = Integer.valueOf(i);
      d.a("Statusbar height = %d", arrayOfObject1);
      return i;
      i = 50;
      continue;
      i = 38;
      continue;
      i = 19;
    }
  }

  public static int g()
  {
    if (a == -1)
      try
      {
        Field localField2 = Class.forName("android.os.Build$VERSION").getField("SDK_INT");
        int j = localField2.getInt(localField2);
        return j;
      }
      catch (Exception localException1)
      {
        try
        {
          Field localField1 = Class.forName("android.os.Build$VERSION").getField("SDK");
          int i = Integer.parseInt((String)localField1.get(localField1));
          return i;
        }
        catch (Exception localException2)
        {
          Track.it(localException2);
          return -1;
        }
      }
    return a;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.k.j
 * JD-Core Version:    0.6.2
 */