package com.flipdog.filebrowser.login.a;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.as;
import com.flipdog.m;

public class c
{
  public final EditText a;
  public final EditText b;
  public final CheckBox c;
  public final Button d;

  public c(MyActivity paramMyActivity)
  {
    this.a = ((EditText)as.a(paramMyActivity, m.cloud_login_email));
    this.b = ((EditText)as.a(paramMyActivity, m.cloud_login_pass));
    this.c = ((CheckBox)as.a(paramMyActivity, m.cloud_login_show_pwd));
    this.d = ((Button)as.a(paramMyActivity, m.cloud_login_start));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.login.a.c
 * JD-Core Version:    0.6.2
 */