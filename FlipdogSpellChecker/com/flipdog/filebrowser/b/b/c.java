package com.flipdog.filebrowser.b.b;

import android.text.SpannableStringBuilder;
import android.widget.ListView;
import android.widget.TextView;
import com.flipdog.commons.a.as;
import com.flipdog.commons.a.ax;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class c
{
  public static final String a = "FOLDER_DIALOG";
  private SpannableStringBuilder b = new SpannableStringBuilder();
  private Stack<e<com.flipdog.a.b.b.b>> c = new Stack();
  private com.flipdog.filebrowser.b.d d;
  private final com.flipdog.filebrowser.e.c e;
  private final ListView f;

  public c(com.flipdog.filebrowser.b.d paramd, com.flipdog.filebrowser.e.c paramc, ListView paramListView)
  {
    this.e = paramc;
    this.f = paramListView;
    this.d = paramd;
  }

  private boolean a(com.flipdog.a.b.b.b paramb1, com.flipdog.a.b.b.b paramb2)
  {
    Iterator localIterator;
    if (paramb2 != null)
    {
      if (ax.c(paramb1.a, paramb2.a))
        return true;
      localIterator = paramb2.e.iterator();
    }
    com.flipdog.a.b.b.a locala;
    do
    {
      if (!localIterator.hasNext())
        return false;
      locala = (com.flipdog.a.b.b.a)localIterator.next();
    }
    while ((!(locala instanceof com.flipdog.a.b.b.b)) || (!a(paramb1, (com.flipdog.a.b.b.b)locala)));
    return true;
  }

  private void b(com.flipdog.a.b.b.b paramb, int paramInt)
  {
    this.e.a(paramb);
    if (paramInt != -1)
      this.f.setSelection(paramInt);
  }

  public void a()
  {
    this.d.h.setText(null);
    this.c.clear();
  }

  public void a(com.flipdog.a.b.b.b paramb, int paramInt)
  {
    e locale = new e(paramb);
    locale.b = paramInt;
    this.c.push(locale);
    this.e.a(paramb);
  }

  public boolean a(com.flipdog.a.b.b.b paramb)
  {
    boolean bool = com.flipdog.filebrowser.preference.a.b().i();
    if (this.c.isEmpty());
    do
    {
      return false;
      this.c.pop();
    }
    while (this.c.isEmpty());
    int j;
    Object localObject;
    if (this.c.isEmpty())
    {
      j = -1;
      localObject = null;
    }
    while (true)
    {
      if (localObject == null)
        localObject = paramb.c;
      if (localObject == null)
        localObject = this.e.b();
      b((com.flipdog.a.b.b.b)localObject, j);
      return true;
      e locale = (e)this.c.peek();
      if (((!bool) && (!com.flipdog.filebrowser.i.b.a((com.flipdog.a.b.b.b)locale.a, paramb.c))) || (!a((com.flipdog.a.b.b.b)locale.a, this.e.b())))
        break;
      com.flipdog.a.b.b.b localb = (com.flipdog.a.b.b.b)locale.a;
      int i = locale.b;
      localObject = localb;
      j = i;
    }
  }

  public List<com.flipdog.a.b.b.a> b(com.flipdog.a.b.b.b paramb)
  {
    List localList = as.b();
    Iterator localIterator = paramb.e.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        Object[] arrayOfObject = new Object[2];
        arrayOfObject[0] = paramb.a();
        arrayOfObject[1] = Integer.valueOf(localList.size());
        com.flipdog.filebrowser.d.a("List folder: %s. Count: %d", arrayOfObject);
        return localList;
      }
      com.flipdog.a.b.b.a locala = (com.flipdog.a.b.b.a)localIterator.next();
      boolean bool = locala instanceof com.flipdog.a.b.b.b;
      String str = locala.a();
      if (com.flipdog.filebrowser.i.b.a(this.d, bool, str))
        localList.add(locala);
    }
  }

  public void c(com.flipdog.a.b.b.b paramb)
  {
    this.c.push(new e(paramb));
    b(paramb, -1);
  }

  public void d(com.flipdog.a.b.b.b paramb)
  {
    this.b.clear();
    this.b.append("/ ");
    Stack localStack = new Stack();
    do
    {
      localStack.add(paramb);
      paramb = paramb.c;
    }
    while (paramb != null);
    this.b.append("root");
    this.b.setSpan(new com.flipdog.filebrowser.b.c((com.flipdog.a.b.b.b)localStack.pop(), this), 2, 6, 33);
    this.b.append(" / ");
    int k;
    for (int i = 9; ; i = k + 3)
    {
      if (localStack.isEmpty())
      {
        this.d.h.setText(this.b);
        return;
      }
      com.flipdog.a.b.b.b localb = (com.flipdog.a.b.b.b)localStack.pop();
      String str = localb.a();
      int j = str.length();
      this.b.append(str);
      this.b.setSpan(new com.flipdog.filebrowser.b.c(localb, this), i, i + j, 33);
      k = i + j;
      this.b.append(" / ");
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.b.b.c
 * JD-Core Version:    0.6.2
 */