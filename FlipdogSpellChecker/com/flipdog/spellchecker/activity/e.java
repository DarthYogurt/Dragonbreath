package com.flipdog.spellchecker.activity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableString;
import com.flipdog.commons.a.as;
import com.flipdog.commons.a.o;
import com.flipdog.errors.activity.ErrorActivity;
import com.flipdog.spellchecker.a.d;
import com.flipdog.spellchecker.w;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class e
{
  private Activity a;

  public e(Activity paramActivity)
  {
    this.a = paramActivity;
  }

  private File a(String paramString1, String paramString2, String paramString3)
    throws FileNotFoundException, UnsupportedEncodingException, IOException
  {
    File localFile = w.b(paramString3);
    as.c(localFile);
    a(paramString1, paramString2, localFile);
    return localFile;
  }

  private void a(Activity paramActivity)
  {
    d.a(paramActivity, new SpannableString(o.a("In order to print, you will need to download <a href='market://details?id=com.flipdog.easyprint'>EasyPrint</a> from the market. EasyPrint is free and works over the Google Cloud Print connector.")));
  }

  private void a(Uri paramUri)
  {
    Intent localIntent = new Intent("android.intent.action.SEND");
    com.flipdog.spellchecker.a.b.a(this.a, localIntent, "com.flipdog.easyprint");
    localIntent.setDataAndType(paramUri, "text/html");
    this.a.startActivityForResult(localIntent, 2);
  }

  private void a(String paramString1, String paramString2, File paramFile)
    throws FileNotFoundException, UnsupportedEncodingException, IOException
  {
    FileOutputStream localFileOutputStream = new FileOutputStream(paramFile);
    try
    {
      OutputStreamWriter localOutputStreamWriter = new OutputStreamWriter(localFileOutputStream, paramString2);
      try
      {
        localOutputStreamWriter.write(paramString1);
        localOutputStreamWriter.flush();
        return;
      }
      finally
      {
      }
    }
    finally
    {
      localFileOutputStream.close();
    }
  }

  public void a(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt2 == 0);
    while (paramInt1 != 2)
      return;
    new File(paramIntent.getData().getPath()).delete();
  }

  public void a(com.flipdog.commons.c.b paramb)
  {
    try
    {
      a(paramb.b, "utf-8", "print.htm");
      a(Uri.parse(w.c("print.htm")));
      return;
    }
    catch (ActivityNotFoundException localActivityNotFoundException)
    {
      a(this.a);
      return;
    }
    catch (Exception localException)
    {
      ErrorActivity.a(this.a, localException, "Printing");
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.activity.e
 * JD-Core Version:    0.6.2
 */