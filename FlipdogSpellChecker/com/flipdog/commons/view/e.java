package com.flipdog.commons.view;

import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.flipdog.commons.a.as;
import com.flipdog.commons.diagnostic.Track;
import java.util.Iterator;
import java.util.List;

public class e
{
  private List<ViewTreeObserver.OnScrollChangedListener> a = as.b();
  private List<a> b = as.b();

  private void a(String paramString, Object[] paramArrayOfObject)
  {
    if (Track.isDisabled("CustomViewMixin"))
      return;
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = getClass().getSimpleName();
    arrayOfObject[1] = String.format(paramString, paramArrayOfObject);
    Track.me("CustomViewMixin", "[%s] %s", arrayOfObject);
  }

  public void a(int paramInt1, int paramInt2)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(paramInt1);
    arrayOfObject[1] = Integer.valueOf(paramInt2);
    a("onMeasure(widthMeasureSpec = %s, heightMeasureSpec = %s)", arrayOfObject);
  }

  public void a(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Iterator localIterator = this.a.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((ViewTreeObserver.OnScrollChangedListener)localIterator.next()).onScrollChanged();
    }
  }

  public void a(ViewTreeObserver.OnScrollChangedListener paramOnScrollChangedListener)
  {
    this.a.add(paramOnScrollChangedListener);
  }

  public void a(a parama)
  {
    this.b.add(parama);
  }

  public void a(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Object[] arrayOfObject = new Object[5];
    arrayOfObject[0] = Boolean.valueOf(paramBoolean);
    arrayOfObject[1] = Integer.valueOf(paramInt1);
    arrayOfObject[2] = Integer.valueOf(paramInt2);
    arrayOfObject[3] = Integer.valueOf(paramInt3);
    arrayOfObject[4] = Integer.valueOf(paramInt4);
    a("onLayout(changed = %s, l = %s, t = %s, r = %s, b = %s)", arrayOfObject);
    Iterator localIterator = this.b.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((a)localIterator.next()).a(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    }
  }

  public void b(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    Object[] arrayOfObject = new Object[4];
    arrayOfObject[0] = Integer.valueOf(paramInt1);
    arrayOfObject[1] = Integer.valueOf(paramInt2);
    arrayOfObject[2] = Integer.valueOf(paramInt3);
    arrayOfObject[3] = Integer.valueOf(paramInt4);
    a("onSizeChanged (w = %s, h = %s, oldw = %s, oldh = %s)", arrayOfObject);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.view.e
 * JD-Core Version:    0.6.2
 */