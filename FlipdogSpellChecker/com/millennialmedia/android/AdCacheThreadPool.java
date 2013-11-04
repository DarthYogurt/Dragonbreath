package com.millennialmedia.android;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

final class AdCacheThreadPool
{
  private static AdCacheThreadPool sharedThreadPool;
  private ThreadPoolExecutor executor;
  private PriorityBlockingQueue queue;

  private AdCacheThreadPool()
  {
    TimeUnit localTimeUnit = TimeUnit.SECONDS;
    PriorityBlockingQueue localPriorityBlockingQueue = new PriorityBlockingQueue(32);
    this.queue = localPriorityBlockingQueue;
    this.executor = new ThreadPoolExecutor(1, 2, 30L, localTimeUnit, localPriorityBlockingQueue);
  }

  static AdCacheThreadPool sharedThreadPool()
  {
    try
    {
      if (sharedThreadPool == null)
        sharedThreadPool = new AdCacheThreadPool();
      AdCacheThreadPool localAdCacheThreadPool = sharedThreadPool;
      return localAdCacheThreadPool;
    }
    finally
    {
    }
  }

  boolean startDownloadTask(Context paramContext, String paramString, CachedAd paramCachedAd, AdCache.AdCacheTaskListener paramAdCacheTaskListener)
  {
    if ((paramContext != null) && (paramCachedAd != null));
    try
    {
      AdCacheTask localAdCacheTask = new AdCacheTask(paramContext, paramString, paramCachedAd, paramAdCacheTaskListener);
      if ((!this.queue.contains(localAdCacheTask)) && (!paramCachedAd.isOnDisk(paramContext)))
      {
        this.executor.execute(localAdCacheTask);
        bool = true;
        return bool;
      }
      boolean bool = false;
    }
    finally
    {
    }
  }

  private class AdCacheTask
    implements Runnable, Comparable<AdCacheTask>
  {
    private CachedAd ad;
    private String adName;
    private WeakReference<Context> contextRef;
    private WeakReference<AdCache.AdCacheTaskListener> listenerRef;

    AdCacheTask(Context paramString, String paramCachedAd, CachedAd paramAdCacheTaskListener, AdCache.AdCacheTaskListener arg5)
    {
      this.contextRef = new WeakReference(paramString.getApplicationContext());
      this.adName = paramCachedAd;
      this.ad = paramAdCacheTaskListener;
      Object localObject;
      if (localObject != null)
        this.listenerRef = new WeakReference(localObject);
    }

    public int compareTo(AdCacheTask paramAdCacheTask)
    {
      return this.ad.downloadPriority - paramAdCacheTask.ad.downloadPriority;
    }

    public boolean equals(Object paramObject)
    {
      if (this == paramObject)
        return true;
      if (!(paramObject instanceof AdCacheTask))
        return false;
      AdCacheTask localAdCacheTask = (AdCacheTask)paramObject;
      return this.ad.equals(localAdCacheTask.ad);
    }

    public void run()
    {
      WeakReference localWeakReference = this.listenerRef;
      AdCache.AdCacheTaskListener localAdCacheTaskListener = null;
      if (localWeakReference != null)
        localAdCacheTaskListener = (AdCache.AdCacheTaskListener)this.listenerRef.get();
      if (localAdCacheTaskListener != null)
        localAdCacheTaskListener.downloadStart(this.ad);
      HandShake.sharedHandShake((Context)this.contextRef.get()).lockAdTypeDownload(this.adName);
      boolean bool1 = this.ad.download((Context)this.contextRef.get());
      HandShake.sharedHandShake((Context)this.contextRef.get()).unlockAdTypeDownload(this.adName);
      if (!bool1)
      {
        String str1 = AdCache.getIncompleteDownload((Context)this.contextRef.get(), this.adName);
        if ((str1 != null) && (this.ad.getId().equals(str1)))
        {
          this.ad.delete((Context)this.contextRef.get());
          AdCache.setIncompleteDownload((Context)this.contextRef.get(), this.adName, null);
        }
      }
      while (true)
      {
        if (localAdCacheTaskListener != null)
          localAdCacheTaskListener.downloadCompleted(this.ad, bool1);
        return;
        Context localContext = (Context)this.contextRef.get();
        String str2 = this.adName;
        boolean bool2 = this.ad.downloadAllOrNothing;
        String str3 = null;
        if (!bool2)
          str3 = this.ad.getId();
        AdCache.setIncompleteDownload(localContext, str2, str3);
        continue;
        AdCache.setIncompleteDownload((Context)this.contextRef.get(), this.adName, null);
      }
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.android.AdCacheThreadPool
 * JD-Core Version:    0.6.2
 */