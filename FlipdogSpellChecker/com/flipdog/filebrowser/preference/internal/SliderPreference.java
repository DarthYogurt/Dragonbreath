package com.flipdog.filebrowser.preference.internal;

import android.content.Context;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.m;
import com.flipdog.p;

public class SliderPreference extends Preference
  implements SeekBar.OnSeekBarChangeListener
{
  private static final int a = 50;
  private int b = 50;
  private int c;
  private int d;

  public SliderPreference(Context paramContext)
  {
    super(paramContext);
    setWidgetLayoutResource(p.fbrowse_preference_sliderbar);
  }

  public SliderPreference(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 16842894);
  }

  public SliderPreference(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet, paramInt);
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, new int[] { 16843091, 16843094 }, paramInt, 0);
    try
    {
      this.c = a(localTypedArray, 1, 0);
      this.d = a(localTypedArray, 0, 100);
      localTypedArray.recycle();
      setWidgetLayoutResource(p.fbrowse_preference_sliderbar_item);
      return;
    }
    finally
    {
      localTypedArray.recycle();
    }
  }

  private final int a(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 == paramInt3)
    {
      if (paramInt1 == paramInt2);
    }
    else
    {
      do
      {
        return paramInt2;
        if (paramInt2 >= paramInt3)
          break;
      }
      while (paramInt1 < paramInt2);
      if (paramInt1 > paramInt3)
      {
        return paramInt3;
        return a(paramInt1, paramInt3, paramInt2);
      }
    }
    return paramInt1;
  }

  private final int a(TypedArray paramTypedArray, int paramInt1, int paramInt2)
  {
    String str = paramTypedArray.getString(paramInt1);
    try
    {
      int i = Integer.parseInt(str);
      return i;
    }
    catch (Exception localException)
    {
      Track.it(localException);
    }
    return paramInt2;
  }

  protected void onBindView(View paramView)
  {
    super.onBindView(paramView);
    if ((paramView instanceof LinearLayout))
      ((LinearLayout)paramView).setOrientation(1);
    View localView = paramView.findViewById(16908312);
    if (localView != null)
      localView.getLayoutParams().width = -1;
    ((TextView)paramView.findViewById(m.preference_sliderbar_min)).setText(String.valueOf(this.c));
    ((TextView)paramView.findViewById(m.preference_sliderbar_max)).setText(String.valueOf(this.d));
    SeekBar localSeekBar = (SeekBar)paramView.findViewById(m.preference_sliderbar_seek);
    localSeekBar.setMax(this.d - this.c);
    localSeekBar.setProgress(this.b - this.c);
    localSeekBar.setOnSeekBarChangeListener(this);
  }

  protected Object onGetDefaultValue(TypedArray paramTypedArray, int paramInt)
  {
    this.c = 0;
    this.d = 100;
    return Integer.valueOf(a(paramTypedArray.getInt(paramInt, 50), this.c, this.d));
  }

  public void onProgressChanged(SeekBar paramSeekBar, int paramInt, boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.b = (paramInt + this.c);
      persistInt(this.b);
    }
  }

  protected void onSetInitialValue(boolean paramBoolean, Object paramObject)
  {
    if (paramObject != null);
    for (int i = ((Integer)paramObject).intValue(); ; i = 50)
    {
      this.b = i;
      if (paramBoolean)
        break;
      persistInt(this.b);
      return;
    }
    this.b = getPersistedInt(this.b);
  }

  public void onStartTrackingTouch(SeekBar paramSeekBar)
  {
  }

  public void onStopTrackingTouch(SeekBar paramSeekBar)
  {
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.preference.internal.SliderPreference
 * JD-Core Version:    0.6.2
 */