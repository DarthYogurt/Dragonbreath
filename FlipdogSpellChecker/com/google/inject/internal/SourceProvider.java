package com.google.inject.internal;

import java.util.ArrayList;
import java.util.List;

public class SourceProvider
{
  public static final SourceProvider DEFAULT_INSTANCE = new SourceProvider(ImmutableSet.of(SourceProvider.class.getName()));
  public static final Object UNKNOWN_SOURCE = "[unknown source]";
  private final ImmutableSet<String> classNamesToSkip;

  public SourceProvider()
  {
    this.classNamesToSkip = ImmutableSet.of(SourceProvider.class.getName());
  }

  private SourceProvider(Iterable<String> paramIterable)
  {
    this.classNamesToSkip = ImmutableSet.copyOf(paramIterable);
  }

  private static List<String> asStrings(Class[] paramArrayOfClass)
  {
    ArrayList localArrayList = Lists.newArrayList();
    int i = paramArrayOfClass.length;
    for (int j = 0; j < i; j++)
      localArrayList.add(paramArrayOfClass[j].getName());
    return localArrayList;
  }

  public StackTraceElement get()
  {
    for (StackTraceElement localStackTraceElement : new Throwable().getStackTrace())
    {
      String str = localStackTraceElement.getClassName();
      if (!this.classNamesToSkip.contains(str))
        return localStackTraceElement;
    }
    throw new AssertionError();
  }

  public SourceProvider plusSkippedClasses(Class[] paramArrayOfClass)
  {
    return new SourceProvider(Iterables.concat(this.classNamesToSkip, asStrings(paramArrayOfClass)));
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.SourceProvider
 * JD-Core Version:    0.6.2
 */