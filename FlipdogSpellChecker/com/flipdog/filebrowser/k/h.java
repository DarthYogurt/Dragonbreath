package com.flipdog.filebrowser.k;

import android.content.Intent;
import com.flipdog.activity.MyActivity;
import java.util.WeakHashMap;

public class h
{
  private static int b = 0;
  private static final String c = "com.flipdog.commons.EXT_NAME";
  private static h d = null;
  private final WeakHashMap<Integer, Object> a = new WeakHashMap();

  public static h a()
  {
    if (d == null)
      d = new h();
    return d;
  }

  private int c(Intent paramIntent)
  {
    if (paramIntent == null)
      return -1;
    return paramIntent.getIntExtra("com.flipdog.commons.EXT_NAME", -1);
  }

  private int d(MyActivity paramMyActivity)
  {
    return paramMyActivity.getIntent().getIntExtra("com.flipdog.commons.EXT_NAME", -1);
  }

  public <T> T a(Intent paramIntent)
  {
    int i = c(paramIntent);
    return this.a.get(Integer.valueOf(i));
  }

  public <T> T a(MyActivity paramMyActivity)
  {
    return a(paramMyActivity.getIntent());
  }

  public void a(Intent paramIntent, Object paramObject)
  {
    if (paramObject == null)
      return;
    paramIntent.putExtra("com.flipdog.commons.EXT_NAME", b);
    WeakHashMap localWeakHashMap = this.a;
    int i = b;
    b = i + 1;
    localWeakHashMap.put(Integer.valueOf(i), paramObject);
  }

  public <T> T b(Intent paramIntent)
  {
    int i = c(paramIntent);
    return this.a.remove(Integer.valueOf(i));
  }

  public void b(MyActivity paramMyActivity)
  {
    int i = d(paramMyActivity);
    if (i != -1)
      paramMyActivity.getIntent().putExtra("com.flipdog.commons.EXT_NAME", i);
  }

  public <T> T c(MyActivity paramMyActivity)
  {
    int i = d(paramMyActivity);
    return this.a.remove(Integer.valueOf(i));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.filebrowser.k.h
 * JD-Core Version:    0.6.2
 */