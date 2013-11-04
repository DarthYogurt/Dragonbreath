package com.flipdog.filebrowser.e;

import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.flipdog.commons.actionbar.ActionBarActivity;
import com.flipdog.errors.activity.ErrorActivity;
import com.flipdog.filebrowser.k.k;
import com.flipdog.m;
import java.io.File;
import java.util.Arrays;
import java.util.List;

public class a extends b
  implements com.flipdog.filebrowser.h.a.a
{
  private com.flipdog.filebrowser.b.b.d g;
  private com.flipdog.filebrowser.a.e h = com.flipdog.filebrowser.f.a.a;
  private File[] i;
  private String j;

  public a(ActionBarActivity paramActionBarActivity, ListView paramListView, com.flipdog.a.b.a.a parama)
  {
    super(paramActionBarActivity, paramListView, parama);
    try
    {
      this.g = new com.flipdog.filebrowser.b.b.d(b, this);
      this.j = this.g.b().getAbsolutePath();
      if (com.flipdog.filebrowser.f.a.b == null)
      {
        com.flipdog.filebrowser.f.a.b = new com.flipdog.filebrowser.h.b();
        com.flipdog.filebrowser.f.a.b.a();
      }
      k.a(a.a, m.button_refresh, null, new e(this));
      return;
    }
    catch (Exception localException)
    {
      ErrorActivity.a(paramActionBarActivity, localException);
    }
  }

  private void o()
  {
    int k = 0;
    this.j = this.g.b().getAbsolutePath();
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.g.a();
    com.flipdog.filebrowser.d.a("Open folder: %s", arrayOfObject);
    this.i = this.g.e();
    if (com.flipdog.filebrowser.preference.a.b().h())
      Arrays.sort(this.i, com.flipdog.filebrowser.i.b.a());
    File[] arrayOfFile = this.i;
    int m = arrayOfFile.length;
    int n = 0;
    int i1 = 0;
    if (n >= m)
    {
      a(this.g.c(), this.g.a(), i1, k);
      return;
    }
    if (arrayOfFile[n].isDirectory())
      i1++;
    while (true)
    {
      n++;
      break;
      k++;
    }
  }

  protected File a(View paramView)
  {
    return (File)super.b(paramView);
  }

  public void a()
  {
    a.g.setVisibility(8);
  }

  public void a(int paramInt)
  {
    File[] arrayOfFile = this.i;
    int k = arrayOfFile.length;
    int m = 0;
    if (m >= k)
    {
      notifyDataSetChanged();
      return;
    }
    File localFile = arrayOfFile[m];
    int n;
    if (localFile.isFile())
    {
      n = this.c.indexOf(localFile);
      if (paramInt != 1)
        break label88;
      if (n == -1)
      {
        this.c.add(localFile);
        this.e = (1 + this.e);
      }
    }
    while (true)
    {
      m++;
      break;
      label88: if (n != -1)
      {
        this.c.remove(n);
        this.e = (-1 + this.e);
      }
    }
  }

  public void a(com.flipdog.filebrowser.h.b.a parama)
  {
    a.a.a(new d(this));
  }

  protected void a(Object paramObject)
  {
    File localFile = (File)paramObject;
    if (localFile == null)
      return;
    if (localFile.isFile())
    {
      a(localFile);
      return;
    }
    this.g.a(localFile, a.h.getFirstVisiblePosition());
    i();
  }

  public void b()
  {
    com.flipdog.filebrowser.preference.a.b().a(this.j);
  }

  public void c()
  {
    super.c();
    i();
    this.g.g();
    a.a(true);
  }

  public void d()
  {
    i();
  }

  public Object e()
  {
    return this.g.b();
  }

  public void f()
  {
    com.flipdog.filebrowser.f.a.b.a(new com.flipdog.filebrowser.h.b.b());
    this.h.a(false);
  }

  public boolean g()
  {
    if (this.g.c())
      return false;
    this.g.d();
    i();
    return true;
  }

  public int getCount()
  {
    if (this.i == null)
      return 0;
    return this.i.length;
  }

  public Object getItem(int paramInt)
  {
    if (this.i.length <= paramInt)
      return null;
    return this.i[paramInt];
  }

  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    File localFile = (File)getItem(paramInt);
    View localView = super.a(paramInt, paramView, paramViewGroup, localFile.isDirectory(), localFile);
    com.flipdog.filebrowser.b.c.a locala = (com.flipdog.filebrowser.b.c.a)localView.getTag();
    locala.c.setText(localFile.getName());
    locala.c.setTextSize(com.flipdog.filebrowser.preference.a.b().d());
    this.h.a(localFile);
    String str = this.h.a();
    if (f == 1)
    {
      locala.e.setVisibility(8);
      if (str != null)
        break label317;
    }
    int m;
    label317: for (int k = b.g.b(); str == null; k = -2)
    {
      locala.d.setVisibility(8);
      locala.h.getLayoutParams().width = k;
      locala.h.setTag(Integer.valueOf(paramInt));
      m = this.h.e();
      if (!com.flipdog.filebrowser.preference.a.b().g())
        break label451;
      if (m != 0)
        break label408;
      locala.b.setImageBitmap(this.h.d());
      return localView;
      locala.e.setVisibility(0);
      locala.g.setText(this.h.c());
      locala.g.setTextSize(com.flipdog.filebrowser.preference.a.b().e());
      locala.g.getLayoutParams().width = b.g.c();
      locala.g.setTag(Integer.valueOf(paramInt));
      locala.f.setText(this.h.b());
      locala.f.setTextSize(com.flipdog.filebrowser.preference.a.b().e());
      locala.f.getLayoutParams().width = b.g.d();
    }
    locala.d.setVisibility(0);
    locala.d.setText(str);
    locala.d.setTag(Integer.valueOf(paramInt));
    locala.d.setTextSize(com.flipdog.filebrowser.preference.a.b().e());
    TextView localTextView = locala.d;
    if ((str != null) && (f == 1));
    for (boolean bool = true; ; bool = false)
    {
      localTextView.setClickable(bool);
      break;
    }
    label408: if (m == -1)
      m = com.flipdog.a.fbrowse_type_image;
    while (true)
    {
      locala.b.setImageResource(m);
      return localView;
      if (m == com.flipdog.a.fbrowse_type_image)
        this.h.a(this);
    }
    label451: locala.b.setImageResource(m);
    return localView;
  }

  public String[] h()
  {
    if (l())
    {
      this.c.clear();
      if (m())
        this.c.add(this.g.b());
    }
    String[] arrayOfString = new String[this.c.size()];
    for (int k = 0; ; k++)
    {
      if (k >= arrayOfString.length)
        return arrayOfString;
      arrayOfString[k] = Uri.fromFile((File)this.c.get(k)).toString();
    }
  }

  public void i()
  {
    o();
    b.g.a();
    notifyDataSetChanged();
    this.h.a(true);
    a.h.setSelection(this.g.f());
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.e.a
 * JD-Core Version:    0.6.2
 */