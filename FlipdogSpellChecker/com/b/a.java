package com.b;

import com.b.a.j;
import com.flipdog.commons.diagnostic.Track;
import com.flipdog.commons.diagnostic.k;
import java.util.Iterator;
import java.util.List;

public class a
{
  public static boolean a = false;
  public static boolean b = false;
  public static boolean c = false;
  public static boolean d = false;

  public static void a(String paramString, Class<?> paramClass)
  {
    if (!a);
    do
    {
      return;
      Track.it(paramString, new String[] { "Sql" });
      if (b)
      {
        StackTraceElement localStackTraceElement2 = k.a(new Class[] { a.class, j.class, paramClass });
        Object[] arrayOfObject2 = new Object[2];
        arrayOfObject2[0] = localStackTraceElement2.getClassName();
        arrayOfObject2[1] = localStackTraceElement2.getMethodName();
        Track.it(String.format("    '-> %s.%s", arrayOfObject2), new String[] { "Sql" });
      }
    }
    while (!c);
    List localList = k.a(15, new Class[] { k.class, a.class, j.class, paramClass });
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = localList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localStringBuilder.append("\n");
        Track.it(localStringBuilder.toString(), new String[] { "Sql" });
        return;
      }
      StackTraceElement localStackTraceElement1 = (StackTraceElement)localIterator.next();
      if (localStringBuilder.length() == 0)
        localStringBuilder.append("    '->");
      Object[] arrayOfObject1 = new Object[3];
      arrayOfObject1[0] = localStackTraceElement1.getClassName();
      arrayOfObject1[1] = localStackTraceElement1.getMethodName();
      arrayOfObject1[2] = Integer.valueOf(localStackTraceElement1.getLineNumber());
      localStringBuilder.append(String.format("\n    %s.%s:%s", arrayOfObject1));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.b.a
 * JD-Core Version:    0.6.2
 */