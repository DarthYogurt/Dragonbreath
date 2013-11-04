package com.flipdog.internal.app;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.ResolveInfo;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

final class n
{
  private List<g> b = new ArrayList();

  public n(ResolverActivity paramResolverActivity, Intent[] paramArrayOfIntent)
  {
    int i;
    if (paramArrayOfIntent != null)
    {
      i = 0;
      if (i < paramArrayOfIntent.length);
    }
    else
    {
      return;
    }
    Intent localIntent = paramArrayOfIntent[i];
    if (localIntent == null);
    while (true)
    {
      i++;
      break;
      ActivityInfo localActivityInfo = localIntent.resolveActivityInfo(paramResolverActivity.getPackageManager(), 0);
      if (localActivityInfo == null)
      {
        Log.w("ResolverActivity", "No activity found for " + localIntent);
      }
      else
      {
        ResolveInfo localResolveInfo = new ResolveInfo();
        localResolveInfo.activityInfo = localActivityInfo;
        if ((localIntent instanceof LabeledIntent))
        {
          LabeledIntent localLabeledIntent = (LabeledIntent)localIntent;
          localResolveInfo.labelRes = localLabeledIntent.b();
          localResolveInfo.nonLocalizedLabel = localLabeledIntent.c();
          localResolveInfo.icon = localLabeledIntent.d();
        }
        this.b.add(new g(paramResolverActivity, localResolveInfo, localResolveInfo.loadLabel(paramResolverActivity.getPackageManager()), null, localIntent));
      }
    }
  }

  public List<g> a()
  {
    return this.b;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.internal.app.n
 * JD-Core Version:    0.6.2
 */