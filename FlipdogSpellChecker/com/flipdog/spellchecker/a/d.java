package com.flipdog.spellchecker.a;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import com.flipdog.spellchecker.m;
import com.flipdog.spellchecker.o;
import com.flipdog.spellchecker.p;

public class d
{
  public static void a(Context paramContext, CharSequence paramCharSequence)
  {
    a(paramContext, paramCharSequence, new i());
  }

  public static void a(Context paramContext, CharSequence paramCharSequence, Runnable paramRunnable)
  {
    TextView localTextView = new TextView(paramContext);
    localTextView.setPadding(10, 10, 10, 10);
    localTextView.setText(paramCharSequence);
    localTextView.setMovementMethod(LinkMovementMethod.getInstance());
    AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext).setView(localTextView).setCancelable(true).create();
    localAlertDialog.setOnDismissListener(new j(paramRunnable));
    localAlertDialog.show();
  }

  public static void a(Context paramContext, String paramString)
  {
    a(paramContext, paramString, new e());
  }

  public static void a(Context paramContext, String paramString, CharSequence paramCharSequence, Runnable paramRunnable1, Runnable paramRunnable2)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext).setTitle(paramString).setMessage(paramCharSequence).setCancelable(true);
    localBuilder.setPositiveButton("OK", new g(paramRunnable1));
    localBuilder.setNegativeButton(o.a(m.d.b), new h(paramRunnable2));
    localBuilder.create().show();
  }

  public static void a(Context paramContext, String paramString, Runnable paramRunnable)
  {
    AlertDialog localAlertDialog = new AlertDialog.Builder(paramContext).setMessage(paramString).setCancelable(true).create();
    localAlertDialog.setOnDismissListener(new f(paramRunnable));
    localAlertDialog.show();
  }

  public static AlertDialog b(Context paramContext, String paramString)
  {
    return new AlertDialog.Builder(paramContext).setMessage(paramString).setCancelable(true).create();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.a.d
 * JD-Core Version:    0.6.2
 */