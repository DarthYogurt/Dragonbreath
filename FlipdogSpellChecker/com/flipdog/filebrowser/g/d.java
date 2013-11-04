package com.flipdog.filebrowser.g;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.ah;
import com.flipdog.commons.a.ax;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.filebrowser.e.b;
import com.flipdog.filebrowser.k.c;
import com.flipdog.m;
import com.flipdog.p;
import com.flipdog.s;
import java.io.File;

public class d extends g
{
  private File c;
  private View d;
  private MyActivity e;
  private DialogInterface.OnClickListener f = new j(this);

  public d(b paramb)
  {
    super(paramb);
  }

  private void a(Context paramContext)
  {
    Context localContext = ah.a(paramContext);
    this.d = LayoutInflater.from(localContext).inflate(p.fbrowse_rename, null);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(localContext);
    localBuilder.setPositiveButton(c.a(17039370), this.f);
    localBuilder.setNegativeButton(c.a(17039360), this.f);
    localBuilder.setView(this.d);
    AlertDialog localAlertDialog = localBuilder.create();
    ((EditText)this.d.findViewById(m.fbrowse_rename)).setText(this.c.getName());
    localAlertDialog.show();
  }

  private File b()
  {
    File localFile1 = this.c.getParentFile();
    String str = ((EditText)this.d.findViewById(m.fbrowse_rename)).getText().toString();
    if (ax.a(str))
      a(this.e, s.fbrowse_name_can_not_blank);
    while (true)
    {
      File localFile2 = null;
      do
      {
        return localFile2;
        localFile2 = new File(localFile1, str);
        com.flipdog.filebrowser.d.a("Parent: %s. Name: %s. File: %s", new Object[] { localFile1, str, localFile2 });
      }
      while (!localFile2.exists());
      a(this.e, s.fbrowse_already_present);
    }
  }

  private void c()
  {
    File localFile = b();
    if (localFile == null)
      return;
    try
    {
      if (this.c.renameTo(localFile))
      {
        a(this.e, s.fbrowse_rename_ok);
        a().d();
        return;
      }
    }
    catch (Exception localException)
    {
      Track.it(localException);
      a(this.e, s.fbrowse_rename_error);
    }
  }

  public void a(Object paramObject, MyActivity paramMyActivity)
  {
    this.e = paramMyActivity;
    this.c = ((File)paramObject);
    File localFile = (File)paramObject;
    Object[] arrayOfObject = new Object[1];
    arrayOfObject[0] = localFile.getName();
    com.flipdog.filebrowser.d.a("Rename %s", arrayOfObject);
    a(paramMyActivity);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.g.d
 * JD-Core Version:    0.6.2
 */