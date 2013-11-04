package com.flipdog.logging;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import com.flipdog.commons.a.as;
import com.flipdog.errors.activity.ErrorActivity;
import com.flipdog.p;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class a extends Dialog
  implements View.OnClickListener
{
  private r a = new r(null);
  private u b;
  private List<m> c = as.b();

  public a(Context paramContext)
  {
    super(paramContext);
  }

  private Set<String> a(c paramc)
  {
    return a(paramc.f());
  }

  private Set<String> a(Collection<String> paramCollection)
  {
    return new HashSet(paramCollection);
  }

  private void a(ArrayList<String> paramArrayList)
  {
    c localc = c.b();
    localc.a(paramArrayList);
    localc.c();
  }

  private void d()
  {
    this.a.a = ((Button)findViewById(com.flipdog.m.ok_button));
    this.a.b = ((Button)findViewById(com.flipdog.m.cancel_button));
    this.a.c = ((ListView)findViewById(com.flipdog.m.list));
  }

  private void e()
  {
  }

  private void f()
  {
    this.a.a.setOnClickListener(this);
    this.a.b.setOnClickListener(this);
  }

  private ArrayList<String> g()
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.c.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      m localm = (m)localIterator.next();
      if (localm.b)
        localArrayList.add(localm.a);
    }
  }

  public LayoutInflater a()
  {
    return (LayoutInflater)getContext().getSystemService("layout_inflater");
  }

  protected void b()
  {
    dismiss();
  }

  protected void c()
  {
    a(g());
    dismiss();
  }

  public void onClick(View paramView)
  {
    if (paramView == this.a.a)
      c();
    while (paramView != this.a.b)
      return;
    b();
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    requestWindowFeature(1);
    setContentView(p.logcat_tags_dialog);
    try
    {
      Set localSet = a(c.b());
      d();
      Iterator localIterator = v.a().iterator();
      while (true)
      {
        if (!localIterator.hasNext())
        {
          this.b = new u(getContext(), this.c);
          this.a.c.setAdapter(this.b);
          f();
          e();
          return;
        }
        String str = (String)localIterator.next();
        m localm = new m();
        localm.a = str;
        localm.b = localSet.contains(str);
        this.c.add(localm);
      }
    }
    catch (Exception localException)
    {
      ErrorActivity.a(getContext(), localException);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.logging.a
 * JD-Core Version:    0.6.2
 */