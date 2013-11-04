package com.google.inject.internal;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class Join
{
  private static void appendOneEntry(Appendable paramAppendable, String paramString, Map.Entry<?, ?> paramEntry)
    throws IOException
  {
    appendOneToken(paramAppendable, paramEntry.getKey());
    paramAppendable.append(paramString);
    appendOneToken(paramAppendable, paramEntry.getValue());
  }

  private static void appendOneToken(Appendable paramAppendable, Object paramObject)
    throws IOException
  {
    paramAppendable.append(toCharSequence(paramObject));
  }

  public static <T extends Appendable> T join(T paramT, String paramString, Iterable<?> paramIterable)
  {
    return join(paramT, paramString, paramIterable.iterator());
  }

  public static <T extends Appendable> T join(T paramT, String paramString, @Nullable Object paramObject, Object[] paramArrayOfObject)
  {
    Preconditions.checkNotNull(paramArrayOfObject);
    return join(paramT, paramString, Lists.newArrayList(paramObject, paramArrayOfObject));
  }

  public static <T extends Appendable> T join(T paramT, String paramString1, String paramString2, Map<?, ?> paramMap)
  {
    Preconditions.checkNotNull(paramT);
    Preconditions.checkNotNull(paramString1);
    Preconditions.checkNotNull(paramString2);
    Iterator localIterator = paramMap.entrySet().iterator();
    if (localIterator.hasNext())
      try
      {
        appendOneEntry(paramT, paramString1, (Map.Entry)localIterator.next());
        while (localIterator.hasNext())
        {
          paramT.append(paramString2);
          appendOneEntry(paramT, paramString1, (Map.Entry)localIterator.next());
        }
      }
      catch (IOException localIOException)
      {
        throw new JoinException(localIOException, null);
      }
    return paramT;
  }

  public static <T extends Appendable> T join(T paramT, String paramString, Iterator<?> paramIterator)
  {
    Preconditions.checkNotNull(paramT);
    Preconditions.checkNotNull(paramString);
    if (paramIterator.hasNext())
      try
      {
        appendOneToken(paramT, paramIterator.next());
        while (paramIterator.hasNext())
        {
          paramT.append(paramString);
          appendOneToken(paramT, paramIterator.next());
        }
      }
      catch (IOException localIOException)
      {
        throw new JoinException(localIOException, null);
      }
    return paramT;
  }

  public static <T extends Appendable> T join(T paramT, String paramString, Object[] paramArrayOfObject)
  {
    return join(paramT, paramString, Arrays.asList(paramArrayOfObject));
  }

  public static String join(String paramString, Iterable<?> paramIterable)
  {
    return join(paramString, paramIterable.iterator());
  }

  public static String join(String paramString, @Nullable Object paramObject, Object[] paramArrayOfObject)
  {
    Preconditions.checkNotNull(paramArrayOfObject);
    return join(paramString, Lists.newArrayList(paramObject, paramArrayOfObject));
  }

  public static String join(String paramString1, String paramString2, Map<?, ?> paramMap)
  {
    return ((StringBuilder)join(new StringBuilder(), paramString1, paramString2, paramMap)).toString();
  }

  public static String join(String paramString, Iterator<?> paramIterator)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    join(localStringBuilder, paramString, paramIterator);
    return localStringBuilder.toString();
  }

  public static String join(String paramString, Object[] paramArrayOfObject)
  {
    return join(paramString, Arrays.asList(paramArrayOfObject));
  }

  private static CharSequence toCharSequence(Object paramObject)
  {
    if ((paramObject instanceof CharSequence))
      return (CharSequence)paramObject;
    return String.valueOf(paramObject);
  }

  public static class JoinException extends RuntimeException
  {
    private static final long serialVersionUID = 1L;

    private JoinException(IOException paramIOException)
    {
      super();
    }
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.google.inject.internal.Join
 * JD-Core Version:    0.6.2
 */