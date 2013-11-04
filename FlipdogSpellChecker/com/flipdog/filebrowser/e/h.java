package com.flipdog.filebrowser.e;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import com.flipdog.filebrowser.b.d;
import java.util.List;

class h
  implements View.OnClickListener
{
  h(b paramb)
  {
  }

  public void onClick(View paramView)
  {
    Object localObject = this.a.b(paramView);
    b localb2;
    if (this.a.c.indexOf(localObject) == -1)
    {
      if (!b.b.c)
        this.a.c.clear();
      this.a.c.add(localObject);
      localb2 = this.a;
    }
    b localb1;
    for (localb2.e = (1 + localb2.e); ; localb1.e = (-1 + localb1.e))
    {
      b.b(this.a);
      if (!b.b.c)
        break;
      b.a(this.a, (ImageView)paramView, localObject);
      return;
      this.a.c.remove(localObject);
      localb1 = this.a;
    }
    this.a.notifyDataSetChanged();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.e.h
 * JD-Core Version:    0.6.2
 */