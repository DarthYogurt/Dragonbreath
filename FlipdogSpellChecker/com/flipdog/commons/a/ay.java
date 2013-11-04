package com.flipdog.commons.a;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import com.flipdog.commons.i.b;

public class ay
{
  private static ay c;
  private static int d = Integer.parseInt(Build.VERSION.SDK);
  public String a;
  public int b;

  private ay()
  {
    try
    {
      Context localContext = (Context)b.a(Context.class);
      PackageInfo localPackageInfo = localContext.getPackageManager().getPackageInfo(localContext.getPackageName(), 0);
      this.a = localPackageInfo.versionName;
      this.b = localPackageInfo.versionCode;
      return;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      throw new RuntimeException(localNameNotFoundException);
    }
  }

  public static ay a()
  {
    try
    {
      if (c == null)
        c = new ay();
      ay localay = c;
      return localay;
    }
    finally
    {
    }
  }

  public static int b()
  {
    return d;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.ay
 * JD-Core Version:    0.6.2
 */