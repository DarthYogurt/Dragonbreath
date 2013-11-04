package com.flipdog.filebrowser.b.a;

import android.graphics.drawable.Drawable;
import com.flipdog.clouds.gdrive.config.GDriveCloudInfo;

public class c extends GDriveCloudInfo
{
  public Drawable a;

  protected c(int paramInt)
  {
    this.a = com.flipdog.filebrowser.k.c.c(paramInt);
  }

  public static com.flipdog.a.b.a.a a()
  {
    return new c(com.flipdog.a.fbrowse_gdrive);
  }

  public static com.flipdog.a.b.a.a a(int paramInt)
  {
    return new c(paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.b.a.c
 * JD-Core Version:    0.6.2
 */