package my.apache.http.util;

public final class LangUtils
{
  public static final int HASH_OFFSET = 37;
  public static final int HASH_SEED = 17;

  public static boolean equals(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null)
      return paramObject2 == null;
    return paramObject1.equals(paramObject2);
  }

  public static boolean equals(Object[] paramArrayOfObject1, Object[] paramArrayOfObject2)
  {
    if (paramArrayOfObject1 == null)
      if (paramArrayOfObject2 != null);
    while (true)
    {
      return true;
      return false;
      if ((paramArrayOfObject2 == null) || (paramArrayOfObject1.length != paramArrayOfObject2.length))
        break;
      for (int i = 0; i < paramArrayOfObject1.length; i++)
        if (!equals(paramArrayOfObject1[i], paramArrayOfObject2[i]))
          return false;
    }
    return false;
  }

  public static int hashCode(int paramInt1, int paramInt2)
  {
    return paramInt2 + paramInt1 * 37;
  }

  public static int hashCode(int paramInt, Object paramObject)
  {
    if (paramObject != null);
    for (int i = paramObject.hashCode(); ; i = 0)
      return hashCode(paramInt, i);
  }

  public static int hashCode(int paramInt, boolean paramBoolean)
  {
    if (paramBoolean);
    for (int i = 1; ; i = 0)
      return hashCode(paramInt, i);
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     my.apache.http.util.LangUtils
 * JD-Core Version:    0.6.2
 */