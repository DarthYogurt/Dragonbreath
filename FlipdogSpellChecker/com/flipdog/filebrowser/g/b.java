package com.flipdog.filebrowser.g;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.ah;
import com.flipdog.filebrowser.a.e;
import com.flipdog.filebrowser.d;
import com.flipdog.filebrowser.k.k;
import com.flipdog.m;
import com.flipdog.p;
import com.flipdog.s;
import java.io.File;

public class b extends g
  implements com.flipdog.filebrowser.c.a
{
  private View c;
  private MyActivity d;
  private File e;
  private int f = k.a(30);

  private void a(ViewGroup paramViewGroup, String paramString1, String paramString2)
  {
    LinearLayout localLinearLayout = new LinearLayout(this.d);
    localLinearLayout.setLayoutParams(new ViewGroup.LayoutParams(-1, -2));
    a(localLinearLayout, paramString1, false);
    a(localLinearLayout, paramString2, true);
    paramViewGroup.addView(localLinearLayout);
  }

  private void a(ViewGroup paramViewGroup, String paramString, boolean paramBoolean)
  {
    TextView localTextView = new TextView(this.d);
    localTextView.setMaxLines(2);
    LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(-2, -2);
    if (paramBoolean)
      localLayoutParams.setMargins(0, 0, this.f, 0);
    while (true)
    {
      localTextView.setLayoutParams(localLayoutParams);
      localTextView.setText(paramString);
      paramViewGroup.addView(localTextView);
      return;
      localLayoutParams.setMargins(this.f, 0, 0, 0);
    }
  }

  public void a(com.flipdog.filebrowser.l.a.b.a parama)
  {
    Context localContext = ah.a(this.d);
    this.c = LayoutInflater.from(localContext).inflate(p.fbrowse_information, null);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(localContext);
    localBuilder.setPositiveButton(com.flipdog.filebrowser.k.c.a(17039370), null);
    localBuilder.setView(this.c);
    AlertDialog localAlertDialog = localBuilder.create();
    com.flipdog.filebrowser.l.a.a locala = (com.flipdog.filebrowser.l.a.a)parama;
    LinearLayout localLinearLayout = (LinearLayout)this.c.findViewById(m.fbrowse_layout_information);
    a(localLinearLayout, com.flipdog.filebrowser.k.c.a(s.fbrowse_name), locala.h);
    if (this.e.isDirectory())
    {
      a(localLinearLayout, com.flipdog.filebrowser.k.c.a(s.fbrowse_number_files), Long.toString(locala.a));
      a(localLinearLayout, com.flipdog.filebrowser.k.c.a(s.fbrowse_subfolders), Long.toString(locala.e));
    }
    a(localLinearLayout, com.flipdog.filebrowser.k.c.a(s.fbrowse_size), com.flipdog.filebrowser.f.a.a.a(locala.f));
    a(localLinearLayout, com.flipdog.filebrowser.k.c.a(s.fbrowse_modified_date), locala.g);
    Window localWindow = localAlertDialog.getWindow();
    WindowManager.LayoutParams localLayoutParams = localWindow.getAttributes();
    localLayoutParams.width = -1;
    localWindow.setAttributes(localLayoutParams);
    localAlertDialog.show();
  }

  public void a(Object paramObject, MyActivity paramMyActivity)
  {
    this.d = paramMyActivity;
    this.e = ((File)paramObject);
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = this.e.getName();
    d.a("Get information about %s", arrayOfObject);
    new com.flipdog.filebrowser.l.c(this.e, this, paramMyActivity, this).execute(new Void[0]);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.g.b
 * JD-Core Version:    0.6.2
 */