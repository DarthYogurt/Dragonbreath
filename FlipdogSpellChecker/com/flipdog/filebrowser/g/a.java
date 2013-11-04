package com.flipdog.filebrowser.g;

import com.flipdog.activity.MyActivity;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.filebrowser.d;
import com.flipdog.s;
import java.io.File;
import java.util.List;

public class a extends g
  implements com.flipdog.filebrowser.c.a
{
  public a(com.flipdog.filebrowser.e.b paramb)
  {
    super(paramb);
  }

  private void a(com.flipdog.a.b.b.a parama, MyActivity paramMyActivity)
  {
    new com.flipdog.filebrowser.l.a(parama, this, paramMyActivity, this).execute(new Void[0]);
  }

  private void a(MyActivity paramMyActivity)
  {
    a(paramMyActivity, s.fbrowse_object_removed);
  }

  private boolean a(File paramFile, MyActivity paramMyActivity)
  {
    try
    {
      String[] arrayOfString;
      if (paramFile.isDirectory())
        arrayOfString = paramFile.list();
      for (int i = 0; ; i++)
      {
        if (i >= arrayOfString.length)
        {
          if (!paramFile.delete())
            break;
          return true;
        }
        boolean bool = a(new File(paramFile, arrayOfString[i]), paramMyActivity);
        if (!bool)
          break label77;
      }
    }
    catch (Exception localException)
    {
      Track.it(localException);
      a(paramMyActivity, s.fbrowse_object_error_remove);
    }
    label77: return false;
  }

  private void b(Object paramObject, MyActivity paramMyActivity)
  {
    if ((paramObject instanceof File))
    {
      if (a((File)paramObject, paramMyActivity))
      {
        a().d();
        a(paramMyActivity);
      }
      return;
    }
    if ((paramObject instanceof com.flipdog.a.b.b.a))
    {
      a((com.flipdog.a.b.b.a)paramObject, paramMyActivity);
      return;
    }
    throw new RuntimeException(paramObject.toString());
  }

  protected void a(com.flipdog.filebrowser.l.b paramb)
  {
    com.flipdog.a.b.b.a locala = ((com.flipdog.filebrowser.l.a)paramb).a();
    ((com.flipdog.a.b.b.b)a().e()).e.remove(locala);
    a().notifyDataSetChanged();
    a(this.a);
  }

  public void a(Object paramObject, MyActivity paramMyActivity)
  {
    super.a(paramObject, paramMyActivity);
    this.a = paramMyActivity;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.b;
    d.a("Remove object: %s", arrayOfObject);
    a(paramMyActivity, s.fbrowse_do_you_want_remove, new e(this, paramMyActivity));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.g.a
 * JD-Core Version:    0.6.2
 */