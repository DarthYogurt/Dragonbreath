package com.flipdog.spellchecker.a;

import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import com.flipdog.commons.a.as;
import com.flipdog.commons.a.ax;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class b
{
  public static final Runnable a = new c();

  public static int a()
  {
    return Integer.parseInt(Build.VERSION.SDK);
  }

  public static int a(ArrayList<String> paramArrayList, String paramString)
  {
    if (paramString == null)
      return -1;
    String str = paramString.toLowerCase();
    int i = 0;
    int j = paramArrayList.size();
    while (true)
    {
      if (i >= j)
        return -1;
      if (((String)paramArrayList.get(i)).toLowerCase().equals(str))
        return i;
      i++;
    }
  }

  public static <T> int a(T[] paramArrayOfT, T paramT)
  {
    for (int i = 0; ; i++)
    {
      if (i >= paramArrayOfT.length)
        i = -1;
      while (as.a(paramArrayOfT[i], paramT))
        return i;
    }
  }

  public static void a(Context paramContext, Intent paramIntent, String paramString)
  {
    b(paramContext, paramIntent, paramString);
  }

  public static ContentResolver b()
  {
    return c().getContentResolver();
  }

  private static void b(Context paramContext, Intent paramIntent, String paramString)
  {
    Iterator localIterator = paramContext.getPackageManager().queryIntentActivities(paramIntent, 65536).iterator();
    ResolveInfo localResolveInfo;
    do
    {
      if (!localIterator.hasNext())
        return;
      localResolveInfo = (ResolveInfo)localIterator.next();
    }
    while (!ax.c(paramString, localResolveInfo.activityInfo.applicationInfo.packageName));
    paramIntent.setComponent(new ComponentName(localResolveInfo.activityInfo.applicationInfo.packageName, localResolveInfo.activityInfo.name));
  }

  private static Context c()
  {
    return (Context)com.flipdog.commons.i.b.a(Context.class);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.spellchecker.a.b
 * JD-Core Version:    0.6.2
 */