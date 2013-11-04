package com.flipdog.filebrowser.k;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;
import com.flipdog.commons.i.b;
import java.util.Locale;

public class c
{
  private static Resources a = ((Context)b.a(Context.class)).getResources();

  public static final String a()
  {
    return a.getConfiguration().locale.toString();
  }

  public static final String a(int paramInt)
  {
    return a.getString(paramInt);
  }

  public static final void a(View paramView, int paramInt)
  {
    paramView.setBackgroundColor(b(paramInt));
  }

  public static final void a(TextView paramTextView, int paramInt)
  {
    paramTextView.setText(a(paramInt));
  }

  public static final int b(int paramInt)
  {
    return a.getColor(paramInt);
  }

  public static String b()
  {
    return a(17039370);
  }

  public static final void b(TextView paramTextView, int paramInt)
  {
    paramTextView.setTextColor(b(paramInt));
  }

  public static final Drawable c(int paramInt)
  {
    return a.getDrawable(paramInt);
  }

  public static String c()
  {
    return a(17039360);
  }

  public static float d(int paramInt)
  {
    return a.getDimension(paramInt);
  }

  public static int e(int paramInt)
  {
    return a.getDimensionPixelSize(paramInt);
  }

  public static final String[] f(int paramInt)
  {
    return a.getStringArray(paramInt);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.k.c
 * JD-Core Version:    0.6.2
 */