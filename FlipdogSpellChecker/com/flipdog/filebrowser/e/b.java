package com.flipdog.filebrowser.e;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.flipdog.commons.a.as;
import com.flipdog.commons.actionbar.ActionBarActivity;
import com.flipdog.filebrowser.k.f;
import com.flipdog.filebrowser.k.k;
import com.flipdog.m;
import com.flipdog.p;
import com.flipdog.s;
import java.io.File;
import java.util.List;

public abstract class b extends BaseAdapter
{
  protected static com.flipdog.filebrowser.b.c.b a;
  protected static com.flipdog.filebrowser.b.d b;
  public static int f;
  private static AdapterView.OnItemClickListener h;
  protected List<Object> c = as.b();
  protected final com.flipdog.a.b.a.a d;
  protected int e = 0;
  private final com.flipdog.filebrowser.j.a.a g;
  private View.OnClickListener i = new j(this);
  private View.OnClickListener j = new h(this);

  public b(ActionBarActivity paramActionBarActivity, ListView paramListView, com.flipdog.a.b.a.a parama)
  {
    this.d = parama;
    this.g = new com.flipdog.filebrowser.j.a.a(paramActionBarActivity, this);
    if (a == null)
    {
      a = new com.flipdog.filebrowser.b.c.b(paramActionBarActivity, paramListView);
      b = new com.flipdog.filebrowser.b.d();
      i();
    }
    a.c.setText(null);
    a.g.setText(null);
    a(true, null, 0, 0);
    f = com.flipdog.filebrowser.k.j.c();
  }

  private void a()
  {
  }

  private void a(ImageView paramImageView, Object paramObject)
  {
    if (this.c.indexOf(paramObject) == -1)
    {
      paramImageView.setImageResource(com.flipdog.a.fbrowse_check_off);
      return;
    }
    paramImageView.setImageResource(com.flipdog.a.fbrowse_check_on);
  }

  private void b()
  {
    if (h == null)
    {
      h = new i(this);
      a.h.setOnItemClickListener(h);
    }
  }

  private void i()
  {
    Intent localIntent = a.a.getIntent();
    b.d = localIntent.getBooleanExtra("com.flipdog.filebrowser.extra.DISABLE_CLOUDS", false);
    b.j = localIntent.getIntExtra("com.flipdog.filebrowser.extra.DISPLAY_MODE", 3);
    int k = localIntent.getIntExtra("com.flipdog.filebrowser.extra.SELECTION_MODE", 3);
    com.flipdog.filebrowser.b.d locald1 = b;
    boolean bool1;
    com.flipdog.filebrowser.b.d locald2;
    if ((k & 0x1) != 0)
    {
      bool1 = true;
      locald1.a = bool1;
      locald2 = b;
      if ((k & 0x2) == 0)
        break label169;
    }
    label169: for (boolean bool2 = true; ; bool2 = false)
    {
      locald2.b = bool2;
      b.c = localIntent.getBooleanExtra("com.flipdog.filebrowser.extra.MULTI_SELECT", true);
      if ((b.b) && (!b.a) && (!b.c))
        b.b = false;
      com.flipdog.filebrowser.i.b.a(b, localIntent);
      b.i = localIntent.getStringExtra("com.flipdog.filebrowser.extra.START_FOLDER");
      b.h = a.c;
      return;
      bool1 = false;
      break;
    }
  }

  public static void j()
  {
    a = null;
    h = null;
  }

  protected View a(int paramInt, View paramView, ViewGroup paramViewGroup, boolean paramBoolean, Object paramObject)
  {
    com.flipdog.filebrowser.b.c.a locala1;
    boolean bool;
    if (paramView == null)
    {
      paramView = a.b.inflate(p.fbrowse_listview_item, null);
      com.flipdog.filebrowser.b.c.a locala2 = new com.flipdog.filebrowser.b.c.a(paramView, this.j);
      locala2.g.setOnClickListener(this.i);
      locala2.d.setOnClickListener(this.i);
      locala2.h.setOnClickListener(this.i);
      paramView.setTag(locala2);
      locala1 = locala2;
      if (!paramBoolean)
        break label142;
      bool = b.b;
      label91: if (!bool)
        break label153;
      locala1.a.setVisibility(0);
      a(locala1.a, paramObject);
    }
    while (true)
    {
      locala1.a.setTag(Integer.valueOf(paramInt));
      return paramView;
      locala1 = (com.flipdog.filebrowser.b.c.a)paramView.getTag();
      break;
      label142: bool = b.a;
      break label91;
      label153: locala1.a.setVisibility(4);
    }
  }

  public abstract void a(int paramInt);

  protected void a(File paramFile)
  {
    if (!f.a(a.a, paramFile))
      com.flipdog.filebrowser.k.d.a(a.a, s.fbrowse_open_file_failed);
  }

  protected abstract void a(Object paramObject);

  protected void a(boolean paramBoolean, String paramString, int paramInt1, int paramInt2)
  {
    a.f.setText(Integer.toString(paramInt2));
    a.e.setText(Integer.toString(paramInt1));
  }

  protected Object b(View paramView)
  {
    return getItem(((Integer)paramView.getTag()).intValue());
  }

  public void c()
  {
    b();
    a.h.setTag(this);
    if (this.d != null);
    for (boolean bool = true; ; bool = false)
    {
      k.a(a.i, bool);
      if (bool)
        a.g.setText(this.d.b);
      return;
    }
  }

  public abstract void d();

  public abstract Object e();

  public abstract void f();

  public abstract boolean g();

  public long getItemId(int paramInt)
  {
    return paramInt;
  }

  public abstract String[] h();

  public com.flipdog.filebrowser.b.d k()
  {
    return b;
  }

  protected boolean l()
  {
    return (!b.b) && (!b.a) && (!b.c);
  }

  protected boolean m()
  {
    return a.h.getTag() == this;
  }

  public int n()
  {
    if (this.d == null)
      return com.flipdog.filebrowser.d.h.a;
    return this.d.a;
  }

  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    b.g.a();
    ((TextView)a.a.findViewById(m.fbrowse_textview_tasks)).setText(Integer.toString(this.c.size()));
    a();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.e.b
 * JD-Core Version:    0.6.2
 */