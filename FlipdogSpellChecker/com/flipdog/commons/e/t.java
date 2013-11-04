package com.flipdog.commons.e;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.flipdog.a.a.b;
import com.flipdog.commons.a.as;
import com.flipdog.m;
import com.flipdog.p;

public abstract class t
{
  private s a = new s(null);
  private b b;
  private AlertDialog c;

  public t(Context paramContext, String paramString)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(paramContext);
    localBuilder.setTitle(com.flipdog.s.file_name);
    View localView = View.inflate(paramContext, p.ask_file_name, null);
    localBuilder.setView(localView);
    this.a.b = ((TextView)as.a(localView, m.ext));
    this.a.b.setText(paramString);
    this.a.a = ((EditText)as.a(localView, m.edit));
    this.a.a.addTextChangedListener(new e(this));
    localBuilder.setNegativeButton(17039360, new f(this));
    localBuilder.setPositiveButton(17039370, new g(this));
    this.c = localBuilder.create();
    this.c.setOnCancelListener(new h(this));
    this.c.show();
    e();
  }

  private void e()
  {
    Button localButton = this.c.getButton(-1);
    if (as.d(a()))
    {
      localButton.setEnabled(false);
      return;
    }
    localButton.setEnabled(true);
  }

  protected String a()
  {
    return this.a.a.getText().toString().trim();
  }

  protected abstract void a(String paramString);

  protected abstract void b();

  public b c()
  {
    return this.b;
  }

  public void d()
  {
    this.c.dismiss();
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.e.t
 * JD-Core Version:    0.6.2
 */