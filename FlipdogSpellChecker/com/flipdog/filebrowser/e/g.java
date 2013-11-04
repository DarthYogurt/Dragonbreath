package com.flipdog.filebrowser.e;

import android.content.Context;
import com.flipdog.a.a.a;

class g
  implements com.flipdog.filebrowser.login.b.b
{
  g(c paramc)
  {
  }

  public void a(Exception paramException, Context paramContext)
  {
    c.a.a(false);
    com.flipdog.filebrowser.d.b.a(paramException, paramContext);
  }

  public void a(Object paramObject)
  {
    if ((paramObject instanceof com.flipdog.a.b.b))
    {
      com.flipdog.a.b.b localb = (com.flipdog.a.b.b)paramObject;
      c.a(this.a).h().a(localb);
      c.a.a(true);
      this.a.a(c.b(this.a));
      return;
    }
    if (paramObject == null);
    for (String str = null; ; str = paramObject.toString())
      throw new RuntimeException(str);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.e.g
 * JD-Core Version:    0.6.2
 */