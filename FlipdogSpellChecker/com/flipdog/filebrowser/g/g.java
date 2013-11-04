package com.flipdog.filebrowser.g;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.OnClickListener;
import android.widget.Toast;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.a.ah;
import com.flipdog.filebrowser.k.c;

public abstract class g
  implements com.flipdog.filebrowser.c.a
{
  protected MyActivity a;
  protected Object b;
  private final com.flipdog.filebrowser.e.b c;

  public g()
  {
    this.c = null;
  }

  public g(com.flipdog.filebrowser.e.b paramb)
  {
    this.c = paramb;
  }

  public com.flipdog.filebrowser.e.b a()
  {
    return this.c;
  }

  protected void a(Context paramContext, int paramInt)
  {
    Toast localToast = Toast.makeText(paramContext, c.a(paramInt), 0);
    localToast.setGravity(1, 0, 100);
    localToast.show();
  }

  protected void a(Context paramContext, int paramInt, DialogInterface.OnClickListener paramOnClickListener)
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(ah.a(paramContext));
    localBuilder.setMessage(c.a(paramInt));
    localBuilder.setPositiveButton(c.a(17039370), paramOnClickListener);
    localBuilder.setNegativeButton(c.a(17039360), paramOnClickListener);
    localBuilder.create().show();
  }

  public void a(com.flipdog.filebrowser.l.a.b.a parama)
  {
    if (parama.c == null)
    {
      if (!this.a.b())
        a(parama.d);
      return;
    }
    com.flipdog.filebrowser.d.b.a(parama.c, this.a);
  }

  protected void a(com.flipdog.filebrowser.l.b paramb)
  {
  }

  public void a(Object paramObject, MyActivity paramMyActivity)
  {
    this.a = paramMyActivity;
    this.b = paramObject;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.g.g
 * JD-Core Version:    0.6.2
 */