package android.widget;

import android.view.View;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import android.view.WindowManager.LayoutParams;
import java.lang.ref.WeakReference;

class b
  implements ViewTreeObserver.OnScrollChangedListener
{
  b(MyPopupWindow paramMyPopupWindow)
  {
  }

  public void onScrollChanged()
  {
    if (MyPopupWindow.a(this.a) != null);
    for (View localView = (View)MyPopupWindow.a(this.a).get(); ; localView = null)
    {
      if ((localView != null) && (MyPopupWindow.b(this.a) != null))
      {
        WindowManager.LayoutParams localLayoutParams = (WindowManager.LayoutParams)MyPopupWindow.b(this.a).getLayoutParams();
        MyPopupWindow.a(this.a, MyPopupWindow.a(this.a, localView, localLayoutParams, MyPopupWindow.c(this.a), MyPopupWindow.d(this.a)));
        this.a.a(localLayoutParams.x, localLayoutParams.y, -1, -1, true);
      }
      return;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     android.widget.b
 * JD-Core Version:    0.6.2
 */