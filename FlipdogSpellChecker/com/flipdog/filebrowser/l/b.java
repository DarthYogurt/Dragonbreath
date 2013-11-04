package com.flipdog.filebrowser.l;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.errors.activity.ErrorActivity;
import com.flipdog.filebrowser.d;
import com.flipdog.filebrowser.e.c;
import com.flipdog.filebrowser.g.g;
import com.flipdog.filebrowser.g.h;

public abstract class b extends AsyncTask<Void, Integer, com.flipdog.filebrowser.l.a.b.a>
{
  protected final MyActivity a;
  private g b;
  private com.flipdog.a.b c;
  private Exception d;
  private final com.flipdog.filebrowser.c.a e;
  private ProgressDialog f;

  public b(Object paramObject, MyActivity paramMyActivity)
  {
    a(paramObject);
    this.a = paramMyActivity;
    this.e = ((com.flipdog.filebrowser.c.a)paramMyActivity);
  }

  public b(Object paramObject, MyActivity paramMyActivity, com.flipdog.filebrowser.c.a parama)
  {
    this.a = paramMyActivity;
    this.e = parama;
    a(paramObject);
  }

  private void a(Object paramObject)
  {
    if ((paramObject instanceof g))
    {
      this.b = ((g)paramObject);
      localc = g();
      if (localc == null)
        this.c = null;
    }
    while (!(paramObject instanceof com.flipdog.a.b))
    {
      c localc;
      return;
      this.c = localc.i();
      return;
    }
    this.b = null;
    this.c = ((com.flipdog.a.b)paramObject);
  }

  private boolean a()
  {
    if (this.d != null);
    try
    {
      Track.it(this.d);
      ErrorActivity.a(this.a, this.d);
      if (this.d != null)
        return true;
    }
    catch (Exception localException)
    {
      while (true)
        Track.it(localException);
    }
    return false;
  }

  protected com.flipdog.filebrowser.l.a.b.a a(Void[] paramArrayOfVoid)
  {
    try
    {
      Object[] arrayOfObject = new Object[1];
      arrayOfObject[0] = getClass().getSimpleName();
      d.a("Start task: %s", arrayOfObject);
      if (c())
        return null;
      com.flipdog.filebrowser.l.a.b.a locala = b();
      d.a("Task result: %s", new Object[] { locala });
      return locala;
    }
    catch (Exception localException)
    {
      this.d = localException;
    }
    return null;
  }

  protected void a(com.flipdog.filebrowser.l.a.b.a parama)
  {
    boolean bool = c();
    d();
    if ((!a()) && (!bool) && (parama != null) && (this.e != null))
      this.e.a(parama);
  }

  protected abstract com.flipdog.filebrowser.l.a.b.a b()
    throws Exception;

  protected boolean c()
  {
    if ((this.f != null) && (this.f.isShowing()))
      return false;
    d.a("BaseAsyncTask cancelled", new Object[0]);
    return true;
  }

  protected void d()
  {
    try
    {
      if ((this.f != null) && (this.f.isShowing()))
        this.f.dismiss();
      return;
    }
    catch (Exception localException)
    {
      Track.it(localException);
      this.f = null;
    }
  }

  protected String e()
  {
    return h.b(this.b);
  }

  protected com.flipdog.a.b f()
  {
    return this.c;
  }

  protected c g()
  {
    return (c)this.b.a();
  }

  protected void onPreExecute()
  {
    try
    {
      this.f = new ProgressDialog(this.a);
      this.f.setMessage(e());
      this.f.show();
      return;
    }
    catch (Exception localException)
    {
      Track.it(localException);
      d();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.l.b
 * JD-Core Version:    0.6.2
 */