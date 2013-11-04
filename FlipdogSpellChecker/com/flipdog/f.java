package com.flipdog;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.util.Linkify;
import com.flipdog.commons.a.a;
import com.flipdog.commons.a.ad;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.filebrowser.g;
import com.flipdog.internal.app.ChooserActivity;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class f
{
  private String a = "";
  private Activity b;
  private boolean c = true;
  private Fragment d;
  private boolean e = false;

  public f(Activity paramActivity)
  {
    if (paramActivity == null)
      throw new IllegalArgumentException("activity");
    this.b = paramActivity;
  }

  public f(Fragment paramFragment)
  {
    if (paramFragment == null)
      throw new IllegalArgumentException("fragment");
    this.d = paramFragment;
  }

  public static Uri a(int paramInt, Intent paramIntent)
  {
    List localList = b(paramInt, paramIntent);
    if (localList.size() == 0)
      return null;
    return (Uri)localList.get(0);
  }

  private void a(int paramInt, ArrayList<Intent> paramArrayList)
  {
    ArrayList localArrayList = d(paramArrayList);
    if (localArrayList.size() != 0)
    {
      if (localArrayList.size() == 1)
      {
        a((Intent)localArrayList.get(0), paramInt);
        return;
      }
      a(localArrayList, 50331648);
      try
      {
        b(localArrayList, paramInt);
        return;
      }
      catch (Exception localException)
      {
        Track.it(localException);
        a(e());
        return;
      }
    }
    a(e());
  }

  private void a(Context paramContext)
  {
    SpannableString localSpannableString = new SpannableString("In order to download and save to your SD card, you need to have one of the following file managers installed on your device:\n\n * OI File Manager\n    http://bit.ly/cO99Zz\n * ES File Explorer\n    http://bit.ly/bMY7ER\n * AndExplorer\n    http://bit.ly/9vt4Tb\n\nAll file managers can be downloaded for free from the Google Marketplace.");
    Linkify.addLinks(localSpannableString, 1);
    a.a(paramContext, localSpannableString);
  }

  private void a(Intent paramIntent, int paramInt)
  {
    if (this.d != null)
    {
      this.d.startActivityForResult(paramIntent, paramInt);
      return;
    }
    this.b.startActivityForResult(paramIntent, paramInt);
  }

  private void a(ArrayList<Intent> paramArrayList)
  {
    if (this.c);
    for (Intent localIntent = new Intent("com.estrongs.action.PICK_DIRECTORY"); ; localIntent = new Intent("com.estrongs.action.PICK_FILE"))
    {
      localIntent.putExtra("com.estrongs.intent.extra.BUTTON_TITLE", this.a);
      paramArrayList.add(localIntent);
      return;
    }
  }

  private void a(ArrayList<Intent> paramArrayList, int paramInt)
  {
    Iterator localIterator = paramArrayList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      ((Intent)localIterator.next()).addFlags(paramInt);
    }
  }

  public static List<Uri> b(int paramInt, Intent paramIntent)
  {
    LinkedList localLinkedList = new LinkedList();
    if (paramInt != -1);
    while (true)
    {
      return localLinkedList;
      if (paramIntent != null)
      {
        Uri localUri = paramIntent.getData();
        if (localUri != null)
          localLinkedList.add(localUri);
        Bundle localBundle = paramIntent.getExtras();
        if (localBundle != null)
        {
          String[] arrayOfString = localBundle.getStringArray("com.flipdog.filebrowser.extra.SELECTED_PATHS");
          if (arrayOfString != null)
          {
            int i = arrayOfString.length;
            for (int j = 0; j < i; j++)
              localLinkedList.add(Uri.parse(arrayOfString[j]));
          }
        }
      }
    }
  }

  private void b()
  {
    this.c = true;
  }

  private void b(ArrayList<Intent> paramArrayList)
  {
    if (this.c);
    for (Intent localIntent = new Intent("org.openintents.action.PICK_DIRECTORY"); ; localIntent = new Intent("org.openintents.action.PICK_FILE"))
    {
      localIntent.putExtra("org.openintents.extra.TITLE", this.a);
      paramArrayList.add(localIntent);
      return;
    }
  }

  private void b(ArrayList<Intent> paramArrayList, int paramInt)
  {
    Intent localIntent = new Intent(e(), ChooserActivity.class);
    if (this.a != null)
      localIntent.putExtra("android.intent.extra.TITLE", this.a);
    localIntent.putExtra("android.intent.extra.INITIAL_INTENTS", (Intent[])ad.a(Intent.class, paramArrayList));
    a(localIntent, paramInt);
  }

  private void c()
  {
    this.c = false;
  }

  private void c(ArrayList<Intent> paramArrayList)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("android.intent.action.PICK");
    Uri localUri = Uri.fromFile(new File("/sdcard"));
    if (this.c)
      localIntent.setDataAndType(localUri, "vnd.android.cursor.dir/lysesoft.andexplorer.directory");
    while (true)
    {
      localIntent.putExtra("explorer_title", this.a);
      paramArrayList.add(localIntent);
      return;
      localIntent.setDataAndType(localUri, "vnd.android.cursor.dir/lysesoft.andexplorer.file");
    }
  }

  private ArrayList<Intent> d()
  {
    ArrayList localArrayList = new ArrayList();
    if (this.c);
    for (Intent localIntent = g.a(e(), this.e); ; localIntent = g.b(e(), this.e))
    {
      localArrayList.add(localIntent);
      a(localArrayList);
      b(localArrayList);
      c(localArrayList);
      return localArrayList;
    }
  }

  private ArrayList<Intent> d(ArrayList<Intent> paramArrayList)
  {
    ArrayList localArrayList = new ArrayList();
    PackageManager localPackageManager = e().getPackageManager();
    Iterator localIterator1 = paramArrayList.iterator();
    while (true)
    {
      if (!localIterator1.hasNext())
        return localArrayList;
      Intent localIntent1 = (Intent)localIterator1.next();
      Iterator localIterator2 = localPackageManager.queryIntentActivities(localIntent1, 0).iterator();
      while (localIterator2.hasNext())
      {
        ResolveInfo localResolveInfo = (ResolveInfo)localIterator2.next();
        Intent localIntent2 = new Intent(localIntent1);
        ActivityInfo localActivityInfo = localResolveInfo.activityInfo;
        localIntent2.setComponent(new ComponentName(localActivityInfo.applicationInfo.packageName, localActivityInfo.name));
        localIntent2.putExtras(localIntent1);
        localArrayList.add(localIntent2);
      }
    }
  }

  private Context e()
  {
    if ((this.d == null) && (this.b == null))
      throw new RuntimeException("Both activity and fragment are null.");
    Object localObject;
    if (this.d != null)
    {
      localObject = this.d.getActivity();
      if (localObject == null)
        throw new RuntimeException("Fragment's activity is null.");
    }
    else
    {
      localObject = this.b;
    }
    return localObject;
  }

  private void e(int paramInt)
  {
    a(paramInt, d());
  }

  public void a()
  {
    this.e = true;
  }

  public void a(int paramInt)
  {
    b();
    e(paramInt);
  }

  public void a(int paramInt, boolean paramBoolean, String paramString)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramBoolean);
    for (Intent localIntent1 = g.a(e()); ; localIntent1 = g.b(e(), false))
    {
      localIntent1.putExtra("com.flipdog.filebrowser.extra.FILTER_BY_EXTENSION", paramString);
      localArrayList.add(localIntent1);
      Intent localIntent2 = new Intent();
      localIntent2.setType("*/*");
      localIntent2.addCategory("android.intent.category.OPENABLE");
      localIntent2.setAction("android.intent.action.GET_CONTENT");
      localArrayList.add(localIntent2);
      a(paramInt, localArrayList);
      return;
    }
  }

  public void a(String paramString)
  {
    this.a = paramString;
  }

  public void b(int paramInt)
  {
    c();
    e(paramInt);
  }

  public void c(int paramInt)
  {
    a(paramInt, true, null);
  }

  public void d(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(g.b(e()));
    Intent localIntent = new Intent();
    localIntent.setType("image/*");
    localIntent.setAction("android.intent.action.GET_CONTENT");
    localArrayList.add(localIntent);
    a(paramInt, localArrayList);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.f
 * JD-Core Version:    0.6.2
 */