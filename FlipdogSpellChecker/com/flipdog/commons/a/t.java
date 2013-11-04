package com.flipdog.commons.a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class t
{
  public static Intent a(Context paramContext)
    throws PackageManager.NameNotFoundException
  {
    PackageManager localPackageManager = paramContext.getPackageManager();
    List localList = localPackageManager.queryIntentActivities(localPackageManager.getLaunchIntentForPackage(paramContext.getPackageName()), 0);
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = localList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        if (localArrayList.size() == 1)
          break;
        throw new RuntimeException(localArrayList.size());
      }
      ActivityInfo localActivityInfo = ((ResolveInfo)localIterator.next()).activityInfo;
      Intent localIntent = new Intent();
      localIntent.setComponent(new ComponentName(localActivityInfo.applicationInfo.packageName, localActivityInfo.name));
      localArrayList.add(localIntent);
    }
    return (Intent)localArrayList.get(0);
  }

  public static boolean a(Context paramContext, Intent paramIntent)
  {
    return paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).size() > 0;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.t
 * JD-Core Version:    0.6.2
 */