package com.flipdog.speller;

import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import com.flipdog.activity.MyActivity;

public class l
{
  public static r a(MyActivity paramMyActivity, EditText paramEditText, ProgressBar paramProgressBar)
  {
    return new r(paramMyActivity, paramEditText, new n(paramMyActivity, paramEditText, paramProgressBar));
  }

  public static String a()
  {
    return d.a().a;
  }

  public static void a(Spinner paramSpinner, c paramc)
  {
    d locald = d.a();
    if (locald.a != null);
    for (String str = locald.a; ; str = "en")
    {
      paramSpinner.setSelection(paramc.a(str));
      return;
    }
  }

  public static void a(String paramString)
  {
    d locald = d.a();
    locald.a = paramString;
    locald.b();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.speller.l
 * JD-Core Version:    0.6.2
 */