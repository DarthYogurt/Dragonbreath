package com.flipdog.filebrowser;

import android.content.Intent;
import android.os.Bundle;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.filebrowser.d.h;
import com.flipdog.filebrowser.login.b.c;

class a
  implements c
{
  a(FileBrowserActivity paramFileBrowserActivity)
  {
  }

  public int a(Object paramObject)
  {
    String[] arrayOfString;
    int i;
    if (((Integer)paramObject).intValue() == 1)
    {
      Track.me("FileBrowser", "Selected objects:", new Object[0]);
      arrayOfString = FileBrowserActivity.a(this.a).e();
      if (arrayOfString != null)
        i = arrayOfString.length;
    }
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        Intent localIntent = new Intent();
        Bundle localBundle = new Bundle();
        localBundle.putStringArray("com.flipdog.filebrowser.extra.SELECTED_PATHS", arrayOfString);
        localIntent.putExtras(localBundle);
        this.a.setResult(-1, localIntent);
        FileBrowserActivity.a(this.a).d();
        this.a.finish();
        return 0;
      }
      d.a("%s", new Object[] { arrayOfString[j] });
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.a
 * JD-Core Version:    0.6.2
 */