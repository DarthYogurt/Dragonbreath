package com.flipdog.filebrowser.g;

import com.flipdog.filebrowser.k.c;
import com.flipdog.s;

public class h
{
  public static final int a = 0;
  public static final int b = 1;
  public static final int c = 2;
  public static final int d = 3;
  public static final int e = 4;
  private static int[] f;
  private static int[] g;

  public static g a(int paramInt, com.flipdog.filebrowser.e.b paramb)
  {
    if (paramInt == -1)
      return null;
    return b(paramInt, paramb);
  }

  public static String a(int paramInt)
  {
    if (f == null)
    {
      int[] arrayOfInt = new int[5];
      arrayOfInt[0] = s.fbrowse_oper_rename;
      arrayOfInt[1] = s.fbrowse_oper_remove;
      arrayOfInt[2] = s.fbrowse_oper_information;
      arrayOfInt[3] = s.fbrowse_oper_create_folder;
      arrayOfInt[4] = s.fbrowse_oper_refresh;
      f = arrayOfInt;
    }
    return c.a(f[paramInt]);
  }

  public static String a(g paramg)
  {
    if (paramg == null)
      return null;
    return a(c(paramg));
  }

  public static int[] a(com.flipdog.a.b paramb)
  {
    if (paramb == null)
      return new int[] { 0, 1, 2 };
    return new int[] { 1 };
  }

  private static g b(int paramInt, com.flipdog.filebrowser.e.b paramb)
  {
    switch (paramInt)
    {
    default:
      throw new IllegalArgumentException(Integer.toString(paramInt));
    case 0:
      return new d(paramb);
    case 1:
      return new a(paramb);
    case 2:
      return new b();
    case 4:
      return new i(paramb);
    case 3:
    }
    return new f(paramb);
  }

  public static String b(int paramInt)
  {
    if (g == null)
    {
      int[] arrayOfInt = new int[5];
      arrayOfInt[0] = s.fbrowse_oper_rename_text;
      arrayOfInt[1] = s.fbrowse_oper_remove_text;
      arrayOfInt[2] = s.fbrowse_oper_information_text;
      arrayOfInt[3] = s.fbrowse_oper_create_folder_text;
      g = arrayOfInt;
    }
    return c.a(g[paramInt]);
  }

  public static String b(g paramg)
  {
    if (paramg == null)
      return null;
    return b(c(paramg));
  }

  private static int c(g paramg)
  {
    if ((paramg instanceof d))
      return 0;
    if ((paramg instanceof a))
      return 1;
    if ((paramg instanceof b))
      return 2;
    if ((paramg instanceof f))
      return 3;
    if ((paramg instanceof i))
      return 4;
    if (paramg == null);
    for (String str = ""; ; str = paramg.getClass().getSimpleName())
      throw new RuntimeException(str);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.g.h
 * JD-Core Version:    0.6.2
 */