package android.support.v4.util;

import android.os.Build.VERSION;

public class BuildVersion
{
  private static int versionCode = Integer.parseInt(Build.VERSION.SDK);

  public static int getCode()
  {
    return versionCode;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     android.support.v4.util.BuildVersion
 * JD-Core Version:    0.6.2
 */