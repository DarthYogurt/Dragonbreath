package com.yoc.android.yocperformance.adsdk;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Handler;
import java.io.BufferedInputStream;
import java.io.InputStream;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

class q
  implements Runnable
{
  Drawable a;

  q(t paramt, Uri paramUri, n paramn)
  {
  }

  public void run()
  {
    HttpResponse localHttpResponse;
    try
    {
      t.a("Requesting ad image: " + this.c.toString());
      HttpGet localHttpGet = new HttpGet(this.c.toString());
      localHttpGet.setHeader("user-agent", t.c());
      localHttpResponse = t.b(this.b).execute(localHttpGet);
      StatusLine localStatusLine = localHttpResponse.getStatusLine();
      if (localStatusLine.getStatusCode() != 200)
        throw new Exception(localStatusLine.getStatusCode() + " " + localStatusLine.getReasonPhrase());
    }
    catch (Exception localException)
    {
      t.c(this.b).post(new m(this, this.d, localException));
      return;
    }
    Header localHeader = localHttpResponse.getFirstHeader("content-type");
    if (localHeader == null)
      throw new IllegalStateException("No content header found");
    String str = localHeader.getValue();
    BufferedInputStream localBufferedInputStream = new BufferedInputStream(localHttpResponse.getEntity().getContent(), 8192);
    try
    {
      if (str.matches("image/gif"))
        this.a = f.a(localBufferedInputStream);
      while (true)
      {
        localBufferedInputStream.close();
        localHttpResponse.getEntity().consumeContent();
        t.c(this.b).post(new l(this, this.d));
        return;
        if (str.matches("image/.+"))
        {
          Bitmap localBitmap = BitmapFactory.decodeStream(localBufferedInputStream);
          localBitmap.setDensity(new Canvas().getDensity());
          this.a = new BitmapDrawable(localBitmap);
        }
      }
    }
    finally
    {
      localBufferedInputStream.close();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.yoc.android.yocperformance.adsdk.q
 * JD-Core Version:    0.6.2
 */