package com.flipdog.filebrowser.k;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.i.b;

public class k
{
  private static float a = ((Context)b.a(Context.class)).getResources().getDisplayMetrics().density;

  public static float a(float paramFloat)
  {
    return 0.5F + paramFloat * a;
  }

  public static int a(int paramInt)
  {
    return (int)a(paramInt);
  }

  public static final View a(Activity paramActivity, int paramInt, Object paramObject)
  {
    return a(paramActivity, paramInt, paramObject, (View.OnClickListener)paramActivity);
  }

  public static final View a(Activity paramActivity, int paramInt, Object paramObject, View.OnClickListener paramOnClickListener)
  {
    View localView = paramActivity.findViewById(paramInt);
    if (paramObject != null)
      localView.setTag(paramObject);
    if (paramOnClickListener != null)
      localView.setOnClickListener(paramOnClickListener);
    return localView;
  }

  public static final View a(Activity paramActivity, int paramInt, boolean paramBoolean)
  {
    View localView = paramActivity.findViewById(paramInt);
    if (localView != null)
      a(localView, paramBoolean);
    return localView;
  }

  public static final View a(View paramView, int paramInt, Object paramObject, View.OnClickListener paramOnClickListener)
  {
    View localView = paramView.findViewById(paramInt);
    if (paramObject != null)
      localView.setTag(paramObject);
    if (paramOnClickListener != null)
      localView.setOnClickListener(paramOnClickListener);
    return localView;
  }

  public static final View a(View paramView, int paramInt, boolean paramBoolean)
  {
    return a(paramView, paramInt, paramBoolean, 8);
  }

  public static final View a(View paramView, int paramInt1, boolean paramBoolean, int paramInt2)
  {
    View localView = paramView.findViewById(paramInt1);
    if (localView != null)
      a(localView, paramBoolean, paramInt2);
    return localView;
  }

  public static final View a(View paramView, boolean paramBoolean)
  {
    a(paramView, paramBoolean, 8);
    return paramView;
  }

  public static final View a(View paramView, boolean paramBoolean, int paramInt)
  {
    if (paramBoolean)
    {
      paramView.setVisibility(0);
      return paramView;
    }
    paramView.setVisibility(paramInt);
    return paramView;
  }

  public static final TextView a(Activity paramActivity, int paramInt, CharSequence paramCharSequence)
  {
    return a((TextView)paramActivity.findViewById(paramInt), paramCharSequence);
  }

  public static final TextView a(View paramView, int paramInt, CharSequence paramCharSequence)
  {
    return a((TextView)paramView.findViewById(paramInt), paramCharSequence);
  }

  public static final TextView a(View paramView, int paramInt, String paramString)
  {
    TextView localTextView = (TextView)paramView.findViewById(paramInt);
    if (localTextView != null)
      localTextView.setText(paramString);
    return localTextView;
  }

  public static final TextView a(TextView paramTextView, CharSequence paramCharSequence)
  {
    if (ax.a(paramCharSequence))
    {
      paramTextView.setVisibility(8);
      return paramTextView;
    }
    paramTextView.setText(paramCharSequence);
    paramTextView.setVisibility(0);
    return paramTextView;
  }

  public static final String a(View paramView)
  {
    return (String)paramView.getTag();
  }

  public static final void a(Activity paramActivity, int paramInt, String paramString)
  {
    TextView localTextView = (TextView)paramActivity.findViewById(paramInt);
    if (localTextView != null)
      localTextView.setText(paramString);
  }

  public static final void a(Activity paramActivity, String[] paramArrayOfString)
  {
    Intent localIntent = new Intent();
    if (paramArrayOfString != null);
    for (int i = 0; ; i += 2)
    {
      if (i >= paramArrayOfString.length)
      {
        paramActivity.setResult(-1, localIntent);
        paramActivity.finish();
        return;
      }
      localIntent.putExtra(paramArrayOfString[i], paramArrayOfString[(i + 1)]);
    }
  }

  public static final int b(View paramView)
  {
    return ((Integer)paramView.getTag()).intValue();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.k.k
 * JD-Core Version:    0.6.2
 */