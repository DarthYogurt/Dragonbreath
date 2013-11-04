package com.flipdog.commons.b;

import android.content.Context;
import android.content.res.Resources.Theme;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.MyPopupWindow;
import com.flipdog.p;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class i
{
  private static int a(Context paramContext, int paramInt1, ListAdapter paramListAdapter, int paramInt2)
  {
    FrameLayout localFrameLayout = new FrameLayout(paramContext);
    int i = View.MeasureSpec.makeMeasureSpec(paramInt1, 1073741824);
    int j = View.MeasureSpec.makeMeasureSpec(0, 0);
    int k = paramListAdapter.getCount();
    int m = 0;
    int n = 0;
    View localView1 = null;
    int i1 = 0;
    if (m >= k)
      return i1 + paramInt2 * paramListAdapter.getCount();
    int i2 = paramListAdapter.getItemViewType(m);
    if (i2 != n);
    for (View localView2 = null; ; localView2 = localView1)
    {
      localView1 = paramListAdapter.getView(m, localView2, localFrameLayout);
      localView1.measure(i, j);
      i1 += localView1.getMeasuredHeight();
      m++;
      n = i2;
      break;
      i2 = n;
    }
  }

  private static int a(Context paramContext, ListAdapter paramListAdapter)
  {
    FrameLayout localFrameLayout = new FrameLayout(paramContext);
    int i = View.MeasureSpec.makeMeasureSpec(0, 0);
    int j = View.MeasureSpec.makeMeasureSpec(0, 0);
    int k = paramListAdapter.getCount();
    int m = 0;
    int n = 0;
    View localView1 = null;
    int i1 = 0;
    if (m >= k)
      return i1;
    int i2 = paramListAdapter.getItemViewType(m);
    if (i2 != n);
    for (View localView2 = null; ; localView2 = localView1)
    {
      localView1 = paramListAdapter.getView(m, localView2, localFrameLayout);
      localView1.measure(i, j);
      i1 = Math.max(i1, localView1.getMeasuredWidth());
      m++;
      n = i2;
      break;
      i2 = n;
    }
  }

  private static Drawable a(Resources.Theme paramTheme, int paramInt)
  {
    TypedArray localTypedArray = paramTheme.obtainStyledAttributes(null, new int[] { paramInt }, com.flipdog.d.toolbarStyle, 0);
    try
    {
      int i = localTypedArray.getIndex(0);
      localTypedArray.getValue(i, new TypedValue());
      Drawable localDrawable = localTypedArray.getDrawable(i);
      return localDrawable;
    }
    finally
    {
      localTypedArray.recycle();
    }
  }

  public static MyPopupWindow a(Context paramContext, LayoutInflater paramLayoutInflater, View paramView, List<c> paramList, d paramd)
  {
    ListView localListView = (ListView)paramLayoutInflater.inflate(p.toolbar_overflow_menu, null);
    k localk = new k(paramContext, paramList);
    localListView.setAdapter(localk);
    localListView.setCacheColorHint(0);
    int i = Math.max(150, a(paramContext, localk));
    int j = a(paramContext, i, localk, localListView.getDividerHeight());
    MyPopupWindow localMyPopupWindow = new MyPopupWindow(paramContext);
    Resources.Theme localTheme = paramContext.getTheme();
    Drawable localDrawable1 = a(localTheme, com.flipdog.d.actionBarOverflowBackground);
    localListView.setDivider(a(localTheme, com.flipdog.d.actionBarOverflowDivider));
    localListView.setDividerHeight(1);
    localMyPopupWindow.a(localDrawable1);
    Drawable localDrawable2 = localMyPopupWindow.a();
    if (localDrawable2 != null)
    {
      Rect localRect = new Rect();
      localDrawable2.getPadding(localRect);
      j += localRect.top + localRect.bottom;
      i += 2 + (localRect.left + localRect.right);
    }
    int k = Math.min(j, localMyPopupWindow.c(paramView));
    localMyPopupWindow.a(localListView);
    localMyPopupWindow.f(i);
    localMyPopupWindow.e(k);
    localMyPopupWindow.c(true);
    localMyPopupWindow.a(true);
    localMyPopupWindow.b(2);
    localListView.setOnItemClickListener(new g(localMyPopupWindow, paramList, paramd));
    localMyPopupWindow.b(paramView);
    localListView.setOnKeyListener(new f(paramd, localMyPopupWindow));
    return localMyPopupWindow;
  }

  public static void a(Context paramContext, j paramj, int paramInt)
  {
    a(paramj, b.a(paramContext, paramInt));
  }

  protected static void a(d paramd, int paramInt)
  {
    if (paramd == null)
      return;
    paramd.a(paramInt);
  }

  public static void a(j paramj, List<c> paramList)
  {
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Iterator localIterator1 = paramList.iterator();
    Iterator localIterator2;
    label42: Iterator localIterator3;
    if (!localIterator1.hasNext())
    {
      localIterator2 = localArrayList1.iterator();
      if (localIterator2.hasNext())
        break label119;
      localIterator3 = localArrayList2.iterator();
    }
    while (true)
    {
      if (!localIterator3.hasNext())
      {
        return;
        c localc = (c)localIterator1.next();
        if (a(localc.b, 2))
        {
          localArrayList1.add(localc);
          break;
        }
        localArrayList2.add(localc);
        break;
        label119: paramj.b((c)localIterator2.next());
        break label42;
      }
      paramj.a((c)localIterator3.next());
    }
  }

  private static boolean a(int paramInt1, int paramInt2)
  {
    return (paramInt1 & paramInt2) != 0;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.b.i
 * JD-Core Version:    0.6.2
 */