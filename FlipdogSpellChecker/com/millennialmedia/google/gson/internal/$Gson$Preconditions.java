package com.millennialmedia.google.gson.internal;

public final class $Gson$Preconditions
{
  public static void checkArgument(boolean paramBoolean)
  {
    if (!paramBoolean)
      throw new IllegalArgumentException();
  }

  public static <T> T checkNotNull(T paramT)
  {
    if (paramT == null)
      throw new NullPointerException();
    return paramT;
  }
}

/* Location:           C:\Programming\Android2Java\FlipdogSpellchecker_dex2jar.jar
 * Qualified Name:     com.millennialmedia.google.gson.internal..Gson.Preconditions
 * JD-Core Version:    0.6.2
 */