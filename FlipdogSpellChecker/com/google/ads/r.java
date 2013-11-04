package com.google.ads;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebView;
import com.google.ads.internal.d;
import com.google.ads.internal.g;
import com.google.ads.util.b;
import com.google.ads.util.i.b;
import com.google.ads.util.i.c;
import java.util.HashMap;
import java.util.Locale;

public class r
  implements o
{
  public void a(d paramd, HashMap<String, String> paramHashMap, WebView paramWebView)
  {
    String str1 = (String)paramHashMap.get("u");
    if (str1 == null)
    {
      b.e("Could not get URL from click gmsg.");
      return;
    }
    g localg = paramd.n();
    if (localg != null)
    {
      Uri localUri4 = Uri.parse(str1);
      String str2 = localUri4.getHost();
      if ((str2 != null) && (str2.toLowerCase(Locale.US).endsWith(".admob.com")))
      {
        String str3 = localUri4.getPath();
        String str4 = null;
        if (str3 != null)
        {
          String[] arrayOfString = str3.split("/");
          int i = arrayOfString.length;
          str4 = null;
          if (i >= 4)
            str4 = arrayOfString[2] + "/" + arrayOfString[3];
        }
        localg.a(str4);
      }
    }
    n localn = paramd.i();
    Context localContext = (Context)localn.f.a();
    Uri localUri1 = Uri.parse(str1);
    try
    {
      al localal = (al)localn.s.a();
      if ((localal != null) && (localal.a(localUri1)))
      {
        Uri localUri3 = localal.a(localUri1, localContext);
        localUri2 = localUri3;
        new Thread(new ae(localUri2.toString(), localContext)).start();
        return;
      }
    }
    catch (am localam)
    {
      while (true)
      {
        b.e("Unable to append parameter to URL: " + str1);
        Uri localUri2 = localUri1;
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.ads.r
 * JD-Core Version:    0.6.2
 */