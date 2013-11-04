package com.flipdog.commons.e;

import android.content.Context;
import com.flipdog.activity.f;
import com.flipdog.commons.f.c;

public class a
{
  public static void a(Context paramContext, f paramf, String paramString, o paramo)
  {
    com.flipdog.commons.l.a locala = new com.flipdog.commons.l.a();
    j localj = new j(paramContext, paramf, paramString, locala);
    c.a(new i(paramo, locala, localj.b(), localj, paramf));
  }

  protected static void a(f paramf, Exception paramException)
  {
    paramf.a(new l(paramException, paramf));
  }

  protected static void a(f paramf, String paramString)
  {
    paramf.a(new k(paramString));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.e.a
 * JD-Core Version:    0.6.2
 */