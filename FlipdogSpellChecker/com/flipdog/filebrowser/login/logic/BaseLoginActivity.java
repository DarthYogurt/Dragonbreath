package com.flipdog.filebrowser.login.logic;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.KeyEvent;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.ax;
import com.flipdog.filebrowser.k.h;
import com.flipdog.filebrowser.login.a.b;
import com.flipdog.p;
import com.flipdog.s;

public class BaseLoginActivity extends MyActivity
{
  private b c;
  private com.flipdog.filebrowser.login.a.c d;

  public static void a(b paramb)
  {
    a(paramb, -1);
  }

  public static void a(b paramb, int paramInt)
  {
    Intent localIntent = new Intent(paramb.a, BaseLoginActivity.class);
    h.a().a(localIntent, paramb);
    if (paramInt == -1)
    {
      paramb.a.startActivity(localIntent);
      return;
    }
    paramb.a.startActivityForResult(localIntent, paramInt);
  }

  private void a(boolean paramBoolean)
  {
    TransformationMethod localTransformationMethod = this.d.b.getTransformationMethod();
    int i = this.d.b.getSelectionStart();
    int j = this.d.b.getSelectionEnd();
    if (localTransformationMethod == null)
      this.d.b.setTransformationMethod(PasswordTransformationMethod.getInstance());
    while (true)
    {
      this.d.b.setSelection(i, j);
      return;
      this.d.b.setTransformationMethod(null);
    }
  }

  private void d()
  {
    this.d.c.setOnCheckedChangeListener(new d(this));
    this.d.d.setOnClickListener(new c(this));
  }

  private void e()
  {
    String str1 = this.d.a.getText().toString();
    String str2 = this.d.b.getText().toString();
    if ((ax.a(str1)) || (ax.a(str2)))
    {
      com.flipdog.filebrowser.k.d.a(s.fbrowse_clouds_email_pwd_not_be_blank);
      return;
    }
    this.c.a = this;
    if (this.c.c == null)
      this.c.c = new com.flipdog.filebrowser.login.a.a();
    this.c.c.a = str1;
    this.c.c.b = str2;
    a locala = new a(this.c);
    com.flipdog.filebrowser.login.b.c[] arrayOfc = new com.flipdog.filebrowser.login.b.c[1];
    arrayOfc[0] = new e(this);
    locala.execute(arrayOfc);
  }

  protected void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    this.c = ((b)h.a().a(this));
    setResult(0);
    if (this.c == null)
      throw new RuntimeException();
    setContentView(p.fbrowse_cloud_login);
    this.d = new com.flipdog.filebrowser.login.a.c(this);
    d();
  }

  public boolean onKeyDown(int paramInt, KeyEvent paramKeyEvent)
  {
    if (paramInt == 4)
    {
      h.a().c(this);
      finish();
      return true;
    }
    return super.onKeyDown(paramInt, paramKeyEvent);
  }

  protected void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    h.a().b(this);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.login.logic.BaseLoginActivity
 * JD-Core Version:    0.6.2
 */