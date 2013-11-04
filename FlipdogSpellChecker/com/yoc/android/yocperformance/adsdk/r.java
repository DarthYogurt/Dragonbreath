package com.yoc.android.yocperformance.adsdk;

import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Handler;
import java.util.Calendar;
import java.util.TimeZone;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

class r
  implements Runnable
{
  r(t paramt, int paramInt, String paramString1, String paramString2, Integer paramInteger, u paramu, j paramj)
  {
  }

  public void run()
  {
    HttpResponse localHttpResponse;
    while (true)
    {
      Uri.Builder localBuilder;
      try
      {
        localBuilder = t.b().buildUpon();
        localBuilder.path("adbanner.php");
        localBuilder.appendQueryParameter("who", Integer.toString(this.b));
        if (this.c != null)
        {
          localBuilder.appendQueryParameter("format", this.c);
          if (this.d != null)
            localBuilder.appendQueryParameter("gateway", this.d);
          if (this.e != null)
            localBuilder.appendQueryParameter("forcewidth", this.e.toString());
          if (this.f != null)
            localBuilder.appendQueryParameter("what", this.f.toString());
          if (!d.a(t.a(this.a)).equals("no Id"))
            localBuilder.appendQueryParameter("cid", d.a(t.a(this.a)));
          localBuilder.appendQueryParameter("time", (int)System.currentTimeMillis() / 1000);
          int i = Calendar.getInstance().getTimeZone().getOffset(System.currentTimeMillis()) / 1000;
          localBuilder.appendQueryParameter("utcoffset", i);
          String str1 = localBuilder.toString();
          t.a("Requesting ad: " + str1);
          HttpGet localHttpGet = new HttpGet(str1);
          localHttpGet.setHeader("user-agent", t.c());
          localHttpResponse = t.b(this.a).execute(localHttpGet);
          StatusLine localStatusLine = localHttpResponse.getStatusLine();
          if (localStatusLine.getStatusCode() == 200)
            break;
          throw new v(localStatusLine.getStatusCode() + " " + localStatusLine.getReasonPhrase());
        }
      }
      catch (Exception localException)
      {
        t.c(this.a).post(new i(this, this.g, localException));
        return;
      }
      localBuilder.appendQueryParameter("format", "adsnipplet");
    }
    String str2 = EntityUtils.toString(localHttpResponse.getEntity(), "UTF-8");
    if ((str2 == null) || (str2.length() == 0))
      throw new v("No ad returned");
    localHttpResponse.getEntity().consumeContent();
    t.c(this.a).post(new h(this, this.g, str2));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.r
 * JD-Core Version:    0.6.2
 */