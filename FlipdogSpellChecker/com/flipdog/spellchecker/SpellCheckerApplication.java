package com.flipdog.spellchecker;

import android.app.Application;
import android.content.Context;
import com.flipdog.ads.a.f;
import com.flipdog.ads.l;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.commons.i.e;
import com.flipdog.n;

public class SpellCheckerApplication extends Application
{
  private void a()
  {
    m.d.a = c.can_t_spell_check;
    m.d.b = c.cancel_action;
    m.d.c = c.choose_language_title;
    m.d.d = c.license_cant_be_validated;
  }

  private void a(Context paramContext)
  {
    com.flipdog.commons.i.c localc = new com.flipdog.commons.i.c();
    com.flipdog.commons.i.b.a(localc);
    com.flipdog.commons.i.b.a(new e(new com.flipdog.spellchecker.e.a(localc, paramContext)));
  }

  private void b()
  {
    com.flipdog.q.a = n.Commons_Base_Black;
    com.flipdog.filebrowser.e.a = "mbni2o8z49l7xq8";
    com.flipdog.filebrowser.e.b = "44ftppfzawlgsrr";
    com.flipdog.filebrowser.e.c = "l2pu2a85o2upp28e26d9nnmsumwc7vnp";
    com.flipdog.filebrowser.e.d = "3rEAabVlXGWIdDyegbCgghEXwPYtapGj";
  }

  private void c()
  {
    com.flipdog.internal.app.c.a.b = c.chooseActivity;
    com.flipdog.internal.app.c.a.a = c.whichApplication;
    com.flipdog.internal.app.c.c.a = u.resolve_list_item;
  }

  public void onCreate()
  {
    a(getApplicationContext());
    com.flipdog.errors.b.a();
    Track.init(new com.flipdog.commons.diagnostic.b(), com.flipdog.commons.diagnostic.c.i);
    com.flipdog.spellchecker.b.a.a();
    l.a(this);
    com.flipdog.commons.i.b.a(com.flipdog.commons.network.a.class);
    ((f)com.flipdog.commons.i.b.a(f.class)).a(t.english_stopwords);
    a();
    b();
    c();
    com.flipdog.commons.e.b.a();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.SpellCheckerApplication
 * JD-Core Version:    0.6.2
 */