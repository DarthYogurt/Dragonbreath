package com.flipdog.filebrowser.e;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.flipdog.commons.a.as;
import com.flipdog.commons.actionbar.ActionBarActivity;
import com.flipdog.filebrowser.d.h;
import com.flipdog.m;
import com.flipdog.p;
import java.util.List;

public class f extends b
{
  private final List<com.flipdog.a.b.a.a> g;
  private final h h;

  public f(ActionBarActivity paramActionBarActivity, ListView paramListView, h paramh)
  {
    super(paramActionBarActivity, paramListView, null);
    this.h = paramh;
    this.g = paramh.c();
  }

  public void a(int paramInt)
  {
    throw new RuntimeException();
  }

  protected void a(Object paramObject)
  {
    com.flipdog.a.b.a.a locala = (com.flipdog.a.b.a.a)paramObject;
    this.h.a(locala.a);
  }

  public com.flipdog.a.b.a.a b(int paramInt)
  {
    return (com.flipdog.a.b.a.a)this.g.get(paramInt);
  }

  public void c()
  {
    super.c();
    a.a(true);
  }

  public void d()
  {
    throw new RuntimeException();
  }

  public Object e()
  {
    throw new RuntimeException();
  }

  public void f()
  {
  }

  public boolean g()
  {
    throw new RuntimeException();
  }

  public int getCount()
  {
    return this.g.size();
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    View localView = as.a(paramView, paramViewGroup, a.b, p.fbrowse_submenu_cloud_item);
    com.flipdog.filebrowser.j.b.a locala1;
    if (paramView == null)
    {
      locala1 = new com.flipdog.filebrowser.j.b.a();
      locala1.b = ((TextView)as.a(localView, m.fbrowse_submenu_item_text));
      locala1.a = ((ImageView)as.a(localView, m.fbrowse_submenu_item_icon));
      localView.setTag(locala1);
    }
    for (com.flipdog.filebrowser.j.b.a locala2 = locala1; ; locala2 = (com.flipdog.filebrowser.j.b.a)localView.getTag())
    {
      com.flipdog.a.b.a.a locala = b(paramInt);
      locala2.b.setText(locala.b);
      locala2.a.setImageDrawable(com.flipdog.filebrowser.d.b.a(locala));
      return localView;
    }
  }

  public String[] h()
  {
    return null;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.e.f
 * JD-Core Version:    0.6.2
 */