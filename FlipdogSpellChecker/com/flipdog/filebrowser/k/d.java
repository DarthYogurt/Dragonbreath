package com.flipdog.filebrowser.k;

import android.content.Context;
import android.widget.Toast;
import com.flipdog.commons.i.b;

public class d extends a
{
  private static int f = 1;

  public static final int a()
  {
    return f;
  }

  public static final void a(int paramInt)
  {
    a(c(paramInt), f);
  }

  public static final void a(int paramInt1, int paramInt2)
  {
    a(c(paramInt1), paramInt2);
  }

  public static final void a(int paramInt, Object[] paramArrayOfObject)
  {
    a(c.a(paramInt), paramArrayOfObject);
  }

  public static final void a(Context paramContext, int paramInt)
  {
    a(paramContext, c(paramInt));
  }

  public static final void a(Context paramContext, int paramInt1, int paramInt2)
  {
    a(paramContext, c(paramInt1), paramInt2);
  }

  public static final void a(Context paramContext, String paramString)
  {
    a(paramContext, paramString, f);
  }

  public static final void a(Context paramContext, String paramString, int paramInt)
  {
    a(b(paramContext, paramString, paramInt));
  }

  private static final void a(Toast paramToast)
  {
    a(paramToast, f);
  }

  private static final void a(Toast paramToast, int paramInt)
  {
    paramToast.setGravity(1, 0, 100);
    paramToast.setDuration(paramInt);
    paramToast.show();
  }

  public static final void a(String paramString)
  {
    a(paramString, f);
  }

  public static final void a(String paramString, int paramInt)
  {
    a((Context)b.a(Context.class), paramString, paramInt);
  }

  public static final void a(String paramString, Object[] paramArrayOfObject)
  {
    a(String.format(paramString, paramArrayOfObject), f);
  }

  private static final Toast b(Context paramContext, String paramString, int paramInt)
  {
    if (paramContext == null);
    for (Context localContext = (Context)b.a(Context.class); ; localContext = paramContext)
    {
      Toast localToast = Toast.makeText(localContext, paramString, paramInt);
      localToast.setText(paramString);
      return localToast;
    }
  }

  public static final void b(int paramInt)
  {
    f = paramInt;
  }

  private static String c(int paramInt)
  {
    return c.a(paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.k.d
 * JD-Core Version:    0.6.2
 */