package com.flipdog.filebrowser.j.a;

import android.graphics.Point;
import android.view.WindowManager.LayoutParams;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.as;
import com.flipdog.filebrowser.e.c;
import com.flipdog.filebrowser.g.g;
import com.flipdog.filebrowser.g.h;
import java.util.List;

public class a extends com.flipdog.filebrowser.j.a<String>
  implements com.flipdog.filebrowser.j.c.a
{
  private int[] d;
  private int e;
  private int f;
  private final com.flipdog.filebrowser.e.b g;

  public a(MyActivity paramMyActivity, com.flipdog.filebrowser.e.b paramb)
  {
    super(null, paramMyActivity);
    this.c = this;
    this.g = paramb;
  }

  protected void a(int paramInt1, int paramInt2, WindowManager.LayoutParams paramLayoutParams)
  {
    Point localPoint = com.flipdog.filebrowser.i.b.a(this.b);
    int i = localPoint.y * this.b.size();
    localPoint.y = this.e;
    if (localPoint.y - i > 0);
    for (localPoint.y -= i; ; localPoint.y += this.f)
    {
      paramLayoutParams.y = localPoint.y;
      paramLayoutParams.x = 0;
      paramLayoutParams.gravity = 53;
      paramLayoutParams.width = localPoint.x;
      return;
    }
  }

  public void a(int paramInt, Object paramObject)
  {
    h.a(this.d[paramInt], this.g).a(paramObject, this.a);
  }

  public void b(int paramInt1, int paramInt2)
  {
    this.e = paramInt1;
    this.f = paramInt2;
    com.flipdog.a.b localb;
    List localList;
    int[] arrayOfInt;
    int i;
    if ((this.g instanceof c))
    {
      localb = ((c)this.g).i();
      this.d = h.a(localb);
      localList = as.b();
      arrayOfInt = this.d;
      i = arrayOfInt.length;
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        super.a(0, 0, localList);
        return;
        localb = null;
        break;
      }
      localList.add(h.a(arrayOfInt[j]));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.j.a.a
 * JD-Core Version:    0.6.2
 */