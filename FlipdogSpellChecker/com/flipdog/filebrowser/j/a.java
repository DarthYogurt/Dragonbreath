package com.flipdog.filebrowser.j;

import android.app.Dialog;
import android.graphics.Point;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.as;
import com.flipdog.m;
import com.flipdog.n;
import com.flipdog.p;
import java.util.Iterator;
import java.util.List;

public class a<T>
{
  protected final MyActivity a;
  protected List<T> b;
  protected com.flipdog.filebrowser.j.c.a c;
  private final LayoutInflater d;
  private final int e;
  private float f;
  private Object g;
  private BaseAdapter h = new c(this);

  public a(com.flipdog.filebrowser.j.c.a parama, MyActivity paramMyActivity)
  {
    this(parama, paramMyActivity, p.fbrowse_submenu_item);
  }

  public a(com.flipdog.filebrowser.j.c.a parama, MyActivity paramMyActivity, int paramInt)
  {
    this.c = parama;
    this.a = paramMyActivity;
    this.d = LayoutInflater.from(this.a);
    this.e = paramInt;
  }

  protected Dialog a(int paramInt1, int paramInt2)
  {
    Dialog localDialog = new Dialog(this.a, n.FbrowserSubMenuStyle);
    localDialog.setContentView(p.fbrowse_submenu);
    localDialog.setCanceledOnTouchOutside(true);
    Window localWindow = localDialog.getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    a(paramInt1, paramInt2, localLayoutParams);
    localWindow.setAttributes(localLayoutParams);
    return localDialog;
  }

  protected com.flipdog.filebrowser.j.b.b a(View paramView)
  {
    com.flipdog.filebrowser.j.b.b localb = new com.flipdog.filebrowser.j.b.b();
    localb.b = ((TextView)as.a(paramView, m.fbrowse_submenu_item_text));
    return localb;
  }

  public Object a()
  {
    return this.g;
  }

  protected void a(int paramInt1, int paramInt2, WindowManager.LayoutParams paramLayoutParams)
  {
    paramLayoutParams.gravity = 51;
    Point localPoint1 = b();
    int i = this.b.size() * localPoint1.y;
    int j = localPoint1.x;
    Display localDisplay = this.a.getWindowManager().getDefaultDisplay();
    Point localPoint2 = new Point();
    localPoint2.x = localDisplay.getWidth();
    localPoint2.y = localDisplay.getHeight();
    if (paramInt1 + j > localPoint2.x)
      paramInt1 -= j;
    if (paramInt2 + i > localPoint2.y)
      paramInt2 -= i;
    paramLayoutParams.x = paramInt1;
    paramLayoutParams.y = paramInt2;
    paramLayoutParams.gravity = 51;
    paramLayoutParams.width = j;
  }

  public void a(int paramInt1, int paramInt2, List<T> paramList)
  {
    this.b = paramList;
    this.f = com.flipdog.filebrowser.preference.a.b().f();
    Dialog localDialog = a(paramInt1, paramInt2);
    ListView localListView = (ListView)as.a(localDialog, m.fbrowse_submenu_listview);
    localListView.setAdapter(this.h);
    localListView.setOnItemClickListener(new b(this, localDialog));
    localDialog.show();
  }

  public void a(View paramView, List<T> paramList)
  {
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    arrayOfInt[1] -= com.flipdog.filebrowser.i.b.a(paramView);
    a(arrayOfInt[0], arrayOfInt[1], paramList);
  }

  protected void a(TextView paramTextView, String paramString)
  {
    paramTextView.setText(paramString);
    paramTextView.setTextSize(this.f);
  }

  protected void a(com.flipdog.filebrowser.j.b.b paramb, T paramT)
  {
    if ((paramT instanceof String))
    {
      a(paramb.b, (String)paramT);
      return;
    }
    throw new IllegalStateException("Must override bindHolder");
  }

  public void a(Object paramObject)
  {
    this.g = paramObject;
  }

  protected Point b()
  {
    if (!(this.b.get(0) instanceof String))
      throw new IllegalStateException("Must override getDialogItemSize");
    List localList = as.b();
    Iterator localIterator = this.b.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return com.flipdog.filebrowser.i.b.a(localList);
      localList.add((String)localIterator.next());
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.j.a
 * JD-Core Version:    0.6.2
 */