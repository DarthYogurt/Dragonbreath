package com.flipdog.commons.a;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.Context;
import android.text.method.LinkMovementMethod;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.TextView;

public class a
{
  public static void a(Dialog paramDialog)
  {
    WindowManager.LayoutParams localLayoutParams = paramDialog.getWindow().getAttributes();
    localLayoutParams.flags = (0x20000 | localLayoutParams.flags);
    paramDialog.getWindow().setAttributes(localLayoutParams);
  }

  public static void a(Context paramContext, CharSequence paramCharSequence)
  {
    a(paramContext, paramCharSequence, new y());
  }

  public static void a(Context paramContext, CharSequence paramCharSequence, Runnable paramRunnable)
  {
    Context localContext = ah.a(paramContext);
    TextView localTextView = new TextView(localContext);
    localTextView.setPadding(20, 20, 20, 20);
    localTextView.setText(paramCharSequence);
    localTextView.setTextSize(1, 16.0F);
    localTextView.setTypeface(null, 1);
    localTextView.setMovementMethod(LinkMovementMethod.getInstance());
    AlertDialog localAlertDialog = new AlertDialog.Builder(localContext).setView(localTextView).setCancelable(true).setTitle("Error").create();
    localAlertDialog.setOnDismissListener(new aa(paramRunnable));
    localAlertDialog.show();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.a
 * JD-Core Version:    0.6.2
 */