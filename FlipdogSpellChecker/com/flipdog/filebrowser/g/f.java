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
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.filebrowser.k.d;
import com.flipdog.filebrowser.l.e;
import com.flipdog.m;
import com.flipdog.p;
import com.flipdog.s;
import java.io.File;
import java.util.List;

public class f extends g
{
  private View c;
  private DialogInterface.OnClickListener d = new c(this);

  public f(com.flipdog.filebrowser.e.b paramb)
  {
    super(paramb);
  }

  private void a(Context paramContext)
  {
    Context localContext = ah.a(paramContext);
    this.c = LayoutInflater.from(localContext).inflate(p.fbrowse_rename, null);
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(localContext);
    localBuilder.setPositiveButton(com.flipdog.filebrowser.k.c.a(17039370), this.d);
    localBuilder.setNegativeButton(com.flipdog.filebrowser.k.c.a(17039360), this.d);
    localBuilder.setView(this.c);
    localBuilder.create().show();
  }

  private void a(com.flipdog.a.b.b.b paramb, String paramString)
  {
    new e(paramb, paramString, this, this.a, this).execute(new Void[0]);
  }

  private void a(MyActivity paramMyActivity)
  {
    d.a(this.a, s.fbrowse_create_folder_ok);
  }

  private boolean a(File paramFile, String paramString)
  {
    try
    {
      boolean bool = new File(paramFile, paramString).mkdir();
      if (bool)
        return true;
    }
    catch (Exception localException)
    {
      Track.it(localException);
      d.a(this.a, s.fbrowse_create_folder_error);
    }
    return false;
  }

  private void b()
  {
    String str = c();
    if ((this.b instanceof File))
    {
      if (a((File)this.b, str))
      {
        a().d();
        a(this.a);
      }
      return;
    }
    if ((this.b instanceof com.flipdog.a.b.b.b))
    {
      a((com.flipdog.a.b.b.b)this.b, str);
      return;
    }
    throw new RuntimeException(this.b.getClass().getSimpleName());
  }

  private String c()
  {
    return ((EditText)this.c.findViewById(m.fbrowse_rename)).getText().toString();
  }

  protected void a(com.flipdog.filebrowser.l.b paramb)
  {
    com.flipdog.a.b.b.b localb1 = (com.flipdog.a.b.b.b)a().e();
    com.flipdog.a.b.b.b localb2 = ((e)paramb).a();
    localb1.e.add(0, localb2);
    a().notifyDataSetChanged();
    a(this.a);
  }

  public void a(Object paramObject, MyActivity paramMyActivity)
  {
    super.a(paramObject, paramMyActivity);
    a(paramMyActivity);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.g.f
 * JD-Core Version:    0.6.2
 */