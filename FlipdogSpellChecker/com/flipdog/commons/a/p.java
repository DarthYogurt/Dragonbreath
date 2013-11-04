package com.flipdog.commons.a;

import android.graphics.drawable.Drawable;
import android.text.Html.ImageGetter;
import com.flipdog.editor.ah;

class p
  implements Html.ImageGetter
{
  public Drawable getDrawable(String paramString)
  {
    if (paramString == null);
    while (!ax.b(paramString, new String[] { "android.resource://", "content://", "file://" }))
      return null;
    return ah.b(paramString);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.p
 * JD-Core Version:    0.6.2
 */