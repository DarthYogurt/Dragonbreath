package com.flipdog.commons.diagnostic;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class k
{
  public static StackTraceElement a(Class<?>[] paramArrayOfClass)
  {
    HashSet localHashSet = new HashSet();
    int i = paramArrayOfClass.length;
    int j = 0;
    StackTraceElement[] arrayOfStackTraceElement;
    if (j >= i)
    {
      localHashSet.add(k.class.getName());
      arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    }
    StackTraceElement localStackTraceElement;
    for (int k = 2; ; k++)
    {
      if (k >= arrayOfStackTraceElement.length)
      {
        return null;
        localHashSet.add(paramArrayOfClass[j].getName());
        j++;
        break;
      }
      localStackTraceElement = arrayOfStackTraceElement[k];
      if (!localHashSet.contains(localStackTraceElement.getClassName()))
        break label91;
    }
    label91: return localStackTraceElement;
  }

  public static String a(int paramInt)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    for (int i = paramInt + 3; ; i++)
    {
      if (i >= arrayOfStackTraceElement.length)
        return localStringBuilder.toString();
      StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i];
      String str1 = localStackTraceElement.getClassName();
      String str2 = localStackTraceElement.getMethodName();
      int j = localStackTraceElement.getLineNumber();
      localStringBuilder.append(str1 + "." + str2 + ":" + j + "\r\n");
    }
  }

  public static List<StackTraceElement> a(int paramInt, Class<?>[] paramArrayOfClass)
  {
    HashSet localHashSet = new HashSet();
    int i = paramArrayOfClass.length;
    StackTraceElement[] arrayOfStackTraceElement;
    ArrayList localArrayList;
    int k;
    for (int j = 0; ; j++)
    {
      if (j >= i)
      {
        arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
        localArrayList = new ArrayList();
        k = 2;
        if (k < arrayOfStackTraceElement.length)
          break;
        return localArrayList;
      }
      localHashSet.add(paramArrayOfClass[j].getName());
    }
    StackTraceElement localStackTraceElement = arrayOfStackTraceElement[k];
    if (localHashSet.contains(localStackTraceElement.getClassName()));
    do
    {
      k++;
      break;
      localArrayList.add(localStackTraceElement);
    }
    while (localArrayList.size() < paramInt);
    return localArrayList;
  }

  public static void a()
  {
    b();
  }

  public static void a(String[] paramArrayOfString)
  {
    a();
  }

  public static void b()
  {
    d();
    System.out.println();
    System.out.println("g() was called by " + c());
  }

  public static String c()
  {
    StackTraceElement localStackTraceElement = Thread.currentThread().getStackTrace()[4];
    String str1 = localStackTraceElement.getClassName();
    String str2 = localStackTraceElement.getMethodName();
    int i = localStackTraceElement.getLineNumber();
    return str1 + "." + str2 + ":" + i;
  }

  public static void d()
  {
    StackTraceElement[] arrayOfStackTraceElement = Thread.currentThread().getStackTrace();
    for (int i = 2; ; i++)
    {
      if (i >= arrayOfStackTraceElement.length)
        return;
      StackTraceElement localStackTraceElement = arrayOfStackTraceElement[i];
      String str1 = localStackTraceElement.getClassName();
      String str2 = localStackTraceElement.getMethodName();
      int j = localStackTraceElement.getLineNumber();
      System.out.println(str1 + "." + str2 + ":" + j);
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.flipdog.commons.diagnostic.k
 * JD-Core Version:    0.6.2
 */