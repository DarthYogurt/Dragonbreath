package com.flipdog.filebrowser.d;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.flipdog.commons.a.as;
import com.flipdog.commons.actionbar.ActionBarActivity;
import com.flipdog.filebrowser.b.d;
import com.flipdog.filebrowser.e.b;
import com.flipdog.m;
import java.util.Iterator;
import java.util.List;

public class h
{
  public static int a = -2;
  public static int b = -1;
  private final List<b> c = as.b();
  private int d;
  private final ActionBarActivity e;
  private final ListView f;
  private View.OnClickListener g = new f(this);

  public h(ActionBarActivity paramActionBarActivity, ListView paramListView)
  {
    this.e = paramActionBarActivity;
    this.f = paramListView;
    g();
  }

  private void g()
  {
    as.a(this.e, m.fbrowse_select_root).setOnClickListener(this.g);
    as.a(this.e, m.fbrowse_textview_path_root).setOnClickListener(this.g);
  }

  public void a()
  {
    g.a();
    com.flipdog.a.b.a.a locala = com.flipdog.a.h.c.a(b);
    com.flipdog.filebrowser.e.a locala1 = new com.flipdog.filebrowser.e.a(this.e, this.f, locala);
    this.c.add(locala1);
    if (locala1.k().d)
    {
      locala1.a();
      a(b);
      return;
    }
    com.flipdog.filebrowser.e.f localf = new com.flipdog.filebrowser.e.f(this.e, this.f, this);
    this.c.add(0, localf);
    this.d = 0;
    a(a);
  }

  public boolean a(int paramInt)
  {
    this.d = 0;
    Object localObject;
    com.flipdog.a.b.a.a locala;
    Iterator localIterator;
    if (paramInt == a)
    {
      localObject = (b)this.c.get(0);
      if (!(localObject instanceof com.flipdog.filebrowser.e.f))
        return false;
    }
    else
    {
      locala = com.flipdog.a.h.c.a(paramInt);
      localIterator = this.c.iterator();
    }
    while (true)
    {
      if (!localIterator.hasNext())
        localObject = null;
      do
      {
        if (localObject == null)
        {
          localObject = new com.flipdog.filebrowser.e.c(this.e, this.f, locala);
          this.c.add(localObject);
        }
        this.f.setAdapter((ListAdapter)localObject);
        ((b)localObject).c();
        this.e.f();
        return true;
        localObject = (b)localIterator.next();
      }
      while (((b)localObject).n() == paramInt);
      this.d = (1 + this.d);
    }
  }

  public b b()
  {
    return (b)this.c.get(this.d);
  }

  public List<com.flipdog.a.b.a.a> c()
  {
    return com.flipdog.a.h.c.a();
  }

  public void d()
  {
    Iterator localIterator = this.c.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      b localb = (b)localIterator.next();
      localb.f();
      if ((localb instanceof com.flipdog.filebrowser.e.a))
        ((com.flipdog.filebrowser.e.a)localb).b();
    }
  }

  public String[] e()
  {
    List localList = as.b();
    Iterator localIterator = this.c.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return (String[])as.a(localList, String.class);
      String[] arrayOfString = ((b)localIterator.next()).h();
      if (arrayOfString != null)
        localList.addAll(as.b(arrayOfString));
    }
  }

  public ActionBarActivity f()
  {
    return this.e;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.d.h
 * JD-Core Version:    0.6.2
 */