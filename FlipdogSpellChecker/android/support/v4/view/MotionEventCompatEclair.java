package android.support.v4.view;

import android.view.MotionEvent;

class MotionEventCompatEclair
{
  public static int findPointerIndex(MotionEvent paramMotionEvent, int paramInt)
  {
    return paramMotionEvent.findPointerIndex(paramInt);
  }

  public static int getPointerId(MotionEvent paramMotionEvent, int paramInt)
  {
    return paramMotionEvent.getPointerId(paramInt);
  }

  public static float getX(MotionEvent paramMotionEvent, int paramInt)
  {
    return paramMotionEvent.getX(paramInt);
  }

  public static float getY(MotionEvent paramMotionEvent, int paramInt)
  {
    return paramMotionEvent.getY(paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     android.support.v4.view.MotionEventCompatEclair
 * JD-Core Version:    0.6.2
 */