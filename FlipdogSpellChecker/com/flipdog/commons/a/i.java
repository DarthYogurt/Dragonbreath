package com.flipdog.commons.a;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class i
{
  public static void a(View paramView, int paramInt)
  {
    paramView.setOnLongClickListener(new x(paramInt));
  }

  public static void a(View paramView, CharSequence paramCharSequence)
  {
    if ((paramCharSequence == null) || (paramCharSequence.length() == 0))
      return;
    paramView.setOnLongClickListener(new z(paramCharSequence));
  }

  private static void b(Context paramContext, int paramInt)
  {
    Toast.makeText(paramContext, paramInt, 0).show();
  }

  private static void b(Context paramContext, CharSequence paramCharSequence)
  {
    Toast.makeText(paramContext, paramCharSequence, 0).show();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.i
 * JD-Core Version:    0.6.2
 */