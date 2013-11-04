package com.flipdog.filebrowser.login.logic;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import com.flipdog.activity.MyActivity;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.filebrowser.login.b.c;

public class a extends AsyncTask<c, Void, Object>
{
  private final com.flipdog.filebrowser.login.a.b a;
  private ProgressDialog b;
  private c c;

  public a(com.flipdog.filebrowser.login.a.b paramb)
  {
    this.a = paramb;
  }

  private void a()
  {
    try
    {
      if ((this.b != null) && (this.b.isShowing()))
        this.b.dismiss();
      return;
    }
    catch (Exception localException)
    {
      Track.it(localException);
      this.b = null;
    }
  }

  protected Object a(c[] paramArrayOfc)
  {
    this.c = paramArrayOfc[0];
    return this.a.e.a(this.a.c);
  }

  protected void onPostExecute(Object paramObject)
  {
    this.a.a.a(new b(this, paramObject));
  }

  protected void onPreExecute()
  {
    try
    {
      this.b = new ProgressDialog(this.a.a);
      this.b.setMessage(this.a.b);
      this.b.show();
      return;
    }
    catch (Exception localException)
    {
      Track.it(localException);
      a();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.login.logic.a
 * JD-Core Version:    0.6.2
 */