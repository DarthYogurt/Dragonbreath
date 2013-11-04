package com.flipdog.filebrowser.b.c;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.flipdog.commons.a.as;
import com.flipdog.m;

public class a
{
  public final ImageView a;
  public final ImageView b;
  public final TextView c;
  public final TextView d;
  public final LinearLayout e;
  public final TextView f;
  public final TextView g;
  public final LinearLayout h;

  public a(View paramView, View.OnClickListener paramOnClickListener)
  {
    this.a = ((ImageView)as.a(paramView, m.fbrowse_item_imageview_checkbox));
    this.a.setOnClickListener(paramOnClickListener);
    this.b = ((ImageView)as.a(paramView, m.fbrowse_item_imageview_type));
    this.c = ((TextView)as.a(paramView, m.fbrowse_item_textview_name));
    this.d = ((TextView)as.a(paramView, m.fbrowse_item_textview_size));
    this.e = ((LinearLayout)as.a(paramView, m.fbrowse_item_layout_date));
    this.f = ((TextView)as.a(paramView, m.fbrowse_item_textview_date));
    this.g = ((TextView)as.a(paramView, m.fbrowse_item_textview_time));
    this.h = ((LinearLayout)as.a(paramView, m.fbrowse_item_layout_submenu));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.b.c.a
 * JD-Core Version:    0.6.2
 */