package com.google.ads;

import android.webkit.WebView;
import com.google.ads.internal.d;
import com.google.ads.internal.e;
import com.google.ads.util.b;
import java.util.HashMap;

public class z
  implements o
{
  private AdActivity.StaticMethodWrapper a;

  public z()
  {
    this(new AdActivity.StaticMethodWrapper());
  }

  public z(AdActivity.StaticMethodWrapper paramStaticMethodWrapper)
  {
    this.a = paramStaticMethodWrapper;
  }

  public void a(d paramd, HashMap<String, String> paramHashMap, WebView paramWebView)
  {
    String str = (String)paramHashMap.get("a");
    if (str == null)
    {
      b.a("Could not get the action parameter for open GMSG.");
      return;
    }
    if (str.equals("webapp"))
    {
      this.a.launchAdActivity(paramd, new e("webapp", paramHashMap));
      return;
    }
    if (str.equals("expand"))
    {
      this.a.launchAdActivity(paramd, new e("expand", paramHashMap));
      return;
    }
    this.a.launchAdActivity(paramd, new e("intent", paramHashMap));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.z
 * JD-Core Version:    0.6.2
 */