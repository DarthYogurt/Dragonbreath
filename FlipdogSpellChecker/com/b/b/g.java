package com.b.b;

import com.flipdog.commons.diagnostic.Track;
import java.lang.reflect.Method;
import java.util.Iterator;

public class g
{
  private static String a(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject == null)
      return "";
    StringBuilder localStringBuilder = new StringBuilder();
    int i = paramArrayOfObject.length;
    for (int j = 0; ; j++)
    {
      if (j >= i)
        return localStringBuilder.toString();
      Object localObject = paramArrayOfObject[j];
      if (localStringBuilder.length() != 0)
        localStringBuilder.append(", ");
      localStringBuilder.append(localObject);
    }
  }

  public static void a(Method paramMethod, Object[] paramArrayOfObject, Iterable<?> paramIterable)
  {
    Object[] arrayOfObject1 = new Object[3];
    arrayOfObject1[0] = paramMethod.getDeclaringClass().getSimpleName();
    arrayOfObject1[1] = paramMethod.getName();
    arrayOfObject1[2] = a(paramArrayOfObject);
    Track.it(String.format("fire %s.%s(%s)", arrayOfObject1), new String[] { "EventBus" });
    Iterator localIterator = paramIterable.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
        return;
      Object localObject = localIterator.next();
      Object[] arrayOfObject2 = new Object[1];
      arrayOfObject2[0] = localObject.getClass().getName();
      Track.it(String.format("    \\-> %s", arrayOfObject2), new String[] { "EventBus" });
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.b.g
 * JD-Core Version:    0.6.2
 */