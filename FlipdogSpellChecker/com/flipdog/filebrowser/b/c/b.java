package com.flipdog.filebrowser.b.c;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.flipdog.commons.a.as;
import com.flipdog.commons.actionbar.ActionBarActivity;
import com.flipdog.filebrowser.b.b.a;
import com.flipdog.filebrowser.k.k;
import com.flipdog.m;

public class b
{
  public final ActionBarActivity a;
  public final LayoutInflater b;
  public final TextView c;
  public final TextView d;
  public final TextView e;
  public final TextView f;
  public final TextView g;
  public final ListView h;
  public final LinearLayout i;
  private final RelativeLayout j;

  public b(ActionBarActivity paramActionBarActivity, ListView paramListView)
  {
    this.a = paramActionBarActivity;
    this.b = LayoutInflater.from(paramActionBarActivity);
    this.h = paramListView;
    this.c = ((TextView)as.a(paramActionBarActivity, m.fbrowse_textview_path));
    this.g = ((TextView)as.a(paramActionBarActivity, m.fbrowse_textview_path_root));
    this.d = ((TextView)as.a(paramActionBarActivity, m.fbrowse_textview_tasks));
    this.e = ((TextView)as.a(paramActionBarActivity, m.fbrowse_textview_dirs));
    this.f = ((TextView)as.a(paramActionBarActivity, m.fbrowse_textview_files));
    this.j = ((RelativeLayout)as.a(paramActionBarActivity, m.fbrowse_not_logging_layout));
    this.i = ((LinearLayout)as.a(paramActionBarActivity, m.fbrowse_layout_path));
    this.c.setMovementMethod(LinkMovementMethod.getInstance());
    this.c.setLinkTextColor(a.a());
    this.c.setHighlightColor(-16776961);
  }

  public void a(boolean paramBoolean)
  {
    k.a(this.h, paramBoolean);
    RelativeLayout localRelativeLayout = this.j;
    if (paramBoolean);
    for (boolean bool = false; ; bool = true)
    {
      k.a(localRelativeLayout, bool);
      return;
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.b.c.b
 * JD-Core Version:    0.6.2
 */