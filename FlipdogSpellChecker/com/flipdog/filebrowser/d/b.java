package com.flipdog.filebrowser.d;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.graphics.drawable.Drawable;
import com.a.a.c.j;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.ax;
import com.flipdog.filebrowser.k.f;
import com.flipdog.filebrowser.login.logic.BaseLoginActivity;
import com.flipdog.s;
import java.util.Comparator;
import java.util.UUID;

public class b
{
  private static Comparator<com.flipdog.a.b.b.a> a = null;

  public static int a(boolean paramBoolean, String paramString)
  {
    int i = 1;
    if (paramBoolean)
      return com.flipdog.a.fbrowse_type_folder;
    String str1 = f.f(paramString);
    int j;
    if (str1 == null)
    {
      String str2 = f.a(paramString);
      if ((!"bmp".equals(str2)) && (!"tif".equals(str2)) && (!"tiff".equals(str2)) && (!"png".equals(str2)) && (!"jpg".equals(str2)))
      {
        boolean bool = "jpeg".equals(str2);
        j = 0;
        if (bool);
      }
      while (j != 0)
      {
        return com.flipdog.a.fbrowse_type_image;
        j = i;
      }
    }
    if (str1.indexOf("image") != -1);
    while (true)
    {
      j = i;
      break;
      i = 0;
    }
    return com.flipdog.a.fbrowse_type_unknown;
  }

  public static Drawable a(com.flipdog.a.b.a.a parama)
  {
    if ((parama instanceof com.flipdog.filebrowser.b.a.a))
      return ((com.flipdog.filebrowser.b.a.a)parama).d;
    if ((parama instanceof com.flipdog.filebrowser.b.a.d))
      return ((com.flipdog.filebrowser.b.a.d)parama).d;
    if ((parama instanceof com.flipdog.filebrowser.b.a.b))
      return ((com.flipdog.filebrowser.b.a.b)parama).d;
    if ((parama instanceof com.flipdog.filebrowser.b.a.c))
      return ((com.flipdog.filebrowser.b.a.c)parama).a;
    throw new RuntimeException(parama.toString());
  }

  public static Comparator<com.flipdog.a.b.b.a> a()
  {
    if (a == null)
      a = new d();
    return a;
  }

  public static void a(Context paramContext, String paramString)
  {
    new AlertDialog.Builder(paramContext).setCancelable(true).setMessage(paramString).setPositiveButton(17039370, null).create().show();
  }

  public static void a(Exception paramException, Context paramContext)
  {
    int i;
    if ((paramException instanceof com.flipdog.a.g.c))
      i = s.fbrowse_clouds_exc_host;
    String str;
    while (true)
    {
      a(paramContext, com.flipdog.filebrowser.k.c.a(i));
      return;
      if ((paramException instanceof com.flipdog.a.g.e))
      {
        i = s.fbrowse_clouds_exc_conn;
      }
      else
      {
        if (!(paramException instanceof com.flipdog.a.g.a))
          break label66;
        str = paramException.getMessage();
        if (!ax.a(str))
          break;
        i = s.fbrowse_clouds_exc_auth_falied;
      }
    }
    a(paramContext, str);
    return;
    label66: b(paramException, paramContext);
  }

  public static void a(String paramString, MyActivity paramMyActivity, com.flipdog.a.b paramb, com.flipdog.filebrowser.login.b.b paramb1)
  {
    if (paramb.g())
    {
      com.flipdog.a.b.a locala = new com.flipdog.a.b.a(UUID.randomUUID().toString(), "hidden");
      locala.c = paramMyActivity;
      locala.e = paramb;
      locala.d = new e(paramb1);
      try
      {
        paramb.a(locala);
        return;
      }
      catch (com.flipdog.a.g.b localb)
      {
        paramb1.a(localb, paramMyActivity);
        return;
      }
    }
    c localc = new c(paramMyActivity, paramb);
    com.flipdog.filebrowser.login.a.b localb1 = new com.flipdog.filebrowser.login.a.b();
    localb1.a = paramMyActivity;
    localb1.b = (paramString + " " + com.flipdog.filebrowser.k.c.a(s.fbrowse_clouds_login_wait));
    localb1.d = paramb1;
    localb1.e = localc;
    BaseLoginActivity.a(localb1);
  }

  private static void b(Exception paramException, Context paramContext)
  {
    if ((paramException instanceof com.flipdog.a.g.b))
    {
      Throwable localThrowable = paramException.getCause();
      if ((localThrowable != null) && ((localThrowable instanceof j)))
        if (((j)localThrowable).t != 403)
          break label81;
    }
    label81: for (int i = s.fbrowse_clouds_dropbox_forbidden; ; i = 0)
    {
      if (i != 0);
      for (String str = com.flipdog.filebrowser.k.c.a(i); ; str = null)
      {
        if (str == null)
          str = paramException.getMessage();
        if (str == null)
          str = paramException.toString();
        a(paramContext, str);
        return;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.d.b
 * JD-Core Version:    0.6.2
 */