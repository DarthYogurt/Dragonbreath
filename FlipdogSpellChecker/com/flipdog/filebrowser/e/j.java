package com.flipdog.filebrowser.e;

import android.view.View;
import android.view.View.OnClickListener;
import com.flipdog.filebrowser.d;
import com.flipdog.filebrowser.j.a.a;

class j
  implements View.OnClickListener
{
  j(b paramb)
  {
  }

  public void onClick(View paramView)
  {
    Object localObject = this.a.b(paramView);
    int[] arrayOfInt = new int[2];
    paramView.getLocationOnScreen(arrayOfInt);
    arrayOfInt[0] = paramView.getHeight();
    arrayOfInt[1] -= com.flipdog.filebrowser.i.b.a(paramView);
    Object[] arrayOfObject = new Object[3];
    arrayOfObject[0] = localObject;
    arrayOfObject[1] = Integer.valueOf(arrayOfInt[1]);
    arrayOfObject[2] = Integer.valueOf(arrayOfInt[0]);
    d.a("Call submenu for %s. Y: %d. H: %d", arrayOfObject);
    b.a(this.a).a(localObject);
    b.a(this.a).b(arrayOfInt[1], arrayOfInt[0]);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.e.j
 * JD-Core Version:    0.6.2
 */