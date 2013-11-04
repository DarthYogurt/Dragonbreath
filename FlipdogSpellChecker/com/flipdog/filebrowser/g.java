package com.flipdog.filebrowser;

import android.content.Context;
import android.content.Intent;

public class g
{
  public static final int a = 1;
  public static final int b = 2;
  public static final int c = 3;
  public static final int d = 1;
  public static final int e = 2;
  public static final int f = 3;
  public static final String g = "com.flipdog.filebrowser.extra.OK_BUTTON_TEXT";
  public static final String h = "com.flipdog.filebrowser.extra.SELECTION_MODE";
  public static final String i = "com.flipdog.filebrowser.extra.DISPLAY_MODE";
  public static final String j = "com.flipdog.filebrowser.extra.PREFERENCES_ENABLED";
  public static final String k = "com.flipdog.filebrowser.extra.START_FOLDER";
  public static final String l = "com.flipdog.filebrowser.extra.MULTI_SELECT";
  public static final String m = "com.flipdog.filebrowser.extra.SELECTED_PATHS";
  public static final String n = "com.flipdog.filebrowser.extra.DISABLE_CLOUDS";

  public static Intent a(Context paramContext)
  {
    return new h(paramContext).a(f.c).a(c.a).c().d();
  }

  public static Intent a(Context paramContext, boolean paramBoolean)
  {
    h localh = new h(paramContext);
    localh.a(f.c).a(c.b).a();
    if (paramBoolean)
      localh.b();
    return localh.d();
  }

  public static Intent b(Context paramContext)
  {
    return new h(paramContext).a(f.c).a(c.a).c().b().d().setType("image/*");
  }

  public static Intent b(Context paramContext, boolean paramBoolean)
  {
    h localh = new h(paramContext);
    localh.a(f.c).a(c.a).a();
    if (paramBoolean)
      localh.b();
    return localh.d();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.g
 * JD-Core Version:    0.6.2
 */