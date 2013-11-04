package com.flipdog.commons.a;

import android.app.Activity;
import android.app.Dialog;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.b.b.h;
import com.flipdog.commons.i.b;
import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.WeakHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class as
{
  public static int a(CharSequence paramCharSequence, char paramChar)
  {
    return a(paramCharSequence, paramChar, 0);
  }

  public static int a(CharSequence paramCharSequence, char paramChar, int paramInt)
  {
    int i = paramCharSequence.length();
    for (int j = paramInt; ; j++)
    {
      if (j >= i)
        j = -1;
      while (paramCharSequence.charAt(j) == paramChar)
        return j;
    }
  }

  public static int a(List<String> paramList, String paramString)
  {
    if (paramString == null)
      return -1;
    String str = paramString.toLowerCase();
    int i = 0;
    int j = paramList.size();
    while (true)
    {
      if (i >= j)
        return -1;
      if (((String)paramList.get(i)).toLowerCase().equals(str))
        return i;
      i++;
    }
  }

  public static <TView extends View> TView a(Activity paramActivity, int paramInt)
  {
    return paramActivity.findViewById(paramInt);
  }

  public static <TView extends View> TView a(Dialog paramDialog, int paramInt)
  {
    return paramDialog.findViewById(paramInt);
  }

  public static <TView extends View> TView a(Fragment paramFragment, int paramInt)
  {
    View localView = paramFragment.getView();
    if (localView != null)
      return localView.findViewById(paramInt);
    return paramFragment.getActivity().findViewById(paramInt);
  }

  public static <TView extends View> TView a(View paramView, int paramInt)
  {
    return paramView.findViewById(paramInt);
  }

  public static View a(View paramView, ViewGroup paramViewGroup, LayoutInflater paramLayoutInflater, int paramInt)
  {
    if (paramView != null)
      return paramView;
    return paramLayoutInflater.inflate(paramInt, paramViewGroup, false);
  }

  public static <T> T a(List<T> paramList)
  {
    if (paramList == null);
    int i;
    do
    {
      return null;
      i = paramList.size();
    }
    while (i == 0);
    return paramList.remove(i - 1);
  }

  public static <Obj, Prop> Prop a(List<Obj> paramList, com.b.e<Obj, Prop> parame)
  {
    return parame.a(paramList.get(0));
  }

  public static <T> T a(List<T> paramList, Object paramObject)
  {
    if (paramList == null)
      return null;
    Iterator localIterator = paramList.iterator();
    Object localObject;
    do
    {
      if (!localIterator.hasNext())
        return null;
      localObject = (Object)localIterator.next();
    }
    while (!a(localObject, paramObject));
    return localObject;
  }

  public static <Base, Obj extends Base, Prop> Obj a(List<Obj> paramList, Prop paramProp, com.b.e<Base, Prop> parame)
  {
    if (paramList == null)
      return null;
    Iterator localIterator = paramList.iterator();
    Object localObject;
    do
    {
      if (!localIterator.hasNext())
        return null;
      localObject = (Object)localIterator.next();
    }
    while (!a(paramProp, parame.a(localObject)));
    return localObject;
  }

  public static <T> T a(T[] paramArrayOfT, int paramInt)
  {
    return paramArrayOfT[paramInt];
  }

  public static String a(Object paramObject)
  {
    if (paramObject == null)
      return null;
    return paramObject.toString();
  }

  @Deprecated
  public static String a(String paramString, Object[] paramArrayOfObject)
  {
    return String.format(paramString, paramArrayOfObject);
  }

  public static <T> List<T> a()
  {
    return Collections.emptyList();
  }

  public static List<File> a(File paramFile)
  {
    File[] arrayOfFile = paramFile.listFiles();
    if (arrayOfFile == null)
      return Collections.emptyList();
    return b(arrayOfFile);
  }

  public static List<File> a(File paramFile, FileFilter paramFileFilter)
  {
    File[] arrayOfFile = paramFile.listFiles(paramFileFilter);
    if (arrayOfFile == null)
      return Collections.emptyList();
    return b(arrayOfFile);
  }

  public static <T> List<T> a(Collection<? extends T> paramCollection)
  {
    if (paramCollection == null)
      return Collections.emptyList();
    return new ArrayList(paramCollection);
  }

  public static <T> List<T> a(Collection<T> paramCollection, com.b.e<T, Boolean> parame)
  {
    return a(paramCollection, parame, 2147483647);
  }

  public static <T> List<T> a(Collection<T> paramCollection, com.b.e<T, Boolean> parame, int paramInt)
  {
    if (paramCollection == null)
      return Collections.emptyList();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramCollection.iterator();
    while (true)
    {
      if (!localIterator.hasNext());
      Object localObject;
      do
      {
        return localArrayList;
        localObject = (Object)localIterator.next();
      }
      while (localArrayList.size() >= paramInt);
      if (((Boolean)parame.a(localObject)).booleanValue())
        localArrayList.add(localObject);
    }
  }

  public static <Obj, Prop> List<Obj> a(Collection<Obj> paramCollection, Prop paramProp, com.b.e<Obj, Prop> parame)
  {
    if (paramCollection == null)
      return Collections.emptyList();
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramCollection.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      Object localObject = (Object)localIterator.next();
      if (a(paramProp, parame.a(localObject)))
        localArrayList.add(localObject);
    }
  }

  public static <T> List<T> a(List<T> paramList, int paramInt)
  {
    if (paramList == null)
      return new ArrayList();
    if ((paramInt < 0) || (paramInt >= paramList.size()))
      return new ArrayList();
    return paramList.subList(paramInt, paramList.size());
  }

  public static void a(int paramInt)
  {
    b(paramInt * 1000);
  }

  public static void a(SpannableStringBuilder paramSpannableStringBuilder, CharSequence paramCharSequence)
  {
    if (paramCharSequence == null)
      return;
    paramSpannableStringBuilder.append(paramCharSequence);
  }

  public static void a(String paramString)
  {
    if (ax.a(paramString))
      return;
    b(new File(paramString));
  }

  public static <T> void a(Collection<T> paramCollection, af<T> paramaf)
  {
    if (paramCollection == null);
    while (true)
    {
      return;
      Iterator localIterator = paramCollection.iterator();
      while (localIterator.hasNext())
        paramaf.a((Object)localIterator.next());
    }
  }

  public static <T> void a(List<T> paramList, T[] paramArrayOfT)
  {
    int i = paramList.size();
    int j = paramArrayOfT.length;
    int k = 0;
    if (i != j)
    {
      arrayOfObject = new Object[2];
      arrayOfObject[0] = Integer.valueOf(paramList.size());
      arrayOfObject[1] = Integer.valueOf(paramArrayOfT.length);
      throw new RuntimeException(a("Source: %s, target: %s", arrayOfObject));
    }
    while (k < paramArrayOfT.length)
    {
      Object[] arrayOfObject;
      paramArrayOfT[k] = paramList.get(k);
      k++;
    }
  }

  public static void a(CountDownLatch paramCountDownLatch)
  {
    try
    {
      paramCountDownLatch.await();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new RuntimeException(localInterruptedException);
    }
  }

  public static <T> boolean a(T paramT1, T paramT2)
  {
    if ((paramT1 == null) && (paramT2 == null))
      return true;
    if ((paramT1 == null) || (paramT2 == null))
      return false;
    return paramT1.equals(paramT2);
  }

  public static boolean a(StringBuilder paramStringBuilder)
  {
    if (paramStringBuilder == null);
    while (paramStringBuilder.length() == 0)
      return true;
    return false;
  }

  public static boolean a(Map<?, ?> paramMap)
  {
    if (paramMap == null);
    while (paramMap.size() == 0)
      return true;
    return false;
  }

  public static boolean a(CountDownLatch paramCountDownLatch, long paramLong, TimeUnit paramTimeUnit)
  {
    try
    {
      boolean bool = paramCountDownLatch.await(paramLong, paramTimeUnit);
      return bool;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new RuntimeException(localInterruptedException);
    }
  }

  public static <T> T[] a(List<T> paramList, Class<?> paramClass)
  {
    Object[] arrayOfObject = (Object[])Array.newInstance(paramClass, paramList.size());
    a(paramList, arrayOfObject);
    return arrayOfObject;
  }

  public static <T> T[] a(T[] paramArrayOfT)
  {
    return paramArrayOfT;
  }

  public static <T> int b(List<T> paramList, Object paramObject)
  {
    if (paramList == null);
    while (true)
    {
      return -1;
      int i = paramList.size();
      for (int j = 0; j < i; j++)
        if (a(paramObject, paramList.get(j)))
          return j;
    }
  }

  public static <Base, Obj extends Base, Prop> int b(List<Obj> paramList, Prop paramProp, com.b.e<Base, Prop> parame)
  {
    if (paramList == null);
    while (true)
    {
      return -1;
      int i = paramList.size();
      for (int j = 0; j < i; j++)
        if (a(paramProp, parame.a(paramList.get(j))))
          return j;
    }
  }

  public static <T> long b(Collection<T> paramCollection, com.b.e<T, Long> parame)
  {
    Iterator localIterator = paramCollection.iterator();
    for (long l = 0L; ; l += ((Long)parame.a((Object)localIterator.next())).longValue())
      if (!localIterator.hasNext())
        return l;
  }

  public static <T> T b(T paramT)
  {
    if (paramT == null)
      return null;
    return c.a(paramT, "clone", new Object[0]);
  }

  public static <T> T b(List<T> paramList, int paramInt)
  {
    return paramList.get(paramInt);
  }

  public static <T> List<T> b()
  {
    return new ArrayList();
  }

  public static <T> List<T> b(T[] paramArrayOfT)
  {
    if (paramArrayOfT == null)
      return Collections.emptyList();
    return new ArrayList(Arrays.asList(paramArrayOfT));
  }

  public static <T> Set<T> b(Collection<? extends T> paramCollection)
  {
    if (paramCollection == null)
      return Collections.emptySet();
    return new HashSet(paramCollection);
  }

  public static void b(int paramInt)
  {
    long l = paramInt;
    try
    {
      Thread.sleep(l);
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new RuntimeException(localInterruptedException);
    }
  }

  public static void b(File paramFile)
  {
    if (paramFile == null)
      return;
    paramFile.setLastModified(new Date().getTime());
  }

  public static void b(String paramString)
  {
    c(new File(paramString));
  }

  public static void b(List<?> paramList)
  {
    if (paramList == null);
    while (paramList.size() == 0)
      return;
    paramList.remove(0);
  }

  public static boolean b(CountDownLatch paramCountDownLatch)
  {
    return a(paramCountDownLatch, 0L, TimeUnit.MILLISECONDS);
  }

  public static long c(String paramString)
  {
    return e(new File(paramString));
  }

  public static <Obj, Prop> List<Prop> c(Collection<Obj> paramCollection, com.b.e<Obj, Prop> parame)
  {
    if (paramCollection == null)
      return Collections.emptyList();
    ArrayList localArrayList = new ArrayList(paramCollection.size());
    Iterator localIterator = paramCollection.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      localArrayList.add(parame.a((Object)localIterator.next()));
    }
  }

  public static <T> List<T> c(List<T> paramList)
  {
    if (paramList == null)
      return null;
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return localArrayList;
      localArrayList.add(b((Object)localIterator.next()));
    }
  }

  public static <T> List<T> c(T[] paramArrayOfT)
  {
    if (paramArrayOfT == null)
      return Collections.emptyList();
    return Arrays.asList(paramArrayOfT);
  }

  public static <T> Set<T> c()
  {
    return new HashSet();
  }

  public static void c(File paramFile)
  {
    paramFile.getParentFile().mkdirs();
  }

  public static void c(Object paramObject)
  {
    try
    {
      paramObject.wait();
      return;
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new RuntimeException(localInterruptedException);
    }
  }

  public static boolean c(Collection<?> paramCollection)
  {
    if (paramCollection == null)
      return true;
    return paramCollection.isEmpty();
  }

  public static int d(Collection<?> paramCollection)
  {
    if (paramCollection == null)
      return 0;
    return paramCollection.size();
  }

  public static <T> T d(List<T> paramList)
  {
    if (paramList == null);
    while (paramList.size() == 0)
      return null;
    return paramList.get(0);
  }

  public static <T> T d(T[] paramArrayOfT)
  {
    if (paramArrayOfT == null);
    while (paramArrayOfT.length == 0)
      return null;
    return paramArrayOfT[0];
  }

  public static <K, V> Map<K, V> d()
  {
    return new HashMap();
  }

  public static void d(File paramFile)
  {
    paramFile.mkdirs();
  }

  public static boolean d(String paramString)
  {
    if (paramString == null);
    while (paramString.length() == 0)
      return true;
    return false;
  }

  public static <T> boolean d(Collection<T> paramCollection, com.b.e<T, Boolean> parame)
  {
    Iterator localIterator = paramCollection.iterator();
    boolean bool = true;
    while (true)
    {
      if (!localIterator.hasNext())
        return bool;
      bool &= ((Boolean)parame.a((Object)localIterator.next())).booleanValue();
    }
  }

  public static long e(File paramFile)
  {
    long l = 0L;
    if (!paramFile.exists());
    File[] arrayOfFile;
    do
    {
      return l;
      arrayOfFile = paramFile.listFiles();
    }
    while (arrayOfFile == null);
    int i = arrayOfFile.length;
    int j = 0;
    label27: File localFile;
    if (j < i)
    {
      localFile = arrayOfFile[j];
      if (!localFile.isDirectory())
        break label62;
    }
    label62: for (l += e(localFile); ; l += localFile.length())
    {
      j++;
      break label27;
      break;
    }
  }

  public static <T> T e(List<T> paramList)
  {
    return d(paramList);
  }

  public static <T> T e(T[] paramArrayOfT)
  {
    if (paramArrayOfT == null);
    while (paramArrayOfT.length == 0)
      return null;
    return paramArrayOfT[(-1 + paramArrayOfT.length)];
  }

  public static String e(String paramString)
  {
    return paramString;
  }

  public static <K, V> WeakHashMap<K, V> e()
  {
    return new WeakHashMap();
  }

  public static <T> boolean e(Collection<T> paramCollection, com.b.e<T, Boolean> parame)
  {
    Iterator localIterator = paramCollection.iterator();
    do
      if (!localIterator.hasNext())
        return false;
    while (!((Boolean)parame.a((Object)localIterator.next())).booleanValue());
    return true;
  }

  public static <T> T f(List<T> paramList)
  {
    if (paramList == null);
    while (paramList.size() == 0)
      return null;
    return paramList.get(-1 + paramList.size());
  }

  public static String f()
  {
    return UUID.randomUUID().toString();
  }

  public static boolean f(File paramFile)
  {
    return h().a(paramFile);
  }

  public static <T> boolean f(T[] paramArrayOfT)
  {
    if (paramArrayOfT == null);
    while (paramArrayOfT.length == 0)
      return true;
    return false;
  }

  public static h g()
  {
    return new h();
  }

  public static <T> void g(T[] paramArrayOfT)
  {
    if (paramArrayOfT == null)
      return;
    Collections.reverse(c(paramArrayOfT));
  }

  public static boolean g(File paramFile)
  {
    return h().b(paramFile);
  }

  public static boolean g(List<?> paramList)
  {
    if (paramList == null)
      return true;
    return paramList.isEmpty();
  }

  public static int h(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject == null)
      return 0;
    return paramArrayOfObject.length;
  }

  private static com.flipdog.commons.e h()
  {
    return (com.flipdog.commons.e)b.a(com.flipdog.commons.e.class);
  }

  public static boolean h(List<?> paramList)
  {
    return !g(paramList);
  }

  public static void i(List<?> paramList)
  {
    if (paramList == null)
      return;
    Collections.reverse(paramList);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.as
 * JD-Core Version:    0.6.2
 */