package android.widget;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

class a extends FrameLayout
{
  private static final String b = "PopupWindow.PopupViewContainer";

  public a(MyPopupWindow paramMyPopupWindow, Context paramContext)
  {
    super(paramContext);
  }

  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if (paramKeyEvent.getKeyCode() == 4)
    {
      if ((paramKeyEvent.getAction() == 0) && (paramKeyEvent.getRepeatCount() == 0))
        return true;
      if (paramKeyEvent.getAction() == 1)
      {
        this.a.r();
        return true;
      }
      return super.dispatchKeyEvent(paramKeyEvent);
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }

  public boolean dispatchTouchEvent(MotionEvent paramMotionEvent)
  {
    if ((MyPopupWindow.f(this.a) != null) && (MyPopupWindow.f(this.a).onTouch(this, paramMotionEvent)))
      return true;
    return super.dispatchTouchEvent(paramMotionEvent);
  }

  protected int[] onCreateDrawableState(int paramInt)
  {
    if (MyPopupWindow.e(this.a))
    {
      int[] arrayOfInt = super.onCreateDrawableState(paramInt + 1);
      View.mergeDrawableStates(arrayOfInt, MyPopupWindow.t());
      return arrayOfInt;
    }
    return super.onCreateDrawableState(paramInt);
  }

  public boolean onTouchEvent(MotionEvent paramMotionEvent)
  {
    int i = (int)paramMotionEvent.getX();
    int j = (int)paramMotionEvent.getY();
    if ((paramMotionEvent.getAction() == 0) && ((i < 0) || (i >= getWidth()) || (j < 0) || (j >= getHeight())))
    {
      this.a.r();
      return true;
    }
    if (paramMotionEvent.getAction() == 4)
    {
      this.a.r();
      return true;
    }
    return super.onTouchEvent(paramMotionEvent);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     android.widget.a
 * JD-Core Version:    0.6.2
 */