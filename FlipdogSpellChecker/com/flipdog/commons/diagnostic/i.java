package com.flipdog.commons.diagnostic;

import java.util.Iterator;
import java.util.List;

public class i
{
  public static String a(List<StackTraceElement> paramList)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = paramList.iterator();
    while (true)
    {
      if (!localIterator.hasNext())
      {
        localStringBuilder.append("\n");
        return localStringBuilder.toString();
      }
      StackTraceElement localStackTraceElement = (StackTraceElement)localIterator.next();
      if (localStringBuilder.length() == 0)
        localStringBuilder.append("    '->");
      Object[] arrayOfObject = new Object[3];
      arrayOfObject[0] = localStackTraceElement.getClassName();
      arrayOfObject[1] = localStackTraceElement.getMethodName();
      arrayOfObject[2] = Integer.valueOf(localStackTraceElement.getLineNumber());
      localStringBuilder.append(String.format("\n    %s.%s:%s", arrayOfObject));
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.diagnostic.i
 * JD-Core Version:    0.6.2
 */