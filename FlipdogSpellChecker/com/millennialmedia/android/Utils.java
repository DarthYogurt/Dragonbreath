package com.millennialmedia.android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

class Utils
{
  static class HttpUtils
  {
    static void executeUrl(String paramString)
    {
      Utils.ThreadUtils.execute(new Runnable()
      {
        public void run()
        {
          try
          {
            new DefaultHttpClient().execute(new HttpGet(this.val$url));
            MMSDK.Log.d("Executed Url :\"" + this.val$url + "\"");
            return;
          }
          catch (IOException localIOException)
          {
            MMSDK.Log.e(localIOException);
          }
        }
      });
    }
  }

  static class IntentUtils
  {
    private static void fixDataAndTypeForVideo(Context paramContext, Intent paramIntent)
    {
      Uri localUri = paramIntent.getData();
      if (localUri != null)
      {
        String str = localUri.getLastPathSegment();
        if ((TextUtils.isEmpty(paramIntent.getStringExtra("class"))) && (!TextUtils.isEmpty(str)) && ((str.endsWith(".mp4")) || (str.endsWith(".3gp")) || (str.endsWith(".mkv")) || (str.endsWith("content.once"))))
          paramIntent.setDataAndType(paramIntent.getData(), "video/*");
      }
    }

    static Intent getIntentForUri(HttpRedirection.RedirectionListenerImpl paramRedirectionListenerImpl)
    {
      if (paramRedirectionListenerImpl == null);
      Uri localUri;
      Context localContext;
      do
      {
        return null;
        localUri = paramRedirectionListenerImpl.destinationUri;
        localContext = (Context)paramRedirectionListenerImpl.weakContext.get();
      }
      while (localContext == null);
      Intent localIntent1 = null;
      String str1;
      if (localUri != null)
      {
        str1 = localUri.getScheme();
        localIntent1 = null;
        if (str1 != null)
        {
          if (!str1.equalsIgnoreCase("market"))
            break label104;
          MMSDK.Log.v("Creating Android Market intent.");
          localIntent1 = new Intent("android.intent.action.VIEW", localUri);
          MMSDK.Event.intentStarted(localContext, "market", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
      }
      while (localIntent1 != null)
      {
        MMSDK.Log.v("%s resolved to Intent: %s", new Object[] { localUri, localIntent1 });
        return localIntent1;
        label104: if (str1.equalsIgnoreCase("rtsp"))
        {
          MMSDK.Log.v("Creating streaming video player intent.");
          localIntent1 = new Intent(localContext, MMActivity.class);
          localIntent1.setData(localUri);
          localIntent1.putExtra("class", "com.millennialmedia.android.VideoPlayerActivity");
        }
        else if (str1.equalsIgnoreCase("tel"))
        {
          MMSDK.Log.v("Creating telephone intent.");
          localIntent1 = new Intent("android.intent.action.DIAL", localUri);
          MMSDK.Event.intentStarted(localContext, "tel", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
        else if (str1.equalsIgnoreCase("sms"))
        {
          MMSDK.Log.v("Creating txt message intent.");
          localIntent1 = new Intent("android.intent.action.VIEW");
          String str3 = localUri.getSchemeSpecificPart();
          int i = str3.indexOf("?body=");
          if (i != -1)
            str3 = str3.substring(0, i);
          localIntent1.putExtra("address", str3.replace(',', ';'));
          if (i != -1)
            localIntent1.putExtra("sms_body", localUri.getSchemeSpecificPart().substring(i + 6));
          localIntent1.setType("vnd.android-dir/mms-sms");
          MMSDK.Event.intentStarted(localContext, "sms", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
        else if (str1.equalsIgnoreCase("mailto"))
        {
          localIntent1 = new Intent("android.intent.action.VIEW", localUri);
          MMSDK.Event.intentStarted(localContext, "email", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
        else if (str1.equalsIgnoreCase("geo"))
        {
          MMSDK.Log.v("Creating Google Maps intent.");
          localIntent1 = new Intent("android.intent.action.VIEW", localUri);
          MMSDK.Event.intentStarted(localContext, "geo", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
        else if (str1.equalsIgnoreCase("https"))
        {
          MMSDK.Log.v("Creating launch browser intent.");
          localIntent1 = new Intent("android.intent.action.VIEW", localUri);
          MMSDK.Event.intentStarted(localContext, "browser", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
        else if (str1.equalsIgnoreCase("mmbrowser"))
        {
          String str2 = localUri.toString().substring(12);
          if ((str2 != null) && (!str2.contains("://")))
            str2 = str2.replaceFirst("//", "://");
          MMSDK.Log.v("MMBrowser - Creating launch browser intent.");
          localIntent1 = new Intent("android.intent.action.VIEW", Uri.parse(str2));
          MMSDK.Event.intentStarted(localContext, "browser", paramRedirectionListenerImpl.creatorAdImplInternalId);
        }
        else if (str1.equalsIgnoreCase("http"))
        {
          if ((localUri.getLastPathSegment() != null) && ((localUri.getLastPathSegment().endsWith(".mp4")) || (localUri.getLastPathSegment().endsWith(".3gp"))))
          {
            MMSDK.Log.v("Creating video player intent.");
            localIntent1 = new Intent(localContext, MMActivity.class);
            localIntent1.setData(localUri);
            localIntent1.putExtra("class", "com.millennialmedia.android.VideoPlayerActivity");
            MMSDK.Event.intentStarted(localContext, "streamingVideo", paramRedirectionListenerImpl.creatorAdImplInternalId);
          }
          else
          {
            if (paramRedirectionListenerImpl.canOpenOverlay())
            {
              MMSDK.Log.v("Creating launch overlay intent.");
              Intent localIntent2 = new Intent(localContext, MMActivity.class);
              localIntent2.putExtra("class", AdViewOverlayActivity.class.getCanonicalName());
              localIntent2.setData(localUri);
              return localIntent2;
            }
            MMSDK.Log.v("Creating launch browser intent.");
            MMSDK.Event.intentStarted(localContext, "browser", paramRedirectionListenerImpl.creatorAdImplInternalId);
            localIntent1 = new Intent("android.intent.action.VIEW", localUri);
          }
        }
        else
        {
          MMSDK.Log.v("Creating intent for unrecognized URI. %s", new Object[] { localUri });
          localIntent1 = new Intent("android.intent.action.VIEW", localUri);
        }
      }
      MMSDK.Log.v("%s", new Object[] { localUri });
      return localIntent1;
    }

    static void startActionView(Context paramContext, String paramString)
    {
      startActivity(paramContext, new Intent("android.intent.action.VIEW", Uri.parse(paramString)));
    }

    static void startActivity(Context paramContext, Intent paramIntent)
    {
      if (!(paramContext instanceof Activity))
        paramIntent.addFlags(268435456);
      fixDataAndTypeForVideo(paramContext, paramIntent);
      paramContext.startActivity(paramIntent);
    }

    static void startAdViewOverlayActivity(Context paramContext)
    {
      Intent localIntent = new Intent(paramContext, MMActivity.class);
      localIntent.putExtra("class", "com.millennialmedia.android.AdViewOverlayActivity");
      startActivity(paramContext, localIntent);
    }

    static void startAdViewOverlayActivity(Context paramContext, Intent paramIntent)
    {
      paramIntent.setClass(paramContext, MMActivity.class);
      paramIntent.putExtra("class", "com.millennialmedia.android.AdViewOverlayActivity");
      startActivity(paramContext, paramIntent);
    }

    static void startAdViewOverlayActivityWithData(Context paramContext, String paramString)
    {
      Intent localIntent = new Intent(paramContext, MMActivity.class);
      localIntent.putExtra("class", "com.millennialmedia.android.AdViewOverlayActivity");
      localIntent.setData(Uri.parse(paramString));
      startActivity(paramContext, localIntent);
    }

    static void startCachedVideoPlayerActivity(Context paramContext, Intent paramIntent)
    {
      paramIntent.setClass(paramContext, MMActivity.class);
      paramIntent.putExtra("class", "com.millennialmedia.android.CachedVideoPlayerActivity");
      startActivity(paramContext, paramIntent);
    }

    static void startPickerActivity(Context paramContext, File paramFile, String paramString)
    {
      Intent localIntent = new Intent(paramContext, MMActivity.class);
      localIntent.setData(Uri.fromFile(paramFile));
      localIntent.putExtra("type", paramString);
      localIntent.putExtra("class", "com.millennialmedia.android.BridgeMMMedia$PickerActivity");
      startActivity(paramContext, localIntent);
    }

    static void startVideoPlayerActivityWithData(Context paramContext, Uri paramUri)
    {
      Intent localIntent = new Intent(paramContext, MMActivity.class);
      localIntent.setData(paramUri);
      localIntent.putExtra("class", "com.millennialmedia.android.VideoPlayerActivity");
      startActivity(paramContext, localIntent);
    }

    static void startVideoPlayerActivityWithData(Context paramContext, File paramFile)
    {
      startVideoPlayerActivityWithData(paramContext, Uri.fromFile(paramFile));
    }

    static void startVideoPlayerActivityWithData(Context paramContext, String paramString)
    {
      startVideoPlayerActivityWithData(paramContext, Uri.parse(paramString));
    }
  }

  static class ThreadUtils
  {
    private static final ExecutorService cachedThreadExecutor = Executors.newCachedThreadPool();

    static void execute(Runnable paramRunnable)
    {
      cachedThreadExecutor.execute(paramRunnable);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.Utils
 * JD-Core Version:    0.6.2
 */