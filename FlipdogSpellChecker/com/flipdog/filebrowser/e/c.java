package com.flipdog.filebrowser.e;

import android.net.Uri;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.flipdog.commons.a.as;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.actionbar.ActionBarActivity;
import com.flipdog.filebrowser.k.e;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class c extends b
  implements com.flipdog.filebrowser.c.a
{
  private com.flipdog.a.b.b.b g = null;
  private com.flipdog.a.b.b.b h;
  private final com.flipdog.a.b i = this.d.a();
  private com.flipdog.filebrowser.b.b.c j;
  private com.flipdog.filebrowser.login.b.b k = new g(this);

  public c(ActionBarActivity paramActionBarActivity, ListView paramListView, com.flipdog.a.b.a.a parama)
  {
    super(paramActionBarActivity, paramListView, parama);
  }

  private void a(com.flipdog.a.b.b.c paramc)
  {
    new com.flipdog.filebrowser.l.f(paramc, this.i, a.a, this).execute(new Void[0]);
  }

  private void b(com.flipdog.a.b.b.b paramb)
  {
    this.g = paramb;
    if (com.flipdog.filebrowser.preference.a.b().h())
      Collections.sort(this.g.e, com.flipdog.filebrowser.d.b.a());
    Iterator localIterator = this.g.e.iterator();
    int m = 0;
    int n = 0;
    while (true)
    {
      if (!localIterator.hasNext())
      {
        com.flipdog.a.b.b.b localb = this.g.c;
        boolean bool = false;
        if (localb == null)
          bool = true;
        a(bool, this.g.a(), n, m);
        this.j.d(paramb);
        notifyDataSetChanged();
        return;
      }
      if (((com.flipdog.a.b.b.a)localIterator.next() instanceof com.flipdog.a.b.b.b))
        n++;
      else
        m++;
    }
  }

  private AsyncTask<Void, Integer, com.flipdog.filebrowser.l.a.b.a> c(com.flipdog.a.b.b.b paramb)
  {
    return new com.flipdog.filebrowser.l.d(this.i, paramb, a.a, this).execute(new Void[0]);
  }

  public void a(int paramInt)
  {
    if (paramInt == 3)
    {
      this.e -= this.c.size();
      this.c.clear();
    }
    while (true)
    {
      notifyDataSetChanged();
      return;
      Iterator localIterator = this.g.e.iterator();
      while (localIterator.hasNext())
      {
        com.flipdog.a.b.b.a locala = (com.flipdog.a.b.b.a)localIterator.next();
        if ((locala instanceof com.flipdog.a.b.b.c))
        {
          int m = this.c.indexOf(locala);
          if (paramInt == 1)
          {
            if (m == -1)
            {
              this.c.add(locala);
              this.e = (1 + this.e);
            }
          }
          else if (m != -1)
          {
            this.c.remove(m);
            this.e = (-1 + this.e);
          }
        }
      }
    }
  }

  public void a(com.flipdog.a.b.b.b paramb)
  {
    if ((paramb != null) && (paramb.c()))
    {
      b(paramb);
      return;
    }
    c(paramb);
  }

  public void a(com.flipdog.filebrowser.l.a.b.a parama)
  {
    com.flipdog.a.g.b localb = parama.c;
    if (localb != null)
    {
      com.flipdog.filebrowser.d.b.a(localb, a.a);
      return;
    }
    if ((parama instanceof com.flipdog.filebrowser.l.a.a.b))
    {
      com.flipdog.a.b.b.b localb1 = (com.flipdog.a.b.b.b)((com.flipdog.filebrowser.l.a.a.b)parama).a;
      if ((this.h == null) && (localb1.c == null));
      for (int m = 1; ; m = 0)
      {
        if (m != 0)
          this.h = localb1;
        localb1.e = this.j.b(localb1);
        if (m == 0)
          break;
        this.j.a(localb1, 0);
        return;
      }
      b(localb1);
      return;
    }
    if ((parama instanceof com.flipdog.filebrowser.l.a.a.a))
    {
      a(((com.flipdog.filebrowser.l.a.a.a)parama).a);
      return;
    }
    throw new RuntimeException(parama.toString());
  }

  protected void a(Object paramObject)
  {
    if ((paramObject instanceof com.flipdog.a.b.b.b))
    {
      com.flipdog.a.b.b.b localb = (com.flipdog.a.b.b.b)paramObject;
      this.j.a(localb, a.h.getFirstVisiblePosition());
      return;
    }
    a((com.flipdog.a.b.b.c)paramObject);
  }

  public void a(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.i.a(null);
      List localList = this.i.h().b_();
      if (localList.isEmpty())
      {
        com.flipdog.filebrowser.d.b.a(this.d.b, a.a, this.i, this.k);
        return;
      }
      this.i.a((com.flipdog.a.b.b)localList.get(0));
      a(this.g);
      a.a(true);
      return;
    }
    this.i.i();
    a(true, null, 0, 0);
    this.g = null;
    a.a(false);
    this.j.a();
    this.c = as.b();
  }

  public boolean a()
  {
    return this.i.j() != null;
  }

  public com.flipdog.a.b.b.b b()
  {
    return this.h;
  }

  public void c()
  {
    super.c();
    this.j = new com.flipdog.filebrowser.b.b.c(b, this, a.h);
    if (this.i.j() == null)
    {
      List localList = this.i.h().b_();
      if (localList.isEmpty())
      {
        a(false);
        return;
      }
      this.i.a((com.flipdog.a.b.b)localList.get(0));
    }
    a.a(true);
    a(this.g);
  }

  public void d()
  {
    if (this.g != null)
      c(this.g);
  }

  public Object e()
  {
    return this.g;
  }

  public void f()
  {
    this.i.a(null);
  }

  public boolean g()
  {
    return this.j.a(this.g);
  }

  public int getCount()
  {
    if (this.g == null)
      return 0;
    return this.g.e.size();
  }

  public Object getItem(int paramInt)
  {
    return this.g.e.get(paramInt);
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    int m = 1;
    com.flipdog.a.b.b.a locala = (com.flipdog.a.b.b.a)getItem(paramInt);
    Object localObject;
    com.flipdog.a.b.b.b localb1;
    label44: View localView;
    com.flipdog.filebrowser.b.c.a locala1;
    String str1;
    String str2;
    if ((locala instanceof com.flipdog.a.b.b.b))
    {
      com.flipdog.a.b.b.b localb2 = (com.flipdog.a.b.b.b)locala;
      localObject = null;
      localb1 = localb2;
      if (localb1 == null)
        break label225;
      int i1 = m;
      localView = super.a(paramInt, paramView, paramViewGroup, i1, localObject);
      locala1 = (com.flipdog.filebrowser.b.c.a)localView.getTag();
      str1 = locala.a();
      locala1.c.setText(str1);
      locala1.c.setTextSize(com.flipdog.filebrowser.preference.a.b().d());
      if (localObject != null)
        break label231;
      str2 = null;
      label107: int i3 = -2;
      if (f != m)
        break label244;
      locala1.e.setVisibility(8);
      if (str2 == null)
        i3 = b.g.b();
      label145: if (str2 != null)
        break label370;
      locala1.d.setVisibility(8);
      locala1.h.getLayoutParams().width = i3;
      locala1.h.setTag(Integer.valueOf(paramInt));
      if (localb1 == null)
        break label456;
    }
    while (true)
    {
      int i6 = com.flipdog.filebrowser.d.b.a(m, str1);
      locala1.b.setImageResource(i6);
      return localView;
      localObject = (com.flipdog.a.b.b.c)locala;
      localb1 = null;
      break;
      label225: int i2 = 0;
      break label44;
      label231: str2 = com.flipdog.filebrowser.k.f.a(((com.flipdog.a.b.b.c)localObject).e);
      break label107;
      label244: locala1.e.setVisibility(0);
      locala1.g.setText(com.flipdog.filebrowser.i.c.d(locala.b));
      locala1.g.setTextSize(com.flipdog.filebrowser.preference.a.b().e());
      locala1.g.getLayoutParams().width = b.g.c();
      locala1.g.setTag(Integer.valueOf(paramInt));
      locala1.f.setText(com.flipdog.filebrowser.i.c.c(locala.b));
      locala1.f.setTextSize(com.flipdog.filebrowser.preference.a.b().e());
      locala1.f.getLayoutParams().width = b.g.d();
      break label145;
      label370: locala1.d.setVisibility(0);
      locala1.d.setText(str2);
      locala1.d.setTag(Integer.valueOf(paramInt));
      locala1.d.setTextSize(com.flipdog.filebrowser.preference.a.b().e());
      TextView localTextView = locala1.d;
      if ((str2 != null) && (f == m));
      int i5;
      for (int i4 = m; ; i5 = 0)
      {
        localTextView.setClickable(i4);
        break;
      }
      label456: int n = 0;
    }
  }

  public String[] h()
  {
    if (l())
    {
      this.c.clear();
      if (m())
        this.c.add(this.g);
    }
    com.flipdog.a.b.b localb = this.i.j();
    if (localb == null)
      return null;
    List localList = as.b();
    String str1 = com.flipdog.filebrowser.d.g.a(this.d.a);
    Iterator localIterator = this.c.iterator();
    com.flipdog.a.b.b.a locala;
    do
    {
      if (!localIterator.hasNext())
        return (String[])as.a(localList, String.class);
      locala = (com.flipdog.a.b.b.a)localIterator.next();
    }
    while (locala == null);
    StringBuilder localStringBuilder = new StringBuilder(str1);
    boolean bool = ax.a(locala.d);
    if (bool)
      localStringBuilder.append(locala.a);
    while (true)
    {
      localStringBuilder.append("?username=").append(e.a(localb.a));
      localStringBuilder.append("&password=").append(e.a(localb.b));
      if ((locala instanceof com.flipdog.a.b.b.c))
      {
        com.flipdog.a.b.b.c localc = (com.flipdog.a.b.b.c)locala;
        String str2 = localc.f;
        if (ax.a(str2))
          str2 = com.flipdog.filebrowser.k.f.f(localc.a());
        localc.f = str2;
        if (!ax.a(str2))
          localStringBuilder.append("&mimeType=").append(e.a(str2));
        localStringBuilder.append("&length=").append(localc.e);
      }
      if (!bool)
        localStringBuilder.append("&name=").append(Uri.encode(locala.a));
      localList.add(localStringBuilder.toString());
      break;
      localStringBuilder.append("/").append(locala.d);
    }
  }

  public com.flipdog.a.b i()
  {
    return this.i;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.e.c
 * JD-Core Version:    0.6.2
 */