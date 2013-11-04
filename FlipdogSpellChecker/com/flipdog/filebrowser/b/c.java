package com.flipdog.filebrowser.b;

import android.text.style.ClickableSpan;
import android.view.View;
import com.flipdog.a.b.b.b;

public class c extends ClickableSpan
{
  private b a;
  private com.flipdog.filebrowser.b.b.c b;

  public c(b paramb, com.flipdog.filebrowser.b.b.c paramc)
  {
    this.a = paramb;
    this.b = paramc;
  }

  public void onClick(View paramView)
  {
    this.b.c(this.a);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.b.c
 * JD-Core Version:    0.6.2
 */