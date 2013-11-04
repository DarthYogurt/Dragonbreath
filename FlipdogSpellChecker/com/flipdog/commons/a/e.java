package com.flipdog.commons.a;

import android.content.Context;
import android.widget.Toast;
import com.flipdog.commons.i.b;

public class e
{
  public static void a(CharSequence paramCharSequence)
  {
    Toast.makeText((Context)b.a(Context.class), paramCharSequence, 0).show();
  }

  public static void a(CharSequence paramCharSequence, Exception paramException)
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = paramCharSequence;
    arrayOfObject[1] = paramException.getMessage();
    a(String.format("%s\n\n%s", arrayOfObject));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.e
 * JD-Core Version:    0.6.2
 */