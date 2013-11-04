package android.support.v4.content;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class LocalBroadcastManager
{
  private static final boolean DEBUG = false;
  static final int MSG_EXEC_PENDING_BROADCASTS = 1;
  private static final String TAG = "LocalBroadcastManager";
  private static LocalBroadcastManager mInstance;
  private static final Object mLock = new Object();
  private final HashMap<String, ArrayList<ReceiverRecord>> mActions = new HashMap();
  private final Context mAppContext;
  private final Handler mHandler;
  private final ArrayList<BroadcastRecord> mPendingBroadcasts = new ArrayList();
  private final HashMap<BroadcastReceiver, ArrayList<IntentFilter>> mReceivers = new HashMap();

  private LocalBroadcastManager(Context paramContext)
  {
    this.mAppContext = paramContext;
    this.mHandler = new Handler(paramContext.getMainLooper())
    {
      public void handleMessage(Message paramAnonymousMessage)
      {
        switch (paramAnonymousMessage.what)
        {
        default:
          super.handleMessage(paramAnonymousMessage);
          return;
        case 1:
        }
        LocalBroadcastManager.this.executePendingBroadcasts();
      }
    };
  }

  private void executePendingBroadcasts()
  {
    ((BroadcastRecord[])null);
    while (true)
    {
      BroadcastRecord localBroadcastRecord;
      int k;
      synchronized (this.mReceivers)
      {
        int i = this.mPendingBroadcasts.size();
        if (i <= 0)
          return;
        BroadcastRecord[] arrayOfBroadcastRecord = new BroadcastRecord[i];
        this.mPendingBroadcasts.toArray(arrayOfBroadcastRecord);
        this.mPendingBroadcasts.clear();
        int j = 0;
        if (j >= arrayOfBroadcastRecord.length)
          break;
        localBroadcastRecord = arrayOfBroadcastRecord[j];
        k = 0;
        if (k >= localBroadcastRecord.receivers.size())
          j++;
      }
      ((ReceiverRecord)localBroadcastRecord.receivers.get(k)).receiver.onReceive(this.mAppContext, localBroadcastRecord.intent);
      k++;
    }
  }

  public static LocalBroadcastManager getInstance(Context paramContext)
  {
    synchronized (mLock)
    {
      if (mInstance == null)
        mInstance = new LocalBroadcastManager(paramContext.getApplicationContext());
      LocalBroadcastManager localLocalBroadcastManager = mInstance;
      return localLocalBroadcastManager;
    }
  }

  public void registerReceiver(BroadcastReceiver paramBroadcastReceiver, IntentFilter paramIntentFilter)
  {
    synchronized (this.mReceivers)
    {
      ReceiverRecord localReceiverRecord = new ReceiverRecord(paramIntentFilter, paramBroadcastReceiver);
      ArrayList localArrayList1 = (ArrayList)this.mReceivers.get(paramBroadcastReceiver);
      if (localArrayList1 == null)
      {
        localArrayList1 = new ArrayList(1);
        this.mReceivers.put(paramBroadcastReceiver, localArrayList1);
      }
      localArrayList1.add(paramIntentFilter);
      int i = 0;
      if (i >= paramIntentFilter.countActions())
        return;
      String str = paramIntentFilter.getAction(i);
      ArrayList localArrayList2 = (ArrayList)this.mActions.get(str);
      if (localArrayList2 == null)
      {
        localArrayList2 = new ArrayList(1);
        this.mActions.put(str, localArrayList2);
      }
      localArrayList2.add(localReceiverRecord);
      i++;
    }
  }

  public boolean sendBroadcast(Intent paramIntent)
  {
    int i;
    label156: int j;
    ArrayList localArrayList2;
    int k;
    label174: int m;
    synchronized (this.mReceivers)
    {
      String str1 = paramIntent.getAction();
      String str2 = paramIntent.resolveTypeIfNeeded(this.mAppContext.getContentResolver());
      Uri localUri = paramIntent.getData();
      String str3 = paramIntent.getScheme();
      Set localSet = paramIntent.getCategories();
      if ((0x8 & paramIntent.getFlags()) != 0)
        break label487;
      i = 0;
      if (i != 0)
        Log.v("LocalBroadcastManager", "Resolving type " + str2 + " scheme " + str3 + " of intent " + paramIntent);
      ArrayList localArrayList1 = (ArrayList)this.mActions.get(paramIntent.getAction());
      if (localArrayList1 == null)
        break label474;
      if (i == 0)
        break label478;
      Log.v("LocalBroadcastManager", "Action list: " + localArrayList1);
      break label478;
      if (j >= localArrayList1.size())
      {
        if (localArrayList2 == null)
          break label474;
        k = 0;
        if (k < localArrayList2.size())
          break label454;
        this.mPendingBroadcasts.add(new BroadcastRecord(paramIntent, localArrayList2));
        if (!this.mHandler.hasMessages(1))
          this.mHandler.sendEmptyMessage(1);
        return true;
      }
      ReceiverRecord localReceiverRecord = (ReceiverRecord)localArrayList1.get(j);
      if (i != 0)
        Log.v("LocalBroadcastManager", "Matching against filter " + localReceiverRecord.filter);
      if (localReceiverRecord.broadcasting)
      {
        if (i != 0)
          Log.v("LocalBroadcastManager", "  Filter's target already added");
      }
      else
      {
        m = localReceiverRecord.filter.match(str1, str2, str3, localUri, localSet, "LocalBroadcastManager");
        if (m >= 0)
        {
          if (i != 0)
            Log.v("LocalBroadcastManager", "  Filter matched!  match=0x" + Integer.toHexString(m));
          if (localArrayList2 == null)
            localArrayList2 = new ArrayList();
          localArrayList2.add(localReceiverRecord);
          localReceiverRecord.broadcasting = true;
        }
      }
    }
    String str4;
    if (i != 0)
      switch (m)
      {
      default:
        str4 = "unknown reason";
      case -3:
      case -4:
      case -2:
      case -1:
      }
    while (true)
    {
      Log.v("LocalBroadcastManager", "  Filter did not match: " + str4);
      break label493;
      label454: ((ReceiverRecord)localArrayList2.get(k)).broadcasting = false;
      k++;
      break label174;
      label474: return false;
      label478: j = 0;
      localArrayList2 = null;
      break label156;
      label487: i = 1;
      break;
      label493: j++;
      break label156;
      str4 = "action";
      continue;
      str4 = "category";
      continue;
      str4 = "data";
      continue;
      str4 = "type";
    }
  }

  public void sendBroadcastSync(Intent paramIntent)
  {
    if (sendBroadcast(paramIntent))
      executePendingBroadcasts();
  }

  public void unregisterReceiver(BroadcastReceiver paramBroadcastReceiver)
  {
    ArrayList localArrayList1;
    int i;
    synchronized (this.mReceivers)
    {
      localArrayList1 = (ArrayList)this.mReceivers.remove(paramBroadcastReceiver);
      if (localArrayList1 != null)
        break label173;
      return;
      if (i >= localArrayList1.size())
        return;
    }
    IntentFilter localIntentFilter = (IntentFilter)localArrayList1.get(i);
    for (int j = 0; ; j++)
    {
      if (j >= localIntentFilter.countActions())
      {
        i++;
        break;
      }
      String str = localIntentFilter.getAction(j);
      ArrayList localArrayList2 = (ArrayList)this.mActions.get(str);
      if (localArrayList2 != null)
      {
        for (int k = 0; ; k++)
        {
          if (k >= localArrayList2.size())
          {
            if (localArrayList2.size() > 0)
              break;
            this.mActions.remove(str);
            break;
          }
          if (((ReceiverRecord)localArrayList2.get(k)).receiver == paramBroadcastReceiver)
          {
            localArrayList2.remove(k);
            k--;
          }
        }
        label173: i = 0;
        break;
      }
    }
  }

  private static class BroadcastRecord
  {
    final Intent intent;
    final ArrayList<LocalBroadcastManager.ReceiverRecord> receivers;

    BroadcastRecord(Intent paramIntent, ArrayList<LocalBroadcastManager.ReceiverRecord> paramArrayList)
    {
      this.intent = paramIntent;
      this.receivers = paramArrayList;
    }
  }

  private static class ReceiverRecord
  {
    boolean broadcasting;
    final IntentFilter filter;
    final BroadcastReceiver receiver;

    ReceiverRecord(IntentFilter paramIntentFilter, BroadcastReceiver paramBroadcastReceiver)
    {
      this.filter = paramIntentFilter;
      this.receiver = paramBroadcastReceiver;
    }

    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder(128);
      localStringBuilder.append("Receiver{");
      localStringBuilder.append(this.receiver);
      localStringBuilder.append(" filter=");
      localStringBuilder.append(this.filter);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     android.support.v4.content.LocalBroadcastManager
 * JD-Core Version:    0.6.2
 */