package com.flipdog.filebrowser;

import android.content.Context;
import android.content.Intent;

public class h
{
  private Intent a = a(paramContext);

  public h(Context paramContext)
  {
  }

  private Intent a(Context paramContext)
  {
    try
    {
      Class localClass = Class.forName("com.flipdog.filebrowser.FileBrowserActivity");
      return new Intent(paramContext, localClass);
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new RuntimeException(localClassNotFoundException);
    }
  }

  public h a()
  {
    this.a.putExtra("com.flipdog.filebrowser.extra.MULTI_SELECT", false);
    return this;
  }

  public h a(c paramc)
  {
    if (paramc == c.a)
    {
      this.a.putExtra("com.flipdog.filebrowser.extra.SELECTION_MODE", 1);
      return this;
    }
    if (paramc == c.b)
    {
      this.a.putExtra("com.flipdog.filebrowser.extra.SELECTION_MODE", 2);
      return this;
    }
    if (paramc == c.c)
    {
      this.a.putExtra("com.flipdog.filebrowser.extra.SELECTION_MODE", 3);
      return this;
    }
    throw new RuntimeException("Unexpected " + paramc);
  }

  public h a(f paramf)
  {
    if (paramf == f.a)
    {
      this.a.putExtra("com.flipdog.filebrowser.extra.DISPLAY_MODE", 1);
      return this;
    }
    if (paramf == f.b)
    {
      this.a.putExtra("com.flipdog.filebrowser.extra.DISPLAY_MODE", 2);
      return this;
    }
    if (paramf == f.c)
    {
      this.a.putExtra("com.flipdog.filebrowser.extra.DISPLAY_MODE", 3);
      return this;
    }
    throw new RuntimeException("Unexpected " + paramf);
  }

  public h a(boolean paramBoolean)
  {
    this.a.putExtra("com.flipdog.filebrowser.extra.PREFERENCES_ENABLED", paramBoolean);
    return this;
  }

  public h b()
  {
    this.a.putExtra("com.flipdog.filebrowser.extra.DISABLE_CLOUDS", true);
    return this;
  }

  public h c()
  {
    this.a.putExtra("com.flipdog.filebrowser.extra.MULTI_SELECT", true);
    return this;
  }

  public Intent d()
  {
    return this.a;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.h
 * JD-Core Version:    0.6.2
 */