package com.flipdog.commons.a;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ad
{
  public static <T> ArrayList<T> a(T[] paramArrayOfT)
  {
    Object localObject;
    if (paramArrayOfT == null)
      localObject = null;
    while (true)
    {
      return localObject;
      localObject = new ArrayList();
      int i = paramArrayOfT.length;
      for (int j = 0; j < i; j++)
        ((ArrayList)localObject).add(paramArrayOfT[j]);
    }
  }

  public static <T> T[] a(Class<T> paramClass, Collection<T> paramCollection)
  {
    if (paramCollection == null)
      return null;
    Object[] arrayOfObject = c.a(paramClass, paramCollection.size());
    paramCollection.toArray(arrayOfObject);
    return arrayOfObject;
  }

  public static String[] a(Collection<String> paramCollection)
  {
    if (paramCollection == null)
      return null;
    String[] arrayOfString = new String[paramCollection.size()];
    paramCollection.toArray(arrayOfString);
    return arrayOfString;
  }

  public static <T> List<T> b(T[] paramArrayOfT)
  {
    if (paramArrayOfT == null)
      return Collections.emptyList();
    return Arrays.asList(paramArrayOfT);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.a.ad
 * JD-Core Version:    0.6.2
 */